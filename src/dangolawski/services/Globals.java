package dangolawski.services;

import java.util.ArrayList;

public class Globals {

    public static String dataFilePath = "src/dangolawski/players202.csv";

    public static final String[] forbiddenValues = {"player_name", "birthday", "p_id", "date"};

    public static final int numberOfClusters = 4;

    public static final String[] nominalValues = {"low", "medium", "high"};

    public static final String[] footNominalValues = {"left", "right"};

    public static ArrayList<String> playerAttributes;
}
