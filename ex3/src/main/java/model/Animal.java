package model;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private String especie;
    private float peso;

    public Animal() {
        this.id = -1;
        this.nome = "";
        this.idade = 0;
        this.especie = "";
        this.peso = 0.0f;
    }

    public Animal(int id, String nome, int idade, String especie, float peso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.peso = peso;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    @Override
    public String toString() {
        return "Animal: " + nome + ", Espécie: " + especie + ", Idade: " + idade + ", Peso: " + peso + "kg";
    }
}
