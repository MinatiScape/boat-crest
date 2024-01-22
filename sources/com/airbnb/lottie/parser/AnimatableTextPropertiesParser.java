package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes.dex */
public class AnimatableTextPropertiesParser {

    /* renamed from: a  reason: collision with root package name */
    public static final JsonReader.Options f2062a = JsonReader.Options.of("a");
    public static final JsonReader.Options b = JsonReader.Options.of("fc", Constants.INAPP_NOTIF_SHOW_CLOSE, "sw", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT);

    public static AnimatableTextProperties a(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        AnimatableColorValue animatableColorValue = null;
        AnimatableColorValue animatableColorValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(b);
            if (selectName == 0) {
                animatableColorValue = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (selectName == 1) {
                animatableColorValue2 = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
            } else if (selectName != 3) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
            }
        }
        jsonReader.endObject();
        return new AnimatableTextProperties(animatableColorValue, animatableColorValue2, animatableFloatValue, animatableFloatValue2);
    }

    public static AnimatableTextProperties parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        AnimatableTextProperties animatableTextProperties = null;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(f2062a) != 0) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                animatableTextProperties = a(jsonReader, lottieComposition);
            }
        }
        jsonReader.endObject();
        return animatableTextProperties == null ? new AnimatableTextProperties(null, null, null, null) : animatableTextProperties;
    }
}
