package dangolawski.services;

import dangolawski.models.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class DataFactory {

    private List<String> forbiddenColumns;

    private String[] columnNames;

    public LinkedHashSet<Object> readDataAndCreatePlayers() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        LinkedHashSet<Object> playerSet = new LinkedHashSet<>();
        forbiddenColumns = Arrays.asList(Globals.forbiddenValues);
        BufferedReader csvReader = new BufferedReader(new FileReader(Globals.dataFilePath));
        String row;
        columnNames = csvReader.readLine().split(","); // remove first row of data file
        while((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            playerSet.add(createNewPlayer(data));
        }
        csvReader.close();
        return playerSet;
    }

    private Object createNewPlayer(String[] values) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> playerClass = Class.forName("dangolawski.models.Player"); // convert string classname to class
        Object player = playerClass.newInstance(); // invoke empty constructor
        // with single parameter, return void
        for(int i = 0; i < columnNames.length; i++) {
            if(forbiddenColumns.contains(columnNames[i])) {continue; }
            System.out.println("set" + columnNames[i] + " - " + values[i]);
            Method method = player.getClass().getMethod("set" + columnNames[i], String.class);
            method.invoke(player, values[i]); // pass arg
        }
        return player;
    }
}
