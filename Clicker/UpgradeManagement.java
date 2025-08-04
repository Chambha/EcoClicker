    
/**
 * Class to handle upgrades
 *
 * Harvey Chamberlain
 * 30/7/2025
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
    private int numDrills = 0;
    
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
    
    //Research lab upgrades
    private int researchLabs = 0;
    private int researchLabCost = 100000;
    private int researchLabIncome = 200;
    private Timer researchLabTimer;
    
    //plant tree
    private int trees = 0;
    private int treeCost = 100;
    //private int treePollutionDecrease = 1;
    
    //solar panel
    private int solarPanels = 0;
    private int solarPanelCost = 500;
    private Timer solarPanelTimer;
    
    //wind turbine
    private int windTurbines = 0;
    private int windTurbineCost = 10000;
    private Timer windTurbineTimer;
    
    //hydroelectric dam
    private int dams = 0;
    private int damCost = 20000;
    private Timer damTimer;
    
    //Geothermal plant
    private int geoPlants = 0;
    private int geoPlantCost = 50000;
    private Timer geoPlantTimer;
    
    ///nuclear power
    private int reactors = 0;
    private int reactorCost = 100000;
    private Timer nuclearTimer;
    
    private window gameWindow;
    
    public int getMoneyPerSec(){
        int total = 0;
        
        total += miners * minerIncome;
        total += factories * factoryIncome;
        total += researchLabs * researchLabIncome;
        
        total += truckFleets * truckFleetIncome / 5; 
        
        total += oilDrills * oilDrillIncome / 2;
        
        total += chemicalPlants * chemicalPlantIncome / 2;
        
        return total;
    }
    
    public UpgradeManagement(MoneyManagement moneyManager, PollutionManagement pollutionManager, window gameWindow){
        this.moneyManager = moneyManager;
        this.pollutionManager = pollutionManager;
        this.gameWindow = gameWindow;
    }
    
    public void upgradeDrill(){
        if(moneyManager.getMoney() >= drillCost){
            moneyManager.decreaseMoney(drillCost);
            moneyManager.increaseClickMoney();
            
            numDrills++;
            System.out.println("Drill upgraded. Current drill level: " + numDrills);
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
                        pollutionManager.increasePollution(1 * miners); //Adds one pollution per second
                        gameWindow.winCondition();
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
                        pollutionManager.increasePollution(5 * factories); //Adds 5 pollution per second per factory
                        gameWindow.winCondition();
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
                        pollutionManager.increasePollution(10 * oilDrills); //Adds 10 pollution per second
                        gameWindow.winCondition();
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
                        pollutionManager.increasePollution(5 * truckFleets); //Adds 10 pollution per second
                        gameWindow.winCondition();
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
                chemicalPlantTimer = new Timer(2000, new ActionListener(){ //Adds money every 2000ms
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(chemicalPlants * chemicalPlantIncome);
                        pollutionManager.increasePollution(10 * chemicalPlants); //Adds 10 pollution every 2 second
                        gameWindow.winCondition();
                    }
                });
                chemicalPlantTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyResearchLab(){
        if(moneyManager.getMoney() >= researchLabCost){
            researchLabs++;
            moneyManager.decreaseMoney(researchLabCost); //decrease money by factory cost
            System.out.println("Research lab purchaed... upgrading tech... total labs: " + researchLabs);
            
            if (researchLabTimer == null){
                researchLabTimer = new Timer(1000, new ActionListener(){ //Adds money every 1000ms
                    public void actionPerformed(ActionEvent e){
                        moneyManager.increaseMoney(researchLabs * researchLabIncome);
                        pollutionManager.increasePollution(15 * researchLabs); //Adds 15 pollution per second
                        gameWindow.winCondition();
                    }
                });
                researchLabTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    /*-- Decrease pollution upgrades --*/
    public void plantTree(){
        if(moneyManager.getMoney() >= treeCost){
            trees++;
            moneyManager.decreaseMoney(treeCost);
            System.out.println("Tree planted. Total trees planted: " + trees);
            pollutionManager.decreasePollution(1); //decrease pollution by 1 per tree
            
        } else {
            System.out.println("Not enough money to plant a tree");
        }
    }
    
    public void buySolarPanel(){
        if(moneyManager.getMoney() >= solarPanelCost){
            solarPanels++;
            moneyManager.decreaseMoney(solarPanelCost); //decrease money by factory cost
            System.out.println("Solar panel purchased. Total solar panels: " + solarPanels);
            
            if (solarPanelTimer == null){
                solarPanelTimer = new Timer(2000, new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        pollutionManager.decreasePollution(1 * solarPanels); //removes 1 pollution every 2000ms
                    }
                });
                solarPanelTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyWindTurbine(){
        if(moneyManager.getMoney() >= windTurbineCost){
            windTurbines++;
            moneyManager.decreaseMoney(windTurbineCost); //decrease money by factory cost
            System.out.println("Wind turbine purchased. Total turbines: " + windTurbines);
            
            if (windTurbineTimer == null){
                windTurbineTimer = new Timer(5000, new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        pollutionManager.decreasePollution(2 * windTurbines); 
                        //removes 20 pollution every 5000ms per wind turbine
                    }
                });
                windTurbineTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyDam(){
        if(moneyManager.getMoney() >= damCost){
            dams++;
            moneyManager.decreaseMoney(damCost); //decrease money by dam cost
            System.out.println("Hydroelectic dam purchased. Total dams: " + dams);
            
            if (damTimer == null){
                damTimer = new Timer(2000, new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        pollutionManager.decreasePollution(10 * dams); 
                        //removes 10 pollution every 2000ms per dam
                    }
                });
                damTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyGeoPlant(){
        if(moneyManager.getMoney() >= geoPlantCost){
            geoPlants++;
            moneyManager.decreaseMoney(geoPlantCost); //decrease money by geoPlant cost
            System.out.println("Geothermal plant purchased. Total plants: " + geoPlants);
            
            if (geoPlantTimer == null){
                geoPlantTimer = new Timer(1000, new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        pollutionManager.decreasePollution(15 * geoPlants); 
                        //removes 15 pollution every 1000ms per geo plant
                    }
                });
                geoPlantTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
    
    public void buyReactor(){
        if(moneyManager.getMoney() >= reactorCost){
            reactors++;
            moneyManager.decreaseMoney(reactorCost); //decrease money by Reacor cost
            System.out.println("Nuclear reactor purchased, good job for keeping the environment clean. Total reactors: " + reactors);
            
            if (nuclearTimer == null){
                nuclearTimer = new Timer(1000, new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        pollutionManager.decreasePollution(35 * reactors); 
                        //removes 35 pollution every 1000ms per reactor
                    }
                });
                nuclearTimer.start();
            }
        } else { 
            System.out.println("Not enough money for this upgrade");
        }
    }
}
