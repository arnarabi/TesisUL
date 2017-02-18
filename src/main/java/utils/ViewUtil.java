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

public class ViewUtil {
    private static VelocityTemplateEngine engine;

    public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.putIfAbsent("titulo", "¿Por qué Alessandra estará loca?");
        List<Feedback> feedback = new ArrayList<>();
        model.putIfAbsent("feedback", feedback);
        model.putIfAbsent("estado", "Incompleto");
        List<Registro> registros = new ArrayList<>();
        registros.add(new Registro(1, "Ma-7pm", "Se reviso las encuestas", true, true));
        model.putIfAbsent("registros", registros);
        List<Asesor> asesores = new ArrayList<>();
        asesores.add(new Asesor(1, "Hernán Quintana", "Martes", "11:00 - 12:30"));
        asesores.add(new Asesor(2, "Julio Padilla", "Lunes", "14:30 - 15:30"));
        asesores.add(new Asesor(3, "Jorge Irey", "Jueves", "9:00 - 11:00"));
        model.putIfAbsent("asesores", asesores);
        return getRenderer().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine getRenderer() {
        if (engine == null) {
            engine = new VelocityTemplateEngine();
        }
        return engine;
    }
}
