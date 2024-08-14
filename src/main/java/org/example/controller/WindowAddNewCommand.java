package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class WindowAddNewCommand {
    AddMainMenu mainMenu;

    private JPanel panelAddCommand;
    private final JLabel newCommandLabel = new JLabel("New command");
    private JTextArea newCommandText;
    private final JLabel nameAnimalLabel = new JLabel("Enter name animal");
    private JTextArea nameAnimalText;

    private JButton btnSendNewCommand = new JButton("Send");
    private JButton btnBack = new JButton("Back");


    public WindowAddNewCommand(AddMainMenu addMainMenu) {
        this.mainMenu = addMainMenu;
    }

    protected Component createWindowAddNewCommand(){
        this.panelAddCommand = new JPanel(new GridLayout(10,1));
        newCommandText = new JTextArea();
        nameAnimalText = new JTextArea();
        panelAddCommand.add(nameAnimalLabel);
        panelAddCommand.add(nameAnimalText);
        panelAddCommand.add(newCommandLabel);
        panelAddCommand.add(newCommandText);
        panelAddCommand.add(btnSendNewCommand);
        panelAddCommand.add(btnBack);

        return panelAddCommand;
    }

    public void  addNewCommandListener(){
        btnSendListener();
        btnBackListener();
    }
    private void  btnSendListener(){
        btnSendNewCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCommandList = newCommandText.getText();
                if(mainMenu.iAnimalRepository.addNewCommandForAnimal(nameAnimalText.getText(), newCommandList)){
                    mainMenu.messageFromUser.setText("Command add");
                    setWorkAAddNewCommand(false);
                    mainMenu.setWorkAddMainMenu(true);
                }
                else {
                    mainMenu.messageFromUser.setText("Error");
                    setWorkAAddNewCommand(false);
                    mainMenu.setWorkAddMainMenu(true);
                };
            }

        });
    }
    private void btnBackListener(){
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setWorkAddMainMenu(true);
                setWorkAAddNewCommand(false);
            }
        });


    }

    private void setWorkAAddNewCommand(boolean flag) {
        panelAddCommand.setVisible(flag);
    }


}
