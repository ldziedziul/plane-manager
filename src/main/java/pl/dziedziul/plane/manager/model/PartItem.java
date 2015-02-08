package pl.dziedziul.plane.manager.model;

/**
 * Created on 2015-02-08.
 */
public class PartItem{
    private Part part;
    private int quantity;

    public PartItem(Part part, int quantity) {
        this.part = part;
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be positive integer");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Part getPart() {
        return part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartItem)) return false;

        PartItem partItem = (PartItem) o;

        if (part != null ? !part.equals(partItem.part) : partItem.part != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return part != null ? part.hashCode() : 0;
    }
}
