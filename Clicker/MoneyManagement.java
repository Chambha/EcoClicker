/**
 * Class to manage money
 *
 * Harvey Chamberlain
 * 5/6/2025
 */
public class MoneyManagement
{
    private int money = 0;
    int clickMoney = 1;
    
    public void addClickMoney(){
        money += clickMoney;
    }   
    
    public int getMoney(){
        return money;
    }
}
