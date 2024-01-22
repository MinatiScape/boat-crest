package com.coveiot.android.leonardo.more.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FitnessBuddiesSubModel implements Serializable {
    @NotNull
    private final String fitnessBuddiesSubHeader;

    public FitnessBuddiesSubModel(@NotNull String fitnessBuddiesSubHeader) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesSubHeader, "fitnessBuddiesSubHeader");
        this.fitnessBuddiesSubHeader = fitnessBuddiesSubHeader;
    }

    public static /* synthetic */ FitnessBuddiesSubModel copy$default(FitnessBuddiesSubModel fitnessBuddiesSubModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fitnessBuddiesSubModel.fitnessBuddiesSubHeader;
        }
        return fitnessBuddiesSubModel.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.fitnessBuddiesSubHeader;
    }

    @NotNull
    public final FitnessBuddiesSubModel copy(@NotNull String fitnessBuddiesSubHeader) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesSubHeader, "fitnessBuddiesSubHeader");
        return new FitnessBuddiesSubModel(fitnessBuddiesSubHeader);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FitnessBuddiesSubModel) && Intrinsics.areEqual(this.fitnessBuddiesSubHeader, ((FitnessBuddiesSubModel) obj).fitnessBuddiesSubHeader);
    }

    @NotNull
    public final String getFitnessBuddiesSubHeader() {
        return this.fitnessBuddiesSubHeader;
    }

    public int hashCode() {
        return this.fitnessBuddiesSubHeader.hashCode();
    }

    @NotNull
    public String toString() {
        return "FitnessBuddiesSubModel(fitnessBuddiesSubHeader=" + this.fitnessBuddiesSubHeader + HexStringBuilder.COMMENT_END_CHAR;
    }
}
