package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ReadManualSpo2Response {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3651a;
    @Nullable
    public ArrayList<ManualSpo2Reading> b;

    public ReadManualSpo2Response(@Nullable ArrayList<ManualSpo2Reading> arrayList) {
        this.b = arrayList;
    }

    @Nullable
    public final ArrayList<ManualSpo2Reading> getManualSpo2Readings() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3651a;
    }

    public final void setComplete(boolean z) {
        this.f3651a = z;
    }

    @NotNull
    public String toString() {
        return "ReadManualSpo2Response(manualSpo2ReadingList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
