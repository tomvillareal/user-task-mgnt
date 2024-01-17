package com.cci.usertaskmngt.repository;

import com.cci.usertaskmngt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
