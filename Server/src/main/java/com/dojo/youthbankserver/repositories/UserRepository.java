package com.dojo.youthbankserver.repositories;

import com.dojo.youthbankserver.entities.Professional;
import com.dojo.youthbankserver.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);

//  Object findAll(Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE first_name LIKE %:kw%", nativeQuery = true)
    List<User> searchUser(@Param("kw") String keyword);
}
