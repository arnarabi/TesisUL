import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {
        get("/parar", (req, resp) -> {
            stop();
            return "";
        });
        post("/index", (req, resp) -> {
            String usuario = req.queryParams("user");
            String pass = req.queryParams("pass");

            MongoClientURI connectionString = new MongoClientURI("mongodb://diego:123@ds033259.mlab.com:33259/soft2");
            MongoClient mongoClient = new MongoClient(connectionString);
            MongoDatabase database = mongoClient.getDatabase("soft2");
            MongoCollection<Document> collection = database.getCollection("login");

            Document filtro = new Document();
            filtro.append("usuario", usuario);
            filtro.append("pass", pass);

            Document myDoc = collection.find(filtro).first();

            System.out.println(myDoc.get("usuario"));
            System.out.println(myDoc.get("pass"));

            System.out.println(usuario);
            System.out.println(pass);

            if (myDoc == null) {
                return new ModelAndView(null, "index.html");

            } else {
                return new ModelAndView(null, "main.html");
            }

        });
        get("/index", (req, resp) -> {
            /*  List<Post> listaPosts = new ArrayList<>();
             listaPosts.add(new Post("post1","01/01/2017"));
             listaPosts.add(new Post("post2","01/01/2018"));
             HashMap<String,Object> map = new HashMap<String,Object>();
             map.put("posts",listaPosts);*/
            return new ModelAndView(null, "index.html");

        }, new Jinja2TemplateEngine());
    }

}

