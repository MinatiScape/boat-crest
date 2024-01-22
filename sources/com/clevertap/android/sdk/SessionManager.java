package com.clevertap.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.events.EventDetail;
import com.clevertap.android.sdk.validation.Validator;
/* loaded from: classes2.dex */
public class SessionManager extends e {

    /* renamed from: a  reason: collision with root package name */
    public long f2585a = 0;
    public int b;
    public final CoreMetaData c;
    public final CleverTapInstanceConfig d;
    public final LocalDataStore e;
    public final Validator f;

    public SessionManager(CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, Validator validator, LocalDataStore localDataStore) {
        this.d = cleverTapInstanceConfig;
        this.c = coreMetaData;
        this.f = validator;
        this.e = localDataStore;
    }

    public final void a(Context context) {
        this.c.i(b());
        Logger logger = this.d.getLogger();
        String accountId = this.d.getAccountId();
        logger.verbose(accountId, "Session created with ID: " + this.c.getCurrentSessionId());
        SharedPreferences preferences = StorageHelper.getPreferences(context);
        int intFromPrefs = StorageHelper.getIntFromPrefs(context, this.d, Constants.SESSION_ID_LAST, 0);
        int intFromPrefs2 = StorageHelper.getIntFromPrefs(context, this.d, Constants.LAST_SESSION_EPOCH, 0);
        if (intFromPrefs2 > 0) {
            this.c.m(intFromPrefs2 - intFromPrefs);
        }
        Logger logger2 = this.d.getLogger();
        String accountId2 = this.d.getAccountId();
        logger2.verbose(accountId2, "Last session length: " + this.c.getLastSessionLength() + " seconds");
        if (intFromPrefs == 0) {
            this.c.j(true);
        }
        StorageHelper.persist(preferences.edit().putInt(StorageHelper.storageKeyWithSuffix(this.d, Constants.SESSION_ID_LAST), this.c.getCurrentSessionId()));
    }

    public int b() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public void c() {
        EventDetail s = this.e.s(Constants.APP_LAUNCHED_EVENT);
        if (s == null) {
            this.b = -1;
        } else {
            this.b = s.getLastTime();
        }
    }

    public void checkTimeoutSession() {
        if (this.f2585a > 0 && System.currentTimeMillis() - this.f2585a > 1200000) {
            this.d.getLogger().verbose(this.d.getAccountId(), "Session Timed Out");
            destroySession();
        }
    }

    public void destroySession() {
        this.c.i(0);
        this.c.g(false);
        if (this.c.isFirstSession()) {
            this.c.j(false);
        }
        this.d.getLogger().verbose(this.d.getAccountId(), "Session destroyed; Session ID is now 0");
        this.c.c();
        this.c.b();
        this.c.a();
        this.c.d();
    }

    public long getAppLastSeen() {
        return this.f2585a;
    }

    public int getLastVisitTime() {
        return this.b;
    }

    public void lazyCreateSession(Context context) {
        if (this.c.inCurrentSession()) {
            return;
        }
        this.c.setFirstRequestInSession(true);
        Validator validator = this.f;
        if (validator != null) {
            validator.setDiscardedEvents(null);
        }
        a(context);
    }

    public void setAppLastSeen(long j) {
        this.f2585a = j;
    }
}
