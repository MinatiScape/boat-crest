package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public abstract class zzn<T> {
    public static String j = "com.google.android.gms.vision.dynamite";

    /* renamed from: a  reason: collision with root package name */
    public final Context f10029a;
    public final String c;
    public final String d;
    public final String e;
    public final boolean f;
    @GuardedBy("lock")
    public T i;
    public final Object b = new Object();
    public boolean g = false;
    public boolean h = false;

    public zzn(Context context, String str, String str2) {
        boolean z = false;
        this.f10029a = context;
        this.c = str;
        String str3 = j;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 1 + String.valueOf(str2).length());
        sb.append(str3);
        sb.append(".");
        sb.append(str2);
        this.d = sb.toString();
        this.e = str2;
        if (context != null) {
            zzbe.maybeInit(context);
            Boolean valueOf = Boolean.valueOf(zzkv.zzjp());
            Boolean bool = Boolean.TRUE;
            zzdg zza = zzdg.zza(OptionalModuleUtils.BARCODE, valueOf, OptionalModuleUtils.FACE, bool, OptionalModuleUtils.ICA, Boolean.valueOf(zzkv.zzjq()), OptionalModuleUtils.OCR, bool);
            if (zza.containsKey(str2) && ((Boolean) zza.get(str2)).booleanValue()) {
                z = true;
            }
        }
        this.f = z;
    }

    public final boolean isOperational() {
        return zzp() != null;
    }

    public abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException;

    public abstract void zzn() throws RemoteException;

    public final void zzo() {
        synchronized (this.b) {
            if (this.i == null) {
                return;
            }
            try {
                zzn();
            } catch (RemoteException e) {
                Log.e(this.c, "Could not finalize native handle", e);
            }
        }
    }

    public final T zzp() {
        DynamiteModule zza;
        synchronized (this.b) {
            T t = this.i;
            if (t != null) {
                return t;
            }
            try {
                zza = DynamiteModule.load(this.f10029a, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.d);
            } catch (DynamiteModule.LoadingException unused) {
                Log.d(this.c, "Cannot load feature, fall back to load dynamite module.");
                zza = zzr.zza(this.f10029a, this.e, this.f);
                if (zza == null && this.f && !this.g) {
                    String str = this.c;
                    String valueOf = String.valueOf(this.e);
                    Log.d(str, valueOf.length() != 0 ? "Broadcasting download intent for dependency ".concat(valueOf) : new String("Broadcasting download intent for dependency "));
                    String str2 = this.e;
                    Intent intent = new Intent();
                    intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                    intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", str2);
                    intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                    this.f10029a.sendBroadcast(intent);
                    this.g = true;
                }
            }
            if (zza != null) {
                try {
                    this.i = zza(zza, this.f10029a);
                } catch (RemoteException | DynamiteModule.LoadingException e) {
                    Log.e(this.c, "Error creating remote native handle", e);
                }
            }
            boolean z = this.h;
            if (!z && this.i == null) {
                Log.w(this.c, "Native handle not yet available. Reverting to no-op handle.");
                this.h = true;
            } else if (z && this.i != null) {
                Log.w(this.c, "Native handle is now available.");
            }
            return this.i;
        }
    }
}
