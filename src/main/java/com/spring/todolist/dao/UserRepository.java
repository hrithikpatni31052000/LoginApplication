package com.spring.todolist.dao;


import com.spring.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query(value = "SELECT * FROM user u WHERE u.user_name = ?1", nativeQuery = true)
    User findByUsername(String userName);
}
