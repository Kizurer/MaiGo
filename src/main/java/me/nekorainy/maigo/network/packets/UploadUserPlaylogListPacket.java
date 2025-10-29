package me.nekorainy.maigo.network.packets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;


//TODO Class
public class UploadUserPlaylogListPacket implements Packet {
    private long uid, loginId;

    public UploadUserPlaylogListPacket(long uid, long loginId) {
        this.uid = uid;
        this.loginId = loginId;
    }

    @Override
    public String execute() throws Exception {
        // 时间 & 随机数
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        Random random = new Random();
        String userDataJson = "";//PacketSender.sendPacket(new GetUserDataPacket(uid));
        JsonObject root = new Gson().fromJson(userDataJson, JsonObject.class);
        JsonObject userData = root.getAsJsonObject("userData");

        //UserPlayLog
        JsonObject playlog = new JsonObject();
        playlog.addProperty("userId", 0);
        playlog.addProperty("orderId", 0);
        playlog.addProperty("playlogId", loginId);
        playlog.addProperty("version", 1051000);
//        playlog.addProperty("placeId", placeId);
//        playlog.addProperty("placeName", placeName);
        playlog.addProperty("loginDate", System.currentTimeMillis() / 1000);
        playlog.addProperty("playDate", LocalDate.now(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        playlog.addProperty("userPlayDate", LocalDateTime.now(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ".0");
        playlog.addProperty("type", 0);
        playlog.addProperty("musicId", 834);
        playlog.addProperty("level", 4);
        playlog.addProperty("trackNo", 1);
        playlog.addProperty("vsMode", 0);
        playlog.addProperty("vsUserName", "");
        playlog.addProperty("vsStatus", 0);
        playlog.addProperty("vsUserRating", 0);
        playlog.addProperty("vsUserAchievement", 0);
        playlog.addProperty("vsUserGradeRank", 0);
        playlog.addProperty("vsRank", 0);
        playlog.addProperty("playerNum", 1);
        playlog.addProperty("playedUserId1", 0);
        playlog.addProperty("playedUserName1", "");
        playlog.addProperty("playedMusicLevel1", 0);
        playlog.addProperty("playedUserId2", 0);
        playlog.addProperty("playedUserName2", "");
        playlog.addProperty("playedMusicLevel2", 0);
        playlog.addProperty("playedUserId3", 0);
        playlog.addProperty("playedUserName3", "");
        playlog.addProperty("playedMusicLevel3", 0);

        int[] charaSlot = new int[5];
        for (int i = 0; i < 5; i++) {
            charaSlot[i] = userData.getAsJsonArray("charaSlot").get(i).getAsInt();
        }

        for (int i = 0; i < 5; i++) {
            playlog.addProperty("characterId" + (i + 1), charaSlot[i]);
            playlog.addProperty("characterLevel" + (i + 1), random.nextInt(5501) + 1000); // 1000~6500
            playlog.addProperty("characterAwakening" + (i + 1), 5);
        }

        // 分数/统计
        playlog.addProperty("achievement", 91780);
        playlog.addProperty("deluxscore", 2102);
        playlog.addProperty("scoreRank", 4);
        playlog.addProperty("maxCombo", random.nextInt(150) + 300);   // 400~500
        playlog.addProperty("totalCombo", random.nextInt(201) + 700); // 700~900
        playlog.addProperty("maxSync", 0);
        playlog.addProperty("totalSync", 0);

        playlog.addProperty("tapCriticalPerfect", random.nextInt(201) + 200);
        playlog.addProperty("tapPerfect", random.nextInt(151) + 100);
        playlog.addProperty("tapGreat", random.nextInt(11));
        playlog.addProperty("tapGood", random.nextInt(11));
        playlog.addProperty("tapMiss", random.nextInt(11));

        playlog.addProperty("holdCriticalPerfect", random.nextInt(21) + 20);
        playlog.addProperty("holdPerfect", random.nextInt(16));
        playlog.addProperty("holdGreat", 0);
        playlog.addProperty("holdGood", 0);
        playlog.addProperty("holdMiss", 0);

        playlog.addProperty("slideCriticalPerfect", random.nextInt(27) + 34);
        playlog.addProperty("slidePerfect", 0);
        playlog.addProperty("slideGreat", 0);
        playlog.addProperty("slideGood", 0);
        playlog.addProperty("slideMiss", 0);

        playlog.addProperty("touchCriticalPerfect", random.nextInt(51) + 20);
        playlog.addProperty("touchPerfect", 0);
        playlog.addProperty("touchGreat", 0);
        playlog.addProperty("touchGood", 0);
        playlog.addProperty("touchMiss", 0);

        playlog.addProperty("breakCriticalPerfect", random.nextInt(23) + 8);
        playlog.addProperty("breakPerfect", random.nextInt(4) + 7);
        playlog.addProperty("breakGreat", 0);
        playlog.addProperty("breakGood", 0);
        playlog.addProperty("breakMiss", 0);

        playlog.addProperty("isTap", true);
        playlog.addProperty("isHold", true);
        playlog.addProperty("isSlide", true);
        playlog.addProperty("isTouch", true);
        playlog.addProperty("isBreak", true);
        playlog.addProperty("isCriticalDisp", true);
        playlog.addProperty("isFastLateDisp", true);
        playlog.addProperty("fastCount", random.nextInt(11) + 20);
        playlog.addProperty("lateCount", random.nextInt(21) + 50);
        playlog.addProperty("isAchieveNewRecord", true);
        playlog.addProperty("isDeluxscoreNewRecord", true);
        playlog.addProperty("comboStatus", 0);
        playlog.addProperty("syncStatus", 0);
        playlog.addProperty("isClear", true);

        int playerRating = userData.get("playerRating").getAsInt();

        playlog.addProperty("beforeRating", playerRating);
        playlog.addProperty("afterRating", 29999);
        playlog.addProperty("beforeGrade", 0);
        playlog.addProperty("afterGrade", 0);
        playlog.addProperty("afterGradeRank", 2);
        playlog.addProperty("beforeDeluxRating", playerRating);
        playlog.addProperty("afterDeluxRating", 29999);

        playlog.addProperty("isPlayTutorial", false);
        playlog.addProperty("isEventMode", false);
        playlog.addProperty("isFreedomMode", false);
        playlog.addProperty("playMode", 0);
        playlog.addProperty("isNewFree", false);
        playlog.addProperty("trialPlayAchievement", -1);
        playlog.addProperty("extNum1", 0);
        playlog.addProperty("extNum2", 0);
        playlog.addProperty("extNum4", 3023);
        playlog.addProperty("extBool1", false);

        JsonObject data = new JsonObject();
        data.addProperty("userId", uid);
        data.add("userPlaylog", playlog);

        return ShitPost.useApi(data.toString(), "UploadUserPlaylogListApi", uid);
    }
}
