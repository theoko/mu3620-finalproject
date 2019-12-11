package finalproject.mu3620;

import finalproject.mu3620.patterns.*;
import finalproject.mu3620.random.Wikipedia;
import finalproject.mu3620.random.WeatherAPI;
import org.jfugue.player.Player;

public class Main {

        public static void main(String[] args) {

                // Initialize patterns
                Pattern1 pattern1 = new Pattern1();
                Pattern2 pattern2 = new Pattern2();
                Pattern3 pattern3 = new Pattern3();
                Pattern4 pattern4 = new Pattern4();
                Pattern5 pattern5 = new Pattern5();

                // Play patterns
                Player player = new Player();
                WeatherAPI weatherAPI = WeatherAPI.getInstance();
                for (int i=0; i<weatherAPI.getTemperature(); i++) {
                        player.play(
                                pattern5.getPattern(),
                                pattern1.getPattern(),
                                pattern2.getPattern(),
                                pattern3.getPattern(),
                                pattern4.getPattern(),
                                pattern1.getPattern()
                        );
                }

    }
}
