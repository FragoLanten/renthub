package ru.jdbcfighters.renthub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
    @Query("DELETE FROM AttributeValue as av WHERE av.id = :id")
    @Modifying
    void deleteAttributeValue(Long id);

    @Query("UPDATE AttributeValue av SET av.name = :name WHERE av.id = :id")
    @Modifying
    void inactiveAttributeValue(Long id, String name);
}
