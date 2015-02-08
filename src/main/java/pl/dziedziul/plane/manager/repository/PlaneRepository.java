package pl.dziedziul.plane.manager.repository;

import pl.dziedziul.plane.manager.model.Plane;

/**
 * Created on 2015-02-08.
 */
public interface PlaneRepository {
    Plane read(String filename) throws RepositoryException;

    void write(Plane plane, String filename) throws RepositoryException;
}
