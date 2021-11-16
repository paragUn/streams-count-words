package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {
    public String countWords(List<String> lines) {
        String result = lines
                .stream()
                .flatMap(str -> Arrays.stream(str.toLowerCase().split("[^а-яА-Яa-zA-Z]")))
                .filter(str -> str.length() >= 4)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(w -> w.getValue() >= 10)
                .sorted(Map.Entry.comparingByKey())
                .sorted((x1, x2) ->(int) (x2.getValue() - x1.getValue()))
                .map(w -> String.format("%s - %d\n", w.getKey(), w.getValue()))
                .collect(Collectors.joining());

        return deleteLastString(result);
    }
    public String deleteLastString (String string){
        StringBuilder builder = new StringBuilder(string);
        return String.valueOf(builder.deleteCharAt(builder.length()-1));
    }
}
