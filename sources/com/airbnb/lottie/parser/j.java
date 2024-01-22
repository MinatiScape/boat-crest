package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static JsonReader.Options f2083a = JsonReader.Options.of(OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME);

    public static <T> List<Keyframe<T>> a(JsonReader jsonReader, LottieComposition lottieComposition, float f, v<T> vVar, boolean z) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.peek() == JsonReader.Token.STRING) {
            lottieComposition.addWarning("Lottie doesn't support expressions.");
            return arrayList;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(f2083a) != 0) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY) {
                jsonReader.beginArray();
                if (jsonReader.peek() == JsonReader.Token.NUMBER) {
                    arrayList.add(i.c(jsonReader, lottieComposition, f, vVar, false, z));
                } else {
                    while (jsonReader.hasNext()) {
                        arrayList.add(i.c(jsonReader, lottieComposition, f, vVar, true, z));
                    }
                }
                jsonReader.endArray();
            } else {
                arrayList.add(i.c(jsonReader, lottieComposition, f, vVar, false, z));
            }
        }
        jsonReader.endObject();
        b(arrayList);
        return arrayList;
    }

    public static <T> void b(List<? extends Keyframe<T>> list) {
        int i;
        T t;
        int size = list.size();
        int i2 = 0;
        while (true) {
            i = size - 1;
            if (i2 >= i) {
                break;
            }
            Keyframe<T> keyframe = list.get(i2);
            i2++;
            Keyframe<T> keyframe2 = list.get(i2);
            keyframe.endFrame = Float.valueOf(keyframe2.startFrame);
            if (keyframe.endValue == null && (t = keyframe2.startValue) != null) {
                keyframe.endValue = t;
                if (keyframe instanceof PathKeyframe) {
                    ((PathKeyframe) keyframe).createPath();
                }
            }
        }
        Keyframe<T> keyframe3 = list.get(i);
        if ((keyframe3.startValue == null || keyframe3.endValue == null) && list.size() > 1) {
            list.remove(keyframe3);
        }
    }
}
