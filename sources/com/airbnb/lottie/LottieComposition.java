package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class LottieComposition {
    public Map<String, List<Layer>> c;
    public Map<String, LottieImageAsset> d;
    public Map<String, Font> e;
    public List<Marker> f;
    public SparseArrayCompat<FontCharacter> g;
    public LongSparseArray<Layer> h;
    public List<Layer> i;
    public Rect j;
    public float k;
    public float l;
    public float m;
    public boolean n;

    /* renamed from: a  reason: collision with root package name */
    public final PerformanceTracker f1976a = new PerformanceTracker();
    public final HashSet<String> b = new HashSet<>();
    public int o = 0;

    @Deprecated
    /* loaded from: classes.dex */
    public static class Factory {

        /* loaded from: classes.dex */
        public static final class a implements LottieListener<LottieComposition>, Cancellable {

            /* renamed from: a  reason: collision with root package name */
            public final OnCompositionLoadedListener f1977a;
            public boolean b;

            @Override // com.airbnb.lottie.LottieListener
            /* renamed from: a */
            public void onResult(LottieComposition lottieComposition) {
                if (this.b) {
                    return;
                }
                this.f1977a.onCompositionLoaded(lottieComposition);
            }

            @Override // com.airbnb.lottie.Cancellable
            public void cancel() {
                this.b = true;
            }

            public a(OnCompositionLoadedListener onCompositionLoadedListener) {
                this.b = false;
                this.f1977a = onCompositionLoadedListener;
            }
        }

        @Deprecated
        public static Cancellable fromAssetFileName(Context context, String str, OnCompositionLoadedListener onCompositionLoadedListener) {
            a aVar = new a(onCompositionLoadedListener);
            LottieCompositionFactory.fromAsset(context, str).addListener(aVar);
            return aVar;
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromFileSync(Context context, String str) {
            return LottieCompositionFactory.fromAssetSync(context, str).getValue();
        }

        @Deprecated
        public static Cancellable fromInputStream(InputStream inputStream, OnCompositionLoadedListener onCompositionLoadedListener) {
            a aVar = new a(onCompositionLoadedListener);
            LottieCompositionFactory.fromJsonInputStream(inputStream, null).addListener(aVar);
            return aVar;
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream inputStream) {
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null).getValue();
        }

        @Deprecated
        public static Cancellable fromJsonReader(JsonReader jsonReader, OnCompositionLoadedListener onCompositionLoadedListener) {
            a aVar = new a(onCompositionLoadedListener);
            LottieCompositionFactory.fromJsonReader(jsonReader, null).addListener(aVar);
            return aVar;
        }

        @Deprecated
        public static Cancellable fromJsonString(String str, OnCompositionLoadedListener onCompositionLoadedListener) {
            a aVar = new a(onCompositionLoadedListener);
            LottieCompositionFactory.fromJsonString(str, null).addListener(aVar);
            return aVar;
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromJsonSync(Resources resources, JSONObject jSONObject) {
            return LottieCompositionFactory.fromJsonSync(jSONObject, null).getValue();
        }

        @Deprecated
        public static Cancellable fromRawFile(Context context, @RawRes int i, OnCompositionLoadedListener onCompositionLoadedListener) {
            a aVar = new a(onCompositionLoadedListener);
            LottieCompositionFactory.fromRawRes(context, i).addListener(aVar);
            return aVar;
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream inputStream, boolean z) {
            if (z) {
                Logger.warning("Lottie now auto-closes input stream!");
            }
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null).getValue();
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromJsonSync(String str) {
            return LottieCompositionFactory.fromJsonStringSync(str, null).getValue();
        }

        @Nullable
        @WorkerThread
        @Deprecated
        public static LottieComposition fromJsonSync(JsonReader jsonReader) {
            return LottieCompositionFactory.fromJsonReaderSync(jsonReader, null).getValue();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void addWarning(String str) {
        Logger.warning(str);
        this.b.add(str);
    }

    public Rect getBounds() {
        return this.j;
    }

    public SparseArrayCompat<FontCharacter> getCharacters() {
        return this.g;
    }

    public float getDuration() {
        return (getDurationFrames() / this.m) * 1000.0f;
    }

    public float getDurationFrames() {
        return this.l - this.k;
    }

    public float getEndFrame() {
        return this.l;
    }

    public Map<String, Font> getFonts() {
        return this.e;
    }

    public float getFrameForProgress(float f) {
        return MiscUtils.lerp(this.k, this.l, f);
    }

    public float getFrameRate() {
        return this.m;
    }

    public Map<String, LottieImageAsset> getImages() {
        return this.d;
    }

    public List<Layer> getLayers() {
        return this.i;
    }

    @Nullable
    public Marker getMarker(String str) {
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            Marker marker = this.f.get(i);
            if (marker.matchesName(str)) {
                return marker;
            }
        }
        return null;
    }

    public List<Marker> getMarkers() {
        return this.f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int getMaskAndMatteCount() {
        return this.o;
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.f1976a;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public List<Layer> getPrecomps(String str) {
        return this.c.get(str);
    }

    public float getProgressForFrame(float f) {
        float f2 = this.k;
        return (f - f2) / (this.l - f2);
    }

    public float getStartFrame() {
        return this.k;
    }

    public ArrayList<String> getWarnings() {
        HashSet<String> hashSet = this.b;
        return new ArrayList<>(Arrays.asList((String[]) hashSet.toArray(new String[hashSet.size()])));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDashPattern() {
        return this.n;
    }

    public boolean hasImages() {
        return !this.d.isEmpty();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void incrementMatteOrMaskCount(int i) {
        this.o += i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void init(Rect rect, float f, float f2, float f3, List<Layer> list, LongSparseArray<Layer> longSparseArray, Map<String, List<Layer>> map, Map<String, LottieImageAsset> map2, SparseArrayCompat<FontCharacter> sparseArrayCompat, Map<String, Font> map3, List<Marker> list2) {
        this.j = rect;
        this.k = f;
        this.l = f2;
        this.m = f3;
        this.i = list;
        this.h = longSparseArray;
        this.c = map;
        this.d = map2;
        this.g = sparseArrayCompat;
        this.e = map3;
        this.f = list2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Layer layerModelForId(long j) {
        return this.h.get(j);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setHasDashPattern(boolean z) {
        this.n = z;
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.f1976a.a(z);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        for (Layer layer : this.i) {
            sb.append(layer.toString("\t"));
        }
        return sb.toString();
    }
}
