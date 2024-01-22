package com.coveiot.android.bleabstract.formatter;

import com.coveiot.android.bleabstract.response.Spo2Wave;
import com.creative.base.BaseDate;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class Spo2Formatter {
    @NotNull
    public final List<Spo2Wave> covertToSpo2Waves(@NotNull List<? extends BaseDate.Wave> waves) {
        Intrinsics.checkNotNullParameter(waves, "waves");
        ArrayList arrayList = new ArrayList();
        for (BaseDate.Wave wave : waves) {
            arrayList.add(new Spo2Wave(wave.data, wave.flag));
        }
        return arrayList;
    }
}
