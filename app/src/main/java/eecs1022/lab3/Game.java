package eecs1022.lab3;

/**
 * Created by mai1015 on 2/2/18.
 */
public class Game {
    private String player1, player2;
    private int round, win1 = 0, win2 = 0, turn = 0;

    //private static final String WIN = "%s\n%s wins";
    
    public Game(String p1, String p2, int r) {
        player1 = p1.isEmpty() ? "Player1" : p1;
        player2 = p2.isEmpty() ? "Player2" : p2;
        round = r;
    }

    public Game(String p1, String p2) {
        player1 = p1.isEmpty() ? "Player1" : p1;
        player2 = p2.isEmpty() ? "Player2" : p2;
        round = 3;
    }

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
        if (isOver()) return "Error: Game is already over!";
        // increase turn
        turn++;
        // format the information
        String ret = String.format("Round %d finished, %s plays: %s, \n%s plays: %s",
                turn, player1, p1, player2, p2);

        GameStatus status = run(p1, p2);
        switch (status) {
            case Win:
                win1++;
                return getResult(ret);//isOver() ? getResult(ret) : String.format("%s\n%s wins", ret, player1);
            case Lose:
                win2++;
                return getResult(ret);//isOver() ? getResult(ret) : String.format("%s\n%s wins", ret, player2);
            default:
                return getResult(ret);//isOver() ? getResult(ret) : String.format("%s\nGame Drawn", ret);
        }
    }

    public String getResult(String pre) {
        String ret = null;
        if (win1 > win2) {
            ret = String.format("%s\nWinner is %s.", pre, player1) ;
        } else if (win1 < win2) {
            ret = String.format("%s\nWinner is %s.", pre, player2) ;
        } else {
            ret = String.format("%s\nGame drawen.", pre) ;
        }
        return isOver() ? String.format("%s\n%s", ret, "Game is over") : ret;
    }

    public boolean isOver() {
        return round <= turn;
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
        return String.format("%sTotal round %d, \n%s wins %d(%.2f%%), \n%s wins %d(%.2f%%), \n Winner is %s",
                isOver() ? "Game is over,\n" : "",
                turn,
                player1, win1, 100.0 * win1/(turn),
                player2, win2, 100.0 * win2/(turn),
                win1>win2? player1 : win1 == win2 ? "Both player" : player2);
    }
}
