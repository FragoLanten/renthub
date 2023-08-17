package ru.jdbcfighters.renthub.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "estate_attribute_value")
public class EstateAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    @JsonBackReference
    private Estate estate;

    @ManyToOne
    @JoinColumn(name = "value_id")
    @JsonBackReference
    private AttributeValue attributeValue;

}
