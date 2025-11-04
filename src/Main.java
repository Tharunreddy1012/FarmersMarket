import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FarmersMarket market = new FarmersMarket();
        ProduceManager produceManager = new ProduceManager();

        // Optional: Preload some produce types
        produceManager.addProduceType("Kiwi");
        produceManager.addProduceType("Muskmelon");
        produceManager.addProduceType("cabbage");
        produceManager.addProduceType("cauliflower");
        produceManager.addProduceType("onions");

        while (true) {
            System.out.println("\n==== Farmer's Market Menu ====");
            System.out.println("1. Add new stand");
            System.out.println("2. Assign farmer to stand");
            System.out.println("3. Add produce to stand");
            System.out.println("4. List all farmers and their produce");
            System.out.println("5. Search for produce");
            System.out.println("6. Buy produce");
            System.out.println("7. Add new produce type");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter stand location (number): ");
                    int loc = readInt(sc);
                    if (loc == Integer.MIN_VALUE) break;
                    market.addStand(loc);
                    System.out.println("Stand at location " + loc + " added.");
                    break;
                }
                case 2: {
                    System.out.print("Enter stand location: ");
                    int loc = readInt(sc);
                    if (loc == Integer.MIN_VALUE) break;
                    Stand st = findStandByLocation(market, loc);
                    if (st == null) {
                        System.out.println("Stand not found!");
                        break;
                    }
                    System.out.print("Enter farmer's name: ");
                    String fname = sc.nextLine().trim();
                    if (fname.isEmpty()) {
                        System.out.println("Farmer name cannot be empty!");
                        break;
                    }
                    st.assignFarmer(new Farmer(fname));
                    System.out.println("Farmer assigned.");
                    break;
                }
                case 3: {
                    System.out.print("Enter stand location: ");
                    int loc = readInt(sc);
                    if (loc == Integer.MIN_VALUE) break;
                    Stand st = findStandByLocation(market, loc);
                    if (st == null) {
                        System.out.println("Stand not found!");
                        break;
                    }
                    System.out.println("Available produce types: " + produceManager.getProduceNames());
                    System.out.print("Enter produce name to add: ");
                    String pname = sc.nextLine().trim();
                    Produce prod = produceManager.getProduceByName(pname);
                    if (prod == null) {
                        System.out.println("Unknown produce type. Please add it first using option 7.");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int qty = readInt(sc);
                    if (qty == Integer.MIN_VALUE || qty <= 0) {
                        System.out.println("Quantity must be a positive number.");
                        break;
                    }
                    st.addOrUpdateProduce(prod, qty);
                    System.out.println(qty + " " + prod.getName() + "(s) added to Stand " + loc + ".");
                    break;
                }
                case 4: {
                    for (Stand s : market.getStands()) {
                        System.out.print("Stand " + s.getLocation() + ": ");
                        if (s.getFarmer() != null)
                            System.out.println(s.getFarmer().getName());
                        else
                            System.out.println("(No farmer assigned)");
                        for (ProduceItem pi : s.getInventory())
                            System.out.println("    " + pi.getProduce().getName() + " (Qty: " + pi.getQuantity() + ")");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Enter produce name to search: ");
                    String pname = sc.nextLine().trim();
                    List<String> res = market.searchProduce(pname);
                    if (res.isEmpty())
                        System.out.println("No stands offer this produce.");
                    else
                        for (String r : res)
                            System.out.println(r);
                    break;
                }
                case 6: {
                    System.out.print("Enter produce name: ");
                    String pname = sc.nextLine().trim();
                    System.out.print("Enter stand location: ");
                    int loc = readInt(sc);
                    if (loc == Integer.MIN_VALUE) break;
                    System.out.print("Enter quantity: ");
                    int qty = readInt(sc);
                    if (qty == Integer.MIN_VALUE || qty <= 0) {
                        System.out.println("Quantity must be a positive number.");
                        break;
                    }
                    if (market.buyProduce(pname, loc, qty))
                        System.out.println("Purchase successful.");
                    else
                        System.out.println("Purchase failed! Not enough quantity or stand/produce not found.");
                    break;
                }
                case 7: {
                    System.out.print("Enter new produce type name to add: ");
                    String newProduceName = sc.nextLine().trim();
                    if (produceManager.addProduceType(newProduceName))
                        System.out.println(newProduceName + " produce type added.");
                    else
                        System.out.println("Produce type already exists or invalid name.");
                    break;
                }
                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static Stand findStandByLocation(FarmersMarket market, int location) {
        for (Stand s : market.getStands()) {
            if (s.getLocation() == location) return s;
        }
        return null;
    }

    private static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input.");
            return Integer.MIN_VALUE;
        }
    }
}
 
