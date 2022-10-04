package com.kai.zooapiproducer.service;

import com.kai.zooapiproducer.entity.DogEntity;
import com.kai.zooapiproducer.pojo.Dog;

import java.util.List;

public interface DogService {


    public DogEntity saveDog(Dog dog);

    public List<DogEntity> getDogByName(String name);

    public List<DogEntity> getDogByBreeds(String species);
}
