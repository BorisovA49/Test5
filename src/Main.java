import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объект Scanner
        int year = sc.nextInt(); // год начала работы
        if (year <= 2001 || year >= 2022) {
            throw new IllegalArgumentException("throws Exception…");
        }
        convenientFunction(year);
    }

    private static void convenientFunction(int year) {

        float withdrawalPercentage = 0; // процент изьятия взял в процентах
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - 2002]; // начальный капитал, 2002 означает год начала нашего года
            withdrawalPercentage += 0.5; // Увеличение на округленный процент
            double withdrawalMoney = Constants.MOEX_RATE[year - 2002] * withdrawalPercentage * 0.01; // процент изъятия в деньгах, 0.01 означает перевод в проценты
            if (veryConvenientFunction(year, initialCapital, withdrawalMoney) < 0) { // Мы не переживаем этот год и используем предыдущий процент изъятия, вчиатем период 0.5
                System.out.println(withdrawalPercentage - 0.5);
                break;
            }
        }
    }

    private static double veryConvenientFunction(int year, double initialCapital, double withdrawal) {
        for (int y = 0; y < 2022 - year; y++) // 2022 - year это  колличесво лет что мы высчитываем
            {
            initialCapital -= withdrawal;
            initialCapital *= (Constants.MOEX_RATE[year - 2002 + y + 1] / Constants.MOEX_RATE[year - 2002 + y]);
            withdrawal = withdrawal *  (100 + Constants.INFLATION_RATE[year - 2002 + y]) / 100; // расчет инфляции
        }
        return initialCapital;
    }
}


