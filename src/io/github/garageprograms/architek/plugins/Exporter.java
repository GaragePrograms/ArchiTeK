package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.UserProject;

public interface Exporter {
	public void exportToDir(String dir, UserProject proj);
}
