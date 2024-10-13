public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine || column == toColumn) {
            int start = Math.min(line, toLine);
            int end = Math.max(line, toLine);
            int startCol = Math.min(column, toColumn);
            int endCol = Math.max(column, toColumn);

            for (int i = start + 1; i < end; i++) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }

            for (int i = startCol + 1; i < endCol; i++) {
                if (chessBoard.board[line][i] != null) {
                    return false;
                }
            }

            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
