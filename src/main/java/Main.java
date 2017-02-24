import Response.GenericResponse;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import static spark.debug.DebugScreen.*;

import model.*;
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
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        staticFiles.expireTime(0L);
        enableDebugScreen();

        //DB CONFIG
        MongoClientURI connectionString = new MongoClientURI("mongodb://admin:admin@ds147599.mlab.com:47599/sof2");
//            MongoClientURI connectionString = new MongoClientURI("mongodb://diego:123@ds033259.mlab.com:33259/sof2");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("sof2");
        MongoCollection<Document> usuarioColl = database.getCollection("usuarios");
        MongoCollection<Document> tesisColl = database.getCollection("tesis");
        MongoCollection<Document> asesoresColl = database.getCollection("asesores");
        MongoCollection<Document> feedbackColl = database.getCollection("feedbacks");
        MongoCollection<Document> registroColl = database.getCollection("registros");
        MongoCollection<Document> repositorioColl = database.getCollection("repositorio");

        final String[] idUsuario = new String[1];
        idUsuario[0] = "diego";
        final int[] idAsesor = new int[1];
        idAsesor[0] = 1;

        //RUTAS
        get("/parar", (req, resp) -> {
            stop();
            return "";
        });

        get("/", (req, resp) -> {
            resp.redirect("/index");
            return "";
        });

        post("/login", (req, resp) -> {
            System.out.println("BODY");

            idUsuario[0] = req.queryParams("login");

            Document filtro = new Document();
            filtro.append("usuario", req.queryParams("login"));
            filtro.append("pass", req.queryParams("password"));

            Document myDoc = usuarioColl.find(filtro).first();

            if (myDoc == null) {

                resp.redirect("/login");

            } else if ((req.queryParams("login")).substring(0, 1).equals("A")) {
                resp.redirect("/repositorio_asesores");
                //se va a la lista de tesis 
            } else {
                //se va a la pantalla de tesis de alumno
                resp.redirect("/index");
            }

            return "asd";

        });

        get("/login", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            System.out.println(usuarioColl.find().first());
            return ViewUtil.render(req, model, "/templates/login.html");
        });

        get("/index", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();

            for (Document document : tesisColl.find(new Document().append("alumno", idUsuario[0]))) {
                model.put("titulo", document.getString("titulo"));
                model.put("rating", document.getInteger("rating"));
            }

            List<Feedback> comentario = new ArrayList<>();

            for (Document document : feedbackColl.find()) {
                System.out.println(document.getString("nombre"));
                comentario.add(new Feedback(document.getString("jurado"), document.getString("desc"), document.getString("fecha")));
            }

            model.putIfAbsent("feedback", comentario);
            return ViewUtil.render(req, model, "/templates/tesis_alumno.vm");
        });

        post("/registro_tesis", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            String titulo = req.queryParams("titulo");

            Document myDoc = new Document();
            myDoc.append("titulo", titulo);

            usuarioColl.insertOne(myDoc);

            return ViewUtil.render(req, model, "/templates/tesis.vm");

        });

        post("/registrar", (req, resp) -> {
            System.out.println("BODY");
            System.out.println(req.queryParams("title"));

            Document myDoc = new Document();
            myDoc.append("titulo", req.queryParams("title"));
            myDoc.append("rating", 0);
            myDoc.append("alumno", idUsuario[0]);

            System.out.println(myDoc.toString());

            tesisColl.insertOne(myDoc);

            resp.redirect("/index");

            return "";

        });

        post("/registro", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            String diahora = req.queryParams("diahora");
            String descripcion = req.queryParams("descripcion");
            Document myDoc = new Document();
            myDoc.append("diahora", diahora);
            myDoc.append("descripcion", descripcion);
            registroColl.insertOne(myDoc);

            return ViewUtil.render(req, model, "/templates/registro.vm");

        });
        
        get("/registro_tesis", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            System.out.println(usuarioColl.find().first());
            return ViewUtil.render(req, model, "/templates/registro_tesis.vm");
        });
        
        get("/repositorio_asesores", (req, resp) -> {
            System.out.println("repositorio");
            Map<String, Object> model = new HashMap<>();
            List<RepositorioTesis> repo = new ArrayList<>();
            for (Document document : repositorioColl.find()) {
                repo.add(new RepositorioTesis(document.getInteger("id"), document.getString("fecha"),
                        document.getString("titulo"), document.getString("autor")));
            }
            model.putIfAbsent("repositorio", repo);
            System.out.println(usuarioColl.find().first());
            return ViewUtil.render(req, model, "/templates/repositorio_asesores.vm");
        });

        get("/tesis_corregir", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            for (Document document : tesisColl.find(new Document().append("alumno", idUsuario[0]))) {
                model.put("titulo", document.getString("titulo"));
                model.put("rating", document.getInteger("rating"));
            }
            return ViewUtil.render(req, model, "/templates/tesis.vm");
        });

        get("/tesis", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<Feedback> comentario = new ArrayList<>();
            for (Document document : feedbackColl.find()) {
                System.out.println(document.getString("nombre"));
                comentario.add(new Feedback(document.getString("jurado"), document.getString("desc"), document.getString("fecha")));
            }
            model.putIfAbsent("feedback", comentario);
            return ViewUtil.render(req, model, "/templates/tesis.vm");
        });
        

        get("/repositorio", (req, resp) -> {
            System.out.println("repositorio");
            Map<String, Object> model = new HashMap<>();
            List<RepositorioTesis> repo = new ArrayList<>();
            for (Document document : repositorioColl.find()) {
                repo.add(new RepositorioTesis(document.getInteger("id"), document.getString("fecha"),
                        document.getString("titulo"), document.getString("autor")));
            }
            model.putIfAbsent("repositorios", repo);
            return ViewUtil.render(req, model, "/templates/repositorio.vm");
        });

        get("/asesores", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();

            List<Asesor> asesores = new ArrayList<>();
            for (Document document : asesoresColl.find()) {
                Asesor a = new Asesor(document.getInteger("id"), document.getString("nombre"), document.getString("dia"), document.getString("hora"));
                if (idAsesor[0] == document.getInteger("id")) {
                    a.setAsignado(true);
                }
                asesores.add(a);
            }
            model.putIfAbsent("asesores", asesores);
            return ViewUtil.render(req, model, "/templates/asesores.vm");
        });

        post("/cambiarasesor", (req, resp) -> {

            //TODO
            //no sé por qué hay error al hacer update: " javalangIllegalArgumentException. Invalid BSON field name idasesor.
            usuarioColl.updateOne(new Document().append("usuario", idUsuario[0]),
                    new Document().append("idasesor", req.queryParams("idasesor")));

            resp.redirect("/asesores");

            return "";

        });

        get("/registro", (req, resp) -> {
            Map<String, Object> model = new HashMap<>();
            List<Registro> registro = new ArrayList<>();
            for (Document document : registroColl.find()) {

                registro.add(new Registro(document.getString("diahora"),
                        document.getString("descripcion")));

            }
            model.putIfAbsent("registros", registro);
            return ViewUtil.render(req, model, "/templates/registro.vm");
        });

        post("/agregar", (req, resp) -> {

            Document myDoc = new Document();
            myDoc.append("diahora", req.queryParams("dia"));
            myDoc.append("descripcion", req.queryParams("desc"));

            System.out.println(myDoc.toString());

            registroColl.insertOne(myDoc);

            resp.redirect("/registro");

            return "";

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