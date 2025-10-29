package me.nekorainy.maigo.network.packets;

import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.network.Packet;
import me.nekorainy.maigo.network.ShitPost;

public class GetUserFavoriteItemPacket implements Packet {
    @SerializedName("userId")
    private final long uid;

    @SerializedName("kind")
    private final int kind;

    @SerializedName("nextIndex")
    private final int nextIndex;

    @SerializedName("maxCount")
    private final int maxCount;

    @SerializedName("isAllFavoriteItem")
    private final boolean isAllFavoriteItem;

    public GetUserFavoriteItemPacket(long uid, int kind, int nextIndex, int maxCount, boolean isAllFavoriteItem) {
        this.uid = uid;
        this.kind = kind;
        this.nextIndex = nextIndex;
        this.maxCount = maxCount;
        this.isAllFavoriteItem = isAllFavoriteItem;
    }

    @Override
    public String execute() throws Exception {
        return ShitPost.useApi(toJson(), "GetUserFavoriteItemApi", uid);
    }
}
