package com.coveiot.android.leonardo.dashboard.socialshare.model;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BadgeShareData extends ShareData {
    @Nullable
    private String badgeDescription;
    @Nullable
    private String badgeName;

    @Nullable
    public final String getBadgeDescription() {
        return this.badgeDescription;
    }

    @Nullable
    public final String getBadgeName() {
        return this.badgeName;
    }

    public final void setBadgeDescription(@Nullable String str) {
        this.badgeDescription = str;
    }

    public final void setBadgeName(@Nullable String str) {
        this.badgeName = str;
    }
}
