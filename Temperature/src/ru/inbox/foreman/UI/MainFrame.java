package ru.inbox.foreman.UI;

import ru.inbox.foreman.model.ConvertTemperature;
import ru.inbox.foreman.support.DigitFilter;


import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.function.Function;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame {
    private JFrame frame;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JMenuBar menuBar;
    JFormattedTextField inputTemp;
    JComboBox<String> selectInputScale;
    JComboBox<String> selectResultTempScale;
    JTextField resultTemp;
    private ConvertTemperature converter;

    private MainFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        converter = new ConvertTemperature(this);
        createUI();

    }

    private void createUI() {
        menuBar = createMenu();
        // панель ввода
        inputPanel = createInputPanel();
        // лэйбел
        JPanel mediumPanel = new JPanel();
        mediumPanel.add(new JLabel("Конвертировать в:"));
        // панель результата
        resultPanel = createResultPanel();

        frame = new JFrame("Конвертер темрературы");
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(resultPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(mediumPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

    }

    private JMenuBar createMenu() {
        menuBar = new JMenuBar();

        JMenu menuScale = new JMenu("Шкала");
        JMenuItem add = new JMenuItem("Добавить");
        add.addActionListener(e -> addScale());
        JMenuItem remove = new JMenuItem("Удалить");
        add.addActionListener(e -> removeScale());
        JMenuItem close = new JMenuItem("Выход");
        close.addActionListener(e -> closeWindow());

        menuScale.add(add);
        menuScale.add(remove);
        menuScale.addSeparator();
        menuScale.add(close);
        menuBar.add(menuScale);

        return menuBar;
    }

    private void closeWindow() {
        this.frame.dispose();
    }

    private void removeScale() {
    }

    private void addScale() {
        new AddScaleFrame(converter);


    }

    private JPanel createResultPanel() {
        selectResultTempScale = new JComboBox<>(converter.getResultScaleName());
        JLabel resultTemp = new JLabel();
        //resultTemp.setEditable(false);
        resultPanel = new JPanel();
        resultPanel.add(selectResultTempScale);
        resultPanel.add(resultTemp);
        selectResultTempScale.addActionListener(e -> calcResultTemp(resultTemp));
        return resultPanel;
    }

    private void calcResultTemp(JLabel resultTemp) {
        double result = converter.convertTemp((String) selectInputScale.getSelectedItem(), (String) selectResultTempScale.getSelectedItem(),
                inputTemp.getValue());
        resultTemp.setText(String.valueOf(result));
    }


    private JPanel createInputPanel() {
        selectInputScale = new JComboBox<>(converter.getInputScaleName());
        inputTemp = new JFormattedTextField(NumberFormat.getNumberInstance());
        //  inputTemp.setValue(10);
        inputTemp.setColumns(15);
        PlainDocument plDoc = (PlainDocument) inputTemp.getDocument();
        plDoc.setDocumentFilter(new DigitFilter());
        inputPanel = new JPanel();
        inputPanel.add(selectInputScale);
        inputPanel.add(inputTemp);
        return inputPanel;
    }

    public double getInputData() {
        String inputData = inputTemp.getText();


        return Double.parseDouble(inputTemp.getText());
    }

    public void updateUI() {
        selectInputScale.addItem(converter.getLastScale());
        selectResultTempScale.addItem(converter.getLastScale());
    }

    public static void main(String[] arg) {
        new MainFrame();
    }
}
