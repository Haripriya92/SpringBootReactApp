package org.launchcode.givewise.models.data;

import org.launchcode.givewise.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query("SELECT s FROM Subscription s WHERE s.email = :email")
    Subscription findByEmail(@Param("email") String email);

    @Query("SELECT s FROM Subscription s")
    List<Subscription> findAllSubscribers();
}
