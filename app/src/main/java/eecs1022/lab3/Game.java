package eecs1022.lab3;

/**
 * Created by user on 2/2/18.
 */
public class Game {
    private String player1, player2;
    private int win1 = 0, win2 = 0, turn = 1;

    //private static final String WIN = "%s\n%s wins";
    
    public Game(String p1, String p2) {
        player1 = p1;
        player2 = p2;
    }

    public Game() {}

    public void setPlayer1(String p1) {
        player1 = p1;
    }

    public void setPlayer2(String p2) {
        player2 = p2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String newTrun(RPSGame p1, RPSGame p2) {
        String ret = String.format("%s plays: %s, %s plays: %s",
                player1, p1, player2, p2);

        turn++;
        switch (run(p1, p2)) {
            case Win:
                win1++;
                return String.format("%s\n%s wins", ret, player1);
            case Lose:
                win2++;
                return String.format("%s\n%s wins", ret, player2);
            default:
                return String.format("%s\nGame Drawn", ret, player2);
        }
    }

    public String newTrun(String p1, String p2) {
        return newTrun(RPSGame.valueOf(p1), RPSGame.valueOf(p2));
    }

    public int getTrun() {
        return turn;
    }

    public GameStatus run(RPSGame player1, RPSGame player2) {
        if (player1 == player2) {
            return GameStatus.Draw;
        }
        if ((player1 == RPSGame.Paper && player2 == RPSGame.Rock) ||
                (player1 == RPSGame.Rock && player2 == RPSGame.Scissors) ||
                (player1 == RPSGame.Scissors && player2 == RPSGame.Paper))
            return GameStatus.Win;

        return GameStatus.Lose;
    }

    @Override
    public String toString() {
        return String.format("%s wins %d(%.2f%%), %s wins %d(%.2f%%)",
                player1, win1, 100.0 * win1/(turn),
                player2, win2, 100.0 * win2/(turn));
    }
}
