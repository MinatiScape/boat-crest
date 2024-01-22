package com.coveiot.android.leonardo.more.models;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ProfileMoreModel implements Serializable {
    @NotNull
    private final Drawable image;
    @NotNull
    private final String name;

    public ProfileMoreModel(@NotNull Drawable image, @NotNull String name) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(name, "name");
        this.image = image;
        this.name = name;
    }

    public static /* synthetic */ ProfileMoreModel copy$default(ProfileMoreModel profileMoreModel, Drawable drawable, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = profileMoreModel.image;
        }
        if ((i & 2) != 0) {
            str = profileMoreModel.name;
        }
        return profileMoreModel.copy(drawable, str);
    }

    @NotNull
    public final Drawable component1() {
        return this.image;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final ProfileMoreModel copy(@NotNull Drawable image, @NotNull String name) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ProfileMoreModel(image, name);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProfileMoreModel) {
            ProfileMoreModel profileMoreModel = (ProfileMoreModel) obj;
            return Intrinsics.areEqual(this.image, profileMoreModel.image) && Intrinsics.areEqual(this.name, profileMoreModel.name);
        }
        return false;
    }

    @NotNull
    public final Drawable getImage() {
        return this.image;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.image.hashCode() * 31) + this.name.hashCode();
    }

    @NotNull
    public String toString() {
        return "ProfileMoreModel(image=" + this.image + ", name=" + this.name + HexStringBuilder.COMMENT_END_CHAR;
    }
}
