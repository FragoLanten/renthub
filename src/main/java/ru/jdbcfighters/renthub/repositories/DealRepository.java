package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.jdbcfighters.renthub.domain.models.Deal;
import ru.jdbcfighters.renthub.domain.models.enums.Status;
import ru.jdbcfighters.renthub.domain.models.enums.Type;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "deal-entity-graph")
    Optional<List<Deal>> getDealsByDealType_Type(Type dealType_type);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "deal-entity-graph")
    Optional<List<Deal>> getDealsByDealStatus_Status(Status dealStatus);

}
