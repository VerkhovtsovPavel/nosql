package by.pavel.nosql.util;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import org.springframework.stereotype.Component;

@Component
public class NotationUtils {

    private NotationUtils() {}

    public String transformSanToFen(String san) {
        final Board board = new Board();
        final MoveList moveList = new MoveList();
        moveList.loadFromSan(san);
        for (Move move : moveList) {
            board.doMove(move);
        }
        return board.getFen(true, false);
    }
}
