package finalproject.mu3620.patterns;

import finalproject.mu3620.random.WeatherAPI;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Pattern1 {

    private String PATTERN_NAME = getClass().getName();

    ChordProgression cp = new ChordProgression("I IV V");
    Rhythm rhythm = new Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("O.o.O.o.O.o.O.o.");
//            .addLayer("..S...S...S...S.")
//            .addLayer("````````````````")
//            .addLayer("...............+");

    public void displayChords() {
        Chord[] chords = cp.setKey("C").getChords();
        for (Chord chord : chords) {
            System.out.print("Chord " + chord + " has these notes: ");
            Note[] notes = chord.getNotes();
            for (Note note : notes) {
                System.out.print(note + " ");
            }
            System.out.println();
        }
    }

    public Pattern getPattern() {
        System.out.println("PLAYING: " + PATTERN_NAME);

        WeatherAPI weatherAPI = WeatherAPI.getInstance();

        displayChords();

        return rhythm.getPattern()
               // Repeat main rhythm based on getCount()
                // $ points to the nth chord while 'V' is staccato, 'R' is rest
               .add(
                   cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0")
                   .eachChordAs("V0 $0s $1s $2s Rs V1 $!q")
               )
               .repeat(2)
               .setTempo(weatherAPI.getCount());
    }

}
