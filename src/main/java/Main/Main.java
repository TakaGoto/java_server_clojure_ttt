package Main;

import Responders.GameStart;
import Responders.PlayGame;
import Responders.TTTOption;
import com.server.Server;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(4567);
        server.mount("/", new TTTOption());
        server.mount("/game", new GameStart());
        server.mount("/player_move", new PlayGame());
        server.listen();
    }
}
