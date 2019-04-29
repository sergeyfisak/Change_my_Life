public class Item
{
    private String _name;
    public String getName()
    {
        return _name;
    }

    private int _weigth;
    public int getWeigth()
    {
        return _weigth;
    }

    private int _price;
    public int getPrice()
    {
        return _price;
    }

    public Item(String name, int weigth, int price)
    {
        if(name.isEmpty())
            throw new IllegalArgumentException("name is empty");
        if(weigth < 0)
            throw new IllegalArgumentException("weigth =< 0");
        if(price < 0)
            throw new IllegalArgumentException("price =< 0");

        _name = name;
        _weigth = weigth;
        _price = price;
    }
}