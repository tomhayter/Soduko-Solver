package sudoku.solver;

import sudoku.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Solver {

  public static void Solve(Sudoku sud) {
    boolean finished = false;
    while(!finished) {
      finished = step(sud);
      System.out.println(sud);
    }
  }

  public static boolean step(Sudoku sud) {
    // Create set of digits 0-9
    Set<Integer> digits = new HashSet<Integer>();
    for (int i=1; i<10; i++) {digits.add(i);}
    for (Row r: sud.getRows()) {
      // Create and fill a set of numbers in this row
      Set<Integer> cantBeRow = new HashSet<Integer>();
      for (Square s: r.getNums()) {
        if (!s.isEmpty()) {
          cantBeRow.add(s.getInt());
        }
      }
      for (Square sq: r.getNums()) {
        
        if (sq.isEmpty()) {
          Set<Integer> cantBeSquare = new HashSet<Integer>();
          cantBeSquare.addAll(cantBeRow);
          for (Row r2: sud.getRows()) {
            if (!((r2.getNums().get(r.getNums().indexOf(sq))).getInt() == 0)) {
              cantBeSquare.add((r2.getNums().get(r.getNums().indexOf(sq))).getInt());
            }
          }
          for (Square inSQ: sq.getGridSq().getSquares()) {
            if (!inSQ.isEmpty()) {
              cantBeSquare.add(inSQ.getInt());
            }
          }
          Set<Integer> tempSet = new HashSet<Integer>();
          for (Integer i: digits) {
            if (!cantBeSquare.contains(i)) {
              tempSet.add(i);
            }
          }
          sq.setPossibilities(tempSet);
        }
      }
    }
    for (Row r: sud.getRows()) {
      Map<Integer, Integer> rowPoss = new HashMap<Integer, Integer>();
      for (Square s: r.getNums()) {
        for (Integer i: s.getPossibilities()) {
          if (rowPoss.containsKey(i)) {
            rowPoss.put(i, rowPoss.get(i) + 1);
          } else {
            rowPoss.put(i, 1);
          }
        }
      }
      for (Square sq: r.getNums()) {
        if (sq.isEmpty()) {
          if (sq.getPossibilities().size() == 1) {
            sq.setInt(sq.getPossibilities().toArray(new Integer[0])[0]);
          } else {

            Map<Integer, Integer> sqPoss = new HashMap<Integer, Integer>();
            for (Square s: sq.getGridSq().getSquares()) {
              for (Integer i: s.getPossibilities()) {
                if (sqPoss.containsKey(i)) {
                  sqPoss.put(i, sqPoss.get(i) + 1);
                } else {
                  sqPoss.put(i, 1);
                }
              }
            }

            Map<Integer, Integer> colPoss = new HashMap<Integer, Integer>();
            for (Row r2: sud.getRows()) {
              Square tempSQ = r2.getNums().get(r.getNums().indexOf(sq));
              for (Integer i: tempSQ.getPossibilities()) {
                if (colPoss.containsKey(i)) {
                  colPoss.put(i, colPoss.get(i) + 1);
                } else {
                  colPoss.put(i, 1);
                }
              }
            }

            for (Integer i: sq.getPossibilities()) {
              if (rowPoss.get(i) == 1) {
                System.out.println("RowPoss = 1");
                sq.setInt(i);
                break;
              } else if (colPoss.get(i) == 1) {
                System.out.println("ColPoss = 1");
                sq.setInt(i);
                break;
              } else if (sqPoss.get(i) == 1) {
                System.out.println("SqPoss = 1");
                sq.setInt(i);
                break;
              }
            }
            // System.out.println(rowPoss);
            // System.out.println(colPoss);
            // System.out.println(sqPoss);
          }
        }
      }
    }
    for (Row r: sud.getRows()) {
      for (Square s: r.getNums()) {
        if(s.isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }
}
