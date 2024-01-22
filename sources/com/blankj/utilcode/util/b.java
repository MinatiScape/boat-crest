package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class b {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public String f2302a;
        public LinkedHashMap<String, String> b = new LinkedHashMap<>();
        public LinkedHashMap<String, String> c = new LinkedHashMap<>();

        public a(String str) {
            this.f2302a = str;
        }

        public void a(String str, String str2) {
            d(this.b, str, str2);
        }

        public void b(String str, String str2) {
            d(this.c, str, str2);
        }

        public void c(Map<String, String> map) {
            e(this.c, map);
        }

        public final void d(Map<String, String> map, String str, String str2) {
            int length;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            if (19 - str.length() > 0) {
                str = str + "                   ".substring(0, length);
            }
            map.put(str, str2);
        }

        public final void e(Map<String, String> map, Map<String, String> map2) {
            if (map2 == null || map2.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                d(map, entry.getKey(), entry.getValue());
            }
        }

        public String f() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.c.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append("\n");
            }
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = "************* " + this.f2302a + " Head ****************\n";
            sb.append(str);
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append("\n");
            }
            sb.append("Rom Info           : ");
            sb.append(RomUtils.getRomInfo());
            sb.append("\n");
            sb.append("Device Manufacturer: ");
            sb.append(Build.MANUFACTURER);
            sb.append("\n");
            sb.append("Device Model       : ");
            sb.append(Build.MODEL);
            sb.append("\n");
            sb.append("Android Version    : ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\n");
            sb.append("Android SDK        : ");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("\n");
            sb.append("App VersionName    : ");
            sb.append(AppUtils.getAppVersionName());
            sb.append("\n");
            sb.append("App VersionCode    : ");
            sb.append(AppUtils.getAppVersionCode());
            sb.append("\n");
            sb.append(f());
            sb.append(str);
            sb.append("\n");
            return sb.toString();
        }
    }

    public static boolean A(CharSequence charSequence, CharSequence charSequence2) {
        return StringUtils.equals(charSequence, charSequence2);
    }

    public static boolean A0() {
        return RomUtils.isSamsung();
    }

    public static ShellUtils.CommandResult B(String str, boolean z) {
        return ShellUtils.execCmd(str, z);
    }

    public static boolean B0(String str) {
        return ServiceUtils.isServiceRunning(str);
    }

    public static Uri C(File file) {
        return UriUtils.file2Uri(file);
    }

    public static boolean C0(String str) {
        return StringUtils.isSpace(str);
    }

    public static void D() {
        ActivityUtils.finishAllActivities();
    }

    public static boolean D0(@NonNull View view, long j) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return DebouncingUtils.isValid(view, j);
    }

    public static void E(Activity activity) {
        KeyboardUtils.fixSoftInputLeaks(activity);
    }

    public static byte[] E0(JSONArray jSONArray) {
        return ConvertUtils.jsonArray2Bytes(jSONArray);
    }

    public static String F(@Nullable String str, Object... objArr) {
        return StringUtils.format(str, objArr);
    }

    public static byte[] F0(JSONObject jSONObject) {
        return ConvertUtils.jsonObject2Bytes(jSONObject);
    }

    public static String G(String str) {
        return JsonUtils.formatJson(str);
    }

    public static View G0(@LayoutRes int i) {
        return ViewUtils.layoutId2View(i);
    }

    public static <T> T H(String str, Type type) {
        return (T) GsonUtils.fromJson(str, type);
    }

    public static String H0(long j, int i) {
        return TimeUtils.c(j, i);
    }

    public static Activity I(Context context) {
        return ActivityUtils.getActivityByContext(context);
    }

    public static void I0(File file) {
        FileUtils.notifySystemToScan(file);
    }

    public static List<Activity> J() {
        return com.blankj.utilcode.util.a.n.k();
    }

    public static byte[] J0(Parcelable parcelable) {
        return ConvertUtils.parcelable2Bytes(parcelable);
    }

    public static int K() {
        return ScreenUtils.getAppScreenWidth();
    }

    public static void K0() {
        L0(AdaptScreenUtils.g());
    }

    public static Application L() {
        return com.blankj.utilcode.util.a.n.o();
    }

    public static void L0(Runnable... runnableArr) {
        for (Runnable runnable : runnableArr) {
            ThreadUtils.getCachedPool().execute(runnable);
        }
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static Intent M(String str) {
        return IntentUtils.getCallIntent(str);
    }

    public static int M0(float f) {
        return SizeUtils.px2dp(f);
    }

    public static String N() {
        return ProcessUtils.getCurrentProcessName();
    }

    public static int N0(float f) {
        return SizeUtils.px2sp(f);
    }

    public static Intent O(String str) {
        return IntentUtils.getDialIntent(str);
    }

    public static byte[] O0(File file) {
        return FileIOUtils.readFile2BytesByChannel(file);
    }

    public static File P(String str) {
        return FileUtils.getFileByPath(str);
    }

    public static void P0() {
        AppUtils.relaunchApp();
    }

    public static String Q() {
        return ProcessUtils.getForegroundProcessName();
    }

    public static void Q0(Activity activity) {
        com.blankj.utilcode.util.a.n.u(activity);
    }

    public static long R(String str) {
        return FileUtils.getFsAvailableSize(str);
    }

    public static void R0(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        com.blankj.utilcode.util.a.n.v(activity, activityLifecycleCallbacks);
    }

    public static long S(String str) {
        return FileUtils.getFsTotalSize(str);
    }

    public static void S0(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        com.blankj.utilcode.util.a.n.w(activityLifecycleCallbacks);
    }

    public static String T(Throwable th) {
        return ThrowableUtils.getFullStackTrace(th);
    }

    public static void T0(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        com.blankj.utilcode.util.a.n.y(onAppStatusChangedListener);
    }

    public static Gson U() {
        return GsonUtils.b();
    }

    public static void U0(Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    public static Intent V(Uri uri) {
        return IntentUtils.getInstallAppIntent(uri);
    }

    public static void V0(Runnable runnable, long j) {
        ThreadUtils.runOnUiThreadDelayed(runnable, j);
    }

    public static Intent W(File file) {
        return IntentUtils.getInstallAppIntent(file);
    }

    public static byte[] W0(Serializable serializable) {
        return ConvertUtils.serializable2Bytes(serializable);
    }

    public static Intent X(String str, boolean z) {
        return IntentUtils.getLaunchAppDetailsSettingsIntent(str, z);
    }

    public static int X0(float f) {
        return SizeUtils.sp2px(f);
    }

    public static Intent Y(String str) {
        return IntentUtils.getLaunchAppIntent(str);
    }

    public static void Y0() {
        ActivityUtils.startHomeActivity();
    }

    public static String Z(String str) {
        return ActivityUtils.getLauncherActivity(str);
    }

    public static byte[] Z0(String str) {
        return ConvertUtils.string2Bytes(str);
    }

    public static void a(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        com.blankj.utilcode.util.a.n.d(activity, activityLifecycleCallbacks);
    }

    public static int a0() {
        return BarUtils.getNavBarHeight();
    }

    public static String a1(Object obj) {
        return GsonUtils.toJson(obj);
    }

    public static void b(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        com.blankj.utilcode.util.a.n.e(activityLifecycleCallbacks);
    }

    public static Notification b0(NotificationUtils.ChannelConfig channelConfig, Utils.Consumer<NotificationCompat.Builder> consumer) {
        return NotificationUtils.getNotification(channelConfig, consumer);
    }

    public static void b1() {
        ToastUtils.cancel();
    }

    public static void c(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        com.blankj.utilcode.util.a.n.g(onAppStatusChangedListener);
    }

    public static Intent c0(String str, String str2) {
        return IntentUtils.getSendSmsIntent(str, str2);
    }

    public static void c1(CharSequence charSequence) {
        ToastUtils.showShort(charSequence);
    }

    public static byte[] d(byte[] bArr) {
        return EncodeUtils.base64Decode(bArr);
    }

    public static SPUtils d0() {
        return SPUtils.getInstance("Utils");
    }

    public static void d1(Application application) {
        com.blankj.utilcode.util.a.n.B(application);
    }

    public static byte[] e(byte[] bArr) {
        return EncodeUtils.base64Encode(bArr);
    }

    public static int e0() {
        return BarUtils.getStatusBarHeight();
    }

    public static File e1(Uri uri) {
        return UriUtils.uri2File(uri);
    }

    public static byte[] f(Bitmap bitmap) {
        return ImageUtils.bitmap2Bytes(bitmap);
    }

    public static String f0(@StringRes int i) {
        return StringUtils.getString(i);
    }

    public static Bitmap f1(View view) {
        return ImageUtils.view2Bitmap(view);
    }

    public static byte[] g(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        return ImageUtils.bitmap2Bytes(bitmap, compressFormat, i);
    }

    public static String g0(@StringRes int i, Object... objArr) {
        return StringUtils.getString(i, objArr);
    }

    public static boolean g1(File file, byte[] bArr) {
        return FileIOUtils.writeFileFromBytesByChannel(file, bArr, true);
    }

    public static Drawable h(Bitmap bitmap) {
        return ImageUtils.bitmap2Drawable(bitmap);
    }

    public static Activity h0() {
        return com.blankj.utilcode.util.a.n.p();
    }

    public static boolean h1(String str, InputStream inputStream) {
        return FileIOUtils.writeFileFromIS(str, inputStream);
    }

    public static String i(long j) {
        return ConvertUtils.byte2FitMemorySize(j);
    }

    public static Intent i0(String str) {
        return IntentUtils.getUninstallAppIntent(str);
    }

    public static boolean i1(String str, String str2, boolean z) {
        return FileIOUtils.writeFileFromString(str, str2, z);
    }

    public static Bitmap j(byte[] bArr) {
        return ImageUtils.bytes2Bitmap(bArr);
    }

    public static byte[] j0(byte[] bArr, String str) {
        return EncryptUtils.a(bArr, str);
    }

    public static Drawable k(byte[] bArr) {
        return ImageUtils.bytes2Drawable(bArr);
    }

    public static byte[] k0(String str) {
        return ConvertUtils.hexString2Bytes(str);
    }

    public static String l(byte[] bArr) {
        return ConvertUtils.bytes2HexString(bArr);
    }

    public static void l0(Application application) {
        com.blankj.utilcode.util.a.n.q(application);
    }

    public static JSONArray m(byte[] bArr) {
        return ConvertUtils.bytes2JSONArray(bArr);
    }

    public static byte[] m0(InputStream inputStream) {
        return ConvertUtils.inputStream2Bytes(inputStream);
    }

    public static JSONObject n(byte[] bArr) {
        return ConvertUtils.bytes2JSONObject(bArr);
    }

    public static List<String> n0(InputStream inputStream, String str) {
        return ConvertUtils.inputStream2Lines(inputStream, str);
    }

    public static Object o(byte[] bArr) {
        return ConvertUtils.bytes2Object(bArr);
    }

    public static boolean o0(Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }

    public static <T> T p(byte[] bArr, Parcelable.Creator<T> creator) {
        return (T) ConvertUtils.bytes2Parcelable(bArr, creator);
    }

    public static boolean p0() {
        return AppUtils.isAppDebug();
    }

    public static String q(byte[] bArr) {
        return ConvertUtils.bytes2String(bArr);
    }

    public static boolean q0() {
        return com.blankj.utilcode.util.a.n.r();
    }

    public static boolean r(File file) {
        return FileUtils.createFileByDeleteOldFile(file);
    }

    public static boolean r0(String str) {
        return AppUtils.isAppInstalled(str);
    }

    public static boolean s(File file) {
        return FileUtils.createOrExistsDir(file);
    }

    public static boolean s0(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return AppUtils.isAppRunning(str);
    }

    public static boolean t(File file) {
        return FileUtils.createOrExistsFile(file);
    }

    public static boolean t0(File file) {
        return FileUtils.isFileExists(file);
    }

    public static boolean u(File file) {
        return FileUtils.deleteAllInDir(file);
    }

    public static boolean u0(String... strArr) {
        return PermissionUtils.isGranted(strArr);
    }

    public static <T> Utils.Task<T> v(Utils.Task<T> task) {
        ThreadUtils.getCachedPool().execute(task);
        return task;
    }

    @RequiresApi(api = 23)
    public static boolean v0() {
        return PermissionUtils.isGrantedDrawOverlays();
    }

    public static int w(float f) {
        return SizeUtils.dp2px(f);
    }

    public static boolean w0(Intent intent) {
        return IntentUtils.isIntentAvailable(intent);
    }

    public static Bitmap x(Drawable drawable) {
        return ImageUtils.drawable2Bitmap(drawable);
    }

    public static boolean x0() {
        return ViewUtils.isLayoutRtl();
    }

    public static byte[] y(Drawable drawable) {
        return ImageUtils.drawable2Bytes(drawable);
    }

    public static boolean y0() {
        return ProcessUtils.isMainProcess();
    }

    public static byte[] z(Drawable drawable, Bitmap.CompressFormat compressFormat, int i) {
        return ImageUtils.drawable2Bytes(drawable, compressFormat, i);
    }

    public static boolean z0() {
        return SDCardUtils.isSDCardEnableByEnvironment();
    }
}
