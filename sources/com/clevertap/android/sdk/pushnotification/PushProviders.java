package com.clevertap.android.sdk.pushnotification;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.AnalyticsManager;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.interfaces.AudibleNotification;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.amp.CTBackgroundIntentService;
import com.clevertap.android.sdk.pushnotification.amp.CTBackgroundJobService;
import com.clevertap.android.sdk.pushnotification.work.CTWorkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import com.clevertap.android.sdk.utils.PackageUtils;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class PushProviders implements CTPushProviderListener {
    public final AnalyticsManager e;
    public final BaseDatabaseManager f;
    public final CleverTapInstanceConfig g;
    public final Context h;
    public final CTWorkManager i;
    public final ValidationResultStack k;
    public CleverTapAPI.DevicePushTokenRefreshListener n;

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<PushConstants.PushType> f2661a = new ArrayList<>();
    public final ArrayList<PushConstants.PushType> b = new ArrayList<>();
    public final ArrayList<CTPushProvider> c = new ArrayList<>();
    public final ArrayList<PushConstants.PushType> d = new ArrayList<>();
    public INotificationRenderer j = new CoreNotificationRenderer();
    public final Object l = new Object();
    public final Object m = new Object();

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ String h;
        public final /* synthetic */ PushConstants.PushType i;

        public a(String str, PushConstants.PushType pushType) {
            this.h = str;
            this.i = pushType;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (PushProviders.this.p(this.h, this.i)) {
                return null;
            }
            String tokenPrefKey = this.i.getTokenPrefKey();
            if (TextUtils.isEmpty(tokenPrefKey)) {
                return null;
            }
            StorageHelper.putStringImmediate(PushProviders.this.h, StorageHelper.storageKeyWithSuffix(PushProviders.this.g, tokenPrefKey), this.h);
            CleverTapInstanceConfig cleverTapInstanceConfig = PushProviders.this.g;
            cleverTapInstanceConfig.log(PushConstants.LOG_TAG, this.i + "Cached New Token successfully " + this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ Bundle h;

        public b(Bundle bundle) {
            this.h = bundle;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            String string = this.h.getString(Constants.NOTIF_MSG);
            if (string == null) {
                string = "";
            }
            if (string.isEmpty()) {
                PushProviders.this.g.getLogger().verbose(PushProviders.this.g.getAccountId(), "Push notification message is empty, not rendering");
                PushProviders.this.f.loadDBAdapter(PushProviders.this.h).storeUninstallTimestamp();
                String string2 = this.h.getString(Constants.PING_FREQUENCY, "");
                if (TextUtils.isEmpty(string2)) {
                    return null;
                }
                PushProviders pushProviders = PushProviders.this;
                pushProviders.updatePingFrequencyIfNeeded(pushProviders.h, Integer.parseInt(string2));
                return null;
            }
            String string3 = this.h.getString(Constants.WZRK_PUSH_ID);
            Bundle bundle = this.h;
            String string4 = bundle.getString("wzrk_ttl", ((System.currentTimeMillis() + Constants.DEFAULT_PUSH_TTL) / 1000) + "");
            long parseLong = Long.parseLong(string4);
            DBAdapter loadDBAdapter = PushProviders.this.f.loadDBAdapter(PushProviders.this.h);
            Logger logger = PushProviders.this.g.getLogger();
            logger.verbose("Storing Push Notification..." + string3 + " - with ttl - " + string4);
            loadDBAdapter.storePushNotificationId(string3, parseLong);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public final /* synthetic */ Context h;

        public c(Context context) {
            this.h = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (Build.VERSION.SDK_INT >= 21) {
                PushProviders.this.g.getLogger().verbose("Creating job");
                PushProviders.this.r(this.h);
                return null;
            }
            PushProviders.this.g.getLogger().verbose("Resetting alarm");
            PushProviders.this.M(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ JobParameters i;

        public d(Context context, JobParameters jobParameters) {
            this.h = context;
            this.i = jobParameters;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (!PushProviders.this.isNotificationSupported()) {
                Logger.v(PushProviders.this.g.getAccountId(), "Token is not present, not running the Job");
                return null;
            }
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(11);
            int i2 = calendar.get(12);
            PushProviders pushProviders = PushProviders.this;
            if (PushProviders.this.C(PushProviders.this.G(Constants.DND_START), PushProviders.this.G(Constants.DND_STOP), pushProviders.G(i + ":" + i2))) {
                Logger.v(PushProviders.this.g.getAccountId(), "Job Service won't run in default DND hours");
                return null;
            }
            long lastUninstallTimestamp = PushProviders.this.f.loadDBAdapter(this.h).getLastUninstallTimestamp();
            if (lastUninstallTimestamp == 0 || lastUninstallTimestamp > System.currentTimeMillis() - 86400000) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("bk", 1);
                    PushProviders.this.e.sendPingEvent(jSONObject);
                    int i3 = Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728;
                    if (this.i == null) {
                        int z = PushProviders.this.z(this.h);
                        AlarmManager alarmManager = (AlarmManager) this.h.getSystemService(NotificationCompat.CATEGORY_ALARM);
                        Intent intent = new Intent(CTBackgroundIntentService.MAIN_ACTION);
                        intent.setPackage(this.h.getPackageName());
                        PendingIntent service = PendingIntent.getService(this.h, PushProviders.this.g.getAccountId().hashCode(), intent, i3);
                        if (alarmManager != null) {
                            alarmManager.cancel(service);
                        }
                        Intent intent2 = new Intent(CTBackgroundIntentService.MAIN_ACTION);
                        intent2.setPackage(this.h.getPackageName());
                        PendingIntent service2 = PendingIntent.getService(this.h, PushProviders.this.g.getAccountId().hashCode(), intent2, i3);
                        if (alarmManager != null && z != -1) {
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            long j = z * Constants.ONE_MIN_IN_MILLIS;
                            alarmManager.setInexactRepeating(2, elapsedRealtime + j, j, service2);
                        }
                    }
                } catch (JSONException unused) {
                    Logger.v("Unable to raise background Ping event");
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public e() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            if (Build.VERSION.SDK_INT >= 21) {
                PushProviders pushProviders = PushProviders.this;
                pushProviders.r(pushProviders.h);
                return null;
            }
            PushProviders pushProviders2 = PushProviders.this;
            pushProviders2.q(pushProviders2.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<Void> {
        public f() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            PushProviders.this.J();
            PushProviders.this.K();
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class g {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2662a;

        static {
            int[] iArr = new int[PushConstants.PushType.values().length];
            f2662a = iArr;
            try {
                iArr[PushConstants.PushType.FCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2662a[PushConstants.PushType.XPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2662a[PushConstants.PushType.HPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2662a[PushConstants.PushType.BPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2662a[PushConstants.PushType.ADM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public PushProviders(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, ValidationResultStack validationResultStack, AnalyticsManager analyticsManager, CTWorkManager cTWorkManager) {
        this.h = context;
        this.g = cleverTapInstanceConfig;
        this.f = baseDatabaseManager;
        this.k = validationResultStack;
        this.e = analyticsManager;
        this.i = cTWorkManager;
        B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(Void r1) {
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void F(List list) throws Exception {
        u(list);
        return null;
    }

    @NonNull
    public static PushProviders load(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, ValidationResultStack validationResultStack, AnalyticsManager analyticsManager, ControllerManager controllerManager, CTWorkManager cTWorkManager) {
        PushProviders pushProviders = new PushProviders(context, cleverTapInstanceConfig, baseDatabaseManager, validationResultStack, analyticsManager, cTWorkManager);
        pushProviders.A();
        controllerManager.setPushProviders(pushProviders);
        return pushProviders;
    }

    @RequiresApi(api = 21)
    public static JobInfo y(int i, JobScheduler jobScheduler) {
        for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
            if (jobInfo.getId() == i) {
                return jobInfo;
            }
        }
        return null;
    }

    public final void A() {
        w();
        final List<CTPushProvider> s = s();
        Task postAsyncSafelyTask = CTExecutorFactory.executors(this.g).postAsyncSafelyTask();
        postAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.pushnotification.a
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public final void onSuccess(Object obj) {
                PushProviders.this.E((Void) obj);
            }
        });
        postAsyncSafelyTask.execute("asyncFindCTPushProviders", new Callable() { // from class: com.clevertap.android.sdk.pushnotification.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void F;
                F = PushProviders.this.F(s);
                return F;
            }
        });
    }

    public final void B() {
        if (!this.g.isBackgroundSync() || this.g.isAnalyticsOnly()) {
            return;
        }
        CTExecutorFactory.executors(this.g).postAsyncSafelyTask().execute("createOrResetJobScheduler", new e());
    }

    public final boolean C(Date date, Date date2, Date date3) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date3);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date2);
        if (date2.compareTo(date) < 0) {
            if (calendar2.compareTo(calendar3) < 0) {
                calendar2.add(5, 1);
            }
            calendar3.add(5, 1);
        }
        return calendar2.compareTo(calendar) >= 0 && calendar2.compareTo(calendar3) < 0;
    }

    public final boolean D(CTPushProvider cTPushProvider) {
        if (50200 < cTPushProvider.minSDKSupportVersionCode()) {
            this.g.log(PushConstants.LOG_TAG, "Provider: %s version %s does not match the SDK version %s. Make sure all CleverTap dependencies are the same version.");
            return false;
        }
        int i = g.f2662a[cTPushProvider.getPushType().ordinal()];
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            if (i == 5 && cTPushProvider.getPlatform() != 2) {
                CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass() + " ADM delivery is only available for Amazon platforms." + cTPushProvider.getPushType());
                return false;
            }
        } else if (cTPushProvider.getPlatform() != 1) {
            CleverTapInstanceConfig cleverTapInstanceConfig2 = this.g;
            cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass() + " delivery is only available for Android platforms." + cTPushProvider.getPushType());
            return false;
        }
        return true;
    }

    public final Date G(String str) {
        try {
            return new SimpleDateFormat("HH:mm", Locale.US).parse(str);
        } catch (ParseException unused) {
            return new Date(0L);
        }
    }

    public final void H(String str, boolean z, PushConstants.PushType pushType) {
        if (pushType == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = getCachedToken(pushType);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.l) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String str2 = z ? "register" : "unregister";
            jSONObject2.put(Constants.KEY_ACTION, str2);
            jSONObject2.put("id", str);
            jSONObject2.put("type", pushType.getType());
            if (pushType == PushConstants.PushType.XPS) {
                this.g.getLogger().verbose("PushProviders: pushDeviceTokenEvent requesting device region");
                jSONObject2.put("region", pushType.getServerRegion());
            }
            jSONObject.put("data", jSONObject2);
            Logger logger = this.g.getLogger();
            String accountId = this.g.getAccountId();
            logger.verbose(accountId, pushType + str2 + " device token " + str);
            this.e.sendDataEvent(jSONObject);
        }
    }

    public final void I() {
        CTExecutorFactory.executors(this.g).ioTask().execute("PushProviders#refreshAllTokens", new f());
    }

    public final void J() {
        Iterator<CTPushProvider> it = this.c.iterator();
        while (it.hasNext()) {
            CTPushProvider next = it.next();
            try {
                next.requestToken();
            } catch (Throwable th) {
                CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "Token Refresh error " + next, th);
            }
        }
    }

    public final void K() {
        Iterator<PushConstants.PushType> it = this.d.iterator();
        while (it.hasNext()) {
            PushConstants.PushType next = it.next();
            try {
                H(getCachedToken(next), true, next);
            } catch (Throwable th) {
                CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "Token Refresh error " + next, th);
            }
        }
    }

    public final void L(String str, PushConstants.PushType pushType) {
        H(str, true, pushType);
        cacheToken(str, pushType);
    }

    public final void M(Context context) {
        if (z(context) <= 0) {
            O(context);
            return;
        }
        O(context);
        q(context);
    }

    public final void N(Context context, int i) {
        StorageHelper.putInt(context, Constants.PING_FREQUENCY, i);
    }

    public final void O(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(CTBackgroundIntentService.MAIN_ACTION);
        intent.setPackage(context.getPackageName());
        PendingIntent service = PendingIntent.getService(context, this.g.getAccountId().hashCode(), intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
        if (alarmManager == null || service == null) {
            return;
        }
        alarmManager.cancel(service);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v12 */
    public final void P(Context context, Bundle bundle, int i) {
        String str;
        int appIconAsIntId;
        int i2;
        NotificationCompat.Builder builder;
        Notification build;
        String notificationIcon;
        String str2;
        int i3;
        int i4 = i;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            this.g.getLogger().debug(this.g.getAccountId(), "Unable to render notification, Notification Manager is null.");
            return;
        }
        String string = bundle.getString(Constants.WZRK_CHANNEL_ID, "");
        boolean z = Build.VERSION.SDK_INT >= 26;
        if (z) {
            if (string.isEmpty()) {
                i3 = 8;
                str2 = bundle.toString();
            } else if (notificationManager.getNotificationChannel(string) == null) {
                i3 = 9;
                str2 = string;
            } else {
                str2 = "";
                i3 = -1;
            }
            if (i3 != -1) {
                ValidationResult create = ValidationResultFactory.create(512, i3, str2);
                this.g.getLogger().debug(this.g.getAccountId(), create.getErrorDesc());
                this.k.pushValidationResult(create);
            }
            str = CTXtensions.getOrCreateChannel(notificationManager, string, context);
            if (str != null && !str.trim().isEmpty()) {
                if (!CTXtensions.isNotificationChannelEnabled(context, str)) {
                    this.g.getLogger().verbose(this.g.getAccountId(), "Not rendering push notification as channel = " + str + " is blocked by user");
                    return;
                }
                this.g.getLogger().debug(this.g.getAccountId(), "Rendering Push on channel = " + str);
            } else {
                this.g.getLogger().debug(this.g.getAccountId(), "Not rendering Push since channel id is null or blank.");
                return;
            }
        } else {
            str = null;
        }
        try {
            notificationIcon = ManifestInfo.getInstance(context).getNotificationIcon();
        } catch (Throwable unused) {
            appIconAsIntId = DeviceInfo.getAppIconAsIntId(context);
        }
        if (notificationIcon != null) {
            appIconAsIntId = context.getResources().getIdentifier(notificationIcon, "drawable", context.getPackageName());
            if (appIconAsIntId == 0) {
                throw new IllegalArgumentException();
            }
            this.j.setSmallIcon(appIconAsIntId, context);
            String string2 = bundle.getString(Constants.NOTIF_PRIORITY);
            if (string2 != null) {
                i2 = string2.equals(Constants.PRIORITY_HIGH);
                if (string2.equals(Constants.PRIORITY_MAX)) {
                    i2 = 2;
                }
            } else {
                i2 = 0;
            }
            if (i4 == -1000) {
                try {
                    Object collapseKey = this.j.getCollapseKey(bundle);
                    if (collapseKey != null) {
                        if (collapseKey instanceof Number) {
                            i4 = ((Number) collapseKey).intValue();
                        } else if (collapseKey instanceof String) {
                            try {
                                i4 = Integer.parseInt(collapseKey.toString());
                                this.g.getLogger().verbose(this.g.getAccountId(), "Converting collapse_key: " + collapseKey + " to notificationId int: " + i4);
                            } catch (NumberFormatException unused2) {
                                i4 = collapseKey.toString().hashCode();
                                this.g.getLogger().verbose(this.g.getAccountId(), "Converting collapse_key: " + collapseKey + " to notificationId int: " + i4);
                            }
                        }
                        i4 = Math.abs(i4);
                        this.g.getLogger().debug(this.g.getAccountId(), "Creating the notification id: " + i4 + " from collapse_key: " + collapseKey);
                    }
                } catch (NumberFormatException unused3) {
                }
            } else {
                this.g.getLogger().debug(this.g.getAccountId(), "Have user provided notificationId: " + i4 + " won't use collapse_key (if any) as basis for notificationId");
            }
            if (i4 == -1000) {
                i4 = (int) (Math.random() * 100.0d);
                this.g.getLogger().debug(this.g.getAccountId(), "Setting random notificationId: " + i4);
            }
            int i5 = i4;
            if (z) {
                builder = new NotificationCompat.Builder(context, str);
                String string3 = bundle.getString(Constants.WZRK_BADGE_ICON, null);
                if (string3 != null) {
                    try {
                        int parseInt = Integer.parseInt(string3);
                        if (parseInt >= 0) {
                            builder.setBadgeIconType(parseInt);
                        }
                    } catch (Throwable unused4) {
                    }
                }
                String string4 = bundle.getString(Constants.WZRK_BADGE_COUNT, null);
                if (string4 != null) {
                    try {
                        int parseInt2 = Integer.parseInt(string4);
                        if (parseInt2 >= 0) {
                            builder.setNumber(parseInt2);
                        }
                    } catch (Throwable unused5) {
                    }
                }
            } else {
                builder = new NotificationCompat.Builder(context);
            }
            builder.setPriority(i2);
            INotificationRenderer iNotificationRenderer = this.j;
            NotificationCompat.Builder builder2 = builder;
            if (iNotificationRenderer instanceof AudibleNotification) {
                builder2 = ((AudibleNotification) iNotificationRenderer).setSound(context, bundle, builder, this.g);
            }
            NotificationCompat.Builder renderNotification = this.j.renderNotification(bundle, context, builder2, this.g, i5);
            if (renderNotification == null) {
                return;
            }
            notificationManager.notify(i5, renderNotification.build());
            this.g.getLogger().debug(this.g.getAccountId(), "Rendered notification: " + build.toString());
            String string5 = bundle.getString(Constants.EXTRAS_FROM);
            if (string5 == null || !string5.equals("PTReceiver")) {
                String string6 = bundle.getString("wzrk_ttl", ((System.currentTimeMillis() + Constants.DEFAULT_PUSH_TTL) / 1000) + "");
                long parseLong = Long.parseLong(string6);
                String string7 = bundle.getString(Constants.WZRK_PUSH_ID);
                DBAdapter loadDBAdapter = this.f.loadDBAdapter(context);
                this.g.getLogger().verbose("Storing Push Notification..." + string7 + " - with ttl - " + string6);
                loadDBAdapter.storePushNotificationId(string7, parseLong);
                if (!"true".equals(bundle.getString(Constants.WZRK_RNV, ""))) {
                    ValidationResult create2 = ValidationResultFactory.create(512, 10, bundle.toString());
                    this.g.getLogger().debug(create2.getErrorDesc());
                    this.k.pushValidationResult(create2);
                    return;
                }
                long j = bundle.getLong(Constants.OMR_INVOKE_TIME_IN_MILLIS, -1L);
                if (j >= 0) {
                    long currentTimeMillis = System.currentTimeMillis() - j;
                    this.g.getLogger().verbose("Rendered Push Notification in " + currentTimeMillis + " millis");
                }
                this.i.init();
                this.e.pushNotificationViewedEvent(bundle);
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public void _createNotification(Context context, Bundle bundle, int i) {
        if (bundle == null || bundle.get("wzrk_pn") == null) {
            return;
        }
        if (this.g.isAnalyticsOnly()) {
            this.g.getLogger().debug(this.g.getAccountId(), "Instance is set for Analytics only, cannot create notification");
            return;
        }
        try {
            if (bundle.getString(Constants.WZRK_PUSH_SILENT, "").equalsIgnoreCase("true")) {
                this.e.pushNotificationViewedEvent(bundle);
                return;
            }
            String string = bundle.getString(Constants.EXTRAS_FROM);
            if (string == null || !string.equals("PTReceiver")) {
                Logger logger = this.g.getLogger();
                String accountId = this.g.getAccountId();
                logger.debug(accountId, "Handling notification: " + bundle);
                if (bundle.getString(Constants.WZRK_PUSH_ID) != null && this.f.loadDBAdapter(context).doesPushNotificationIdExist(bundle.getString(Constants.WZRK_PUSH_ID))) {
                    this.g.getLogger().debug(this.g.getAccountId(), "Push Notification already rendered, not showing again");
                    return;
                }
                String message = this.j.getMessage(bundle);
                if (message == null) {
                    message = "";
                }
                if (message.isEmpty()) {
                    this.g.getLogger().verbose(this.g.getAccountId(), "Push notification message is empty, not rendering");
                    this.f.loadDBAdapter(context).storeUninstallTimestamp();
                    String string2 = bundle.getString(Constants.PING_FREQUENCY, "");
                    if (TextUtils.isEmpty(string2)) {
                        return;
                    }
                    updatePingFrequencyIfNeeded(context, Integer.parseInt(string2));
                    return;
                }
            }
            if (this.j.getTitle(bundle, context).isEmpty()) {
                String str = context.getApplicationInfo().name;
            }
            P(context, bundle, i);
        } catch (Throwable th) {
            this.g.getLogger().debug(this.g.getAccountId(), "Couldn't render notification: ", th);
        }
    }

    public void cacheToken(String str, PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str) || pushType == null) {
            return;
        }
        try {
            CTExecutorFactory.executors(this.g).ioTask().execute("PushProviders#cacheToken", new a(str, pushType));
        } catch (Throwable th) {
            CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
            cleverTapInstanceConfig.log(PushConstants.LOG_TAG, pushType + "Unable to cache token " + str, th);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void doTokenRefresh(String str, PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str) || pushType == null) {
            return;
        }
        int i = g.f2662a[pushType.ordinal()];
        if (i == 1) {
            handleToken(str, PushConstants.PushType.FCM, true);
        } else if (i == 2) {
            handleToken(str, PushConstants.PushType.XPS, true);
        } else if (i == 3) {
            handleToken(str, PushConstants.PushType.HPS, true);
        } else if (i == 4) {
            handleToken(str, PushConstants.PushType.BPS, true);
        } else if (i != 5) {
        } else {
            handleToken(str, PushConstants.PushType.ADM, true);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void forcePushDeviceToken(boolean z) {
        Iterator<PushConstants.PushType> it = this.f2661a.iterator();
        while (it.hasNext()) {
            H(null, z, it.next());
        }
    }

    @NonNull
    public ArrayList<PushConstants.PushType> getAvailablePushTypes() {
        ArrayList<PushConstants.PushType> arrayList = new ArrayList<>();
        Iterator<CTPushProvider> it = this.c.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPushType());
        }
        return arrayList;
    }

    public String getCachedToken(PushConstants.PushType pushType) {
        if (pushType != null) {
            String tokenPrefKey = pushType.getTokenPrefKey();
            if (!TextUtils.isEmpty(tokenPrefKey)) {
                String stringFromPrefs = StorageHelper.getStringFromPrefs(this.h, this.g, tokenPrefKey, null);
                CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, pushType + "getting Cached Token - " + stringFromPrefs);
                return stringFromPrefs;
            }
        }
        if (pushType != null) {
            CleverTapInstanceConfig cleverTapInstanceConfig2 = this.g;
            cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, pushType + " Unable to find cached Token for type ");
        }
        return null;
    }

    public CleverTapAPI.DevicePushTokenRefreshListener getDevicePushTokenRefreshListener() {
        return this.n;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public INotificationRenderer getPushNotificationRenderer() {
        return this.j;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Object getPushRenderingLock() {
        return this.m;
    }

    public void handleToken(String str, PushConstants.PushType pushType, boolean z) {
        if (z) {
            L(str, pushType);
        } else {
            unregisterToken(str, pushType);
        }
    }

    public boolean isNotificationSupported() {
        Iterator<PushConstants.PushType> it = getAvailablePushTypes().iterator();
        while (it.hasNext()) {
            if (getCachedToken(it.next()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProviderListener
    public void onNewToken(String str, PushConstants.PushType pushType) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        doTokenRefresh(str, pushType);
        t(str, pushType);
    }

    public void onTokenRefresh() {
        I();
    }

    public final boolean p(String str, PushConstants.PushType pushType) {
        boolean z = (TextUtils.isEmpty(str) || pushType == null || !str.equalsIgnoreCase(getCachedToken(pushType))) ? false : true;
        if (pushType != null) {
            CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
            cleverTapInstanceConfig.log(PushConstants.LOG_TAG, pushType + "Token Already available value: " + z);
        }
        return z;
    }

    public void processCustomPushNotification(Bundle bundle) {
        CTExecutorFactory.executors(this.g).postAsyncSafelyTask().execute("customHandlePushAmplification", new b(bundle));
    }

    public final void q(Context context) {
        int z = z(context);
        if (z > 0) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intent intent = new Intent(CTBackgroundIntentService.MAIN_ACTION);
            intent.setPackage(context.getPackageName());
            PendingIntent service = PendingIntent.getService(context, this.g.getAccountId().hashCode(), intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
            if (alarmManager != null) {
                alarmManager.setInexactRepeating(2, SystemClock.elapsedRealtime(), Constants.ONE_MIN_IN_MILLIS * z, service);
            }
        }
    }

    @RequiresApi(api = 21)
    @SuppressLint({"MissingPermission"})
    public final void r(Context context) {
        int i = StorageHelper.getInt(context, Constants.PF_JOB_ID, -1);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (Build.VERSION.SDK_INT < 26) {
            if (i >= 0) {
                jobScheduler.cancel(i);
                StorageHelper.putInt(context, Constants.PF_JOB_ID, -1);
            }
            this.g.getLogger().debug(this.g.getAccountId(), "Push Amplification feature is not supported below Oreo");
        } else if (jobScheduler == null) {
        } else {
            int z = z(context);
            if (i >= 0 || z >= 0) {
                if (z < 0) {
                    jobScheduler.cancel(i);
                    StorageHelper.putInt(context, Constants.PF_JOB_ID, -1);
                    return;
                }
                ComponentName componentName = new ComponentName(context, CTBackgroundJobService.class);
                boolean z2 = i < 0 && z > 0;
                JobInfo y = y(i, jobScheduler);
                if (y != null && y.getIntervalMillis() != z * Constants.ONE_MIN_IN_MILLIS) {
                    jobScheduler.cancel(i);
                    StorageHelper.putInt(context, Constants.PF_JOB_ID, -1);
                    z2 = true;
                }
                if (z2) {
                    int hashCode = this.g.getAccountId().hashCode();
                    JobInfo.Builder builder = new JobInfo.Builder(hashCode, componentName);
                    builder.setRequiredNetworkType(1);
                    builder.setRequiresCharging(false);
                    builder.setPeriodic(z * Constants.ONE_MIN_IN_MILLIS, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
                    builder.setRequiresBatteryNotLow(true);
                    if (Utils.hasPermission(context, "android.permission.RECEIVE_BOOT_COMPLETED")) {
                        builder.setPersisted(true);
                    }
                    if (jobScheduler.schedule(builder.build()) == 1) {
                        String accountId = this.g.getAccountId();
                        Logger.d(accountId, "Job scheduled - " + hashCode);
                        StorageHelper.putInt(context, Constants.PF_JOB_ID, hashCode);
                        return;
                    }
                    String accountId2 = this.g.getAccountId();
                    Logger.d(accountId2, "Job not scheduled - " + hashCode);
                }
            }
        }
    }

    public void runInstanceJobWork(Context context, JobParameters jobParameters) {
        CTExecutorFactory.executors(this.g).postAsyncSafelyTask().execute("runningJobService", new d(context, jobParameters));
    }

    @NonNull
    public final List<CTPushProvider> s() {
        ArrayList arrayList = new ArrayList();
        Iterator<PushConstants.PushType> it = this.f2661a.iterator();
        while (it.hasNext()) {
            CTPushProvider x = x(it.next(), true);
            if (x != null) {
                arrayList.add(x);
            }
        }
        Iterator<PushConstants.PushType> it2 = this.b.iterator();
        while (it2.hasNext()) {
            PushConstants.PushType next = it2.next();
            PushConstants.PushType pushType = PushConstants.PushType.XPS;
            if (next == pushType && !TextUtils.isEmpty(getCachedToken(pushType))) {
                CTPushProvider x2 = x(next, false);
                if (x2 instanceof UnregistrableCTPushProvider) {
                    ((UnregistrableCTPushProvider) x2).unregisterPush(this.h);
                    CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                    cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "unregistering existing token for disabled " + next);
                }
            }
        }
        return arrayList;
    }

    public void setDevicePushTokenRefreshListener(CleverTapAPI.DevicePushTokenRefreshListener devicePushTokenRefreshListener) {
        this.n = devicePushTokenRefreshListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setPushNotificationRenderer(@NonNull INotificationRenderer iNotificationRenderer) {
        this.j = iNotificationRenderer;
    }

    public final void t(String str, PushConstants.PushType pushType) {
        if (this.n != null) {
            Logger logger = this.g.getLogger();
            String accountId = this.g.getAccountId();
            logger.debug(accountId, "Notifying devicePushTokenDidRefresh: " + str);
            this.n.devicePushTokenDidRefresh(str, pushType);
        }
    }

    public final void u(List<CTPushProvider> list) {
        if (list.isEmpty()) {
            this.g.log(PushConstants.LOG_TAG, "No push providers found!. Make sure to install at least one push provider");
            return;
        }
        for (CTPushProvider cTPushProvider : list) {
            if (!D(cTPushProvider)) {
                CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "Invalid Provider: " + cTPushProvider.getClass());
            } else if (!cTPushProvider.isSupported()) {
                CleverTapInstanceConfig cleverTapInstanceConfig2 = this.g;
                cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, "Unsupported Provider: " + cTPushProvider.getClass());
            } else if (cTPushProvider.isAvailable()) {
                CleverTapInstanceConfig cleverTapInstanceConfig3 = this.g;
                cleverTapInstanceConfig3.log(PushConstants.LOG_TAG, "Available Provider: " + cTPushProvider.getClass());
                this.c.add(cTPushProvider);
            } else {
                CleverTapInstanceConfig cleverTapInstanceConfig4 = this.g;
                cleverTapInstanceConfig4.log(PushConstants.LOG_TAG, "Unavailable Provider: " + cTPushProvider.getClass());
            }
        }
    }

    public void unregisterToken(String str, PushConstants.PushType pushType) {
        H(str, false, pushType);
    }

    public void updatePingFrequencyIfNeeded(Context context, int i) {
        Logger logger = this.g.getLogger();
        logger.verbose("Ping frequency received - " + i);
        Logger logger2 = this.g.getLogger();
        logger2.verbose("Stored Ping Frequency - " + z(context));
        if (i != z(context)) {
            N(context, i);
            if (!this.g.isBackgroundSync() || this.g.isAnalyticsOnly()) {
                return;
            }
            CTExecutorFactory.executors(this.g).postAsyncSafelyTask().execute("createOrResetJobScheduler", new c(context));
        }
    }

    public final void v() {
        this.d.addAll(this.f2661a);
        Iterator<CTPushProvider> it = this.c.iterator();
        while (it.hasNext()) {
            this.d.remove(it.next().getPushType());
        }
    }

    public final void w() {
        PushConstants.PushType[] pushTypes;
        for (PushConstants.PushType pushType : PushNotificationUtil.getPushTypes(this.g.getAllowedPushTypes())) {
            String messagingSDKClassName = pushType.getMessagingSDKClassName();
            try {
                Class.forName(messagingSDKClassName);
                this.f2661a.add(pushType);
                this.g.log(PushConstants.LOG_TAG, "SDK Class Available :" + messagingSDKClassName);
                if (pushType.getRunningDevices() == 3) {
                    this.f2661a.remove(pushType);
                    this.b.add(pushType);
                    this.g.log(PushConstants.LOG_TAG, "disabling " + pushType + " due to flag set as PushConstants.NO_DEVICES");
                }
                if (pushType.getRunningDevices() == 2 && !PackageUtils.isXiaomiDeviceRunningMiui(this.h)) {
                    this.f2661a.remove(pushType);
                    this.b.add(pushType);
                    this.g.log(PushConstants.LOG_TAG, "disabling " + pushType + " due to flag set as PushConstants.XIAOMI_MIUI_DEVICES");
                }
            } catch (Exception e2) {
                this.g.log(PushConstants.LOG_TAG, "SDK class Not available " + messagingSDKClassName + " Exception:" + e2.getClass().getName());
            }
        }
    }

    @Nullable
    public final CTPushProvider x(PushConstants.PushType pushType, boolean z) {
        String ctProviderClassName = pushType.getCtProviderClassName();
        CTPushProvider cTPushProvider = null;
        try {
            Class<?> cls = Class.forName(ctProviderClassName);
            cTPushProvider = z ? (CTPushProvider) cls.getConstructor(CTPushProviderListener.class, Context.class, CleverTapInstanceConfig.class).newInstance(this, this.h, this.g) : (CTPushProvider) cls.getConstructor(CTPushProviderListener.class, Context.class, CleverTapInstanceConfig.class, Boolean.class).newInstance(this, this.h, this.g, Boolean.FALSE);
            CleverTapInstanceConfig cleverTapInstanceConfig = this.g;
            cleverTapInstanceConfig.log(PushConstants.LOG_TAG, "Found provider:" + ctProviderClassName);
        } catch (ClassNotFoundException unused) {
            CleverTapInstanceConfig cleverTapInstanceConfig2 = this.g;
            cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, "Unable to create provider ClassNotFoundException" + ctProviderClassName);
        } catch (IllegalAccessException unused2) {
            CleverTapInstanceConfig cleverTapInstanceConfig3 = this.g;
            cleverTapInstanceConfig3.log(PushConstants.LOG_TAG, "Unable to create provider IllegalAccessException" + ctProviderClassName);
        } catch (InstantiationException unused3) {
            CleverTapInstanceConfig cleverTapInstanceConfig4 = this.g;
            cleverTapInstanceConfig4.log(PushConstants.LOG_TAG, "Unable to create provider InstantiationException" + ctProviderClassName);
        } catch (Exception e2) {
            CleverTapInstanceConfig cleverTapInstanceConfig5 = this.g;
            cleverTapInstanceConfig5.log(PushConstants.LOG_TAG, "Unable to create provider " + ctProviderClassName + " Exception:" + e2.getClass().getName());
        }
        return cTPushProvider;
    }

    public final int z(Context context) {
        return StorageHelper.getInt(context, Constants.PING_FREQUENCY, 240);
    }
}
