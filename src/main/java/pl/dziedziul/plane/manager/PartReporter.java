package pl.dziedziul.plane.manager;

import pl.dziedziul.plane.manager.model.Part;
import pl.dziedziul.plane.manager.model.PartItem;
import pl.dziedziul.plane.manager.model.Plane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2015-02-08.
 */
public class PartReporter {
    static final int PLANE_QUANTITY = 1;

    public void printReport(Plane plane) {
        System.out.println("======================");
        System.out.println("Report: ");
        Map<Part, Integer> partQuantities = new HashMap<>();

        partQuantities.put(plane, PLANE_QUANTITY);
        calc(plane.getPartItems(), partQuantities, PLANE_QUANTITY);
        for (Map.Entry<Part, Integer> e : partQuantities.entrySet()) {
            System.out.println(e.getKey().getName() + ": " + e.getValue());
        }
    }

    private void calc(List<PartItem> partItems, Map<Part, Integer> partQuantities, int parentQuantity) {
        for (PartItem pi : partItems) {
            final Part part = pi.getPart();
            final int quantityToAdd = parentQuantity * pi.getQuantity();

            partQuantities.put(part, quantityToAdd + partQuantities.getOrDefault(part, 0));

            calc(pi.getPart().getPartItems(), partQuantities, quantityToAdd);
        }
    }
}
