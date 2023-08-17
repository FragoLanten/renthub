package ru.jdbcfighters.renthub.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.jdbcfighters.renthub.models.cpk.EstateAttributeValueId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "estate_attribute_value")
public class EstateAttributeValue {

    @EmbeddedId
    private EstateAttributeValueId id;

    @ManyToOne
    @MapsId("attributeId")
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    private Attribute attribute;

    @ManyToOne
    @MapsId("estateId")
    @JoinColumn(name = "estate_id")
    @JsonBackReference
    private Estate estate;

    @ManyToOne
    @MapsId("attributeId")
    @JoinColumn(name = "value_id")
    @JsonBackReference
    private AttributeValue attributeValue;

}
