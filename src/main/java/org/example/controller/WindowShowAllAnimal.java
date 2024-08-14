package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WindowShowAllAnimal {
    AddMainMenu mainMenu;

    private final JButton btnBack = new JButton("Back");
    private JPanel panelAllAnimal;

    public WindowShowAllAnimal(AddMainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    protected Component createPanelTextArea() {
        panelAllAnimal = new JPanel(new BorderLayout());
        panelAllAnimal.add(btnBack, BorderLayout.NORTH);
        return panelAllAnimal;
    }

    protected void showAllAnimal() {
        mainMenu.messageFromUser.setText("");
        List<String> strings = mainMenu.iAnimalRepository.showAllAnimalInFile();
        strings.forEach(el -> mainMenu.messageFromUser.append(el + "\n"));
    }

    protected void btnBackListener(){
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setWorkAddMainMenu(true);
                setWorkWindowShowAllAnimal(false);
                mainMenu.messageFromUser.setText("");
            }
        });
    }
    protected void setWorkWindowShowAllAnimal( boolean flag ){
        panelAllAnimal.setVisible(flag);
    }

}
