package dangolawski.services;

import dangolawski.models.Cluster;
import dangolawski.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClusteringService {

    float previousGroupVariability = 0;

    public void makeClusters(LinkedHashSet<Player> players, Set<Cluster>clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // wyliczenie srodkow ciezkosci

        while(true) {
            calculateMeans(players, clusters);
            calculateSquaresOfDeviations(players, clusters);
            float groupVariability = (float) clusters.stream().mapToDouble(Cluster::getSumOfSquaredDeviations).sum();
            if (groupVariability == previousGroupVariability) break;
            previousGroupVariability = groupVariability;
            changePlayerClusters(players, clusters);
        }
    }

    private void calculateMeans(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for(Cluster cluster : clusters) {
            for(String attribute : Globals.playerAttributes) {
                float attributeValuesSum = 0;
                float playersCounter = 0;
                for(Player player : players) {
                    if(player.getClusterNumber() == cluster.getClusterNumber()){
                        playersCounter += 1;
                        attributeValuesSum += (float) player.getClass().getMethod("get" + attribute).invoke(player);
                    }
                }
                cluster.getClass().getMethod("set"+attribute+"Mean", Float.class).invoke(cluster, attributeValuesSum / playersCounter);
            }
        }
    }

    private void calculateSquaresOfDeviations(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for(Cluster cluster : clusters) {
            float sumOfSquares = 0;
            for(String attribute : Globals.playerAttributes) {
                for(Player player : players) {
                    if(player.getClusterNumber() == cluster.getClusterNumber()){
                        sumOfSquares += Math.pow((float) cluster.getClass().getMethod("get"+attribute+"Mean").invoke(cluster) - (float) player.getClass().getMethod("get"+attribute).invoke(player), 2);
                    }
                }
            }
            cluster.setSumOfSquaredDeviations(sumOfSquares);
        }

    }

    private void changePlayerClusters(LinkedHashSet<Player> players, Set<Cluster> clusters) {
        for(Player player : players){

        }
    }


}
