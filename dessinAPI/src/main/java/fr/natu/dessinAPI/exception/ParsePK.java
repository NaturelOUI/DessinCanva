package fr.natu.dessinAPI.exception;

public class ParsePK extends Exception {
	public ParsePK(String s) {
		System.out.println(s + "cannot be converted to an int");
	}
}
