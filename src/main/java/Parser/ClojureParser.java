package Parser;

import Ui.JavaTTTUi;
import clojure.lang.*;
import com.tictactoe.Board.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ClojureParser {

    public static Hashtable<Keyword, String> parseToKeyword(Hashtable settings) {
        Hashtable newSettings = new Hashtable();
        newSettings.put(Keyword.intern("p-one"), settings.get("playerOne"));
        newSettings.put(Keyword.intern("p-two"), settings.get("playerTwo"));
        newSettings.put(Keyword.intern("board"), parseBoard((String) settings.get("board")));
        newSettings.put(Keyword.intern("board-size"), settings.get("boardSize"));
        return newSettings;
    }

    public static List<String> parseBoard(String board) {
        List<String> newBoard = new ArrayList<String>();
        for(char c: board.toCharArray()) {
            newBoard.add(String.valueOf(c));
        }
//        newBoard.add(String.valueOf(board.charAt(0)));
//        newBoard.add(String.valueOf(board.charAt(1)));
//        newBoard.add(String.valueOf(board.charAt(2)));
//        newBoard.add(String.valueOf(board.charAt(3)));
//        newBoard.add(String.valueOf(board.charAt(4)));
//        newBoard.add(String.valueOf(board.charAt(5)));
//        newBoard.add(String.valueOf(board.charAt(6)));
//        newBoard.add(String.valueOf(board.charAt(7)));
//        newBoard.add(String.valueOf(board.charAt(8)));
        return newBoard;
    }

    public static String parseArrayBoard(PersistentVector board) {
        StringBuilder builder = new StringBuilder();
        for (Object s: board) {
            builder.append(String.valueOf(s));
            builder.append("");
        }
        return builder.toString();
    }

    public static Board playGame(JavaTTTUi ui, Hashtable settings, String move) {
        Board board = new Board(Integer.parseInt((String) settings.get("boardSize")));
        setUpClojureEnv();
        Var play = RT.var("ttt.game", "play");
        PersistentVector returnBoard = (PersistentVector) play.invoke(ui, parseToKeyword(settings), move);
        board.setSlots(parseArrayBoard(returnBoard));
        return board;
    }

    public static Board playGame(JavaTTTUi ui, Hashtable settings) {
        Board board = new Board(Integer.parseInt((String) settings.get("boardSize")));
        setUpClojureEnv();
        Var play = RT.var("ttt.game", "play");
        PersistentVector returnBoard = (PersistentVector) play.invoke(ui, parseToKeyword(settings));
        board.setSlots(parseArrayBoard(returnBoard));
        return board;
    }

    public static void loadClojure() {
        try {
            RT.load("clojure.core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void requireClojureTTT() {
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("ttt.game"));
    }

    public static void setUpClojureEnv() {
        loadClojure();
        requireClojureTTT();
    }
}
