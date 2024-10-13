public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn];
                board[startLine][startColumn] = null;

                // Обновление переменной check для королей и ладей
                if (board[endLine][endColumn] instanceof King || board[endLine][endColumn] instanceof Rook) {
                    ((King) board[endLine][endColumn]).check = false;
                }

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        int kingLine = nowPlayer.equals("White") ? 0 : 7;
        int kingColumn = 4;
        int rookColumn = 0;

        King king = (King) board[kingLine][kingColumn];
        Rook rook = (Rook) board[kingLine][rookColumn];

        if (king != null && rook != null && king.check && rook.check) {
            if (board[kingLine][kingColumn - 1] == null && board[kingLine][kingColumn - 2] == null && board[kingLine][kingColumn - 3] == null) {
                if (!king.isUnderAttack(this, kingLine, kingColumn) && !king.isUnderAttack(this, kingLine, kingColumn - 1) && !king.isUnderAttack(this, kingLine, kingColumn - 2)) {
                    board[kingLine][kingColumn - 2] = king;
                    board[kingLine][kingColumn - 1] = rook;
                    board[kingLine][kingColumn] = null;
                    board[kingLine][rookColumn] = null;
                    king.check = false;
                    rook.check = false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean castling7() {
        int kingLine = nowPlayer.equals("White") ? 0 : 7;
        int kingColumn = 4;
        int rookColumn = 7;

        King king = (King) board[kingLine][kingColumn];
        Rook rook = (Rook) board[kingLine][rookColumn];

        if (king != null && rook != null && king.check && rook.check) {
            if (board[kingLine][kingColumn + 1] == null && board[kingLine][kingColumn + 2] == null) {
                if (!king.isUnderAttack(this, kingLine, kingColumn) && !king.isUnderAttack(this, kingLine, kingColumn + 1) && !king.isUnderAttack(this, kingLine, kingColumn + 2)) {
                    board[kingLine][kingColumn + 2] = king;
                    board[kingLine][kingColumn + 1] = rook;
                    board[kingLine][kingColumn] = null;
                    board[kingLine][rookColumn] = null;
                    king.check = false;
                    rook.check = false;
                    return true;
                }
            }
        }
        return false;
    }
}
