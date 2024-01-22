package com.coveiot.android.bleabstract.bleimpl;

import android.os.Handler;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SessionStepsDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.khsmadb.activity.KhActivityRepository;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoRepository;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$24", f = "SmaBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleApiImpl$onResponse$24 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SmaBaseBleApiImpl f3224a;
    public final /* synthetic */ SmaBaseRes b;
    public final /* synthetic */ Ref.ObjectRef<BleBaseRequest> c;
    public final /* synthetic */ DataResultListener d;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$24$2", f = "SmaBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$24$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SmaBaseBleApiImpl f3225a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SmaBaseBleApiImpl smaBaseBleApiImpl, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.f3225a = smaBaseBleApiImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f3225a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.f3225a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            String macAddress = this.f3225a.getMacAddress();
            SmaUtils smaUtils = SmaUtils.INSTANCE;
            KhActivityRepository.Companion.getInstance(this.f3225a.getContext()).updateActivityDataProcessedBefore(macAddress, (smaUtils.getTodayStartTime().getTimeInMillis() - smaUtils.getCalenderFor2000().getTimeInMillis()) / 1000);
            KhSmaDeviceInfoRepository.Companion.getInstance(this.f3225a.getContext()).updateStepLastSyncTime(this.f3225a.getMacAddress(), System.currentTimeMillis());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleApiImpl$onResponse$24(SmaBaseBleApiImpl smaBaseBleApiImpl, SmaBaseRes smaBaseRes, Ref.ObjectRef<BleBaseRequest> objectRef, DataResultListener dataResultListener, Continuation<? super SmaBaseBleApiImpl$onResponse$24> continuation) {
        super(2, continuation);
        this.f3224a = smaBaseBleApiImpl;
        this.b = smaBaseRes;
        this.c = objectRef;
        this.d = dataResultListener;
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleApiImpl$onResponse$24(this.f3224a, this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleApiImpl$onResponse$24(this.f3224a, this.b, this.c, this.d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        LogHelper.d(this.f3224a.getTAG(), "set 4");
        Object obj2 = this.b.getObj();
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
        for (Object obj3 : (List) obj2) {
            LogHelper.d(this.f3224a.getTAG(), "set 5");
            if (obj3 instanceof StepsResponse) {
                if (this.c.element instanceof SessionStepsDataRequest) {
                    if (((StepsResponse) obj3).isComplete()) {
                        this.f3224a.setCompleteAndProcessNext();
                    }
                } else {
                    LogHelper.d(this.f3224a.getTAG(), "set 6");
                    final BleBaseResponse bleBaseResponse = new BleBaseResponse(this.c.element);
                    StepsResponse stepsResponse = (StepsResponse) obj3;
                    bleBaseResponse.setResponseData(stepsResponse);
                    Handler mDataResponseHandler = this.f3224a.getMDataResponseHandler();
                    final DataResultListener dataResultListener = this.d;
                    mDataResponseHandler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.a8
                        @Override // java.lang.Runnable
                        public final void run() {
                            SmaBaseBleApiImpl$onResponse$24.a(DataResultListener.this, bleBaseResponse);
                        }
                    });
                    if (stepsResponse.isComplete()) {
                        String tag = this.f3224a.getTAG();
                        LogHelper.d(tag, "SmaUtils.getTodayStartTime().timeInMillis -> " + SmaUtils.INSTANCE.getTodayStartTime().getTimeInMillis());
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(this.f3224a, null), 2, null);
                        this.f3224a.setCompleteAndProcessNext();
                        LogHelper.d(this.f3224a.getTAG(), "Activities Complete");
                    } else {
                        LogHelper.d(this.f3224a.getTAG(), "Activities InComplete");
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
