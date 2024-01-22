package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.List;
/* loaded from: classes.dex */
public class AnimatableValueParser {
    public static <T> List<Keyframe<T>> a(JsonReader jsonReader, float f, LottieComposition lottieComposition, v<T> vVar) throws IOException {
        return j.a(jsonReader, lottieComposition, f, vVar, false);
    }

    public static <T> List<Keyframe<T>> b(JsonReader jsonReader, LottieComposition lottieComposition, v<T> vVar) throws IOException {
        return j.a(jsonReader, lottieComposition, 1.0f, vVar, false);
    }

    public static AnimatableColorValue c(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatableColorValue(b(jsonReader, lottieComposition, ColorParser.INSTANCE));
    }

    public static AnimatableTextFrame d(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatableTextFrame(a(jsonReader, Utils.dpScale(), lottieComposition, DocumentDataParser.INSTANCE));
    }

    public static AnimatableGradientColorValue e(JsonReader jsonReader, LottieComposition lottieComposition, int i) throws IOException {
        return new AnimatableGradientColorValue(b(jsonReader, lottieComposition, new GradientColorParser(i)));
    }

    public static AnimatableIntegerValue f(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatableIntegerValue(b(jsonReader, lottieComposition, IntegerParser.INSTANCE));
    }

    public static AnimatablePointValue g(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatablePointValue(j.a(jsonReader, lottieComposition, Utils.dpScale(), PointFParser.INSTANCE, true));
    }

    public static AnimatableScaleValue h(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatableScaleValue(b(jsonReader, lottieComposition, ScaleXYParser.INSTANCE));
    }

    public static AnimatableShapeValue i(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return new AnimatableShapeValue(a(jsonReader, Utils.dpScale(), lottieComposition, ShapeDataParser.INSTANCE));
    }

    public static AnimatableFloatValue parseFloat(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        return parseFloat(jsonReader, lottieComposition, true);
    }

    public static AnimatableFloatValue parseFloat(JsonReader jsonReader, LottieComposition lottieComposition, boolean z) throws IOException {
        return new AnimatableFloatValue(a(jsonReader, z ? Utils.dpScale() : 1.0f, lottieComposition, FloatParser.INSTANCE));
    }
}
