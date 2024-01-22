package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
/* loaded from: classes.dex */
public class FloatParser implements v<Float> {
    public static final FloatParser INSTANCE = new FloatParser();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.v
    public Float parse(JsonReader jsonReader, float f) throws IOException {
        return Float.valueOf(h.g(jsonReader) * f);
    }
}
