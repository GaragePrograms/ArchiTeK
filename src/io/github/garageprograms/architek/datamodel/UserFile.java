package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

public class UserFile extends ArchiTeKNode{
	public ArrayList<UserClass> encapsulatedClasses = new ArrayList<UserClass>();
	public ArrayList<UserFunction> encapsulatedFunctions = new ArrayList<UserFunction>();
	public ArrayList<UserVariable> encapsulatedVariables = new ArrayList<UserVariable>();
	public UserProject parent = null;
	
	public void addClass(UserClass c){
		this.encapsulatedClasses.add(c);
		c.parent = this;
	}
	
	public void addFunction(UserFunction f){
		this.encapsulatedFunctions.add(f);
		f.parent = this;
	}
	
	public void addVariable(UserVariable v){
		this.encapsulatedVariables.add(v);
		v.parent = this;
	}
}
