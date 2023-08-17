package ru.jdbcfighters.renthub.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
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
    private Float price;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "is_moderated")
    private Boolean moderated;

    @ManyToOne
    @JoinColumn(name = "owner_client_id")
    @JsonBackReference
    private Client owner;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Wishlist> wishlistSet = new HashSet<>();

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<EstateAttributeValue> estateAttributeValues = new HashSet<>();

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Deal> deals = new HashSet<>();

}
