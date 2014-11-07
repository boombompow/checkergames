package org.soen387.domain.model.checkerboard;

import java.awt.Point;
import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.checkerboard.mapper.CheckerBoardDataMapper;
import org.soen387.domain.model.player.IPlayer;

public class CheckerBoardProxy implements ICheckerBoard{

	private long id;
	private ICheckerBoard c;
	
	public CheckerBoardProxy(long id){
		this.id=id;
	}
	
	private ICheckerBoard getInnerObject(){
		if(c == null)
		{
			try {
				c = CheckerBoardDataMapper.findById(id);
			} catch (MapperException e) {
			}
			catch (SQLException e)
			{
			}
		}
		return c;
	}

	public ICheckerBoard getChallenge()
	{
		return getInnerObject();
	}
	
	@Override
	public int getVersion() {
		return getInnerObject().getVersion();
	}

	@Override
	public void setVersion(int version) {
		getInnerObject().setVersion(version);
	}

	@Override
	public GameStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(GameStatus status) {
		getInnerObject().setStatus(status);
	}

	@Override
	public char[][] getPieces() {
		return getInnerObject().getPieces();
	}

	@Override
	public void setPieces(char[][] pieces) {
		getInnerObject().setPieces(pieces);
	}

	@Override
	public IPlayer getFirstPlayer() {
		return getInnerObject().getFirstPlayer();
	}

	@Override
	public void setFirstPlayer(IPlayer firstPlayer) {
		getInnerObject().setFirstPlayer(firstPlayer);
	}

	@Override
	public IPlayer getSecondPlayer() {
		return getInnerObject().getSecondPlayer();
	}

	@Override
	public void setSecondPlayer(IPlayer secondPlayer) {
		getInnerObject().setSecondPlayer(secondPlayer);
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return getInnerObject().getCurrentPlayer();
	}

	@Override
	public void setCurrentPlayer(IPlayer currentPlayer) {
		getInnerObject().setCurrentPlayer(currentPlayer);
	}

	@Override
	public long getId() {
		return getInnerObject().getId();
	}

	@Override
	public void move(Point source, Point target) {
		getInnerObject().move(source, target);
	}

	@Override
	public void jump(Point source, Point... targets) {
		getInnerObject().jump(source, targets);
	}

}
