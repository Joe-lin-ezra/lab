package com.lab.base.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User
{
	@Id
	@Column(name = "uuid")
	private UUID uuid;

	@Column( name = "email", nullable = false, unique = true, length = 100 )
	private String email;

	@Column( name = "password", nullable = false)
	private String password;

	@Column(name = "token")
	private String token;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	@OneToOne
	@JoinColumn( name = "author_id" )
	private transient Author author;

}
