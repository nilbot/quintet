package model;

/**
 * Project class is the String class. We just don't fancy the whole
 * String.intern() fiasco.
 */
public class Project {
    private String projectName;
    public Project(String text) {
        this.projectName = text.intern();
    }
    @Override
    public int hashCode() {
        return this.projectName.hashCode();
    }
    @Override
    public String toString() {
        return this.projectName;
    }
    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
    public boolean equals(Project other){
        return this.hashCode() == other.hashCode();
    }
}
