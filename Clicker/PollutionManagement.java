
/**
 * Class to manage increase and decrease of pollution
 *
 * Harvey Chamberlain
 * 24/6/2025
 */
public class PollutionManagement
{
    private int pollution = 0;
    int clickPollution = 1;
    
    public void addClickPollution(){
        pollution += clickPollution;
    }   
    
    public int getPollution(){
        return pollution;
    }
    
    public void decreasePollution(int amount){
        pollution -= amount;
    }
    
    public void increasePollution(int amount){
        pollution += amount;
    }
}
