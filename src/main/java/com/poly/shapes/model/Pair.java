package com.poly.shapes.model;

public class Pair<U, V> {

    private U first;
    private V second;
    
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public U getFirst() {
        return first;
    }
    
    public V getSecond() {
        return second;
    }
    
    public void setFirst(U first) {
        this.first = first;
    }
    
    public void setSecond(V second) {
        this.second = second;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.first != other.first && (this.first == null || !this.first.equals(other.first))) {
            return false;
        }
        if (this.second != other.second && (this.second == null || !this.second.equals(other.second))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 97 * hash + (this.second != null ? this.second.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "Pair [fst=" + first + ", snd=" + second + "]";
    }
    
}
