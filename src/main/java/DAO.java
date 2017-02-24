
import org.bson.Document;


public class DAO {
    
    
    Document filtro = new Document();
    public boolean performLogin(String usuario, String pass){
            
           
        
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
    return true
    }
}
