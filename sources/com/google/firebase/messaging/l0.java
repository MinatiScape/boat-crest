package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public final class l0 {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11348a;
    public final Executor e;
    @GuardedBy("internalQueue")
    public final ArrayDeque<String> d = new ArrayDeque<>();
    @GuardedBy("internalQueue")
    public boolean f = false;
    public final String b = "topic_operation_queue";
    public final String c = com.clevertap.android.sdk.Constants.SEPARATOR_COMMA;

    public l0(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        this.f11348a = sharedPreferences;
        this.e = executor;
    }

    @WorkerThread
    public static l0 d(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        l0 l0Var = new l0(sharedPreferences, "topic_operation_queue", com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, executor);
        l0Var.e();
        return l0Var;
    }

    public boolean a(@NonNull String str) {
        boolean add;
        if (TextUtils.isEmpty(str) || str.contains(this.c)) {
            return false;
        }
        synchronized (this.d) {
            add = this.d.add(str);
            c(add);
        }
        return add;
    }

    @GuardedBy("internalQueue")
    public final boolean c(boolean z) {
        if (!z || this.f) {
            return z;
        }
        j();
        return true;
    }

    @WorkerThread
    public final void e() {
        synchronized (this.d) {
            this.d.clear();
            String string = this.f11348a.getString(this.b, "");
            if (!TextUtils.isEmpty(string) && string.contains(this.c)) {
                String[] split = string.split(this.c, -1);
                if (split.length == 0) {
                    Log.e(Constants.TAG, "Corrupted queue. Please check the queue contents and item separator provided");
                }
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.d.add(str);
                    }
                }
            }
        }
    }

    @Nullable
    public String f() {
        String peek;
        synchronized (this.d) {
            peek = this.d.peek();
        }
        return peek;
    }

    public boolean g(@Nullable Object obj) {
        boolean remove;
        synchronized (this.d) {
            remove = this.d.remove(obj);
            c(remove);
        }
        return remove;
    }

    @NonNull
    @GuardedBy("internalQueue")
    public String h() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(this.c);
        }
        return sb.toString();
    }

    @WorkerThread
    /* renamed from: i */
    public final void b() {
        synchronized (this.d) {
            this.f11348a.edit().putString(this.b, h()).commit();
        }
    }

    public final void j() {
        this.e.execute(new Runnable(this) { // from class: com.google.firebase.messaging.k0
            public final l0 h;

            {
                this.h = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.b();
            }
        });
    }
}
