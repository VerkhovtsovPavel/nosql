package by.pavel.nosql.transform;

import by.pavel.nosql.model.BestMoveItem;
import by.pavel.nosql.util.NotationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bhlangonijr.chesslib.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

@Component
public class ChessTransformer {

    @Autowired
    NotationUtils notationUtils;

    public List<BestMoveItem> jsonToBestMoves(String json) {
        ArrayList<BestMoveItem> bestMoveList = new ArrayList<>();
        Set<String> fenList = new HashSet<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map map = mapper.readValue(json, Map.class);
            parseMove(map, "", (bestMoveItem -> {
                if (!fenList.contains(bestMoveItem.getFen())) {
                    fenList.add(bestMoveItem.getFen());
                    bestMoveList.add(bestMoveItem);
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bestMoveList;
    }

    private void parseMove(Map moves, String movesHistory, Consumer<BestMoveItem> collector) {
        for (Map move : (ArrayList<Map>) moves.get("s")) {
            ArrayList<Map> nextLevelMoves = ((ArrayList<Map>) move.get("s"));
            if (!nextLevelMoves.isEmpty()) {
                parseMove(move, movesHistory + " " + move.get("m"), collector);
            } else {
                String fenNotation = notationUtils.transformSanToFen(movesHistory.trim());
                String bestMove = (String) ((ArrayList<Map>) moves.get("s")).get(0).get("m");
                double score = Double.parseDouble(""+((Map) moves.get("e")).get("v"));
                int depth = (int) ((Map) moves.get("e")).get("d");
                int sp = (int) (score * 100);
                BestMoveItem bestMoveItem = new BestMoveItem(fenNotation, bestMove, score, depth, sp);
                collector.accept(bestMoveItem);
                break;
            }
        }
    }
}
