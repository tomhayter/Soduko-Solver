import sudoku.*;
import sudoku.solver.*;

import java.io.IOException;

public class SolverManager {
  public static void main(String[] args) {
    try {
      Sudoku sd = Sudoku.getSud("../res/sud1.txt");
      System.out.println(sd.toString());
      Solver.Solve(sd);
      System.out.println(sd.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
