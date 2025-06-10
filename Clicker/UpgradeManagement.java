
/**
 * Class to handle upgrades
 *
 * Harvey Chamberlain
 * 11/6/2025
 */
public class UpgradeManagement
{
    private MoneyManagement moneyManager;
    private int drillCost = 100;
    
    public UpgradeManagement(MoneyManagement moneyManager){
        this.moneyManager = moneyManager;
    }
    
    public void upgradeDrill(){
        if(moneyManager.getMoney() >= drillCost){
            moneyManager.decreaseMoney(drillCost);
            moneyManager.increaseClickMoney();
            System.out.println("Drill upgraded");
        } else { 
            System.out.println("Not enough money for this upgrade!");
        }
    }
    
    /*-- +Pollution upgrades --*/
    
    /*-- -Pollution upgrades --*/
}
