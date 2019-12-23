package dangolawski;

import dangolawski.services.DataFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        DataFactory dataFactory = new DataFactory();

        LinkedHashSet<Object> players = dataFactory.readDataAndCreatePlayers();
        System.out.println(players);
    }
}
