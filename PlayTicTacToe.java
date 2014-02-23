import java.util.LinkedList;
import java.util.Scanner;
public class PlayTicTacToe {
    public static void demo() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(" " + Integer.toString(i * 3 + j + 1) + " ");
                if (j < 3 - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < 3 - 1) {
                    sb.append("---|---|---\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
    //human is O and Computer is X. always.
        demo();
        System.out.println("The arrangement of the board appears above");
        System.out.println("Enter your move by entering the number of the tile");
        System.out.println("You are O");
        LinkedList<int[]> moves = new LinkedList<int[]>();
        TicBoard board = new TicBoard();
        ComputerPlayer player = new ComputerPlayer(board);
        Scanner sc = new Scanner(System.in);
        while (!board.checkWin() && (moves.size() < 9)) {
            System.out.println(board);
            System.out.print(">> ");
            int humanMove = Integer.parseInt(sc.nextLine()) - 1;
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
        String winner = board.getWinner();
        if (winner.equals(" ")) {
            winner = "Cat";
        }
        System.out.printf("%s has won.%n", winner);
    }
}