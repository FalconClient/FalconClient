package ir.albino.client.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
@UtilityClass
public class MathUtils {
    @Getter
    private final Random random = new Random();

    public static double easeInOutQuad(double x, int step) {
        return x < 0.5D ? 2.0D * x * x : 1.0D - Math.pow(-2.0D * x + 2.0D, step) / 2.0D;
    }

    public int randInt(int min, int max) {
        return MathUtils.randInt(max) + min;
    }

    public boolean randBool() {
        return MathUtils.getRandom().nextBoolean();
    }

    public double randDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public int randInt(int max) {
        return MathUtils.getRandom().nextInt(max);
    }

    public long randLong(long min, long max) {
        return MathUtils.getRandom().nextLong() * (max - min) + min;
    }

    public long randLong(long max) {
        return MathUtils.randLong(0, max);
    }

    public double distance(float x, float y, float x1, float y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public double locationDistance(double x, double z, double x1, double z1) {
        x = x1 - x;
        z = z1 - z;
        return Math.sqrt((x * x) + (z * z));
    }

    public int countMatches(String string, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(string);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    public <T> T randSelect(List<T> values) {
        return values.get(MathUtils.randInt(values.size()));
    }

    @SafeVarargs
    public <T> T randSelect(T... values) {
        return values[MathUtils.randInt(values.length)];
    }

    public short randShort(short min, short max) {
        return (short) Math.min(Math.max(MathUtils.randShort(max) + min, Short.MIN_VALUE), Short.MAX_VALUE);
    }

    public short randShort(short max) {
        return (short) MathUtils.randInt(max);
    }

    public double easeOutExpo(double x) {
        return x == 1.0D ? 1.0D : 1 - Math.pow(2.0D, -10 * x);
    }

    public double easeInExpo(double x) {
        return x == 0.0D ? 0.0D : Math.pow(2.0D, 10 * x - 10);
    }

    public float randFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    public float randFloat(float max) {
        return random.nextFloat() * max;
    }


    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public float calculateGaussianValue(float x, float sigma) {
        double PI = 3.141592653;
        double output = 1.0 / Math.sqrt(2.0 * PI * (sigma * sigma));
        return (float) (output * Math.exp(-(x * x) / (2.0 * (sigma * sigma))));
    }

    public String randString(int max) {
        return IntStream.range(0, max).map(i -> 97 + (int) (random.nextFloat() * (26)))
                .mapToObj(randomLimitedInt -> String.valueOf((char) randomLimitedInt)).collect(Collectors.joining());
    }

    public String randString(int min, int max) {
        return MathUtils.randString(MathUtils.randInt(min, max));
    }

    public String randChar(int min, int max) {
        return MathUtils.randChar(MathUtils.randInt(min, max));
    }

    public char randChar() {
        return (char) (random.nextFloat() * 26);
    }

    public String randChar(int max) {
        return IntStream.range(0, max).map(i -> MathUtils.randChar())
                .mapToObj(randomLimitedInt -> String.valueOf((char) randomLimitedInt)).collect(Collectors.joining());
    }

    public int clamp(int value, int min, int max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    public float clamp(float value, float min, float max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    public double clamp(double value, double min, double max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }
}
