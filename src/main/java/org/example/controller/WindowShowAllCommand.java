package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowShowAllCommand {


    private AddMainMenu mainMenu;

    private JPanel panelAllCommand;
    private final Label labelNameAnimal = new Label("Enter name animal");
    private JTextField nameAnimal;

    private final JButton btnBack = new JButton("Back");
    private final JButton btnSearch = new JButton("Search");

    public WindowShowAllCommand(AddMainMenu addMainMenu) {
        this.mainMenu = addMainMenu;

    }


    protected Component createPanelTextArea() {
        panelAllCommand = new JPanel(new GridLayout(10,1));
        nameAnimal = new JTextField();
        panelAllCommand.add(labelNameAnimal, BorderLayout.NORTH);
        panelAllCommand.add(nameAnimal, BorderLayout.NORTH);
        panelAllCommand.add(btnSearch, BorderLayout.SOUTH);
        panelAllCommand.add(btnBack, BorderLayout.SOUTH);


        return panelAllCommand;

    }

    private void btnBackListener() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setWorkAddMainMenu(true);
                setWorkWindowShowAllCommand(false);
                mainMenu.messageFromUser.setText("");

            }
        });
    }
    private void btnSearchByName(){
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = mainMenu.iAnimalRepository.searchCommandByName(nameAnimal.getText());
                mainMenu.messageFromUser.setText(command);
            }
        });

    }

    protected void showAllCommandListener(){
        btnBackListener();
        btnSearchByName();
    }

    private void setWorkWindowShowAllCommand(boolean flag) {
        panelAllCommand.setVisible(flag);
    }
}
