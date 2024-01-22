package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.SedentaryData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ReadSedentaryResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3652a;
    @Nullable
    public ArrayList<SedentaryData> b;

    public ReadSedentaryResponse(@Nullable ArrayList<SedentaryData> arrayList) {
        this.b = arrayList;
    }

    @Nullable
    public final ArrayList<SedentaryData> getSedentaryData() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3652a;
    }

    public final void setComplete(boolean z) {
        this.f3652a = z;
    }

    @NotNull
    public String toString() {
        return "ReadSedentaryResponse(sedentaryDataList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
