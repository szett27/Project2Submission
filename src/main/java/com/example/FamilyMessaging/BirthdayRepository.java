package com.example.FamilyMessaging;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BirthdayRepository extends CrudRepository<Birthday, Long> {
    @Query(value = "SELECT * FROM birthday WHERE id = :id", nativeQuery = true)
    public User byBirthdayId(Long id);
}
