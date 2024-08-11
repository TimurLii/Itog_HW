package org.example.services;


import org.example.repo.AnimalRepository;
import org.example.repo.IAnimalRepository;

public class AnimalServices implements IAnimalServices {
    IAnimalRepository iAnimalRepository = new AnimalRepository();

    @Override
    public void addAnimal() {

    }

}
