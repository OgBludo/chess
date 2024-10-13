public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            if (chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
                return targetPiece == null || !targetPiece.getColor().equals(this.color);
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
