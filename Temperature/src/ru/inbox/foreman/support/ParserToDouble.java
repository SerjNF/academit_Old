package ru.inbox.foreman.support;

public class ParserToDouble {
    public static double parseToDouble(String string) {
        String[] arrayString = string.split("[\\s,. ]+");
        double k = arrayString[0].charAt(0) == '-' ? -1.0 : 1.0;
        double before = Double.valueOf(arrayString[0]);
        double after = arrayString.length > 1 ? Double.valueOf(arrayString[1]) / (Math.pow(10.0, arrayString[1].trim().length())) : 0;
        return (before + after * k);
    }
}


