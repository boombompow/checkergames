package org.soen387.domain.model.checkerboard;

import java.awt.Point;

import org.soen387.domain.model.player.IPlayer;

public class CheckerBoard implements ICheckerBoard {

	public CheckerBoard(long id, int version, GameStatus status,
			char[][] pieces, IPlayer firstPlayer, IPlayer secondPlayer,
			IPlayer currentPlayer) {
		super();
		this.id = id;
		this.version = version;
		this.status = status;
		this.pieces = pieces;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.currentPlayer = currentPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getVersion()
	 */
	@Override
	public int getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setVersion(int)
	 */
	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getStatus()
	 */
	@Override
	public GameStatus getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setStatus(org.soen387.domain.model.checkerboard.GameStatus)
	 */
	@Override
	public void setStatus(GameStatus status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getPieces()
	 */
	@Override
	public char[][] getPieces() {
		return pieces;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setPieces(char[][])
	 */
	@Override
	public void setPieces(char[][] pieces) {
		this.pieces = pieces;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getFirstPlayer()
	 */
	@Override
	public IPlayer getFirstPlayer() {
		return firstPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setFirstPlayer(org.soen387.domain.model.player.IPlayer)
	 */
	@Override
	public void setFirstPlayer(IPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getSecondPlayer()
	 */
	@Override
	public IPlayer getSecondPlayer() {
		return secondPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setSecondPlayer(org.soen387.domain.model.player.IPlayer)
	 */
	@Override
	public void setSecondPlayer(IPlayer secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getCurrentPlayer()
	 */
	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#setCurrentPlayer(org.soen387.domain.model.player.IPlayer)
	 */
	@Override
	public void setCurrentPlayer(IPlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	long id;
	int version;
	GameStatus status;
	char[][] pieces;
	IPlayer firstPlayer;
	IPlayer secondPlayer;
	IPlayer currentPlayer;


	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#move(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void move(Point source, Point target) {
		
	}
	
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.checkerboard.ICheckerBoard#jump(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void jump(Point source, Point... targets) {
		
	}
}
