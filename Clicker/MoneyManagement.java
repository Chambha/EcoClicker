/**
 * Class to manage money
 *
 * Harvey Chamberlain
 * 6/8/2025
 */

public class MoneyManagement
{
    private int money = 0;
    
    // <!> NOTE FOR MARKERS <!>
    // Change this int to a higher value to progress through the game faster
    int clickMoney = 1;
    
    public void addClickMoney(){
        money += clickMoney;
    }   
    
    public int getMoney(){
        return money;
    }
    
    public void decreaseMoney(int amount){
        money -= amount;
    }
    
    public void increaseMoney(int amount){
        money += amount;
    }
    
    public void increaseClickMoney(){
        clickMoney += 1;
    }
}
