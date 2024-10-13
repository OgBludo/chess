public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        if (line == toLine || column == toColumn) {
            return new Rook(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        } else if (deltaX == deltaY) {
            return new Bishop(color).canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
