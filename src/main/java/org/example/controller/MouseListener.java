package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MouseListener implements IMouseListener {
    Controller controller;

    public MouseListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void btnAddAnimalListener(JButton btnAddAnimal) {
        btnAddAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setWorkPanelBtn(false);
                controller.createAnimal();

            }
        });
    }

    @Override
    public void sendBtnListener(JButton sendBtnListener) {

        if((controller.getNameAnimalText()!= null)  && (controller.getBirthDayText() != null)) {
            String animalSpices = controller.getAnimalSpecies().toString();
            String[] command = controller.getCommandAnimalText().getText().split(",");
            controller.iAnimalRepository.addNewRecording(controller.caseAnimal(animalSpices,command));
            controller.setWorkPanelBtn(true);
            controller.setWorkAddAnimalPanel(false);
            controller.createTextArea("Animal added.");
            controller.setWorkTextArea(true);

        }
        else {
            controller.setWorkPanelBtn(true);
            controller.setWorkAddAnimalPanel(false);
            controller.createTextArea("Invalid data!");
            controller.setWorkTextArea(true);
        }
    }
}
