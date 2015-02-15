package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.io.SaveManager;
import io.github.garageprograms.architek.plugins.FilePaths;
import io.github.garageprograms.architek.plugins.PluginManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserProject extends SerializableArchiTeKNode {
	public ArrayList<UserFile> files = new ArrayList<UserFile>();
	public ArrayList<UserClass> protoClasses = new ArrayList<UserClass>();
	public ArrayList<UserProject> imports = new ArrayList<UserProject>();
	public String programmingLanguageUID = "";

	public UserProject(String name, String comment) {
		super(name, comment);
		PluginManager.getInstance().onProjectStart(this);
	}
	
	public UserProject(Element elem){
		super("","");
		this.defaultLoadFromXML(elem);
		
		this.programmingLanguageUID = elem.getAttribute("programmingLanguageUID");
		
		NodeList importsNode = elem.getElementsByTagName("imports").item(0).getChildNodes();
		for (int temp = 0; temp < importsNode.getLength(); temp++) {
			Element importNode = (Element)importsNode.item(temp);
			try {
				this.imports.add(SaveManager.loadProject(importNode.getAttribute("referencePath")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		NodeList classStubs = elem.getElementsByTagName("UserClass");
		for (int temp = 0; temp < classStubs.getLength(); temp++) {
			Element stub = (Element)classStubs.item(temp);
			UserFile temp1 = new UserFile(stub.getAttribute("protoClassSourceID"),"");
			UserClass tempclass = new UserClass(stub, (ArchiTeKNode) temp1, this);
			temp1.addClass(tempclass);
			this.protoClasses.add(tempclass);
		}
		
		NodeList filesNode = elem.getElementsByTagName("files").item(0).getChildNodes();
		for (int temp = 0; temp < filesNode.getLength(); temp++) {
			Element fileNode = (Element)filesNode.item(temp);
			this.addFile(new UserFile(fileNode, (ArchiTeKNode)this, this));
		}
		
		//PluginManager.getInstance().onProjectStart(this);
	}
	
	public String referencePath = ""; //TODO: Setup
	
	public void save(String filename){};
	public void load(String filename){};
	
	public UserFile addFile(UserFile f){
		this.files.add(f);
		f.parent=this;
		return f;
	}
	
	public Element saveReferenceToXML(Document doc){
		Element node = doc.createElement("UserProjectReference");
		node.setAttribute("referencePath", this.referencePath);
		return node;
	}

	public Element saveToXML(Document doc) {
		Element node = doc.createElement("UserProject");
		node.setAttribute("programmingLanguageUID", this.programmingLanguageUID);
		
		node.appendChild(this.defaultSaveToXML(doc));
		Element filesNode = doc.createElement("files");
		for (UserFile f : this.files){
			filesNode.appendChild(f.saveToXML(doc));
		}
		node.appendChild(filesNode);
		Element importsNode = doc.createElement("imports");
		for (UserProject p : this.imports){
			importsNode.appendChild(p.saveReferenceToXML(doc));
		}
		node.appendChild(importsNode);
		
		return node;
	}
	
	public boolean isInUserLibraries(String lookupID){
		for (UserFile f : this.files){
			for (UserClass c : f.encapsulatedClasses){
				if (c.getLookupID().equals(lookupID)){
					return true;
				}
			}
		}
		return false;
	}
	
	public UserClass getClassByLookup(String lookupID){
		System.out.println("====================================");
		System.out.println("LUID: "+lookupID);
		System.out.println("REFZ: "+this.name);
		for (UserFile f : this.files){
			for (UserClass c : f.encapsulatedClasses){
				//c.getLookupID();
				//System.out.println("====>"+c.getLookupID());
				if (c.getLookupID().equals(lookupID)){
					return c;
				}
			}
		}
		System.out.println("Not found locally predefined, proceeding to imports...");
		System.out.println("At this time imports = "+this.imports);
		for (UserProject p : this.imports){
			System.out.println("Searching in imports for "+lookupID);
			System.out.println("Result "+p.getClassByLookup(lookupID));
			if (p.getClassByLookup(lookupID)!=null){
				return p.getClassByLookup(lookupID);
			}
		}
		System.out.println("Not found in referenced modules. Proceeding to predefs...");
		for (UserClass c : this.protoClasses){
			if (c.getLookupID().equals(lookupID)){
				return c;
			}
		}
		return null;
	}
	
	public void installLibrary(String filename){
		if (new File(FilePaths.getLocalLibraryPath().getAbsolutePath()+"/"+filename+".ark").exists()){
			try{
				this.addImport(FilePaths.getLocalLibraryPath().getAbsolutePath()+"/"+filename+".ark");
				//return;
			}catch(NullPointerException e){
				System.out.println("Could not find local library path. Does '"+FilePaths.getLocalLibraryPath()+"' exist?");
			} catch (FileNotFoundException e) {
				System.out.println("File not found. Is it a valid filename?");
			}
		}else{
			try{
				this.addImport(FilePaths.getGlobalLibraryPath().getAbsolutePath()+"/"+filename+".ark");
			}catch(NullPointerException e){
				System.out.println("Could not find global library path. Does '"+FilePaths.getGlobalLibraryPath()+"' exist?");
			} catch (FileNotFoundException e) {
				System.out.println("File not found. Is it a valid filename?");
			}
		}
	}
	
	public void addImport(String filename) throws FileNotFoundException{
		this.imports.add(SaveManager.loadProject(filename));
		System.out.println("I've installed "+this.imports.get(0).files.get(0).encapsulatedClasses.get(0).getLookupID());
	}
}
