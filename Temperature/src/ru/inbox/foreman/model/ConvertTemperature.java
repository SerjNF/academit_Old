package ru.inbox.foreman.model;

import ru.inbox.foreman.UI.MainFrame;


import java.util.HashMap;
import java.util.function.Function;

public class ConvertTemperature {
    MainFrame mFrame;
    private HashMap<String, Function<Long, Long>> fTempToCelsius;
    private HashMap<String, Function<Long, Long>> fTempToResultScale;

    public ConvertTemperature(MainFrame frame) {
        this.mFrame = frame;
        fTempToCelsius = new HashMap<>();
        fTempToCelsius.put("Цельсия", d -> d);
        fTempToResultScale = new HashMap<>();
        fTempToResultScale.put("Цельсия", d -> d);
    }

    public String[] getInputScaleName() {
        return fTempToCelsius.keySet().toArray(new String[fTempToCelsius.size()]);
    }
    public String[] getResultScaleName() {
        return fTempToResultScale.keySet().toArray(new String[fTempToCelsius.size()]);
    }

    public String getLastScale(){
        return fTempToCelsius.keySet().toArray(new String[fTempToCelsius.size()])[fTempToCelsius.size() - 1];
    }

    public double convertTemp(String selectedInputScale, String selectedResultScale, Object inputTemp) {
        double tempInCelsius = fTempToCelsius.get(selectedInputScale).apply((Long) inputTemp);
        return fTempToResultScale.get(selectedResultScale).apply((long) tempInCelsius);

    }

    public void addInputScale(String scaleName, Function<Long, Long> functionInput, Function<Long, Long> functionResult){
        fTempToCelsius.put(scaleName, functionInput);
        fTempToResultScale.put(scaleName, functionResult);
        mFrame.updateUI();
    }
}
