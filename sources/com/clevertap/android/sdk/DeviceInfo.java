package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.login.LoginInfoProvider;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class DeviceInfo {
    public static final int SMART_PHONE = 1;
    public static final int TABLET = 2;
    public static int m = -1;
    public d c;
    public final CleverTapInstanceConfig d;
    public final Context e;
    public final CoreMetaData k;

    /* renamed from: a  reason: collision with root package name */
    public final Object f2576a = new Object();
    public boolean b = false;
    public final Object f = new Object();
    public boolean g = false;
    public String h = null;
    public boolean j = false;
    public final ArrayList<ValidationResult> l = new ArrayList<>();
    public String i = null;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            DeviceInfo.this.n();
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements OnSuccessListener<Void> {
        public b() {
        }

        @Override // com.clevertap.android.sdk.task.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Void r4) {
            DeviceInfo.this.m().verbose(DeviceInfo.this.d.getAccountId() + ":async_deviceID", "DeviceID initialized successfully!" + Thread.currentThread());
            CleverTapAPI.instanceWithConfig(DeviceInfo.this.e, DeviceInfo.this.d).j(DeviceInfo.this.getDeviceID());
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public final /* synthetic */ String h;

        public c(String str) {
            this.h = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            DeviceInfo.this.s(this.h);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d {
        public final int e;
        public final double n;
        public String o;
        public int p;
        public final String m = F();
        public final String j = C();
        public final String k = D();
        public final String g = z();
        public final String h = A();
        public final String c = u();
        public final int b = t();
        public final String i = B();

        /* renamed from: a  reason: collision with root package name */
        public final String f2578a = s();
        public final String d = v();
        public final int l = E();
        public final double f = x();

        public d() {
            y();
            this.n = G();
            H();
            this.e = w();
            this.p = DeviceInfo.this.r();
            if (Build.VERSION.SDK_INT >= 28) {
                this.o = r();
            }
        }

        public static /* synthetic */ int g(d dVar) {
            int i = dVar.p;
            dVar.p = i + 1;
            return i;
        }

        public final String A() {
            return Build.MODEL.replace(z(), "");
        }

        @SuppressLint({"MissingPermission"})
        public final String B() {
            return Utils.getDeviceNetworkType(DeviceInfo.this.e);
        }

        public final String C() {
            return "Android";
        }

        public final String D() {
            return Build.VERSION.RELEASE;
        }

        public final int E() {
            return BuildConfig.VERSION_CODE;
        }

        public final String F() {
            try {
                return DeviceInfo.this.e.getPackageManager().getPackageInfo(DeviceInfo.this.e.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
                Logger.d("Unable to get app version");
                return null;
            }
        }

        public final double G() {
            int i;
            float f;
            WindowManager windowManager = (WindowManager) DeviceInfo.this.e.getSystemService("window");
            if (windowManager == null) {
                return 0.0d;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                Configuration configuration = DeviceInfo.this.e.getResources().getConfiguration();
                Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemGestures());
                i = (currentWindowMetrics.getBounds().width() - insetsIgnoringVisibility.right) - insetsIgnoringVisibility.left;
                f = configuration.densityDpi;
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                i = displayMetrics.widthPixels;
                f = displayMetrics.xdpi;
            }
            return I(i / f);
        }

        public final int H() {
            WindowManager windowManager = (WindowManager) DeviceInfo.this.e.getSystemService("window");
            if (windowManager == null) {
                return 0;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemGestures());
                return (currentWindowMetrics.getBounds().width() - insetsIgnoringVisibility.right) - insetsIgnoringVisibility.left;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }

        public final double I(double d) {
            return Math.round(d * 100.0d) / 100.0d;
        }

        @RequiresApi(api = 28)
        public final String r() {
            int appStandbyBucket = ((UsageStatsManager) DeviceInfo.this.e.getSystemService("usagestats")).getAppStandbyBucket();
            return appStandbyBucket != 10 ? appStandbyBucket != 20 ? appStandbyBucket != 30 ? appStandbyBucket != 40 ? appStandbyBucket != 45 ? "" : DirectionsCriteria.EXCLUDE_RESTRICTED : "rare" : "frequent" : "working_set" : AppMeasurementSdk.ConditionalUserProperty.ACTIVE;
        }

        public final String s() {
            return (Build.VERSION.SDK_INT < 18 || !DeviceInfo.this.e.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) ? DeviceInfo.this.e.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? "classic" : "none" : "ble";
        }

        public final int t() {
            try {
                return DeviceInfo.this.e.getPackageManager().getPackageInfo(DeviceInfo.this.e.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                Logger.d("Unable to get app build");
                return 0;
            }
        }

        public final String u() {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.e.getSystemService("phone");
                if (telephonyManager != null) {
                    return telephonyManager.getNetworkOperatorName();
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        public final String v() {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.e.getSystemService("phone");
                return telephonyManager != null ? telephonyManager.getSimCountryIso() : "";
            } catch (Throwable unused) {
                return "";
            }
        }

        public final int w() {
            WindowManager windowManager = (WindowManager) DeviceInfo.this.e.getSystemService("window");
            if (windowManager == null) {
                return 0;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                return DeviceInfo.this.e.getResources().getConfiguration().densityDpi;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.densityDpi;
        }

        public final double x() {
            int i;
            float f;
            WindowManager windowManager = (WindowManager) DeviceInfo.this.e.getSystemService("window");
            if (windowManager == null) {
                return 0.0d;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                Configuration configuration = DeviceInfo.this.e.getResources().getConfiguration();
                Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemGestures());
                i = (currentWindowMetrics.getBounds().height() - insetsIgnoringVisibility.top) - insetsIgnoringVisibility.bottom;
                f = configuration.densityDpi;
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                i = displayMetrics.heightPixels;
                f = displayMetrics.ydpi;
            }
            return I(i / f);
        }

        public final int y() {
            WindowManager windowManager = (WindowManager) DeviceInfo.this.e.getSystemService("window");
            if (windowManager == null) {
                return 0;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemGestures());
                return (currentWindowMetrics.getBounds().height() - insetsIgnoringVisibility.top) - insetsIgnoringVisibility.bottom;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }

        public final String z() {
            return Build.MANUFACTURER;
        }
    }

    public DeviceInfo(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, CoreMetaData coreMetaData) {
        this.e = context;
        this.d = cleverTapInstanceConfig;
        this.k = coreMetaData;
        t(str);
        Logger m2 = m();
        m2.verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "DeviceInfo() called");
    }

    public static int getAppIconAsIntId(Context context) {
        return context.getApplicationInfo().icon;
    }

    public static int getDeviceType(Context context) {
        if (m == -1) {
            try {
                if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4) {
                    m = 3;
                    return 3;
                }
            } catch (Exception e) {
                Logger.d("Failed to decide whether device is a TV!");
                e.printStackTrace();
            }
            try {
                m = context.getResources().getBoolean(R.bool.ctIsTablet) ? 2 : 1;
            } catch (Exception e2) {
                Logger.d("Failed to decide whether device is a smart phone or tablet!");
                e2.printStackTrace();
                m = 0;
            }
        }
        return m;
    }

    public final void A(String str) {
        Logger m2 = m();
        String accountId = this.d.getAccountId();
        m2.verbose(accountId, "Updating the fallback id - " + str);
        StorageHelper.putString(this.e, q(), str);
    }

    public final String a() {
        synchronized (this.f) {
            if (this.d.isDefaultInstance()) {
                String string = StorageHelper.getString(this.e, o(), null);
                if (string == null) {
                    string = StorageHelper.getString(this.e, "deviceId", null);
                }
                return string;
            }
            return StorageHelper.getString(this.e, o(), null);
        }
    }

    public void forceNewDeviceID() {
        forceUpdateDeviceId(k());
    }

    public void forceUpdateCustomCleverTapID(String str) {
        if (Utils.validateCTID(str)) {
            Logger m2 = m();
            String accountId = this.d.getAccountId();
            m2.info(accountId, "Setting CleverTap ID to custom CleverTap ID : " + str);
            forceUpdateDeviceId(Constants.CUSTOM_CLEVERTAP_ID_PREFIX + str);
            return;
        }
        z();
        w();
        m().info(this.d.getAccountId(), v(21, str, p()));
    }

    @SuppressLint({"CommitPrefEdits"})
    public void forceUpdateDeviceId(String str) {
        Logger m2 = m();
        String accountId = this.d.getAccountId();
        m2.verbose(accountId, "Force updating the device ID to " + str);
        synchronized (this.f) {
            StorageHelper.putString(this.e, o(), str);
        }
    }

    public String getAppBucket() {
        return n().o;
    }

    public JSONObject getAppLaunchedFields() {
        try {
            return CTJsonConverter.from(this, this.k, this.g, getGoogleAdID() != null ? new LoginInfoProvider(this.e, this.d, this).deviceIsMultiUser() : false);
        } catch (Throwable th) {
            this.d.getLogger().verbose(this.d.getAccountId(), "Failed to construct App Launched event", th);
            return new JSONObject();
        }
    }

    public String getBluetoothVersion() {
        return n().f2578a;
    }

    public int getBuild() {
        return n().b;
    }

    public String getCarrier() {
        return n().c;
    }

    public Context getContext() {
        return this.e;
    }

    public String getCountryCode() {
        return n().d;
    }

    public int getDPI() {
        return n().e;
    }

    public String getDeviceID() {
        return a() != null ? a() : p();
    }

    public String getGoogleAdID() {
        String str;
        synchronized (this.f2576a) {
            str = this.h;
        }
        return str;
    }

    public double getHeight() {
        return n().f;
    }

    public String getLibrary() {
        return this.i;
    }

    public int getLocalInAppCount() {
        return n().p;
    }

    public String getManufacturer() {
        return n().g;
    }

    public String getModel() {
        return n().h;
    }

    public String getNetworkType() {
        return n().i;
    }

    public String getOsName() {
        return n().j;
    }

    public String getOsVersion() {
        return n().k;
    }

    public int getSdkVersion() {
        return n().l;
    }

    public ArrayList<ValidationResult> getValidationResults() {
        ArrayList<ValidationResult> arrayList = (ArrayList) this.l.clone();
        this.l.clear();
        return arrayList;
    }

    public String getVersionName() {
        return n().m;
    }

    public double getWidth() {
        return n().n;
    }

    public void h(boolean z) {
        this.g = z;
        StorageHelper.putBoolean(this.e, StorageHelper.storageKeyWithSuffix(this.d, Constants.NETWORK_INFO), this.g);
        Logger logger = this.d.getLogger();
        String accountId = this.d.getAccountId();
        logger.verbose(accountId, "Device Network Information reporting set to " + this.g);
    }

    public final synchronized void i() {
        m().verbose(this.d.getAccountId() + ":async_deviceID", "fetchGoogleAdID() called!");
        if (getGoogleAdID() == null && !this.b) {
            boolean z = true;
            this.b = true;
            Object invoke = AdvertisingIdClient.class.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.e);
            Boolean bool = (Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0]);
            synchronized (this.f2576a) {
                if (bool == null || !bool.booleanValue()) {
                    z = false;
                }
                this.j = z;
                m().verbose(this.d.getAccountId() + ":async_deviceID", "limitAdTracking = " + this.j);
                if (this.j) {
                    m().debug(this.d.getAccountId(), "Device user has opted out of sharing Advertising ID, falling back to random UUID for CleverTap ID generation");
                    return;
                }
                String str = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                if (str != null && str.trim().length() > 2) {
                    synchronized (this.f2576a) {
                        if (str.contains("00000000")) {
                            m().debug(this.d.getAccountId(), "Device user has opted out of sharing Advertising ID, falling back to random UUID for CleverTap ID generation");
                            return;
                        }
                        this.h = str.replace("-", "");
                    }
                }
                m().verbose(this.d.getAccountId() + ":async_deviceID", "fetchGoogleAdID() done executing!");
            }
        }
    }

    public void incrementLocalInAppCount() {
        d.g(n());
    }

    @SuppressLint({"MissingPermission"})
    public Boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter;
        try {
            if (this.e.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.e.getPackageName()) != 0 || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return null;
            }
            return Boolean.valueOf(defaultAdapter.isEnabled());
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isErrorDeviceId() {
        return getDeviceID() != null && getDeviceID().startsWith(Constants.ERROR_PROFILE_PREFIX);
    }

    public boolean isLimitAdTrackingEnabled() {
        boolean z;
        synchronized (this.f2576a) {
            z = this.j;
        }
        return z;
    }

    public Boolean isWifiConnected() {
        ConnectivityManager connectivityManager;
        if (this.e.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (connectivityManager = (ConnectivityManager) this.e.getSystemService("connectivity")) == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = true;
        return Boolean.valueOf((activeNetworkInfo != null && activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected()) ? false : false);
    }

    public final synchronized void j() {
        String k;
        String str;
        m().verbose(this.d.getAccountId() + ":async_deviceID", "generateDeviceID() called!");
        String googleAdID = getGoogleAdID();
        if (googleAdID != null) {
            str = Constants.GUID_PREFIX_GOOGLE_AD_ID + googleAdID;
        } else {
            synchronized (this.f) {
                k = k();
            }
            str = k;
        }
        forceUpdateDeviceId(str);
        m().verbose(this.d.getAccountId() + ":async_deviceID", "generateDeviceID() done executing!");
    }

    public final String k() {
        return "__" + UUID.randomUUID().toString().replace("-", "");
    }

    public String l() {
        return getDeviceID();
    }

    public final Logger m() {
        return this.d.getLogger();
    }

    public final d n() {
        if (this.c == null) {
            this.c = new d();
        }
        return this.c;
    }

    public final String o() {
        return "deviceId:" + this.d.getAccountId();
    }

    public final String p() {
        return StorageHelper.getString(this.e, q(), null);
    }

    public final String q() {
        return "fallbackId:" + this.d.getAccountId();
    }

    @WorkerThread
    public final int r() {
        return StorageHelper.getInt(this.e, InAppController.LOCAL_INAPP_COUNT, 0);
    }

    public final void s(String str) {
        Logger m2 = m();
        m2.verbose(this.d.getAccountId() + ":async_deviceID", "Called initDeviceID()");
        if (this.d.getEnableCustomCleverTapId()) {
            if (str == null) {
                this.d.getLogger().info(v(18, new String[0]));
            }
        } else if (str != null) {
            this.d.getLogger().info(v(19, new String[0]));
        }
        Logger m3 = m();
        m3.verbose(this.d.getAccountId() + ":async_deviceID", "Calling _getDeviceID");
        String a2 = a();
        Logger m4 = m();
        m4.verbose(this.d.getAccountId() + ":async_deviceID", "Called _getDeviceID");
        if (a2 != null && a2.trim().length() > 2) {
            m().verbose(this.d.getAccountId(), "CleverTap ID already present for profile");
            if (str != null) {
                m().info(this.d.getAccountId(), v(20, a2, str));
            }
        } else if (this.d.getEnableCustomCleverTapId()) {
            forceUpdateCustomCleverTapID(str);
        } else if (!this.d.d()) {
            Logger m5 = m();
            m5.verbose(this.d.getAccountId() + ":async_deviceID", "Calling generateDeviceID()");
            j();
            Logger m6 = m();
            m6.verbose(this.d.getAccountId() + ":async_deviceID", "Called generateDeviceID()");
        } else {
            i();
            j();
            Logger m7 = m();
            m7.verbose(this.d.getAccountId() + ":async_deviceID", "initDeviceID() done executing!");
        }
    }

    public void setCurrentUserOptOutStateFromStorage() {
        String u = u();
        if (u == null) {
            this.d.getLogger().verbose(this.d.getAccountId(), "Unable to set current user OptOut state from storage: storage key is null");
            return;
        }
        boolean a2 = StorageHelper.a(this.e, this.d, u);
        this.k.setCurrentUserOptedOut(a2);
        Logger logger = this.d.getLogger();
        String accountId = this.d.getAccountId();
        logger.verbose(accountId, "Set current user OptOut state from storage to: " + a2 + " for key: " + u);
    }

    public void t(String str) {
        CTExecutorFactory.executors(this.d).ioTask().execute("getDeviceCachedInfo", new a());
        Task ioTask = CTExecutorFactory.executors(this.d).ioTask();
        ioTask.addOnSuccessListener(new b());
        ioTask.execute("initDeviceID", new c(str));
    }

    public String u() {
        String deviceID = getDeviceID();
        if (deviceID == null) {
            return null;
        }
        return "OptOut:" + deviceID;
    }

    public final String v(int i, String... strArr) {
        ValidationResult create = ValidationResultFactory.create(514, i, strArr);
        this.l.add(create);
        return create.getErrorDesc();
    }

    public final void w() {
        StorageHelper.remove(this.e, o());
    }

    public void x() {
        boolean a2 = StorageHelper.a(this.e, this.d, Constants.NETWORK_INFO);
        Logger logger = this.d.getLogger();
        String accountId = this.d.getAccountId();
        logger.verbose(accountId, "Setting device network info reporting state from storage to " + a2);
        this.g = a2;
    }

    public void y(String str) {
        this.i = str;
    }

    public final synchronized void z() {
        if (p() == null) {
            synchronized (this.f) {
                String str = Constants.ERROR_PROFILE_PREFIX + UUID.randomUUID().toString().replace("-", "");
                if (str.trim().length() > 2) {
                    A(str);
                } else {
                    m().verbose(this.d.getAccountId(), "Unable to generate fallback error device ID");
                }
            }
        }
    }
}
