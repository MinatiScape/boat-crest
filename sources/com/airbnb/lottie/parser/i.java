package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes.dex */
public class i {
    public static SparseArrayCompat<WeakReference<Interpolator>> b;

    /* renamed from: a  reason: collision with root package name */
    public static final Interpolator f2082a = new LinearInterpolator();
    public static JsonReader.Options c = JsonReader.Options.of(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "s", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "o", "i", "h", TypedValues.TransitionType.S_TO, Constants.INAPP_ID_IN_PAYLOAD);
    public static JsonReader.Options d = JsonReader.Options.of("x", EllipticCurveJsonWebKey.Y_MEMBER_NAME);

    @Nullable
    public static WeakReference<Interpolator> a(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (i.class) {
            weakReference = g().get(i);
        }
        return weakReference;
    }

    public static Interpolator b(PointF pointF, PointF pointF2) {
        Interpolator linearInterpolator;
        pointF.x = MiscUtils.clamp(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.clamp(pointF.y, -100.0f, 100.0f);
        pointF2.x = MiscUtils.clamp(pointF2.x, -1.0f, 1.0f);
        float clamp = MiscUtils.clamp(pointF2.y, -100.0f, 100.0f);
        pointF2.y = clamp;
        int hashFor = Utils.hashFor(pointF.x, pointF.y, pointF2.x, clamp);
        WeakReference<Interpolator> a2 = L.getDisablePathInterpolatorCache() ? null : a(hashFor);
        Interpolator interpolator = a2 != null ? a2.get() : null;
        if (a2 == null || interpolator == null) {
            try {
                linearInterpolator = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e) {
                if ("The Path cannot loop back on itself.".equals(e.getMessage())) {
                    linearInterpolator = PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y);
                } else {
                    linearInterpolator = new LinearInterpolator();
                }
            }
            interpolator = linearInterpolator;
            if (!L.getDisablePathInterpolatorCache()) {
                try {
                    h(hashFor, new WeakReference(interpolator));
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        }
        return interpolator;
    }

    public static <T> Keyframe<T> c(JsonReader jsonReader, LottieComposition lottieComposition, float f, v<T> vVar, boolean z, boolean z2) throws IOException {
        if (z && z2) {
            return e(lottieComposition, jsonReader, f, vVar);
        }
        if (z) {
            return d(lottieComposition, jsonReader, f, vVar);
        }
        return f(jsonReader, f, vVar);
    }

    public static <T> Keyframe<T> d(LottieComposition lottieComposition, JsonReader jsonReader, float f, v<T> vVar) throws IOException {
        Interpolator interpolator;
        Interpolator interpolator2;
        T t;
        jsonReader.beginObject();
        PointF pointF = null;
        boolean z = false;
        T t2 = null;
        T t3 = null;
        PointF pointF2 = null;
        PointF pointF3 = null;
        float f2 = 0.0f;
        PointF pointF4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(c)) {
                case 0:
                    f2 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    t3 = vVar.parse(jsonReader, f);
                    break;
                case 2:
                    t2 = vVar.parse(jsonReader, f);
                    break;
                case 3:
                    pointF = h.e(jsonReader, 1.0f);
                    break;
                case 4:
                    pointF4 = h.e(jsonReader, 1.0f);
                    break;
                case 5:
                    if (jsonReader.nextInt() != 1) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 6:
                    pointF2 = h.e(jsonReader, f);
                    break;
                case 7:
                    pointF3 = h.e(jsonReader, f);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        if (z) {
            interpolator2 = f2082a;
            t = t3;
        } else {
            if (pointF != null && pointF4 != null) {
                interpolator = b(pointF, pointF4);
            } else {
                interpolator = f2082a;
            }
            interpolator2 = interpolator;
            t = t2;
        }
        Keyframe<T> keyframe = new Keyframe<>(lottieComposition, t3, t, interpolator2, f2, null);
        keyframe.pathCp1 = pointF2;
        keyframe.pathCp2 = pointF3;
        return keyframe;
    }

    public static <T> Keyframe<T> e(LottieComposition lottieComposition, JsonReader jsonReader, float f, v<T> vVar) throws IOException {
        Interpolator interpolator;
        Interpolator b2;
        Interpolator b3;
        T t;
        PointF pointF;
        Keyframe<T> keyframe;
        PointF pointF2;
        float f2;
        PointF pointF3;
        jsonReader.beginObject();
        PointF pointF4 = null;
        boolean z = false;
        PointF pointF5 = null;
        PointF pointF6 = null;
        PointF pointF7 = null;
        T t2 = null;
        PointF pointF8 = null;
        PointF pointF9 = null;
        PointF pointF10 = null;
        float f3 = 0.0f;
        PointF pointF11 = null;
        T t3 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(c)) {
                case 0:
                    pointF2 = pointF4;
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    pointF2 = pointF4;
                    t2 = vVar.parse(jsonReader, f);
                    break;
                case 2:
                    pointF2 = pointF4;
                    t3 = vVar.parse(jsonReader, f);
                    break;
                case 3:
                    pointF2 = pointF4;
                    f2 = f3;
                    PointF pointF12 = pointF11;
                    if (jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                        jsonReader.beginObject();
                        float f4 = 0.0f;
                        float f5 = 0.0f;
                        float f6 = 0.0f;
                        float f7 = 0.0f;
                        while (jsonReader.hasNext()) {
                            int selectName = jsonReader.selectName(d);
                            if (selectName == 0) {
                                JsonReader.Token peek = jsonReader.peek();
                                JsonReader.Token token = JsonReader.Token.NUMBER;
                                if (peek == token) {
                                    f6 = (float) jsonReader.nextDouble();
                                    f4 = f6;
                                } else {
                                    jsonReader.beginArray();
                                    f4 = (float) jsonReader.nextDouble();
                                    f6 = jsonReader.peek() == token ? (float) jsonReader.nextDouble() : f4;
                                    jsonReader.endArray();
                                }
                            } else if (selectName != 1) {
                                jsonReader.skipValue();
                            } else {
                                JsonReader.Token peek2 = jsonReader.peek();
                                JsonReader.Token token2 = JsonReader.Token.NUMBER;
                                if (peek2 == token2) {
                                    f7 = (float) jsonReader.nextDouble();
                                    f5 = f7;
                                } else {
                                    jsonReader.beginArray();
                                    f5 = (float) jsonReader.nextDouble();
                                    f7 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f5;
                                    jsonReader.endArray();
                                }
                            }
                        }
                        PointF pointF13 = new PointF(f4, f5);
                        PointF pointF14 = new PointF(f6, f7);
                        jsonReader.endObject();
                        pointF8 = pointF14;
                        pointF7 = pointF13;
                        pointF11 = pointF12;
                        f3 = f2;
                        break;
                    } else {
                        pointF5 = h.e(jsonReader, f);
                        f3 = f2;
                        pointF11 = pointF12;
                        break;
                    }
                case 4:
                    if (jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                        jsonReader.beginObject();
                        float f8 = 0.0f;
                        float f9 = 0.0f;
                        float f10 = 0.0f;
                        float f11 = 0.0f;
                        while (jsonReader.hasNext()) {
                            PointF pointF15 = pointF11;
                            int selectName2 = jsonReader.selectName(d);
                            if (selectName2 != 0) {
                                pointF3 = pointF4;
                                if (selectName2 != 1) {
                                    jsonReader.skipValue();
                                } else {
                                    JsonReader.Token peek3 = jsonReader.peek();
                                    JsonReader.Token token3 = JsonReader.Token.NUMBER;
                                    if (peek3 == token3) {
                                        f11 = (float) jsonReader.nextDouble();
                                        f3 = f3;
                                        f9 = f11;
                                    } else {
                                        float f12 = f3;
                                        jsonReader.beginArray();
                                        float nextDouble = (float) jsonReader.nextDouble();
                                        float nextDouble2 = jsonReader.peek() == token3 ? (float) jsonReader.nextDouble() : nextDouble;
                                        jsonReader.endArray();
                                        f3 = f12;
                                        pointF11 = pointF15;
                                        pointF4 = pointF3;
                                        f11 = nextDouble2;
                                        f9 = nextDouble;
                                    }
                                }
                            } else {
                                pointF3 = pointF4;
                                float f13 = f3;
                                JsonReader.Token peek4 = jsonReader.peek();
                                JsonReader.Token token4 = JsonReader.Token.NUMBER;
                                if (peek4 == token4) {
                                    f10 = (float) jsonReader.nextDouble();
                                    f3 = f13;
                                    f8 = f10;
                                } else {
                                    jsonReader.beginArray();
                                    f8 = (float) jsonReader.nextDouble();
                                    f10 = jsonReader.peek() == token4 ? (float) jsonReader.nextDouble() : f8;
                                    jsonReader.endArray();
                                    f3 = f13;
                                }
                            }
                            pointF11 = pointF15;
                            pointF4 = pointF3;
                        }
                        pointF2 = pointF4;
                        f2 = f3;
                        PointF pointF16 = new PointF(f8, f9);
                        PointF pointF17 = new PointF(f10, f11);
                        jsonReader.endObject();
                        pointF10 = pointF17;
                        pointF9 = pointF16;
                        f3 = f2;
                        break;
                    } else {
                        pointF2 = pointF4;
                        pointF6 = h.e(jsonReader, f);
                        break;
                    }
                case 5:
                    if (jsonReader.nextInt() == 1) {
                        z = true;
                    } else {
                        z = false;
                        continue;
                    }
                case 6:
                    pointF11 = h.e(jsonReader, f);
                    continue;
                case 7:
                    pointF4 = h.e(jsonReader, f);
                    continue;
                default:
                    pointF2 = pointF4;
                    jsonReader.skipValue();
                    break;
            }
            pointF4 = pointF2;
        }
        PointF pointF18 = pointF4;
        float f14 = f3;
        PointF pointF19 = pointF11;
        jsonReader.endObject();
        if (z) {
            interpolator = f2082a;
            t = t2;
        } else {
            if (pointF5 != null && pointF6 != null) {
                interpolator = b(pointF5, pointF6);
            } else if (pointF7 != null && pointF8 != null && pointF9 != null && pointF10 != null) {
                b2 = b(pointF7, pointF9);
                b3 = b(pointF8, pointF10);
                t = t3;
                interpolator = null;
                if (b2 == null && b3 != null) {
                    pointF = pointF19;
                    keyframe = new Keyframe<>(lottieComposition, t2, t, b2, b3, f14, null);
                } else {
                    pointF = pointF19;
                    keyframe = new Keyframe<>(lottieComposition, t2, t, interpolator, f14, null);
                }
                keyframe.pathCp1 = pointF;
                keyframe.pathCp2 = pointF18;
                return keyframe;
            } else {
                interpolator = f2082a;
            }
            t = t3;
        }
        b2 = null;
        b3 = null;
        if (b2 == null) {
        }
        pointF = pointF19;
        keyframe = new Keyframe<>(lottieComposition, t2, t, interpolator, f14, null);
        keyframe.pathCp1 = pointF;
        keyframe.pathCp2 = pointF18;
        return keyframe;
    }

    public static <T> Keyframe<T> f(JsonReader jsonReader, float f, v<T> vVar) throws IOException {
        return new Keyframe<>(vVar.parse(jsonReader, f));
    }

    public static SparseArrayCompat<WeakReference<Interpolator>> g() {
        if (b == null) {
            b = new SparseArrayCompat<>();
        }
        return b;
    }

    public static void h(int i, WeakReference<Interpolator> weakReference) {
        synchronized (i.class) {
            b.put(i, weakReference);
        }
    }
}
