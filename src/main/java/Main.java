import Response.GenericResponse;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import static spark.debug.DebugScreen.*;

import model.Asesor;
import model.RepositorioTesis;
import org.bson.Document;

import java.util.*;
import model.Feedback;
import model.Registro;

import static spark.Spark.*;
import static spark.Spark.staticFiles;

import request.LoginRequest;
import utils.JsonTransformer;
import utils.ViewUtil;

public class Main {

    public static void main(String[] args) {

        //CONFIG
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        staticFiles.expireTime(0L);
        enableDebugScreen();

        //DB CONFIG

        MongoClientURI connectionString = new MongoClientURI("mongodb://admin:admin@ds147599.mlab.com:47599/sof2");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("sof2");
        MongoCollection<Document> usuarioColl = database.getCollection("usuarios");
        MongoCollection<Document> tesisColl = database.getCollection("tesis");
        MongoCollection<Document> asesoresColl = database.getCollection("asesores");
        MongoCollection<Document> feedbackColl = database.getCollection("feedbacks");
        MongoCollection<Document> registroColl = database.getCollection("registros");
        MongoCollection<Document> repositorioColl = database.getCollection("repositorios");
        

        //RUTAS

        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        post("/login", (req, resp) -> {
            System.out.println("BODY");
            System.out.println(req.body());

            Document filtro = new Document();
            filtro.append("usuario", /*body.getUsuario()*/"diego");
            filtro.append("pass", /*body.getPass()*/"123");

            Document myDoc = usuarioColl.find(filtro).first();

            System.out.println(myDoc.get("usuario"));
            System.out.println(myDoc.get("pass"));

            System.out.println("body");

            String usuario = req.queryParams("usuario");
            return new GenericResponse("Login Exitoso", true);
            
            if (usuario.substring(0, 1).equals("A")) {
                //se va a la lista de tesis 
                Map<String, Object> model = new HashMap<>();
                return ViewUtil.render(req, model, "/templates/repositorio_asesores.vm");
                
            } else {
                //se va a la pantalla de tesis de alumno
                 Map<String, Object> model = new HashMap<>();
                return ViewUtil.render(req, model, "/templates/tesis.vm");
            }
            
        }, new JsonTransformer());

        get("/login", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            System.out.println(usuarioColl.find().first());
            return ViewUtil.render(req, model, "/templates/login.html");
        });

        get("/index", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<Feedback> comentario = new ArrayList<>();
            for (Document document : feedbackColl.find()) {
                System.out.println(document.getString("nombre"));
                comentario.add(new Feedback(document.getString("jurado"),document.getString("desc"),document.getString("fecha")));
            }
            model.putIfAbsent("feedback", comentario);
            return ViewUtil.render(req, model, "/templates/tesis.vm");
        });
            
         get("/repositorio", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<RepositorioTesis> repo = new ArrayList<>();
            for (Document document : repositorioColl.find()) {
                repo.add(new RepositorioTesis(document.getInteger("id"),document.getString("fecha"),
                        document.getString("titulo"),document.getString("autor")));
            }
            model.putIfAbsent("repositorio", repo);
            return ViewUtil.render(req, model, "/templates/repositorio.vm");
        });

        
        get("/repositorio_asesores", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<RepositorioTesis> repositorio = new ArrayList<>();
            for (Document document : asesoresColl.find()) {
                System.out.println(document.getString("nombre"));
                repositorio.add(new RepositorioTesis(document.getInteger("id"), document.getString("fecha"),
                        document.getString("titulo"), document.getString("autor")));
            }
            return ViewUtil.render(req, model, "/templates/repositorio_asesores.vm");
        });
        get("/registro", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<Registro> registro = new ArrayList<>();
            for (Document document : registroColl.find()) {
                registro.add(new Registro(document.getInteger("id"), document.getString("diahora"),
                        document.getString("descripcion")));

            }
            model.putIfAbsent("registros", registro);
            return ViewUtil.render(req, model, "/templates/registro.vm");
        });

        
            //get("/registro_tesis", (req, resp) -> {
                

            //}
        
            post("/registro_tesis", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            String titulo = req.queryParams("titulo");

            Document myDoc = new Document();
            myDoc.append("titulo", titulo);

            usuarioColl.insertOne(myDoc);

            return ViewUtil.render(req, model, "/templates/tesis.vm");

        });
            
            post("/registro_tesis", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            String diahora = req.queryParams("diahora");
            String descripcion = req.queryParams("descripcion");
            Document myDoc = new Document();
            myDoc.append("diahora", diahora);
            myDoc.append("descripcion", descripcion);
            registroColl.insertOne(myDoc);

            return ViewUtil.render(req, model, "/templates/registro.vm");

        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}

