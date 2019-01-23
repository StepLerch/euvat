package service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonService {

    final static Logger log = Logger.getLogger(JsonService.class);

    public static <T> T getObjectFromUrl(Class<T> clazz, String urlString){
        URL url = null;
        T result;
        InputStreamReader reader = null;
        try {
            url = new URL(urlString);
            reader = new InputStreamReader(url.openStream());
            result = new Gson().fromJson(reader, clazz);
        } catch (MalformedURLException e) {
            log.error("Malformed url exception - invalid or missing protocol. Presented url: " + urlString);
            return null;
        } catch (IOException e) {
            log.error("IOException unable to read from presented url: " + urlString);
            return null;
        } catch (JsonSyntaxException | JsonIOException e){
            log.error("Unable to parse Json from presented url: " + urlString + " " + e.getMessage());
            return null;
        }
        if(result == null){
            log.warn("No object retrieved from url: " + urlString);
        }
        return result;
    }

}
