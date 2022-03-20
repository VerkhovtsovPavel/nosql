package by.pavel.nosql.controller;

import by.pavel.nosql.model.BestMoveItem;
import by.pavel.nosql.model.BestMoveShortItem;
import by.pavel.nosql.repository.BestMoveItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BestMoveController {

    @Autowired
    BestMoveItemRepository bestMoveItemRepository;

    @GetMapping("/api/fenbase")
    public Map<String, BestMoveShortItem> getFenBase(HttpServletRequest request) {
        Map<String, BestMoveShortItem> response = new HashMap<>();
        bestMoveItemRepository.findAll().stream().forEach(x -> {
            BestMoveShortItem bestMove = new BestMoveShortItem(x.getBestMove(), x.getScore(), x.getDepth());
            response.put(x.getFen(), bestMove);
        });
        return response;
    }

    @GetMapping("/api/fendata")
    public BestMoveItem getBestMove(@RequestParam String fen) {
        BestMoveItem move = bestMoveItemRepository.findItemByFen(fen);
        move.setId(null);
        return move;
    }
}