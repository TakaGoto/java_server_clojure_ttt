package Main;

import Responders.GameStart;
import Responders.PlayGame;
import Responders.TTTOption;
import clojure.lang.Keyword;
import clojure.lang.Symbol;
import clojure.lang.Var;
import com.server.Server;
import clojure.lang.RT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        try {
//         RT.load("clojure.core");
//         Var require = RT.var("clojure.core", "require");
//         require.invoke(Symbol.create("ttt.ui.console-ui"));
//         Var welcome = RT.var("ttt.ui.console-ui", "display-welcome-message");
//         welcome.invoke();
//     } catch (IOException e) {
//         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//     } catch (ClassNotFoundException e) {
//         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//     }

//        Hashtable<Keyword, String> test = new Hashtable<Keyword, String>();
//        test.put(Keyword.intern("p-one"), "h");
//        test.put(Keyword.intern("p-two"), "c");
//        test.put(Keyword.intern("board"), "test");
//        test.put(Keyword.intern("board-size"), "3");
//
//        RT.load("clojure.core");
//        Var require = RT.var("clojure.core", "require");
//        require.invoke(Symbol.create("ttt.game"));
//        Var board = RT.var("ttt.game", "play");
//        String newBoard = (String) board.invoke("blah", test, "world");
//        System.out.println(newBoard);



        Server server = new Server(4567);
        server.mount("/", new TTTOption());
        server.mount("/game", new GameStart());
        server.mount("/player_move", new PlayGame());
        server.listen();
    }
}
