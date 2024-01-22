package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.formatter.SMASleepFormatter;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoRepository;
import com.coveiot.khsmadb.sleep.KhSleepRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21", f = "SmaBaseBleApiImpl.kt", i = {0, 0}, l = {2000}, m = "invokeSuspend", n = {"response", "bleBaseResponse"}, s = {"L$1", "L$2"})
/* loaded from: classes2.dex */
public final class SmaBaseBleApiImpl$onResponse$21 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public Object f3221a;
    public Object b;
    public Object c;
    public int d;
    public final /* synthetic */ SmaBaseRes e;
    public final /* synthetic */ Ref.ObjectRef<BleBaseRequest> f;
    public final /* synthetic */ SmaBaseBleApiImpl g;
    public final /* synthetic */ DataResultListener h;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21$2", f = "SmaBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SmaBaseBleApiImpl f3222a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SmaBaseBleApiImpl smaBaseBleApiImpl, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.f3222a = smaBaseBleApiImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f3222a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.f3222a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            KhSleepRepository.Companion.getInstance(this.f3222a.getContext()).updateSleepDataProcessedBefore(this.f3222a.getMacAddress(), (SMASleepFormatter.Companion.getInstance(this.f3222a.getContext()).getTodaySleepStartTime().getTimeInMillis() - SmaUtils.INSTANCE.getCalenderFor2000().getTimeInMillis()) / 1000);
            KhSmaDeviceInfoRepository.Companion.getInstance(this.f3222a.getContext()).updateSleepLastSyncTime(this.f3222a.getMacAddress(), System.currentTimeMillis());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleApiImpl$onResponse$21(SmaBaseRes smaBaseRes, Ref.ObjectRef<BleBaseRequest> objectRef, SmaBaseBleApiImpl smaBaseBleApiImpl, DataResultListener dataResultListener, Continuation<? super SmaBaseBleApiImpl$onResponse$21> continuation) {
        super(2, continuation);
        this.e = smaBaseRes;
        this.f = objectRef;
        this.g = smaBaseBleApiImpl;
        this.h = dataResultListener;
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleApiImpl$onResponse$21(this.e, this.f, this.g, this.h, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleApiImpl$onResponse$21(this.e, this.f, this.g, this.h, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0068 -> B:16:0x006b). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r1 = r11.d
            r2 = 1
            if (r1 == 0) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r1 = r11.c
            com.coveiot.android.bleabstract.response.BleBaseResponse r1 = (com.coveiot.android.bleabstract.response.BleBaseResponse) r1
            java.lang.Object r3 = r11.b
            java.lang.Object r4 = r11.f3221a
            java.util.Iterator r4 = (java.util.Iterator) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L6b
        L1a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L22:
            kotlin.ResultKt.throwOnFailure(r12)
            com.coveiot.android.smasdk.api.SmaBaseRes r12 = r11.e
            java.lang.Object r12 = r12.getObj()
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.collections.List<*>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12, r1)
            java.util.List r12 = (java.util.List) r12
            java.util.Iterator r12 = r12.iterator()
            r4 = r12
            r12 = r11
        L38:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto Ld3
            java.lang.Object r3 = r4.next()
            boolean r1 = r3 instanceof com.coveiot.android.bleabstract.response.SleepResponse
            if (r1 == 0) goto L38
            com.coveiot.android.bleabstract.response.BleBaseResponse r1 = new com.coveiot.android.bleabstract.response.BleBaseResponse
            kotlin.jvm.internal.Ref$ObjectRef<com.coveiot.android.bleabstract.request.BleBaseRequest> r5 = r12.f
            T r5 = r5.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            com.coveiot.android.bleabstract.request.BleBaseRequest r5 = (com.coveiot.android.bleabstract.request.BleBaseRequest) r5
            r1.<init>(r5)
            r5 = r3
            com.coveiot.android.bleabstract.response.SleepResponse r5 = (com.coveiot.android.bleabstract.response.SleepResponse) r5
            r1.setResponseData(r5)
            r12.f3221a = r4
            r12.b = r3
            r12.c = r1
            r12.d = r2
            r5 = 200(0xc8, double:9.9E-322)
            java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r12)
            if (r5 != r0) goto L6b
            return r0
        L6b:
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl r5 = r12.g
            android.os.Handler r5 = r5.getMDataResponseHandler()
            com.coveiot.android.bleabstract.listeners.DataResultListener r6 = r12.h
            com.coveiot.android.bleabstract.bleimpl.z7 r7 = new com.coveiot.android.bleabstract.bleimpl.z7
            r7.<init>()
            r5.post(r7)
            java.lang.String r1 = "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.SleepResponse"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r1)
            com.coveiot.android.bleabstract.response.SleepResponse r3 = (com.coveiot.android.bleabstract.response.SleepResponse) r3
            boolean r1 = r3.isComplete()
            if (r1 == 0) goto L38
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl r1 = r12.g
            java.lang.String r1 = r1.getTAG()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "getTodaySleepStartTime -> "
            r3.append(r5)
            com.coveiot.android.bleabstract.formatter.SMASleepFormatter$Companion r5 = com.coveiot.android.bleabstract.formatter.SMASleepFormatter.Companion
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl r6 = r12.g
            android.content.Context r6 = r6.getContext()
            java.lang.Object r5 = r5.getInstance(r6)
            com.coveiot.android.bleabstract.formatter.SMASleepFormatter r5 = (com.coveiot.android.bleabstract.formatter.SMASleepFormatter) r5
            java.util.Calendar r5 = r5.getTodaySleepStartTime()
            long r5 = r5.getTimeInMillis()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.coveiot.utils.utility.LogHelper.d(r1, r3)
            kotlinx.coroutines.GlobalScope r5 = kotlinx.coroutines.GlobalScope.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21$2 r8 = new com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21$2
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl r1 = r12.g
            r3 = 0
            r8.<init>(r1, r3)
            r7 = 0
            r9 = 2
            r10 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
            com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl r1 = r12.g
            r1.setCompleteAndProcessNext()
            goto L38
        Ld3:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$21.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
