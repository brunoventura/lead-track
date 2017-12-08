package com.perone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(value = "user", noClassnameStored = true)
public class User implements Serializable {

    @Id
    private Long id;

    @NotNull
    @Size(min = 4, max = 100)
    private String name;

    @NotNull
    @Size(min = 4, max = 100)
    private String email;

    @JsonIgnore
    private String password;

}
