package com.coveiot.android.qrtray.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchAndServerQRData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<QRCodeDataApp> f5577a;
    @NotNull
    public ArrayList<QRCodeDataApp> b;
    @NotNull
    public ArrayList<QRCodeDataApp> c;

    public WatchAndServerQRData() {
        this(null, null, null, 7, null);
    }

    public WatchAndServerQRData(@NotNull ArrayList<QRCodeDataApp> watchQrCodes, @NotNull ArrayList<QRCodeDataApp> serverAppliedQrCodes, @NotNull ArrayList<QRCodeDataApp> serverUnAppliedQrCodes) {
        Intrinsics.checkNotNullParameter(watchQrCodes, "watchQrCodes");
        Intrinsics.checkNotNullParameter(serverAppliedQrCodes, "serverAppliedQrCodes");
        Intrinsics.checkNotNullParameter(serverUnAppliedQrCodes, "serverUnAppliedQrCodes");
        this.f5577a = watchQrCodes;
        this.b = serverAppliedQrCodes;
        this.c = serverUnAppliedQrCodes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WatchAndServerQRData copy$default(WatchAndServerQRData watchAndServerQRData, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = watchAndServerQRData.f5577a;
        }
        if ((i & 2) != 0) {
            arrayList2 = watchAndServerQRData.b;
        }
        if ((i & 4) != 0) {
            arrayList3 = watchAndServerQRData.c;
        }
        return watchAndServerQRData.copy(arrayList, arrayList2, arrayList3);
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> component1() {
        return this.f5577a;
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> component2() {
        return this.b;
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> component3() {
        return this.c;
    }

    @NotNull
    public final WatchAndServerQRData copy(@NotNull ArrayList<QRCodeDataApp> watchQrCodes, @NotNull ArrayList<QRCodeDataApp> serverAppliedQrCodes, @NotNull ArrayList<QRCodeDataApp> serverUnAppliedQrCodes) {
        Intrinsics.checkNotNullParameter(watchQrCodes, "watchQrCodes");
        Intrinsics.checkNotNullParameter(serverAppliedQrCodes, "serverAppliedQrCodes");
        Intrinsics.checkNotNullParameter(serverUnAppliedQrCodes, "serverUnAppliedQrCodes");
        return new WatchAndServerQRData(watchQrCodes, serverAppliedQrCodes, serverUnAppliedQrCodes);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchAndServerQRData) {
            WatchAndServerQRData watchAndServerQRData = (WatchAndServerQRData) obj;
            return Intrinsics.areEqual(this.f5577a, watchAndServerQRData.f5577a) && Intrinsics.areEqual(this.b, watchAndServerQRData.b) && Intrinsics.areEqual(this.c, watchAndServerQRData.c);
        }
        return false;
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> getServerAppliedQrCodes() {
        return this.b;
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> getServerUnAppliedQrCodes() {
        return this.c;
    }

    @NotNull
    public final ArrayList<QRCodeDataApp> getWatchQrCodes() {
        return this.f5577a;
    }

    public int hashCode() {
        return (((this.f5577a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public final void setServerAppliedQrCodes(@NotNull ArrayList<QRCodeDataApp> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setServerUnAppliedQrCodes(@NotNull ArrayList<QRCodeDataApp> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.c = arrayList;
    }

    public final void setWatchQrCodes(@NotNull ArrayList<QRCodeDataApp> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f5577a = arrayList;
    }

    @NotNull
    public String toString() {
        return "WatchAndServerQRData(watchQrCodes=" + this.f5577a + ", serverAppliedQrCodes=" + this.b + ", serverUnAppliedQrCodes=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchAndServerQRData(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList, (i & 2) != 0 ? new ArrayList() : arrayList2, (i & 4) != 0 ? new ArrayList() : arrayList3);
    }
}
