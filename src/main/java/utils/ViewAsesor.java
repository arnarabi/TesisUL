
package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Asesor;
import model.Feedback;
import model.Registro;
import model.RepositorioTesis;
import model.Tesis;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

public class ViewAsesor {
    private static VelocityTemplateEngine engine;
    
    public static String render(Request request, Map<String, Object> model, String templatePath) {
        
       List<Feedback> feedback = new ArrayList<>();
       List<Registro> registros = new ArrayList<>();
       List<Asesor> asesores = new ArrayList<>();
       List<RepositorioTesis> repositorio = new ArrayList<>();
        
       return getRenderer().render(new ModelAndView(model, templatePath));
        
}
    
     private static VelocityTemplateEngine getRenderer() {
        if (engine == null) {
            engine = new VelocityTemplateEngine();
        }
        return engine;
    }
    
}