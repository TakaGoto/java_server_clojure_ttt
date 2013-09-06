package Parser;

import clojure.lang.Keyword;
import clojure.lang.PersistentVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClojureParserTest {
    @Test public void testParseKeyBoard() {
        Hashtable settings = new Hashtable();
        settings.put("playerOne", "world");
        settings.put("playerTwo", "world");
        settings.put("board", "_________");
        settings.put("boardSize", "world");
        Hashtable newSettings = ClojureParser.parseToKeyword(settings);
        assertEquals("world", newSettings.get(Keyword.find("p-one")));
    }

    @Test public void parseArrayBoard() {
        List<String> list = createEmptyBoard();
        PersistentVector board = PersistentVector.create(list);
        String result = ClojureParser.parseArrayBoard(board);
        assertEquals("_________", result);
    }

    @Test public void parseBoard() {
        String board = "_________";
        List<String> expected = createEmptyBoard();
        List<String> result = ClojureParser.parseBoard(board);
        assertEquals(expected, result);
    }

    public List<String> createEmptyBoard() {
        List<String> expected = new ArrayList<String>();
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        expected.add("_");
        return expected;
    }
}
