package com.kai.zooapiproducer.repository;

import com.kai.zooapiproducer.entity.DogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends MongoRepository<DogEntity, Integer> {
    public List<DogEntity> findByName(String name);

    public List<DogEntity> findByBreeds(String species);
}
