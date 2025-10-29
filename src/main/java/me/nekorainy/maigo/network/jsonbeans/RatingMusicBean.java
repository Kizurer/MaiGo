package me.nekorainy.maigo.network.jsonbeans;

import me.nekorainy.maigo.utils.MusicLevel;

public class RatingMusicBean {
    String musicName;
    MusicLevel level;
    int romVersion;
    int achievement;
    int musicId;

    public RatingMusicBean(String musicName, MusicLevel level, int romVersion, int achievement, int id) {
        this.musicName = musicName;
        this.level = level;
        this.romVersion = romVersion;
        this.achievement = achievement;
        this.musicId = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public MusicLevel getLevel() {
        return level;
    }

    public int getRomVersion() {
        return romVersion;
    }

    public int getAchievement() {
        return achievement;
    }

    public int getMusicId() {
        return musicId;
    }

    public String formatRatingSimple(long raw) {
        double v = raw / 10000.0;
        return String.format(java.util.Locale.US, "%.4f%%", v);
    }
}
