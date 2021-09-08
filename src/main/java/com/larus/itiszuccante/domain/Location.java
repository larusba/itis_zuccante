package com.larus.itiszuccante.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.client.model.geojson.Polygon;

import java.io.Serializable;

@Document(collection = "location")
public class Location extends AbstractAuditingEntity implements Serializable {
    
	private String description;
	
    private Polygon polygon;

}
