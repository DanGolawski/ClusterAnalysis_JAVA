package dangolawski.services;

import dangolawski.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.OptionalDouble;

public class StandarizationService {

    public void standardize(LinkedHashSet<Player> players) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Player maxPlayer = createPlayerWithMaxValues(players);
        for (Player player : players) standardizeAttribute(player, maxPlayer);

    }

    private void standardizeAttribute(Player player, Player maxPlayer) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String attr : Globals.playerAttributes) {
            float attrPrev = (float) player.getClass().getMethod("get"+attr).invoke(player);
            float attrMax = (float) maxPlayer.getClass().getMethod("get"+attr).invoke(maxPlayer);
            player.getClass().getMethod("set"+attr, Float.class).invoke(player, attrPrev/attrMax);
        }
    }

    private Player createPlayerWithMaxValues(LinkedHashSet<Player> players) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Player maxPlayer = new Player();
        for (String attr : Globals.playerAttributes)
            maxPlayer.getClass().getMethod("set"+attr, Float.class).invoke(maxPlayer,
                    getMaxValueByAttribute(players, attr));
        System.out.println(maxPlayer);
        return maxPlayer;
    }

    private float getMaxValueByAttribute(LinkedHashSet<Player> players, String attr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        float maxValue = -1;
        for (Player player : players) {
            float value = (float) player.getClass().getMethod("get"+attr).invoke(player);
            if (value > maxValue) maxValue = value;
        }
        return maxValue;
    }
}
