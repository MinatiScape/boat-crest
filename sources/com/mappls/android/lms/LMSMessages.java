package com.mappls.android.lms;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.event.stat.one.d;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import com.mappls.android.util.HttpService;
import com.mappls.android.util.LegacyVersionUtils;
import com.mappls.android.util.MPLog;
import com.mappls.android.util.MessageEncryptor;
import com.mappls.android.util.RemoteService;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class LMSMessages {
    private static final int CLEAR_ANONYMOUS_UPDATES = 7;
    private static final int EMPTY_QUEUES = 6;
    private static final int ENQUEUE_EVENTS = 1;
    private static final int ENQUEUE_FCM_EVENTS = 10;
    private static final int ENQUEUE_GROUP = 3;
    private static final int ENQUEUE_PEOPLE = 0;
    private static final int FLUSH_QUEUE = 2;
    private static final int KILL_WORKER = 5;
    private static final String LOGTAG = "MapplsAnalyticsAPI.Messages";
    private static final int PUSH_ANONYMOUS_PEOPLE_RECORDS = 4;
    private static final int REMOVE_RESIDUAL_IMAGE_FILES = 9;
    private static final int REWRITE_EVENT_PROPERTIES = 8;
    private static final Map<Context, LMSMessages> sInstances = new HashMap();
    public final String deviceId;
    public final MapplsLMSConfig mConfig;
    public final Context mContext;
    private final Worker mWorker = createWorker();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class EventDescription extends MapplsAnalyticsMessageDescription {
        private final String mEventName;
        private final boolean mIsAutomatic;
        private final JSONObject mSessionMetadata;

        public EventDescription(String str, JSONObject jSONObject, String str2) {
            this(str, jSONObject, str2, false, new JSONObject());
        }

        public EventDescription(String str, JSONObject jSONObject, String str2, boolean z, JSONObject jSONObject2) {
            super(str2, jSONObject);
            this.mEventName = str;
            this.mIsAutomatic = z;
            this.mSessionMetadata = jSONObject2;
        }

        public String getEventName() {
            return this.mEventName;
        }

        public JSONObject getProperties() {
            return getMessage();
        }

        public JSONObject getSessionMetadata() {
            return this.mSessionMetadata;
        }

        public boolean isAutomatic() {
            return this.mIsAutomatic;
        }
    }

    /* loaded from: classes11.dex */
    class GroupDescription extends MapplsAnalyticsMessageDescription {
        public GroupDescription(JSONObject jSONObject, String str) {
            super(str, jSONObject);
        }

        public String toString() {
            return getMessage().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class MapplsAnalyticsDescription {
        private final String mToken;

        public MapplsAnalyticsDescription(String str) {
            this.mToken = str;
        }

        public String getToken() {
            return this.mToken;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class MapplsAnalyticsMessageDescription extends MapplsAnalyticsDescription {
        private final JSONObject mMessage;

        public MapplsAnalyticsMessageDescription(String str, JSONObject jSONObject) {
            super(str);
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        jSONObject.get(next).toString();
                    } catch (AssertionError e) {
                        jSONObject.remove(next);
                        MPLog.e(LMSMessages.LOGTAG, "Removing people profile property from update", e);
                    } catch (JSONException unused) {
                    }
                }
            }
            this.mMessage = jSONObject;
        }

        public JSONObject getMessage() {
            return this.mMessage;
        }
    }

    /* loaded from: classes11.dex */
    class PeopleDescription extends MapplsAnalyticsMessageDescription {
        public PeopleDescription(JSONObject jSONObject, String str) {
            super(str, jSONObject);
        }

        public boolean isAnonymous() {
            return !getMessage().has("$distinct_id");
        }

        public String toString() {
            return getMessage().toString();
        }
    }

    /* loaded from: classes11.dex */
    class PushAnonymousPeopleDescription extends MapplsAnalyticsDescription {
        private final String mDistinctId;

        public PushAnonymousPeopleDescription(String str, String str2) {
            super(str2);
            this.mDistinctId = str;
        }

        public String getDistinctId() {
            return this.mDistinctId;
        }

        public String toString() {
            return this.mDistinctId;
        }
    }

    /* loaded from: classes11.dex */
    class UpdateEventsPropertiesDescription extends MapplsAnalyticsDescription {
        private final Map<String, String> mProps;

        public UpdateEventsPropertiesDescription(String str, Map<String, String> map) {
            super(str);
            this.mProps = map;
        }

        public Map<String, String> getProperties() {
            return this.mProps;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class Worker {
        private SystemInformation mSystemInformation;
        private final Object mHandlerLock = new Object();
        private long mFlushCount = 0;
        private long mAveFlushFrequency = 0;
        private long mLastFlushTime = -1;
        private Handler mHandler = restartWorkerThread();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class AnalyticsMessageHandler extends Handler {
            private MapplsLMSDbAdapter mDbAdapter;
            private int mFailedRetries;
            private final long mFlushInterval;
            private long mTrackEngageRetryAfter;

            public AnalyticsMessageHandler(Looper looper) {
                super(looper);
                this.mDbAdapter = null;
                Worker.this.mSystemInformation = SystemInformation.getInstance(LMSMessages.this.mContext);
                this.mFlushInterval = LMSMessages.this.mConfig.getFlushInterval();
            }

            private JSONObject getDefaultEventProperties() {
                JSONObject jSONObject = new JSONObject();
                if (MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET) {
                    jSONObject.put("mp_lib", Constants.KEY_ANDROID);
                    jSONObject.put("$lib_version", "1.0.0");
                    jSONObject.put("$os", "Android");
                    String str = Build.VERSION.RELEASE;
                    if (str == null) {
                        str = "UNKNOWN";
                    }
                    jSONObject.put("$os_version", str);
                    String str2 = Build.MANUFACTURER;
                    if (str2 == null) {
                        str2 = "UNKNOWN";
                    }
                    jSONObject.put("$manufacturer", str2);
                    String str3 = Build.BRAND;
                    if (str3 == null) {
                        str3 = "UNKNOWN";
                    }
                    jSONObject.put("$brand", str3);
                    String str4 = Build.MODEL;
                    jSONObject.put("$model", str4 != null ? str4 : "UNKNOWN");
                    DisplayMetrics displayMetrics = Worker.this.mSystemInformation.getDisplayMetrics();
                    jSONObject.put("$screen_dpi", displayMetrics.densityDpi);
                    jSONObject.put("$screen_height", displayMetrics.heightPixels);
                    jSONObject.put("$screen_width", displayMetrics.widthPixels);
                    String appVersionName = Worker.this.mSystemInformation.getAppVersionName();
                    if (appVersionName != null) {
                        jSONObject.put("$app_version", appVersionName);
                        jSONObject.put("$app_version_string", appVersionName);
                    }
                    Integer appVersionCode = Worker.this.mSystemInformation.getAppVersionCode();
                    if (appVersionCode != null) {
                        String valueOf = String.valueOf(appVersionCode);
                        jSONObject.put("$app_release", valueOf);
                        jSONObject.put("$app_build_number", valueOf);
                    }
                    Boolean valueOf2 = Boolean.valueOf(Worker.this.mSystemInformation.hasNFC());
                    if (valueOf2 != null) {
                        jSONObject.put("$has_nfc", valueOf2.booleanValue());
                    }
                    Boolean valueOf3 = Boolean.valueOf(Worker.this.mSystemInformation.hasTelephony());
                    if (valueOf3 != null) {
                        jSONObject.put("$has_telephone", valueOf3.booleanValue());
                    }
                    String currentNetworkOperator = Worker.this.mSystemInformation.getCurrentNetworkOperator();
                    if (currentNetworkOperator != null && !currentNetworkOperator.trim().isEmpty()) {
                        jSONObject.put("$carrier", currentNetworkOperator);
                    }
                    Boolean isWifiConnected = Worker.this.mSystemInformation.isWifiConnected();
                    if (isWifiConnected != null) {
                        jSONObject.put("$wifi", isWifiConnected.booleanValue());
                    }
                    Boolean isBluetoothEnabled = Worker.this.mSystemInformation.isBluetoothEnabled();
                    if (isBluetoothEnabled != null) {
                        jSONObject.put("$bluetooth_enabled", isBluetoothEnabled);
                    }
                    String bluetoothVersion = Worker.this.mSystemInformation.getBluetoothVersion();
                    if (bluetoothVersion != null) {
                        jSONObject.put("$bluetooth_version", bluetoothVersion);
                    }
                    return jSONObject;
                }
                return jSONObject;
            }

            private JSONObject prepareEventObject(EventDescription eventDescription) {
                JSONObject jSONObject = new JSONObject();
                JSONObject properties = eventDescription.getProperties();
                JSONObject defaultEventProperties = getDefaultEventProperties();
                defaultEventProperties.put("appid", eventDescription.getToken());
                defaultEventProperties.put("timestamp", System.currentTimeMillis() / 1000);
                defaultEventProperties.put(d.g, LMSMessages.this.deviceId);
                if (properties != null) {
                    Iterator<String> keys = properties.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        defaultEventProperties.put(next, properties.get(next));
                    }
                }
                jSONObject.put("properties", defaultEventProperties);
                jSONObject.put("activity_name", eventDescription.getEventName());
                if (MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET) {
                    jSONObject.put("$mp_metadata", eventDescription.getSessionMetadata());
                }
                return jSONObject;
            }

            private void sendAllData(MapplsLMSDbAdapter mapplsLMSDbAdapter, String str) {
                RemoteService poster = LMSMessages.this.getPoster();
                LMSMessages lMSMessages = LMSMessages.this;
                if (!poster.isOnline(lMSMessages.mContext, lMSMessages.mConfig.getOfflineMode())) {
                    LMSMessages.this.logAboutMessageToMapplsAnalytics("Not flushing data to Mappls Analytics because the device is not connected to the internet.");
                    return;
                }
                sendData(mapplsLMSDbAdapter, str, MapplsLMSDbAdapter.Table.EVENTS, LMSMessages.this.mConfig.getEventsEndpoint());
                sendData(mapplsLMSDbAdapter, str, MapplsLMSDbAdapter.Table.FCM_EVENTS, LMSMessages.this.mConfig.getFcmEventsEndpoint());
            }

            /* JADX WARN: Removed duplicated region for block: B:43:0x0145  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0166 A[SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private void sendData(com.mappls.android.lms.MapplsLMSDbAdapter r17, java.lang.String r18, com.mappls.android.lms.MapplsLMSDbAdapter.Table r19, java.lang.String r20) {
                /*
                    Method dump skipped, instructions count: 442
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.LMSMessages.Worker.AnalyticsMessageHandler.sendData(com.mappls.android.lms.MapplsLMSDbAdapter, java.lang.String, com.mappls.android.lms.MapplsLMSDbAdapter$Table, java.lang.String):void");
            }

            public long getTrackEngageRetryAfter() {
                return this.mTrackEngageRetryAfter;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String str;
                JSONObject prepareEventObject;
                String token;
                String token2;
                MapplsLMSDbAdapter mapplsLMSDbAdapter;
                if (this.mDbAdapter == null) {
                    LMSMessages lMSMessages = LMSMessages.this;
                    MapplsLMSDbAdapter makeDbAdapter = lMSMessages.makeDbAdapter(lMSMessages.mContext);
                    this.mDbAdapter = makeDbAdapter;
                    makeDbAdapter.cleanupEvents(System.currentTimeMillis() - LMSMessages.this.mConfig.getDataExpiration(), MapplsLMSDbAdapter.Table.EVENTS);
                    this.mDbAdapter.cleanupEvents(System.currentTimeMillis() - LMSMessages.this.mConfig.getDataExpiration(), MapplsLMSDbAdapter.Table.PEOPLE);
                }
                int i = -3;
                try {
                    int i2 = message.what;
                    if (i2 == 0) {
                        PeopleDescription peopleDescription = (PeopleDescription) message.obj;
                        MapplsLMSDbAdapter.Table table = peopleDescription.isAnonymous() ? MapplsLMSDbAdapter.Table.ANONYMOUS_PEOPLE : MapplsLMSDbAdapter.Table.PEOPLE;
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("Queuing people record for sending later");
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("    " + peopleDescription.toString());
                        str = peopleDescription.getToken();
                        i = this.mDbAdapter.addJSON(MessageEncryptor.offlineEncryptMessage(peopleDescription.getMessage().toString()), str, table);
                        if (peopleDescription.isAnonymous()) {
                            i = 0;
                        }
                    } else if (i2 == 3) {
                        GroupDescription groupDescription = (GroupDescription) message.obj;
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("Queuing group record for sending later");
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("    " + groupDescription.toString());
                        String token3 = groupDescription.getToken();
                        i = this.mDbAdapter.addJSON(MessageEncryptor.offlineEncryptMessage(groupDescription.getMessage().toString()), token3, MapplsLMSDbAdapter.Table.GROUPS);
                        str = token3;
                    } else {
                        if (i2 != 1 && i2 != 10) {
                            if (i2 == 4) {
                                PushAnonymousPeopleDescription pushAnonymousPeopleDescription = (PushAnonymousPeopleDescription) message.obj;
                                String distinctId = pushAnonymousPeopleDescription.getDistinctId();
                                String token4 = pushAnonymousPeopleDescription.getToken();
                                str = token4;
                                i = this.mDbAdapter.pushAnonymousUpdatesToPeopleDb(token4, distinctId);
                            } else {
                                if (i2 == 7) {
                                    token2 = ((MapplsAnalyticsDescription) message.obj).getToken();
                                    mapplsLMSDbAdapter = this.mDbAdapter;
                                } else {
                                    if (i2 == 8) {
                                        UpdateEventsPropertiesDescription updateEventsPropertiesDescription = (UpdateEventsPropertiesDescription) message.obj;
                                        MPLog.d(LMSMessages.LOGTAG, this.mDbAdapter.rewriteEventDataWithProperties(updateEventsPropertiesDescription.getProperties(), updateEventsPropertiesDescription.getToken()) + " stored events were updated with new properties.");
                                    } else if (i2 == 2) {
                                        LMSMessages.this.logAboutMessageToMapplsAnalytics("Flushing queue due to scheduled or forced flush");
                                        Worker.this.updateFlushFrequency();
                                        str = (String) message.obj;
                                        sendAllData(this.mDbAdapter, str);
                                    } else if (i2 == 6) {
                                        token2 = ((MapplsAnalyticsDescription) message.obj).getToken();
                                        this.mDbAdapter.cleanupAllEvents(MapplsLMSDbAdapter.Table.EVENTS, token2);
                                        this.mDbAdapter.cleanupAllEvents(MapplsLMSDbAdapter.Table.FCM_EVENTS, token2);
                                        this.mDbAdapter.cleanupAllEvents(MapplsLMSDbAdapter.Table.PEOPLE, token2);
                                        this.mDbAdapter.cleanupAllEvents(MapplsLMSDbAdapter.Table.GROUPS, token2);
                                        mapplsLMSDbAdapter = this.mDbAdapter;
                                    } else if (i2 == 5) {
                                        MPLog.w(LMSMessages.LOGTAG, "Worker received a hard kill. Dumping all events and force-killing. Thread id " + Thread.currentThread().getId());
                                        synchronized (Worker.this.mHandlerLock) {
                                            this.mDbAdapter.deleteDB();
                                            Worker.this.mHandler = null;
                                            Looper.myLooper().quit();
                                        }
                                    } else if (i2 == 9) {
                                        LegacyVersionUtils.removeLegacyResidualImageFiles((File) message.obj);
                                    } else {
                                        MPLog.e(LMSMessages.LOGTAG, "Unexpected message received by Mappls Analytics worker: " + message);
                                    }
                                    str = null;
                                }
                                str = token2;
                                mapplsLMSDbAdapter.cleanupAllEvents(MapplsLMSDbAdapter.Table.ANONYMOUS_PEOPLE, str);
                            }
                        }
                        EventDescription eventDescription = (EventDescription) message.obj;
                        try {
                            prepareEventObject = prepareEventObject(eventDescription);
                            LMSMessages.this.logAboutMessageToMapplsAnalytics("Queuing event for sending later");
                            LMSMessages.this.logAboutMessageToMapplsAnalytics("    " + prepareEventObject.toString());
                            token = eventDescription.getToken();
                        } catch (JSONException e) {
                            e = e;
                            str = null;
                        }
                        try {
                            i = this.mDbAdapter.addJSON(MessageEncryptor.offlineEncryptMessage(prepareEventObject.toString()), token, message.what == 10 ? MapplsLMSDbAdapter.Table.FCM_EVENTS : MapplsLMSDbAdapter.Table.EVENTS);
                            str = token;
                        } catch (JSONException e2) {
                            e = e2;
                            str = token;
                            MPLog.e(LMSMessages.LOGTAG, "Exception tracking event " + eventDescription.getEventName(), e);
                            if (i < LMSMessages.this.mConfig.getBulkUploadLimit()) {
                            }
                            LMSMessages.this.logAboutMessageToMapplsAnalytics("Flushing queue due to bulk upload limit (" + i + ") for project " + str);
                            Worker.this.updateFlushFrequency();
                            sendAllData(this.mDbAdapter, str);
                        }
                    }
                    if ((i < LMSMessages.this.mConfig.getBulkUploadLimit() || i == -2) && this.mFailedRetries <= 0 && str != null) {
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("Flushing queue due to bulk upload limit (" + i + ") for project " + str);
                        Worker.this.updateFlushFrequency();
                        sendAllData(this.mDbAdapter, str);
                    } else if (i <= 0 || hasMessages(2, str)) {
                    } else {
                        LMSMessages.this.logAboutMessageToMapplsAnalytics("Queue depth " + i + " - Adding flush in " + this.mFlushInterval);
                        if (this.mFlushInterval >= 0) {
                            Message obtain = Message.obtain();
                            obtain.what = 2;
                            obtain.obj = str;
                            obtain.arg1 = 1;
                            sendMessageDelayed(obtain, this.mFlushInterval);
                        }
                    }
                } catch (RuntimeException e3) {
                    MPLog.e(LMSMessages.LOGTAG, "Worker threw an unhandled exception", e3);
                    synchronized (Worker.this.mHandlerLock) {
                        Worker.this.mHandler = null;
                        try {
                            Looper.myLooper().quit();
                            MPLog.e(LMSMessages.LOGTAG, "Mappls Analytics will not process any more analytics messages", e3);
                        } catch (Exception e4) {
                            MPLog.e(LMSMessages.LOGTAG, "Could not halt looper", e4);
                        }
                    }
                }
            }
        }

        public Worker() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateFlushFrequency() {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.mFlushCount;
            long j2 = 1 + j;
            long j3 = this.mLastFlushTime;
            if (j3 > 0) {
                long j4 = ((this.mAveFlushFrequency * j) + (currentTimeMillis - j3)) / j2;
                this.mAveFlushFrequency = j4;
                LMSMessages lMSMessages = LMSMessages.this;
                lMSMessages.logAboutMessageToMapplsAnalytics("Average send frequency approximately " + (j4 / 1000) + " seconds.");
            }
            this.mLastFlushTime = currentTimeMillis;
            this.mFlushCount = j2;
        }

        public boolean isDead() {
            boolean z;
            synchronized (this.mHandlerLock) {
                z = this.mHandler == null;
            }
            return z;
        }

        public Handler restartWorkerThread() {
            HandlerThread handlerThread = new HandlerThread("com.mappls.android.AnalyticsWorker", 10);
            handlerThread.start();
            return new AnalyticsMessageHandler(handlerThread.getLooper());
        }

        public void runMessage(Message message) {
            synchronized (this.mHandlerLock) {
                Handler handler = this.mHandler;
                if (handler == null) {
                    LMSMessages lMSMessages = LMSMessages.this;
                    lMSMessages.logAboutMessageToMapplsAnalytics("Dead Mappls Analytics worker dropping a message: " + message.what);
                } else {
                    handler.sendMessage(message);
                }
            }
        }
    }

    public LMSMessages(Context context) {
        this.mContext = context;
        this.mConfig = getConfig(context);
        this.deviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        getPoster().checkIsMapplsAnalyticsBlocked();
    }

    public static LMSMessages getInstance(Context context) {
        LMSMessages lMSMessages;
        Map<Context, LMSMessages> map = sInstances;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (map.containsKey(applicationContext)) {
                lMSMessages = map.get(applicationContext);
            } else {
                lMSMessages = new LMSMessages(applicationContext);
                map.put(applicationContext, lMSMessages);
            }
        }
        return lMSMessages;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAboutMessageToMapplsAnalytics(String str) {
        MPLog.v(LOGTAG, str + " (Thread " + Thread.currentThread().getId() + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAboutMessageToMapplsAnalytics(String str, Throwable th) {
        MPLog.v(LOGTAG, str + " (Thread " + Thread.currentThread().getId() + ")", th);
    }

    public void clearAnonymousUpdatesMessage(MapplsAnalyticsDescription mapplsAnalyticsDescription) {
        Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = mapplsAnalyticsDescription;
        this.mWorker.runMessage(obtain);
    }

    public Worker createWorker() {
        return new Worker();
    }

    public void emptyTrackingQueues(MapplsAnalyticsDescription mapplsAnalyticsDescription) {
        Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = mapplsAnalyticsDescription;
        this.mWorker.runMessage(obtain);
    }

    public void eventsMessage(EventDescription eventDescription) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = eventDescription;
        this.mWorker.runMessage(obtain);
    }

    public void fcmEventsMessage(EventDescription eventDescription) {
        Message obtain = Message.obtain();
        obtain.what = 10;
        obtain.obj = eventDescription;
        this.mWorker.runMessage(obtain);
    }

    public MapplsLMSConfig getConfig(Context context) {
        return MapplsLMSConfig.getInstance(context);
    }

    public RemoteService getPoster() {
        return new HttpService();
    }

    public long getTrackEngageRetryAfter() {
        return ((Worker.AnalyticsMessageHandler) this.mWorker.mHandler).getTrackEngageRetryAfter();
    }

    public void groupMessage(GroupDescription groupDescription) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = groupDescription;
        this.mWorker.runMessage(obtain);
    }

    public void hardKill() {
        Message obtain = Message.obtain();
        obtain.what = 5;
        this.mWorker.runMessage(obtain);
    }

    public boolean isDead() {
        return this.mWorker.isDead();
    }

    public MapplsLMSDbAdapter makeDbAdapter(Context context) {
        return MapplsLMSDbAdapter.getInstance(context);
    }

    public void peopleMessage(PeopleDescription peopleDescription) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = peopleDescription;
        this.mWorker.runMessage(obtain);
    }

    public void postToServer(MapplsAnalyticsDescription mapplsAnalyticsDescription) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = mapplsAnalyticsDescription.getToken();
        obtain.arg1 = 0;
        this.mWorker.runMessage(obtain);
    }

    public void pushAnonymousPeopleMessage(PushAnonymousPeopleDescription pushAnonymousPeopleDescription) {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = pushAnonymousPeopleDescription;
        this.mWorker.runMessage(obtain);
    }

    public void removeResidualImageFiles(File file) {
        Message obtain = Message.obtain();
        obtain.what = 9;
        obtain.obj = file;
        this.mWorker.runMessage(obtain);
    }

    public void updateEventProperties(UpdateEventsPropertiesDescription updateEventsPropertiesDescription) {
        Message obtain = Message.obtain();
        obtain.what = 8;
        obtain.obj = updateEventsPropertiesDescription;
        this.mWorker.runMessage(obtain);
    }
}
