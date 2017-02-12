import Response.GenericResponse;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import static spark.debug.DebugScreen.*;

import org.bson.Document;

import java.util.*;

import static spark.Spark.*;
import static spark.Spark.staticFiles;

import request.LoginRequest;
import utils.JsonTransformer;
import utils.ViewUtil;

public class Main {

    public static void main(String[] args) {

        //CONFIG
        staticFiles.location("/public");
        staticFiles.expireTime(0L);
        enableDebugScreen();

        //DB CONFIG

        MongoClientURI connectionString = new MongoClientURI("mongodb://admin:admin@ds147599.mlab.com:47599/sof2");
//            MongoClientURI connectionString = new MongoClientURI("mongodb://diego:123@ds033259.mlab.com:33259/soft2");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("soft2");
        MongoCollection<Document> usuarioColl = database.getCollection("usuarios");
        MongoCollection<Document> tesisColl = database.getCollection("tesis");

        //RUTAS

        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        post("/login", (req, resp) -> {
            System.out.println("BODY");
            System.out.println(req.body());
            LoginRequest body = new Gson().fromJson(req.body(), LoginRequest.class);

            Document filtro = new Document();
            filtro.append("usuario", /*body.getUsuario()*/"diego");
            filtro.append("pass", /*body.getPass()*/"123");

            Document myDoc = usuarioColl.find(filtro).first();

            System.out.println(myDoc.get("usuario"));
            System.out.println(myDoc.get("pass"));

            System.out.println("body");
            System.out.println(body);

            return new GenericResponse("Login Exitoso", true);

        }, new JsonTransformer());

        get("/login", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(req, model, "/templates/login.html");
        });

        get("/index", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(req, model, "/templates/tesis.vm");
        });

        get("/registro", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(req, model, "/templates/registro.vm");
        });

        get("/asesores", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(req, model, "/templates/asesores.vm");
        });
    }

}

