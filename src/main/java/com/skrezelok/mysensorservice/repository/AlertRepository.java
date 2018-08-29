package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

}
