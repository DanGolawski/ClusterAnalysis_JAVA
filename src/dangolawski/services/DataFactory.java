package dangolawski.services;

import dangolawski.models.Cluster;
import dangolawski.models.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DataFactory {

    private List<String> forbiddenColumns;

    private String[] columnNames;

    public LinkedHashSet<Player> readDataAndCreatePlayers() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        LinkedHashSet<Object> playerSet = new LinkedHashSet<>();
        forbiddenColumns = Arrays.asList(Globals.forbiddenValues);
        BufferedReader csvReader = new BufferedReader(new FileReader(Globals.dataFilePath));
        String row;
        columnNames = splitData(csvReader.readLine()); // remove first row of data file and get column names
        while((row = csvReader.readLine()) != null) {
            String[] data = splitData(row);
            playerSet.add(createNewPlayer(data));
        }
        csvReader.close();
        getDesiredPlayerAttributes();
        return castObjectsToPlayers(playerSet);
//        return playerSet;
    }

    /**
     * metoda tworzy nowy obiekt Player - na podstawie skonkatenowanego stringa wywoływany jest setter
     * @param values
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private Object createNewPlayer(String[] values) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Random random = new Random();
        Class<?> playerClass = Class.forName("dangolawski.models.Player"); // convert string classname to class
        Object player = playerClass.newInstance(); // invoke empty constructor
        // with single parameter, return void
        for(int i = 0; i < columnNames.length; i++) {
            if(forbiddenColumns.contains(columnNames[i])) {continue; }
            Method method = player.getClass().getMethod("set" + columnNames[i], String.class);
            method.invoke(player, values[i]); // pass arg
        }
        player.getClass().getMethod("setPlayerName", String.class).invoke(player, values[39]);
        player.getClass().getMethod("setClusterNumber", Integer.class).invoke(player, random.nextInt(Globals.numberOfClusters)+1);
        return player;
    }

    /**
     * metoda zamieniajaca stringa na tablice poprzez ciecie elementow oddzielonych przecinkiem
     * ignorowane sa przecinki pomiedzy cudzyslowem
     * @param data
     * @return
     */
    private String[] splitData(String data) {
        int count = (int) data.chars().filter(ch -> ch == ',').count();
        boolean active = true;
        String[] splitedData = new String[count+1];
        int elementIndex = 0;
        StringBuffer dataElement = new StringBuffer();
        for(char c : data.toCharArray()) {
            if(c == '"') {
                active = !active;
                continue;
            }
            if(c == ',' && active) {
                splitedData[elementIndex++] = dataElement.toString();
                dataElement.delete(0, dataElement.length());
            }
            else dataElement.append(c);

        }
        splitedData[elementIndex++] = dataElement.toString();
        return splitedData;
    }

    private void getDesiredPlayerAttributes() {
        Globals.playerAttributes = new ArrayList<>();
        for(String attribute : columnNames) {
            if(!forbiddenColumns.contains(attribute)) Globals.playerAttributes.add(attribute);
        }
    }

    public static Set<Cluster> createClusters() {
        Set<Cluster> clusters = new HashSet<>();
        for(int i = 0; i < Globals.numberOfClusters; i++) {
            clusters.add(new Cluster(i+1));
        }

        return clusters;
    }

    public LinkedHashSet<Player> castObjectsToPlayers(LinkedHashSet<Object> objects) {
        LinkedHashSet<Player> players = new LinkedHashSet<>();
        for(Object object : objects) players.add(Player.class.cast(object));
        return players;
    }

    public void createMainCluster(LinkedHashSet<Player> players) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Globals.mainCluster = new Cluster(1);
        for (String attribute : Globals.playerAttributes) {
            float elementCounter = 0;
            float sum = 0;
            for (Player player : players) {
                float value = (float) player.getClass().getMethod("get"+attribute).invoke(player);
                if (value == -1) continue;
                elementCounter += 1;
                sum += value;
            }
            Globals.mainCluster.getClass().getMethod("set"+attribute+"Mean", Float.class).invoke(Globals.mainCluster, sum/elementCounter);
        }
    }
}
