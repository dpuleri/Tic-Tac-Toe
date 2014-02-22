public class TicBoard {
    private byte[][] board;
    private byte winner = 0;
    public TicBoard() {
        board = new byte[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setTile(" ", i, j);
            }
        }
    }
    public void setTile(String input, int i, int j) {
        input.toUpperCase();
        byte setting = 0;
        if (input.equals(" ")) {
            setting = 0;
        } else if (input.equals("X")) {
            setting = 1;
        } else if (input.equals("O")) {
            setting = -1;
        }
        board[i][j] = setting;
    }
    public void setTile(String input, int in) {
        int i = in / 3;
        int j = in % 3;
        setTile(input, i, j);
    }
    public void setTile(String input, int[] in) {
        setTile(input, in[0], in[1]);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String currentStr = getCurrentStr(i, j);
                sb.append(" " + currentStr + " ");
                if (j < board[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < board.length - 1) {
                    sb.append("---|---|---\n");
            }
        }
        return sb.toString();
    }
    private String getCurrentStr(int i, int j) {
        String out = " ";
        byte in = board[i][j];
        if (in == 0) {
            out = " ";
        } else if (in == 1) {
            out = "X";
        } else if (in == -1) {
            out = "O";
        }
        return out;
    }
    protected byte[][] getBoard() {
        return board;
    }
    public boolean checkWin() {
        for (int i = 0; i < board.length; i++) {
            int rowSum = board[i][0] + board[i][1] + board[i][2];
            int colSum = board[0][i] + board[1][i] + board[2][i];
            if (rowSum == 3 || colSum == 3) {
                winner = 1;
                return true;
            } else if (rowSum == -3 || colSum == -3) {
                winner = -1;
                return true;
            }
        }
        if (((board[0][0] + board[1][1] + board[2][2]) == 3) ||
            ((board[0][2] + board[1][1] + board[2][0]) == 3)) {
            winner = 1;
            return true;
        } else if (((board[0][0] + board[1][1] + board[2][2]) == -3) ||
            ((board[0][2] + board[1][1] + board[2][0]) == -3)) {
            winner = -1;
            return true;
        }
        return false;
    }
    public String getWinner() {
        String out = " ";
        if (winner == 1) {
            out = "X";
        } else if (winner == -1) {
            out = "O";
        }
        return out;
    }
    public static void main(String[] args) {
        //testing purposes
        TicBoard test = new TicBoard();
        test.setTile("X",0,0);
        test.setTile("X",1,1);
        test.setTile("X",2,2);
        System.out.println(test);
    }
}