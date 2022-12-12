package com.raymundo.lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that parses CSV table to {@link List} of {@link Person}
 *
 * @author RaymundoZ
 */
public class CSVResolver {

    /**
     * @value List that contains {@link Person} instances
     */
    private static final List<Person> list = new ArrayList<>();

    /**
     * @value Value to fill a field id of the {@link Division} object
     */
    private static long genId;

    /**
     * A method that parses file to {@link List} of {@link Person}
     *
     * @param pathToFile A path to parsable file
     * @return {@link List} of {@link Person}
     */
    public static List<Person> getListFromFile(String pathToFile) {
        list.clear();
        genId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line = reader.readLine(); //need to avoid first non-parsable line
            while ((line = reader.readLine()) != null)
                lineToList(line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(list);
    }

    /**
     * A method that parses a line to a {@link Person} instance and adds it to {@link CSVResolver#list}
     *
     * @param line Single line of a parsable table
     */
    private static void lineToList(String line) {
        String[] data = line.split(";");
        long id = Long.parseLong(data[0]);
        String name = data[1];
        Gender gender = data[2].equals("Male") ? Gender.MALE : Gender.FEMALE;
        List<Integer> date = Arrays.stream(data[3].split("\\.")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        Date birthDate = new Calendar.Builder().setDate(date.get(2), date.get(1) - 1, date.get(0)).build().getTime();
        Division division = new Division(genId++, data[4]);
        double salary = Double.parseDouble(data[5]);
        list.add(new Person(id, name, gender, birthDate, division, salary));
    }

}
