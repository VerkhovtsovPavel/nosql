package by.pavel.nosql.repository;

import java.util.List;

import by.pavel.nosql.model.BestMoveItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BestMoveItemRepository extends MongoRepository<BestMoveItem, String> {

	BestMoveItem findItemByFen(String fen);

	List<BestMoveItem> findAll();
}
