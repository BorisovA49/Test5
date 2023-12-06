import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объектвs
            int year = sc.nextInt(); // год начала работы
            exceptions(year);
    }
    private static float exceptions(int year) {
        float a = 0; // еще одна не нужная функция
        if (year >= 2002 && year < 2022) {
            a = convenientFunction(year);
        }
        else {
            System.out.println("throws exception...");
        }
        return a;
    }
        private static float convenientFunction(int year) {
        float seizurePercentage = 0; // процент изъятия в процентах ключ
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - 2002]; // начальный капитал нельзя избваться, переменная будет изменятсья
            seizurePercentage += 0.5; // Прибавка к капиталу -
            double cash = Constants.MOEX_RATE[year - 2002] * seizurePercentage * 0.01; // процент изъятия в деньгах (бумажных)
            for (int y = 0; y < 2022 - year; y++) {
                initialCapital -= cash;
                initialCapital = initialCapital * (Constants.MOEX_RATE[year - 2002 + y + 1] / Constants.MOEX_RATE[year - 2002 + y]);
                cash = cash * (100 + Constants.INFLATION_RATE[year - 2002 + y]) / 100;
            }
            if (initialCapital < 0) {
                System.out.println(seizurePercentage - 0.5);
                break;
            }
        }
            return seizurePercentage;
        }
}


