package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class h {
    public static h c;
    public static final SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy z");

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11282a;
    public final SharedPreferences b;

    public h(Context context) {
        this.f11282a = context.getSharedPreferences("FirebaseAppHeartBeat", 0);
        this.b = context.getSharedPreferences("FirebaseAppHeartBeatStorage", 0);
    }

    public static synchronized h c(Context context) {
        h hVar;
        synchronized (h.class) {
            if (c == null) {
                c = new h(context);
            }
            hVar = c;
        }
        return hVar;
    }

    public static boolean f(long j, long j2) {
        Date date = new Date(j);
        Date date2 = new Date(j2);
        SimpleDateFormat simpleDateFormat = d;
        return !simpleDateFormat.format(date).equals(simpleDateFormat.format(date2));
    }

    public final synchronized void a() {
        long j = this.f11282a.getLong("fire-count", 0L);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : this.b.getAll().entrySet()) {
            arrayList.add(Long.valueOf(Long.parseLong(entry.getKey())));
        }
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.b.edit().remove(String.valueOf((Long) it.next())).apply();
            j--;
            this.f11282a.edit().putLong("fire-count", j).apply();
            if (j <= 100) {
                return;
            }
        }
    }

    public synchronized void b() {
        this.b.edit().clear().apply();
        this.f11282a.edit().remove("fire-count").apply();
    }

    public synchronized long d() {
        return this.f11282a.getLong("fire-global", -1L);
    }

    public synchronized List<SdkHeartBeatResult> e(boolean z) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : this.b.getAll().entrySet()) {
            arrayList.add(SdkHeartBeatResult.create((String) entry.getValue(), Long.parseLong(entry.getKey())));
        }
        Collections.sort(arrayList);
        if (z) {
            b();
        }
        return arrayList;
    }

    public synchronized boolean g(long j) {
        return h("fire-global", j);
    }

    public synchronized boolean h(String str, long j) {
        if (this.f11282a.contains(str)) {
            if (f(this.f11282a.getLong(str, -1L), j)) {
                this.f11282a.edit().putLong(str, j).apply();
                return true;
            }
            return false;
        }
        this.f11282a.edit().putLong(str, j).apply();
        return true;
    }

    public synchronized void i(String str, long j) {
        long j2 = this.f11282a.getLong("fire-count", 0L);
        this.b.edit().putString(String.valueOf(j), str).apply();
        long j3 = j2 + 1;
        this.f11282a.edit().putLong("fire-count", j3).apply();
        if (j3 > 200) {
            a();
        }
    }

    public synchronized void j(long j) {
        this.f11282a.edit().putLong("fire-global", j).apply();
    }
}
