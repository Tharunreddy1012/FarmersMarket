import java.util.*;

public class FarmersMarket {
    private List<Stand> stands = new ArrayList<>();

    public Stand addStand(int location) {
        Stand s = new Stand(location);
        stands.add(s);
        return s;
    }

    public List<Stand> getStands() {
        return stands;
    }

    public List<String> searchProduce(String name) {
        List<String> results = new ArrayList<>();
        for (Stand s : stands) {
            ProduceItem item = s.getProduceItem(name);
            if (item != null && item.getQuantity() > 0 && s.getFarmer() != null) {
                results.add(String.format("%s at Stand %d (Qty: %d)",
                        s.getFarmer().getName(), s.getLocation(), item.getQuantity()));
            }
        }
        return results;
    }

    public boolean buyProduce(String name, int location, int qty) {
        for (Stand s : stands) {
            if (s.getLocation() == location) {
                ProduceItem item = s.getProduceItem(name);
                if (item != null && item.getQuantity() >= qty) {
                    item.setQuantity(item.getQuantity() - qty);
                    return true;
                }
            }
        }
        return false;
    }
}