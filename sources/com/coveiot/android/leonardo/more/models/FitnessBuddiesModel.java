package com.coveiot.android.leonardo.more.models;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FitnessBuddiesModel implements Serializable {
    @NotNull
    private final String fitnessBuddiesName;
    @NotNull
    private final List<FitnessBuddiesSubModel> fitnessBuddiesSubList;
    @NotNull
    private final Drawable image;

    public FitnessBuddiesModel(@NotNull Drawable image, @NotNull String fitnessBuddiesName, @NotNull List<FitnessBuddiesSubModel> fitnessBuddiesSubList) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(fitnessBuddiesName, "fitnessBuddiesName");
        Intrinsics.checkNotNullParameter(fitnessBuddiesSubList, "fitnessBuddiesSubList");
        this.image = image;
        this.fitnessBuddiesName = fitnessBuddiesName;
        this.fitnessBuddiesSubList = fitnessBuddiesSubList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessBuddiesModel copy$default(FitnessBuddiesModel fitnessBuddiesModel, Drawable drawable, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = fitnessBuddiesModel.image;
        }
        if ((i & 2) != 0) {
            str = fitnessBuddiesModel.fitnessBuddiesName;
        }
        if ((i & 4) != 0) {
            list = fitnessBuddiesModel.fitnessBuddiesSubList;
        }
        return fitnessBuddiesModel.copy(drawable, str, list);
    }

    @NotNull
    public final Drawable component1() {
        return this.image;
    }

    @NotNull
    public final String component2() {
        return this.fitnessBuddiesName;
    }

    @NotNull
    public final List<FitnessBuddiesSubModel> component3() {
        return this.fitnessBuddiesSubList;
    }

    @NotNull
    public final FitnessBuddiesModel copy(@NotNull Drawable image, @NotNull String fitnessBuddiesName, @NotNull List<FitnessBuddiesSubModel> fitnessBuddiesSubList) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(fitnessBuddiesName, "fitnessBuddiesName");
        Intrinsics.checkNotNullParameter(fitnessBuddiesSubList, "fitnessBuddiesSubList");
        return new FitnessBuddiesModel(image, fitnessBuddiesName, fitnessBuddiesSubList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessBuddiesModel) {
            FitnessBuddiesModel fitnessBuddiesModel = (FitnessBuddiesModel) obj;
            return Intrinsics.areEqual(this.image, fitnessBuddiesModel.image) && Intrinsics.areEqual(this.fitnessBuddiesName, fitnessBuddiesModel.fitnessBuddiesName) && Intrinsics.areEqual(this.fitnessBuddiesSubList, fitnessBuddiesModel.fitnessBuddiesSubList);
        }
        return false;
    }

    @NotNull
    public final String getFitnessBuddiesName() {
        return this.fitnessBuddiesName;
    }

    @NotNull
    public final List<FitnessBuddiesSubModel> getFitnessBuddiesSubList() {
        return this.fitnessBuddiesSubList;
    }

    @NotNull
    public final Drawable getImage() {
        return this.image;
    }

    public int hashCode() {
        return (((this.image.hashCode() * 31) + this.fitnessBuddiesName.hashCode()) * 31) + this.fitnessBuddiesSubList.hashCode();
    }

    @NotNull
    public String toString() {
        return "FitnessBuddiesModel(image=" + this.image + ", fitnessBuddiesName=" + this.fitnessBuddiesName + ", fitnessBuddiesSubList=" + this.fitnessBuddiesSubList + HexStringBuilder.COMMENT_END_CHAR;
    }
}
