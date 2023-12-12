import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создали объект Scanner
        int year = sc.nextInt(); // год начала работы

        if (year < Constants.START_YEAR || year >= Constants.END_YEAR) {
            throw new IllegalArgumentException("throws Exception…");
        }
        convenientFunction(year);
    }

    private static void convenientFunction(int year) {

        double withdrawalPercentage = 0; // процент изьятия взял в процентах, ноль потому что отсчет пойдет с 0,5 и а цикл стартует с + 0,5
        while (true) {
            double initialCapital = Constants.MOEX_RATE[year - Constants.START_YEAR]; // начальный капитал, 2002 означает год начала нашего года
            withdrawalPercentage += Constants.UPDATE_PROC; // Увеличение на округленный процент
            double withdrawalMoney = Constants.MOEX_RATE[year - Constants.START_YEAR] * withdrawalPercentage * 0.01; // процент изъятия в деньгах, 0.01 означает перевод в проценты
            if (veryConvenientFunction(year, initialCapital, withdrawalMoney) < 0) { // Мы не переживаем этот год и используем предыдущий процент изъятия, вчиатем период 0.5
                System.out.println(withdrawalPercentage - Constants.UPDATE_PROC);
                break;
            }
        }
    }

    private static double veryConvenientFunction(int year, double initialCapital, double withdrawal) {
        for (int y = 0; y < Constants.END_YEAR - year; y++) {
            initialCapital -= withdrawal;
            initialCapital *= (Constants.MOEX_RATE[year - Constants.START_YEAR + y + 1] / Constants.MOEX_RATE[year - Constants.START_YEAR + y]);
            withdrawal = withdrawal * (Constants.GENERAL_FORM_INFLATION + Constants.INFLATION_RATE[year - Constants.START_YEAR + y]) / Constants.GENERAL_FORM_INFLATION; // расчет инфляции по формуле
            // X = X *// (100 + инфляция)/100 где первое 100 требуется для измения инфляции в математически приятный вид, а 2 приводит число в используемую для задачи форму, смотря сколько знаков мы имеем после зпт
            //


        }
        return initialCapital;
    }
}

