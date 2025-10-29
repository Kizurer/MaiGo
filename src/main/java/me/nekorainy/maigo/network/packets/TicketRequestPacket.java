package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;
import me.nekorainy.maigo.network.jsonbeans.UserCharge;
import me.nekorainy.maigo.network.jsonbeans.UserChargelog;

public class TicketRequestPacket implements Packet {
    @SerializedName("userId")
    private long userId;

    @SerializedName("dateTime")
    private long dateTime;  // time.time() → 秒级时间戳

    @SerializedName("userCharge")
    private UserCharge userCharge;

    @SerializedName("userChargelog")
    private UserChargelog userChargelog;

    public TicketRequestPacket(long userId, long dateTime,
                         UserCharge userCharge, UserChargelog userChargelog) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.userCharge = userCharge;
        this.userChargelog = userChargelog;
    }


    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "UpsertUserChargelogApi", userId);
    }
}
