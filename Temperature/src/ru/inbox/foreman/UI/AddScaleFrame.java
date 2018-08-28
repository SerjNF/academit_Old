package ru.inbox.foreman.UI;

import ru.inbox.foreman.model.ConvertTemperature;
import ru.inbox.foreman.support.DigitFilter;
import ru.inbox.foreman.support.ParserToDouble;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.*;
import java.util.List;

import java.util.stream.Collectors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
        addScale.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        HashMap<JTextField, Double> coefficients = new HashMap<>();
        kListTextField.forEach(e -> coefficients.put(e, ParserToDouble.parseToDouble(e.getText())));

        double[] kInput = new double[] {coefficients.get(kListTextField.get(0)),coefficients.get(kListTextField.get(1)),coefficients.get(kListTextField.get(2))};
        double[] kResult = new double[] {coefficients.get(kListTextField.get(3)),coefficients.get(kListTextField.get(4)),coefficients.get(kListTextField.get(5))};

        converter.addScale(nameScale.getText(), kInput, kResult);
        addScale.dispose();
    }

    private boolean validation() {
        List<Boolean> kBoolean = kListTextField.stream().map(e -> e.getText().trim().isEmpty()).collect(Collectors.toList());
        Optional<Boolean> res = kBoolean.stream().reduce((e1, e2) -> !e1 && !e2);
        return !res.isPresent() || !res.get();
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
