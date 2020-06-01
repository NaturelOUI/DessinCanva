package fr.natu.dessinAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import fr.natu.dessinAPI.GlobalVariable;
import fr.natu.dessinAPI.LoggingController;

@Entity(name = "tableau")
public class Box {
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id ;
	
	@Column(name ="lin")
	private int line;
	
	@Column(name ="col")
	private int column;

	@Column(name = "color")
	private String color;
	
	

	public Box() {
		super();
	}
	
	public Box(int line, int column, String color) {
		super();
		this.line = line;
		this.column = column;
		this.color = color;
	}
	
	public ResponseEntity<Void> verify() {
		Logger logger = LoggerFactory.getLogger(LoggingController.class);
		ResponseEntity<Void> resp = null;
		
		if(line >= GlobalVariable.getNbRow() ) {
			logger.error("line is more than the number of rows");
			resp = ResponseEntity.badRequest().build();
		}
		if(column >= GlobalVariable.getNbColumn() ) {
			logger.error("column is more than the number of columns");
			resp = ResponseEntity.badRequest().build();
		}
		if(color.length() != 6  ) {
			logger.error("color doesn't contain 6 characters, it contains : " + color.length() + " characters.");
			resp = ResponseEntity.badRequest().build();
		}
		
		
		return resp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}


	@Override
	public String toString() {
		return "Box [id=" + id + ", line=" + line + ", column=" + column + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + column;
		result = prime * result + id;
		result = prime * result + line;
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
		Box other = (Box) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (column != other.column)
			return false;
		if (id != other.id)
			return false;
		if (line != other.line)
			return false;
		return true;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
