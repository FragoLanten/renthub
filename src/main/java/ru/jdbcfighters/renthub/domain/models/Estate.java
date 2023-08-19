package ru.jdbcfighters.renthub.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
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
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Street street;

    @OneToOne
    @JoinColumn(name = "advertisement_id")
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
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "estate_attribute_value",
            joinColumns = @JoinColumn(name = "estate_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;

}
