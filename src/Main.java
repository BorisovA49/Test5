import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объект Scanner
        int year = sc.nextInt(); // год начала работы

        if (year < GlobalConstants.START_YEAR || year >= GlobalConstants.END_YEAR) {
            throw new IllegalArgumentException("throws Exception…");
        }
        convenientFunction(year);
    }

    private static void convenientFunction(int year) {

        double withdrawalPercentage = 0; // процент изьятия взял в процентах, ноль потому что отсчет пойдет с 0,5 и а цикл стартует с + 0,5
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - GlobalConstants.START_YEAR]; // начальный капитал, 2002 означает год начала нашего года
            withdrawalPercentage += GlobalConstants.UPDATE_PROC; // Увеличение на округленный процент
            double withdrawalMoney = Constants.MOEX_RATE[year - GlobalConstants.START_YEAR] * withdrawalPercentage * 0.01; // процент изъятия в деньгах, 0.01 означает перевод в проценты
            if (veryConvenientFunction(year, initialCapital, withdrawalMoney) < 0) { // Мы не переживаем этот год и используем предыдущий процент изъятия, вчиатем период 0.5
                System.out.println(withdrawalPercentage - GlobalConstants.UPDATE_PROC);
                break;
            }
        }
    }

    private static double veryConvenientFunction(int year, double initialCapital, double withdrawal) {
        for (int y = 0; y < GlobalConstants.END_YEAR - year; y++) {
            initialCapital -= withdrawal;
            initialCapital *= (Constants.MOEX_RATE[year - GlobalConstants.START_YEAR + y + 1] / Constants.MOEX_RATE[year - GlobalConstants.START_YEAR + y]);
            withdrawal = withdrawal * (GlobalConstants.GENERAL_FORM_INFLATION + Constants.INFLATION_RATE[year - GlobalConstants.START_YEAR + y]) / GlobalConstants.GENERAL_FORM; // расчет инфляции по формуле
            // X = X *// (100 + инфляция)/100 где первое 100 требуется для измения инфляции в математически приятный вид, а 2 приводит число в используемую для задачи форму


        }
        return initialCapital;
    }
}

class GlobalConstants {
    public static final double UPDATE_PROC = 0.5; // процент округления
    public static final int START_YEAR = 2002; // начало лет по заданию(стартовая точка)
    public static final int END_YEAR = 2022;// Конец лет по заданию(конечняа точка)
    public static final int GENERAL_FORM_INFLATION = 100; // Приведение  инфляции с учетом зпт
    public static final int GENERAL_FORM = 100; // Приведение в вид с учетом умножений
}