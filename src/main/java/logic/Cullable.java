package logic;

import config.CullConfig;
import data.GenePool;

public interface Cullable {
    GenePool cull(CullConfig config, GenePool input);
}