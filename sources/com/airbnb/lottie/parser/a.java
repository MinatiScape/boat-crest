package com.airbnb.lottie.parser;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.io.IOException;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final JsonReader.Options f2073a = JsonReader.Options.of("ef");
    public static final JsonReader.Options b = JsonReader.Options.of("ty", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE);

    @Nullable
    public static BlurEffect a(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        BlurEffect blurEffect = null;
        while (true) {
            boolean z = false;
            while (jsonReader.hasNext()) {
                int selectName = jsonReader.selectName(b);
                if (selectName != 0) {
                    if (selectName != 1) {
                        jsonReader.skipName();
                        jsonReader.skipValue();
                    } else if (z) {
                        blurEffect = new BlurEffect(AnimatableValueParser.parseFloat(jsonReader, lottieComposition));
                    } else {
                        jsonReader.skipValue();
                    }
                } else if (jsonReader.nextInt() == 0) {
                    z = true;
                }
            }
            jsonReader.endObject();
            return blurEffect;
        }
    }

    @Nullable
    public static BlurEffect b(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        BlurEffect blurEffect = null;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(f2073a) != 0) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    BlurEffect a2 = a(jsonReader, lottieComposition);
                    if (a2 != null) {
                        blurEffect = a2;
                    }
                }
                jsonReader.endArray();
            }
        }
        return blurEffect;
    }
}
