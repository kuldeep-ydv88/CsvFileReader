package com.example.Emailsender.Repository;

import com.example.Emailsender.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE MONTH(u.birthDate) = ?1 AND DAY(u.birthDate) = ?2")
    List<User> findByBirthDate(int month, int day);
}


