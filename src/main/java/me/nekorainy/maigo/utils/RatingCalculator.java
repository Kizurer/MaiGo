package me.nekorainy.maigo.utils;

public class RatingCalculator {

    public static class RaResult {
        public int ra;
        public String rate;

        public RaResult(int ra, String rate) {
            this.ra = ra;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "RaResult{ra=" + ra + ", rate='" + rate + "'}";
        }
    }

    /**
     * 计算底分和评价
     * @param ds 定数
     * @param achievement 成绩
     * @param onlyRate 是否只返回评级字符串
     * @param isRate 是否返回完整 (ra, 评级)
     * @return 根据模式返回 int、String 或 RaResult
     */
    private static Object computeRa(double ds, double achievement, boolean onlyRate, boolean isRate) {
        double baseRa;
        String rate;

        if (achievement < 50) {
            baseRa = 7.0; rate = "D";
        } else if (achievement < 60) {
            baseRa = 8.0; rate = "C";
        } else if (achievement < 70) {
            baseRa = 9.6; rate = "B";
        } else if (achievement < 75) {
            baseRa = 11.2; rate = "BB";
        } else if (achievement < 80) {
            baseRa = 12.0; rate = "BBB";
        } else if (achievement < 90) {
            baseRa = 13.6; rate = "A";
        } else if (achievement < 94) {
            baseRa = 15.2; rate = "AA";
        } else if (achievement < 97) {
            baseRa = 16.8; rate = "AAA";
        } else if (achievement < 98) {
            baseRa = 20.0; rate = "S";
        } else if (achievement < 99) {
            baseRa = 20.3; rate = "Sp";
        } else if (achievement < 99.5) {
            baseRa = 20.8; rate = "SS";
        } else if (achievement < 100) {
            baseRa = 21.1; rate = "SSp";
        } else if (achievement < 100.5) {
            baseRa = 21.6; rate = "SSS";
        } else {
            baseRa = 22.4; rate = "SSSp";
        }

        double capped = Math.min(100.5, achievement);
        int raValue = (int) Math.floor(ds * (capped / 100.0) * baseRa);

        if (isRate) {
            return new RaResult(raValue, rate);
        } else if (onlyRate) {
            return rate;
        } else {
            return raValue;
        }
    }

    // 便捷重载
    public static int computeRa(double ds, double achievement) {
        return (int) computeRa(ds, achievement, false, false);
    }

    public static String computeRaRate(double ds, double achievement) {
        return (String) computeRa(ds, achievement, true, false);
    }

    public static RaResult computeRaWithRate(double ds, double achievement) {
        return (RaResult) computeRa(ds, achievement, false, true);
    }
}
