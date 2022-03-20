package by.pavel.nosql.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("best_move")
public class BestMoveItem {

	@Id
	@JsonInclude(Include.NON_NULL)
	private String id;

	private String fen;
	private String bestMove;

	private double score;
	private int depth;
	private int sp;

	public BestMoveItem() {}

	public BestMoveItem(String fen, String bestMove, double score, int depth, int sp) {
		this.fen = fen;
		this.bestMove = bestMove;
		this.score = score;
		this.depth = depth;
		this.sp = sp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}

	public String getBestMove() {
		return bestMove;
	}

	public void setBestMove(String bestMove) {
		this.bestMove = bestMove;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
}
