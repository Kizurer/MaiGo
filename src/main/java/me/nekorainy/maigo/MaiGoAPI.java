package me.nekorainy.maigo;

import me.nekorainy.maigo.network.jsonbeans.UserCharge;
import me.nekorainy.maigo.network.jsonbeans.UserChargelog;
import me.nekorainy.maigo.network.packets.GetUserActivityPacket;
import me.nekorainy.maigo.network.packets.GetUserDataPacket;
import me.nekorainy.maigo.network.packets.GetUserExtendPacket;
import me.nekorainy.maigo.network.packets.GetUserFavoriteItemPacket;
import me.nekorainy.maigo.network.packets.GetUserOptionPacket;
import me.nekorainy.maigo.network.packets.GetUserRatingPacket;
import me.nekorainy.maigo.network.packets.GetUserTicketsPacket;
import me.nekorainy.maigo.network.packets.LoginRequestPacket;
import me.nekorainy.maigo.network.packets.LogoutRequestPacket;
import me.nekorainy.maigo.network.packets.TicketRequestPacket;
import me.nekorainy.maigo.network.packets.UploadUserPlaylogListPacket;
import me.nekorainy.maigo.network.packets.UserPreviewPacket;
import me.nekorainy.maigo.network.packets.UserIdRequestPacket;

public class MaiGoAPI {
    private final String aes_iv, aes_key, title_server_url, client_id, place_name, chime_url, chime_salt, packet_obfuscation_key;
    private final long place_id, region_id;
    public static MaiGoAPI instance = null;

    public MaiGoAPI(String aes_iv, String aes_key, String title_server_url, String client_id, String place_name, String chime_url, String chime_salt, String packet_obfuscation_key, long place_id, long region_id) {
        this.aes_iv = aes_iv;
        this.aes_key = aes_key;
        this.title_server_url = title_server_url;
        this.client_id = client_id;
        this.place_name = place_name;
        this.chime_url = chime_url;
        this.chime_salt = chime_salt;
        this.packet_obfuscation_key = packet_obfuscation_key;
        this.place_id = place_id;
        this.region_id = region_id;
        instance = this;
    }


    public String sendPacket(APIEnum apiEnum, Object... args) {
        switch (apiEnum){
            /**
             * args: long userId, long dateTime
             */
            case LOGIN -> {
                if (args.length != 2) throw new RuntimeException("Invalid arguments for Login packet. Expected 2, got " + args.length);
                try {
                    return new LoginRequestPacket(((Number) args[0]).longValue(), region_id, place_id, client_id, ((Number) args[1]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId, long dateTime
             */
            case LOGOUT -> {
                if (args.length != 2) throw new RuntimeException("Invalid arguments for Logout packet. Expected 2, got " + args.length);
                try {
                    return new LogoutRequestPacket(((Number) args[0]).longValue(), "", region_id, place_id, client_id, ((Number) args[1]).longValue(), 1).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_DATA -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserData packet. Expected 1, got " + args.length);
                try {
                    return new GetUserDataPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId, long dateTime, UserCharge userCharge, UserChargelog userChargelog
             */
            case REQUEST_TICKET -> {
                if (args.length != 4) throw new RuntimeException("Invalid arguments for TicketRequest packet. Expected 4, got " + args.length);
                try {
                    return new TicketRequestPacket(((Number) args[0]).longValue(), ((Number) args[1]).longValue(), (UserCharge) args[2], (UserChargelog) args[3]).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // ---------- Generated cases below ----------

            /**
             * args: String qrCodeToken
             */
            case GET_USER_UID_BY_QRCODE_TOKEN -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserUidByQrCodeToken packet. Expected 1, got " + args.length);
                try {
                    return new UserIdRequestPacket((String) args[0]).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_ACTIVITY -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserActivity packet. Expected 1, got " + args.length);
                try {
                    return new GetUserActivityPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_EXTEND_DATA -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserExtend packet. Expected 1, got " + args.length);
                try {
                    return new GetUserExtendPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId, int kind, int nextIndex, int maxCount, boolean isAllFavoriteItem
             */
            case GET_USER_FAVORITE_ITEM -> {
                if (args.length != 5) throw new RuntimeException("Invalid arguments for GetUserFavoriteItem packet. Expected 5, got " + args.length);
                try {
                    long uid = ((Number) args[0]).longValue();
                    int kind = ((Number) args[1]).intValue();
                    int nextIndex = ((Number) args[2]).intValue();
                    int maxCount = ((Number) args[3]).intValue();
                    boolean isAll = (boolean) args[4];
                    return new GetUserFavoriteItemPacket(uid, kind, nextIndex, maxCount, isAll).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_OPTION -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserOption packet. Expected 1, got " + args.length);
                try {
                    return new GetUserOptionPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_RATING -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserRating packet. Expected 1, got " + args.length);
                try {
                    return new GetUserRatingPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_TICKETS -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserTickets packet. Expected 1, got " + args.length);
                try {
                    return new GetUserTicketsPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId, long loginId
             */
            case UPLOAD_USER_PLAYLOG -> {
                if (args.length != 2) throw new RuntimeException("Invalid arguments for UploadUserPlaylog packet. Expected 2, got " + args.length);
                try {
                    return new UploadUserPlaylogListPacket(((Number) args[0]).longValue(), ((Number) args[1]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * args: long userId
             */
            case GET_USER_PREVIEW -> {
                if (args.length != 1) throw new RuntimeException("Invalid arguments for GetUserPreview packet. Expected 1, got " + args.length);
                try {
                    return new UserPreviewPacket(((Number) args[0]).longValue()).execute();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return "";
    }

    public String getAes_iv() {
        return aes_iv;
    }

    public String getAes_key() {
        return aes_key;
    }

    public String getTitle_server_url() {
        return title_server_url;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getChime_url() {
        return chime_url;
    }

    public String getChime_salt() {
        return chime_salt;
    }

    public String getPacket_obfuscation_key() {
        return packet_obfuscation_key;
    }

    public long getPlace_id() {
        return place_id;
    }

    public long getRegion_id() {
        return region_id;
    }
}
