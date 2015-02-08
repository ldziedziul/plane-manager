package pl.dziedziul.plane.manager.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import pl.dziedziul.plane.manager.model.Plane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Created on 2015-02-08.
 */
public class PlaneRepository {

    private final Gson gson;

    public PlaneRepository() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(Set.class, new SetSerializer())
                .setPrettyPrinting();
        gson = builder.create();
    }

    /**
     * Read plane object from json file of given filename
     * @param filename file to read from
     * @return Plane object
     * @throws RepositoryException if there were some problems with reading file
     */
    public Plane read(String filename) throws RepositoryException {
        try {
            return gson.fromJson(new FileReader(filename), Plane.class);
        } catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
            throw new RepositoryException("Failed to read plane from file " + filename, e);
        }
    }

    public void write(Plane plane, String filename) throws RepositoryException {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(plane, writer);
        } catch (IOException e) {
            throw new RepositoryException("Failed to write plane to file " + filename, e);
        }

    }
}
