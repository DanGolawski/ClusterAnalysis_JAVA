package dangolawski;

import dangolawski.models.Cluster;
import dangolawski.models.Player;
import dangolawski.services.ClusteringService;
import dangolawski.services.DataFactory;
import dangolawski.services.DataAnalysisService;
import dangolawski.services.DataFixService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    // TODO zaimplementowac zmiane wartosci -1 na srednie lub mediany

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        DataFactory dataFactory = new DataFactory();
        ClusteringService clusteringService = new ClusteringService();
        DataAnalysisService dataAnalysisService = new DataAnalysisService();
        DataFixService dataFixService = new DataFixService();

        LinkedHashSet<Player> players = dataFactory.readDataAndCreatePlayers();
        dataFactory.createMainCluster(players);
        dataFixService.fixData(players);
        Set<Cluster> clusters = DataFactory.createClusters();
        LinkedHashSet<Player> clusteredPlayers = clusteringService.makeClusters(players, clusters);
        dataAnalysisService.displayResults(clusteredPlayers, clusters);
    }

    private static void display(Player player) {
        System.out.print(player.getPlayerName() + "__" + player.getClusterNumber() + " | ");
    }
}
