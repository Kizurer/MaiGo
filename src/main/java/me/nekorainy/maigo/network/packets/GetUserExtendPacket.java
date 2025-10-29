package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

public class GetUserExtendPacket implements Packet {
    @SerializedName("userId")
    private long uid;

    public GetUserExtendPacket(long uid) {
        this.uid = uid;
    }

    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "GetUserExtendApi", uid);
    }
}
