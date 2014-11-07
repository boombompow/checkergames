package org.soen387.domain.model.challenge;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.mapper.ChallengeMapper;
import org.soen387.domain.checkerboard.mapper.CheckerBoardDataMapper;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.player.IPlayer;

public class Challenge implements IChallenge {
	private IPlayer challenger = null;
	private IPlayer challengee = null;
	private ChallengeStatus status = null;
	private long id = 0;
	private int version = 0;
	
	public Challenge(long id, int version, ChallengeStatus status,
			IPlayer challenger, IPlayer challengee)
	{
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
		this.id = id;
		this.version = version;
	}
	
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#getChallenger()
	 */
	@Override
	public IPlayer getChallenger() {
		return challenger;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#setChallenger(org.soen387.domain.model.player.IPlayer)
	 */
	@Override
	public void setChallenger(IPlayer challenger) {
		this.challenger = challenger;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#getChallengee()
	 */
	@Override
	public IPlayer getChallengee() {
		return challengee;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#setChallengee(org.soen387.domain.model.player.IPlayer)
	 */
	@Override
	public void setChallengee(IPlayer challengee) {
		this.challengee = challengee;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#getStatus()
	 */
	@Override
	public ChallengeStatus getStatus() {
			return status;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#setStatus(org.soen387.domain.model.challenge.ChallengeStatus)
	 */
	@Override
	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#getId()
	 */
	@Override
	public long getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#getVersion()
	 */
	@Override
	public int getVersion() {
		return version;
	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.challenge.IChallenge#setVersion(int)
	 */
	@Override
	public void setVersion(int version) {
		this.version = version;
	}
	
}
