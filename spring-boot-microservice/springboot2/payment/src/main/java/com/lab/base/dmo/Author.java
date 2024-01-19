package com.lab.base.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author
{
	@Id
	public UUID uuid;

	public String nickname;

	public String briefIntro;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "user_uuid" )
	public User user;

	@OneToMany( mappedBy = "author" )
	private List<Book> books = new ArrayList<>();

}
