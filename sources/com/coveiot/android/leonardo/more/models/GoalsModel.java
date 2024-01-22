package com.coveiot.android.leonardo.more.models;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class GoalsModel implements Serializable {
    @NotNull
    private final String goalsName;
    private final int goalsTarget;
    private final int goalsValue;
    @NotNull
    private transient Drawable image;

    public GoalsModel(@NotNull Drawable image, int i, @NotNull String goalsName, int i2) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(goalsName, "goalsName");
        this.image = image;
        this.goalsValue = i;
        this.goalsName = goalsName;
        this.goalsTarget = i2;
    }

    public static /* synthetic */ GoalsModel copy$default(GoalsModel goalsModel, Drawable drawable, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            drawable = goalsModel.image;
        }
        if ((i3 & 2) != 0) {
            i = goalsModel.goalsValue;
        }
        if ((i3 & 4) != 0) {
            str = goalsModel.goalsName;
        }
        if ((i3 & 8) != 0) {
            i2 = goalsModel.goalsTarget;
        }
        return goalsModel.copy(drawable, i, str, i2);
    }

    @NotNull
    public final Drawable component1() {
        return this.image;
    }

    public final int component2() {
        return this.goalsValue;
    }

    @NotNull
    public final String component3() {
        return this.goalsName;
    }

    public final int component4() {
        return this.goalsTarget;
    }

    @NotNull
    public final GoalsModel copy(@NotNull Drawable image, int i, @NotNull String goalsName, int i2) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(goalsName, "goalsName");
        return new GoalsModel(image, i, goalsName, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoalsModel) {
            GoalsModel goalsModel = (GoalsModel) obj;
            return Intrinsics.areEqual(this.image, goalsModel.image) && this.goalsValue == goalsModel.goalsValue && Intrinsics.areEqual(this.goalsName, goalsModel.goalsName) && this.goalsTarget == goalsModel.goalsTarget;
        }
        return false;
    }

    @NotNull
    public final String getGoalsName() {
        return this.goalsName;
    }

    public final int getGoalsTarget() {
        return this.goalsTarget;
    }

    public final int getGoalsValue() {
        return this.goalsValue;
    }

    @NotNull
    public final Drawable getImage() {
        return this.image;
    }

    public int hashCode() {
        return (((((this.image.hashCode() * 31) + Integer.hashCode(this.goalsValue)) * 31) + this.goalsName.hashCode()) * 31) + Integer.hashCode(this.goalsTarget);
    }

    public final void setImage(@NotNull Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "<set-?>");
        this.image = drawable;
    }

    @NotNull
    public String toString() {
        return "GoalsModel(image=" + this.image + ", goalsValue=" + this.goalsValue + ", goalsName=" + this.goalsName + ", goalsTarget=" + this.goalsTarget + HexStringBuilder.COMMENT_END_CHAR;
    }
}
