package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.io.SaveManager;
import io.github.garageprograms.architek.plugins.FilePaths;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserProject extends SerializableArchiTeKNode {
	public ArrayList<UserFile> files = new ArrayList<UserFile>();
	public ArrayList<UserClass> protoClasses = new ArrayList<UserClass>();
	public ArrayList<UserProject> imports = new ArrayList<UserProject>();

	public UserProject(String name, String comment) {
		super(name, comment);
	}
	
	public UserProject(Element elem){
		super("","");
		this.defaultLoadFromXML(elem);
		
		NodeList importsNode = elem.getElementsByTagName("imports").item(0).getChildNodes();
		for (int temp = 0; temp < importsNode.getLength(); temp++) {
			Element importNode = (Element)importsNode.item(temp);
			this.imports.add(new UserProject(importNode));
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
		
		
	}
	
	public String referencePath = ""; //TODO: Setup
	
	public void save(String filename){};
	public void load(String filename){};
	
	public void addFile(UserFile f){
		this.files.add(f);
		f.parent=this;
	}
	
	public Element saveReferenceToXML(Document doc){
		Element node = doc.createElement("UserProjectReference");
		node.setAttribute("referencePath", this.referencePath);
		return node;
	}

	public Element saveToXML(Document doc) {
		Element node = doc.createElement("UserProject");
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
	
	public UserClass getClassByLookup(String lookupID){
		//System.out.println("LUID: "+lookupID);
		for (UserFile f : this.files){
			for (UserClass c : f.encapsulatedClasses){
				//c.getLookupID();
				//System.out.println("====>"+c.getLookupID());
				if (c.getLookupID().equals(lookupID)){
					return c;
				}
			}
		}
		for (UserProject p : this.imports){
			if (p.getClassByLookup(lookupID)!=null){
				return p.getClassByLookup(lookupID);
			}
		}
		for (UserClass c : this.protoClasses){
			if (c.getLookupID().equals(lookupID)){
				return c;
			}
		}
		return null;
	}
	
	public void installLibrary(String filename){
		try{
			this.addImport(FilePaths.getLocalLibraryPath().getAbsolutePath()+"/"+filename+".ark");
			return;
		}catch(NullPointerException e){
			System.out.println("Could not find local library path. Does '"+FilePaths.getLocalLibraryPath()+"' exist?");
		}
		
		try{
			this.addImport(FilePaths.getGlobalLibraryPath().getAbsolutePath()+"/"+filename+".ark");
		}catch(NullPointerException e){
			System.out.println("Could not find global library path. Does '"+FilePaths.getGlobalLibraryPath()+"' exist?");
		}
	}
	
	public void addImport(String filename){
		this.imports.add(SaveManager.loadProject(filename));
	}
}
