package com.diwash.OneToMany.controller;

import com.diwash.OneToMany.Models.Manufactures;
import com.diwash.OneToMany.exception.ResourceNotFoundException;
import com.diwash.OneToMany.repository.ManufacturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/manufactures")
public class ManufacturesController {
    @Autowired
    private ManufacturesRepository manufacturesRepository;

    @GetMapping
    public List<Manufactures> getAllManufactures(){
        return manufacturesRepository.findAll();
    }

    // create employee REST API
    @PostMapping
    public Manufactures createManufactures(@RequestBody Manufactures manufactures) {
        return manufacturesRepository.save(manufactures);
    }

    // get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Manufactures> getManufacturesById(@PathVariable  long id){
        Manufactures manufactures = manufacturesRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufactures not exist with id:" + id));
        return ResponseEntity.ok(manufactures);
    }

    // update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Manufactures> updateManufactures(@PathVariable long id,@RequestBody Manufactures manufactures) {
        Manufactures updateManufactures = manufacturesRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufactures not exist with id: " + id));

        updateManufactures.setId(manufactures.getId());
        updateManufactures.setManufactures_name(manufactures.getManufactures_name());

        manufacturesRepository.save(updateManufactures);

        return ResponseEntity.ok(updateManufactures);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteManufactures(@PathVariable long id){

        Manufactures manufactures = manufacturesRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufactures not exist with id: " + id));

        manufacturesRepository.delete(manufactures);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

