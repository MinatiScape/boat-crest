package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.bitmap.BitmapDownloadRequest;
import com.clevertap.android.sdk.bitmap.HttpBitmapLoader;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.google.firebase.messaging.RemoteMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class Utils {
    public static boolean haveVideoPlayerSupport = a();

    public static boolean a() {
        Class<?> cls = null;
        try {
            Class.forName("com.google.android.exoplayer2.ExoPlayer");
            Class.forName("com.google.android.exoplayer2.source.hls.HlsMediaSource");
            cls = Class.forName("com.google.android.exoplayer2.ui.StyledPlayerView");
            Logger.d("ExoPlayer is present");
            return true;
        } catch (Throwable unused) {
            Logger.d("ExoPlayer library files are missing!!!");
            Logger.d("Please add ExoPlayer dependencies to render InApp or Inbox messages playing video. For more information checkout CleverTap documentation.");
            if (cls != null) {
                Logger.d("ExoPlayer classes not found " + cls.getName());
            } else {
                Logger.d("ExoPlayer classes not found");
            }
            return false;
        }
    }

    public static Bitmap b(@NonNull Drawable drawable) throws NullPointerException {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    @NonNull
    public static DownloadedBitmap c(Context context) throws NullPointerException {
        try {
            Drawable applicationLogo = context.getPackageManager().getApplicationLogo(context.getApplicationInfo());
            if (applicationLogo != null) {
                return DownloadedBitmapFactory.INSTANCE.successBitmap(b(applicationLogo), 0L);
            }
            throw new Exception("Logo is null");
        } catch (Exception e) {
            e.printStackTrace();
            return DownloadedBitmapFactory.INSTANCE.successBitmap(b(context.getPackageManager().getApplicationIcon(context.getApplicationInfo())), 0L);
        }
    }

    public static boolean containsIgnoreCase(Collection<String> collection, String str) {
        if (collection != null && str != null) {
            for (String str2 : collection) {
                if (str.equalsIgnoreCase(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static HashMap<String, Object> convertBundleObjectToHashMap(@NonNull Bundle bundle) {
        HashMap<String, Object> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                hashMap.putAll(convertBundleObjectToHashMap((Bundle) obj));
            } else {
                hashMap.put(str, bundle.get(str));
            }
        }
        return hashMap;
    }

    public static ArrayList<HashMap<String, Object>> convertJSONArrayOfJSONObjectsToArrayListOfHashMaps(JSONArray jSONArray) {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(convertJSONObjectToHashMap(jSONArray.getJSONObject(i)));
                } catch (JSONException e) {
                    Logger.v("Could not convert JSONArray of JSONObjects to ArrayList of HashMaps - " + e.getMessage());
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<String> convertJSONArrayToArrayList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e) {
                    Logger.v("Could not convert JSONArray to ArrayList - " + e.getMessage());
                }
            }
        }
        return arrayList;
    }

    public static HashMap<String, Object> convertJSONObjectToHashMap(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONObject) {
                    hashMap.putAll(convertJSONObjectToHashMap((JSONObject) obj));
                } else {
                    hashMap.put(next, jSONObject.get(next));
                }
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    public static String convertToTitleCase(String str) {
        char[] charArray;
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (char c : str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                z = true;
            } else if (z) {
                c = Character.toTitleCase(c);
                z = false;
            } else {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static Bitmap getBitmapFromURL(@NonNull String str) {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_INAPP_BITMAP, new BitmapDownloadRequest(str)).getBitmap();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] getByteArrayFromImageURL(java.lang.String r8) {
        /*
            java.lang.String r0 = "Couldn't close connection!"
            java.lang.String r1 = "///"
            java.lang.String r2 = "/"
            java.lang.String r8 = r8.replace(r1, r2)
            java.lang.String r1 = "//"
            java.lang.String r8 = r8.replace(r1, r2)
            java.lang.String r1 = "http:/"
            java.lang.String r2 = "http://"
            java.lang.String r8 = r8.replace(r1, r2)
            java.lang.String r1 = "https:/"
            java.lang.String r2 = "https://"
            java.lang.String r8 = r8.replace(r1, r2)
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            java.io.InputStream r3 = r2.getInputStream()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = new byte[r4]     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            r5.<init>()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
        L39:
            int r6 = r3.read(r4)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            r7 = -1
            if (r6 == r7) goto L45
            r7 = 0
            r5.write(r4, r7, r6)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            goto L39
        L45:
            byte[] r8 = r5.toByteArray()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L74
            r2.disconnect()     // Catch: java.lang.Throwable -> L4d
            goto L51
        L4d:
            r1 = move-exception
            com.clevertap.android.sdk.Logger.v(r0, r1)
        L51:
            return r8
        L52:
            r8 = move-exception
            goto L76
        L54:
            r2 = r1
        L55:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r3.<init>()     // Catch: java.lang.Throwable -> L74
            java.lang.String r4 = "Error processing image bytes from url: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L74
            r3.append(r8)     // Catch: java.lang.Throwable -> L74
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L74
            com.clevertap.android.sdk.Logger.v(r8)     // Catch: java.lang.Throwable -> L74
            if (r2 == 0) goto L73
            r2.disconnect()     // Catch: java.lang.Throwable -> L6f
            goto L73
        L6f:
            r8 = move-exception
            com.clevertap.android.sdk.Logger.v(r0, r8)
        L73:
            return r1
        L74:
            r8 = move-exception
            r1 = r2
        L76:
            if (r1 == 0) goto L80
            r1.disconnect()     // Catch: java.lang.Throwable -> L7c
            goto L80
        L7c:
            r1 = move-exception
            com.clevertap.android.sdk.Logger.v(r0, r1)
        L80:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.Utils.getByteArrayFromImageURL(java.lang.String):byte[]");
    }

    @SuppressLint({"MissingPermission"})
    public static String getCurrentNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "Unavailable";
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            return (networkInfo == null || !networkInfo.isConnected()) ? getDeviceNetworkType(context) : "WiFi";
        } catch (Throwable unused) {
            return "Unavailable";
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String getDeviceNetworkType(@NonNull Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "Unavailable";
        }
        int i = 0;
        if (Build.VERSION.SDK_INT >= 30) {
            if (hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                try {
                    i = telephonyManager.getDataNetworkType();
                } catch (SecurityException e) {
                    Logger.d("Security Exception caught while fetch network type" + e.getMessage());
                }
            } else {
                Logger.d("READ_PHONE_STATE permission not asked by the app or not granted by the user");
            }
        } else {
            i = telephonyManager.getNetworkType();
        }
        if (i != 20) {
            switch (i) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "Unknown";
            }
        }
        return "5G";
    }

    @NonNull
    public static DownloadedBitmap getDownloadedBitmapPostFallbackIconCheck(boolean z, Context context, @NonNull DownloadedBitmap downloadedBitmap) {
        return (downloadedBitmap.getBitmap() == null && z) ? c(context) : downloadedBitmap;
    }

    public static long getMemoryConsumption() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @NonNull
    public static DownloadedBitmap getNotificationBitmapWithTimeout(String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j) throws NullPointerException {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT, new BitmapDownloadRequest(str, z, context, cleverTapInstanceConfig, j));
    }

    @NonNull
    public static DownloadedBitmap getNotificationBitmapWithTimeoutAndSize(String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i) throws NullPointerException {
        return HttpBitmapLoader.getHttpBitmap(HttpBitmapLoader.HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT, new BitmapDownloadRequest(str, z, context, cleverTapInstanceConfig, j, i));
    }

    public static int getNow() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static long getNowInMillis() {
        return System.currentTimeMillis();
    }

    public static String getSCDomain(String str) {
        String[] split = str.split("\\.", 2);
        return split[0] + "." + Constants.AUTH + "." + split[1];
    }

    public static int getThumbnailImage(Context context, String str) {
        if (context != null) {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        }
        return -1;
    }

    public static boolean hasPermission(@NonNull Context context, @NonNull String str) {
        try {
            return ContextCompat.checkSelfPermission(context, str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isActivityDead(Activity activity) {
        boolean z = true;
        if (activity == null) {
            return true;
        }
        boolean isFinishing = activity.isFinishing();
        if (Build.VERSION.SDK_INT >= 17) {
            if (!isFinishing && !activity.isDestroyed()) {
                z = false;
            }
            return z;
        }
        return isFinishing;
    }

    public static boolean isMainProcess(Context context, String str) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid && str.equals(runningAppProcessInfo.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isRenderFallback(RemoteMessage remoteMessage, Context context) {
        return !Boolean.parseBoolean(remoteMessage.getData().get(Constants.WZRK_TSR_FB)) && Boolean.parseBoolean(remoteMessage.getData().get(Constants.NOTIFICATION_RENDER_FALLBACK));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean isServiceAvailable(@NonNull Context context, Class cls) {
        if (cls == null) {
            return false;
        }
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services) {
                if (serviceInfo.name.equals(cls.getName())) {
                    Logger.v("Service " + serviceInfo.name + " found");
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.d("Intent Service name not found exception - " + e.getLocalizedMessage());
        }
        return false;
    }

    public static void navigateToAndroidSettingsForNotifications(Context context) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
            intent.addFlags(268435456);
        } else if (i >= 21) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
        }
        context.startActivity(intent);
    }

    public static String optionalStringKey(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }
    }

    public static void setPackageNameFromResolveInfoList(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities != null) {
            String packageName = context.getPackageName();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (packageName.equals(resolveInfo.activityInfo.packageName)) {
                    intent.setPackage(packageName);
                    return;
                }
            }
        }
    }

    public static Bundle stringToBundle(String str) throws JSONException {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
        }
        return bundle;
    }

    public static boolean validateCTID(String str) {
        if (str == null) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has been set as 1 in AndroidManifest.xml but custom CleverTap ID passed is NULL.");
            return false;
        } else if (str.isEmpty()) {
            Logger.i("CLEVERTAP_USE_CUSTOM_ID has been set as 1 in AndroidManifest.xml but custom CleverTap ID passed is empty.");
            return false;
        } else if (str.length() > 64) {
            Logger.i("Custom CleverTap ID passed is greater than 64 characters. ");
            return false;
        } else if (str.matches("[=|<>;+.A-Za-z0-9()!:$@_-]*")) {
            return true;
        } else {
            Logger.i("Custom CleverTap ID cannot contain special characters apart from : =,(,),_,!,@,$,|<,>,;,+,. and - ");
            return false;
        }
    }
}
