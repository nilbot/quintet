package logic;

import data.GenePool;

/**
 * Combinable:
 */
public interface Combinable {
    GenePool combine(CombineConfig cfg, GenePool data);
}
