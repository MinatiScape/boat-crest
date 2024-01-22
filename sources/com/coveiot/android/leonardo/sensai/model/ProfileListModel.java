package com.coveiot.android.leonardo.sensai.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ProfileListModel {
    private boolean isDataPresent;
    @NotNull
    private String profile;

    public ProfileListModel(boolean z, @NotNull String profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        this.isDataPresent = z;
        this.profile = profile;
    }

    public static /* synthetic */ ProfileListModel copy$default(ProfileListModel profileListModel, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = profileListModel.isDataPresent;
        }
        if ((i & 2) != 0) {
            str = profileListModel.profile;
        }
        return profileListModel.copy(z, str);
    }

    public final boolean component1() {
        return this.isDataPresent;
    }

    @NotNull
    public final String component2() {
        return this.profile;
    }

    @NotNull
    public final ProfileListModel copy(boolean z, @NotNull String profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        return new ProfileListModel(z, profile);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProfileListModel) {
            ProfileListModel profileListModel = (ProfileListModel) obj;
            return this.isDataPresent == profileListModel.isDataPresent && Intrinsics.areEqual(this.profile, profileListModel.profile);
        }
        return false;
    }

    @NotNull
    public final String getProfile() {
        return this.profile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.isDataPresent;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.profile.hashCode();
    }

    public final boolean isDataPresent() {
        return this.isDataPresent;
    }

    public final void setDataPresent(boolean z) {
        this.isDataPresent = z;
    }

    public final void setProfile(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profile = str;
    }

    @NotNull
    public String toString() {
        return "ProfileListModel(isDataPresent=" + this.isDataPresent + ", profile=" + this.profile + HexStringBuilder.COMMENT_END_CHAR;
    }
}
