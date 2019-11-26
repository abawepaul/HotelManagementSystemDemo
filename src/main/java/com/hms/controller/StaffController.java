package com.hms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.exception.ResourceNotFoundException;
import com.hms.model.Staff;
import com.hms.repository.StaffRepository;

@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    StaffRepository staffRepository;

    // Get All Notes
    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/staff")
    public Staff createStaff(@Valid @RequestBody Staff staff) {
        return staffRepository.save(staff);
    }

    // Get a Single Note
    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable(value = "id") Long staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", staffId));
    }

    // Update a Note
    @PutMapping("/staff/{id}")
    public Staff updateStaff(@PathVariable(value = "id") Long staffId,
                                            @Valid @RequestBody Staff staffDetails) {

    	Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", staffId));

    	staff.setEmpName(staffDetails.getEmpName());
    	staff.setEmpEmail(staffDetails.getEmpEmail());
    	staff.setEmpProfession(staffDetails.getEmpProfession());
    	staff.setHotelName(staffDetails.getHotelName());

    	
        Staff updatedStaff = staffRepository.save(staff);
        return updatedStaff;
    }

    // Delete a Note
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") Long staffId) {
    	Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff", "id", staffId));

    	staffRepository.delete(staff);

        return ResponseEntity.ok().build();
    }
    
    
}