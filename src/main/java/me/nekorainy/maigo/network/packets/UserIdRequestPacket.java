package me.nekorainy.maigo.network.packets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.nekorainy.maigo.MaiGoAPI;
import me.nekorainy.maigo.network.Packet;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserIdRequestPacket implements Packet {
    @SerializedName("chipID")
    private String chipId;

    @SerializedName("openGameID")
    private String openGameId = "MAID"; // 固定

    @SerializedName("key")
    private String key;

    @SerializedName("qrCode")
    private String qrCode;

    @SerializedName("timestamp")
    private String timestamp;

    private long userIdOrError = -1;
    private String aimeHost = MaiGoAPI.instance.getChime_url();

    public UserIdRequestPacket(String qrCodeToken) {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                .substring(2);

        int randomInt = new Random().nextInt(100000000); // 0-99999999
        this.chipId = String.format("A63E-01E%08d", randomInt);

        String rawKey = chipId + timestamp + MaiGoAPI.instance.getChime_salt();
        this.key = sha256(rawKey).toUpperCase();

        this.qrCode = qrCodeToken.substring(20);
    }


    @Override
    public String execute() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI( aimeHost+ "/wc_aime/api/get_data"))
                .header("User-Agent", "WC_AIME_LIB")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(toJson()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject obj = new Gson().fromJson(response.body(), JsonObject.class);

        int errorID = obj.get("errorID").getAsInt();
        long userID = obj.get("userID").getAsLong();

        if (errorID == 0) {
            userIdOrError = userID;
        } else {
            userIdOrError = errorID;
        }

        return String.valueOf(userIdOrError);
    }

    private static String sha256(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
