public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        if (deltaX == deltaY) {
            int stepX = toColumn > column ? 1 : -1;
            int stepY = toLine > line ? 1 : -1;

            int currentX = column + stepX;
            int currentY = line + stepY;

            while (currentX != toColumn && currentY != toLine) {
                if (chessBoard.board[currentY][currentX] != null) {
                    return false;
                }
                currentX += stepX;
                currentY += stepY;
            }

            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
