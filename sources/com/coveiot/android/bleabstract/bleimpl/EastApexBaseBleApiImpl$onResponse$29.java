package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.kheastapexdb.activity.KHEAActivityRepository;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$onResponse$29", f = "EastApexBaseBleApiImpl.kt", i = {0, 0, 0, 0}, l = {1444}, m = "invokeSuspend", n = {"entityActvityList", "bleBaseResponse", "mRes", "i"}, s = {"L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes2.dex */
public final class EastApexBaseBleApiImpl$onResponse$29 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public Object f2948a;
    public Object b;
    public Object c;
    public int d;
    public int e;
    public int f;
    public final /* synthetic */ EastApexBaseRes g;
    public final /* synthetic */ BleBaseRequest h;
    public final /* synthetic */ EastApexBaseBleApiImpl i;
    public final /* synthetic */ DataResultListener j;

    @DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$onResponse$29$2", f = "EastApexBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$onResponse$29$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EastApexBaseBleApiImpl f2949a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(EastApexBaseBleApiImpl eastApexBaseBleApiImpl, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.f2949a = eastApexBaseBleApiImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass2(this.f2949a, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.f2949a, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            String str;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            str = this.f2949a.o;
            StringBuilder sb = new StringBuilder();
            sb.append("EastApexActivityDataReq updating entity table getMacAddress() ");
            sb.append(this.f2949a.getMacAddress());
            sb.append(" BleApiUtils.getTodayStartTime().timeInMillis ");
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            sb.append(bleApiUtils.getTodayStartTime().getTimeInMillis());
            LogHelper.d(str, sb.toString());
            KHEAActivityRepository.Companion.getInstance(this.f2949a.getContext()).updateActivityDataProcessedBefore(this.f2949a.getMacAddress(), bleApiUtils.getTodayStartTime().getTimeInMillis());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EastApexBaseBleApiImpl$onResponse$29(EastApexBaseRes eastApexBaseRes, BleBaseRequest bleBaseRequest, EastApexBaseBleApiImpl eastApexBaseBleApiImpl, DataResultListener dataResultListener, Continuation<? super EastApexBaseBleApiImpl$onResponse$29> continuation) {
        super(2, continuation);
        this.g = eastApexBaseRes;
        this.h = bleBaseRequest;
        this.i = eastApexBaseBleApiImpl;
        this.j = dataResultListener;
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new EastApexBaseBleApiImpl$onResponse$29(this.g, this.h, this.i, this.j, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new EastApexBaseBleApiImpl$onResponse$29(this.g, this.h, this.i, this.j, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00bb -> B:19:0x00be). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl$onResponse$29.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
