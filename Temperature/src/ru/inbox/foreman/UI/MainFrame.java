package ru.inbox.foreman.UI;

import ru.inbox.foreman.model.ConvertTemperature;
import ru.inbox.foreman.support.DigitFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame {
    private JFrame frame;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JMenuBar menuBar;
    private JTextField inputTemp;
    private JComboBox<String> selectInputScale;
    private JComboBox<String> selectResultTempScale;
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

    public static void main(String[] arg) {
        new MainFrame();
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
        frame.setLayout(new GridLayout(3, 1));
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(inputPanel);
        frame.getContentPane().add(mediumPanel);
        frame.getContentPane().add(resultPanel);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setResizable(false);
        //   frame.pack();
    }

    private JMenuBar createMenu() {
        menuBar = new JMenuBar();

        JMenu menuScale = new JMenu("Шкала");
        JMenuItem addButton = new JMenuItem("Добавить");
        addButton.addActionListener(e -> addScale());
        JMenuItem removeButton = new JMenuItem("Удалить");
        removeButton.addActionListener(e -> removeScale());
        JMenuItem close = new JMenuItem("Выход");
        close.addActionListener(e -> closeWindow());

        menuScale.add(addButton);
        menuScale.add(removeButton);
        menuScale.addSeparator();
        menuScale.add(close);
        menuBar.add(menuScale);

        return menuBar;
    }

    private void closeWindow() {
        this.frame.dispose();
    }

    private void removeScale() {
        String[] listScale = converter.getScaleName();
        String result = (String) JOptionPane.showInputDialog(
                frame,
                "Выберите какую шкалу удалить :",
                "Выбор напитка",
                JOptionPane.QUESTION_MESSAGE, null, listScale, converter.getScaleName()[0]);
        selectResultTempScale.removeItem(result);
        selectInputScale.removeItem(result);
        converter.removeScale(result);

    }

    private void addScale() {
        new AddScaleFrame(converter);
    }

    private JPanel createResultPanel() {
        selectResultTempScale = new JComboBox<>(converter.getScaleName());
        JLabel resultTemp = new JLabel();
        //resultTemp.setEditable(false);
        resultPanel = new JPanel(new GridLayout(1, 2));
        resultPanel.add(selectResultTempScale);
        resultPanel.add(resultTemp);
        selectResultTempScale.addActionListener(e -> calcResultTemp(resultTemp));
        return resultPanel;
    }

    private void calcResultTemp(JLabel resultTemp) {
        String convertedTemp = inputTemp.getText().trim().isEmpty() ? "0" : inputTemp.getText();
        double result = converter.convertTemp((String) selectInputScale.getSelectedItem(), (String) selectResultTempScale.getSelectedItem(),
                convertedTemp);
        resultTemp.setText(String.valueOf(result));
    }

    private JPanel createInputPanel() {
        selectInputScale = new JComboBox<>(converter.getScaleName());
        inputTemp = new JTextField(15);
        //фильтр
        ((AbstractDocument) inputTemp.getDocument()).setDocumentFilter(new DigitFilter());

        inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(selectInputScale);
        inputPanel.add(inputTemp);
        return inputPanel;
    }

    public void updateUI(String scaleName) {
        selectInputScale.addItem(scaleName);
        selectResultTempScale.addItem(scaleName);
    }

    public JFrame getFrame(){
        return frame;
    }
}
