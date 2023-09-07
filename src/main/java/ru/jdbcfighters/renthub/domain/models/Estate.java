package ru.jdbcfighters.renthub.domain.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@EqualsAndHashCode
@ToString
@NamedEntityGraph(
        name = "estate-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("owner"),
                @NamedAttributeNode("street"),
                @NamedAttributeNode("deal"),
                @NamedAttributeNode("advertisement"),
                @NamedAttributeNode("city"),
                @NamedAttributeNode("attributes")
        })
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "estate")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "square")
    private Float square;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name = "street_id")
    @JsonBackReference
    private Street street;

    @OneToOne
    @JoinColumn(name = "advertisment_id")
    @JsonBackReference
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;

    @OneToOne(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Deal deal;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "estate_id")
    )
    @JsonIgnore
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "estate_attribute_value",
            joinColumns = @JoinColumn(name = "estate_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> attributes;

    @ManyToMany
    @JoinTable(
            name = "estate_attribute_value",
            joinColumns = @JoinColumn(name = "estate_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id")
    )
    private List<AttributeValue> attributeValue;

}
