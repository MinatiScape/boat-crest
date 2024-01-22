package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ShowHideTypeModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4858a;
    @Nullable
    public Integer b;
    @Nullable
    public String c;

    public ShowHideTypeModel(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        this.f4858a = str;
        this.b = num;
        this.c = str2;
    }

    public static /* synthetic */ ShowHideTypeModel copy$default(ShowHideTypeModel showHideTypeModel, String str, Integer num, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = showHideTypeModel.f4858a;
        }
        if ((i & 2) != 0) {
            num = showHideTypeModel.b;
        }
        if ((i & 4) != 0) {
            str2 = showHideTypeModel.c;
        }
        return showHideTypeModel.copy(str, num, str2);
    }

    @Nullable
    public final String component1() {
        return this.f4858a;
    }

    @Nullable
    public final Integer component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final ShowHideTypeModel copy(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        return new ShowHideTypeModel(str, num, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ShowHideTypeModel) {
            ShowHideTypeModel showHideTypeModel = (ShowHideTypeModel) obj;
            return Intrinsics.areEqual(this.f4858a, showHideTypeModel.f4858a) && Intrinsics.areEqual(this.b, showHideTypeModel.b) && Intrinsics.areEqual(this.c, showHideTypeModel.c);
        }
        return false;
    }

    @Nullable
    public final Integer getHideShowIcon() {
        return this.b;
    }

    @Nullable
    public final String getShowOrHideText() {
        return this.c;
    }

    @Nullable
    public final String getTypeName() {
        return this.f4858a;
    }

    public int hashCode() {
        String str = this.f4858a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.b;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.c;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setHideShowIcon(@Nullable Integer num) {
        this.b = num;
    }

    public final void setShowOrHideText(@Nullable String str) {
        this.c = str;
    }

    public final void setTypeName(@Nullable String str) {
        this.f4858a = str;
    }

    @NotNull
    public String toString() {
        return "ShowHideTypeModel(typeName=" + this.f4858a + ", hideShowIcon=" + this.b + ", showOrHideText=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
