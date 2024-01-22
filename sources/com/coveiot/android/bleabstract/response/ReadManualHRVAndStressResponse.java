package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ManualHRVAndStressReading;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ReadManualHRVAndStressResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ManualHRVAndStressReading> f3650a;

    public ReadManualHRVAndStressResponse(@Nullable ArrayList<ManualHRVAndStressReading> arrayList) {
        this.f3650a = arrayList;
    }

    @Nullable
    public final ArrayList<ManualHRVAndStressReading> getManualHRVStressReadings() {
        return this.f3650a;
    }

    @NotNull
    public String toString() {
        return "ReadManualHRVAndStressResponse(manualHrvStressReadingList=" + this.f3650a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
