package org.example.controller;


import lombok.Getter;
import lombok.Setter;
import org.example.model.*;
import org.example.repo.AnimalRepository;
import org.example.repo.IAnimalRepository;
import org.example.services.AnimalServices;
import org.example.services.CommandServices;
import org.example.services.IAnimalServices;
import org.example.services.ICommandServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class Controller extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private Animal animal;

    private JPanel menu;
    private JPanel panelBtn;
    private JButton btnShowAllAnimal;
    private JButton btnAddAnimal;
    private JButton btnAddCommand;
    private JButton btnShowAllCommandAnimal;
    private JTextArea textArea;

    private JPanel panelAddAnimal;
    private final JLabel nameLabel = new JLabel("Name animal.");
    private final JLabel commandLabel = new JLabel("List of commands performed by the pet.");
    private final JLabel dayOfBirthLabel = new JLabel("Birth day.");

    private JTextField nameAnimalText;
    private JTextField commandAnimalText;
    private JTextField birthDayText;
    private JButton sendAnimal;
    private JComboBox<EnumAnimal> animalSpecies;


    private boolean workAddAnimalPanel = false;
    //    public boolean isWorkAddAnimalPanel() {
    //        return workAddAnimalPanel;
    //    }
    //
    //    public void setWorkAddAnimalPanel(boolean workAddAnimalPanel) {
    //        this.panelAddAnimal.setVisible(workAddAnimalPanel);
    //    }
    //
    @Getter
    private boolean workPanelBtn = true;
    private boolean workTextArea = false;


    IAnimalRepository iAnimalRepository;
    IAnimalServices iAnimalServices;
    ICommandServices iCommandServices;
    IMouseListener iMouseListener;


    public Controller() {
        initInterface();

        this.setSettings();
        this.add(createMenu(), BorderLayout.NORTH);
        this.createTextArea("");
        mouseListener();



        this.setVisible(true);


    }

    private void mouseListener() {
        this.btnAddAnimalListener();
        this.sendBtnListener();
    }

    private void initInterface() {
        this.iAnimalServices = new AnimalServices();
        this.iAnimalRepository = new AnimalRepository();
        this.iCommandServices = new CommandServices();
        this.iMouseListener = new MouseListener(this);
    }


    private void setSettings() {
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Animal registry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Component createMenu() {
        menu = new JPanel(new GridLayout(2, 1));
        menu.add(createBtn());
        return menu;
    }

    private Component createBtn() {
        this.panelBtn = new JPanel(new GridLayout(2, 2));
        this.btnAddAnimal = new JButton("Add animal.");
        this.btnAddCommand = new JButton("Add command.");
        this.btnShowAllAnimal = new JButton("Show all animal.");
        this.btnShowAllCommandAnimal = new JButton("Show all command");
        this.panelBtn.add(this.btnAddAnimal);
        this.panelBtn.add(this.btnAddCommand);
        this.panelBtn.add(this.btnShowAllAnimal);
        this.panelBtn.add(this.btnShowAllCommandAnimal);


        return this.panelBtn;
    }

    protected Component createTextArea(String string) {
        this.textArea = new JTextArea();
        this.add(textArea);
        textArea.append(string);
        textArea.setVisible(isWorkTextArea());
        return textArea;
    }




    protected Component createAnimal() {

        this.panelAddAnimal = new JPanel(new GridLayout(8, 2));
        this.animalSpecies = new JComboBox<>(EnumAnimal.values());
        this.sendAnimal = new JButton("Send");
        this.nameAnimalText = new JTextField();
        this.commandAnimalText = new JTextField();
        this.birthDayText = new JTextField();

        panelAddAnimal.add(animalSpecies);
        panelAddAnimal.add(nameLabel);
        panelAddAnimal.add(nameAnimalText);
        panelAddAnimal.add(commandLabel);
        panelAddAnimal.add(commandAnimalText);
        panelAddAnimal.add(dayOfBirthLabel);
        panelAddAnimal.add(birthDayText);
        panelAddAnimal.add(sendAnimal);


        this.add(panelAddAnimal, BorderLayout.CENTER);


        return this;
    }


    private void btnAddAnimalListener() {
        iMouseListener.btnAddAnimalListener(btnAddAnimal);
//        btnAddAnimal.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panelBtn.setVisible(false);
//                createAnimal();
//                sendBtnListener();
//            }
//        });

    }

    private void sendBtnListener() {
        sendAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iMouseListener.sendBtnListener(sendAnimal);
//                if(!nameAnimalText.getText().isEmpty() && !birthDayText.getText().isEmpty()) {
//                    String animalSpices = animalSpecies.getSelectedItem().toString();
//                    String[] command = commandAnimalText.getText().split(",");
//                    caseAnimal(animalSpices, command);
//                    iAnimalRepository.addNewRecording(animal);
//                    setWorkPanelBtn(true);
//                    setWorkAddAnimalPanel(false);
//                    createTextArea("Animal added.");
//                    setWorkTextArea(true);
//
//                }
//                else {
//                    setWorkPanelBtn(true);
//                    setWorkAddAnimalPanel(false);
//                    createTextArea("Invalid data!");
//                    setWorkTextArea(true);
//                }
            }
        });
    }

    protected Animal caseAnimal(String animalSpices, String[] command) {
        switch (animalSpices) {
            case "CAT" ->  animal = new Cat(nameAnimalText.getText(), command, birthDayText.getText());
            case "DOG" -> animal = new Dog(nameAnimalText.getText(), command, birthDayText.getText());
            case "HAMSTER" -> animal = new Hamster(nameAnimalText.getText(), command, birthDayText.getText());
            case "CAMEL" -> animal = new Camel(nameAnimalText.getText(), command, birthDayText.getText());
            case "DONKEY" -> animal = new Donkey(nameAnimalText.getText(), command, birthDayText.getText());
            case "HORSE" -> animal = new Horse(nameAnimalText.getText(), command, birthDayText.getText());
        }
        return animal;
    }

    public void setWorkPanelBtn(boolean workPanelBtn) {
       panelBtn.setVisible(workPanelBtn);
    }

    public void setWorkAddAnimalPanel(boolean workAddAnimalPanel) {
        panelAddAnimal.setVisible(workAddAnimalPanel);
    }

    public void setWorkTextArea(boolean workTextArea) {
       textArea.setVisible(workTextArea);
    }
}


