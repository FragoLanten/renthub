package ru.jdbcfighters.renthub.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "estate", "type", "status", "buyer", "employee"
})
@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float cost;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    @JsonBackReference
    @ToString.Exclude
    private Estate estate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    @ToString.Exclude
    private DealType type;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @JsonBackReference
    @ToString.Exclude
    private DealStatus status;

    @ManyToOne
    @JoinColumn(name = "buyer_client_id")
    @JsonBackReference
    @ToString.Exclude
    private Client buyer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    @ToString.Exclude
    private Employee employee;

}
