package me.nekorainy.maigo.network.jsonbeans;

import com.google.gson.annotations.SerializedName;

public class UserCharge {
    @SerializedName("chargeId")
    private int chargeId;

    @SerializedName("stock")
    private int stock;

    /**
     * Purchase date in the format "YYYY-MM-DD HH:MM:SS"
     */
    @SerializedName("purchaseDate")
    private String purchaseDate;

    /**
     * Valid date in the format "YYYY-MM-DD HH:MM:SS"
     */
    @SerializedName("validDate")
    private String validDate;

    public UserCharge(int chargeId, int stock, String purchaseDate, String validDate) {
        this.chargeId = chargeId;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
        this.validDate = validDate;
    }
}
