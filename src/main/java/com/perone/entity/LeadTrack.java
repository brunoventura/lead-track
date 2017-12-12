package com.perone.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.perone.entity.helper.ObjectIdJsonSerializer;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(value = "leadTrack", noClassnameStored = true)
@Indexes({
        @Index(fields = @Field(value = "idLead", type = IndexType.ASC)),
        @Index(fields = @Field(value = "leadRef.idLead", type = IndexType.ASC))
})
public class LeadTrack implements Serializable {

    @Id
    @NotNull
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @NotNull
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId idLead;

    @NotNull
    private String url;

}
