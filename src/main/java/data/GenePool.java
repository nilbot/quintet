package data;

import java.util.*;

/**
 * Created by Kevin on 15/04/2016.
 */
public class GenePool<E> {
    private TreeSet<E> pool;
    private E backup = null;

    void addToPool(E solution){
        if(!pool.contains(solution)){
            pool.add(solution);
        }
    }

    Set<E> getPool(){ return pool; }

    int Size(){ return pool.size(); }

    E getBottom() {
        backup = pool.last();
        pool.remove(backup);
        return backup;
    }

    E getTop(){
        backup = pool.first();
        pool.remove(backup);
        return backup;
    }

    void Undo(){
        if(!pool.contains(backup)){
            pool.add(backup);
            backup = null;
        }
    }
}
