package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

public class UserPreviewPacket implements Packet {
    @SerializedName("userId")
    private long uid;

    public UserPreviewPacket(long uid) {
        this.uid = uid;
    }

    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "GetUserPreviewApi", uid);
    }
}
