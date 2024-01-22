package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public final class zzqg {
    @Nullable
    public static List<String> l;

    /* renamed from: a  reason: collision with root package name */
    public final String f8795a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final zzb f;
    public final zzqu g;
    public final Task<String> h;
    public final Map<zzod, Long> i;
    public final int j;
    public static final GmsLogger k = new GmsLogger("MlStatsLogger", "");
    public static final Component<?> zzbjt = Component.builder(zza.class).add(Dependency.required(zzqf.class)).add(Dependency.required(Context.class)).add(Dependency.required(zzqu.class)).add(Dependency.required(zzb.class)).factory(r4.f8723a).build();

    /* loaded from: classes7.dex */
    public static class zza extends zzps<Integer, zzqg> {
        public final zzqf b;
        public final Context c;
        public final zzqu d;
        public final zzb e;

        public zza(zzqf zzqfVar, Context context, zzqu zzquVar, zzb zzbVar) {
            this.b = zzqfVar;
            this.c = context;
            this.d = zzquVar;
            this.e = zzbVar;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzps
        public final /* synthetic */ zzqg create(Integer num) {
            return new zzqg(this.b, this.c, this.d, this.e, num.intValue());
        }
    }

    /* loaded from: classes7.dex */
    public interface zzb {
        void zza(zzns.zzad zzadVar);
    }

    public zzqg(zzqf zzqfVar, Context context, zzqu zzquVar, zzb zzbVar, int i) {
        String projectId;
        String gcmSenderId;
        String apiKey;
        this.i = new HashMap();
        new HashMap();
        this.j = i;
        FirebaseApp zzoh = zzqfVar.zzoh();
        String str = "";
        this.c = (zzoh == null || (projectId = zzoh.getOptions().getProjectId()) == null) ? "" : projectId;
        FirebaseApp zzoh2 = zzqfVar.zzoh();
        this.d = (zzoh2 == null || (gcmSenderId = zzoh2.getOptions().getGcmSenderId()) == null) ? "" : gcmSenderId;
        FirebaseApp zzoh3 = zzqfVar.zzoh();
        if (zzoh3 != null && (apiKey = zzoh3.getOptions().getApiKey()) != null) {
            str = apiKey;
        }
        this.e = str;
        this.f8795a = context.getPackageName();
        this.b = zzpt.zzb(context);
        this.g = zzquVar;
        this.f = zzbVar;
        this.h = zzpx.zzof().zza(q4.h);
        zzpx zzof = zzpx.zzof();
        zzquVar.getClass();
        zzof.zza(p4.a(zzquVar));
    }

    public static final /* synthetic */ zza b(ComponentContainer componentContainer) {
        return new zza((zzqf) componentContainer.get(zzqf.class), (Context) componentContainer.get(Context.class), (zzqu) componentContainer.get(zzqu.class), (zzb) componentContainer.get(zzb.class));
    }

    @NonNull
    public static synchronized List<String> d() {
        synchronized (zzqg.class) {
            List<String> list = l;
            if (list != null) {
                return list;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            l = new ArrayList(locales.size());
            for (int i = 0; i < locales.size(); i++) {
                l.add(zzpt.a(locales.get(i)));
            }
            return l;
        }
    }

    public static zzqg zza(@NonNull zzqf zzqfVar, int i) {
        Preconditions.checkNotNull(zzqfVar);
        return ((zza) zzqfVar.get(zza.class)).get(Integer.valueOf(i));
    }

    public final /* synthetic */ void a(zzns.zzad.zza zzaVar, zzod zzodVar) {
        String version;
        if (!c()) {
            k.d("MlStatsLogger", "Logging is disabled.");
            return;
        }
        String zznx = zzaVar.zzlz().zznx();
        zzns.zzbc.zza zzx = zzns.zzbc.zzny().zzbp(this.f8795a).zzbq(this.b).zzbr(this.c).zzbu(this.d).zzbv(this.e).zzbt(("NA".equals(zznx) || "".equals(zznx)) ? "NA" : "NA").zzx(d());
        if (this.h.isSuccessful()) {
            version = this.h.getResult();
        } else {
            version = zzpv.zzod().getVersion("firebase-ml-common");
        }
        zzaVar.zza(zzodVar).zza(zzx.zzbs(version));
        try {
            this.f.zza((zzns.zzad) ((zzwz) zzaVar.zzvb()));
        } catch (RuntimeException e) {
            k.e("MlStatsLogger", "Exception thrown from the logging side", e);
        }
    }

    @WorkerThread
    public final boolean c() {
        int i = this.j;
        if (i != 1) {
            if (i != 2) {
                return i == 3 || i == 4 || i == 5;
            }
            return this.g.zzoq();
        }
        return this.g.zzop();
    }

    public final void zza(@NonNull final zzns.zzad.zza zzaVar, @NonNull final zzod zzodVar) {
        zzpx.zzoe().execute(new Runnable(this, zzaVar, zzodVar) { // from class: com.google.android.gms.internal.firebase_ml.s4
            public final zzqg h;
            public final zzns.zzad.zza i;
            public final zzod j;

            {
                this.h = this;
                this.i = zzaVar;
                this.j = zzodVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.h.a(this.i, this.j);
            }
        });
    }

    @WorkerThread
    public final void zza(@NonNull zzqo zzqoVar, @NonNull zzod zzodVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = true;
        if (!c() || (this.i.get(zzodVar) != null && elapsedRealtime - this.i.get(zzodVar).longValue() <= TimeUnit.SECONDS.toMillis(30L))) {
            z = false;
        }
        if (z) {
            this.i.put(zzodVar, Long.valueOf(elapsedRealtime));
            zza(zzqoVar.zzok(), zzodVar);
        }
    }

    @WorkerThread
    public final <K> void zza(@NonNull K k2, long j, @NonNull zzod zzodVar, @NonNull zzqm<K> zzqmVar) {
        c();
    }
}
