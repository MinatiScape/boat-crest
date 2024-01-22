package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
/* loaded from: classes.dex */
public interface v<V> {
    V parse(JsonReader jsonReader, float f) throws IOException;
}
