package com.android.installreferrer.api;

import android.os.Bundle;
/* loaded from: classes.dex */
public class ReferrerDetails {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f2141a;

    public ReferrerDetails(Bundle bundle) {
        this.f2141a = bundle;
    }

    public boolean getGooglePlayInstantParam() {
        return this.f2141a.getBoolean("google_play_instant");
    }

    public long getInstallBeginTimestampSeconds() {
        return this.f2141a.getLong("install_begin_timestamp_seconds");
    }

    public long getInstallBeginTimestampServerSeconds() {
        return this.f2141a.getLong("install_begin_timestamp_server_seconds");
    }

    public String getInstallReferrer() {
        return this.f2141a.getString("install_referrer");
    }

    public String getInstallVersion() {
        return this.f2141a.getString("install_version");
    }

    public long getReferrerClickTimestampSeconds() {
        return this.f2141a.getLong("referrer_click_timestamp_seconds");
    }

    public long getReferrerClickTimestampServerSeconds() {
        return this.f2141a.getLong("referrer_click_timestamp_server_seconds");
    }
}
