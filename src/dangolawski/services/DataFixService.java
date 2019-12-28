package dangolawski.services;

import dangolawski.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;

public class DataFixService {

    public void fixData(LinkedHashSet<Player> players) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LinkedHashSet<Player> fixedPlayers = new LinkedHashSet<>();
        for (Player player : players) {
            for (String attribute : Globals.playerAttributes) {
                float value = (float) player.getClass().getMethod("get"+attribute).invoke(player);
                if (value != -1) continue;
                player.getClass().getMethod("set"+attribute, Float.class).invoke(player,
                        Globals.mainCluster.getClass().getMethod("get"+attribute+"Mean").invoke(Globals.mainCluster));
            }
        }
    }
}
