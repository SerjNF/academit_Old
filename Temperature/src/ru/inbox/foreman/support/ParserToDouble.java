package ru.inbox.foreman.support;

public class ParserToDouble {
    public static double parseToDouble(String string) {
        double k = string.charAt(0) == '-' ? -1.0 : 1.0;
        String[] arrayString = string.replaceAll("-","").split("[\\s,.]+");
        double before = Double.valueOf(arrayString[0]);
        double after = arrayString.length > 1 ? Double.valueOf(arrayString[1]) / (Math.pow(10.0, arrayString[1].trim().length())) : 0;
        return (before + after) * k;
    }
}


