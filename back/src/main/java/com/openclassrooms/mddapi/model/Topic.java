package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	private String title;

	@NotNull
	@Size(max = 500)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "subscriptions",
			joinColumns = @JoinColumn( name = "topic_id"),
			inverseJoinColumns = @JoinColumn( name = "user_id") )
	private List<User> users;

}
