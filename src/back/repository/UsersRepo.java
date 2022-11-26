package com.qwerty.practice.repository;
import com.qwerty.practice.entity.Movie;
import com.qwerty.practice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    List<Users> findAllBy();
    Users findByUsername(String username);
    Users findByUsernameAndPassword(String username, String password);
    Users findByEmail(String email);
    Users findByEmailAndUsername(String username, String email);
    void deleteByUserid(Integer userid);

    @Query(value = """
            SELECT *
            FROM users
            WHERE userid = ?1 ;""",
            nativeQuery = true)
    Users getByUserid(Integer userId);

    @Transactional
    @Modifying
    @Query(value = """
            call change_password(?1, ?2, ?3) ;""",
            nativeQuery = true)
    void change_password(Integer userid, String new_password, String old_password);

    @Transactional
    @Modifying
    @Query(value = """
            call profile_settings(:userid, :new_username, :new_firstname, :new_lastname, :new_email) ;""",
            nativeQuery = true)
    void profile_settings(Integer userid, String new_username, String new_firstname, String new_lastname, String new_email);
}



