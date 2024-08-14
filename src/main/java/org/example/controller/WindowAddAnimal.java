package org.example.controller;

import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowAddAnimal {
    private AddMainMenu mainMenu;
    private Animal animal;

    private JPanel panelAddAnimal;
    private JComboBox<EnumAnimal> animalSpecies;
    private final JButton btnSendAnimal = new JButton("Send");
    private final JButton btnBack = new JButton("Back");
    private JTextField nameAnimalText;
    private JTextField commandAnimalText;
    private JTextField birthDayText;


    private final JLabel nameLabel = new JLabel("Name animal.");
    private final JLabel commandLabel = new JLabel("List of commands performed by the pet.");
    private final JLabel dayOfBirthLabel = new JLabel("Birth day.");


    public WindowAddAnimal(AddMainMenu addMainMenu) {
        this.mainMenu = addMainMenu;
    }

    protected Component panelCreateAnimal() {

        this.panelAddAnimal = new JPanel(new GridLayout(9, 2));
        this.animalSpecies = new JComboBox<>(EnumAnimal.values());
        this.nameAnimalText = new JTextField();
        this.commandAnimalText = new JTextField();
        this.birthDayText = new JTextField();


        this.panelAddAnimal.add(animalSpecies);
        this.panelAddAnimal.add(nameLabel);
        this.panelAddAnimal.add(nameAnimalText);
        this.panelAddAnimal.add(commandLabel);
        this.panelAddAnimal.add(commandAnimalText);
        this.panelAddAnimal.add(dayOfBirthLabel);
        this.panelAddAnimal.add(birthDayText);
        this.panelAddAnimal.add(btnSendAnimal, BorderLayout.SOUTH);
        this.panelAddAnimal.add(btnBack, BorderLayout.SOUTH);

        return panelAddAnimal;
    }


    protected void btnSendNewAnimalListener() {
        btnSendAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!nameAnimalText.getText().isEmpty())
                        && (!birthDayText.getText().isEmpty())) {
                    String animalSpices = String.valueOf(animalSpecies.getSelectedItem());
                    String[] command = commandAnimalText.getText().split(",");
                    Animal animal = caseAnimal(animalSpices, command);
                    mainMenu.iAnimalRepository.addNewRecording(animal);
                    mainMenu.messageFromUser.setText("Animal added");
                    mainMenu.setWorkAddMainMenu(false);
                    setWorkAddAnimalPanel(false);
                    mainMenu.setWorkAddMainMenu(true);
                } else {
                    mainMenu.messageFromUser.setText("Invalid animal");
                    mainMenu.setWorkAddMainMenu(true);
                    setWorkAddAnimalPanel(false);

                }
            }
        });
    }

    protected void btnBackListener(){
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setWorkAddMainMenu(true);
                setWorkAddAnimalPanel(false);

            }
        });
    }

    protected void listenerForAddAnimalWindow(){
        btnSendNewAnimalListener();
        btnBackListener();
    }

    private Animal caseAnimal(String animalSpices, String[] command) {

        switch (animalSpices) {
            case "CAT" -> animal = new Cat(nameAnimalText.getText(), command, birthDayText.getText());
            case "DOG" -> animal = new Dog(nameAnimalText.getText(), command, birthDayText.getText());
            case "HAMSTER" -> animal = new Hamster(nameAnimalText.getText(), command, birthDayText.getText());
            case "CAMEL" -> animal = new Camel(nameAnimalText.getText(), command, birthDayText.getText());
            case "DONKEY" -> animal = new Donkey(nameAnimalText.getText(), command, birthDayText.getText());
            case "HORSE" -> animal = new Horse(nameAnimalText.getText(), command, birthDayText.getText());
        }
        return animal;
    }

    protected void setWorkAddAnimalPanel(boolean flag) {
        panelAddAnimal.setVisible(flag);
    }


}
