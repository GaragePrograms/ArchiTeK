package io.github.garageprograms.architek.plugins;

public interface Plugin {
	public String getName();
	public String getUID();
	public abstract void install();
}
