import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String [] args){
        try {
            Scanner in = new Scanner(System.in);
            String firstString = in.nextLine();
            int carrying = convertToInt(firstString);
            String secondString = in.nextLine();
//            int carrying = 50;  // to test
//            String secondString = "стол/30/7000 шкаф/40/9000 пылесос/10/6000 кровать/40/10000";  // for test
//            String secondString = "стол/18/11000 шкаф/40/9000 пылесос/10/6000 кровать/4/10000";  // for test
            ArrayList<Item> items = createItems(secondString);
            Knapsack knapsack = new Knapsack(carrying, items);
            ArrayList<Item> res = knapsack.calcAnsw();
//            knapsack.printTable(); // for test
            int totalPrice = knapsack.getTotalPrice();
            printRes(res, totalPrice);
        }
        catch(Exception e){
            System.out.println("Something went wrong! Error message: " + e.getMessage());
        }
    }

    public static void printRes(ArrayList<Item> results, int totalPrice){
//        for (int i = results.size()-1; i >= 0; i--) {
        for (int i = 0; i <results.size(); i++) {
            System.out.print(results.get(i).getName() + " ");
        }
        System.out.println(totalPrice);
    }

    public static ArrayList<Item> createItems(String string) {
        String[] strItems = string.split(" ");
        ArrayList<Item> items = new ArrayList<Item>();
        for (String strItem : strItems){
            String[] itemsProp = strItem.split("/");
            String name = itemsProp[0];
            int weight = convertToInt(itemsProp[1]);
            int price = convertToInt(itemsProp[2]);
            Item item = new Item(name, weight, price);
            items.add(item);
        }
        return items;
    }

    public static int convertToInt(String string) {
        int res;
        try {
            res = Integer.parseInt(string);
            return res;
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("String is not Integer!");
        }
    }


}
