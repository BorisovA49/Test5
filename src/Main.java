import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объект Scanner
        int year = sc.nextInt(); // год начала работы

        if (year < Constant.START_YEAR || year >= Constant.END_YEAR) {
            throw new IllegalArgumentException("throws Exception…");
        }
        convenientFunction(year);
    }

    private static void convenientFunction(int year) {

        double withdrawalPercentage = 0; // процент изьятия взял в процентах
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - Constant.START_YEAR]; // начальный капитал, 2002 означает год начала нашего года
            withdrawalPercentage += Constant.UPDATE_PROC; // Увеличение на округленный процент
            double withdrawalMoney = Constants.MOEX_RATE[year - Constant.START_YEAR] * withdrawalPercentage * 0.01; // процент изъятия в деньгах, 0.01 означает перевод в проценты
            if (veryConvenientFunction(year, initialCapital, withdrawalMoney) < 0) { // Мы не переживаем этот год и используем предыдущий процент изъятия, вчиатем период 0.5
                System.out.println(withdrawalPercentage - Constant.UPDATE_PROC);
                break;
            }
        }
    }

    private static double veryConvenientFunction(int year, double initialCapital, double withdrawal) {
        for (int y = 0; y < Constant.END_YEAR - year; y++) {
            initialCapital -= withdrawal;
            initialCapital *= (Constants.MOEX_RATE[year - Constant.START_YEAR + y + 1] / Constants.MOEX_RATE[year - Constant.START_YEAR + y]);
            withdrawal = withdrawal * (100 + Constants.INFLATION_RATE[year - Constant.START_YEAR + y]) / 100; // расчет инфляции


        }
        return initialCapital;
    }


}
class Constant {
    public static final double UPDATE_PROC = 0.5;
    public static final int START_YEAR = 2002;
    public static final int END_YEAR = 2022;
}