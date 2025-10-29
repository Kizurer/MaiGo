package me.nekorainy.maigo.network.jsonbeans;

import com.google.gson.annotations.SerializedName;

public class UserChargelog {
    @SerializedName("chargeId")
    private int chargeId;

    @SerializedName("price")
    private int price;

    @SerializedName("purchaseDate")
    private String purchaseDate;

    @SerializedName("placeId")
    private long placeId;

    @SerializedName("regionId")
    private long regionId;

    @SerializedName("clientId")
    private String clientId;

    public UserChargelog(int chargeId, int price, String purchaseDate,
                         long placeId, long regionId, String clientId) {
        this.chargeId = chargeId;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.placeId = placeId;
        this.regionId = regionId;
        this.clientId = clientId;
    }
}
