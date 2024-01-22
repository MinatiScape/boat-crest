package com.airbnb.lottie.parser;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.util.Collections;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public static final JsonReader.Options f2089a = JsonReader.Options.of(Constants.NOTIF_MSG, com.google.android.material.color.c.f10260a, "o", "fillEnabled", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "hd");

    public static ShapeFill a(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        AnimatableIntegerValue animatableIntegerValue = null;
        boolean z = false;
        boolean z2 = false;
        int i = 1;
        String str = null;
        AnimatableColorValue animatableColorValue = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(f2089a);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName == 1) {
                animatableColorValue = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatableIntegerValue = AnimatableValueParser.f(jsonReader, lottieComposition);
            } else if (selectName == 3) {
                z = jsonReader.nextBoolean();
            } else if (selectName == 4) {
                i = jsonReader.nextInt();
            } else if (selectName != 5) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                z2 = jsonReader.nextBoolean();
            }
        }
        return new ShapeFill(str, z, i == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, animatableColorValue, animatableIntegerValue == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : animatableIntegerValue, z2);
    }
}
