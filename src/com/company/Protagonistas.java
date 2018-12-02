package com.company;

public class Protagonistas {

    int ID;
    String Nombre;
    int Edad;
    String Representante;


    public Protagonistas() {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Representante = Representante;
    }

    public Protagonistas(int ID, String Nombre, int Edad, String Representante) {
    	this.ID = ID;
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Representante = Representante;
	}

	public Protagonistas(String Nombre, int Edad, String Representante) {
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Representante = Representante;
	}

	public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getRepresentante() {
        return Representante;
    }

    public void setRepresentante(String Representante) {
        this.Representante = Representante;
    }
    public String toString(){
		String aux ="";
		
		aux += "------------------------------------------";
		aux += "\n	ID: " + this.ID;
		aux += "\n	NOMBRE: " + this.Nombre;
		aux += "\n	EDAD: " + this.Edad;
		aux += "\n	REPRESENTANTE: " + this.Representante;
		aux += "\n------------------------------------------";
		
		return aux;
	}

}
