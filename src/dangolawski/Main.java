package dangolawski;

import dangolawski.models.Cluster;
import dangolawski.models.Player;
import dangolawski.services.ClusteringService;
import dangolawski.services.DataFactory;
import dangolawski.services.Globals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    // TODO zaimplementowac zmiane wartosci -1 na srednie lub mediany

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        DataFactory dataFactory = new DataFactory();
        ClusteringService clusteringService = new ClusteringService();

        LinkedHashSet<Player> players = dataFactory.readDataAndCreatePlayers();
        Set<Cluster> clusters = DataFactory.createClusters();
        clusteringService.makeClusters(players, clusters);
        System.out.println(players);
    }
}
