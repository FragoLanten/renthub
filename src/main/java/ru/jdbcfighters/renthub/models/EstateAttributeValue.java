package ru.jdbcfighters.renthub.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "attribute", "estate", "attributeValue"
})
@Entity
@Table(name = "estate_attribute_value")
public class EstateAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    @ToString.Exclude
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    @JsonBackReference
    @ToString.Exclude
    private Estate estate;

    @ManyToOne
    @JoinColumn(name = "value_id")
    @JsonBackReference
    @ToString.Exclude
    private AttributeValue attributeValue;

}
