package com.diwash.OneToMany.controller;

import com.diwash.OneToMany.Models.Model;
import com.diwash.OneToMany.exception.ResourceNotFoundException;
import com.diwash.OneToMany.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/models")
public class ModelsController {
    @Autowired
    private ModelRepository modelRepository;

    @GetMapping
    public List<Model> getAllModels(){
        return modelRepository.findAll();
    }

    // create models REST API
    @PostMapping
    public Model createModel(@RequestBody Model model) {
        return modelRepository.save(model);
    }

    // get models by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Model> getModelById(@PathVariable  long id){
        Model model = modelRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not exist with id:" + id));
        return ResponseEntity.ok(model);
    }

    // update models REST API
    @PutMapping("{id}")
    public ResponseEntity<Model> updateModel(@PathVariable long id,@RequestBody Model modelDetails) {
        Model updateModel = modelRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not exist with id: " + id));

        updateModel.setModel_id(modelDetails.getModel_id());
        updateModel.setName(modelDetails.getName());
        updateModel.setOb(modelDetails.getOb());

        modelRepository.save(updateModel);

        return ResponseEntity.ok(updateModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable long id){

        Model model = modelRepository.findById((int) id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not exist with id: " + id));

        modelRepository.delete(model);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

