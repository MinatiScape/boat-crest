package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSOSRecordsItem {

    /* renamed from: a  reason: collision with root package name */
    public int f3619a;
    @Nullable
    public Long b;
    public int c;
    @Nullable
    public String d;
    public int e;
    @Nullable
    public String f;

    public GetSOSRecordsItem() {
        this(0, null, 0, null, 0, null, 63, null);
    }

    public GetSOSRecordsItem(int i, @Nullable Long l, int i2, @Nullable String str, int i3, @Nullable String str2) {
        this.f3619a = i;
        this.b = l;
        this.c = i2;
        this.d = str;
        this.e = i3;
        this.f = str2;
    }

    @Nullable
    public final String getContactName() {
        return this.d;
    }

    public final int getContactNameLength() {
        return this.c;
    }

    @Nullable
    public final String getContactNumber() {
        return this.f;
    }

    public final int getContactNumberLength() {
        return this.e;
    }

    public final int getStatus() {
        return this.f3619a;
    }

    @Nullable
    public final Long getUnixTimeStamp() {
        return this.b;
    }

    public final void setContactName(@Nullable String str) {
        this.d = str;
    }

    public final void setContactNameLength(int i) {
        this.c = i;
    }

    public final void setContactNumber(@Nullable String str) {
        this.f = str;
    }

    public final void setContactNumberLength(int i) {
        this.e = i;
    }

    public final void setStatus(int i) {
        this.f3619a = i;
    }

    public final void setUnixTimeStamp(@Nullable Long l) {
        this.b = l;
    }

    @NotNull
    public String toString() {
        return "GetSOSRecordsItem(status=" + this.f3619a + ", unixTimeStamp=" + this.b + ", contactNameLength=" + this.c + ", contactName=" + this.d + ", contactNumberLength=" + this.e + ", contactNumber=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ GetSOSRecordsItem(int i, Long l, int i2, String str, int i3, String str2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? null : l, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? null : str, (i4 & 16) == 0 ? i3 : 0, (i4 & 32) != 0 ? null : str2);
    }
}
