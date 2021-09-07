package com.larus.itiszuccante.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larus.itiszuccante.config.Constants;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "group")
public class Group extends AbstractAuditingEntity implements Serializable {

    @Id
    private String id;

    private String admin;

    private List<String> members;

    private String name;

    private String description;

    private List<Location> location;

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

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Group{" +
            "id='" + id + '\'' +
            ", admin='" + admin + '\'' +
            ", members=" + Arrays.toString(members) +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", location=" + location +
            '}';
    }
}
