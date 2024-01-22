package com.coveiot.android.leonardo.dashboard.health.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FitnessBlogsModel implements Serializable {
    @NotNull
    private final String fitnessBlogsDate;
    @NotNull
    private final String fitnessBlogsDesc;
    @NotNull
    private final String fitnessBlogsDuration;
    @NotNull
    private final String fitnessBlogsImage;
    @NotNull
    private final String fitnessBlogsType;

    public FitnessBlogsModel(@NotNull String fitnessBlogsImage, @NotNull String fitnessBlogsType, @NotNull String fitnessBlogsDuration, @NotNull String fitnessBlogsDesc, @NotNull String fitnessBlogsDate) {
        Intrinsics.checkNotNullParameter(fitnessBlogsImage, "fitnessBlogsImage");
        Intrinsics.checkNotNullParameter(fitnessBlogsType, "fitnessBlogsType");
        Intrinsics.checkNotNullParameter(fitnessBlogsDuration, "fitnessBlogsDuration");
        Intrinsics.checkNotNullParameter(fitnessBlogsDesc, "fitnessBlogsDesc");
        Intrinsics.checkNotNullParameter(fitnessBlogsDate, "fitnessBlogsDate");
        this.fitnessBlogsImage = fitnessBlogsImage;
        this.fitnessBlogsType = fitnessBlogsType;
        this.fitnessBlogsDuration = fitnessBlogsDuration;
        this.fitnessBlogsDesc = fitnessBlogsDesc;
        this.fitnessBlogsDate = fitnessBlogsDate;
    }

    public static /* synthetic */ FitnessBlogsModel copy$default(FitnessBlogsModel fitnessBlogsModel, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fitnessBlogsModel.fitnessBlogsImage;
        }
        if ((i & 2) != 0) {
            str2 = fitnessBlogsModel.fitnessBlogsType;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = fitnessBlogsModel.fitnessBlogsDuration;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = fitnessBlogsModel.fitnessBlogsDesc;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = fitnessBlogsModel.fitnessBlogsDate;
        }
        return fitnessBlogsModel.copy(str, str6, str7, str8, str5);
    }

    @NotNull
    public final String component1() {
        return this.fitnessBlogsImage;
    }

    @NotNull
    public final String component2() {
        return this.fitnessBlogsType;
    }

    @NotNull
    public final String component3() {
        return this.fitnessBlogsDuration;
    }

    @NotNull
    public final String component4() {
        return this.fitnessBlogsDesc;
    }

    @NotNull
    public final String component5() {
        return this.fitnessBlogsDate;
    }

    @NotNull
    public final FitnessBlogsModel copy(@NotNull String fitnessBlogsImage, @NotNull String fitnessBlogsType, @NotNull String fitnessBlogsDuration, @NotNull String fitnessBlogsDesc, @NotNull String fitnessBlogsDate) {
        Intrinsics.checkNotNullParameter(fitnessBlogsImage, "fitnessBlogsImage");
        Intrinsics.checkNotNullParameter(fitnessBlogsType, "fitnessBlogsType");
        Intrinsics.checkNotNullParameter(fitnessBlogsDuration, "fitnessBlogsDuration");
        Intrinsics.checkNotNullParameter(fitnessBlogsDesc, "fitnessBlogsDesc");
        Intrinsics.checkNotNullParameter(fitnessBlogsDate, "fitnessBlogsDate");
        return new FitnessBlogsModel(fitnessBlogsImage, fitnessBlogsType, fitnessBlogsDuration, fitnessBlogsDesc, fitnessBlogsDate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessBlogsModel) {
            FitnessBlogsModel fitnessBlogsModel = (FitnessBlogsModel) obj;
            return Intrinsics.areEqual(this.fitnessBlogsImage, fitnessBlogsModel.fitnessBlogsImage) && Intrinsics.areEqual(this.fitnessBlogsType, fitnessBlogsModel.fitnessBlogsType) && Intrinsics.areEqual(this.fitnessBlogsDuration, fitnessBlogsModel.fitnessBlogsDuration) && Intrinsics.areEqual(this.fitnessBlogsDesc, fitnessBlogsModel.fitnessBlogsDesc) && Intrinsics.areEqual(this.fitnessBlogsDate, fitnessBlogsModel.fitnessBlogsDate);
        }
        return false;
    }

    @NotNull
    public final String getFitnessBlogsDate() {
        return this.fitnessBlogsDate;
    }

    @NotNull
    public final String getFitnessBlogsDesc() {
        return this.fitnessBlogsDesc;
    }

    @NotNull
    public final String getFitnessBlogsDuration() {
        return this.fitnessBlogsDuration;
    }

    @NotNull
    public final String getFitnessBlogsImage() {
        return this.fitnessBlogsImage;
    }

    @NotNull
    public final String getFitnessBlogsType() {
        return this.fitnessBlogsType;
    }

    public int hashCode() {
        return (((((((this.fitnessBlogsImage.hashCode() * 31) + this.fitnessBlogsType.hashCode()) * 31) + this.fitnessBlogsDuration.hashCode()) * 31) + this.fitnessBlogsDesc.hashCode()) * 31) + this.fitnessBlogsDate.hashCode();
    }

    @NotNull
    public String toString() {
        return "FitnessBlogsModel(fitnessBlogsImage=" + this.fitnessBlogsImage + ", fitnessBlogsType=" + this.fitnessBlogsType + ", fitnessBlogsDuration=" + this.fitnessBlogsDuration + ", fitnessBlogsDesc=" + this.fitnessBlogsDesc + ", fitnessBlogsDate=" + this.fitnessBlogsDate + HexStringBuilder.COMMENT_END_CHAR;
    }
}
