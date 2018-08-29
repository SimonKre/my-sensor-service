package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.SensorType;
import com.skrezelok.mysensorservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
