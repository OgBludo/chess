public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int direction = color.equals("White") ? 1 : -1;

        if (column == toColumn) {
            if (line + direction == toLine) {
                return chessBoard.board[toLine][toColumn] == null;
            }
            if (line == (color.equals("White") ? 1 : 6) && line + 2 * direction == toLine) {
                return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + direction][column] == null;
            }
        } else if (Math.abs(column - toColumn) == 1 && line + direction == toLine) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece != null && !targetPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
