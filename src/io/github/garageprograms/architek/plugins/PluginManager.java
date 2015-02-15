package io.github.garageprograms.architek.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

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
	public ArrayList<ProgrammingLanguage> languages = new ArrayList<ProgrammingLanguage>();
	
	public Property getProperty(String name){
		for (Property p : this.language.properties){
			if (p.name==name){
				return p;
			}
		}
		return null;
	}
	
	public ProgrammingLanguage getLanguage(String uniqueID){
		for (ProgrammingLanguage l : this.languages){
			if (l.uniqueID.equals(uniqueID)){
				return l;
			}
		}
		return null;
	}
	
	public void installLanguage(String uniqueID){
		this.language=this.getLanguage(uniqueID);
	}
	
	public void loadAllPlugins(String libDir){
		File folder = new File(libDir);
		String[] listOfFiles = folder.list();
		
		 for (int i = 0; i < listOfFiles.length; i++) {
			   String file = listOfFiles[i];
		       if (file.endsWith(".jar"))
		       {
		    	   this.loadPlugin(libDir, file);
		       }
		 }
	}
	private void loadPlugin(String libDir, String file) {
		System.out.println("Loading Plugin: "+file);
		try {
			URL[] urlList = new URL[1];
			urlList[0]=new File(libDir+"/"+file).toURI().toURL();
			URLClassLoader child = new URLClassLoader (urlList, this.getClass().getClassLoader());
			Class<?> classToLoad = Class.forName ("architek.extend.PackedPlugin", true, child);
			Object pluginObj = classToLoad.newInstance();
			//System.out.println(pluginObj.getClass());
			if (pluginObj.getClass().getGenericSuperclass() == Plugin.class){
				System.out.println("-->Name: "+(String)pluginObj.getClass().getField("name").get(pluginObj));
				System.out.println("-->Desc: "+(String)pluginObj.getClass().getField("description").get(pluginObj));
				Method method = classToLoad.getDeclaredMethod ("install");
				method.invoke(pluginObj);
			}else{
				System.out.println("architek.extend.PackedPlugin was not an instance of plugin...");
			}
		} catch (IllegalArgumentException | SecurityException | NoSuchFieldException
				| ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}