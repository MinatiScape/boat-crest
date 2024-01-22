package com.coveiot.android.sportsnotification.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CoinNotifications {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5868a;
    @Nullable
    public String b;
    @Nullable
    public Integer c;
    @Nullable
    public String d;

    public CoinNotifications() {
        this(null, null, null, null, 15, null);
    }

    public CoinNotifications(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        this.f5868a = str;
        this.b = str2;
        this.c = num;
        this.d = str3;
    }

    public static /* synthetic */ CoinNotifications copy$default(CoinNotifications coinNotifications, String str, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coinNotifications.f5868a;
        }
        if ((i & 2) != 0) {
            str2 = coinNotifications.b;
        }
        if ((i & 4) != 0) {
            num = coinNotifications.c;
        }
        if ((i & 8) != 0) {
            str3 = coinNotifications.d;
        }
        return coinNotifications.copy(str, str2, num, str3);
    }

    @Nullable
    public final String component1() {
        return this.f5868a;
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
    public final CoinNotifications copy(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        return new CoinNotifications(str, str2, num, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CoinNotifications) {
            CoinNotifications coinNotifications = (CoinNotifications) obj;
            return Intrinsics.areEqual(this.f5868a, coinNotifications.f5868a) && Intrinsics.areEqual(this.b, coinNotifications.b) && Intrinsics.areEqual(this.c, coinNotifications.c) && Intrinsics.areEqual(this.d, coinNotifications.d);
        }
        return false;
    }

    @Nullable
    public final String getDescription() {
        return this.b;
    }

    @Nullable
    public final Integer getEarnedCoins() {
        return this.c;
    }

    @Nullable
    public final String getNotificationUrl() {
        return this.d;
    }

    @Nullable
    public final String getTitle() {
        return this.f5868a;
    }

    public int hashCode() {
        String str = this.f5868a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.c;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.d;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setDescription(@Nullable String str) {
        this.b = str;
    }

    public final void setEarnedCoins(@Nullable Integer num) {
        this.c = num;
    }

    public final void setNotificationUrl(@Nullable String str) {
        this.d = str;
    }

    public final void setTitle(@Nullable String str) {
        this.f5868a = str;
    }

    @NotNull
    public String toString() {
        return "CoinNotifications(title=" + this.f5868a + ", description=" + this.b + ", earnedCoins=" + this.c + ", notificationUrl=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ CoinNotifications(String str, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? null : str3);
    }
}
