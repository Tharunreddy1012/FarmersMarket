public class ProduceItem {
    private Produce produce;
    private int quantity;

    public ProduceItem(Produce produce, int quantity) {
        this.produce = produce;
        this.quantity = quantity;
    }

    public Produce getProduce() {
        return produce;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}