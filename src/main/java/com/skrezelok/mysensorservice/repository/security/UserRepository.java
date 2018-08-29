package com.skrezelok.mysensorservice.repository.security;


import com.skrezelok.mysensorservice.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    void delete(User user);
}
