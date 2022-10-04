package com.kai.zooapiproducer.controller;

import com.kai.zooapiproducer.entity.DogEntity;
import com.kai.zooapiproducer.pojo.Dog;
import com.kai.zooapiproducer.service.DogService;
import com.kai.zooapiproducer.vo.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/dog")
public class DogController {

    @Autowired
    DogService dogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dog createDog(@Validated @RequestBody Dog dog) {
        DogEntity dogEntity = dogService.saveDog(dog);
        return Dog.builder()
                .breeds(dogEntity.getBreeds())
                .name(dogEntity.getName())
                .age(dogEntity.getAge())
                .build();
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List getDogByName(@PathVariable String name) {
        List<DogEntity> dogEntities = dogService.getDogByName(name);

        if (dogEntities == null) {
            throw new NoSuchElementException(name + " not exists");
        }

        List<Dog> dogs = new ArrayList<>();

        for (DogEntity dogEntity: dogEntities){
            dogs.add(Dog.builder()
                    .breeds(dogEntity.getBreeds())
                    .name(dogEntity.getName())
                    .age(dogEntity.getAge())
                    .build());
        }

        return dogs;
    }

    @GetMapping("/breeds/{breeds}")
    @ResponseStatus(HttpStatus.OK)
    public List getDogBySpecies(@PathVariable String breeds) {
        List<DogEntity> dogEntities = dogService.getDogByBreeds(breeds);

        if (dogEntities == null) {
            throw new NoSuchElementException(breeds + " not exists");
        }

        List<Dog> dogs = new ArrayList<>();

        for (DogEntity dogEntity: dogEntities){
            dogs.add(Dog.builder()
                    .breeds(dogEntity.getBreeds())
                    .name(dogEntity.getName())
                    .age(dogEntity.getAge())
                    .build());
        }

        return dogs;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerNoSuchElement(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
