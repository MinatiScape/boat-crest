package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
/* loaded from: classes.dex */
public class PathParser implements v<PointF> {
    public static final PathParser INSTANCE = new PathParser();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.v
    public PointF parse(JsonReader jsonReader, float f) throws IOException {
        return h.e(jsonReader, f);
    }
}
