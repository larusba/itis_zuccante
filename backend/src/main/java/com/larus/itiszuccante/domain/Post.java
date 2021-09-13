package com.larus.itiszuccante.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_post")
public class Post extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    private String type;

    @NotNull
    private String group;

    @Size(min = 1, max = 10_000)
    @NotNull
    private String content;

    private String location;

    @JsonIgnore
    private HashSet<String> images = new HashSet<>();

    private boolean locked = false;

    @Field("locked_by")
    private String lockedBy;

    @Field("admin_locked")
    private boolean adminLocked = false;

    public Post() {}

    public Post(String type, String group, String content) {
        this.type = type;
        this.group = group;
        this.content = content;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public HashSet<String> getImages() {
        return images;
    }

    public void setImages(HashSet<String> images) {
        this.images = images;
    }

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
            ", type='" + type + '\'' +
            ", groupID='" + group + '\'' +
            ", content='" + content + '\'' +
            ", location='" + location + '\'' +
            ", locked=" + locked +
            '}';
    }
}
