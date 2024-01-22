package com.mappls.android.lms;

import android.content.Context;
import android.content.SharedPreferences;
import com.mappls.android.util.MPLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class PersistentIdentity {
    private static final String DELIMITER = ",";
    private static final String LOGTAG = "MapplsAnalyticsAPI.PIdentity";
    private static Boolean sIsFirstAppLaunch = null;
    private static Integer sPreviousVersionCode = null;
    private static boolean sReferrerPrefsDirty = true;
    private static final Object sReferrerPrefsLock = new Object();
    private String mAnonymousId;
    private String mEventsDistinctId;
    private boolean mEventsUserIdPresent;
    private boolean mHadPersistedDistinctId;
    private Boolean mIsUserOptOut;
    private final Future<SharedPreferences> mLoadReferrerPreferences;
    private final Future<SharedPreferences> mLoadStoredPreferences;
    private final Future<SharedPreferences> mMapplsAnalyticsPreferences;
    private String mPeopleDistinctId;
    private final Future<SharedPreferences> mTimeEventsPreferences;
    private final Object mSuperPropsLock = new Object();
    private JSONObject mSuperPropertiesCache = null;
    private Map<String, String> mReferrerPropertiesCache = null;
    private boolean mIdentitiesLoaded = false;
    private final SharedPreferences.OnSharedPreferenceChangeListener mReferrerChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.mappls.android.lms.PersistentIdentity.1
        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            synchronized (PersistentIdentity.sReferrerPrefsLock) {
                PersistentIdentity.this.readReferrerProperties();
                boolean unused = PersistentIdentity.sReferrerPrefsDirty = false;
            }
        }
    };

    public PersistentIdentity(Future<SharedPreferences> future, Future<SharedPreferences> future2, Future<SharedPreferences> future3, Future<SharedPreferences> future4) {
        this.mLoadReferrerPreferences = future;
        this.mLoadStoredPreferences = future2;
        this.mTimeEventsPreferences = future3;
        this.mMapplsAnalyticsPreferences = future4;
    }

    public static String getPeopleDistinctId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("people_distinct_id", null);
    }

    private JSONObject getSuperPropertiesCache() {
        if (this.mSuperPropertiesCache == null) {
            readSuperProperties();
        }
        return this.mSuperPropertiesCache;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readIdentities() {
        /*
            r4 = this;
            java.lang.String r0 = "Cannot read distinct ids from sharedPreferences."
            java.lang.String r1 = "MapplsAnalyticsAPI.PIdentity"
            r2 = 0
            java.util.concurrent.Future<android.content.SharedPreferences> r3 = r4.mLoadStoredPreferences     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10
            java.lang.Object r3 = r3.get()     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10
            android.content.SharedPreferences r3 = (android.content.SharedPreferences) r3     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10
            goto L19
        Le:
            r3 = move-exception
            goto L15
        L10:
            r3 = move-exception
            java.lang.Throwable r3 = r3.getCause()
        L15:
            com.mappls.android.util.MPLog.e(r1, r0, r3)
            r3 = r2
        L19:
            if (r3 != 0) goto L1c
            return
        L1c:
            java.lang.String r0 = "events_distinct_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mEventsDistinctId = r0
            java.lang.String r0 = "events_user_id_present"
            r1 = 0
            boolean r0 = r3.getBoolean(r0, r1)
            r4.mEventsUserIdPresent = r0
            java.lang.String r0 = "people_distinct_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mPeopleDistinctId = r0
            java.lang.String r0 = "anonymous_id"
            java.lang.String r0 = r3.getString(r0, r2)
            r4.mAnonymousId = r0
            java.lang.String r0 = "had_persisted_distinct_id"
            boolean r0 = r3.getBoolean(r0, r1)
            r4.mHadPersistedDistinctId = r0
            java.lang.String r0 = r4.mEventsDistinctId
            if (r0 != 0) goto L6a
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r4.mAnonymousId = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "$device:"
            r0.<init>(r2)
            java.lang.String r2 = r4.mAnonymousId
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4.mEventsDistinctId = r0
            r4.mEventsUserIdPresent = r1
            r4.writeIdentities()
        L6a:
            r0 = 1
            r4.mIdentitiesLoaded = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.PersistentIdentity.readIdentities():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readOptOutFlag(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "Cannot read opt out flag from sharedPreferences."
            java.lang.String r1 = "MapplsAnalyticsAPI.PIdentity"
            java.util.concurrent.Future<android.content.SharedPreferences> r2 = r3.mMapplsAnalyticsPreferences     // Catch: java.lang.InterruptedException -> Ld java.util.concurrent.ExecutionException -> Lf
            java.lang.Object r2 = r2.get()     // Catch: java.lang.InterruptedException -> Ld java.util.concurrent.ExecutionException -> Lf
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2     // Catch: java.lang.InterruptedException -> Ld java.util.concurrent.ExecutionException -> Lf
            goto L18
        Ld:
            r2 = move-exception
            goto L14
        Lf:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
        L14:
            com.mappls.android.util.MPLog.e(r1, r0, r2)
            r2 = 0
        L18:
            if (r2 != 0) goto L1b
            return
        L1b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "opt_out_"
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r0 = 0
            boolean r4 = r2.getBoolean(r4, r0)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3.mIsUserOptOut = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.PersistentIdentity.readOptOutFlag(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readReferrerProperties() {
        Throwable e;
        this.mReferrerPropertiesCache = new HashMap();
        try {
            SharedPreferences sharedPreferences = this.mLoadReferrerPreferences.get();
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            sharedPreferences.registerOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                Object value = entry.getValue();
                this.mReferrerPropertiesCache.put(entry.getKey(), value.toString());
            }
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Cannot load referrer properties from shared preferences.", e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Cannot load referrer properties from shared preferences.", e);
        }
    }

    private void readSuperProperties() {
        JSONObject jSONObject;
        try {
            try {
                try {
                    String string = this.mLoadStoredPreferences.get().getString("super_properties", "{}");
                    MPLog.v(LOGTAG, "Loading Super Properties " + string);
                    this.mSuperPropertiesCache = new JSONObject(string);
                } catch (JSONException unused) {
                    MPLog.e(LOGTAG, "Cannot parse stored superProperties");
                    storeSuperProperties();
                    if (this.mSuperPropertiesCache != null) {
                        return;
                    }
                    jSONObject = new JSONObject();
                    this.mSuperPropertiesCache = jSONObject;
                }
            } catch (InterruptedException e) {
                MPLog.e(LOGTAG, "Cannot load superProperties from SharedPreferences.", e);
                if (this.mSuperPropertiesCache == null) {
                    jSONObject = new JSONObject();
                    this.mSuperPropertiesCache = jSONObject;
                }
            } catch (ExecutionException e2) {
                MPLog.e(LOGTAG, "Cannot load superProperties from SharedPreferences.", e2.getCause());
                if (this.mSuperPropertiesCache == null) {
                    jSONObject = new JSONObject();
                    this.mSuperPropertiesCache = jSONObject;
                }
            }
        } catch (Throwable th) {
            if (this.mSuperPropertiesCache == null) {
                this.mSuperPropertiesCache = new JSONObject();
            }
            throw th;
        }
    }

    private void storeSuperProperties() {
        Throwable e;
        JSONObject jSONObject = this.mSuperPropertiesCache;
        if (jSONObject == null) {
            MPLog.e(LOGTAG, "storeSuperProperties should not be called with uninitialized superPropertiesCache.");
            return;
        }
        String jSONObject2 = jSONObject.toString();
        MPLog.v(LOGTAG, "Storing Super Properties " + jSONObject2);
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.putString("super_properties", jSONObject2);
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Cannot store superProperties in shared preferences.", e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Cannot store superProperties in shared preferences.", e);
        }
    }

    private static void writeEdits(SharedPreferences.Editor editor) {
        editor.apply();
    }

    private void writeIdentities() {
        Throwable e;
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.putString("events_distinct_id", this.mEventsDistinctId);
            edit.putBoolean("events_user_id_present", this.mEventsUserIdPresent);
            edit.putString("people_distinct_id", this.mPeopleDistinctId);
            edit.putString("anonymous_id", this.mAnonymousId);
            edit.putBoolean("had_persisted_distinct_id", this.mHadPersistedDistinctId);
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Can't write distinct ids to shared preferences.", e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Can't write distinct ids to shared preferences.", e);
        }
    }

    private void writeOptOutFlag(String str) {
        Throwable e;
        try {
            SharedPreferences.Editor edit = this.mMapplsAnalyticsPreferences.get().edit();
            edit.putBoolean("opt_out_" + str, this.mIsUserOptOut.booleanValue());
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Can't write opt-out shared preferences.", e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Can't write opt-out shared preferences.", e);
        }
    }

    public static void writeReferrerPrefs(Context context, String str, Map<String, String> map) {
        synchronized (sReferrerPrefsLock) {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.clear();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                edit.putString(entry.getKey(), entry.getValue());
            }
            writeEdits(edit);
            sReferrerPrefsDirty = true;
        }
    }

    public void addSuperPropertiesToObject(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = superPropertiesCache.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, superPropertiesCache.get(next));
                } catch (JSONException e) {
                    MPLog.e(LOGTAG, "Object read from one JSON Object cannot be written to another", e);
                }
            }
        }
    }

    public void addTimeEvent(String str, Long l) {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.putLong(str, l.longValue());
            writeEdits(edit);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public synchronized void clearPreferences() {
        try {
            SharedPreferences.Editor edit = this.mLoadStoredPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
            readSuperProperties();
            readIdentities();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public void clearReferrerProperties() {
        String str;
        String str2;
        Throwable e;
        synchronized (sReferrerPrefsLock) {
            try {
                SharedPreferences.Editor edit = this.mLoadReferrerPreferences.get().edit();
                edit.clear();
                writeEdits(edit);
            } catch (InterruptedException e2) {
                e = e2;
                str = LOGTAG;
                str2 = "Cannot load referrer properties from shared preferences.";
                MPLog.e(str, str2, e);
            } catch (ExecutionException e3) {
                str = LOGTAG;
                str2 = "Cannot load referrer properties from shared preferences.";
                e = e3.getCause();
                MPLog.e(str, str2, e);
            }
        }
    }

    public void clearSuperProperties() {
        synchronized (this.mSuperPropsLock) {
            this.mSuperPropertiesCache = new JSONObject();
            storeSuperProperties();
        }
    }

    public void clearTimedEvents() {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getAnonymousId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mAnonymousId;
    }

    public synchronized String getEventsDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mEventsDistinctId;
    }

    public synchronized String getEventsUserId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        if (this.mEventsUserIdPresent) {
            return this.mEventsDistinctId;
        }
        return null;
    }

    public synchronized boolean getHadPersistedDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mHadPersistedDistinctId;
    }

    public synchronized boolean getOptOutTracking(String str) {
        if (this.mIsUserOptOut == null) {
            readOptOutFlag(str);
        }
        return this.mIsUserOptOut.booleanValue();
    }

    public synchronized String getPeopleDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mPeopleDistinctId;
    }

    public Map<String, String> getReferrerProperties() {
        synchronized (sReferrerPrefsLock) {
            if (sReferrerPrefsDirty || this.mReferrerPropertiesCache == null) {
                readReferrerProperties();
                sReferrerPrefsDirty = false;
            }
        }
        return this.mReferrerPropertiesCache;
    }

    public Map<String, Long> getTimeEvents() {
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry<String, ?> entry : this.mTimeEventsPreferences.get().getAll().entrySet()) {
                hashMap.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public boolean hasOptOutFlag(String str) {
        Throwable e;
        try {
            return this.mMapplsAnalyticsPreferences.get().contains("opt_out_" + str);
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Can't read opt-out shared preferences.", e);
            return false;
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Can't read opt-out shared preferences.", e);
            return false;
        }
    }

    public synchronized boolean isFirstIntegration(String str) {
        boolean z;
        Throwable e;
        String str2;
        String str3;
        z = false;
        try {
            z = this.mMapplsAnalyticsPreferences.get().getBoolean(str, false);
        } catch (InterruptedException e2) {
            e = e2;
            str2 = LOGTAG;
            str3 = "Couldn't read internal Mappls Analytics from shared preferences.";
            MPLog.e(str2, str3, e);
            return z;
        } catch (ExecutionException e3) {
            e = e3.getCause();
            str2 = LOGTAG;
            str3 = "Couldn't read internal Mappls Analytics shared preferences.";
            MPLog.e(str2, str3, e);
            return z;
        }
        return z;
    }

    public synchronized boolean isFirstLaunch(boolean z, String str) {
        if (sIsFirstAppLaunch == null) {
            try {
                if (this.mMapplsAnalyticsPreferences.get().getBoolean("has_launched_" + str, false)) {
                    sIsFirstAppLaunch = Boolean.FALSE;
                } else {
                    Boolean valueOf = Boolean.valueOf(!z);
                    sIsFirstAppLaunch = valueOf;
                    if (!valueOf.booleanValue()) {
                        setHasLaunched(str);
                    }
                }
            } catch (InterruptedException | ExecutionException unused) {
                sIsFirstAppLaunch = Boolean.FALSE;
            }
        }
        return sIsFirstAppLaunch.booleanValue();
    }

    public synchronized boolean isNewVersion(String str) {
        Throwable e;
        String str2;
        String str3;
        if (str == null) {
            return false;
        }
        Integer valueOf = Integer.valueOf(str);
        try {
            if (sPreviousVersionCode == null) {
                Integer valueOf2 = Integer.valueOf(this.mMapplsAnalyticsPreferences.get().getInt("latest_version_code", -1));
                sPreviousVersionCode = valueOf2;
                if (valueOf2.intValue() == -1) {
                    sPreviousVersionCode = valueOf;
                    SharedPreferences.Editor edit = this.mMapplsAnalyticsPreferences.get().edit();
                    edit.putInt("latest_version_code", valueOf.intValue());
                    writeEdits(edit);
                }
            }
            if (sPreviousVersionCode.intValue() < valueOf.intValue()) {
                SharedPreferences.Editor edit2 = this.mMapplsAnalyticsPreferences.get().edit();
                edit2.putInt("latest_version_code", valueOf.intValue());
                writeEdits(edit2);
                return true;
            }
        } catch (InterruptedException e2) {
            e = e2;
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics from shared preferences.";
            MPLog.e(str2, str3, e);
            return false;
        } catch (ExecutionException e3) {
            e = e3.getCause();
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics shared preferences.";
            MPLog.e(str2, str3, e);
            return false;
        }
        return false;
    }

    public synchronized void markEventsUserIdPresent() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mEventsUserIdPresent = true;
        writeIdentities();
    }

    public void registerSuperProperties(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    superPropertiesCache.put(next, jSONObject.get(next));
                } catch (JSONException e) {
                    MPLog.e(LOGTAG, "Exception registering super property.", e);
                }
            }
            storeSuperProperties();
        }
    }

    public void registerSuperPropertiesOnce(JSONObject jSONObject) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!superPropertiesCache.has(next)) {
                    try {
                        superPropertiesCache.put(next, jSONObject.get(next));
                    } catch (JSONException e) {
                        MPLog.e(LOGTAG, "Exception registering super property.", e);
                    }
                }
            }
            storeSuperProperties();
        }
    }

    public void removeOptOutFlag(String str) {
        Throwable e;
        try {
            SharedPreferences.Editor edit = this.mMapplsAnalyticsPreferences.get().edit();
            edit.clear();
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            MPLog.e(LOGTAG, "Can't remove opt-out shared preferences.", e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            MPLog.e(LOGTAG, "Can't remove opt-out shared preferences.", e);
        }
    }

    public void removeTimedEvent(String str) {
        try {
            SharedPreferences.Editor edit = this.mTimeEventsPreferences.get().edit();
            edit.remove(str);
            writeEdits(edit);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setAnonymousIdIfAbsent(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        if (this.mAnonymousId != null) {
            return;
        }
        this.mAnonymousId = str;
        this.mHadPersistedDistinctId = true;
        writeIdentities();
    }

    public synchronized void setEventsDistinctId(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mEventsDistinctId = str;
        writeIdentities();
    }

    public synchronized void setHasLaunched(String str) {
        Throwable e;
        String str2;
        String str3;
        try {
            SharedPreferences.Editor edit = this.mMapplsAnalyticsPreferences.get().edit();
            edit.putBoolean("has_launched_" + str, true);
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics shared preferences.";
            MPLog.e(str2, str3, e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics shared preferences.";
            MPLog.e(str2, str3, e);
        }
    }

    public synchronized void setIsIntegrated(String str) {
        Throwable e;
        String str2;
        String str3;
        try {
            SharedPreferences.Editor edit = this.mMapplsAnalyticsPreferences.get().edit();
            edit.putBoolean(str, true);
            writeEdits(edit);
        } catch (InterruptedException e2) {
            e = e2;
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics from shared preferences.";
            MPLog.e(str2, str3, e);
        } catch (ExecutionException e3) {
            e = e3.getCause();
            str2 = LOGTAG;
            str3 = "Couldn't write internal Mappls Analytics shared preferences.";
            MPLog.e(str2, str3, e);
        }
    }

    public synchronized void setOptOutTracking(boolean z, String str) {
        this.mIsUserOptOut = Boolean.valueOf(z);
        writeOptOutFlag(str);
    }

    public synchronized void setPeopleDistinctId(String str) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mPeopleDistinctId = str;
        writeIdentities();
    }

    public void unregisterSuperProperty(String str) {
        synchronized (this.mSuperPropsLock) {
            getSuperPropertiesCache().remove(str);
            storeSuperProperties();
        }
    }

    public void updateSuperProperties(SuperPropertyUpdate superPropertyUpdate) {
        synchronized (this.mSuperPropsLock) {
            JSONObject superPropertiesCache = getSuperPropertiesCache();
            JSONObject jSONObject = new JSONObject();
            try {
                Iterator<String> keys = superPropertiesCache.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject.put(next, superPropertiesCache.get(next));
                }
                JSONObject update = superPropertyUpdate.update(jSONObject);
                if (update == null) {
                    MPLog.w(LOGTAG, "An update to Mappls Analytics's super properties returned null, and will have no effect.");
                    return;
                }
                this.mSuperPropertiesCache = update;
                storeSuperProperties();
            } catch (JSONException e) {
                MPLog.e(LOGTAG, "Can't copy from one JSONObject to another", e);
            }
        }
    }
}
