package com.larus.itiszuccante.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larus.itiszuccante.config.Constants;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_post")
public class Post extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("author")
    private String authorID;

    @NotNull
    private String type;

    @NotNull
    @Field("group")
    private String groupID;

    @Size(min = 1, max = 10_000)
    private String content;

    private String location;

    @JsonIgnore
    private HashSet<String> images = new HashSet<>();

    private boolean locked = false;

    @Field("locked_by")
    private String lockedBy;

    @Field("admin_locked")
    private boolean adminLocked = false;

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

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public boolean isAdminLocked() {
        return adminLocked;
    }

    public void setAdminLocked(boolean adminLocked) {
        this.adminLocked = adminLocked;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public HashSet<String> getImages() {
        return images;
    }

    public void setImages(HashSet<String> images) {
        this.images = images;
    }

    // TODO: 07/09/21 createdAt, getters/setters for ID unfurling

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        return id != null && id.equals(((Post) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Post{" +
            "id='" + id + '\'' +
            ", authorID=" + authorID +
            ", type='" + type + '\'' +
            ", groupID='" + groupID + '\'' +
            ", content='" + content + '\'' +
            ", location='" + location + '\'' +
            ", locked=" + locked +
            '}';
    }
}
