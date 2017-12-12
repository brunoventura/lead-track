package com.perone.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.perone.entity.helper.ObjectIdJsonSerializer;
import com.sun.istack.internal.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(value = "lead", noClassnameStored = true)
@Indexes({
        @Index(fields = @Field(value = "idLead", type = IndexType.ASC))
})
public class Lead implements Serializable {

    @Id
    @NotNull
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    @NotNull
    @Size(min = 4, max = 100)
    private String name;

    @NotNull
    @Size(min = 4, max = 100)
    private String email;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode()
    public static class LeadRef {

        @NotNull
        @JsonSerialize(using = ObjectIdJsonSerializer.class)
        private ObjectId id;

    }

    public LeadRef myRef() {
        LeadRef ref = new LeadRef();
        ref.setId(this.id);
        return ref;
    }

}
