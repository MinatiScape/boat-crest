package com.airbnb.lottie;

import android.util.Log;
import androidx.collection.ArraySet;
import androidx.core.util.Pair;
import com.airbnb.lottie.utils.MeanCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class PerformanceTracker {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1986a = false;
    public final Set<FrameListener> b = new ArraySet();
    public final Map<String, MeanCalculator> c = new HashMap();
    public final Comparator<Pair<String, Float>> d = new a(this);

    /* loaded from: classes.dex */
    public interface FrameListener {
        void onFrameRendered(float f);
    }

    /* loaded from: classes.dex */
    public class a implements Comparator<Pair<String, Float>> {
        public a(PerformanceTracker performanceTracker) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Pair<String, Float> pair, Pair<String, Float> pair2) {
            float floatValue = pair.second.floatValue();
            float floatValue2 = pair2.second.floatValue();
            if (floatValue2 > floatValue) {
                return 1;
            }
            return floatValue > floatValue2 ? -1 : 0;
        }
    }

    public void a(boolean z) {
        this.f1986a = z;
    }

    public void addFrameListener(FrameListener frameListener) {
        this.b.add(frameListener);
    }

    public void clearRenderTimes() {
        this.c.clear();
    }

    public List<Pair<String, Float>> getSortedRenderTimes() {
        if (!this.f1986a) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.c.size());
        for (Map.Entry<String, MeanCalculator> entry : this.c.entrySet()) {
            arrayList.add(new Pair(entry.getKey(), Float.valueOf(entry.getValue().getMean())));
        }
        Collections.sort(arrayList, this.d);
        return arrayList;
    }

    public void logRenderTimes() {
        if (this.f1986a) {
            List<Pair<String, Float>> sortedRenderTimes = getSortedRenderTimes();
            Log.d(L.TAG, "Render times:");
            for (int i = 0; i < sortedRenderTimes.size(); i++) {
                Pair<String, Float> pair = sortedRenderTimes.get(i);
                Log.d(L.TAG, String.format("\t\t%30s:%.2f", pair.first, pair.second));
            }
        }
    }

    public void recordRenderTime(String str, float f) {
        if (this.f1986a) {
            MeanCalculator meanCalculator = this.c.get(str);
            if (meanCalculator == null) {
                meanCalculator = new MeanCalculator();
                this.c.put(str, meanCalculator);
            }
            meanCalculator.add(f);
            if (str.equals("__container")) {
                for (FrameListener frameListener : this.b) {
                    frameListener.onFrameRendered(f);
                }
            }
        }
    }

    public void removeFrameListener(FrameListener frameListener) {
        this.b.remove(frameListener);
    }
}
