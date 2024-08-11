package org.example.controller;


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
import java.util.List;


public class Controller extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    AddAnimalPanel addAnimalPanel;

    private Animal animal;

    private JPanel menu;
    private JPanel panelBtn;
    private JPanel addCommandPanel;
    private JButton btnShowAllAnimal;
    private JButton btnAddAnimal;
    private JButton btnAddCommand;
    private JButton btnShowAllCommandAnimal;
    private JButton btnBack;
    private JTextArea textArea;

    private JPanel panelAddAnimal;
    private final JLabel nameLabel = new JLabel("Name animal.");
    private final JLabel commandLabel = new JLabel("List of commands performed by the pet.");
    private final JLabel dayOfBirthLabel = new JLabel("Birth day.");

    private JTextField nameAnimalText;
    private JTextField nameAnimalTextForCommand;
    private JTextField commandAnimalText;
    private JTextField birthDayText;
    private JButton btnSendAnimal;
    private JButton btnSendCommand;
    private JComboBox<EnumAnimal> animalSpecies;


    private boolean workPanelBtn = true;
    private boolean workAddAnimalPanel = false;
    private boolean workTextArea = false;
    private boolean workAddCommandPanel = false;


    IAnimalRepository iAnimalRepository;
    IAnimalServices iAnimalServices;
    ICommandServices iCommandServices;


    public Controller() {
        initInterface();

        this.setSettings();
        this.add(createMenu(), BorderLayout.NORTH);
        this.createTextArea("").setVisible(false);
        this.add(panelAddCommand());


        this.setVisible(true);

        mouseListener();

    }

    private void mouseListener() {
        btnAddAnimalListener();
        showAlAnimalListener();
        addCommandForAnimal();
        backInMenu();
    }

    private void initInterface() {
        this.iAnimalServices = new AnimalServices();
        this.iAnimalRepository = new AnimalRepository();
        this.iCommandServices = new CommandServices();
    }


    private void setSettings() {
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Animal registry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Component createMenu() {
        menu = new JPanel(new GridLayout(2, 1));
        addAnimalPanel = new AddAnimalPanel(this);
        menu.add(addAnimalPanel.createBtn());
//        menu.add(createBtn());
        return menu;
    }

//    private Component createBtn() {
//        this.panelBtn = new JPanel(new GridLayout(3, 2));
//        this.btnAddAnimal = new JButton("Add animal.");
//        this.btnAddCommand = new JButton("Add command.");
//        this.btnShowAllAnimal = new JButton("Show all animal.");
//        this.btnShowAllCommandAnimal = new JButton("Show all command");
//        this.btnBack = new JButton("Back");
//        this.textArea = new JTextArea();
//        this.panelBtn.add(this.btnAddAnimal);
//        this.panelBtn.add(this.btnAddCommand);
//        this.panelBtn.add(this.btnShowAllAnimal);
//        this.panelBtn.add(this.btnShowAllCommandAnimal);
//
//
//        return this.panelBtn;
//    }

    protected Component createTextArea(String string) {
        this.textArea = new JTextArea();
        this.add(textArea);
//        this.add(btnBack, BorderLayout.SOUTH);
        textArea.append(string);
        textArea.setVisible(isWorkTextArea());
        return textArea;
    }


    protected Component panelCreateAnimal() {

        this.panelAddAnimal = new JPanel(new GridLayout(8, 2));
        this.animalSpecies = new JComboBox<>(EnumAnimal.values());
        this.btnSendAnimal = new JButton("Send");
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
        this.panelAddAnimal.add(btnSendAnimal);

        this.panelAddAnimal.setVisible(true);
        this.add(panelAddAnimal, BorderLayout.CENTER);

        return panelAddAnimal;
    }

    private Component panelAddCommand() {
        this.addCommandPanel = new JPanel(new GridLayout(10, 2));
        this.nameAnimalTextForCommand = new JTextField();
        this.btnSendCommand = new JButton("Send command");
        nameAnimalText = new JTextField();
        addCommandPanel.add(nameLabel);
        addCommandPanel.add(nameAnimalText);
        addCommandPanel.add(commandLabel);
        addCommandPanel.add(nameAnimalTextForCommand);
        addCommandPanel.add(btnSendCommand);

        this.add(addCommandPanel);
        setWorkAddCommandPanel(false);
        return addCommandPanel;
    }


    private void btnAddAnimalListener() {
        btnAddAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkPanelBtn(false);
                panelCreateAnimal();
                sendBtnListener();
            }
        });

    }

    private void sendBtnListener() {
        btnSendAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!nameAnimalText.getText().isEmpty())
                        && (!birthDayText.getText().isEmpty())) {
                    String animalSpices = String.valueOf(animalSpecies.getSelectedItem());
                    String[] command = commandAnimalText.getText().split(",");
                    Animal animal = caseAnimal(animalSpices, command);
                    iAnimalRepository.addNewRecording(animal);
                    setWorkPanelBtn(true);
                    setWorkAddAnimalPanel(false);
                    createTextArea("Animal added.");
                    textArea.setText("Animal added.");
                    setWorkTextArea(true);
                    backInMenu();


                } else {
                    setWorkPanelBtn(true);
                    setWorkAddAnimalPanel(false);
                    setWorkTextArea(true);
                    createTextArea("Invalid data!");
                    textArea.setText("Invalid data!");

                }
            }
        });
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

    private void backInMenu() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getWorkAddAnimalPanel()) {
                    setWorkAddAnimalPanel(false);
                }
                if (getWorkPanelBtn()) {
                    setWorkPanelBtn(true);
                }
                if (getWorkTextArea()) {
                    textArea.setText("");
                    setWorkTextArea(false);
                }
                if (!isWorkAddCommandPanel()) {
                    setWorkAddCommandPanel(false);
                }
            }
        });

    }


    private void showAlAnimalListener() {
        btnShowAllAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkPanelBtn(false);
                List<String> animalList = iAnimalRepository.showAllAnimalInFile();
                setWorkTextArea(true);


                for (String s : animalList) {
                    textArea.append(s);
                    textArea.append("\n");
                }

            }
        });
    }

    private void addCommandForAnimal() {
        btnAddCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkPanelBtn(false);
                setWorkAddCommandPanel(true);

                sendCommand(nameAnimalTextForCommand.getText());
                System.out.println(nameAnimalTextForCommand.getText());

                iAnimalRepository.addNewCommandForAnimal();


            }
        });

    }
    private void sendCommand(String name ){
        btnSendCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iAnimalRepository.searchCommandByName(name);
            }
        });

    }

    public void setWorkPanelBtn(boolean workPanelBtn) {
        menu.setVisible(workPanelBtn);
    }

    public void setWorkAddAnimalPanel(boolean workAddAnimalPanel) {
        panelAddAnimal.setVisible(workAddAnimalPanel);
    }

    private boolean getWorkAddAnimalPanel() {
        return workAddAnimalPanel;
    }

    public void setWorkTextArea(boolean workTextArea) {
        textArea.setVisible(workTextArea);
    }

    private boolean isWorkTextArea() {
        return workTextArea;
    }

    private boolean getWorkPanelBtn() {
        return workPanelBtn;
    }

    private boolean getWorkTextArea() {
        return workTextArea;
    }

    public boolean isWorkAddCommandPanel() {
        return workAddCommandPanel;
    }

    public void setWorkAddCommandPanel(boolean workAddCommandPanel) {
        addCommandPanel.setVisible(workAddCommandPanel);
    }
}


