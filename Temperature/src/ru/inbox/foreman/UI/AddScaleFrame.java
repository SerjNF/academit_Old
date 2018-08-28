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
        JPanel kC = new JPanel(new GridLayout(1, 6));
        JTextField k1 = new JTextField(5);
        k1.setToolTipText("k1");
        JTextField k2 = new JTextField(5);
        k2.setToolTipText("k2");
        JTextField k3 = new JTextField(5);
        k3.setToolTipText("k3");
        kListTextField.add(k1);
        kListTextField.add(k2);
        kListTextField.add(k3);
        JLabel k1Label = new JLabel("k1:");
        k1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        kC.add(k1Label);
        kC.add(k1);
        JLabel k1Labe2 = new JLabel("k2:");
        k1Labe2.setHorizontalAlignment(SwingConstants.RIGHT);
        kC.add(k1Labe2);
        kC.add(k2);
        JLabel k1Labe3 = new JLabel("k3:");
        k1Labe3.setHorizontalAlignment(SwingConstants.RIGHT);
        kC.add(k1Labe3);
        kC.add(k3);

        tempToCelsius.add(new Label("Коэффициенты последовательно функции (t*k1 + k2)*k3 перевода температуры в Гр. цельсия"));
        tempToCelsius.add(kC);

        JPanel tempToResult = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel kR = new JPanel(new GridLayout(1, 6));
        JTextField k4 = new JTextField(5);
        k4.setToolTipText("k4");
        JTextField k5 = new JTextField(5);
        k5.setToolTipText("k5");
        JTextField k6 = new JTextField(5);
        k6.setToolTipText("k6");
        kListTextField.add(k4);
        kListTextField.add(k5);
        kListTextField.add(k6);
        JLabel k1Labe4 = new JLabel("k4:");
        k1Labe4.setHorizontalAlignment(SwingConstants.RIGHT);
        kR.add(k1Labe4);
        kR.add(k4);
        JLabel k1Labe5 = new JLabel("k5:");
        k1Labe5.setHorizontalAlignment(SwingConstants.RIGHT);
        kR.add(k1Labe5);
        kR.add(k5);
        JLabel k1Labe6 = new JLabel("k6:");
        k1Labe6.setHorizontalAlignment(SwingConstants.RIGHT);
        kR.add(k1Labe6);
        kR.add(k6);
        tempToResult.add(new Label("Коэффициенты последовательно функции (t*k4 + k5)*k6 перевода температуры из Гр. цельсия"));
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
