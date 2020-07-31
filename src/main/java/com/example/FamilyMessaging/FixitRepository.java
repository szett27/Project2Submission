package com.example.FamilyMessaging;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FixitRepository extends CrudRepository <Fixit, Long> {

    @Query(value = "SELECT * FROM need_to_fix WHERE id = :id", nativeQuery = true)
    public Fixit findbyId(Long id);
}
