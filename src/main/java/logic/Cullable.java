package logic;

import data.GenePool;

public interface Cullable {
    GenePool cull(CullConfig config, GenePool input);
}