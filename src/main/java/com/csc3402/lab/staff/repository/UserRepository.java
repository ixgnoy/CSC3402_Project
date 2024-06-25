package com.csc3402.lab.staff.repository;

import com.csc3402.lab.staff.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM Users WHERE User_Id = :id", nativeQuery = true)
    User findUserById(@Param("id") int id);

    User findByEmail(String email);

}
