package org.example.repo;


import org.example.model.Animal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class AnimalRepository implements IAnimalRepository {
    public static final String LOG_PATH = "src/main/java/org/example/repo/dataAnimal/DataAnimal.txt";
    List<String> animalList = new ArrayList<>();

    @Override
    public void addNewRecording(Animal animal) {

        animalList.add(animal.toString());
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(String.valueOf(animal));
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> showAllAnimalInFile() {
        animalList.clear();
        if (Files.exists(Path.of(LOG_PATH ))) {
            try (BufferedReader br = new BufferedReader(new FileReader(LOG_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    animalList.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return  animalList;

    }

    @Override
    public void readCommandByAnimal() {

    }

    @Override
    public void addNewCommandForAnimal() {

    }

    @Override
    public String searchCommandByName(String name ) {
        List<String> tempList = new ArrayList<>();
        tempList = showAllAnimalInFile();
        for(String s : tempList){
            if(s.contains(name)){
                return s;
            }
        }
        return "Invalid name";
    }
}
