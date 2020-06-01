package fr.natu.dessinAPI.persistence;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.natu.dessinAPI.model.Box;

public interface BoxRepository extends JpaRepository<Box, Integer>{
	
	@Query(value = "Select color from tableau where (lin = :line and col = :column)",nativeQuery = true)
    public String findByLineAndColumn(
    		 @Param("line") int line,
    		 @Param("column") int column);
	
	@Transactional
	@Modifying
	@Query(value = "Insert into tableau (lin, col, color) values (:line, :column, :color)",  nativeQuery = true)
	public void postColor(
			@Param("line") int line,
			@Param("column") int column,
			@Param("color") String color);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tableau SET color = :color WHERE (col = :column AND lin = :line)",  nativeQuery = true)
	public void putColor(
			@Param("line") int line,
			@Param("column") int column,
			@Param("color") String color);
	
	@Query(value = "Select color from tableau order by lin, col", nativeQuery = true)
	public List<String> getAll();
}
 