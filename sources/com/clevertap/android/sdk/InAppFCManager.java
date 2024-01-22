package com.clevertap.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.mappls.sdk.services.api.nearby.NearbyCriteria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class InAppFCManager {
    public final CleverTapInstanceConfig b;
    public final Context c;
    public String d;

    /* renamed from: a  reason: collision with root package name */
    public final SimpleDateFormat f2579a = new SimpleDateFormat("ddMMyyyy", Locale.US);
    public final ArrayList<String> e = new ArrayList<>();
    public final HashMap<String, Integer> f = new HashMap<>();
    public int g = 0;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppFCManager inAppFCManager = InAppFCManager.this;
            inAppFCManager.n(inAppFCManager.d);
            return null;
        }
    }

    public InAppFCManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        this.b = cleverTapInstanceConfig;
        this.c = context;
        this.d = str;
        CTExecutorFactory.executors(cleverTapInstanceConfig).postAsyncSafelyTask().execute("initInAppFCManager", new a());
    }

    public void attachToHeader(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(NearbyCriteria.IMPORTANCE, g(h(Constants.KEY_COUNTS_SHOWN_TODAY, this.d), 0));
            JSONArray jSONArray = new JSONArray();
            Map<String, ?> all = StorageHelper.getPreferences(context, h(Constants.KEY_COUNTS_PER_INAPP, this.d)).getAll();
            for (String str : all.keySet()) {
                Object obj = all.get(str);
                if (obj instanceof String) {
                    String[] split = ((String) obj).split(Constants.SEPARATOR_COMMA);
                    if (split.length == 2) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(0, str);
                        jSONArray2.put(1, Integer.parseInt(split[0]));
                        jSONArray2.put(2, Integer.parseInt(split[1]));
                        jSONArray.put(jSONArray2);
                    }
                }
            }
            jSONObject.put(Constants.KEY_TLC, jSONArray);
        } catch (Throwable th) {
            Logger.v("Failed to attach FC to header", th);
        }
    }

    public final String c() {
        return this.b.getAccountId();
    }

    public boolean canShow(CTInAppNotification cTInAppNotification) {
        if (cTInAppNotification == null) {
            return false;
        }
        if (f(cTInAppNotification) == null || cTInAppNotification.isExcludeFromCaps()) {
            return true;
        }
        if (!l(cTInAppNotification) && !k(cTInAppNotification)) {
            if (!j(cTInAppNotification)) {
                return true;
            }
        }
        return false;
    }

    public void changeUser(String str) {
        this.f.clear();
        this.g = 0;
        this.e.clear();
        this.d = str;
        n(str);
    }

    public final Logger d() {
        return this.b.getLogger();
    }

    public void didDismiss(CTInAppNotification cTInAppNotification) {
        String id = cTInAppNotification.getId();
        if (id != null) {
            this.e.add(id.toString());
        }
    }

    public void didShow(Context context, CTInAppNotification cTInAppNotification) {
        String f = f(cTInAppNotification);
        if (f == null) {
            return;
        }
        this.g++;
        Integer num = this.f.get(f);
        if (num == null) {
            num = 1;
        }
        this.f.put(f, Integer.valueOf(num.intValue() + 1));
        m(f);
        StorageHelper.putInt(context, p(h(Constants.KEY_COUNTS_SHOWN_TODAY, this.d)), g(h(Constants.KEY_COUNTS_SHOWN_TODAY, this.d), 0) + 1);
    }

    public final int[] e(String str) {
        String string = StorageHelper.getPreferences(this.c, h(Constants.KEY_COUNTS_PER_INAPP, this.d)).getString(str, null);
        if (string == null) {
            return new int[]{0, 0};
        }
        try {
            String[] split = string.split(Constants.SEPARATOR_COMMA);
            return split.length != 2 ? new int[]{0, 0} : new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
        } catch (Throwable unused) {
            return new int[]{0, 0};
        }
    }

    public final String f(CTInAppNotification cTInAppNotification) {
        if (cTInAppNotification.getId() != null && !cTInAppNotification.getId().isEmpty()) {
            try {
                return cTInAppNotification.getId();
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public final int g(String str, int i) {
        if (this.b.isDefaultInstance()) {
            int i2 = StorageHelper.getInt(this.c, p(str), -1000);
            return i2 != -1000 ? i2 : StorageHelper.getInt(this.c, str, i);
        }
        return StorageHelper.getInt(this.c, p(str), i);
    }

    public final String h(String str, String str2) {
        return str + ":" + str2;
    }

    public final String i(String str, String str2) {
        if (this.b.isDefaultInstance()) {
            String string = StorageHelper.getString(this.c, p(str), str2);
            return string != null ? string : StorageHelper.getString(this.c, str, str2);
        }
        return StorageHelper.getString(this.c, p(str), str2);
    }

    public final boolean j(CTInAppNotification cTInAppNotification) {
        String f = f(cTInAppNotification);
        if (f == null) {
            return false;
        }
        if (g(h(Constants.KEY_COUNTS_SHOWN_TODAY, this.d), 0) >= g(h(Constants.KEY_MAX_PER_DAY, this.d), 1)) {
            return true;
        }
        try {
            int totalDailyCount = cTInAppNotification.getTotalDailyCount();
            if (totalDailyCount == -1) {
                return false;
            }
            return e(f)[0] >= totalDailyCount;
        } catch (Throwable unused) {
            return true;
        }
    }

    public final boolean k(CTInAppNotification cTInAppNotification) {
        String f = f(cTInAppNotification);
        if (f == null || cTInAppNotification.getTotalLifetimeCount() == -1) {
            return false;
        }
        try {
            return e(f)[1] >= cTInAppNotification.getTotalLifetimeCount();
        } catch (Exception unused) {
            return true;
        }
    }

    public final boolean l(CTInAppNotification cTInAppNotification) {
        String f = f(cTInAppNotification);
        if (f == null) {
            return false;
        }
        if (this.e.contains(f)) {
            return true;
        }
        try {
            int maxPerSession = cTInAppNotification.getMaxPerSession() >= 0 ? cTInAppNotification.getMaxPerSession() : 1000;
            Integer num = this.f.get(f);
            if (num != null) {
                if (num.intValue() >= maxPerSession) {
                    return true;
                }
            }
            return this.g >= g(h(Constants.INAPP_MAX_PER_SESSION, this.d), 1);
        } catch (Throwable unused) {
            return true;
        }
    }

    public final void m(String str) {
        int[] e = e(str);
        e[0] = e[0] + 1;
        e[1] = e[1] + 1;
        SharedPreferences.Editor edit = StorageHelper.getPreferences(this.c, h(Constants.KEY_COUNTS_PER_INAPP, this.d)).edit();
        edit.putString(str, e[0] + Constants.SEPARATOR_COMMA + e[1]);
        StorageHelper.persist(edit);
    }

    public final void n(String str) {
        Logger d = d();
        d.verbose(this.b.getAccountId() + ":async_deviceID", "InAppFCManager init() called");
        try {
            o(str);
            String format = this.f2579a.format(new Date());
            if (format.equals(i(h("ict_date", str), "20140428"))) {
                return;
            }
            StorageHelper.putString(this.c, p(h("ict_date", str)), format);
            StorageHelper.putInt(this.c, p(h(Constants.KEY_COUNTS_SHOWN_TODAY, str)), 0);
            SharedPreferences preferences = StorageHelper.getPreferences(this.c, h(Constants.KEY_COUNTS_PER_INAPP, str));
            SharedPreferences.Editor edit = preferences.edit();
            Map<String, ?> all = preferences.getAll();
            for (String str2 : all.keySet()) {
                Object obj = all.get(str2);
                if (!(obj instanceof String)) {
                    edit.remove(str2);
                } else {
                    String[] split = ((String) obj).split(Constants.SEPARATOR_COMMA);
                    if (split.length != 2) {
                        edit.remove(str2);
                    } else {
                        edit.putString(str2, "0," + split[1]);
                    }
                }
            }
            StorageHelper.persist(edit);
        } catch (Exception e) {
            Logger d2 = d();
            String c = c();
            d2.verbose(c, "Failed to init inapp manager " + e.getLocalizedMessage());
        }
    }

    public final void o(String str) {
        if (i(p(h("ict_date", str)), null) != null || i("ict_date", null) == null) {
            return;
        }
        Logger.v("Migrating InAppFC Prefs");
        StorageHelper.putString(this.c, p(h("ict_date", str)), i("ict_date", "20140428"));
        StorageHelper.putInt(this.c, p(h(Constants.KEY_COUNTS_SHOWN_TODAY, str)), g(p(Constants.KEY_COUNTS_SHOWN_TODAY), 0));
        SharedPreferences preferences = StorageHelper.getPreferences(this.c, Constants.KEY_COUNTS_PER_INAPP);
        SharedPreferences.Editor edit = preferences.edit();
        SharedPreferences.Editor edit2 = StorageHelper.getPreferences(this.c, h(Constants.KEY_COUNTS_PER_INAPP, str)).edit();
        Map<String, ?> all = preferences.getAll();
        for (String str2 : all.keySet()) {
            Object obj = all.get(str2);
            if (!(obj instanceof String)) {
                edit.remove(str2);
            } else if (((String) obj).split(Constants.SEPARATOR_COMMA).length != 2) {
                edit.remove(str2);
            } else {
                edit2.putString(str2, obj.toString());
            }
        }
        StorageHelper.persist(edit2);
        edit.clear().apply();
    }

    public final String p(String str) {
        return str + ":" + c();
    }

    public void processResponse(Context context, JSONObject jSONObject) {
        try {
            if (jSONObject.has("inapp_stale")) {
                JSONArray jSONArray = jSONObject.getJSONArray("inapp_stale");
                SharedPreferences.Editor edit = StorageHelper.getPreferences(context, h(Constants.KEY_COUNTS_PER_INAPP, this.d)).edit();
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object obj = jSONArray.get(i);
                    if (obj instanceof Integer) {
                        edit.remove("" + obj);
                        Logger.d("Purged stale in-app - " + obj);
                    } else if (obj instanceof String) {
                        edit.remove((String) obj);
                        Logger.d("Purged stale in-app - " + obj);
                    }
                }
                StorageHelper.persist(edit);
            }
        } catch (Throwable th) {
            Logger.v("Failed to purge out stale targets", th);
        }
    }

    public synchronized void updateLimits(Context context, int i, int i2) {
        StorageHelper.putInt(context, p(h(Constants.KEY_MAX_PER_DAY, this.d)), i);
        StorageHelper.putInt(context, p(h(Constants.INAPP_MAX_PER_SESSION, this.d)), i2);
    }
}
