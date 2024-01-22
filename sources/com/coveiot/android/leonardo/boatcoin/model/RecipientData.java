package com.coveiot.android.leonardo.boatcoin.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class RecipientData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4656a;
    @Nullable
    public String b;
    @Nullable
    public Integer c;
    @Nullable
    public String d;

    public RecipientData() {
        this(null, null, null, null, 15, null);
    }

    public RecipientData(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        this.f4656a = str;
        this.b = str2;
        this.c = num;
        this.d = str3;
    }

    public static /* synthetic */ RecipientData copy$default(RecipientData recipientData, String str, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = recipientData.f4656a;
        }
        if ((i & 2) != 0) {
            str2 = recipientData.b;
        }
        if ((i & 4) != 0) {
            num = recipientData.c;
        }
        if ((i & 8) != 0) {
            str3 = recipientData.d;
        }
        return recipientData.copy(str, str2, num, str3);
    }

    @Nullable
    public final String component1() {
        return this.f4656a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final RecipientData copy(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        return new RecipientData(str, str2, num, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RecipientData) {
            RecipientData recipientData = (RecipientData) obj;
            return Intrinsics.areEqual(this.f4656a, recipientData.f4656a) && Intrinsics.areEqual(this.b, recipientData.b) && Intrinsics.areEqual(this.c, recipientData.c) && Intrinsics.areEqual(this.d, recipientData.d);
        }
        return false;
    }

    @Nullable
    public final Integer getCoins() {
        return this.c;
    }

    @Nullable
    public final String getInviteText() {
        return this.d;
    }

    @Nullable
    public final String getMobileNumber() {
        return this.b;
    }

    @Nullable
    public final String getName() {
        return this.f4656a;
    }

    public int hashCode() {
        String str = this.f4656a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.c;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.d;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setCoins(@Nullable Integer num) {
        this.c = num;
    }

    public final void setInviteText(@Nullable String str) {
        this.d = str;
    }

    public final void setMobileNumber(@Nullable String str) {
        this.b = str;
    }

    public final void setName(@Nullable String str) {
        this.f4656a = str;
    }

    @NotNull
    public String toString() {
        return "RecipientData(name=" + this.f4656a + ", mobileNumber=" + this.b + ", coins=" + this.c + ", inviteText=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ RecipientData(String str, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? null : str3);
    }
}
