package me.nekorainy.maigo.utils;

public enum MusicLevel {
    Basic,
    Advanced,
    Expert,
    Master,
    ReMaster;

    public static MusicLevel fromInt(int level) {
        switch (level) {
            case 0:
                return Basic;
            case 1:
                return Advanced;
            case 2:
                return Expert;
            case 3:
                return Master;
            case 4:
                return ReMaster;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }
}
