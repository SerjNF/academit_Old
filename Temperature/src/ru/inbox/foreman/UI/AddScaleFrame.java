package ru.inbox.foreman.UI;

import ru.inbox.foreman.model.ConvertTemperature;
import ru.inbox.foreman.support.DigitFilter;
import ru.inbox.foreman.support.ParserToDouble;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class AddScaleFrame {
    private JFrame addScale;
    private JTextField nameScale;
    private ConvertTemperature converter;
    private List<JTextField> kListTextField = new ArrayList<>();

    AddScaleFrame(ConvertTemperature converter) {
        this.converter = converter;
        createUI();
    }

    private void createUI() {
        addScale = new JFrame("Добавление шкалы");
        JPanel inputPanel = inputPanel();
        JPanel buttonPanel = buttonPanel();

        addScale.getContentPane().add(inputPanel, BorderLayout.CENTER);
        addScale.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addScale.setSize(300, 300);
        addScale.setResizable(false);
        addScale.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                addScale.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //     addScale.setDefaultCloseOperation(addScale.EXIT_ON_CLOSE);
        addScale.setLocationRelativeTo(null);
        addScale.setVisible(true);
        addScale.pack();
    }

    private JPanel buttonPanel() {
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> this.addScale.dispose());
        okButton.addActionListener(e -> addScaleToConverter());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private void addScaleToConverter() {
        if (validation()) {
            JOptionPane.showMessageDialog(addScale, "Поля должны быть заполнены");
            return;
        }
        double[] coefficients = new double[6];
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = ParserToDouble.parseToDouble(kListTextField.get(i).getText());
        }
        converter.addScale(nameScale.getText(), coefficients);
        addScale.dispose();
    }

    private boolean validation() {
        Optional<Boolean> kBoolean = kListTextField.stream()
                .map(e -> e.getText().trim().isEmpty())
                .reduce((e1, e2) -> !e1 && !e2);
        return !kBoolean.isPresent() || !kBoolean.get();
    }

    private JPanel inputPanel() {
        nameScale = new JTextField(20);
        JPanel inputNameScale = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inputNameScale.add(new Label("Название шкалы"));
        inputNameScale.add(nameScale);

        JPanel tempToCelsius = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel kC = new JPanel(new GridLayout(1, 3));
        kListTextField.add(new JTextField(5));
        kListTextField.add(new JTextField(5));
        kListTextField.add(new JTextField(5));

        tempToCelsius.add(new Label("Коэффициенты А и В  функции (t*k1 + k2)*k3 перевода температуры в Гр. цельсия"));
        kC.add(kListTextField.get(0));
        kC.add(kListTextField.get(1));
        kC.add(kListTextField.get(2));
        tempToCelsius.add(kC);

        JPanel tempToResult = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel kR = new JPanel(new GridLayout(1, 3));
        kListTextField.add(new JTextField(5));
        kListTextField.add(new JTextField(5));
        kListTextField.add(new JTextField(5));

        tempToResult.add(new Label("Коэффициенты А и В  функции (t*k1 + k2)*k3 перевода температуры из Гр. цельсия"));
        kR.add(kListTextField.get(3));
        kR.add(kListTextField.get(4));
        kR.add(kListTextField.get(5));
        tempToResult.add(kR);

        setFilter();

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.add(inputNameScale);
        inputPanel.add(tempToCelsius);
        inputPanel.add(tempToResult);
        return inputPanel;
    }

    private void setFilter() {
        kListTextField.forEach(e -> ((AbstractDocument) e.getDocument()).setDocumentFilter(new DigitFilter()));
    }


}
