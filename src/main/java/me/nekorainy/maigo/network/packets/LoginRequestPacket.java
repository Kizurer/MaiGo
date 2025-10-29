package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

public class LoginRequestPacket implements Packet {
    @SerializedName("userId")
    private long userId;

    @SerializedName("accessCode")
    private String accessCode = "";

    @SerializedName("regionId")
    private long regionId;

    @SerializedName("placeId")
    private long placeId;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("dateTime")
    private long dateTime;

    @SerializedName("isContinue")
    private boolean isContinue = false;

    @SerializedName("genericFlag")
    private int genericFlag = 0;

    public LoginRequestPacket(long userId, long regionId, long placeId, String clientId, long dateTime) {
        this.userId = userId;
        this.regionId = regionId;
        this.placeId = placeId;
        this.clientId = clientId;
        this.dateTime = dateTime;
    }


    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "UserLoginApi", userId);
    }
}
