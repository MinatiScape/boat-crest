package com.coveiot.covepreferences.data;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class AlexaDetails {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f7006a;
    @Nullable
    public final String b;
    @Nullable
    public final List<AlexaLocale> c;

    public AlexaDetails() {
        this(null, null, null, 7, null);
    }

    public AlexaDetails(@Nullable String str, @Nullable String str2, @Nullable List<AlexaLocale> list) {
        this.f7006a = str;
        this.b = str2;
        this.c = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AlexaDetails copy$default(AlexaDetails alexaDetails, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = alexaDetails.f7006a;
        }
        if ((i & 2) != 0) {
            str2 = alexaDetails.b;
        }
        if ((i & 4) != 0) {
            list = alexaDetails.c;
        }
        return alexaDetails.copy(str, str2, list);
    }

    @Nullable
    public final String component1() {
        return this.f7006a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final List<AlexaLocale> component3() {
        return this.c;
    }

    @NotNull
    public final AlexaDetails copy(@Nullable String str, @Nullable String str2, @Nullable List<AlexaLocale> list) {
        return new AlexaDetails(str, str2, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlexaDetails) {
            AlexaDetails alexaDetails = (AlexaDetails) obj;
            return Intrinsics.areEqual(this.f7006a, alexaDetails.f7006a) && Intrinsics.areEqual(this.b, alexaDetails.b) && Intrinsics.areEqual(this.c, alexaDetails.c);
        }
        return false;
    }

    @Nullable
    public final List<AlexaLocale> getLocales() {
        return this.c;
    }

    @Nullable
    public final String getLwaFallbackUrl() {
        return this.b;
    }

    @Nullable
    public final String getNativeAppUrl() {
        return this.f7006a;
    }

    public int hashCode() {
        String str = this.f7006a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<AlexaLocale> list = this.c;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "AlexaDetails(nativeAppUrl=" + this.f7006a + ", lwaFallbackUrl=" + this.b + ", locales=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AlexaDetails(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
    }
}
