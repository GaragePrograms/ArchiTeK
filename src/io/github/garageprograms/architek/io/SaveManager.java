package io.github.garageprograms.architek.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import io.github.garageprograms.architek.datamodel.UserProject;

public class SaveManager {
	public static void saveProject(UserProject project, String filename){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc.appendChild(project.saveToXML(doc));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
	 
			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
		
	}
	
	public static UserProject loadProject(String filename) throws FileNotFoundException{
		System.out.println("Loading "+filename);
		try {
			Document doc;
			File fXmlFile = new File(filename);
			if (!fXmlFile.exists()){
				throw new FileNotFoundException();
			}
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			UserProject up = new UserProject(root);
			up.referencePath=filename;
			return up;
		} catch (SAXException | IOException | ParserConfigurationException ex) {
			ex.printStackTrace();
			
		}
	 
		return null;
	}
}
