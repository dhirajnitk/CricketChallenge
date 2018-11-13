package  com.dhiraj.GeekTrust;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

enum Teams{
    Lengaburu,Enchai;

};
enum TossType{
    BOWLS,BATS;
}

public class TossConditions {

    private Integer evaluateToss(Teams teams, String [] inputs){
        Integer type = 0;
        for(String input: inputs){
            WeatherType [] weatherTypes = WeatherType.values();
            for(WeatherType weatherType : weatherTypes){
                if(weatherType.toString().equalsIgnoreCase(input)){
                    if(tossWeatherMatix.containsKey(new AbstractMap.SimpleEntry<>(teams, weatherType)))
                       type = tossWeatherMatix.get(new AbstractMap.SimpleEntry<>(teams, weatherType)).ordinal();
                    break;

                }
            }

            DayNightType [] dayNightTypes = DayNightType.values();
            for(DayNightType dayNightType : dayNightTypes){
                if(dayNightType.toString().equalsIgnoreCase(input)){
                    if(tossDayMatrix.containsKey(new AbstractMap.SimpleEntry<>(teams, dayNightType)))
                        type |= tossDayMatrix.get(new AbstractMap.SimpleEntry<>(teams, dayNightType)).ordinal();
                    break;
                }
            }
        }
        return type;
    }
    enum WeatherType {
        CLEAR, CLOUDY;
    }
    enum DayNightType {
        DAY, NIGHT;
    }
    private static TossConditions instance = new TossConditions();

    private static Map<AbstractMap.SimpleEntry<Teams,WeatherType>,TossType> tossWeatherMatix;
    private static Map<AbstractMap.SimpleEntry<Teams, DayNightType>,TossType> tossDayMatrix;
    private static void buildMap(){
        tossWeatherMatix = new HashMap<AbstractMap.SimpleEntry<Teams, WeatherType>, TossType>();
        tossDayMatrix = new HashMap<AbstractMap.SimpleEntry<Teams, DayNightType>, TossType>();
        tossWeatherMatix.put(new AbstractMap.SimpleEntry<>(Teams.Lengaburu, WeatherType.CLEAR),TossType.BATS);
        tossWeatherMatix.put(new AbstractMap.SimpleEntry<>(Teams.Lengaburu, WeatherType.CLOUDY),TossType.BOWLS);
        tossWeatherMatix.put(new AbstractMap.SimpleEntry<>(Teams.Enchai, WeatherType.CLEAR),TossType.BOWLS);
        tossWeatherMatix.put(new AbstractMap.SimpleEntry<>(Teams.Enchai, WeatherType.CLOUDY),TossType.BATS);

        tossDayMatrix.put(new AbstractMap.SimpleEntry<>(Teams.Lengaburu, DayNightType.DAY),TossType.BATS);
        tossDayMatrix.put(new AbstractMap.SimpleEntry<>(Teams.Lengaburu, DayNightType.NIGHT),TossType.BOWLS);
        tossDayMatrix.put(new AbstractMap.SimpleEntry<>(Teams.Enchai, DayNightType.DAY),TossType.BOWLS);
        tossDayMatrix.put(new AbstractMap.SimpleEntry<>(Teams.Enchai, DayNightType.NIGHT),TossType.BATS);

    }
    static TossConditions getInstance(){
        if(tossWeatherMatix == null)
            buildMap();
        return instance;
    }

    String getTossResult(Teams teams, String ... inputs){
        String outputFormat = "%s wins toss and %s";
        int tossResult = evaluateToss(teams, inputs);
        return String.format(outputFormat, teams.toString(),  TossType.values()[tossResult].name().toLowerCase());
    }

}
