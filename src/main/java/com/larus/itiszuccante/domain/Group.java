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
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_group")
public class Group extends AbstractAuditingEntity implements Serializable {
    
    @MongoId
    private String id;

    @Size(min = 1, max = 100)
    @NotNull
    private String admin;

    private String name;

    @Size(min = 1, max = 500)
    @NotNull
    private String description;

    @Field("group_created_by")
    @NotNull
    private String creatorID;

    @Field("admin_id")
    @NotNull
    private String adminID;

    private boolean closed = false;

    private boolean terminated = false;

    private boolean secret = false;

    // TODO: moderators, coordinates, createdAt

    @JsonIgnore
    private HashSet<User> members = new HashSet<>();

    @JsonIgnore
    private HashSet<User> moderators = new HashSet<>();
    
}
