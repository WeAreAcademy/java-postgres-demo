import java.util.Random;

public class RandomNameGenerator {

    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    private static final char[] CONSONANTS = {
            'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'
    };

    private static final Random RANDOM = new Random();

    public static String generateRandomName() {
        return new StringBuilder()
                .append(randomConsonant())
                .append(randomVowel())
                .append(randomConsonant())
                .append(randomVowel())
                .append(randomConsonant())
                .toString();
    }

    private static char randomVowel() {
        return VOWELS[RANDOM.nextInt(VOWELS.length)];
    }

    private static char randomConsonant() {
        return CONSONANTS[RANDOM.nextInt(CONSONANTS.length)];
    }

}