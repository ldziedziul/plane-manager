package pl.dziedziul.plane.manager.repository;

import org.junit.Test;
import pl.dziedziul.plane.manager.model.Part;
import pl.dziedziul.plane.manager.model.PartItem;
import pl.dziedziul.plane.manager.model.Plane;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaneRepositoryTest {

    private static String VALID_PLANE_FILENAME = "src/test/resources/data/valid-test-plane.json";

    @Test
    public void shouldWorkRead() throws Exception {
        //given
        PlaneRepository sut = new PlaneRepository();
        //when
        Plane plane = sut.read(VALID_PLANE_FILENAME);
        //then
        assertThat(plane.getPartItems()).hasSize(1);
        //checkin engine
        PartItem engineItem = plane.getPartItems().get(0);
        Part engine = engineItem.getPart();
        assertThat(engine.getName()).isEqualTo("Engine");
        assertThat(engineItem.getQuantity()).isEqualTo(1);

        //checking Air Intake
        PartItem airIntakeItem = engine.getPartItems().get(1);
        assertThat(airIntakeItem.getPart().getName()).isEqualTo("Air Intake");
        assertThat(airIntakeItem.getQuantity()).isEqualTo(2);

    }

    @Test
    public void shouldWritePlaneToFile() throws Exception {
        //given
        PlaneRepository sut = new PlaneRepository();
        Plane plane = createSamplePlane();
        //when
        String filename = "target/test-plane.json";
        sut.write(plane, filename);
        //then
        //shoud not throw exception and:

        assertThat(new File(filename))
                .exists()
                .canWrite()
                .isFile()
                .hasContentEqualTo(new File(VALID_PLANE_FILENAME));
    }

    @Test(expected = RepositoryException.class)
    public void shouldFailReadingInvalidPlaneFromFile() throws Exception {
        //given
        PlaneRepository sut = new PlaneRepository();
        //when
        String filename = "src/test/resources/data/invalid-test-plane.json";
        sut.read(filename);
        //then
        //shoud throw exception
    }

    private Plane createSamplePlane() {
        Plane plane = new Plane();
        Part engine = new Part("Engine");
        engine.addPart(new Part("FlyWheel"), 1);
        engine.addPart(new Part("Bolt"), 100);
        engine.addPart(new Part("Air Intake"), 2);
        plane.addPart(engine, 1);
        return plane;
    }
}