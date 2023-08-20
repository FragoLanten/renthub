package ru.jdbcfighters.renthub.repositories;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.jdbcfighters.renthub.domain.models.Attribute;

@Repository
public interface AttrubuteRepository extends JpaRepository<Attribute, Long> {
    @Query("DELETE FROM Attribute a WHERE a.id = :id")
    @Modifying
    void deleteAttribute(Long id);

    @Query("UPDATE Attribute a SET a.name = :name WHERE a.id = :id")
    @Modifying
    void inactiveAttribute(Long id, String name);
}
