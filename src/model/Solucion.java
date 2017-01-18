package model;

public class Solucion {
	public String correcta;
	public String anotacion;
	
	public Solucion(){
		
	}
	public Solucion(String correcta , String anotacion){
		this.anotacion = anotacion;
		this.correcta = correcta;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public String getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}
	
	

}
