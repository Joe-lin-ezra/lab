package com.lab.base.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book
{
	@Id
	public UUID uuid;

	public String title;

	public String subtitle;

	public String content;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "author_id" )
	public Author author;
}

