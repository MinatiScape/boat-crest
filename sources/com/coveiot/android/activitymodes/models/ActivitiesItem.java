package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivitiesItem {
    @SerializedName("shareIconUrl")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final String f2844a;
    @SerializedName("sessionType")
    @Nullable
    private final String b;
    @SerializedName("id")
    @Nullable
    private final Integer c;
    @SerializedName("iconUrl")
    @Nullable
    private final String d;
    @SerializedName("activitySite")
    @Nullable
    private final String e;
    @SerializedName("activityName")
    @Nullable
    private final String f;
    @SerializedName("binFileUrl")
    @Nullable
    private final String g;

    public ActivitiesItem() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public ActivitiesItem(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        this.f2844a = str;
        this.b = str2;
        this.c = num;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
    }

    public static /* synthetic */ ActivitiesItem copy$default(ActivitiesItem activitiesItem, String str, String str2, Integer num, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = activitiesItem.f2844a;
        }
        if ((i & 2) != 0) {
            str2 = activitiesItem.b;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            num = activitiesItem.c;
        }
        Integer num2 = num;
        if ((i & 8) != 0) {
            str3 = activitiesItem.d;
        }
        String str8 = str3;
        if ((i & 16) != 0) {
            str4 = activitiesItem.e;
        }
        String str9 = str4;
        if ((i & 32) != 0) {
            str5 = activitiesItem.f;
        }
        String str10 = str5;
        if ((i & 64) != 0) {
            str6 = activitiesItem.g;
        }
        return activitiesItem.copy(str, str7, num2, str8, str9, str10, str6);
    }

    @Nullable
    public final String component1() {
        return this.f2844a;
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

    @Nullable
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final String component7() {
        return this.g;
    }

    @NotNull
    public final ActivitiesItem copy(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        return new ActivitiesItem(str, str2, num, str3, str4, str5, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivitiesItem) {
            ActivitiesItem activitiesItem = (ActivitiesItem) obj;
            return Intrinsics.areEqual(this.f2844a, activitiesItem.f2844a) && Intrinsics.areEqual(this.b, activitiesItem.b) && Intrinsics.areEqual(this.c, activitiesItem.c) && Intrinsics.areEqual(this.d, activitiesItem.d) && Intrinsics.areEqual(this.e, activitiesItem.e) && Intrinsics.areEqual(this.f, activitiesItem.f) && Intrinsics.areEqual(this.g, activitiesItem.g);
        }
        return false;
    }

    @Nullable
    public final String getActivityName() {
        return this.f;
    }

    @Nullable
    public final String getActivitySite() {
        return this.e;
    }

    @Nullable
    public final String getBinFileUrl() {
        return this.g;
    }

    @Nullable
    public final String getIconUrl() {
        return this.d;
    }

    @Nullable
    public final Integer getId() {
        return this.c;
    }

    @Nullable
    public final String getSessionType() {
        return this.b;
    }

    @Nullable
    public final String getShareIconUrl() {
        return this.f2844a;
    }

    public int hashCode() {
        String str = this.f2844a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.c;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.d;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.e;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.g;
        return hashCode6 + (str6 != null ? str6.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ActivitiesItem(shareIconUrl=" + this.f2844a + ", sessionType=" + this.b + ", id=" + this.c + ", iconUrl=" + this.d + ", activitySite=" + this.e + ") , activityName=" + this.f + ") , binFileUrl=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ActivitiesItem(String str, String str2, Integer num, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6);
    }
}
