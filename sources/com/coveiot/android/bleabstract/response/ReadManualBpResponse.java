package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ReadManualBpResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ManualBpReading> f3649a;

    public ReadManualBpResponse(@Nullable ArrayList<ManualBpReading> arrayList) {
        this.f3649a = arrayList;
    }

    @Nullable
    public final ArrayList<ManualBpReading> getManualBpReadings() {
        return this.f3649a;
    }

    @NotNull
    public String toString() {
        return "ReadManualBpResponse(manualBpReadingList=" + this.f3649a + HexStringBuilder.COMMENT_END_CHAR;
    }
}
