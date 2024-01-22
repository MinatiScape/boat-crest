package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.WorldClock;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetWorldClockDataResponse implements Serializable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<WorldClock> f3629a;

    public GetWorldClockDataResponse() {
        this(null, 1, null);
    }

    public GetWorldClockDataResponse(@Nullable ArrayList<WorldClock> arrayList) {
        this.f3629a = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GetWorldClockDataResponse copy$default(GetWorldClockDataResponse getWorldClockDataResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = getWorldClockDataResponse.f3629a;
        }
        return getWorldClockDataResponse.copy(arrayList);
    }

    @Nullable
    public final ArrayList<WorldClock> component1() {
        return this.f3629a;
    }

    @NotNull
    public final GetWorldClockDataResponse copy(@Nullable ArrayList<WorldClock> arrayList) {
        return new GetWorldClockDataResponse(arrayList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetWorldClockDataResponse) && Intrinsics.areEqual(this.f3629a, ((GetWorldClockDataResponse) obj).f3629a);
    }

    @Nullable
    public final ArrayList<WorldClock> getWorldClockList() {
        return this.f3629a;
    }

    public int hashCode() {
        ArrayList<WorldClock> arrayList = this.f3629a;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.hashCode();
    }

    public final void setWorldClockList(@Nullable ArrayList<WorldClock> arrayList) {
        this.f3629a = arrayList;
    }

    @NotNull
    public String toString() {
        return "GetWorldClockDataResponse(worldClockList=" + this.f3629a + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ GetWorldClockDataResponse(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList);
    }
}
