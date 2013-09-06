package Parser;

import Ui.JavaTTTUi;
import clojure.lang.Keyword;
import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;
import com.tictactoe.Board.Board;

import java.io.IOException;
import java.util.Hashtable;

public class ClojureParser {
    public static Hashtable<Keyword, String> parseToKeyword(Hashtable settings) {
        settings.put(Keyword.intern("p-one"), settings.get("playerOne"));
        settings.put(Keyword.intern("p-two"), settings.get("playerTwo"));
        settings.put(Keyword.intern("board"), settings.get("board"));
        settings.put(Keyword.intern("board-size"), settings.get("boardSize"));
        return settings;
    }

    public static Board playGame(JavaTTTUi ui, Hashtable settings, String move) throws IOException, ClassNotFoundException {
        Board board = new Board(Integer.parseInt((String) settings.get("board-size")));
        RT.load("clojure.core");
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("ttt.game"));
        Var play = RT.var("ttt.game", "play");
        String returnBoard = (String) play.invoke(ui, parseToKeyword(settings), move);
        board.setSlots(returnBoard);
        return board;
    }
}
