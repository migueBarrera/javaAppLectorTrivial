


import java.awt.color.CMMException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Pregunta;
import model.Respuesta;

public class ManejadoraDataBase {
	
	public int insertarPregunta(Pregunta pregunta){
		int pregunta_id = 0;
		Connection con;
		Statement cmd;
		String cadenaInsert;
		String cadenaUltimoID = "SELECT MAX(id)  FROM `preguntas`";
		
		//Preparamos la cadena segun tenga anotacion o no
		if(pregunta.getAnotacion() != ""){
			cadenaInsert = "INSERT INTO `preguntas` (`regla_id`, `pregunta`, `anotacion`) VALUES ('"+pregunta.getRegla_id()+"', '"+pregunta.getPregunta()+"', '"+pregunta.getAnotacion()+"');";
			
		}else {
			cadenaInsert = "INSERT INTO `preguntas` (`regla_id`, `pregunta`) VALUES ('"+pregunta.getRegla_id()+"', '"+pregunta.getPregunta()+"');";
			
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
		              "jdbc:mysql://localhost/db_trivial?"
		              + "user=root&password=");
			cmd = con.createStatement();
			cmd.executeUpdate(cadenaInsert);
			
			//Consultar ultimo id insertado
			ResultSet rs = cmd.executeQuery(cadenaUltimoID);
			if(rs!=null){
				rs.next();
				pregunta_id = rs.getInt(1);
			}
			
			cmd.close();
			con.close();
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Problema al insertar la pregunta "+e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
		
		return pregunta_id;
	}
	
	public void insertarRespuesta(Respuesta respuesta){
		String cadenaInsert = "INSERT INTO `respuestas` ( `pregunta_id`, `contenido`, `correcta`) VALUES ('"+respuesta.pregunta_id+"', '"+respuesta.getContenido()+"', '"+respuesta.getCorrecta()+"');";
		Connection con;
		Statement cmd;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
		              "jdbc:mysql://localhost/db_trivial?"
		              + "user=root&password=");
			
			cmd = con.createStatement();
			cmd.executeUpdate(cadenaInsert);
			
					
			cmd.close();
			con.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Problema al insertar la respuesta "+e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   
	    
	    
		
	}

}
