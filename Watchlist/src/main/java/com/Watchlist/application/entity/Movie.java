package com.Watchlist.application.entity;

import org.hibernate.validator.constraints.Range;

import com.Watchlist.application.entity.validations.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Please enter the Title.")
	@Size(min = 1,message = "Title must not be empty.")
	private String title;
	
	@Size(max = 50, message = "There are max 50 characters accepted." )
	private String comment;
	
	@Range(min = 5,max=10,message = "Plese enter the Range from 5.0 to 10.0.")
	private float rating;
	
	@Priority
	private String priority;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
