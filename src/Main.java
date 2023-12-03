import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объект
        try {
            int year = sc.nextInt(); // год начала работы
            convenientFunction(year);
        } catch (Exception exception) {
            System.out.println("throws exception...");
        }
        //System.out.println("(─‿‿─)");//Я думаю что именно для этой зазписи вы меня  попросили ввести искдючения в виде try и catch
    }

    private static void convenientFunction(int year) {
        final double[] initialSum = Constants.MOEX_RATE; // выдем значение из класса Constants
        final double[] inflation = Constants.INFLATION_RATE; // выдем значение из класса Constants
        float seizurePercentage = 0; // процент изъятия в процентах
        while (true) {
            double initialCapital = initialSum[year - 2002];
            seizurePercentage += 0.5;
            double cash = initialSum[year - 2002] * seizurePercentage * 0.01; // процент изъятия в деньгах (бумажных)
            for (int y = 0; y < 2022 - year; y++) {
                initialCapital -= cash;
                initialCapital = initialCapital * (initialSum[year - 2002 + y + 1] / initialSum[year - 2002 + y]);
                cash = cash * (100 + inflation[year - 2002 + y]) / 100;
            }
            if (initialCapital < 0) {
                System.out.println(seizurePercentage - 0.5);

                break;

            }
            if (seizurePercentage > 100) {
                System.out.println(seizurePercentage);
                break;
            }
        }
    }
}


