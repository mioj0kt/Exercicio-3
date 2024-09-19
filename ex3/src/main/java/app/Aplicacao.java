package app;

import static spark.Spark.*;
import service.AnimalService;

public class Aplicacao {
    
    private static AnimalService animalService = new AnimalService();
    
    public static void main(String[] args) {
    	port(6790);
        
        staticFiles.location("/public");
        
        post("/animal/insert", (request, response) -> animalService.insert(request, response));
        
        get("/animal/:id", (request, response) -> animalService.get(request, response));
        
        get("/animal/list/:orderby", (request, response) -> animalService.getAll(request, response));
        
        get("/animal/update/:id", (request, response) -> animalService.getToUpdate(request, response));
        
        post("/animal/update/:id", (request, response) -> animalService.update(request, response));
        
        get("/animal/delete/:id", (request, response) -> animalService.delete(request, response));
    }
}
