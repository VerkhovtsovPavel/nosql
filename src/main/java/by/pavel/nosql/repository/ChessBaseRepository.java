package by.pavel.nosql.repository;

import by.pavel.nosql.model.ChessBaseItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessBaseRepository extends MongoRepository<ChessBaseItem, String> {

}
