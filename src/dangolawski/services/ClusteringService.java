package dangolawski.services;

import dangolawski.models.Cluster;
import dangolawski.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ClusteringService {

    float previousGroupVariability = 0;

    public LinkedHashSet<Player> makeClusters(LinkedHashSet<Player> randomlyAsignedPlayers, Set<Cluster>clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // wyliczenie srodkow ciezkosci

        LinkedHashSet<Player> clusteredPlayers = new LinkedHashSet<>(randomlyAsignedPlayers);

        while(true) {
            calculateMeans(clusteredPlayers, clusters);
            calculateSquaresOfDeviations(clusteredPlayers, clusters);
            float groupVariability = (float) clusters.stream().mapToDouble(Cluster::getSumOfSquaredDeviations).sum();
            if (groupVariability == previousGroupVariability) break;
            previousGroupVariability = groupVariability;
            changePlayerClusters(clusteredPlayers, clusters);
        }

        return clusteredPlayers;
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

    private void changePlayerClusters(LinkedHashSet<Player> players1, Set<Cluster> clusters1) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Integer, Float> distances = new HashMap<>();
        for(Player player : players1) {
            for(Cluster cluster : clusters1) {
                distances.put(cluster.getClusterNumber(), calculateDistance(player, cluster));
            }
            player.setClusterNumber(Collections.min(distances.entrySet(), Map.Entry.comparingByValue()).getKey());
        }
    }

    private float calculateDistance(Player player, Cluster cluster) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        float distance = 0;
        for(String attribute : Globals.playerAttributes) {
            distance += Math.pow((float) player.getClass().getMethod("get"+attribute).invoke(player) - (float) cluster.getClass().getMethod("get"+attribute+"Mean").invoke(cluster), 2);
        }
        return (float) Math.sqrt(distance);
    }

}
