package pl.dziedziul.plane.manager.repository;

import pl.dziedziul.plane.manager.model.Part;
import pl.dziedziul.plane.manager.model.Plane;

/**
 * Created on 2015-02-08.
 */
public class PlaneTestHelper {
    public Plane createSamplePlane() {
        Plane plane = new Plane();
        Part engine = new Part("Engine");
        engine.addPart(new Part("FlyWheel"), 1);
        engine.addPart(new Part("Bolt"), 100);
        engine.addPart(new Part("Air Intake"), 2);
        plane.addPart(engine, 1);
        return plane;
    }
}
