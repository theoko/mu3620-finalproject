package finalproject.mu3620.patterns;

import finalproject.mu3620.random.WeatherAPI;
import finalproject.mu3620.random.Wikipedia;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Pattern5 {
    private String PATTERN_NAME = getClass().getName();

    ChordProgression cp = new ChordProgression("I IV V");
    Rhythm rhythm = new Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("O.o.O.o.O.o.O.o.");

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

        Wikipedia wikipedia = new Wikipedia();
        String text = wikipedia.getText();
        if (text != null) {
            StringBuilder wikiChord = new StringBuilder();
            for (int i=0; i<text.length(); i++) {
                char c = text.charAt(i);
                System.out.println((int) c);
                String asciiStr = String.valueOf((int) c);
                for (int j=0; j<asciiStr.length(); j++) {
                    if (j < 3) {
                        wikiChord.append(j);
                    }
                    wikiChord.append(" ");
                }
            }
            return rhythm.getPattern()
                    // Repeat main rhythm based on getCount()
                    //.repeat(1)
                    // $ points to the nth chord while 'V' is staccato, 'R' is rest
                    .add(
                            cp.allChordsAs(wikiChord.toString())
                    )
                    .repeat(2)
                    .setTempo(weatherAPI.getCount());
        }

        return rhythm.getPattern()
                // Repeat main rhythm based on getCount()
                //.repeat(1)
                // $ points to the nth chord while 'V' is staccato, 'R' is rest
                .add(
                    cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0")
                )
                .repeat(2)
                .setTempo(weatherAPI.getCount());
    }
}
