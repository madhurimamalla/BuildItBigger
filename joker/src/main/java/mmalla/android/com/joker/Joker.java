package mmalla.android.com.joker;

import java.util.Random;

public class Joker {

    private final String[] jokes = {
            "To be or not to be! That's the joke",
            "Knock! knock! Who's there? A running JVM",
            "And this one too!"
    };

    public String getJoke() {
        int index = new Random().nextInt(jokes.length);
        return jokes[index];
    }
}