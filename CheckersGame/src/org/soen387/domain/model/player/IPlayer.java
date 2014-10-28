package org.soen387.domain.model.player;

public interface IPlayer {

	public abstract long getId();

	public abstract String getFirstN();

	public abstract String getLastN();

	public abstract String getEmail();

	public abstract int getVersion();

	public abstract void setFirstN(String f);

	public abstract void setLastN(String l);

	public abstract void setEmail(String e);

	public abstract void setVersion(int v);

}