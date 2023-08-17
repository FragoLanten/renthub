package ru.jdbcfighters.renthub.models.cpk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class EstateAttributeValueId implements Serializable {

    @Column(name = "estate_id")
    private Long estateId;

    @Column(name = "attribute_id")
    private Long attributeId;

    @Column(name = "value_id")
    private Long valueId;

}
