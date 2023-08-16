package ru.jdbcfighters.renthub.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "owner", "address", "wishlistSet", "estateAttributeValues", "deals"
})
@Entity
@Table(name = "estate")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer number;

    @Column
    private Float square;

    @Column
    private Float price;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column
    private Boolean visible;

    @Column
    private Integer rank;

    @Column(name = "is_moderated")
    private Boolean moderated;

    @ManyToOne
    @JoinColumn(name = "owner_client_id")
    @JsonBackReference
    @ToString.Exclude
    private Client owner;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonBackReference
    @ToString.Exclude
    private Address address;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private Set<Wishlist> wishlistSet = Collections.emptySet();

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private Set<EstateAttributeValue> estateAttributeValues = Collections.emptySet();

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private Set<Deal> deals = Collections.emptySet();

}
