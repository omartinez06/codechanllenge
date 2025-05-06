package com.oscarmartinez.codechanllenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oscarmartinez.codechanllenge.model.CallLog;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
}