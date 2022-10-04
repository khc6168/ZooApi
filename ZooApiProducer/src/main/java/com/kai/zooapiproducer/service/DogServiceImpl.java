package com.kai.zooapiproducer.service;

import com.kai.zooapiproducer.entity.DogEntity;
import com.kai.zooapiproducer.pojo.Dog;
import com.kai.zooapiproducer.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

    @Override
    public DogEntity saveDog(Dog dog) {
        DogEntity newDogEntity = DogEntity.builder().breeds(dog.getBreeds()).name(dog.getName()).age(dog.getAge()).build();
        DogEntity dogEntity = dogRepository.save(newDogEntity);
        return dogEntity;
    }

    @Override
    public List<DogEntity> getDogByName(String name) {
        List<DogEntity> dogEntity = dogRepository.findByName(name);
        return dogEntity == null ? null:dogEntity;
    }

    @Override
    public List<DogEntity> getDogByBreeds(String species) {
        List<DogEntity> dogEntity = dogRepository.findByBreeds(species);
        return dogEntity == null ? null:dogEntity;
    }
}
