package logic;

import java.io.*;
import java.util.*;

public class Cull implements Cullable {

    public GAData cull(CullConfig config, GAData input) throws Exception {

        // establish that the config is sane
        if (config.getCullAmount() >= input.getPopulationSize()) {
            System.out.println(config.getCullAmount());
            System.out.println(input.getPopulationSize());
            throw new Exception("Cull amount can not be larger than population");
        }
        // second you modifiy the GAData using information from config
        for (int i = 0; i < config.getCullAmount(); i++) {
            input.removeWeakest();
        }

        return input;
    }
}