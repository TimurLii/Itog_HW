package org.example.repo;

import org.example.model.Animal;

import java.util.List;

public interface IAnimalRepository {
    void addNewRecording(Animal animal);
    List<String> showAllAnimalInFile();
    void addNewRecordingAfterChanges();
    boolean addNewCommandForAnimal(String name, String newCommandList);
    String searchCommandByName(String name);
}
