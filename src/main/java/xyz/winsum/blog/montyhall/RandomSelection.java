package xyz.winsum.blog.montyhall;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class RandomSelection {

    private Random random = new Random();

    public void scheme1(int totalNumber, int optionNumber) {
        int count = 0;
        for (int i = 0; i < totalNumber; i++) {
            int rightOption = randSelExpect(optionNumber);
            int firstSelectedOption = randSelExpect(optionNumber);
            if (firstSelectedOption == rightOption) {
                count++;
            }
        }
        System.out.println(String.format("第一种方案：总共做对%d道题, 正确率为 : %6.5f", count, 1.0 * count / totalNumber));
    }

    public void scheme2(int totalNumber, int optionNumber) {
        int count = 0;
        for (int i = 0; i < totalNumber; i++) {
            int rightOption = randSelExpect(optionNumber);
            int wrongOption = randSelExpect(optionNumber, rightOption);
            int selectedOption = randSelExpect(optionNumber, wrongOption);
            if (selectedOption == rightOption) {
                count++;
            }
        }
        System.out.println(String.format("第二种方案：总共做对%d道题, 正确率为 : %6.5f", count, 1.0 * count / totalNumber));
    }

    public void scheme3(int totalNumber, int optionNumber) {
        int count = 0;
        for (int i = 0; i < totalNumber; i++) {
            int rightOption = randSelExpect(optionNumber);
            int firstSelectedOption = randSelExpect(optionNumber);
            int wrongSelect = randSelExpect(optionNumber, rightOption, firstSelectedOption);
            int secondSelectedOption = randSelExpect(optionNumber, wrongSelect, firstSelectedOption);
            if (secondSelectedOption == rightOption) {
                count++;
            }
        }
        System.out.println(String.format("第三种方案：总共做对%d道题, 正确率为 : %6.5f", count, 1.0 * count / totalNumber));
    }

    private int randSelExpect(int optionNumber, int... selected) {
        TreeSet<Integer> selectUnique = new TreeSet<>();
        Arrays.stream(selected).boxed().forEach(selectUnique::add);
        int index = random.nextInt(optionNumber - selectUnique.size());
        for (int item : selectUnique) {
            if (item <= index) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        RandomSelection randomSelection = new RandomSelection();
        randomSelection.scheme1(1000000, 4);
        randomSelection.scheme2(1000000, 4);
        randomSelection.scheme3(1000000, 4);
    }

}
