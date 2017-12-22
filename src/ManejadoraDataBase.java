


import java.awt.color.CMMException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Pregunta;
import model.Respuesta;

public class ManejadoraDataBase {
	
	public Connection con;
	
	public ManejadoraDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
		              "jdbc:mysql://localhost/develop?"
		              + "user=migue&password=22102010");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insertarPregunta(Pregunta pregunta){
		int pregunta_id = 0;
		//Connection con;
		Statement cmd;
		String cadenaInsert;
		String cadenaUltimoID = "SELECT MAX(id)  FROM `preguntas`";
		
		//Preparamos la cadena segun tenga anotacion o no
		if(pregunta.getAnotacion() != ""){
			cadenaInsert = "INSERT INTO `preguntas` (`regla_id`, `pregunta`, `anotacion`,`test_id`) VALUES ('"+pregunta.getRegla_id()+"', '"+pregunta.getPregunta()+"', '"+pregunta.getAnotacion()+"','0');";
			
		}else {
			cadenaInsert = "INSERT INTO `preguntas` (`regla_id`, `pregunta`,`test_id`) VALUES ('"+pregunta.getRegla_id()+"', '"+pregunta.getPregunta()+"','0');";
			
		}
		
		try {
			/*Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
		              "jdbc:mysql://localhost/develop?"
		              + "user=migue&password=22102010");*/
			cmd = con.createStatement();
			cmd.executeUpdate(cadenaInsert);
			
			//Consultar ultimo id insertado
			ResultSet rs = cmd.executeQuery(cadenaUltimoID);
			if(rs!=null){
				rs.next();
				pregunta_id = rs.getInt(1);
			}
			
			cmd.close();
			//con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
		
		return pregunta_id;
	}
	
	public void insertarRespuesta(Respuesta respuesta){
		String cadenaInsert = "INSERT INTO `respuestas` ( `pregunta_id`, `contenido`, `correcta`) VALUES ('"+respuesta.pregunta_id+"', '"+respuesta.getContenido()+"', '"+respuesta.getCorrecta()+"');";
		//Connection con;
		Statement cmd;
		try {
			/*Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
		              "jdbc:mysql://localhost/develop?"
		              + "user=migue&password=22102010");*/
			
			cmd = con.createStatement();
			cmd.executeUpdate(cadenaInsert);
			
					
			cmd.close();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   
	    
	    
		
	}

}
