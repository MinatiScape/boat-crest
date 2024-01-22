package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.clearcut.zzaa;
import com.google.android.gms.internal.clearcut.zzge;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzj;
import com.google.android.gms.internal.clearcut.zzp;
import com.google.android.gms.internal.clearcut.zzr;
import java.util.ArrayList;
import java.util.TimeZone;
import javax.annotation.Nullable;
@KeepForSdk
/* loaded from: classes6.dex */
public final class ClearcutLogger {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zzj> m;
    public static final Api.AbstractClientBuilder<zzj, Api.ApiOptions.NoOptions> n;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8220a;
    public final String b;
    public final int c;
    public String d;
    public int e;
    public String f;
    public final boolean g;
    public zzge.zzv.zzb h;
    public final com.google.android.gms.clearcut.zzb i;
    public final Clock j;
    public zzc k;
    public final zza l;

    /* loaded from: classes6.dex */
    public class LogEventBuilder {

        /* renamed from: a  reason: collision with root package name */
        public int f8221a;
        public String b;
        public String c;
        public String d;
        public zzge.zzv.zzb e;
        public boolean f;
        public final zzha g;
        public boolean h;

        public LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr) {
            this(bArr, (zzb) null);
        }

        public LogEventBuilder(byte[] bArr, zzb zzbVar) {
            this.f8221a = ClearcutLogger.this.e;
            this.b = ClearcutLogger.this.d;
            this.c = ClearcutLogger.this.f;
            this.d = null;
            this.e = ClearcutLogger.this.h;
            this.f = true;
            zzha zzhaVar = new zzha();
            this.g = zzhaVar;
            this.h = false;
            this.c = ClearcutLogger.this.f;
            this.d = null;
            zzhaVar.zzbkc = zzaa.zze(ClearcutLogger.this.f8220a);
            zzhaVar.zzbjf = ClearcutLogger.this.j.currentTimeMillis();
            zzhaVar.zzbjg = ClearcutLogger.this.j.elapsedRealtime();
            zzc unused = ClearcutLogger.this.k;
            zzhaVar.zzbju = TimeZone.getDefault().getOffset(zzhaVar.zzbjf) / 1000;
            if (bArr != null) {
                zzhaVar.zzbjp = bArr;
            }
        }

        public /* synthetic */ LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr, a aVar) {
            this(clearcutLogger, bArr);
        }

        @KeepForSdk
        public void log() {
            if (this.h) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.h = true;
            zze zzeVar = new zze(new zzr(ClearcutLogger.this.b, ClearcutLogger.this.c, this.f8221a, this.b, this.c, this.d, ClearcutLogger.this.g, this.e), this.g, null, null, ClearcutLogger.e(null), null, ClearcutLogger.e(null), null, null, this.f);
            if (ClearcutLogger.this.l.zza(zzeVar)) {
                ClearcutLogger.this.i.zzb(zzeVar);
            } else {
                PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, (GoogleApiClient) null);
            }
        }

        @KeepForSdk
        public LogEventBuilder setEventCode(int i) {
            this.g.zzbji = i;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public interface zza {
        boolean zza(zze zzeVar);
    }

    /* loaded from: classes6.dex */
    public interface zzb {
        byte[] zza();
    }

    /* loaded from: classes6.dex */
    public static class zzc {
    }

    static {
        Api.ClientKey<zzj> clientKey = new Api.ClientKey<>();
        m = clientKey;
        a aVar = new a();
        n = aVar;
        API = new Api<>("ClearcutLogger.API", aVar, clientKey);
    }

    @VisibleForTesting
    public ClearcutLogger(Context context, int i, String str, String str2, String str3, boolean z, com.google.android.gms.clearcut.zzb zzbVar, Clock clock, zzc zzcVar, zza zzaVar) {
        this.e = -1;
        zzge.zzv.zzb zzbVar2 = zzge.zzv.zzb.DEFAULT;
        this.h = zzbVar2;
        this.f8220a = context;
        this.b = context.getPackageName();
        this.c = a(context);
        this.e = -1;
        this.d = str;
        this.f = str2;
        this.g = z;
        this.i = zzbVar;
        this.j = clock;
        this.k = new zzc();
        this.h = zzbVar2;
        this.l = zzaVar;
        if (z) {
            Preconditions.checkArgument(str2 == null, "can't be anonymous with an upload account");
        }
    }

    @KeepForSdk
    public ClearcutLogger(Context context, String str, @Nullable String str2) {
        this(context, -1, str, str2, null, false, com.google.android.gms.internal.clearcut.zze.zzb(context), DefaultClock.getInstance(), null, new zzp(context));
    }

    public static int a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.", e);
            return 0;
        }
    }

    @KeepForSdk
    public static ClearcutLogger anonymousLogger(Context context, String str) {
        return new ClearcutLogger(context, -1, str, null, null, true, com.google.android.gms.internal.clearcut.zze.zzb(context), DefaultClock.getInstance(), null, new zzp(context));
    }

    public static int[] c(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Integer num = arrayList.get(i);
            i++;
            iArr[i2] = num.intValue();
            i2++;
        }
        return iArr;
    }

    public static /* synthetic */ int[] e(ArrayList arrayList) {
        return c(null);
    }

    @KeepForSdk
    public final LogEventBuilder newEvent(@Nullable byte[] bArr) {
        return new LogEventBuilder(this, bArr, (a) null);
    }
}
