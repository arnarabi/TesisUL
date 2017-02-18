package utils;

import model.Asesor;
import model.Feedback;
import model.Registro;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by estef on 11/02/2017.
 */
public class ViewUtil {
    private static VelocityTemplateEngine engine;

    public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.putIfAbsent("titulo", "¿Por qué Alessandra estará loca?");
        List<Feedback> feedback = new ArrayList<>();
        feedback.add(new Feedback("Hernán Quintana", "me gustó", "11/02/2017"));
        feedback.add(new Feedback("Jorge Irey", "muy buen tema", "29/01/2017"));
        model.putIfAbsent("feedback", feedback);
        model.putIfAbsent("estado", "Incompleto");
        List<Registro> registros = new ArrayList<>();
        registros.add(new Registro(1, "Ma-7pm", "Se reviso las encuestas", true, true));
        registros.add(new Registro(2, "Lun-5pm", "Se corrigio la bibliografía", true, false));
        registros.add(new Registro(3, "Mie-4pm", "Se indico uso de codigo APA", true, false));
        registros.add(new Registro(5, "Jue-2pm", "Se reviso marco teorico", false, true));
        model.putIfAbsent("registros", registros);
        return getRenderer().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine getRenderer() {
        if (engine == null) {
            engine = new VelocityTemplateEngine();
        }
        return engine;
    }
}
