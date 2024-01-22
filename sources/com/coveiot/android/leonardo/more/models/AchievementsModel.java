package com.coveiot.android.leonardo.more.models;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AchievementsModel implements Serializable {
    @NotNull
    private final String achievementsName;
    @NotNull
    private final String achievementsValue;
    @NotNull
    private final Drawable image;

    public AchievementsModel(@NotNull Drawable image, @NotNull String achievementsValue, @NotNull String achievementsName) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(achievementsValue, "achievementsValue");
        Intrinsics.checkNotNullParameter(achievementsName, "achievementsName");
        this.image = image;
        this.achievementsValue = achievementsValue;
        this.achievementsName = achievementsName;
    }

    public static /* synthetic */ AchievementsModel copy$default(AchievementsModel achievementsModel, Drawable drawable, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = achievementsModel.image;
        }
        if ((i & 2) != 0) {
            str = achievementsModel.achievementsValue;
        }
        if ((i & 4) != 0) {
            str2 = achievementsModel.achievementsName;
        }
        return achievementsModel.copy(drawable, str, str2);
    }

    @NotNull
    public final Drawable component1() {
        return this.image;
    }

    @NotNull
    public final String component2() {
        return this.achievementsValue;
    }

    @NotNull
    public final String component3() {
        return this.achievementsName;
    }

    @NotNull
    public final AchievementsModel copy(@NotNull Drawable image, @NotNull String achievementsValue, @NotNull String achievementsName) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(achievementsValue, "achievementsValue");
        Intrinsics.checkNotNullParameter(achievementsName, "achievementsName");
        return new AchievementsModel(image, achievementsValue, achievementsName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AchievementsModel) {
            AchievementsModel achievementsModel = (AchievementsModel) obj;
            return Intrinsics.areEqual(this.image, achievementsModel.image) && Intrinsics.areEqual(this.achievementsValue, achievementsModel.achievementsValue) && Intrinsics.areEqual(this.achievementsName, achievementsModel.achievementsName);
        }
        return false;
    }

    @NotNull
    public final String getAchievementsName() {
        return this.achievementsName;
    }

    @NotNull
    public final String getAchievementsValue() {
        return this.achievementsValue;
    }

    @NotNull
    public final Drawable getImage() {
        return this.image;
    }

    public int hashCode() {
        return (((this.image.hashCode() * 31) + this.achievementsValue.hashCode()) * 31) + this.achievementsName.hashCode();
    }

    @NotNull
    public String toString() {
        return "AchievementsModel(image=" + this.image + ", achievementsValue=" + this.achievementsValue + ", achievementsName=" + this.achievementsName + HexStringBuilder.COMMENT_END_CHAR;
    }
}
