package data;

import java.util.*;

/**
 * Created by Kevin on 15/04/2016.
 */
public class GenePool<E> {
    private TreeSet<E> pool;
    private E backup = null;

    public void addToPool(E solution){
        if(!pool.contains(solution)){
            pool.add(solution);
        }
    }

    public Set<E> getPool(){ return pool; }

    public int Size(){ return pool.size(); }

    public E getBottom() {
        backup = pool.last();
        pool.remove(backup);
        return backup;
    }

    public E getTop(){
        backup = pool.first();
        pool.remove(backup);
        return backup;
    }

    public void Undo(){
        if(!pool.contains(backup)){
            pool.add(backup);
            backup = null;
        }
    }

    public GenePool() {
        this.pool = new TreeSet<>();
    }
}
