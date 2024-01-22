package com.mappls.sdk.plugins.places.autocomplete.data.converter;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
/* loaded from: classes10.dex */
public final class a {
    @TypeConverter
    public static ELocation a(String str) {
        if (str == null) {
            return null;
        }
        return (ELocation) new Gson().fromJson(str, (Class<Object>) ELocation.class);
    }

    @TypeConverter
    public static String a(@NonNull ELocation eLocation) {
        return new Gson().toJson(eLocation);
    }
}
