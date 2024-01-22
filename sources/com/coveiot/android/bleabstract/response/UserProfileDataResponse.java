package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class UserProfileDataResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public UserProfileData f3682a;
    public boolean b;

    @Nullable
    public final UserProfileData getUserProfileData() {
        return this.f3682a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setUserProfileData(@Nullable UserProfileData userProfileData) {
        this.f3682a = userProfileData;
    }

    @NotNull
    public String toString() {
        return "UserProfileDataResponse(userProfileData=" + this.f3682a + ", isComplete=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
