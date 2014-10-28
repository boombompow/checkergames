package org.soen387.domain.model.user;

public interface IUser {

	public abstract long getId();

	public abstract String getUserN();

	public abstract String getPassW();

	public abstract void setUserN(String u);

	public abstract void setPassW(String p);

}