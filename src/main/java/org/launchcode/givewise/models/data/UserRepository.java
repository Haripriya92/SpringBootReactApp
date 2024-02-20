package org.launchcode.givewise.models.data;

import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    public User findByEmailIgnoreCase(String email);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
  public boolean existsByEmail(String email);
    Optional<User> getUserByuserName(String username);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findByEmail(String email);
}
