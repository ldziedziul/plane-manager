package pl.dziedziul.plane.manager;

import org.apache.log4j.Logger;
import pl.dziedziul.plane.manager.model.Part;
import pl.dziedziul.plane.manager.model.PartNotFoundException;
import pl.dziedziul.plane.manager.model.Plane;
import pl.dziedziul.plane.manager.repository.PlaneRepository;
import pl.dziedziul.plane.manager.repository.RepositoryException;
import pl.dziedziul.plane.manager.repository.XmlPlaneRepository;

/**
 * Created on 2015-02-08.
 */
public class Application {
    static private final Logger LOG = Logger.getLogger(Application.class);
    static private final String FILENAME = "data/plane.xml";// or "data/plane.json"

    private PlaneRepository planeRepository = new XmlPlaneRepository(); //or new JsonPlaneRepository();
    private PlaneRenderer planeRenderer = new PlaneRenderer();
    private PartReporter partReporter = new PartReporter();

    public void start() {
        try {

            Plane plane = planeRepository.read(FILENAME);
            LOG.info("Plane loaded");
            display(plane);

            LOG.info("Removing bolts from engine");
            final Part engine = plane.getPartItems().get(0).getPart();
            engine.removePart(new Part("Bolt"));

            display(plane);

            LOG.info("Removing engine from plane");
            plane.removePart(new Part("Engine"));
            LOG.info("Displaying plane after removing engine");
            display(plane);

            LOG.info("Saving changed plane to file");
            planeRepository.write(plane, FILENAME);

        } catch (RepositoryException e) {
            LOG.error("Failed to read plane from file", e);
        } catch (PartNotFoundException e) {
            LOG.error("Failed to remove engine from plane", e);
        }
    }

    private void display(Plane plane) {
        planeRenderer.display(plane);
        partReporter.printReport(plane);
    }
}
