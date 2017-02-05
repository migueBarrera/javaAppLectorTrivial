
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;

import model.Pregunta;
import model.Respuesta;
import model.Solucion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {

	public static void main(String[] args) {
		
		//CAMBIAR ESTOS DATOS SEGUN LA REGLA
				int numeroDeRegla = 1;
		do{
			int REGLA_ID = numeroDeRegla;
			String tituloRegla = "reglas/"+numeroDeRegla+".txt";
			String tituloSolucion = "soluciones/sol"+numeroDeRegla+".txt";
			

			Respuesta respuesta;
			Pregunta pregunta;
			Solucion solucion;
			int pregunta_id;
			int contadorRespuesta;
			String correcta;
			ManejadoraDataBase manejadoraBD =new ManejadoraDataBase();

			    
			    BufferedReader brRegla = null;
				FileReader frRegla = null;
				
				FileReader frSolucion= null;
				BufferedReader brSolucion = null;
			    try {

				   
				    //Lector Regla
					frRegla = new FileReader(tituloRegla);
					brRegla = new BufferedReader(frRegla);
					
					//Lector Solucion
					frSolucion = new FileReader(tituloSolucion);
					brSolucion = new BufferedReader(frSolucion);
					
					String linea;
					String lineapreparada;

					
					linea = brRegla.readLine();
					while (linea != null) {
						
						//Si la linea contiene .- => estamos la primera linea de una pregunta
						while (linea.contains(".-")) {
							
							lineapreparada = linea.substring(4);//le quitamos la primera parte n.-
							
							linea = brRegla.readLine();
							//Mientras siga siendo la pregunta
							while (!linea.contains("a)") && !linea.contains("b)") && !linea.contains("c)") && !linea.contains("d)") ) {
								lineapreparada = lineapreparada + linea;
								
								linea = brRegla.readLine();
							}
							//Ya tenemos la pregunta Y la insertamos
							solucion = obtenerSolucion(brSolucion);
							pregunta = new Pregunta(REGLA_ID, lineapreparada);
							
							//Si tiene anotacion se la añadimos
							if(solucion.getAnotacion()!=""){
								pregunta.setAnotacion(solucion.getAnotacion());
							}
							pregunta_id = manejadoraBD.insertarPregunta(pregunta);
							//System.out.println("PREGUNTA ------------------->");
							
							
							contadorRespuesta = 0;
							//Mientras tenga a) o b) o c) o d) sera una nueva respuesta
							while(linea.contains("a)") || linea.contains("b)")|| linea.contains("c)") || linea.contains("d)") ){
								lineapreparada = linea.substring(2);
								contadorRespuesta++;
								linea = brRegla.readLine();
								//Mientras siga siendo la respuesta
								while ((!linea.contains("a)") && !linea.contains("b)") && !linea.contains("c)") && !linea.contains("d)")) && !linea.contains("FIN") && !linea.contains(".-") ) {
									lineapreparada = lineapreparada + linea;
									
									linea = brRegla.readLine();
								}
								correcta = calcularCorrecta(solucion, contadorRespuesta);
								respuesta = new Respuesta(pregunta_id, lineapreparada,correcta);
								manejadoraBD.insertarRespuesta(respuesta);
								//System.out.println("RESPUESTA --->");
								
							}			

						}
						
						linea = brRegla.readLine();
					}

					} catch (IOException ex) {
						ex.printStackTrace();

					}finally {
						try {
							brRegla.close();
							brSolucion.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    
			
			numeroDeRegla++;
		}while(numeroDeRegla<=15);
		
	}
	
	
	
	//METODOS
	
	public static String calcularCorrecta(Solucion solucion,int contador){
		String correcta = "F";
		int numeroCorrecta = 0;
		
		switch (solucion.getCorrecta()) {
		case "A":
			numeroCorrecta = 1;
			break;

		case "B":
			numeroCorrecta = 2;
			break;
		case "C":
			numeroCorrecta = 3;
			break;
		case "D":
			numeroCorrecta = 4;
			break;
		}
		
		if(numeroCorrecta == contador){
			correcta = "T";
		}
		
		
		return correcta;
	}
	
	public static Solucion obtenerSolucion(BufferedReader br){
		Solucion solucion;
		String linea;
		String lineaAnotacion = "";
		StringTokenizer st =null;
		solucion = new Solucion();
		
		try {
			linea = br.readLine();
			st = new StringTokenizer(linea);
			
			while(st.hasMoreTokens()){
				st.nextToken();//El numero primero no nos interesa
				solucion.setCorrecta(st.nextToken());
				while (st.hasMoreTokens()) {
					lineaAnotacion = lineaAnotacion +" "+ st.nextToken();
				}
				solucion.setAnotacion(lineaAnotacion);
			}

		} catch (IOException e) {
			
			System.out.println("Ocurrio un error mientras se leia el fichero de soluciones");
		}
		
		return solucion;
	}

}
