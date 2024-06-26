package com.advantal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tnc_policy")
public class TncAndPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
	
	@Column(name ="Name")
	private String name;
	
	@Column(name = "Description")
	@Lob
	private String description;

}
