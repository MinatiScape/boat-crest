package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DoMoreWithYourWatchDataModel {
    @Nullable
    private DoMoreWithYourWatchFeatureType doMoreWithYourWatchType;
    @Nullable
    private String info;
    private boolean show;
    @Nullable
    private String title;

    public DoMoreWithYourWatchDataModel() {
        this(null, false, null, null, 15, null);
    }

    public DoMoreWithYourWatchDataModel(@Nullable DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType, boolean z, @Nullable String str, @Nullable String str2) {
        this.doMoreWithYourWatchType = doMoreWithYourWatchFeatureType;
        this.show = z;
        this.title = str;
        this.info = str2;
    }

    public static /* synthetic */ DoMoreWithYourWatchDataModel copy$default(DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            doMoreWithYourWatchFeatureType = doMoreWithYourWatchDataModel.doMoreWithYourWatchType;
        }
        if ((i & 2) != 0) {
            z = doMoreWithYourWatchDataModel.show;
        }
        if ((i & 4) != 0) {
            str = doMoreWithYourWatchDataModel.title;
        }
        if ((i & 8) != 0) {
            str2 = doMoreWithYourWatchDataModel.info;
        }
        return doMoreWithYourWatchDataModel.copy(doMoreWithYourWatchFeatureType, z, str, str2);
    }

    @Nullable
    public final DoMoreWithYourWatchFeatureType component1() {
        return this.doMoreWithYourWatchType;
    }

    public final boolean component2() {
        return this.show;
    }

    @Nullable
    public final String component3() {
        return this.title;
    }

    @Nullable
    public final String component4() {
        return this.info;
    }

    @NotNull
    public final DoMoreWithYourWatchDataModel copy(@Nullable DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType, boolean z, @Nullable String str, @Nullable String str2) {
        return new DoMoreWithYourWatchDataModel(doMoreWithYourWatchFeatureType, z, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DoMoreWithYourWatchDataModel) {
            DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel = (DoMoreWithYourWatchDataModel) obj;
            return this.doMoreWithYourWatchType == doMoreWithYourWatchDataModel.doMoreWithYourWatchType && this.show == doMoreWithYourWatchDataModel.show && Intrinsics.areEqual(this.title, doMoreWithYourWatchDataModel.title) && Intrinsics.areEqual(this.info, doMoreWithYourWatchDataModel.info);
        }
        return false;
    }

    @Nullable
    public final DoMoreWithYourWatchFeatureType getDoMoreWithYourWatchType() {
        return this.doMoreWithYourWatchType;
    }

    @Nullable
    public final String getInfo() {
        return this.info;
    }

    public final boolean getShow() {
        return this.show;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType = this.doMoreWithYourWatchType;
        int hashCode = (doMoreWithYourWatchFeatureType == null ? 0 : doMoreWithYourWatchFeatureType.hashCode()) * 31;
        boolean z = this.show;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        String str = this.title;
        int hashCode2 = (i2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.info;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setDoMoreWithYourWatchType(@Nullable DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType) {
        this.doMoreWithYourWatchType = doMoreWithYourWatchFeatureType;
    }

    public final void setInfo(@Nullable String str) {
        this.info = str;
    }

    public final void setShow(boolean z) {
        this.show = z;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    @NotNull
    public String toString() {
        return "DoMoreWithYourWatchDataModel(doMoreWithYourWatchType=" + this.doMoreWithYourWatchType + ", show=" + this.show + ", title=" + this.title + ", info=" + this.info + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType, boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : doMoreWithYourWatchFeatureType, (i & 2) != 0 ? true : z, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? "" : str2);
    }
}
