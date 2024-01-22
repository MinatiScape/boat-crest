package com.mappls.sdk.traffic;

import android.provider.Settings;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.traffic.api.MapplsBeacon;
import com.mappls.sdk.traffic.db.BeaconDatabase;
import com.mappls.sdk.traffic.model.BeaconPacket;
import com.mappls.sdk.traffic.model.ProbeWrapper;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
@DebugMetadata(c = "com.mappls.sdk.traffic.LocationStorage$sendLocation$1", f = "LocationStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f13315a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(a aVar, Continuation<? super c> continuation) {
        super(2, continuation);
        this.f13315a = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new c(this.f13315a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        NavigationApplication navigationApplication;
        BeaconDatabase e;
        com.mappls.sdk.traffic.db.dao.a a2;
        NavigationApplication navigationApplication2;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        navigationApplication = this.f13315a.c;
        if (navigationApplication != null && (e = navigationApplication.e()) != null && (a2 = e.a()) != null) {
            a aVar = this.f13315a;
            ArrayList probeLocations = ((com.mappls.sdk.traffic.db.dao.b) a2).a();
            if (probeLocations.size() <= 0) {
                a.c(aVar);
            } else if (aVar.c() == null) {
                return Unit.INSTANCE;
            } else {
                BeaconPacket beaconPacket = new BeaconPacket();
                ArrayList arrayList = new ArrayList();
                ProbeWrapper probeWrapper = new ProbeWrapper();
                navigationApplication2 = aVar.c;
                Intrinsics.checkNotNull(navigationApplication2);
                probeWrapper.setDeviceId(Settings.Secure.getString(navigationApplication2.getContentResolver(), "android_id"));
                arrayList.add(probeWrapper);
                probeWrapper.setGps(probeLocations);
                beaconPacket.setProbes(arrayList);
                MapplsBeacon.Builder c = MapplsBeacon.c();
                if (MapplsNavigationHelper.getInstance().getBaseRes() != null) {
                    c.baseUrl(MapplsNavigationHelper.getInstance().getBaseRes());
                }
                try {
                    Response<ResponseBody> executeCall = c.beaconKey(aVar.c()).beaconPacket(beaconPacket).build().executeCall();
                    Intrinsics.checkNotNullExpressionValue(executeCall, "call.executeCall()");
                    int code = executeCall.code();
                    if (200 <= code && code < 404) {
                        aVar.d = System.currentTimeMillis();
                        Intrinsics.checkNotNullExpressionValue(probeLocations, "probeLocations");
                        a.a(aVar, probeLocations);
                        a.d(aVar);
                    } else {
                        a.b(aVar);
                    }
                } catch (Exception unused) {
                    a.b(aVar);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
