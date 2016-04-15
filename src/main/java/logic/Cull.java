package logic;

import java.io.*;
import java.util.*;

public class Cull implements Cullable {

    public GAData cull(CullConfig config, GAData input) {
        if (input == null || input.getPopulationSize() == 0) {
            throw new IllegalArgumentException("input is null");
        }

        // establish that the config is sane
        if (config.getCullAmount() >= input.getPopulationSize()) {
            System.err.println(config.getCullAmount());
            System.err.println(input.getPopulationSize());
            throw new IllegalArgumentException("Cull amount can not be larger" +
                    " than population");
        }
        // second you modifiy the GAData using information from config
        for (int i = 0; i < config.getCullAmount(); i++) {
            input.removeWeakest();
        }

        return input;
    }
}