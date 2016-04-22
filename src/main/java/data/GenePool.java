package data;

import java.util.*;

import static model.service.UtilityService.TheRNG;

public class GenePool<E> {
    private TreeSet<E> pool;
    private Stack<E> backup;

    public void addToPool(E solution){
        if(!pool.contains(solution)){
            pool.add(solution);
        }
    }

    public Set<E> getPool(){ return pool; }

    public int size(){ return pool.size(); }

    public E getWorst() {
        backup.push(pool.pollLast());
        return backup.peek();
    }

    public E peekWorst() {
        return pool.last();
    }

    public E getBest(){
        backup.push(pool.pollFirst());
        return backup.peek();
    }

    public E peekBest() {
        return pool.first();
    }

    public void undo(){
        while (!backup.empty()) {
            E b = backup.pop();
            if (!pool.contains(b)) {
                pool.add(b);
            }
        }
    }

    public List<E> getCoupleFromTop25Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        int cutoff = sz/4;
        int husband = TheRNG().nextInt(cutoff);
        int wife = TheRNG().nextInt(cutoff);
        int idx = 0;
        Iterator<E> i = pool.iterator();
        while (i.hasNext() && idx++ < cutoff) {
            E o = i.next();
            if (idx == husband) {
                rst.add(o);
            } else if (idx == wife) {
                rst.add(o);
            }
        }

        return rst;
    }

    public List<E> getCoupleFromBottom25Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        int cutoff = sz/4;
        int husband = TheRNG().nextInt(cutoff);
        int wife = TheRNG().nextInt(cutoff);
        int idx = 0;
        Iterator<E> i = pool.descendingIterator();
        while (i.hasNext() && idx++ < cutoff) {
            E o = i.next();
            if (idx == husband) {
                rst.add(o);
            } else if (idx == wife) {
                rst.add(o);
            }
        }

        return rst;
    }

    public List<E> getCoupleFromMiddle50Percent() {
        List<E> rst = new ArrayList<>();
        int sz = size();
        int minCutoff = sz/4;
        int maxCutoff = sz - minCutoff;
        int husband = TheRNG().nextInt(minCutoff,maxCutoff);
        int wife = TheRNG().nextInt(minCutoff,maxCutoff);
        int idx = 0;
        Iterator<E> i = pool.iterator();
        while (i.hasNext() && idx++ < maxCutoff-minCutoff) {
            E o = i.next();
            if (idx == husband) {
                rst.add(o);
            } else if (idx == wife) {
                rst.add(o);
            }
        }

        return rst;
    }

    public List<E> getRandomCouple() {
        List<E> rst = new ArrayList<>();
        int sz = size();

        int husband = TheRNG().nextInt(sz);
        int wife = TheRNG().nextInt(sz);
        int idx = 0;
        Iterator<E> i = pool.iterator();
        while (i.hasNext() && idx++ < sz) {
            E o = i.next();
            if (idx == husband) {
                rst.add(o);
            } else if (idx == wife) {
                rst.add(o);
            }
        }

        return rst;
    }

    public GenePool() {
        this.pool = new TreeSet<>(Collections.reverseOrder());
        this.backup = new Stack<>();
    }
}
