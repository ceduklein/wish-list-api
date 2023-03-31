package br.senai.sc.wishlistapi.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "wish_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {

	@Id
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "items")
	private String[] items;
	
	@Column(name = "is_completed")
	private boolean is_completed;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updated_at;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;
}
