package me.nekorainy.maigo.network.packets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

//Fuck you walhap tech.
public class UpsertAllUserDataPacket implements Packet {
    private final long timestamp, placeId, regionId, loginId, uid;
    private final String loginDate, placeName, regionName, clientId;
    private final JsonObject userdata, user_extend, user_option, user_rating, user_charge, user_activity;
    private final JsonObject root = new JsonObject();

    public UpsertAllUserDataPacket(long timestamp, long placeId, long regionId, long loginId, long uid, String loginDate, String placeName, String regionName, String clientId, JsonObject userdata, JsonObject user_extend, JsonObject user_option, JsonObject user_rating, JsonObject user_charge, JsonObject user_activity) {
        this.timestamp = timestamp;
        this.placeId = placeId;
        this.regionId = regionId;
        this.loginId = loginId;
        this.uid = uid;
        this.loginDate = loginDate;
        this.placeName = placeName;
        this.regionName = regionName;
        this.clientId = clientId;
        this.userdata = userdata;
        this.user_extend = user_extend;
        this.user_option = user_option;
        this.user_rating = user_rating;
        this.user_charge = user_charge;
        this.user_activity = user_activity;

        Gson gson = new Gson();

        // 顶层
        root.addProperty("userId", uid);
        root.addProperty("playlogId", loginId);
        root.addProperty("isEventMode", false);
        root.addProperty("isFreePlay", false);

        // upsertUserAll
        JsonObject upsertUserAll = new JsonObject();

        /* ======================== userData ======================== */
        JsonArray userDataArr = new JsonArray();
        JsonObject userData = new JsonObject();

        userData.addProperty("accessCode", "");
        userData.add("userName", userdata.getAsJsonObject("userData").get("userName"));
        userData.addProperty("isNetMember", 1);
        userData.add("point", userdata.getAsJsonObject("userData").get("point"));
        userData.add("totalPoint", userdata.getAsJsonObject("userData").get("totalPoint"));
        userData.add("iconId", userdata.getAsJsonObject("userData").get("iconId"));
        userData.add("plateId", userdata.getAsJsonObject("userData").get("plateId"));
        userData.add("titleId", userdata.getAsJsonObject("userData").get("titleId"));
        userData.add("partnerId", userdata.getAsJsonObject("userData").get("partnerId"));
        userData.add("frameId", userdata.getAsJsonObject("userData").get("frameId"));
        userData.add("selectMapId", userdata.getAsJsonObject("userData").get("selectMapId"));
        userData.add("totalAwake", userdata.getAsJsonObject("userData").get("totalAwake"));
        userData.add("gradeRating", userdata.getAsJsonObject("userData").get("gradeRating"));
        userData.add("musicRating", userdata.getAsJsonObject("userData").get("musicRating"));
        userData.add("playerRating", userdata.getAsJsonObject("userData").get("playerRating"));
        userData.add("highestRating", userdata.getAsJsonObject("userData").get("highestRating"));
        userData.add("gradeRank", userdata.getAsJsonObject("userData").get("gradeRank"));
        userData.add("classRank", userdata.getAsJsonObject("userData").get("classRank"));
        userData.add("courseRank", userdata.getAsJsonObject("userData").get("courseRank"));
        userData.add("charaSlot", userdata.getAsJsonObject("userData").get("charaSlot"));
        userData.add("charaLockSlot", userdata.getAsJsonObject("userData").get("charaLockSlot"));
        userData.add("contentBit", userdata.getAsJsonObject("userData").get("contentBit"));
        userData.add("playCount", userdata.getAsJsonObject("userData").get("playCount"));
        userData.add("currentPlayCount", userdata.getAsJsonObject("userData").get("currentPlayCount"));
        userData.addProperty("renameCredit", 0);
        userData.addProperty("mapStock", 0);
        userData.add("eventWatchedDate", userdata.getAsJsonObject("userData").get("eventWatchedDate"));
        userData.addProperty("lastGameId", "SDGB");
        userData.add("lastRomVersion", userdata.getAsJsonObject("userData").get("lastRomVersion"));
        userData.add("lastDataVersion", userdata.getAsJsonObject("userData").get("lastDataVersion"));
        userData.addProperty("lastLoginDate", loginDate);

        String now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ".0";
        userData.addProperty("lastPlayDate", now);

        userData.addProperty("lastPlayCredit", 1);
        userData.addProperty("lastPlayMode", 0);
        userData.addProperty("lastPlaceId", placeId);
        userData.addProperty("lastPlaceName", placeName);
        userData.addProperty("lastAllNetId", 0);
        userData.addProperty("lastRegionId", regionId);
        userData.addProperty("lastRegionName", regionName);
        userData.addProperty("lastClientId", clientId);
        userData.addProperty("lastCountryCode", "CHN");
        userData.addProperty("lastSelectEMoney", 0);
        userData.addProperty("lastSelectTicket", 0);
        userData.add("lastSelectCourse", userdata.getAsJsonObject("userData").get("lastSelectCourse"));
        userData.addProperty("lastCountCourse", 0);
        userData.addProperty("firstGameId", "SDGB");
        userData.add("firstRomVersion", userdata.getAsJsonObject("userData").get("firstRomVersion"));
        userData.add("firstDataVersion", userdata.getAsJsonObject("userData").get("firstDataVersion"));
        userData.add("firstPlayDate", userdata.getAsJsonObject("userData").get("firstPlayDate"));
        userData.add("compatibleCmVersion", userdata.getAsJsonObject("userData").get("compatibleCmVersion"));
        userData.add("dailyBonusDate", userdata.getAsJsonObject("userData").get("dailyBonusDate"));
        userData.add("dailyCourseBonusDate", userdata.getAsJsonObject("userData").get("dailyCourseBonusDate"));
        userData.add("lastPairLoginDate", userdata.getAsJsonObject("userData").get("lastPairLoginDate"));
        userData.add("lastTrialPlayDate", userdata.getAsJsonObject("userData").get("lastTrialPlayDate"));
        userData.addProperty("playVsCount", 0);
        userData.addProperty("playSyncCount", 0);
        userData.addProperty("winCount", 0);
        userData.addProperty("helpCount", 0);
        userData.addProperty("comboCount", 0);
        userData.add("totalDeluxscore", userdata.getAsJsonObject("userData").get("totalDeluxscore"));
        userData.add("totalBasicDeluxscore", userdata.getAsJsonObject("userData").get("totalBasicDeluxscore"));
        userData.add("totalAdvancedDeluxscore", userdata.getAsJsonObject("userData").get("totalAdvancedDeluxscore"));
        userData.add("totalExpertDeluxscore", userdata.getAsJsonObject("userData").get("totalExpertDeluxscore"));
        userData.add("totalMasterDeluxscore", userdata.getAsJsonObject("userData").get("totalMasterDeluxscore"));
        userData.add("totalReMasterDeluxscore", userdata.getAsJsonObject("userData").get("totalReMasterDeluxscore"));
        userData.add("totalSync", userdata.getAsJsonObject("userData").get("totalSync"));
        userData.add("totalBasicSync", userdata.getAsJsonObject("userData").get("totalBasicSync"));
        userData.add("totalAdvancedSync", userdata.getAsJsonObject("userData").get("totalAdvancedSync"));
        userData.add("totalExpertSync", userdata.getAsJsonObject("userData").get("totalExpertSync"));
        userData.add("totalMasterSync", userdata.getAsJsonObject("userData").get("totalMasterSync"));
        userData.add("totalReMasterSync", userdata.getAsJsonObject("userData").get("totalReMasterSync"));
        userData.add("totalAchievement", userdata.getAsJsonObject("userData").get("totalAchievement"));
        userData.add("totalBasicAchievement", userdata.getAsJsonObject("userData").get("totalBasicAchievement"));
        userData.add("totalAdvancedAchievement", userdata.getAsJsonObject("userData").get("totalAdvancedAchievement"));
        userData.add("totalExpertAchievement", userdata.getAsJsonObject("userData").get("totalExpertAchievement"));
        userData.add("totalMasterAchievement", userdata.getAsJsonObject("userData").get("totalMasterAchievement"));
        userData.add("totalReMasterAchievement", userdata.getAsJsonObject("userData").get("totalReMasterAchievement"));
        userData.add("playerOldRating", userdata.getAsJsonObject("userData").get("playerOldRating"));
        userData.add("playerNewRating", userdata.getAsJsonObject("userData").get("playerNewRating"));
        userData.addProperty("banState", 0);
        userData.addProperty("friendRegistSkip", 0);
        userData.addProperty("dateTime", timestamp);

        userDataArr.add(userData);
        upsertUserAll.add("userData", userDataArr);

        /* ======================== userExtend / userOption / userRating ======================== */
        JsonArray userExtendArr = new JsonArray();
        JsonObject userExtendShit = user_extend.getAsJsonObject("userExtend");
        //userExtendArr.add(user_extend.getAsJsonObject("userExtend"));
        userExtendShit.addProperty("isPhotoAgree", false);
        userExtendShit.addProperty("isGotoCodeRead", false);
        userExtendShit.add("selectedCardList", new JsonArray());
        userExtendShit.add("encountMapNpcList", new JsonArray());
        userExtendArr.add(userExtendShit);
        upsertUserAll.add("userExtend", userExtendArr);

        JsonArray userOptionArr = new JsonArray();
        JsonObject userOptionObj = user_option.getAsJsonObject("userOption");
        userOptionObj.remove("tempoVolume");
        userOptionArr.add(userOptionObj);
        upsertUserAll.add("userOption", userOptionArr);

        upsertUserAll.add("userGhost", new JsonArray());

        //"userMapList": [
        //            {
        //                "mapId": 450005,
        //                "distance": 153573000,
        //                "isLock": false,
        //                "isClear": false,
        //                "isComplete": false,
        //                "unlockFlag": 0
        //            }
        //        ],
        upsertUserAll.add("userMapList", new JsonArray());

        upsertUserAll.add("userLoginBonusList", new JsonArray());

        JsonArray userRatingArr = new JsonArray();
        userRatingArr.add(user_rating.getAsJsonObject("userRating"));
        upsertUserAll.add("userRatingList", userRatingArr);

        //        "userItemList": [
        //            {
        //                "itemKind": 14,
        //                "itemId": 10001,
        //                "stock": 1232,
        //                "isValid": true
        //            }
        //        ],

        /* ======================== userChargeList ======================== */
        upsertUserAll.add("userChargeList", user_charge.getAsJsonArray("userChargeList"));

        /* ======================== userActivityList ======================== */
        JsonArray userActivityArr = new JsonArray();
        userActivityArr.add(user_activity.getAsJsonObject("userActivity"));
        upsertUserAll.add("userActivityList", userActivityArr);

        /* ======================== userMusicDetailList ======================== */
        JsonArray musicDetailArr = new JsonArray();
        JsonObject musicDetail = new JsonObject();
        musicDetail.addProperty("musicId", 834);
        musicDetail.addProperty("level", 4);
        musicDetail.addProperty("playCount", 11);
        musicDetail.addProperty("achievement", 91780);
        musicDetail.addProperty("comboStatus", 120);
        musicDetail.addProperty("syncStatus", 0);
        musicDetail.addProperty("deluxscoreMax", 2102);
        musicDetail.addProperty("scoreRank", 4);
        musicDetail.addProperty("extNum1", 0);
        musicDetailArr.add(musicDetail);
        upsertUserAll.add("userMusicDetailList", musicDetailArr);

        /* ======================== userGamePlaylogList ======================== */
        JsonArray playlogArr = new JsonArray();
        JsonObject playlog = new JsonObject();
        playlog.addProperty("playlogId", loginId);
        playlog.addProperty("version", "1.51.00");
        playlog.addProperty("playDate", now);
        playlog.addProperty("playMode", 0);
        playlog.addProperty("useTicketId", -1);
        playlog.addProperty("playCredit", 1);
        playlog.addProperty("playTrack", 1);
        playlog.addProperty("clientId", clientId);
        playlog.addProperty("isPlayTutorial", false);
        playlog.addProperty("isEventMode", false);
        playlog.addProperty("isNewFree", false);
        playlog.addProperty("playCount", 0);
        playlog.addProperty("playSpecial", calcRandom()); // TODO: CalcRandom()
        playlog.addProperty("playOtherUserId", 0);
        playlogArr.add(playlog);
        upsertUserAll.add("userGamePlaylogList", playlogArr);

        /* ======================== user2pPlaylog ======================== */
        JsonObject user2p = new JsonObject();
        user2p.addProperty("userId1", 0);
        user2p.addProperty("userId2", 0);
        user2p.addProperty("userName1", "");
        user2p.addProperty("userName2", "");
        user2p.addProperty("regionId", 0);
        user2p.addProperty("placeId", 0);
        user2p.add("user2pPlaylogDetailList", new JsonArray());
        upsertUserAll.add("user2pPlaylog", user2p);

        // 新增字段部分
        upsertUserAll.add("userIntimateList", new JsonArray());
        upsertUserAll.add("userShopItemStockList", new JsonArray());

        upsertUserAll.add("userGetPointList", new JsonArray());

        // tradeItemList 删除，不加
        upsertUserAll.add("userTradeItemList", new JsonArray());
        upsertUserAll.add("userFavoritemusicList", new JsonArray());
        upsertUserAll.add("userKaleidxScopeList", new JsonArray());

        // isNew 字段们
        upsertUserAll.addProperty("isNewCharacterList", "000");
        upsertUserAll.addProperty("isNewMapList", "0");
        upsertUserAll.addProperty("isNewLoginBonusList", "");
        upsertUserAll.addProperty("isNewItemList", "0");
        upsertUserAll.addProperty("isNewMusicDetailList", "000");
        upsertUserAll.addProperty("isNewCourseList", "0");
        upsertUserAll.addProperty("isNewFavoriteList", "");
        upsertUserAll.addProperty("isNewFriendSeasonRankingList", "");
        upsertUserAll.addProperty("isNewUserIntimateList", "");
        upsertUserAll.addProperty("isNewFavoritemusicList", "");
        upsertUserAll.addProperty("isNewKaleidxScopeList", "1");//test

        root.add("upsertUserAll", upsertUserAll);

    }

    @Override
    public String execute() throws Exception {
        Gson gson = new Gson();
        return ShitPost.useApi(gson.toJson(root).replace("12929", "29386"), "UpsertUserAllApi", uid);
    }

    private transient final Random random = new Random();

    private int calcRandom() {
        int max = 1037933;
        int num2 = (random.nextInt(max) + 1) * 2069; // randint(1, max)
        num2 += 1024; // specialnum

        int num3 = 0;
        for (int i = 0; i < 32; i++) {
            num3 <<= 1;
            num3 += (num2 & 1); // num2 % 2
            num2 >>= 1;
        }

        return num3;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(root);
    }
}
