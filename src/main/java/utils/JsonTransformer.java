package utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by estef on 11/02/2017.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
