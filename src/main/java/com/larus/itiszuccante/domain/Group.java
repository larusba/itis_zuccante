package com.larus.itiszuccante.domain;

import java.io.Serializable;
import java.util.HashSet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_group")
public class Group extends AbstractAuditingEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    private String admin;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Size(min = 1, max = 500)
    @NotNull
    private String description;

    private boolean closed = false;

    private boolean terminated = false;

    private boolean secret = false;

    public Group() {}

    public Group(String name, String description) {
        this.admin = "user-1";
        this.name = name;
        this.description = description;
    }

    @JsonIgnore
    private HashSet<User> members = new HashSet<>();

    @JsonIgnore
    private HashSet<User> moderators = new HashSet<>();

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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public HashSet<User> getMembers() {
        return members;
    }

    public void setMembers(HashSet<User> members) {
        this.members = members;
    }

    public HashSet<User> getModerators() {
        return moderators;
    }

    public void setModerators(HashSet<User> moderators) {
        this.moderators = moderators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id.equals(group.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return (
            "Group{" +
            "id='" +
            id +
            '\'' +
            ", admin='" +
            admin +
            '\'' +
            ", name='" +
            name +
            '\'' +
            ", description='" +
            description +
            '\'' +
            ", closed=" +
            closed +
            ", terminated=" +
            terminated +
            ", secret=" +
            secret +
            '}'
        );
    }
}
