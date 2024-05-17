package org.example.functions;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.split;

public class ParseTextFunction {
    public static List<Double> parseTextToDoubleUsingNumberUtils(String text){
        return parseTextUsingNumberUtils(text, t -> Double.valueOf(t));
    }

    public static <T> List<T> parseTextUsingNumberUtils(String text, Function<String, T> func){
        return Arrays.stream(text.split(","))
                .filter(t -> !t.isEmpty())
                .map(String::trim)
                .map(ParseTextFunction::sanitizeString)
                .filter(NumberUtils::isCreatable)
                .map(func::apply).toList();
    }

    private static String sanitizeString(String value){
        return value.replaceAll("//$", "")
                .replaceAll("xf", "")
                .replaceAll("x", "");
    }

    public static List<Double> parseTextToDouble(String text){
        return parseText(text, t -> Double.valueOf(sanitizeString(t)));
    }

    public static List<Integer> parseTextToInteger(String text){
        return parseText(text, t -> Integer.valueOf(sanitizeString(t)));
    }

    public static <T> List<T> parseText(String text, Function<String, T> func){
        return Arrays.stream(text.split(",")).filter(t -> !t.isEmpty())
                .map(t -> {
                    try {
                        return func.apply(t.trim());
                    }catch (NumberFormatException ne){}
                    return null;
                }).filter(Objects::nonNull).toList();
    }

    public static <T> T applyFunctionToParseData(String text, Function<String, T> func){
        return func.apply(text);
    }

    public static String extractNumbersUsingLambda(String text) {
        return Arrays.stream(text.split(","))
                .map(String::trim)
                .filter(NumberUtils::isCreatable)
                .collect(Collectors.joining(", "));
    }

    public static String extractNumbers(String text) {
        String[] elements = text.split(",");
        StringBuilder sb = new StringBuilder();
        for (String ele : elements) {
            ele = ele.trim();
            if (NumberUtils.isCreatable(ele)) {
                if (sb.isEmpty()) sb.append(ele);
                else sb.append(", ").append(ele);
            }
        }
        return sb.toString();
    }
}
