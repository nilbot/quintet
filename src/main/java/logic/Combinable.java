package logic;

import config.CombineConfig;
import data.GenePool;

/**
 * Combinable:
 */
public interface Combinable {
    GenePool combine(CombineConfig cfg, GenePool data, int children);
}
