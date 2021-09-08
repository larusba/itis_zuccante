package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Badge")
public class Badge extends AbstractAuditingEntity implements Serializable {
	
	@Id
	private String id;
	
	private String name;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Badge [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}