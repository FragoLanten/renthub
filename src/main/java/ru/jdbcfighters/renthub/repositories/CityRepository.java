package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jdbcfighters.renthub.domain.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
