package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.jdbcfighters.renthub.domain.models.DealStatus;
import ru.jdbcfighters.renthub.domain.models.enums.Status;

import java.util.Optional;

public interface DealStatusRepository extends JpaRepository<DealStatus, Long> {

    @Query("select d from DealStatus d where d.status = ?1")
    Optional<DealStatus> findByTypeStatus(Status dealStatus);
}
