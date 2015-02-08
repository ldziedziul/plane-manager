package pl.dziedziul.plane.manager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 2015-02-08.
 */
@XmlRootElement
public class Plane extends Part {

    public Plane() {
        super("Plane");
    }
}
