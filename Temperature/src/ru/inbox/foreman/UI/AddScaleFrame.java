package ru.inbox.foreman.UI;

import ru.inbox.foreman.model.ConvertTemperature;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

class AddScaleFrame {
    private JFrame addScale;
    private JTextField nameScale;
    private JFormattedTextField k1ToC;
    private JFormattedTextField k2ToC;
    private JFormattedTextField k3ToC;
    private JFormattedTextField k1ToR;
    private JFormattedTextField k2ToR;
    private JFormattedTextField k3ToR;
    private MaskFormatter formatter;
    private ConvertTemperature converter;

    AddScaleFrame(ConvertTemperature converter) {
        this.converter = converter;
        try {
            formatter = new MaskFormatter("###.####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        createUI();


    }

    private void createUI() {
        addScale = new JFrame("Добавление шкалы");
        JPanel inputPanel = inputPanel();
        JPanel buttonPanel = buttonPanel();
        JPanel mediumPanel = mediumPanel();

        addScale.getContentPane().add(inputPanel, BorderLayout.CENTER);
        addScale.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addScale.setSize(300, 300);
        addScale.setResizable(false);
        addScale.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addScale.setLocationRelativeTo(null);
        addScale.setVisible(true);
        addScale.pack();
    }

    private JPanel mediumPanel() {
        JLabel label = new JLabel("Пример записи перевода (Цельсия в Кельвины): t -> t- 273");
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);
        return labelPanel;
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.addScale.dispose());
        okButton.addActionListener(e -> addScaleToConverter());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private void addScaleToConverter() {
        List<JFormattedTextField> kList = new ArrayList<>(Arrays.asList(k1ToC, k2ToC, k3ToC, k1ToR, k2ToR, k3ToR));

        List<Boolean> kBoolean = kList.stream().map(e -> e.getText().trim().isEmpty()).collect(Collectors.toList());
        Optional<Boolean> res = kBoolean.stream().reduce((e1, e2) -> e1 && e2);

        if (res.isPresent() && res.get()) {
            JOptionPane.showMessageDialog(addScale, "Поля должны быть заполнены");
            return;
        }

        HashMap<JFormattedTextField, Long> coefficients = new HashMap<>();
        kList.forEach(e -> {
            coefficients.put(e, (Long) e.getValue());
        });

        Function<Long, Long> f = e -> ((e * coefficients.get(k1ToC) + coefficients.get(k2ToC)) * coefficients.get(k3ToC));
        Function<Long, Long> r = e -> ((e * coefficients.get(k1ToR) + coefficients.get(k1ToR)) * coefficients.get(k1ToR));

        converter.addInputScale(nameScale.getText(), f, r);
        addScale.dispose();
    }

    private JPanel inputPanel() {
        nameScale = new JTextField(20);
        JPanel inputNameScale = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inputNameScale.add(new Label("Название шкалы"));
        inputNameScale.add(nameScale);

        JPanel TempToCelsius = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel kC = new JPanel(new GridLayout(1, 3));
        k1ToC = new JFormattedTextField(formatter);
        k2ToC = new JFormattedTextField(formatter);
        k3ToC = new JFormattedTextField(formatter);
        TempToCelsius.add(new Label("Коэффициенты А и В  функции (t*k1 + k2)*k3 перевода температуры в Гр. цельсия"));
        kC.add(k1ToC);
        kC.add(k2ToC);
        kC.add(k3ToC);
        TempToCelsius.add(kC);

        JPanel TempToResult = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel kR = new JPanel(new GridLayout(1, 3));
        k1ToR = new JFormattedTextField(formatter);
        k2ToR = new JFormattedTextField(formatter);
        k3ToR = new JFormattedTextField(formatter);
        TempToResult.add(new Label("Коэффициенты А и В  функции (t*k1 + k2)*k3 перевода температуры из Гр. цельсия"));
        kR.add(k1ToR);
        kR.add(k2ToR);
        kR.add(k3ToR);
        TempToResult.add(kR);

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.add(inputNameScale);
        inputPanel.add(TempToCelsius);
        inputPanel.add(TempToResult);
        return inputPanel;
    }


}
