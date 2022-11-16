package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

/**
 * An example Rock Paper Scissors game plug-in.
 */
public class TicTacToePlugin implements GamePlugin<TicTacToe.Player> {

    private static final String GAME_NAME = "Tic Tac Toe";

    /** The standard size of a tic-tac-toe board. */
    public static final int SIZE = 3;

    private static final String GAME_TIED_MSG = "The game ended in a tie.";

    private static final String GAME_START_FOOTER = "You are playing Tic Tac Toe with a computer!";

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return SIZE;
    }

    @Override
    public int getGridHeight() {
        return SIZE;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        framework.setFooterText(GAME_START_FOOTER);
        game = new TicTacToe();
    }

    @Override
    public void onNewMove() { } // Nothing to do here.

    @Override
    public boolean isMoveValid(int x, int y) {
        return true; // Impossible to make an invalid move.
    }

    @Override
    public boolean isMoveOver() {
        return game.isValidPlay(SIZE, SIZE);
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x, y, game.currentPlayer().toString());
        game.play(x, y);;
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        TicTacToe.Player possibleWinner = game.winner();
        if (possibleWinner == null) return GAME_TIED_MSG;
        return String.format("%s won!", possibleWinner);
    }

    @Override
    public void onGameClosed() { } // Nothing to do here.

    @Override
    public TicTacToe.Player currentPlayer() {
        return game.currentPlayer();
    }
}
