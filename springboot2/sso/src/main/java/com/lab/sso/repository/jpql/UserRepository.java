package com.lab.sso.repository.jpql;

import com.lab.base.dmo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//import org.springframework.data.r2dbc.repository.R2dbcRepository;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import org.springframework.validation.annotation.Validated;
//import reactor.core.publisher.Mono;
//import javax.validation.constraints.NotNull;
//import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
	@Query( value = "SELECT * FROM public.users users WHERE users.uuid = ?1", nativeQuery = true )
//	Mono<User> findByUuid( UUID uuid );
	User findByUuid( UUID uuid );

	@Query( value = "SELECT * FROM public.users users WHERE users.email = ?1", nativeQuery = true )
//	Mono<User> findByEmail( String email );
	User findByEmail( String email );

	@Query( value = "SELECT (COUNT(*) > 0) FROM public.users users WHERE users.token = ?1", nativeQuery = true )
//	Mono<Boolean> existsByToken( String token );
	Boolean existsByToken( String token );

}
