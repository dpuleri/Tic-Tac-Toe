import java.util.LinkedList;
import java.util.Scanner;
public class PlayTicTacToe {
    public static void main(String[] args) {
    //human is O and Computer is X. always.
        LinkedList<int[]> moves = new LinkedList<int[]>();
        TicBoard board = new TicBoard();
        ComputerPlayer player = new ComputerPlayer(board);
        Scanner sc = new Scanner(System.in);
        while (!board.checkWin() && (moves.size() < 9)) {
            System.out.println(board);
            int humanMove = Integer.parseInt(sc.nextLine());
            board.setTile("O", humanMove);
            moves.addLast(new int[]{humanMove / 3, humanMove % 3});
            player.updateSet(moves.getLast());
            if (!board.checkWin() && (moves.size() < 9)) {
                int[] computerMove = player.getNextMove();
                board.setTile("X", computerMove);
                moves.addLast(computerMove);
                player.updateSet(moves.getLast());
            }
        }
        System.out.println(board);
    }
}