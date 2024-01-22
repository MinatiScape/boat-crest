package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class RespiratoryRateDataHelper {
    @NotNull
    public static final RespiratoryRateDataHelper INSTANCE = new RespiratoryRateDataHelper();

    public final ArrayList<Float> a(ArrayList<Float> arrayList) {
        ArrayList<Float> arrayList2 = new ArrayList<>();
        arrayList2.addAll(arrayList.subList(21, arrayList.size()));
        arrayList2.addAll(arrayList.subList(0, 10));
        return arrayList2;
    }

    public final ArrayList<Integer> b(ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.addAll(arrayList.subList(21, arrayList.size()));
        arrayList2.addAll(arrayList.subList(0, 10));
        return arrayList2;
    }

    @NotNull
    public final Pair<Integer, Integer> getLastReadValueWithHour(@NotNull Context context, @NotNull RespiratoryRateData data) {
        int i;
        RespiratoryRateRemoteConfiguration.Computation computation;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(context).getRespiratoryRateRemoteConfig();
        Float valueOf = (respiratoryRateRemoteConfig == null || (computation = respiratoryRateRemoteConfig.getComputation()) == null) ? null : Float.valueOf(computation.getConfidenceLevelThreshold());
        ArrayList<Integer> values = data.getValues();
        int i2 = 0;
        if (!(values == null || values.isEmpty())) {
            ArrayList<Float> accuracies = data.getAccuracies();
            if (!(accuracies == null || accuracies.isEmpty())) {
                ArrayList<Integer> values2 = data.getValues();
                Intrinsics.checkNotNull(values2);
                int size = values2.size();
                ArrayList<Float> accuracies2 = data.getAccuracies();
                Intrinsics.checkNotNull(accuracies2);
                if (size == accuracies2.size()) {
                    ArrayList<Integer> values3 = data.getValues();
                    Intrinsics.checkNotNull(values3);
                    ArrayList<Integer> b = b(values3);
                    ArrayList<Float> accuracies3 = data.getAccuracies();
                    Intrinsics.checkNotNull(accuracies3);
                    ArrayList<Float> a2 = a(accuracies3);
                    int i3 = 21;
                    Iterator<Integer> it = b.iterator();
                    i = 0;
                    int i4 = 0;
                    int i5 = 0;
                    while (it.hasNext()) {
                        int i6 = i5 + 1;
                        Integer next = it.next();
                        if (next != null && next.intValue() >= 0.0f && a2.get(i5) != null && valueOf != null) {
                            Float f = a2.get(i5);
                            Intrinsics.checkNotNull(f);
                            if (f.floatValue() >= valueOf.floatValue()) {
                                i = next.intValue();
                                i4 = i3;
                            }
                        }
                        i3++;
                        if (i3 == 24) {
                            i3 = 0;
                        }
                        i5 = i6;
                    }
                    i2 = i4;
                    return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
                }
            }
        }
        i = 0;
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
    }
}
