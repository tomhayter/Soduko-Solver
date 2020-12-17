package sudoku;

import java.util.List;
import java.util.ArrayList;

// TBTS = Three by Three Square

public class TBTS {
  private List<Square> squares = new ArrayList<Square>();

  public TBTS(List<Square> squares) {
    this.squares = squares;
  }

  public List<Square> getSquares() {
    return this.squares;
  }

  public void addSquare(Square s) {
    this.squares.add(s);
  }
}
