package by.pavel.nosql.controller;

import by.pavel.nosql.model.ChessBaseItem;
import by.pavel.nosql.repository.BestMoveItemRepository;
import by.pavel.nosql.repository.ChessBaseRepository;
import by.pavel.nosql.transform.ChessTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ChessBaseController {

    @Autowired
    ChessBaseRepository chessBaseRepository;

    @Autowired
    BestMoveItemRepository bestMoveItemRepository;

    @Autowired
    ChessTransformer chessTransformer;

    @GetMapping("/api/base")
    public String getBase(HttpServletRequest request) {
        return chessBaseRepository.findAll().get(0).getBase();
    }

    @PostMapping("/api/base")
    public void updateBase(@RequestBody String json) {
        ChessBaseItem chessBase = new ChessBaseItem(json);
        chessBaseRepository.save(chessBase);
        bestMoveItemRepository.deleteAll();
        bestMoveItemRepository.saveAll(chessTransformer.jsonToBestMoves(json));
    }
}