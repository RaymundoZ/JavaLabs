package com.raymundo.lab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

public class Injector {

    private static final String PROPERTIES_PATH = "src/main/resources/app.properties";
    private final Properties properties;

    public Injector() {
        properties = new Properties();
        try (FileInputStream stream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object inject(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(AutoInjectable.class)).forEach(field -> {
            try {
                Field f = object.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                f.set(object, Class.forName(properties.getProperty(f.getType().getName())).getConstructor(null).newInstance());
            } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException |
                     InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return object;
    }

}
