package com.coveiot.covepreferences.data;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class AlexaLocale {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f7007a;
    @Nullable
    public String b;
    @Nullable
    public List<String> c;
    @Nullable
    public String d;

    public AlexaLocale() {
        this(null, null, null, null, 15, null);
    }

    public AlexaLocale(@Nullable String str, @Nullable String str2, @Nullable List<String> list, @Nullable String str3) {
        this.f7007a = str;
        this.b = str2;
        this.c = list;
        this.d = str3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AlexaLocale copy$default(AlexaLocale alexaLocale, String str, String str2, List list, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = alexaLocale.f7007a;
        }
        if ((i & 2) != 0) {
            str2 = alexaLocale.b;
        }
        if ((i & 4) != 0) {
            list = alexaLocale.c;
        }
        if ((i & 8) != 0) {
            str3 = alexaLocale.d;
        }
        return alexaLocale.copy(str, str2, list, str3);
    }

    @Nullable
    public final String component1() {
        return this.f7007a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final List<String> component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final AlexaLocale copy(@Nullable String str, @Nullable String str2, @Nullable List<String> list, @Nullable String str3) {
        return new AlexaLocale(str, str2, list, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlexaLocale) {
            AlexaLocale alexaLocale = (AlexaLocale) obj;
            return Intrinsics.areEqual(this.f7007a, alexaLocale.f7007a) && Intrinsics.areEqual(this.b, alexaLocale.b) && Intrinsics.areEqual(this.c, alexaLocale.c) && Intrinsics.areEqual(this.d, alexaLocale.d);
        }
        return false;
    }

    @Nullable
    public final List<String> getExamplePhrases() {
        return this.c;
    }

    @Nullable
    public final String getHelpUrl() {
        return this.d;
    }

    @Nullable
    public final String getLabel() {
        return this.b;
    }

    @Nullable
    public final String getLocale() {
        return this.f7007a;
    }

    public int hashCode() {
        String str = this.f7007a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.c;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.d;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setExamplePhrases(@Nullable List<String> list) {
        this.c = list;
    }

    public final void setHelpUrl(@Nullable String str) {
        this.d = str;
    }

    public final void setLabel(@Nullable String str) {
        this.b = str;
    }

    public final void setLocale(@Nullable String str) {
        this.f7007a = str;
    }

    @NotNull
    public String toString() {
        return "AlexaLocale(locale=" + this.f7007a + ", label=" + this.b + ", examplePhrases=" + this.c + ", helpUrl=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AlexaLocale(String str, String str2, List list, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str3);
    }
}
