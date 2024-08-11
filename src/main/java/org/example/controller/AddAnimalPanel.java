package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalPanel extends JFrame {

    private Controller controller;
    private JPanel panelBtn;
    private JButton btnSendCommand;
    private JButton btnShowAllAnimal;
    private JButton btnAddAnimal;
    private JButton btnAddCommand;
    private JButton btnShowAllCommandAnimal;
    private JTextField nameAnimalTextForCommand;
    private JTextArea textArea;
    private JButton btnBack;


    public AddAnimalPanel(Controller controller) {
        this.controller = controller;
    }

    public Component createBtn() {
        this.panelBtn = new JPanel(new GridLayout(3, 2));
        this.btnAddAnimal = new JButton("Add animal.");
        this.btnAddCommand = new JButton("Add command.");
        this.btnShowAllAnimal = new JButton("Show all animal.");
        this.btnShowAllCommandAnimal = new JButton("Show all command");
        this.btnBack = new JButton("Back");
        this.textArea = new JTextArea();
        this.panelBtn.add(this.btnAddAnimal);
        this.panelBtn.add(this.btnAddCommand);
        this.panelBtn.add(this.btnShowAllAnimal);
        this.panelBtn.add(this.btnShowAllCommandAnimal);


        return this.panelBtn;
    }
    private void addCommandForAnimal() {
        btnAddCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setWorkPanelBtn(false);
                controller.setWorkAddCommandPanel(true);

                sendCommand(nameAnimalTextForCommand.getText());
                System.out.println(nameAnimalTextForCommand.getText());

                controller.iAnimalRepository.addNewCommandForAnimal();


            }
        });


    }
    private void sendCommand(String name ){
        btnSendCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.iAnimalRepository.searchCommandByName(name);
            }
        });

    }
}
