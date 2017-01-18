package baseDatos;

import java.sql.*;


public class ConexionBaseDatos {

	private Connection conexion;
	private Statement sentencia ;
	private String sourceURL="";
	private String user="";
	private String password="";
	
	//Constructores
		
		public ConexionBaseDatos(String sourceURl,String user , String password) {
			this.sourceURL = sourceURl;
			this.user = user;
			this.password = password;
			
		}
	
	//Metodos Consultores
		public Connection getConexion() {
			return conexion;
		}


		public Statement getSentencia() {
			return sentencia;
		}
		
		public String getSourceURL() {
			return sourceURL;
		}

		public String getUser() {
			return user;
		}
		
	//Metodos Modificadores
		public void setSentencia(Statement sentencia) {
			this.sentencia = sentencia;
		}

		public void setConexion(Connection conexion) {
			this.conexion = conexion;
		}


		public void setSourceURL(String sourceURL) {
			this.sourceURL = sourceURL;
		}



		public void setUser(String user) {
			this.user = user;
		}
		
		
		
	//Metodos Añadidos
	

	

		/*		Cabecera : public void establecerConexion();
		 * 	Descripcion:establecera una concexion con la base de datos y  asi podra tener acceso a los usuarios
		 * 				y la contraseña y otros metodos
		 * 	Entradas:
		 * 	Salidas:
		 *  Postcondiciones:establece una concecion con la base de datos 
		 */
		public void establecerConexion(){
			try {
				
				conexion = DriverManager.getConnection(sourceURL, user, password);
				sentencia = conexion.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		/*		Cabecera : public void cerrarConexion();
		 * 	Descripcion: cerrara la concecion
		 * 	Entradas:
		 * 	Salidas:
		 *  Postcondiciones:cierra una concecion con la base de datos ICORT
		 */
		public void cerrarConexion(){
			try {
				sentencia.close();
				conexion.close();
			} catch (SQLException e) {
				System.out.println("Error al establecer la coneccion");
			}
			
		}
		
		
	

	
	/*		Cabecera : public boolean conectado()
	 * 	Descripcion:comprobara si hay coneccion establecida con el server
	 * 	Entradas:
	 * 	Salidas:boolean
	 *  Postcondiciones: devolvera true en caso de que la coneccion este establecida
	 */
		public boolean conectado(){
			boolean conect = false;
			
				if(conexion!=null){
					conect = true;
				}
				
			return(conect);
		}
	
	/*		Cabecera : 
	 * 	Descripcion:
	 * 	Entradas:
	 * 	Salidas:
	 *  Postcondiciones:
	 */
	/*		Cabecera : 
	 * 	Descripcion:
	 * 	Entradas:
	 * 	Salidas:
	 *  Postcondiciones:
	 */
	
}
