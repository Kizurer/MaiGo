package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

public class LogoutRequestPacket implements Packet {
    @SerializedName("userId")
    private long userId;

    @SerializedName("accessCode")
    private String accessCode;

    @SerializedName("regionId")
    private long regionId;

    @SerializedName("placeId")
    private long placeId;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("dateTime")
    private long dateTime;

    @SerializedName("type")
    private int type;

    public LogoutRequestPacket(long userId, String accessCode, long regionId,
                         long placeId, String clientId, long dateTime, int type) {
        this.userId = userId;
        this.accessCode = accessCode;
        this.regionId = regionId;
        this.placeId = placeId;
        this.clientId = clientId;
        this.dateTime = dateTime;
        this.type = type;
    }


    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "UserLogoutApi", userId);
    }
}
