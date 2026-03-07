package models;

public class Marca {

    private int idMarca;
    private String nome;

    public Marca(int idMarca, String nome) {
        this.idMarca = idMarca;
    	this.nome = nome;
    }

    public int getId() {
        return idMarca;
    }

    public String getNome() {
        return nome;
    }
}