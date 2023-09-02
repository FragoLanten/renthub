package ru.jdbcfighters.renthub.domain.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
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
    private List<AttributeValue> atributeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<AttributeValue> getAtributeValue() {
        return atributeValue;
    }

    public void setAtributeValue(List<AttributeValue> atributeValue) {
        this.atributeValue = atributeValue;
    }
}
