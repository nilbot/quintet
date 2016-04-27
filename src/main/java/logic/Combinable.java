package logic;

import data.GenePool;

/**
 * Combinable:
 */
public interface Combinable {
    GenePool Combine(CombineConfig cfg, GenePool data);
}
