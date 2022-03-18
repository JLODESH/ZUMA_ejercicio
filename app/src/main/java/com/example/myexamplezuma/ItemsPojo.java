package com.example.myexamplezuma;

public class ItemsPojo {

    private String nombre;
    private String email;
    private String foto;
    private String genero;
    private String apellido;

    public ItemsPojo(String nombre, String email, String foto, String genero, String apellido) {
        this.nombre = nombre;
        this.email = email;
        this.foto=foto;
        this.genero = genero;
        this.apellido = apellido;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
