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
import com.hms.model.Menu;
import com.hms.repository.MenuRepository;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    // Get All Notes
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/menus")
    public Menu createMenu(@Valid @RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    // Get a Single Note
    @GetMapping("/menus/{id}")
    public Menu getMenuById(@PathVariable(value = "id") Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));
    }

    // Update a Note
    @PutMapping("/menus/{id}")
    public Menu updateMenu(@PathVariable(value = "id") Long menuId,
                                            @Valid @RequestBody Menu menuDetails) {

    	Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));

    	menu.setFoodName(menuDetails.getFoodName());
    	menu.setFoodDesc(menuDetails.getFoodDesc());
    	menu.setFoodCost(menuDetails.getFoodCost());
    	menu.setHotelName(menuDetails.getHotelName());



        Menu updatedMenu = menuRepository.save(menu);
        return updatedMenu;
    }
    

    // Delete a Note
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable(value = "id") Long menuId) {
    	Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));

    	menuRepository.delete(menu);

        return ResponseEntity.ok().build();
    }
    
}