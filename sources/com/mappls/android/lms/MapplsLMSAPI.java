package com.mappls.android.lms;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.event.stat.one.d;
import com.mappls.android.lms.LMSMessages;
import com.mappls.android.lms.SharedPreferencesLoader;
import com.mappls.android.util.MPLog;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MapplsLMSAPI {
    private static final String APP_LINKS_LOGTAG = "MapplsAnalyticsAPI.AL";
    private static final String ENGAGE_DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String LOGTAG = "MapplsAnalyticsAPI.API";
    public static final String VERSION = "1.0.0";
    public static String host;
    public static Integer port;
    private static final Map<String, Map<Context, MapplsLMSAPI>> sInstanceMap = new HashMap();
    private static final SharedPreferencesLoader sPrefsLoader = new SharedPreferencesLoader();
    private static Future<SharedPreferences> sReferrerPrefs;
    private final MapplsLMSConfig mConfig;
    private final Context mContext;
    private final Map<String, String> mDeviceInfo;
    private final Map<String, Long> mEventTimings;
    private final Map<String, GroupImpl> mGroups;
    private MapplsLMSActivityLifecycleCallbacks mMapplsLMSActivityLifecycleCallbacks;
    private final LMSMessages mMessages;
    private final PeopleImpl mPeople;
    private final PersistentIdentity mPersistentIdentity;
    private final SessionMetadata mSessionMetadata;
    private final String mToken;
    private final Boolean mTrackAutomaticEvents;

    /* loaded from: classes11.dex */
    public interface Group {
        void deleteGroup();

        void remove(String str, Object obj);

        void set(String str, Object obj);

        void set(JSONObject jSONObject);

        void setMap(Map<String, Object> map);

        void setOnce(String str, Object obj);

        void setOnce(JSONObject jSONObject);

        void setOnceMap(Map<String, Object> map);

        void union(String str, JSONArray jSONArray);

        void unset(String str);
    }

    /* loaded from: classes11.dex */
    class GroupImpl implements Group {
        private final Object mGroupID;
        private final String mGroupKey;

        public GroupImpl(String str, Object obj) {
            this.mGroupKey = str;
            this.mGroupID = obj;
        }

        private JSONObject stdGroupMessage(String str, Object obj) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, obj);
            jSONObject.put("$token", MapplsLMSAPI.this.mToken);
            jSONObject.put("$time", System.currentTimeMillis());
            jSONObject.put("$group_key", this.mGroupKey);
            jSONObject.put("$group_id", this.mGroupID);
            jSONObject.put("$mp_metadata", MapplsLMSAPI.this.mSessionMetadata.getMetadataForPeople());
            return jSONObject;
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void deleteGroup() {
            try {
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage(Constants.COMMAND_DELETE, JSONObject.NULL));
                MapplsLMSAPI.this.mGroups.remove(MapplsLMSAPI.this.makeMapKey(this.mGroupKey, this.mGroupID));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception deleting a group", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void remove(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, obj);
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage(Constants.COMMAND_REMOVE, jSONObject));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception removing a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void set(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                set(new JSONObject().put(str, obj));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "set", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void set(JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject2.put(next, jSONObject.get(next));
                }
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage(Constants.COMMAND_SET, jSONObject2));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception setting group properties", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void setMap(Map<String, Object> map) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            if (map == null) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "setMap does not accept null properties");
            } else {
                set(new JSONObject((Map<?, ?>) map));
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void setOnce(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                setOnce(new JSONObject().put(str, obj));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Property name cannot be null", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void setOnce(JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage("$set_once", jSONObject));
            } catch (JSONException unused) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception setting group properties");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void setOnceMap(Map<String, Object> map) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            if (map == null) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "setOnceMap does not accept null properties");
                return;
            }
            try {
                setOnce(new JSONObject((Map<?, ?>) map));
            } catch (NullPointerException unused) {
                MPLog.w(MapplsLMSAPI.LOGTAG, "Can't have null keys in the properties for setOnceMap!");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void union(String str, JSONArray jSONArray) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, jSONArray);
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage("$union", jSONObject));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception unioning a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.Group
        public void unset(String str) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(str);
                MapplsLMSAPI.this.recordGroupMessage(stdGroupMessage("$unset", jSONArray));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception unsetting a property", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public interface InstanceProcessor {
        void process(MapplsLMSAPI mapplsLMSAPI);
    }

    /* loaded from: classes11.dex */
    public interface People {
        void append(String str, Object obj);

        void clearCharges();

        void deleteUser();

        String getDistinctId();

        void identify(String str);

        void increment(String str, double d);

        void increment(Map<String, ? extends Number> map);

        boolean isIdentified();

        void merge(String str, JSONObject jSONObject);

        void remove(String str, Object obj);

        void set(String str, Object obj);

        void set(JSONObject jSONObject);

        void setMap(Map<String, Object> map);

        void setOnce(String str, Object obj);

        void setOnce(JSONObject jSONObject);

        void setOnceMap(Map<String, Object> map);

        void trackCharge(double d, JSONObject jSONObject);

        void union(String str, JSONArray jSONArray);

        void unset(String str);

        People withIdentity(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class PeopleImpl implements People {
        private PeopleImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void identify_people(String str) {
            synchronized (MapplsLMSAPI.this.mPersistentIdentity) {
                MapplsLMSAPI.this.mPersistentIdentity.setPeopleDistinctId(str);
            }
            MapplsLMSAPI.this.pushWaitingPeopleRecord(str);
        }

        private JSONObject stdPeopleMessage(String str, Object obj) {
            JSONObject jSONObject = new JSONObject();
            if (MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET) {
                String distinctId = getDistinctId();
                String anonymousId = MapplsLMSAPI.this.getAnonymousId();
                jSONObject.put(str, obj);
                jSONObject.put("$token", MapplsLMSAPI.this.mToken);
                jSONObject.put("$time", System.currentTimeMillis());
                jSONObject.put("$had_persisted_distinct_id", MapplsLMSAPI.this.mPersistentIdentity.getHadPersistedDistinctId());
                if (anonymousId != null) {
                    jSONObject.put("$device_id", anonymousId);
                }
                if (distinctId != null) {
                    jSONObject.put("$distinct_id", distinctId);
                    jSONObject.put("$user_id", distinctId);
                }
                jSONObject.put("$mp_metadata", MapplsLMSAPI.this.mSessionMetadata.getMetadataForPeople());
                return jSONObject;
            }
            return jSONObject;
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void append(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, obj);
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage("$append", jSONObject));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception appending a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void clearCharges() {
            unset("$transactions");
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void deleteUser() {
            try {
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage(Constants.COMMAND_DELETE, JSONObject.NULL));
            } catch (JSONException unused) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception deleting a user");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public String getDistinctId() {
            return MapplsLMSAPI.this.mPersistentIdentity.getPeopleDistinctId();
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void identify(String str) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            MPLog.w(MapplsLMSAPI.LOGTAG, "People.identify() is deprecated and calling it is no longer necessary, please use MapplsAnalyticsAPI.identify() and set 'usePeople' to true instead");
            if (str == null) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Can't identify with null distinct_id.");
            } else if (str.equals(MapplsLMSAPI.this.mPersistentIdentity.getEventsDistinctId())) {
                identify_people(str);
            } else {
                MPLog.w(MapplsLMSAPI.LOGTAG, "Identifying with a distinct_id different from the one being set by MapplsAnalyticsAPI.identify() is not supported.");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void increment(String str, double d) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(str, Double.valueOf(d));
            increment(hashMap);
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void increment(Map<String, ? extends Number> map) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage(Constants.COMMAND_ADD, new JSONObject((Map<?, ?>) map)));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception incrementing properties", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public boolean isIdentified() {
            return getDistinctId() != null;
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void merge(String str, JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(str, jSONObject);
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage("$merge", jSONObject2));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception merging a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void remove(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, obj);
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage(Constants.COMMAND_REMOVE, jSONObject));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception appending a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void set(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                set(new JSONObject().put(str, obj));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "set", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void set(JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject((Map<?, ?>) MapplsLMSAPI.this.mDeviceInfo);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject2.put(next, jSONObject.get(next));
                }
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage(Constants.COMMAND_SET, jSONObject2));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception setting people properties", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void setMap(Map<String, Object> map) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            if (map == null) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "setMap does not accept null properties");
                return;
            }
            try {
                set(new JSONObject((Map<?, ?>) map));
            } catch (NullPointerException unused) {
                MPLog.w(MapplsLMSAPI.LOGTAG, "Can't have null keys in the properties of setMap!");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void setOnce(String str, Object obj) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                setOnce(new JSONObject().put(str, obj));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "set", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void setOnce(JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage("$set_once", jSONObject));
            } catch (JSONException unused) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception setting people properties");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void setOnceMap(Map<String, Object> map) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            if (map == null) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "setOnceMap does not accept null properties");
                return;
            }
            try {
                setOnce(new JSONObject((Map<?, ?>) map));
            } catch (NullPointerException unused) {
                MPLog.w(MapplsLMSAPI.LOGTAG, "Can't have null keys in the properties setOnceMap!");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void trackCharge(double d, JSONObject jSONObject) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("$amount", d);
                jSONObject2.put("$time", simpleDateFormat.format(date));
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject2.put(next, jSONObject.get(next));
                    }
                }
                append("$transactions", jSONObject2);
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception creating new charge", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void union(String str, JSONArray jSONArray) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, jSONArray);
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage("$union", jSONObject));
            } catch (JSONException unused) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception unioning a property");
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public void unset(String str) {
            if (MapplsLMSAPI.this.hasOptedOutTracking()) {
                return;
            }
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(str);
                MapplsLMSAPI.this.recordPeopleMessage(stdPeopleMessage("$unset", jSONArray));
            } catch (JSONException e) {
                MPLog.e(MapplsLMSAPI.LOGTAG, "Exception unsetting a property", e);
            }
        }

        @Override // com.mappls.android.lms.MapplsLMSAPI.People
        public People withIdentity(final String str) {
            if (str == null) {
                return null;
            }
            return new PeopleImpl() { // from class: com.mappls.android.lms.MapplsLMSAPI.PeopleImpl.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.mappls.android.lms.MapplsLMSAPI.PeopleImpl, com.mappls.android.lms.MapplsLMSAPI.People
                public String getDistinctId() {
                    return str;
                }

                @Override // com.mappls.android.lms.MapplsLMSAPI.PeopleImpl, com.mappls.android.lms.MapplsLMSAPI.People
                public void identify(String str2) {
                    throw new RuntimeException("This MapplsAnalyticsPeople object has a fixed, constant distinctId");
                }
            };
        }
    }

    public MapplsLMSAPI(Context context, Future<SharedPreferences> future, String str, MapplsLMSConfig mapplsLMSConfig, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        this.mContext = context;
        this.mToken = str;
        this.mPeople = new PeopleImpl();
        this.mGroups = new HashMap();
        this.mConfig = mapplsLMSConfig;
        this.mTrackAutomaticEvents = Boolean.valueOf(z2);
        HashMap hashMap = new HashMap();
        hashMap.put("android_lib_version", "1.0.0");
        hashMap.put("android_os", "Android");
        String str3 = Build.VERSION.RELEASE;
        hashMap.put("android_os_version", str3 == null ? "UNKNOWN" : str3);
        String str4 = Build.MANUFACTURER;
        hashMap.put("android_manufacturer", str4 == null ? "UNKNOWN" : str4);
        String str5 = Build.BRAND;
        hashMap.put("android_brand", str5 == null ? "UNKNOWN" : str5);
        String str6 = Build.MODEL;
        hashMap.put("android_model", str6 != null ? str6 : "UNKNOWN");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            hashMap.put("android_app_version", packageInfo.versionName);
            hashMap.put("android_app_version_code", Integer.toString(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            MPLog.e(LOGTAG, "Exception getting app version name", e);
        }
        this.mDeviceInfo = Collections.unmodifiableMap(hashMap);
        this.mSessionMetadata = new SessionMetadata();
        this.mMessages = getLMSMessages();
        PersistentIdentity persistentIdentity = getPersistentIdentity(context, future, str, str2);
        this.mPersistentIdentity = persistentIdentity;
        this.mEventTimings = persistentIdentity.getTimeEvents();
        if (z && (hasOptedOutTracking() || !persistentIdentity.hasOptOutFlag(str))) {
            optOutTracking();
        }
        if (jSONObject != null) {
            registerSuperProperties(jSONObject);
        }
        boolean exists = MapplsLMSDbAdapter.getInstance(this.mContext).getDatabaseFile().exists();
        registerMapplsAnalyticsActivityLifecycleCallbacks();
        if (persistentIdentity.isFirstLaunch(exists, this.mToken) && this.mTrackAutomaticEvents.booleanValue()) {
            track(AutomaticEvents.FIRST_OPEN, null, true);
            persistentIdentity.setHasLaunched(this.mToken);
        }
        if (sendAppOpen() && this.mTrackAutomaticEvents.booleanValue()) {
            track(FirebaseAnalytics.Event.APP_OPEN, null);
        }
        if (!persistentIdentity.isFirstIntegration(this.mToken)) {
            persistentIdentity.setIsIntegrated(this.mToken);
            try {
                if (this.mTrackAutomaticEvents.booleanValue()) {
                    sendHttpEvent("Integration", str, str, null, false);
                }
            } catch (JSONException unused) {
            }
        }
        if (this.mPersistentIdentity.isNewVersion((String) hashMap.get("android_app_version_code")) && this.mTrackAutomaticEvents.booleanValue()) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(AutomaticEvents.VERSION_UPDATED, hashMap.get("android_app_version"));
                track(AutomaticEvents.APP_UPDATED, jSONObject2, true);
            } catch (JSONException unused2) {
            }
        }
        if (!this.mConfig.getDisableExceptionHandler()) {
            ExceptionHandler.init();
        }
        if (this.mConfig.getRemoveLegacyResidualFiles()) {
            this.mMessages.removeResidualImageFiles(new File(this.mContext.getApplicationInfo().dataDir));
        }
    }

    public MapplsLMSAPI(Context context, Future<SharedPreferences> future, String str, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        this(context, future, str, MapplsLMSConfig.getInstance(context), z, jSONObject, str2, z2);
    }

    public MapplsLMSAPI(Context context, Future<SharedPreferences> future, String str, boolean z, JSONObject jSONObject, boolean z2) {
        this(context, future, str, MapplsLMSConfig.getInstance(context), z, jSONObject, null, z2);
    }

    public static void allInstances(InstanceProcessor instanceProcessor) {
        Map<String, Map<Context, MapplsLMSAPI>> map = sInstanceMap;
        synchronized (map) {
            for (Map<Context, MapplsLMSAPI> map2 : map.values()) {
                for (MapplsLMSAPI mapplsLMSAPI : map2.values()) {
                    instanceProcessor.process(mapplsLMSAPI);
                }
            }
        }
    }

    private static void checkIntentForInboundAppLink(Context context) {
        StringBuilder sb;
        String str;
        if (context instanceof Activity) {
            try {
                Class.forName("bolts.AppLinks").getMethod("getTargetUrlFromInboundIntent", Context.class, Intent.class).invoke(null, context, ((Activity) context).getIntent());
                return;
            } catch (ClassNotFoundException e) {
                e = e;
                sb = new StringBuilder("Please install the Bolts library >= 1.1.2 to track App Links: ");
                sb.append(e.getMessage());
                str = sb.toString();
                MPLog.d(APP_LINKS_LOGTAG, str);
            } catch (IllegalAccessException e2) {
                str = "Unable to detect inbound App Links: " + e2.getMessage();
            } catch (NoSuchMethodException e3) {
                e = e3;
                sb = new StringBuilder("Please install the Bolts library >= 1.1.2 to track App Links: ");
                sb.append(e.getMessage());
                str = sb.toString();
                MPLog.d(APP_LINKS_LOGTAG, str);
            } catch (InvocationTargetException e4) {
                MPLog.d(APP_LINKS_LOGTAG, "Failed to invoke bolts.AppLinks.getTargetUrlFromInboundIntent() -- Unable to detect inbound App Links", e4);
                return;
            }
        } else {
            str = "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.";
        }
        MPLog.d(APP_LINKS_LOGTAG, str);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, String str2, boolean z) {
        return getInstance(context, str, false, null, str2, z);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, JSONObject jSONObject, String str2, boolean z) {
        return getInstance(context, str, false, jSONObject, str2, z);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, JSONObject jSONObject, boolean z) {
        return getInstance(context, str, false, jSONObject, null, z);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, boolean z) {
        return getInstance(context, str, false, null, null, z);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, boolean z, String str2, boolean z2) {
        return getInstance(context, str, z, null, str2, z2);
    }

    public static MapplsLMSAPI getInstance(Context context, String str, boolean z, JSONObject jSONObject, String str2, boolean z2) {
        MapplsLMSAPI mapplsLMSAPI;
        if (str == null || context == null) {
            return null;
        }
        Map<String, Map<Context, MapplsLMSAPI>> map = sInstanceMap;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (sReferrerPrefs == null) {
                sReferrerPrefs = sPrefsLoader.loadPreferences(context, MapplsLMSConfig.REFERRER_PREFS_NAME, null);
            }
            String str3 = str2 != null ? str2 : str;
            Map<Context, MapplsLMSAPI> map2 = map.get(str3);
            if (map2 == null) {
                map2 = new HashMap<>();
                map.put(str3, map2);
            }
            Map<Context, MapplsLMSAPI> map3 = map2;
            mapplsLMSAPI = map3.get(applicationContext);
            if (mapplsLMSAPI == null && ConfigurationChecker.checkBasicConfiguration(applicationContext)) {
                MapplsLMSAPI mapplsLMSAPI2 = new MapplsLMSAPI(applicationContext, sReferrerPrefs, str, z, jSONObject, str2, z2);
                registerAppLinksListeners(context, mapplsLMSAPI2);
                map3.put(applicationContext, mapplsLMSAPI2);
                mapplsLMSAPI = mapplsLMSAPI2;
            }
            checkIntentForInboundAppLink(context);
        }
        return mapplsLMSAPI;
    }

    public static MapplsLMSAPI getInstance(Context context, String str, boolean z, boolean z2) {
        return getInstance(context, str, z, null, null, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String makeMapKey(String str, Object obj) {
        return str + '_' + obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushWaitingPeopleRecord(String str) {
        this.mMessages.pushAnonymousPeopleMessage(new LMSMessages.PushAnonymousPeopleDescription(str, this.mToken));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordGroupMessage(JSONObject jSONObject) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (jSONObject.has("$group_key") && jSONObject.has("$group_id")) {
            this.mMessages.groupMessage(new LMSMessages.GroupDescription(jSONObject, this.mToken));
        } else {
            MPLog.e(LOGTAG, "Attempt to update group without key and value--this should not happen.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordPeopleMessage(JSONObject jSONObject) {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mMessages.peopleMessage(new LMSMessages.PeopleDescription(jSONObject, this.mToken));
    }

    private static void registerAppLinksListeners(Context context, MapplsLMSAPI mapplsLMSAPI) {
        StringBuilder sb;
        try {
            Object obj = LocalBroadcastManager.f;
            LocalBroadcastManager.class.getMethod("registerReceiver", BroadcastReceiver.class, IntentFilter.class).invoke(LocalBroadcastManager.class.getMethod("getInstance", Context.class).invoke(null, context), new BroadcastReceiver() { // from class: com.mappls.android.lms.MapplsLMSAPI.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    JSONObject jSONObject = new JSONObject();
                    Bundle bundleExtra = intent.getBundleExtra("event_args");
                    if (bundleExtra != null) {
                        for (String str : bundleExtra.keySet()) {
                            try {
                                jSONObject.put(str, bundleExtra.get(str));
                            } catch (JSONException e) {
                                MPLog.e(MapplsLMSAPI.APP_LINKS_LOGTAG, "failed to add key \"" + str + "\" to properties for tracking bolts event", e);
                            }
                        }
                    }
                    MapplsLMSAPI mapplsLMSAPI2 = MapplsLMSAPI.this;
                    mapplsLMSAPI2.track("$" + intent.getStringExtra("event_name"), jSONObject);
                }
            }, new IntentFilter("com.parse.bolts.measurement_event"));
        } catch (ClassNotFoundException e) {
            e = e;
            sb = new StringBuilder("To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': ");
            sb.append(e.getMessage());
            MPLog.d(APP_LINKS_LOGTAG, sb.toString());
        } catch (IllegalAccessException e2) {
            e = e2;
            sb = new StringBuilder("App Links tracking will not be enabled due to this exception: ");
            sb.append(e.getMessage());
            MPLog.d(APP_LINKS_LOGTAG, sb.toString());
        } catch (NoSuchMethodException e3) {
            e = e3;
            sb = new StringBuilder("To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': ");
            sb.append(e.getMessage());
            MPLog.d(APP_LINKS_LOGTAG, sb.toString());
        } catch (InvocationTargetException e4) {
            MPLog.d(APP_LINKS_LOGTAG, "Failed to invoke LocalBroadcastManager.registerReceiver() -- App Links tracking will not be enabled due to this exception", e4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sendHttpEvent(java.lang.String r6, java.lang.String r7, java.lang.String r8, org.json.JSONObject r9, boolean r10) {
        /*
            r5 = this;
            org.json.JSONObject r0 = r5.getSuperProperties()
            java.lang.String r1 = "$lib_version"
            r2 = 0
            if (r0 == 0) goto L1c
            java.lang.String r3 = "mp_lib"
            java.lang.Object r3 = r0.get(r3)     // Catch: org.json.JSONException -> L18
            java.lang.String r3 = (java.lang.String) r3     // Catch: org.json.JSONException -> L18
            java.lang.Object r0 = r0.get(r1)     // Catch: org.json.JSONException -> L19
            java.lang.String r0 = (java.lang.String) r0     // Catch: org.json.JSONException -> L19
            goto L1a
        L18:
            r3 = r2
        L19:
            r0 = r2
        L1a:
            r2 = r3
            goto L1d
        L1c:
            r0 = r2
        L1d:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            if (r2 == 0) goto L25
            goto L26
        L25:
            r2 = r7
        L26:
            java.lang.String r4 = "appid"
            r3.put(r4, r2)
            boolean r2 = com.mappls.android.lms.MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET
            if (r2 == 0) goto L41
            java.lang.String r2 = "distinct_id"
            r3.put(r2, r8)
            if (r0 == 0) goto L37
            goto L39
        L37:
            java.lang.String r0 = "1.0.0"
        L39:
            r3.put(r1, r0)
            java.lang.String r0 = "Project Token"
            r3.put(r0, r8)
        L41:
            if (r9 == 0) goto L5b
            java.util.Iterator r0 = r9.keys()
        L47:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L5b
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r9.get(r1)
            r3.put(r1, r2)
            goto L47
        L5b:
            com.mappls.android.lms.LMSMessages$EventDescription r9 = new com.mappls.android.lms.LMSMessages$EventDescription
            r9.<init>(r6, r3, r7)
            com.mappls.android.lms.LMSMessages r0 = r5.mMessages
            r0.eventsMessage(r9)
            if (r10 == 0) goto L8e
            org.json.JSONObject r9 = new org.json.JSONObject
            r9.<init>()
            org.json.JSONObject r10 = new org.json.JSONObject
            r10.<init>()
            r0 = 1
            r10.put(r6, r0)
            java.lang.String r6 = "$add"
            r9.put(r6, r10)
            java.lang.String r6 = "$token"
            r9.put(r6, r7)
            java.lang.String r6 = "$distinct_id"
            r9.put(r6, r8)
            com.mappls.android.lms.LMSMessages r6 = r5.mMessages
            com.mappls.android.lms.LMSMessages$PeopleDescription r8 = new com.mappls.android.lms.LMSMessages$PeopleDescription
            r8.<init>(r9, r7)
            r6.peopleMessage(r8)
        L8e:
            com.mappls.android.lms.LMSMessages r6 = r5.mMessages
            com.mappls.android.lms.LMSMessages$MapplsAnalyticsDescription r8 = new com.mappls.android.lms.LMSMessages$MapplsAnalyticsDescription
            r8.<init>(r7)
            r6.postToServer(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.MapplsLMSAPI.sendHttpEvent(java.lang.String, java.lang.String, java.lang.String, org.json.JSONObject, boolean):void");
    }

    public void addGroup(final String str, final Object obj) {
        if (hasOptedOutTracking()) {
            return;
        }
        updateSuperProperties(new SuperPropertyUpdate() { // from class: com.mappls.android.lms.MapplsLMSAPI.1
            @Override // com.mappls.android.lms.SuperPropertyUpdate
            public JSONObject update(JSONObject jSONObject) {
                try {
                    jSONObject.accumulate(str, obj);
                } catch (JSONException e) {
                    MPLog.e(MapplsLMSAPI.LOGTAG, "Failed to add groups superProperty", e);
                }
                return jSONObject;
            }
        });
        this.mPeople.union(str, new JSONArray().put(obj));
    }

    public void alias(String str, String str2) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (str2 == null) {
            str2 = getDistinctId();
        }
        if (str.equals(str2)) {
            MPLog.w(LOGTAG, "Attempted to alias identical distinct_ids " + str + ". Alias message will not be sent.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("alias", str);
            jSONObject.put("distinct_id", str2);
            track("$create_alias", jSONObject);
        } catch (JSONException e) {
            MPLog.e(LOGTAG, "Failed to alias", e);
        }
        flush();
    }

    public void clearSuperProperties() {
        this.mPersistentIdentity.clearSuperProperties();
    }

    public void clearTimedEvent(String str) {
        synchronized (this.mEventTimings) {
            this.mEventTimings.remove(str);
            this.mPersistentIdentity.removeTimedEvent(str);
        }
    }

    public void clearTimedEvents() {
        synchronized (this.mEventTimings) {
            this.mEventTimings.clear();
            this.mPersistentIdentity.clearTimedEvents();
        }
    }

    public double eventElapsedTime(String str) {
        Long l;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.mEventTimings) {
            l = this.mEventTimings.get(str);
        }
        if (l == null) {
            return 0.0d;
        }
        return (currentTimeMillis - l.longValue()) / 1000;
    }

    public void flush() {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mMessages.postToServer(new LMSMessages.MapplsAnalyticsDescription(this.mToken));
    }

    public String getAnonymousId() {
        return this.mPersistentIdentity.getAnonymousId();
    }

    public Context getContext() {
        return this.mContext;
    }

    public Map<String, String> getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public String getDistinctId() {
        return this.mPersistentIdentity.getEventsDistinctId();
    }

    public int getFlushBatchSize() {
        return this.mConfig.getFlushBatchSize();
    }

    public Group getGroup(String str, Object obj) {
        String makeMapKey = makeMapKey(str, obj);
        GroupImpl groupImpl = this.mGroups.get(makeMapKey);
        if (groupImpl == null) {
            groupImpl = new GroupImpl(str, obj);
            this.mGroups.put(makeMapKey, groupImpl);
        }
        if (groupImpl.mGroupKey.equals(str) && groupImpl.mGroupID.equals(obj)) {
            return groupImpl;
        }
        MPLog.i(LOGTAG, "groups map key collision " + makeMapKey);
        GroupImpl groupImpl2 = new GroupImpl(str, obj);
        this.mGroups.put(makeMapKey, groupImpl2);
        return groupImpl2;
    }

    public LMSMessages getLMSMessages() {
        return LMSMessages.getInstance(this.mContext);
    }

    public int getMaximumDatabaseLimit() {
        return this.mConfig.getMaximumDatabaseLimit();
    }

    public People getPeople() {
        return this.mPeople;
    }

    public PersistentIdentity getPersistentIdentity(Context context, Future<SharedPreferences> future, String str) {
        return getPersistentIdentity(context, future, str, null);
    }

    public PersistentIdentity getPersistentIdentity(Context context, Future<SharedPreferences> future, String str, String str2) {
        SharedPreferencesLoader.OnPrefsLoadedListener onPrefsLoadedListener = new SharedPreferencesLoader.OnPrefsLoadedListener() { // from class: com.mappls.android.lms.MapplsLMSAPI.3
            @Override // com.mappls.android.lms.SharedPreferencesLoader.OnPrefsLoadedListener
            public void onPrefsLoaded(SharedPreferences sharedPreferences) {
                String peopleDistinctId = PersistentIdentity.getPeopleDistinctId(sharedPreferences);
                if (peopleDistinctId != null) {
                    MapplsLMSAPI.this.pushWaitingPeopleRecord(peopleDistinctId);
                }
            }
        };
        if (str2 != null) {
            str = str2;
        }
        SharedPreferencesLoader sharedPreferencesLoader = sPrefsLoader;
        return new PersistentIdentity(future, sharedPreferencesLoader.loadPreferences(context, "com.mappls.android.metrics.MapplsAnalyticsAPI_" + str, onPrefsLoadedListener), sharedPreferencesLoader.loadPreferences(context, "com.mappls.android.metrics.MapplsAnalyticsAPI.TimeEvents_" + str, null), sharedPreferencesLoader.loadPreferences(context, "com.mappls.android.metrics.MapplsAnalytics", null));
    }

    public JSONObject getSuperProperties() {
        JSONObject jSONObject = new JSONObject();
        this.mPersistentIdentity.addSuperPropertiesToObject(jSONObject);
        return jSONObject;
    }

    public Boolean getTrackAutomaticEvents() {
        return this.mTrackAutomaticEvents;
    }

    public String getUserId() {
        return this.mPersistentIdentity.getEventsUserId();
    }

    public boolean hasOptedOutTracking() {
        return this.mPersistentIdentity.getOptOutTracking(this.mToken);
    }

    public void identify(String str) {
        identify(str, true);
    }

    public void identify(String str, boolean z) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (str == null || !MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET) {
            MPLog.e(LOGTAG, "Can't identify with null distinct_id.");
            return;
        }
        synchronized (this.mPersistentIdentity) {
            String eventsDistinctId = this.mPersistentIdentity.getEventsDistinctId();
            if (!str.equals(eventsDistinctId)) {
                if (str.startsWith("$device:")) {
                    MPLog.e(LOGTAG, "Can't identify with '$device:' distinct_id.");
                    return;
                }
                this.mPersistentIdentity.setEventsDistinctId(str);
                this.mPersistentIdentity.setAnonymousIdIfAbsent(eventsDistinctId);
                this.mPersistentIdentity.markEventsUserIdPresent();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("$anon_distinct_id", eventsDistinctId);
                    track("$identify", jSONObject);
                } catch (JSONException unused) {
                    MPLog.e(LOGTAG, "Could not track $identify event");
                }
            }
            if (z) {
                this.mPeople.identify_people(str);
            }
        }
    }

    public boolean isAppInForeground() {
        if (Build.VERSION.SDK_INT < 14) {
            MPLog.e(LOGTAG, "Your build version is below 14. This method will always return false.");
            return false;
        }
        MapplsLMSActivityLifecycleCallbacks mapplsLMSActivityLifecycleCallbacks = this.mMapplsLMSActivityLifecycleCallbacks;
        if (mapplsLMSActivityLifecycleCallbacks != null) {
            return mapplsLMSActivityLifecycleCallbacks.isInForeground();
        }
        return false;
    }

    public void onBackground() {
        if (this.mConfig.getFlushOnBackground()) {
            flush();
        }
    }

    public void onForeground() {
        this.mSessionMetadata.initSession();
    }

    public void optInTracking() {
        optInTracking(null, null);
    }

    public void optInTracking(String str) {
        optInTracking(str, null);
    }

    public void optInTracking(String str, JSONObject jSONObject) {
        this.mPersistentIdentity.setOptOutTracking(false, this.mToken);
        if (str != null) {
            identify(str);
        }
        track("$opt_in", jSONObject);
    }

    public void optOutTracking() {
        getLMSMessages().emptyTrackingQueues(new LMSMessages.MapplsAnalyticsDescription(this.mToken));
        if (getPeople().isIdentified()) {
            getPeople().deleteUser();
            getPeople().clearCharges();
        }
        this.mPersistentIdentity.clearPreferences();
        synchronized (this.mEventTimings) {
            this.mEventTimings.clear();
            this.mPersistentIdentity.clearTimedEvents();
        }
        this.mPersistentIdentity.clearReferrerProperties();
        this.mPersistentIdentity.setOptOutTracking(true, this.mToken);
    }

    public void registerMapplsAnalyticsActivityLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT >= 14) {
            if (!(this.mContext.getApplicationContext() instanceof Application)) {
                MPLog.i(LOGTAG, "Context is not an Application, Mappls Analytics won't be able to automatically flush on an app background.");
                return;
            }
            MapplsLMSActivityLifecycleCallbacks mapplsLMSActivityLifecycleCallbacks = new MapplsLMSActivityLifecycleCallbacks(this, this.mConfig);
            this.mMapplsLMSActivityLifecycleCallbacks = mapplsLMSActivityLifecycleCallbacks;
            ((Application) this.mContext.getApplicationContext()).registerActivityLifecycleCallbacks(mapplsLMSActivityLifecycleCallbacks);
        }
    }

    public void registerSuperProperties(JSONObject jSONObject) {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mPersistentIdentity.registerSuperProperties(jSONObject);
    }

    public void registerSuperPropertiesMap(Map<String, Object> map) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (map == null) {
            MPLog.e(LOGTAG, "registerSuperPropertiesMap does not accept null properties");
            return;
        }
        try {
            registerSuperProperties(new JSONObject((Map<?, ?>) map));
        } catch (NullPointerException unused) {
            MPLog.w(LOGTAG, "Can't have null keys in the properties of registerSuperPropertiesMap");
        }
    }

    public void registerSuperPropertiesOnce(JSONObject jSONObject) {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mPersistentIdentity.registerSuperPropertiesOnce(jSONObject);
    }

    public void registerSuperPropertiesOnceMap(Map<String, Object> map) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (map == null) {
            MPLog.e(LOGTAG, "registerSuperPropertiesOnceMap does not accept null properties");
            return;
        }
        try {
            registerSuperPropertiesOnce(new JSONObject((Map<?, ?>) map));
        } catch (NullPointerException unused) {
            MPLog.w(LOGTAG, "Can't have null keys in the properties of registerSuperPropertiesOnce!");
        }
    }

    public void removeGroup(final String str, final Object obj) {
        if (hasOptedOutTracking()) {
            return;
        }
        updateSuperProperties(new SuperPropertyUpdate() { // from class: com.mappls.android.lms.MapplsLMSAPI.2
            @Override // com.mappls.android.lms.SuperPropertyUpdate
            public JSONObject update(JSONObject jSONObject) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(str);
                    JSONArray jSONArray2 = new JSONArray();
                    if (jSONArray.length() <= 1) {
                        jSONObject.remove(str);
                        MapplsLMSAPI.this.mPeople.unset(str);
                    } else {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (!jSONArray.get(i).equals(obj)) {
                                jSONArray2.put(jSONArray.get(i));
                            }
                        }
                        jSONObject.put(str, jSONArray2);
                        MapplsLMSAPI.this.mPeople.remove(str, obj);
                    }
                } catch (JSONException unused) {
                    jSONObject.remove(str);
                    MapplsLMSAPI.this.mPeople.unset(str);
                }
                return jSONObject;
            }
        });
    }

    public void reset() {
        this.mPersistentIdentity.clearPreferences();
        getLMSMessages().clearAnonymousUpdatesMessage(new LMSMessages.MapplsAnalyticsDescription(this.mToken));
        identify(getDistinctId(), false);
        flush();
    }

    public boolean sendAppOpen() {
        return !this.mConfig.getDisableAppOpenEvent();
    }

    public void setBaseUrl(String str) {
        this.mConfig.setServerURL(str);
    }

    public void setEnableLogging(boolean z) {
        this.mConfig.setEnableLogging(z);
    }

    public void setFlushBatchSize(int i) {
        this.mConfig.setFlushBatchSize(i);
    }

    public void setGroup(String str, Object obj) {
        if (hasOptedOutTracking()) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(obj);
        setGroup(str, (List<Object>) arrayList);
    }

    public void setGroup(String str, List<Object> list) {
        if (hasOptedOutTracking()) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj == null) {
                MPLog.w(LOGTAG, "groupID must be non-null");
            } else {
                jSONArray.put(obj);
            }
        }
        try {
            registerSuperProperties(new JSONObject().put(str, jSONArray));
            this.mPeople.set(str, jSONArray);
        } catch (JSONException unused) {
            MPLog.w(LOGTAG, "groupKey must be non-null");
        }
    }

    public void setMaximumDatabaseLimit(int i) {
        this.mConfig.setMaximumDatabaseLimit(i);
    }

    public void setServerURL(String str) {
        this.mConfig.setServerURL(str);
    }

    public void setUseIpAddressForGeolocation(boolean z) {
        this.mConfig.setUseIpAddressForGeolocation(z);
    }

    public void timeEvent(String str) {
        if (hasOptedOutTracking()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.mEventTimings) {
            this.mEventTimings.put(str, Long.valueOf(currentTimeMillis));
            this.mPersistentIdentity.addTimeEvent(str, Long.valueOf(currentTimeMillis));
        }
    }

    public void track(String str) {
        if (hasOptedOutTracking()) {
            return;
        }
        track(str, null);
    }

    public void track(String str, JSONObject jSONObject) {
        if (hasOptedOutTracking()) {
            return;
        }
        track(str, jSONObject, false);
    }

    public void track(String str, JSONObject jSONObject, boolean z) {
        Long l;
        if (hasOptedOutTracking()) {
            return;
        }
        if (!z || this.mTrackAutomaticEvents.booleanValue()) {
            synchronized (this.mEventTimings) {
                l = this.mEventTimings.get(str);
                this.mEventTimings.remove(str);
                this.mPersistentIdentity.removeTimedEvent(str);
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, String> entry : this.mPersistentIdentity.getReferrerProperties().entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
                this.mPersistentIdentity.addSuperPropertiesToObject(jSONObject2);
                double currentTimeMillis = System.currentTimeMillis() / 1000.0d;
                String distinctId = getDistinctId();
                String anonymousId = getAnonymousId();
                String userId = getUserId();
                if (MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET) {
                    jSONObject2.put(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, System.currentTimeMillis());
                    jSONObject2.put("distinct_id", distinctId);
                    jSONObject2.put("$had_persisted_distinct_id", this.mPersistentIdentity.getHadPersistedDistinctId());
                    if (userId != null) {
                        jSONObject2.put("user_id", userId);
                    }
                    if (anonymousId != null) {
                        jSONObject2.put(d.g, anonymousId);
                    }
                }
                jSONObject2.put("appid", this.mToken);
                if (l != null) {
                    jSONObject2.put("$duration", currentTimeMillis - (l.longValue() / 1000.0d));
                }
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject2.put(next, jSONObject.opt(next));
                    }
                }
                LMSMessages.EventDescription eventDescription = new LMSMessages.EventDescription(str, jSONObject2, this.mToken, z, this.mSessionMetadata.getMetadataForEvent());
                if (!str.equalsIgnoreCase("fcm") && !str.equalsIgnoreCase(AutomaticEvents.FCM_TOKEN_DELETE)) {
                    this.mMessages.eventsMessage(eventDescription);
                    return;
                }
                this.mMessages.fcmEventsMessage(eventDescription);
            } catch (JSONException e) {
                MPLog.e(LOGTAG, "Exception tracking event " + str, e);
            }
        }
    }

    public void trackMap(String str, Map<String, Object> map) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (map == null) {
            track(str, null);
            return;
        }
        try {
            track(str, new JSONObject((Map<?, ?>) map));
        } catch (NullPointerException unused) {
            MPLog.w(LOGTAG, "Can't have null keys in the properties of trackMap!");
        }
    }

    public void trackWithGroups(String str, Map<String, Object> map, Map<String, Object> map2) {
        if (hasOptedOutTracking()) {
            return;
        }
        if (map2 != null) {
            if (map == null) {
                trackMap(str, map2);
                return;
            }
            for (Map.Entry<String, Object> entry : map2.entrySet()) {
                if (entry.getValue() != null) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        trackMap(str, map);
    }

    public void unregisterSuperProperty(String str) {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mPersistentIdentity.unregisterSuperProperty(str);
    }

    public void updateSuperProperties(SuperPropertyUpdate superPropertyUpdate) {
        if (hasOptedOutTracking()) {
            return;
        }
        this.mPersistentIdentity.updateSuperProperties(superPropertyUpdate);
    }
}
