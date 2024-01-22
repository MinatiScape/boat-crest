package com.coveiot.android.respiratoryrate;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes6.dex */
public class RespiratoryRatePreferenceManager {
    public static final String LAST_RUN_TIMESTAMP = "last_run_timestamp";

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f5663a;
    public static SharedPreferences.Editor b;
    public static int c;
    public static RespiratoryRatePreferenceManager d;

    public static RespiratoryRatePreferenceManager getInstance(Context context) {
        if (d == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("respiratory_rate_preference", c);
            f5663a = sharedPreferences;
            b = sharedPreferences.edit();
            d = new RespiratoryRatePreferenceManager();
        }
        return d;
    }

    public long getLastRunTimestamp() {
        return f5663a.getLong(LAST_RUN_TIMESTAMP, 0L);
    }

    public void saveLastRunTimestamp(long j) {
        b.putLong(LAST_RUN_TIMESTAMP, j);
        b.commit();
    }
}
