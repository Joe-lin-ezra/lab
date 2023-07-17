package com.lab.sso.repository.jpql;

import com.lab.base.dmo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
	@Query( value = "SELECT * FROM public.users users WHERE users.uuid = ?1", nativeQuery = true )
	User findByUuid( UUID uuid );

	@Query( value = "SELECT * FROM public.users users WHERE users.email = ?1", nativeQuery = true )
	User findByEmail( String email );

	@Query( value = "SELECT (COUNT(*) > 0) FROM public.users users WHERE users.token = ?1", nativeQuery = true )
	Boolean existsByToken( String token );
}
