package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class ShapeDataParser implements v<ShapeData> {
    public static final ShapeDataParser INSTANCE = new ShapeDataParser();

    /* renamed from: a  reason: collision with root package name */
    public static final JsonReader.Options f2072a = JsonReader.Options.of(com.google.android.material.color.c.f10260a, CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "i", "o");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.v
    public ShapeData parse(JsonReader jsonReader, float f) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.beginArray();
        }
        jsonReader.beginObject();
        List<PointF> list = null;
        List<PointF> list2 = null;
        List<PointF> list3 = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(f2072a);
            if (selectName == 0) {
                z = jsonReader.nextBoolean();
            } else if (selectName == 1) {
                list = h.f(jsonReader, f);
            } else if (selectName == 2) {
                list2 = h.f(jsonReader, f);
            } else if (selectName != 3) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                list3 = h.f(jsonReader, f);
            }
        }
        jsonReader.endObject();
        if (jsonReader.peek() == JsonReader.Token.END_ARRAY) {
            jsonReader.endArray();
        }
        if (list != null && list2 != null && list3 != null) {
            if (list.isEmpty()) {
                return new ShapeData(new PointF(), false, Collections.emptyList());
            }
            int size = list.size();
            PointF pointF = list.get(0);
            ArrayList arrayList = new ArrayList(size);
            for (int i = 1; i < size; i++) {
                PointF pointF2 = list.get(i);
                int i2 = i - 1;
                arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(i2), list3.get(i2)), MiscUtils.addPoints(pointF2, list2.get(i)), pointF2));
            }
            if (z) {
                PointF pointF3 = list.get(0);
                int i3 = size - 1;
                arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(i3), list3.get(i3)), MiscUtils.addPoints(pointF3, list2.get(0)), pointF3));
            }
            return new ShapeData(pointF, z, arrayList);
        }
        throw new IllegalArgumentException("Shape data was missing information.");
    }
}