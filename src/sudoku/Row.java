package sudoku;
import java.util.List;
import java.util.ArrayList;

public class Row {
  private List<Square> numbers = new ArrayList<Square>();

  public Row(List<Integer> nums) {
    for (Integer i: nums) {
      this.numbers.add(new Square(i));
    }
  }

  public String toString() {
    String s = "";
    for (Square i: this.numbers) {
      s = s + i.toString();
    }
    return s;
  }

  public List<Square> getNums() {
      return this.numbers;
  }

}
