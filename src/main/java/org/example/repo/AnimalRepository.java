package org.example.repo;


import org.example.model.Animal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AnimalRepository implements IAnimalRepository {
    public static final String LOG_PATH = "src/main/java/org/example/repo/dataAnimal/DataAnimal.txt";
    static List<Animal> animalList = new ArrayList<>();
    List<String> tempAnimalList = new ArrayList<>();

    @Override
    public void addNewRecording(Animal animal) {
        animalList.add(animal);
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(String.valueOf(animal));
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> showAllAnimalInFile() {
        tempAnimalList.clear();
        if (Files.exists(Path.of(LOG_PATH))) {
            try (BufferedReader br = new BufferedReader(new FileReader(LOG_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    tempAnimalList.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tempAnimalList;

    }

    @Override
    public void addNewRecordingAfterChanges() {
        try (FileWriter writer = new FileWriter(LOG_PATH, false)) {
            for(Animal animal:animalList){
                writer.write(String.valueOf(animal));
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addNewCommandForAnimal(String name, String newCommandList) {
      List<String> tempListCommand = new ArrayList<>();
        for (Animal el : animalList) {
            if (el.getName().equals(name)) {
                tempListCommand.addAll(List.of(el.getCommand()));
                Collections.addAll(tempListCommand, newCommandList);
                String[] res = tempListCommand.toArray(new String[0]);
                el.setCommand(res);
                addNewRecordingAfterChanges();
                return true;
            }
        }
        return false;
    }


    @Override
    public String searchCommandByName(String name) {
        for (Animal animal : animalList) {
            if (animal.getName().equals(name)) {
                return Arrays.toString(animal.getCommand());
            }
        }
        return "Invalid name";
    }
}
