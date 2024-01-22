package com.mappls.sdk.traffic;

import android.location.Location;
import android.os.Handler;
import androidx.work.PeriodicWorkRequest;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.traffic.db.BeaconDatabase;
import com.mappls.sdk.traffic.db.ProbeLocation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class a {
    @Nullable
    public static a h;
    @Nullable
    public String b;
    @Nullable
    public NavigationApplication c;
    public long d;
    public long e;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f13311a = new ArrayList();
    @NotNull
    public final Handler f = new Handler();
    @NotNull
    public final Runnable g = new Runnable() { // from class: com.mappls.sdk.traffic.b
        @Override // java.lang.Runnable
        public final void run() {
            a.g(a.this);
        }
    };

    @DebugMetadata(c = "com.mappls.sdk.traffic.LocationStorage$addLocation$1$1", f = "LocationStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.mappls.sdk.traffic.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static final class C0707a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.mappls.sdk.traffic.db.dao.a f13312a;
        public final /* synthetic */ List<ProbeLocation> b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0707a(com.mappls.sdk.traffic.db.dao.a aVar, List<ProbeLocation> list, Continuation<? super C0707a> continuation) {
            super(2, continuation);
            this.f13312a = aVar;
            this.b = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new C0707a(this.f13312a, this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C0707a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            ((com.mappls.sdk.traffic.db.dao.b) this.f13312a).a(this.b);
            return Unit.INSTANCE;
        }
    }

    public static final void a(a aVar, ArrayList arrayList) {
        BeaconDatabase e;
        com.mappls.sdk.traffic.db.dao.a a2;
        NavigationApplication navigationApplication = aVar.c;
        if (navigationApplication == null || (e = navigationApplication.e()) == null || (a2 = e.a()) == null) {
            return;
        }
        ((com.mappls.sdk.traffic.db.dao.b) a2).a(arrayList);
    }

    public static final void b(a aVar) {
        aVar.f.removeCallbacksAndMessages(null);
        aVar.f.postDelayed(aVar.g, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
    }

    public static final void c(a aVar) {
        aVar.f.removeCallbacksAndMessages(null);
        aVar.f.postDelayed(aVar.g, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
    }

    public static final void d(a aVar) {
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new c(aVar, null), 2, null);
    }

    public static final void g(a this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getClass();
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new c(this$0, null), 2, null);
    }

    public final void a(@Nullable NavigationApplication navigationApplication) {
        this.c = navigationApplication;
        this.b = "odlsdnhdgtropfns78bDSQLP";
    }

    @Nullable
    public final String c() {
        return this.b;
    }

    @Nullable
    public static final a b() {
        if (h == null) {
            h = new a();
        }
        return h;
    }

    public final void a(@NotNull Location pLocation) {
        BeaconDatabase e;
        com.mappls.sdk.traffic.db.dao.a a2;
        Intrinsics.checkNotNullParameter(pLocation, "pLocation");
        if (MapplsNavigationHelper.getInstance().isTrafficProbeEnabled()) {
            this.f13311a.add(pLocation);
            if (System.currentTimeMillis() - this.e >= 120000 || this.f13311a.size() >= 20) {
                ArrayList arrayList = new ArrayList(this.f13311a.size());
                Iterator it = this.f13311a.iterator();
                while (it.hasNext()) {
                    arrayList.add(new ProbeLocation((Location) it.next()));
                }
                NavigationApplication navigationApplication = this.c;
                if (navigationApplication == null || (e = navigationApplication.e()) == null || (a2 = e.a()) == null) {
                    return;
                }
                GlobalScope globalScope = GlobalScope.INSTANCE;
                e.e(globalScope, Dispatchers.getIO(), null, new C0707a(a2, arrayList, null), 2, null);
                this.f13311a.clear();
                this.e = System.currentTimeMillis();
                if (System.currentTimeMillis() - this.d >= PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                    e.e(globalScope, Dispatchers.getIO(), null, new c(this, null), 2, null);
                }
            }
        }
    }
}
