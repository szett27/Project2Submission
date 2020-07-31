package com.example.FamilyMessaging;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BuyitRepository extends CrudRepository<Buyit, Long> {

//    @Query(value = "SELECT * FROM need_to_buy WHERE id = :id", nativeQuery = true)
//    public Buyit findById(Long id);
}
