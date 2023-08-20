package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.jdbcfighters.renthub.domain.models.Revenue;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("SELECT rev FROM Revenue rev Join fetch rev.user u where u.id = :userId")
    List<Revenue> findRevenueByUserId(Long userId);

    @Query("SELECT rev From Revenue rev where rev.date = :localDate")
    List<Revenue> findRevenueByDate(LocalDate localDate);

}
