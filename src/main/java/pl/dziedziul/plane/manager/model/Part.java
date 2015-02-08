package pl.dziedziul.plane.manager.model;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2015-02-08.
 */
public class Part {
    private static final int ANY_QUANTITY = 1;
    private String name;
    Set<PartItem> partItems = new LinkedHashSet<>();

    public Part(String name) {
        this.name = name;
    }

    public void addPart(Part part, int quantity) {
        if (!partItems.add(new PartItem(part, quantity))) {
            throw new IllegalArgumentException("Part " + part.getName() + " already added");
        }
    }

    public void removePart(Part part) throws PartNotFoundException {
        //equals is checking only part.name
        if (!partItems.remove(new PartItem(part, ANY_QUANTITY))) {
            throw new PartNotFoundException("Removing part failed. Part " + part.name + " not found");
        }
    }

    /**
     *
     * @return part items sorted by quantity
     */
    public List<PartItem> getPartItems() {
        if (partItems != null) {
            return Ordering.natural()
                    .onResultOf(new Function<PartItem, Integer>() {
                        @Override
                        public Integer apply(PartItem item) {
                            return item.getQuantity();
                        }
                    })
                    .immutableSortedCopy(partItems);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;

        Part part = (Part) o;

        if (name != null ? !name.equals(part.name) : part.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
