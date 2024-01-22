package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AdditionalActivitiesModel {

    /* renamed from: a  reason: collision with root package name */
    public final int f4849a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public final String f;

    public AdditionalActivitiesModel(int i, @NotNull String activityName, @NotNull String sessionType, @NotNull String activitySite, @NotNull String activityIconUrl, @NotNull String activityBinUrl) {
        Intrinsics.checkNotNullParameter(activityName, "activityName");
        Intrinsics.checkNotNullParameter(sessionType, "sessionType");
        Intrinsics.checkNotNullParameter(activitySite, "activitySite");
        Intrinsics.checkNotNullParameter(activityIconUrl, "activityIconUrl");
        Intrinsics.checkNotNullParameter(activityBinUrl, "activityBinUrl");
        this.f4849a = i;
        this.b = activityName;
        this.c = sessionType;
        this.d = activitySite;
        this.e = activityIconUrl;
        this.f = activityBinUrl;
    }

    public static /* synthetic */ AdditionalActivitiesModel copy$default(AdditionalActivitiesModel additionalActivitiesModel, int i, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = additionalActivitiesModel.f4849a;
        }
        if ((i2 & 2) != 0) {
            str = additionalActivitiesModel.b;
        }
        String str6 = str;
        if ((i2 & 4) != 0) {
            str2 = additionalActivitiesModel.c;
        }
        String str7 = str2;
        if ((i2 & 8) != 0) {
            str3 = additionalActivitiesModel.d;
        }
        String str8 = str3;
        if ((i2 & 16) != 0) {
            str4 = additionalActivitiesModel.e;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = additionalActivitiesModel.f;
        }
        return additionalActivitiesModel.copy(i, str6, str7, str8, str9, str5);
    }

    public final int component1() {
        return this.f4849a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final AdditionalActivitiesModel copy(int i, @NotNull String activityName, @NotNull String sessionType, @NotNull String activitySite, @NotNull String activityIconUrl, @NotNull String activityBinUrl) {
        Intrinsics.checkNotNullParameter(activityName, "activityName");
        Intrinsics.checkNotNullParameter(sessionType, "sessionType");
        Intrinsics.checkNotNullParameter(activitySite, "activitySite");
        Intrinsics.checkNotNullParameter(activityIconUrl, "activityIconUrl");
        Intrinsics.checkNotNullParameter(activityBinUrl, "activityBinUrl");
        return new AdditionalActivitiesModel(i, activityName, sessionType, activitySite, activityIconUrl, activityBinUrl);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdditionalActivitiesModel) {
            AdditionalActivitiesModel additionalActivitiesModel = (AdditionalActivitiesModel) obj;
            return this.f4849a == additionalActivitiesModel.f4849a && Intrinsics.areEqual(this.b, additionalActivitiesModel.b) && Intrinsics.areEqual(this.c, additionalActivitiesModel.c) && Intrinsics.areEqual(this.d, additionalActivitiesModel.d) && Intrinsics.areEqual(this.e, additionalActivitiesModel.e) && Intrinsics.areEqual(this.f, additionalActivitiesModel.f);
        }
        return false;
    }

    @NotNull
    public final String getActivityBinUrl() {
        return this.f;
    }

    @NotNull
    public final String getActivityIconUrl() {
        return this.e;
    }

    @NotNull
    public final String getActivityName() {
        return this.b;
    }

    @NotNull
    public final String getActivitySite() {
        return this.d;
    }

    public final int getId() {
        return this.f4849a;
    }

    @NotNull
    public final String getSessionType() {
        return this.c;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.f4849a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    @NotNull
    public String toString() {
        return "AdditionalActivitiesModel(id=" + this.f4849a + ", activityName=" + this.b + ", sessionType=" + this.c + ", activitySite=" + this.d + ", activityIconUrl=" + this.e + ", activityBinUrl=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }
}
