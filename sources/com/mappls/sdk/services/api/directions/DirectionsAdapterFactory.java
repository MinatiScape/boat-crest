package com.mappls.sdk.services.api.directions;

import androidx.annotation.Keep;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
@GsonTypeAdapterFactory
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionsAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_DirectionsAdapterFactory();
    }
}
