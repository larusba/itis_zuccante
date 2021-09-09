package com.larus.itiszuccante.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suggestion")
public class Suggestion {

    @Id
    private String id;

    private String type;

    private String description;
    
    public Suggestion() {
    	
    }
    
    public Suggestion(String type, String description) {
    	this.type = type;
    	this.description = description;
    } 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Suggestion [id=" + id + ", type=" + type + ", description=" + description + "]";
    }
}
