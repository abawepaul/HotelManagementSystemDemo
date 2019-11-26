package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.model.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
