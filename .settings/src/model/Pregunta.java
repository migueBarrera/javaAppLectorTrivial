package model;

public class Pregunta {

	public int regla_id ;
	public String pregunta;
	public String anotacion;
	public Respuesta[] respuestas;
	
	public Pregunta(int regla_id, String pregunta) {
		this.regla_id = regla_id;
		this.pregunta = pregunta;
		
	}
	

	public String getAnotacion() {
		return anotacion;
	}


	public void setAnotacion(String anotacion) {
		this.anotacion = anotacion;
	}


	public int getRegla_id() {
		return regla_id;
	}

	public void setRegla_id(int regla_id) {
		this.regla_id = regla_id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Respuesta[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Respuesta[] respuestas) {
		this.respuestas = respuestas;
	}

	
	
}
