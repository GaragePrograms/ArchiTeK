package io.github.garageprograms.architek.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import io.github.garageprograms.architek.ArchiTeK;
import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserProject;

public class PluginManager {
   private static PluginManager instance = null;
   private PluginManager() {}
   public boolean hasStarted = false;
   public static PluginManager getInstance() {
      if(instance == null) {
         instance = new PluginManager();
         instance.languages.add(new DummyProgrammingLanguageImpl());
         instance.installLanguage("_default");
      }
      return instance;
   }
	   
	public ProgrammingLanguage language;
	public ArrayList<ProgrammingLanguage> languages = new ArrayList<ProgrammingLanguage>();
	public ArrayList<Plugin> plugins = new ArrayList<Plugin>();
	public ArrayList<String> requredLibraries = new ArrayList<String>();
	
	public Property getProperty(String name){
		for (Property p : this.language.getProperties()){
			if (p.name==name){
				return p;
			}
		}
		return null;
	}
	
	public ProgrammingLanguage getLanguage(String uniqueID){
		for (ProgrammingLanguage l : this.languages){
			if (l.getUniqueID().equals(uniqueID)){
				return l;
			}
		}
		return null;
	}
	
	public void installLanguage(String uniqueID){
		this.language=this.getLanguage(uniqueID);
	}
	
	public void loadAllPlugins(){
		try{
			this.loadAllPluginsInDir(FilePaths.getGlobalPluginPath().getAbsolutePath());
		}catch(NullPointerException e){
			System.out.println("Could not find global plugin path. Does '"+FilePaths.getGlobalPluginPath()+"' exist?");
		}
		
		try{
			this.loadAllPluginsInDir(FilePaths.getLocalPluginPath().getAbsolutePath());
		}catch(NullPointerException e){
			System.out.println("Could not find local plugin path. Does '"+FilePaths.getLocalPluginPath()+"' exist?");
		}
		this.hasStarted=true;
	}
	
	public void loadAllPluginsInDir(String libDir){
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
			Plugin plugin = (Plugin)classToLoad.newInstance();
			System.out.println("-->Name: "+plugin.getName());
			System.out.println("-->UUID: "+plugin.getUID());
			plugin.install();
			//System.out.println(pluginObj.getClass());
//			if (pluginObj.getClass().getGenericSuperclass() == Plugin.class){
//				System.out.println("-->Name: "+(String)pluginObj.getClass().getField("name").get(pluginObj));
//				System.out.println("-->Desc: "+(String)pluginObj.getClass().getField("description").get(pluginObj));
//				Method method = classToLoad.getDeclaredMethod ("install");
//				method.invoke(pluginObj);
//			}else{
//				System.out.println("architek.extend.PackedPlugin was not an instance of plugin...");
//			}
		} catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onProjectStart(UserProject p){
		this.language.install(p);
		p.programmingLanguageUID=this.language.getUniqueID();
	}
}