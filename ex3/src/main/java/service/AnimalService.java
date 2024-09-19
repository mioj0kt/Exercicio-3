package service;

import java.util.*;
import dao.AnimalDAO;
import model.Animal;
import spark.Request;
import spark.Response;

public class AnimalService {

    private AnimalDAO animalDAO = new AnimalDAO();

    public Object insert(Request request, Response response) {
        String nome = request.queryParams("nome");
        int idade = Integer.parseInt(request.queryParams("idade"));
        String especie = request.queryParams("especie");
        float peso = Float.parseFloat(request.queryParams("peso"));

        Animal animal = new Animal(-1, nome, idade, especie, peso);

        animalDAO.insert(animal);

        response.status(201); // HTTP 201 Created
        return "Animal cadastrado com sucesso!";
    }
    
    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Animal animal = animalDAO.get(id);
        
        if (animal != null) {
            return animal.toString(); // ou um formato JSON, dependendo do que deseja retornar
        } else {
            response.status(404); // HTTP 404 Not Found
            return "Animal não encontrado.";
        }
    }

    
    public Object getAll(Request request, Response response) {
        String orderBy = request.params(":orderby");
        List<Animal> animais = animalDAO.getAll(orderBy); // Assumindo que você tem uma lógica de ordenação no DAO

        if (animais != null && !animais.isEmpty()) {
            return animais.toString(); // ou um formato JSON
        } else {
            response.status(404);
            return "Nenhum animal encontrado.";
        }
    }
    
    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Animal animal = animalDAO.get(id);

        if (animal != null) {
            return animal.toString(); // Retornar o animal a ser atualizado
        } else {
            response.status(404);
            return "Animal não encontrado para atualização.";
        }
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        String nome = request.queryParams("nome");
        int idade = Integer.parseInt(request.queryParams("idade"));
        String especie = request.queryParams("especie");
        float peso = Float.parseFloat(request.queryParams("peso"));

        Animal animal = new Animal(id, nome, idade, especie, peso);
        
        if (animalDAO.update(animal)) {
            response.status(200); // HTTP 200 OK
            return "Animal atualizado com sucesso!";
        } else {
            response.status(404); // HTTP 404 Not Found
            return "Animal não encontrado.";
        }
    }

    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        if (animalDAO.delete(id)) {
            response.status(200); // HTTP 200 OK
            return "Animal removido com sucesso!";
        } else {
            response.status(404); // HTTP 404 Not Found
            return "Animal não encontrado.";
        }
    }


    	
}
