package com.zinhle.critterkeeper.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Thin wrapper around Gson so the rest of the codebase never imports Gson
 * directly - keeps the JSON library swappable in one place.
 */
public final class JsonParser {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private JsonParser() {
        // no instances
    }

    public static Request parseRequest(String json) {
        return GSON.fromJson(json, Request.class);
    }

    public static Response parseResponse(String json) {
        return GSON.fromJson(json, Response.class);
    }

    public static String toJson(Response response) {
        return GSON.toJson(response);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }
}
