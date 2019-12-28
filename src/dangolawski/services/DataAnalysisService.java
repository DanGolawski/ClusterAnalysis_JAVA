package dangolawski.services;

import dangolawski.models.Cluster;
import dangolawski.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataAnalysisService {

    public void displayResults(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        getNumberOfElementsForEveryCluster(players, clusters);
        calculateStandardDeviationForEveryCluster(players, clusters);
        calculateRadiusForEveryCluster(players, clusters);
        calculatePrefferedFootRightMean(players, clusters);
        calculateCharacteristicsForAttributes(players, clusters);
        calculateDistancesBetweenClusters(clusters);
        calculateMembership(clusters, players);
        System.out.println("\n" + "Liczba wszystkich obserwacji : " + players.size());
        calculateStandardDeviationForAllObjects(players);
    }

    // 1
    // 2232, 579, 2341, 1716
    private void getNumberOfElementsForEveryCluster(LinkedHashSet<Player> players, Set<Cluster> clusters) {
        System.out.println("\n" + "Liczba elementów : ");
        for (Cluster cluster : clusters)
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + getNumberOfElementsForParticularCluster(cluster, players));
    }

    // 2
    // 58.63,  53.66,  45.47,  60.53
    private void calculateStandardDeviationForEveryCluster(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println("\n" + "Odchylenia standardowe : ");
        for (Cluster cluster : clusters) {
            float sumOfSquares = 0;
            int elemCounter = 0;
            for(Player player : players) {
                if (player.getClusterNumber() != cluster.getClusterNumber()) continue;
                elemCounter += 1;
                sumOfSquares += calculateSumOfSquaredDifferencesOfAllAttributes(player, cluster);
            }
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + calculateStandardDeviation(sumOfSquares, elemCounter));
        }
    }

    // 3
    // 123.96,  115.50,  98.78,  105.31
    private void calculateRadiusForEveryCluster(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println("\n" + "Promienie skupienia :");
        for (Cluster cluster : clusters)
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + getTheLargestDistance(cluster, players));
    }

    // 4
    // 0.76,  0,78,  0.69,  0,87
    private void calculatePrefferedFootRightMean(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println("\n" + "Wartości średnie dla preffered_right_foot :");
        for (Cluster cluster : clusters) {
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + calculateAverageForAttribute("preferred_foot", cluster, players));
        }
    }

    // 5
    // 26.53,  41.0,  17.0,  4.33 // 26.91,  40.0,  17.0,  4.67 // 29.25,  44.0,  17.0,  5.03 // 27.93,  42.0,  18.0,  4.15
    // 163.57,  164.10,  184.04,  173.63
    // 179.88,  179.85,  188.88,  184.98
    // 70.54,   54,60,   29.94,   69.70
    private void calculateCharacteristicsForAttributes(LinkedHashSet<Player> players, Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println("\n" + "Charakterystyki dla Age :");
        for (Cluster cluster : clusters) {
            System.out.print("Skupienie " + cluster.getClusterNumber() + " : ");
            System.out.print("wartosc srednia = " + calculateAverageForAttribute("age", cluster, players));
            System.out.print(" wartosc maksymalna = " + calculateMaxValueForAttribute("age", cluster, players));
            System.out.print(" wartosc minimalna = " + calculateMinValueForAttribute("age", cluster, players));
            System.out.println(" odchylenie standardowe = " + CalculateStandardDeviationForAttribute("age", cluster, players));
        }
        System.out.println("\n" + "Wartość średnia dla Weight :");
        for (Cluster cluster : clusters) {
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + "wartosc srednia = "
                    + calculateAverageForAttribute("weight", cluster, players));
        }
        System.out.println("\n" + "Wartość średnia dla Height :");
        for (Cluster cluster : clusters) {
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + "wartosc srednia = "
                    + calculateAverageForAttribute("height", cluster, players));
        }
        System.out.println("\n" + "Wartość średnia dla Aggression :");
        for (Cluster cluster : clusters) {
            System.out.println("Skupienie " + cluster.getClusterNumber() + " : " + "wartosc srednia = "
                    + calculateAverageForAttribute("aggression", cluster, players));
        }
    }

    // 6
    // 77.86,   250,55
    private void calculateDistancesBetweenClusters(Set<Cluster> clusters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Cluster[] nearestClusters = new Cluster[2];
        Cluster[] furthestForEachOther = new Cluster[2];
        boolean first = true;
        float minDistance = 0;
        float maxDistance = 0;
        float currDistance;
        for (Cluster cluster1 : clusters) {
            for (Cluster cluster2 : clusters) {
                if (cluster1.equals(cluster2)) continue;
                currDistance = calculateDistance(cluster1, cluster2);
                if (currDistance < minDistance || first) {
                    minDistance = currDistance;
                    nearestClusters[0] = cluster1;
                    nearestClusters[1] = cluster2;
                }
                if (currDistance > maxDistance || first) {
                    maxDistance = currDistance;
                    furthestForEachOther[0] = cluster1;
                    furthestForEachOther[1] = cluster2;
                }
                first = false;
            }
        }

        System.out.println("\n" + "Odległości między skupieniami");
        System.out.print("Najbliżej położone względem siebie : ");
        System.out.print("Skupienie " + nearestClusters[0].getClusterNumber() + " --- ");
        System.out.println("Skupienie " + nearestClusters[1].getClusterNumber() + "   odleglosc =  " + minDistance);
        System.out.print("Najdalej położone względem siebie : ");
        System.out.print("Skupienie " + furthestForEachOther[0].getClusterNumber() + " --- ");
        System.out.println("Skupienie " + furthestForEachOther[1].getClusterNumber() + "   odleglosc =  " + maxDistance);
    }

    // 7
    //
    private void calculateMembership(Set<Cluster> clusters, LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println("\n"+"Przynależność obserwacji do skupienia :");
        for (Cluster cluster : clusters) {
            Player furthestPlayer = getFurthestPlayer(cluster, players);
            System.out.print("Najdalej wysuniety obiekt dla Skupienia " + cluster.getClusterNumber() + " : ");
            System.out.print(furthestPlayer.getPlayerName() + ",   odleglosc od srodka=" + calculateDistance(furthestPlayer, cluster));
            System.out.println(",   odleglosc od srodka zmiennej Overall-Rating="+Math.sqrt(calculateSquaredDifference(furthestPlayer, cluster, "overall_rating")));
        }
        for (Cluster cluster : clusters) {
            Player nearestPlayer = getNearestPlayer(cluster, players);
            System.out.print("Najbliżej wysuniety obiekt dla Skupienia " + cluster.getClusterNumber() + " : ");
            System.out.print(nearestPlayer.getPlayerName() + ",   odleglosc od srodka=" + calculateDistance(nearestPlayer, cluster));
            System.out.println(",   odleglosc od srodka zmiennej Overall-Rating="+Math.sqrt(calculateSquaredDifference(nearestPlayer, cluster, "overall_rating")));
        }
    }

    // wyznacza obiekt skupienia wysunięty najdalej od środka podanego skupienia
    private Player getFurthestPlayer(Cluster cluster, LinkedHashSet<Player> players) {
        return players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber())
                .max(Comparator.comparingDouble(player -> {
                    try {
                        return calculateDistance(player, cluster);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                })).get();
    }

    // wyznacza obiekt skupienia wysunięty najbliżej od środka podanego skupienia
    private Player getNearestPlayer(Cluster cluster, LinkedHashSet<Player> players) {
        return players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber())
                .min(Comparator.comparingDouble(player -> {
                    try {
                        return calculateDistance(player, cluster);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                })).get();
    }


    // wylicza odchylenie standardowe dla podanego atrybutu na podstawie obiektow należących do podanego skupienia
    private float CalculateStandardDeviationForAttribute(String attribute, Cluster cluster, LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        float sumOfSquaredDifferences = 0;
        int numberOfElements = 0;
        for(Player player : players) {
            if (player.getClusterNumber() != cluster.getClusterNumber()) continue;
            numberOfElements += 1;
            sumOfSquaredDifferences += calculateSquaredDifference(player, cluster, attribute);
        }
        return (float) Math.sqrt(sumOfSquaredDifferences / numberOfElements);
    }

    // wylicza kwadrat różnicy dla podanego atrybutu
    private float calculateSquaredDifference(Player player, Cluster cluster, String attribute) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (float) Math.pow((getValueByGetter(player, attribute) - getValueByGetter(cluster, attribute+"Mean")), 2);
    }

    // wyszukuje obiekt o najmniejszej wartości podanego atrybutu i zwraca wartość
    private float calculateMinValueForAttribute(String attribute, Cluster cluster, LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Player minPlayer = players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber())
                .min((player1, player2) -> {
                    try {
                        return Float.compare(getValueByGetter(player1, attribute), getValueByGetter(player2, attribute));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }).get();
        return getValueByGetter(minPlayer, attribute);
    }

    // wyszukuje obiekt o największej wartości podanego atrybutu i zwraca wartość
    private float calculateMaxValueForAttribute(String attribute, Cluster cluster, LinkedHashSet<Player> players) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Player maxPlayer = players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber())
                .max((player1, player2) -> {
                    try {
                        return Float.compare(getValueByGetter(player1, attribute), getValueByGetter(player2, attribute));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
        }).get();
        return getValueByGetter(maxPlayer, attribute);
    }

    // wylicza średnią arytmetyczną wartości podanego atrybutu dla obiektów należących do podanego skupienia
    private float calculateAverageForAttribute(String attribute, Cluster cluster, LinkedHashSet<Player> players) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        float sum = 0;
        int playerCounter = 0;
        for (Player player : players) {
            if (player.getClusterNumber() != cluster.getClusterNumber()) continue;
            playerCounter += 1;
            sum += getValueByGetter(player, attribute);
        }
        return sum / playerCounter;
    }

    // wyznacza obiekt skupienia wysunięty najdalej od podanego skupienia i zwraca odległość
    private float getTheLargestDistance(Cluster cluster, LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Player farPlayer = players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber())
                .max(Comparator.comparingDouble(player -> {
            try {
                return calculateDistance(player, cluster);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        })).get();
        return calculateDistance(farPlayer, cluster);
    }

    // wylicza odległość euklidesową dla obiektu i skupienia
    private float calculateDistance(Player player, Cluster cluster) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (float) Math.sqrt(calculateSumOfSquaredDifferencesOfAllAttributes(player, cluster));
    }

    // wylicza odległość euklidesową dla dwóch skupienia
    private float calculateDistance(Cluster cluster1, Cluster cluster2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (float) Math.sqrt(calculateSumOfSquaredDifferencesOfAllAttributes(cluster1, cluster2));
    }

    // wylicza odchylenie standardowe na podstawie sumy i liczby elementów
    private float calculateStandardDeviation(float sumOfSquares, int elemCounter) {
        return (float) Math.sqrt(sumOfSquares / elemCounter);
    }

    // wylicza sumę kwadratów różnic wszystkich atrybutów dla obiektu i skupienia
    private float calculateSumOfSquaredDifferencesOfAllAttributes(Player player, Cluster cluster) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        float sum = 0;
        for (String attr : Globals.playerAttributes)
            sum += Math.pow(getValueByGetter(player, attr) - getValueByGetter(cluster, attr+"Mean"), 2);
        return sum;
    }

    // wylicza sumę kwadratów różnic wszystkich atrybutów dla dwóch skupień
    private float calculateSumOfSquaredDifferencesOfAllAttributes(Cluster cluster1, Cluster cluster2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        float sum = 0;
        for (String attr : Globals.playerAttributes)
            sum += Math.pow(getValueByGetter(cluster1, attr+"Mean") - getValueByGetter(cluster2, attr+"Mean"), 2);
        return sum;
    }

    // uruchamia getter obiektu i zwraca wartość dla podanego atrybutu
    private float getValueByGetter(Object object, String attribute) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (float) object.getClass().getMethod("get"+attribute).invoke(object);
    }

    // zwraca liczbę obiektów należących do danego skupienia
    private int getNumberOfElementsForParticularCluster(Cluster cluster, Set<Player> players) {
        return (int) players.stream().filter(player -> player.getClusterNumber() == cluster.getClusterNumber()).count();
    }

    // 8
    public void calculateStandardDeviationForAllObjects(LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        float sum = 0;
        int numberOfElements = players.size();
        for (Player player : players) {
            sum += calculateSumOfSquaredDifferencesOfAllAttributes(player, Globals.mainCluster);
        }
        System.out.println("\n" + "Odchylenie standardowe całego zbioru danych : " + Math.sqrt(sum / numberOfElements));
    }
}
