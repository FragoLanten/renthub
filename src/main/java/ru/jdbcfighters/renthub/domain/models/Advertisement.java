package ru.jdbcfighters.renthub.domain.models;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "advertisments")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "is_moderated")
    private Boolean moderated;

    @OneToOne(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Estate estate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
