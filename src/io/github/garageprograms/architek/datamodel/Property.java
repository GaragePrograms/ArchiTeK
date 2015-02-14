package io.github.garageprograms.architek.datamodel;

public class Property extends ArchiTeKNode{
	public boolean canBeAppliedToClass(UserClass c){
		return false;
	}
	
	public boolean canBeAppliedToFunction(UserFunction c){
		return false;
	}
	
	public boolean canBeAppliedToVariable(UserVariable c){
		return false;
	}
}
