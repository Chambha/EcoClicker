
/**
 * Class to manage increase and decrease of pollution
 *
 * Harvey Chamberlain
 * 3/7/2025
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
        
        //prevents pollution going into negative amounts
        if (pollution <= 0){
            pollution = 0;
        }
    }
    
    public void increasePollution(int amount){
        pollution += amount;
    }
}
