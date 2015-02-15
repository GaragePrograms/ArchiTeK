package io.github.garageprograms.architek.plugins;

import java.io.File;

public class FilePaths {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() { //TODO: Support
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }
    
    public static final String additivePluginPath = "plugins";
    public static final String additiveLibraryPath = "libraries";
    
    public static final String linuxGlobalPath = "/usr/share/ArchiTeK/";
    public static final String windowsGlobalPath = "C:/Program Files/ArchiTeK/";
    
    public static final String linuxLocalPath = "~/.ArchiTeK/";
    public static final String windowsLocalPath = System.getProperty("user.home")+"/.ArchiTeK/";
    
    public static File getGlobalPath(){
    	if (isWindows()){
    		return new File(windowsGlobalPath);
    	}
    	return new File(linuxGlobalPath);
    }
    
    public static File getLocalPath(){
    	if (isWindows()){
    		return new File(windowsLocalPath);
    	}
    	return new File(linuxLocalPath);
    }
    
    public static File getLocalPluginPath(){
    	return new File(getLocalPath(), additivePluginPath);
    }
    
    public static File getLocalLibraryPath(){
    	return new File(getLocalPath(), additiveLibraryPath);
    }
    
    public static File getGlobalPluginPath(){
    	return new File(getGlobalPath(), additivePluginPath);
    }
    
    public static File getGlobalLibraryPath(){
    	return new File(getGlobalPath(), additiveLibraryPath);
    }
}
