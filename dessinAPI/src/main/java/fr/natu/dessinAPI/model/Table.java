package fr.natu.dessinAPI.model;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import fr.natu.dessinAPI.GlobalVariable;

public class Table {
	private String grille[][] = new String[GlobalVariable.getNbRow()][GlobalVariable.getNbColumn()];

	public Table(List<String> sortedColorList) {
		super();
		ListIterator<String> iterator = sortedColorList.listIterator();
		for (int i = 0 ; i < GlobalVariable.getNbRow()  ; i++  ) {
			for (int j = 0 ; j < GlobalVariable.getNbColumn() ; j++  ) {
				grille[i][j] = iterator.next();
						//sortedColorList[i* GlobalVariable.getNbColumn() + j];
				System.out.print( grille[i][j] + "|");
			}
			
			System.out.println( "\n__________________________");
		}
		
		
		
		
		
	}

	public String[][] getGrille() {
		return grille;
	}

	public void setGrille(String[][] grille) {
		this.grille = grille;
	}

	@Override
	public String toString() {
		return "Table [grille=" + Arrays.toString(grille) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grille);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (!Arrays.deepEquals(grille, other.grille))
			return false;
		return true;
	}
	
	
	
	
 }
