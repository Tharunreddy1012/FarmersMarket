import java.util.*;

public class ProduceManager {
    private Map<String, Produce> produceMap = new HashMap<>();

    // Add a new produce type, returns false if already exists or invalid
    public boolean addProduceType(String name) {
        if(name == null || name.trim().isEmpty()) return false;
        String key = name.trim().toLowerCase();
        if(produceMap.containsKey(key)) return false;
        produceMap.put(key, new Produce(name.trim()));
        return true;
    }

    // Retrieve produce type by name (case-insensitive)
    public Produce getProduceByName(String name) {
        if(name == null) return null;
        return produceMap.get(name.trim().toLowerCase());
    }

    // Get sorted list of produce names
    public List<String> getProduceNames() {
        List<String> names = new ArrayList<>();
        for(Produce p : produceMap.values()) {
            names.add(p.getName());
        }
        Collections.sort(names);
        return names;
    }
}