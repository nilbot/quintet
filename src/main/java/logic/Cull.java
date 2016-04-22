package logic;

import data.GenePool;

import java.io.*;
import java.util.*;

public class Cull implements Cullable {

    public GenePool cull(CullConfig config, GenePool input) {
        if (input == null || input.Size() == 0) {
            throw new IllegalArgumentException("input is null");
        }

        // establish that the config is sane
        if (config.getCullAmount() >= input.Size()) {
            System.err.println(config.getCullAmount());
            System.err.println(input.Size());
            throw new IllegalArgumentException("Cull amount can not be larger" +
                    " than population");
        }
        // modify the genepool using information from config
        for (int i = 0; i < config.getCullAmount(); i++) {
            input.getBottom();
        }

        return input;
    }
}