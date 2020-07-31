package com.example.FamilyMessaging;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChristmasRepository extends CrudRepository<Christmas, Long> {
    @Query(value = "SELECT * FROM christmas WHERE id = :id", nativeQuery = true)
    public User byChristmasId(Long id);
}
