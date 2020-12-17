package sudoku;

import java.util.Set;
import java.util.HashSet;

public class Square {
  private int i;
  private Set<Integer> possibilities = new HashSet<Integer>();
  private TBTS gridSq;

  public Square(Integer i) {
    this.i = i;
  }

  public boolean isEmpty() {
    return (this.i == 0);
  }

  public int getInt() {
    return this.i;
  }

  public void setInt(int i) {
    this.i = i;
  }

  public void setGridSq(TBTS sq) {
    this.gridSq = sq;
  }

  public TBTS getGridSq() {
    return this.gridSq;
  }

  public Set<Integer> getPossibilities() {
    return this.possibilities;
  }

  public void addPossibility(Integer i) {
    this.possibilities.add(i);
  }

  public void setPossibilities(Set<Integer> poss) {
    this.possibilities = poss;
  }

  public void remPossibility(Integer i) {
    this.possibilities.remove(i);
  }

  public String toString() {
    return Integer.toString(this.i);
  }

}
