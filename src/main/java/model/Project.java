package model;

/**
 * Project class is the String class. We just don't fancy the whole
 * String.intern() fiasco.
 */
public class Project {
    private String internal;
    public Project(String text) {
        this.internal = text.intern();
    }
    @Override
    public int hashCode() {
        return this.internal.hashCode();
    }
    @Override
    public String toString() {
        return this.internal;
    }
    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
    public boolean equals(Project other){
        return this.hashCode() == other.hashCode();
    }
}
