import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Random;
public class ComputerPlayer {
    private HashSet<Integer> set;
    private TicBoard ticBoard;
    private byte[][] board;
    private static final int[][] CORNERS = new int[][]{{0, 0}, {2, 2},
        {0, 2}, {2, 0}};
    public ComputerPlayer(TicBoard ticBoard) {
        this.ticBoard = ticBoard;
        this.board = ticBoard.getBoard();
        initSet();
    }
    public int[] getNextMove() {
        int[] find2 = findRowWith2();
        if (find2[2] != 0) {
            return new int[]{find2[0], find2[1]};
        }
        //maybe add stuff for forks
        int[] findCenter = findCenter();
        if (findCenter[2] != 0) {
            return new int[]{findCenter[0], findCenter[1]};
        }
        int[] findCorner = findCorner();
        if (findCorner[2] != 0) {
            return new int[]{findCorner[0], findCorner[1]};
        }
        return getSide();
    }
    public void updateSet(int[] in) {
        set.remove((in[0] * 3) + in[1]);
    }
    private int[] findRowWith2() {
    //fixed, possibly could be optimized
        int[] out = new int[]{0, 0, 0};
        for (int i = 0; i < board.length; i++) {
            int rowSum = board[i][0] + board[i][1] + board[i][2];
            int colSum = board[0][i] + board[1][i] + board[2][i];
            if (rowSum == 2) {
                for (int j = 0; j < board.length; j++) {
                    if (checkTile(i, j)) {
                        out = new int[]{i, j, 1};
                    }
                }
                return out;
            } else if (colSum == 2) {
                for (int j = 0; j < board.length; j++) {
                    if (checkTile(j, i)) {
                        out = new int[]{j, i, 1};
                    }
                }
                return out;
            } else if (rowSum == -2) {
                for (int j = 0; j < board.length; j++) {
                    if (checkTile(i, j)) {
                        out = new int[]{i, j, -1};
                    }
                }
            } else if (colSum == -2) {
                for (int j = 0; j < board.length; j++) {
                    if (checkTile(j, i)) {
                        out = new int[]{j, i, -1};
                    }
                }
            }
        }
        return out;
    }
    private int[] findCenter() {
        //System.out.println("im running"); debugging
        //System.out.println(checkTile(1, 1));
        if (checkTile(1, 1)) {
            return new int[]{1, 1, 1};
        } else {
            return new int[]{-1, -1, 0};
        }
    }
    private int[] findCorner() {
        // first check if opponent is in corner then play opposite if possible
        for (int i = 0; i < CORNERS.length; i++) {
            if (!checkTile(CORNERS[i])) {
                if ((board[CORNERS[i][0]][CORNERS[i][1]] == -1)
                    && (checkOppositeCorner(CORNERS[i]))){
                    return findOppositeCorner(CORNERS[i]);
                }
            }
        }
        for (int i = 0; i < CORNERS.length; i++) {
            if (checkTile(CORNERS[i])) {
                return new int[]{CORNERS[i][0], CORNERS[i][1], 1};
            }
        }
        return new int[]{-1, -1, 0};
    }
    private boolean checkOppositeCorner(int ... cornerIn) {
        int[] opposite = findOppositeCorner(cornerIn[0], cornerIn[1]);
        return checkTile(opposite[0], opposite[1]);
    }
    private int[] findOppositeCorner(int ... cornerIn) {
        int i, j;
        if (cornerIn[0] == 0) {
            i = 2;
        } else {
            i = 0;
        }
        if (cornerIn[1] == 0) {
            j = 2;
        } else {
            j = 0;
        }
        return new int[]{i, j, 1};
    }
    private int[] getSide() {
        Random rand = new Random();
        //get random move
        ArrayList<Integer> tempList = new ArrayList<Integer>(set);
        int next = tempList.get(rand.nextInt(tempList.size()));
        int i = next / 3;
        int j = next % 3;
        int[] out = new int[]{i, j};
        return out;
    }
    private boolean checkTile(int ... in) {
    //true if it is open
        return set.contains((in[0] * 3) + in[1]);
    }
    private void initSet() {
        set = new HashSet<Integer>();
        for (int i = 0; i < board.length * board[0].length; i++) {
            set.add(i);
        }
    }
}