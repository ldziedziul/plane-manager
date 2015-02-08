package pl.dziedziul.plane.manager.repository;

import pl.dziedziul.plane.manager.model.Part;
import pl.dziedziul.plane.manager.model.PartItem;
import pl.dziedziul.plane.manager.model.Plane;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created on 2015-02-08.
 */
public class XmlPlaneRepository implements PlaneRepository {

    private final Marshaller jaxbMarshaller;
    private final Unmarshaller jaxbUnmarshaller;

    public XmlPlaneRepository() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Plane.class, PartItem.class, Part.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to create repository", e);
        }

    }

    @Override
    public Plane read(String filename) throws RepositoryException {
        File file = new File(filename);
        try {
            return (Plane) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RepositoryException("Failed to read plane from file " + filename, e);
        }
    }

    @Override
    public void write(Plane plane, String filename) throws RepositoryException {
        File file = new File(filename);
        try {
            jaxbMarshaller.marshal(plane, file);
        } catch (JAXBException e) {
            throw new RepositoryException("Failed to write plane to file " + filename, e);
        }
    }
}