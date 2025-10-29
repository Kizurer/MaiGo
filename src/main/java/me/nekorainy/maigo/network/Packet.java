package me.nekorainy.maigo.network;

import com.google.gson.Gson;

public interface Packet {
    String execute() throws Exception;

    default String toJson() {
        return new Gson().toJson(this);
    }
}
