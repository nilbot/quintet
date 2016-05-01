package data;

import java.util.*;

import static model.service.UtilityService.TheRNG;

public class GenePool<E> {
    private TreeSet<E> pool;

    public void addToPool(E solution){
        if(!pool.contains(solution)){
            pool.add(solution);
        }
    }

    public Set<E> getPool(){ return pool; }

    public int size(){ return pool.size(); }

    public E getWorst() {
        return pool.pollLast();
    }

    public E peekWorst() {
        return pool.last();
    }

    public E getBest(){
        return pool.pollFirst();
    }

    public E peekBest() {
        return pool.first();
    }

    public List<E> getCoupleFromTop25Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        if (sz < 2) {
            throw new RuntimeException("genePool has only 1 item");
        }
        int cutoff = sz/4;
        TreeSet<Integer> pair = new TreeSet<>();
        while(pair.size() < 2) {
            pair.add(TheRNG().nextInt(cutoff));
        }
        // use the values in cullIndex to cull elements in the genepool
        Iterator<Integer> ci = pair.iterator();
        Iterator<E> pi = pool.iterator();
        int currVal = ci.next();
        int i = 0;

        while (pi.hasNext()) {
            // only retrieve the iterator value once at the start of the loop
            E item = pi.next();
            if (i == currVal) {
                rst.add(item);
                if (ci.hasNext()) {
                    currVal = ci.next();
                } else { break; }
            }
            i++;
        }
        return rst;
    }

    public List<E> getCoupleFromBottom25Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        if (sz < 2) {
            throw new RuntimeException("genePool has only 1 item");
        }
        int cutoff = sz/4;
        TreeSet<Integer> pair = new TreeSet<>();
        while(pair.size() < 2) {
            pair.add(TheRNG().nextInt(sz-cutoff,sz));
        }
        // use the values in cullIndex to cull elements in the genepool
        Iterator<Integer> ci = pair.iterator();
        Iterator<E> pi = pool.iterator();
        int currVal = ci.next();
        int i = 0;

        while (pi.hasNext()) {
            // only retrieve the iterator value once at the start of the loop
            E item = pi.next();
            if (i == currVal) {
                rst.add(item);
                if (ci.hasNext()) {
                    currVal = ci.next();
                } else { break; }
            }
            i++;
        }
        return rst;
    }

    public List<E> getCoupleFromMiddle50Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        if (sz < 2) {
            throw new RuntimeException("genePool has only 1 item");
        }
        int cutoff = sz/4;
        TreeSet<Integer> pair = new TreeSet<>();
        while(pair.size() < 2) {
            pair.add(TheRNG().nextInt(cutoff,sz-cutoff));
        }
        // use the values in cullIndex to cull elements in the genepool
        Iterator<Integer> ci = pair.iterator();
        Iterator<E> pi = pool.iterator();
        int currVal = ci.next();
        int i = 0;

        while (pi.hasNext()) {
            // only retrieve the iterator value once at the start of the loop
            E item = pi.next();
            if (i == currVal) {
                rst.add(item);
                if (ci.hasNext()) {
                    currVal = ci.next();
                } else { break; }
            }
            i++;
        }
        return rst;
    }

    public List<E> getRandomCouple() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        if (sz < 2) {
            throw new RuntimeException("genePool has only 1 item");
        }
        int cutoff = sz/4;
        TreeSet<Integer> pair = new TreeSet<>();
        while(pair.size() < 2) {
            pair.add(TheRNG().nextInt(sz));
        }
        // use the values in cullIndex to cull elements in the genepool
        Iterator<Integer> ci = pair.iterator();
        Iterator<E> pi = pool.iterator();
        int currVal = ci.next();
        int i = 0;

        while (pi.hasNext()) {
            // only retrieve the iterator value once at the start of the loop
            E item = pi.next();
            if (i == currVal) {
                rst.add(item);
                if (ci.hasNext()) {
                    currVal = ci.next();
                } else { break; }
            }
            i++;
        }
        return rst;
    }

    public GenePool() {
        this.pool = new TreeSet<>();
    }
}
