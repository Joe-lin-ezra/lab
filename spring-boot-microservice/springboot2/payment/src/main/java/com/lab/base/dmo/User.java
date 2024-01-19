package com.lab.base.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User
{
	@Id
	public UUID uuid;

	public String username;

	public String password;

	public String email;

	@OneToOne
	@JoinColumn( name = "author_id" )
	public Author author;

}
