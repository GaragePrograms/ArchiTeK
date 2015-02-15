package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.Property;

public class PluginManager {
   private static PluginManager instance = null;
   private PluginManager() {}
   public static PluginManager getInstance() {
      if(instance == null) {
         instance = new PluginManager();
      }
      return instance;
   }
	   
	public ProgrammingLanguage language;
	
	public Property getProperty(String name){
		for (Property p : this.language.properties){
			if (p.name==name){
				return p;
			}
		}
		return null;
	}
}