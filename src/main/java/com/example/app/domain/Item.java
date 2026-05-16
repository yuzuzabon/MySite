package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Item {

		private Integer id;

		@NotBlank
		@Size(max=50)
		private String name;

		@NotNull
		@Range(min=0)
		private Integer amount;
		private Location location;
		private String note;
		private LocalDateTime registered;
		private LocalDateTime updated;

}
