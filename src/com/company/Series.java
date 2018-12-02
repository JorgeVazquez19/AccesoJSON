package com.company;

public class Series {

    int id;
    String nombre;
    String descripcion;
    String productora;
    int temporadas;
    int id_protagonistas;


    public Series() {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productora = productora;
        this.temporadas = temporadas;
        this.id_protagonistas = id_protagonistas;
    }

    public Series(int id, String nombre, String descripcion, String productora, int temporadas,
			int id_protagonistas) {
    	
    	this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productora = productora;
        this.temporadas = temporadas;
        this.id_protagonistas = id_protagonistas;
	}

	public Series(String nombre, String descripcion, String productora, int temporadas, int id_protagonistas) {
		this.nombre = nombre;
        this.descripcion = descripcion;
        this.productora = productora;
        this.temporadas = temporadas;
        this.id_protagonistas = id_protagonistas;
	}

	public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        productora = productora;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        temporadas = temporadas;
    }

    public int getProtagonistas() {
        return id_protagonistas;
    }

    public void setProtagonistas(int protagonistas) {
        this.id_protagonistas = protagonistas;
    }
    public String toString(){
		String aux ="";
		
		aux += "------------------------------------------";
		aux += "\n	ID: " + this.id;
		aux += "\n	NOMBRE: " + this.nombre;
		aux += "\n	DESCRIPCION: " + this.descripcion;
		aux += "\n	PRODUCTORA: " + this.productora;
		aux += "\n	TEMPORADAS: " + this.temporadas;
		aux += "\n	ID_PROTAGONISTAS: " + this.id_protagonistas;
		aux += "\n------------------------------------------";
		
		return aux;
	}
}
