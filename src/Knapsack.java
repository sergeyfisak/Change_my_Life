import java.util.ArrayList;
import java.util.Arrays;


public class Knapsack {
    private int _carrying;
    private int[][] _table;
    private ArrayList<Item> _items;
    private ArrayList<Item> _answers;
    private int _totalPrice;

    public Knapsack(int carrying, ArrayList<Item> items){
        if(carrying < 0)
            throw new IllegalArgumentException("carrying =< 0");
        if(items == null)
            throw new IllegalArgumentException("items is null");

        _carrying = carrying;
        _items = items;
        _table = new int[items.size()+1][carrying+1];
        _answers = new ArrayList<>();
    }


    private void resolveDynamic() {
        for(int i = 0; i < _carrying; i++) {
            _table[0][i] = 0;
        }
        for(int i = 0; i < _items.size(); i++) {
            _table[i][0] = 0;
        }

        for(int i = 1; i <= _items.size(); i++) {
            for(int w = 1; w <= _carrying; w++) {
                if(w >= getItem(i).getWeigth()){
                    _table[i][w] = Math.max(_table[i-1][w],  _table[i-1][w - getItem(i).getWeigth()] + getItem(i).getPrice());
                }
                else {
                    _table[i][w] = _table[i-1][w];
                }
            }
        }
    }

    private void findAnsw(int i, int w) {
        if (_table[i][w] == 0){
            return;
        }

        if (_table[i-1][w] == _table[i][w]){
            findAnsw(i-1, w);
        }
        else{
            _answers.add(getItem(i));
            findAnsw(i-1, w-getItem(i).getWeigth());

        }

    }
    
    private Item getItem(int index) {
       return _items.get(index-1);
    }

    public ArrayList<Item> calcAnsw() {
        resolveDynamic();
        _totalPrice = _table[_items.size()][_carrying];
        findAnsw(_items.size(), _carrying);
               return _answers;
    }

    public int getTotalPrice(){
        return _totalPrice;
    }

    public void printTable(){
        for(int[] row : _table) {
            System.out.println(Arrays.toString(row));
        }
    }
}
