package com.lab.sso.repository.criteria;

import com.lab.base.dmo.Author;
import com.lab.base.dmo.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserCriteriaRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findByEmail( String email )
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery( User.class );
		Root<User> userRoot = query.from( User.class );
		Join<User, Author> authorJoin = userRoot.join( "author" );

		query.select( userRoot )
				.where( cb.equal( authorJoin.get( "email" ), email ) );

		return entityManager.createQuery( query ).getResultList();
	}
}
