package com.lab.base.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author
{
	@Id
	@Column(name = "uuid")
	private UUID uuid;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "brief_intro")
	private String briefIntro;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "user_uuid" )
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Book> books = new ArrayList<>();

}
