package com.coveiot.android.bleabstract.bleimpl;

import android.os.Handler;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.khtouchdb.activities.KHTGActivityRepository;
import com.google.android.gms.common.ConnectionResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$onResponse$30", f = "TouchELXBaseBleImpl.kt", i = {0}, l = {ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED}, m = "invokeSuspend", n = {"bleBaseResponse"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class TouchELXBaseBleImpl$onResponse$30 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public Object f3276a;
    public int b;
    public final /* synthetic */ TouchELXBaseRes c;
    public final /* synthetic */ BleBaseRequest d;
    public final /* synthetic */ TouchELXBaseBleImpl e;
    public final /* synthetic */ DataResultListener f;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$onResponse$30$2", f = "TouchELXBaseBleImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$onResponse$30$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ TouchELXBaseBleImpl f3277a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TouchELXBaseBleImpl touchELXBaseBleImpl, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.f3277a = touchELXBaseBleImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f3277a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.f3277a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            KHTGActivityRepository.Companion.getInstance(this.f3277a.getContext()).updateActivityDataProcessedBefore(this.f3277a.getMacAddress(), BleApiUtils.INSTANCE.getTodayStartTime().getTimeInMillis());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchELXBaseBleImpl$onResponse$30(TouchELXBaseRes touchELXBaseRes, BleBaseRequest bleBaseRequest, TouchELXBaseBleImpl touchELXBaseBleImpl, DataResultListener dataResultListener, Continuation<? super TouchELXBaseBleImpl$onResponse$30> continuation) {
        super(2, continuation);
        this.c = touchELXBaseRes;
        this.d = bleBaseRequest;
        this.e = touchELXBaseBleImpl;
        this.f = dataResultListener;
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TouchELXBaseBleImpl$onResponse$30(this.c, this.d, this.e, this.f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new TouchELXBaseBleImpl$onResponse$30(this.c, this.d, this.e, this.f, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        final BleBaseResponse bleBaseResponse;
        Handler handler;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.b;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.c.getObj() instanceof ActivityModeSummaryResponse) {
                BleBaseResponse bleBaseResponse2 = new BleBaseResponse(this.d);
                Object obj2 = this.c.getObj();
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse");
                bleBaseResponse2.setResponseData((ActivityModeSummaryResponse) obj2);
                this.f3276a = bleBaseResponse2;
                this.b = 1;
                if (DelayKt.delay(200L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                bleBaseResponse = bleBaseResponse2;
            }
            return Unit.INSTANCE;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            bleBaseResponse = (BleBaseResponse) this.f3276a;
            ResultKt.throwOnFailure(obj);
        }
        if (this.c.isComplete()) {
            Object obj3 = this.c.getObj();
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse");
            ((ActivityModeSummaryResponse) obj3).setComplete(true);
        }
        handler = this.e.p;
        final DataResultListener dataResultListener = this.f;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.ia
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXBaseBleImpl$onResponse$30.a(DataResultListener.this, bleBaseResponse);
            }
        });
        if (this.c.isComplete()) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(this.e, null), 2, null);
            this.e.setCompleteAndProcessNext(this.d);
        }
        return Unit.INSTANCE;
    }
}
