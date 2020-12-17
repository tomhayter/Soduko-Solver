package sudoku;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class Sudoku {
  private List<Row> rows = new ArrayList<Row>();
  private List<TBTS> squares = new ArrayList<TBTS>();

  public Sudoku(ArrayList<Row> rows) {
    this.rows = rows;
    setupTBTS();
  }

  public Sudoku() {
  }

  public List<Row> getRows() {
    return this.rows;
  }

  public void addRow(Row r) {
    this.rows.add(r);
  }

  public void setRows(ArrayList<Row> rows) {
    this.rows = rows;
  }

  public void setupTBTS() {
    for (int i=0; i<9; i=i+3) {
      for (int j=0; j<9; j=j+3) {
        List<Square> temps = new ArrayList<Square>();
        for (int k=0; k<3; k++) {
          for (int l=0; l<3; l++) {
            temps.add(this.rows.get(i+k).getNums().get(j+l));
          }
        }
        this.squares.add(new TBTS(temps));
      }
    }

    for (TBTS t: this.squares) {
      for (Square s: t.getSquares()) {
        s.setGridSq(t);
      }
    }
  }

  public static Sudoku getSud(String path) throws IOException{
    try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
      Sudoku sud = new Sudoku();
      String line = bf.readLine();
      while (line != null) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int i=0; i<9; i++) {
          nums.add(Character.getNumericValue(line.charAt(i)));
        }
        sud.addRow(new Row(nums));
        line = bf.readLine();
      }
      sud.setupTBTS();
      return sud;
    } catch (IOException e) {
      throw e;
    }
  }

  public String toString() {
    String s = "";
    for (Row r: this.rows) {
      s = s + r.toString() + "\n";
    }
    return s;
  }
}
