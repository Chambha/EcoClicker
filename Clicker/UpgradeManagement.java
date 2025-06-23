    
/**
 * Class to handle upgrades
 *
 * Harvey Chamberlain
 * 23/6/2025
 */

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class UpgradeManagement
{
    private MoneyManagement moneyManager;
    private PollutionManagement pollutionManager;
    private int drillCost = 100;
    
    private Timer incomeTimer;
    
    //miner upgrade
    private int miners = 0; 
    private int minerCost = 1000;
    private int minerIncome = 1;
    private Timer minerTimer;
    
    //truck fleet upgrade
    private int truckFleets = 0;
    private int truckFleetCost = 5000;
    private int truckFleetIncome = 30;
    private Timer truckFleetTimer;
    
    //factory upgrade
    private int factories = 0;
    private int factoryCost = 10000;
    private int factoryIncome = 10;
    private Timer factoryTimer;
    
    //drill oil upgrade
    private int oilDrills = 0;
    private int oilDrillCost = 15000;
    private int oilDrillIncome = 50;
    private Timer oilDrillTimer;
    
    //chemical plant upgrade
    private int chemicalPlants = 0;
    private int chemicalPlantCost = 20000;
    private int chemicalPlantIncome = 100;
    private Timer chemicalPlantTimer;
    
    public UpgradeManagement(MoneyManagement moneyManager, PollutionManagement pollutionManager){
        this.moneyManager = moneyManager;
        this.pollutionManager = pollutionManager;
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
    
    /*-- Increase pollution upgrades --*/
    public void employMiner(){
        if(moneyManager.getMoney() >= minerCost){
            miners++;
            moneyManager.decreaseMoney(minerCost);
            System.out.println("Miner hired. Total employees: " + miners);
            
            if (minerTimer == null){
                minerTimer = new Timer(1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(miners * minerIncome); //Adds 1 money per second per miner
                        pollutionManager.increasePollution(miners); //Adds one pollution per second
                    }
                });
                minerTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyFactory(){
        if(moneyManager.getMoney() >= factoryCost){
            factories++;
            moneyManager.decreaseMoney(factoryCost); //decrease money by factory cost
            System.out.println("Factory purchased. Total factories: " + factories);
            
            if (factoryTimer == null){
                factoryTimer = new Timer(1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(factories * factoryIncome);
                        pollutionManager.increasePollution(5); //Adds 5 pollution per second
                    }
                });
                factoryTimer.start();
            }   
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyOilDrill(){
        if(moneyManager.getMoney() >= oilDrillCost){
            oilDrills++;
            moneyManager.decreaseMoney(oilDrillCost); //decrease money by factory cost
            System.out.println("Oil drilled. Total oil drills: " + oilDrills);
            
            if (oilDrillTimer == null){
                oilDrillTimer = new Timer(2000, new ActionListener(){ //Adds money every 2000ms
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(oilDrills * oilDrillIncome);
                        pollutionManager.increasePollution(10); //Adds 10 pollution per second
                    }
                });
                oilDrillTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyTruckFleet(){
        if(moneyManager.getMoney() >= truckFleetCost){
            truckFleets++;
            moneyManager.decreaseMoney(truckFleetCost); //decrease money by factory cost
            System.out.println("Truck fleet purchased. Total truck fleets: " + truckFleets);
            
            if (truckFleetTimer == null){
                truckFleetTimer = new Timer(5000, new ActionListener(){ //Adds money every 5000ms
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(truckFleets * truckFleetIncome);
                        pollutionManager.increasePollution(5); //Adds 10 pollution per second
                    }
                });
                truckFleetTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyChemicalPlant(){
        if(moneyManager.getMoney() >= chemicalPlantCost){
            chemicalPlants++;
            moneyManager.decreaseMoney(chemicalPlantCost); //decrease money by factory cost
            System.out.println("Chemical plant purchased. Total chemical plants: " + chemicalPlants);
            
            if (chemicalPlantTimer == null){
                chemicalPlantTimer = new Timer(2000, new ActionListener(){ //Adds money every 5000ms
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(chemicalPlants * chemicalPlantIncome);
                        pollutionManager.increasePollution(10); //Adds 10 pollution per second
                    }
                });
                chemicalPlantTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    /*-- Decrease pollution upgrades --*/
}
