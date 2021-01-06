package jmotw.text;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.WordUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextExample {

    public static void capitalize() {
        System.out.println(StringUtils.capitalize("hello world"));    // Hello world
        System.out.println(WordUtils.capitalizeFully("hello world")); // Hello World
    }

    public static void stringTemplate() {
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        System.out.println(resolvedString);
    }

    public static void constants() {
        List<String> alphas = IntStream.rangeClosed('a', 'z')
                .boxed()
                .map(i -> Character.toString(i.intValue()))
                .collect(Collectors.toList());
        System.out.println(alphas);
    }

}
