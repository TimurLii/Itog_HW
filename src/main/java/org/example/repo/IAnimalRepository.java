package org.example.repo;

import org.example.model.Animal;

public interface IAnimalRepository {
    void addNewRecording(Animal animal);
    void readCommandByAnimal();
    void addNewCommandByAnimal();

}
