package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.User;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EstateRepo extends JpaRepository<Estate, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "estate_entity-graph",
            attributePaths = {"owner", "street", "deal", "advertisement", "city", "attributes"})
    List<Estate> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "estate_entity-graph",
            attributePaths = {"owner", "street", "deal", "advertisement", "city", "attributes"})
    Optional<Estate> findById(long id);
}







