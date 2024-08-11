package org.example.repo;

import org.example.model.Animal;


import java.io.FileWriter;


public class AnimalRepository implements IAnimalRepository {
    public static final String LOG_PATH = "src/main/java/org/example/repo/dataAnimal/DataAnimal.txt";

    @Override
    public void addNewRecording(Animal animal) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(animal.toString());
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readCommandByAnimal() {

    }

    @Override
    public void addNewCommandByAnimal() {

    }
}
