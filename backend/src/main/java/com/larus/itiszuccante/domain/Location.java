package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.client.model.geojson.Polygon;

@Document(collection = "location")
public class Location extends AbstractAuditingEntity implements Serializable {

	private String description;

    private Polygon polygon;

}
