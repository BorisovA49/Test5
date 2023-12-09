import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объектв ss
        int year = sc.nextInt(); // год начала работы
        if (year <= 2001 || year >= 2022) {
            throw new ArrayIndexOutOfBoundsException("throws Exception…");
        }
        convenientFunction(year);
    }

    private static void convenientFunction(int year) {

        float withdrawalPercentage = 0; // процент изъятия в процентах ключ
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - 2002]; // начальный капитал нельзя избваться, переменная будет изменятсья
            withdrawalPercentage += 0.5; // Прибавка к капиталу -
            double withdrawal = Constants.MOEX_RATE[year - 2002] * withdrawalPercentage * 0.01; // процент изъятия в деньгах (бумажных)
            if (veryConvenientFunction(year, initialCapital, withdrawal) < 0) {
                System.out.println(withdrawalPercentage - 0.5);
                break;
            }
        }
    }

    private static double veryConvenientFunction(int year, double initialCapital, double withdrawal) {
        for (int y = 0; y < 2022 - year; y++) {
            initialCapital -= withdrawal;
            initialCapital *= (Constants.MOEX_RATE[year - 2002 + y + 1] / Constants.MOEX_RATE[year - 2002 + y]);
            withdrawal *= (100 + Constants.INFLATION_RATE[year - 2002 + y]) / 100;
        }
        return initialCapital;
    }
}


