package model;

public class Respuesta {

	public String contenido;
	public int pregunta_id;
	public String correcta;
	public Respuesta(int id,String contenido, String correcta) {
		this.pregunta_id = id;
		this.contenido = contenido;
		
		this.correcta = correcta;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getPregunta_id() {
		return pregunta_id;
	}
	public void setPregunta_id(int pregunta_id) {
		this.pregunta_id = pregunta_id;
	}
	public String getCorrecta() {
		return correcta;
	}
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}
	
	
}
