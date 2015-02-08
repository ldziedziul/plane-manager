package pl.dziedziul.plane.manager;

import com.google.common.base.Strings;
import pl.dziedziul.plane.manager.model.PartItem;
import pl.dziedziul.plane.manager.model.Plane;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created on 2015-02-08.
 */
public class PlaneRenderer {
    public void display(Plane plane) {
        System.out.println(" * " + plane.getName());
        displayItems(plane.getPartItems(), 1);
    }

    private void displayItems(List<PartItem> partItems, int level) {
        for (PartItem pi : partItems) {
            String name = pi.getPart().getName();
            int quantity = pi.getQuantity();
            out(MessageFormat.format(" - {0} (with quantity {1})", name, quantity), level * 4);
            displayItems(pi.getPart().getPartItems(), level + 1);
        }
    }

    private void out(String string, int padding) {
        System.out.println(Strings.padStart(string, padding + string.length(), ' '));

    }


}
