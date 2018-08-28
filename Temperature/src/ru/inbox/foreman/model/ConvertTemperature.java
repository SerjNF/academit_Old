package ru.inbox.foreman.model;

import ru.inbox.foreman.UI.MainFrame;
import ru.inbox.foreman.support.ParserToDouble;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class ConvertTemperature {
    private MainFrame mFrame;
    private HashMap<String, double[]> coefficientsToCelsius;
    private HashMap<String, double[]> coefficientsToResult;

    public ConvertTemperature(MainFrame frame) {
        this.mFrame = frame;
        deserialization();
    }

    public String[] getScaleName() {
        return coefficientsToCelsius.keySet().toArray(new String[0]);
    }

    public double convertTemp(String selectedInputScale, String selectedResultScale, String inputTemp) {
        double[] kTempToCelsius = coefficientsToCelsius.get(selectedInputScale);
        double tempInCelsius = (ParserToDouble.parseToDouble(inputTemp) * kTempToCelsius[0] + kTempToCelsius[1]) * kTempToCelsius[2];
        double[] kTempToResult = coefficientsToResult.get(selectedResultScale);
        return (tempInCelsius * kTempToResult[3] + kTempToResult[4]) * kTempToResult[5];
    }

    public void addScale(String scaleName, double[] kInput) {
        coefficientsToCelsius.put(scaleName, kInput);
        coefficientsToResult.put(scaleName, kInput);
        mFrame.updateScales(scaleName);
        serialization();
    }

    public void removeScale(String removedScale) {
        coefficientsToCelsius.remove(removedScale);
        coefficientsToResult.remove(removedScale);
        serialization();
    }

    private void serialization() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("converter.ser"))) {
            objectOutputStream.writeObject(coefficientsToCelsius);
            objectOutputStream.writeObject(coefficientsToResult);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mFrame.getFrame(), "Ошибка записи");
        }
    }

    private void deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("converter.ser"))) {
            coefficientsToCelsius = (HashMap<String, double[]>) objectInputStream.readObject();
            coefficientsToResult = (HashMap<String, double[]>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            coefficientsToCelsius = new HashMap<>();
            coefficientsToCelsius.put("Цельсия", new double[]{1, 0, 1, 0, 0, 0});
            coefficientsToResult = new HashMap<>();
            coefficientsToResult.put("Цельсия", new double[]{0, 0 ,0, 1, 0, 1});
        }
    }
}
