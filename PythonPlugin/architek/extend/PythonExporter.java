package architek.extend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.plugins.Exporter;

public class PythonExporter implements Exporter {
	public static void log(String text){
		System.out.println("PythonExporter -> "+text);
	}
	
	public void ensureDirectoryExists(File location){
		log("Ensuring "+location.getAbsolutePath());
		location.mkdirs();
	}
	
	public void recusivleyDelete(File root){
		String[]entries = root.list();
		for(String s: entries){
		    File currentFile = new File(root,s);
		    currentFile.delete();
		    log("Deleted "+currentFile.getAbsolutePath()+" as part of deleting "+root.getAbsolutePath());
		}
	}
	
	public BufferedWriter getWriter(File dest){
		log("Opening "+dest.getAbsolutePath()+" for writing");
		try {
			return new BufferedWriter(new FileWriter(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeUserFile(UserFile f, File root) throws IOException{
		log("Creating a UserFile");
		File path = new File(root.getAbsolutePath());
		for (String i : f.name.split(".")){
			path=new File(path, i);
		}
		log("Mapped python module "+f.name+" to folder set "+path.getAbsolutePath());
		ensureDirectoryExists(path);
		BufferedWriter writer = getWriter(path);
		writer.write(f.name);
		writer.close();
	}
	
	public void exportToDir(String dir, UserProject proj) {
		log("Exporting "+proj.name+" to python...");
		File root = new File(dir, "PythonExporter_Build");
		log("Base is "+root.getAbsolutePath());
		this.recusivleyDelete(root);
		for (UserFile f : proj.files){
			try {
				this.writeUserFile(f, root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
