import java.util.*;

public class Stand {
    private int location;
    private Farmer farmer;
    private List<ProduceItem> inventory = new ArrayList<>();

    public Stand(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public void assignFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void addOrUpdateProduce(Produce produce, int qty) {
        for (ProduceItem item : inventory) {
            if (item.getProduce().equals(produce)) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        inventory.add(new ProduceItem(produce, qty));
    }

    public List<ProduceItem> getInventory() {
        return inventory;
    }

    public ProduceItem getProduceItem(String name) {
        for (ProduceItem item : inventory) {
            if (item.getProduce().getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }
}