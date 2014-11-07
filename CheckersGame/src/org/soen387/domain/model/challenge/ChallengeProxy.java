package org.soen387.domain.model.challenge;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.mapper.ChallengeMapper;
import org.soen387.domain.model.player.IPlayer;

public class ChallengeProxy implements IChallenge{

	private long id;
	private IChallenge c;
	
	public ChallengeProxy(long id){
		this.id=id;
	}
	
	private IChallenge getInnerObject(){
		if(c == null)
		{
			try {
				c = ChallengeMapper.findById(id);
			} catch (MapperException e) {
			}
		}
		return c;
	}

	public IChallenge getChallenge()
	{
		return getInnerObject();
	}
	
	
	@Override
	public IPlayer getChallenger() {
		return getInnerObject().getChallenger();
	}

	@Override
	public void setChallenger(IPlayer challenger) {
		getInnerObject().setChallenger(challenger);
	}

	@Override
	public IPlayer getChallengee() {
		return getInnerObject().getChallengee();
	}

	@Override
	public void setChallengee(IPlayer challengee) {
		getInnerObject().setChallengee(challengee);
	}

	@Override
	public ChallengeStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(ChallengeStatus status) {
		getInnerObject().setStatus(status);
	}

	@Override
	public long getId() {
		return getInnerObject().getId();
	}

	@Override
	public void setId(int id) {
		getInnerObject().setId(id);
	}

	@Override
	public int getVersion() {
		return getInnerObject().getVersion();
	}

	@Override
	public void setVersion(int version) {
		getInnerObject().setVersion(version);
	}


}
