package ru.inbox.foreman.model;

import ru.inbox.foreman.UI.MainFrame;
import ru.inbox.foreman.support.ParserToDouble;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class ConvertTemperature {
    private MainFrame mFrame;
    private HashMap<String, double[]> fTempToCelsius;
    private HashMap<String, double[]> fTempToResultScale;

    public ConvertTemperature(MainFrame frame) {
        this.mFrame = frame;
        deserialization();
    }

    public String[] getScaleName() {
        return fTempToCelsius.keySet().toArray(new String[0]);
    }

    public double convertTemp(String selectedInputScale, String selectedResultScale, String inputTemp) {
        double[] kTempToCelsius = fTempToCelsius.get(selectedInputScale);
        double tempInCelsius = (ParserToDouble.parseToDouble(inputTemp) * kTempToCelsius[0] + kTempToCelsius[1]) * kTempToCelsius[2];
        double[] kTempToResult = fTempToResultScale.get(selectedResultScale);
        return (tempInCelsius * kTempToResult[0] + kTempToResult[1]) * kTempToResult[2];
    }

    public void addScale(String scaleName, double[] kInput, double[] kResult) {
        fTempToCelsius.put(scaleName, kInput);
        fTempToResultScale.put(scaleName, kResult);
        mFrame.updateUI(scaleName);
        serialization();
    }

    public void removeScale(String removedScale) {
        fTempToCelsius.remove(removedScale);
        fTempToResultScale.remove(removedScale);
        serialization();
    }

    private void serialization() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("converter.ser"))) {
            objectOutputStream.writeObject(fTempToCelsius);
            objectOutputStream.writeObject(fTempToResultScale);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mFrame.getFrame(), "Ошибка записи");
        }
    }

    private void deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("converter.ser"))) {
            fTempToCelsius = (HashMap<String, double[]>) objectInputStream.readObject();
            fTempToResultScale = (HashMap<String, double[]>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            fTempToCelsius = new HashMap<>();
            fTempToCelsius.put("Цельсия", new double[]{1, 0, 1});
            fTempToResultScale = new HashMap<>();
            fTempToResultScale.put("Цельсия", new double[]{1, 0, 1});
        }
    }
}
