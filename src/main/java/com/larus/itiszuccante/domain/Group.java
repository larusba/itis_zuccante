package com.larus.itiszuccante.domain;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group")
public class Group extends AbstractAuditingEntity implements Serializable {
	
	@Id
	private String id;
	
	private String admin;
	
	private String[] members;
	
	private String name;
	
	private String description;
	
	private Location[] location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location[] getLocation() {
		return location;
	}

	public void setLocation(Location[] location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", admin=" + admin + ", members=" + Arrays.toString(members) + ", name=" + name
				+ ", description=" + description + ", location=" + Arrays.toString(location) + "]";
	}

}