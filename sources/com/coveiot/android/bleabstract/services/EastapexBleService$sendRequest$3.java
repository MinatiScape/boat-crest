package com.coveiot.android.bleabstract.services;

import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.kheastapexdb.activity.EntityEAActivityData;
import com.coveiot.kheastapexdb.activity.KHEAActivityRepository;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.EastapexBleService$sendRequest$3", f = "EastapexBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class EastapexBleService$sendRequest$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3741a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EastapexBleService$sendRequest$3(EastapexBleService eastapexBleService, Continuation<? super EastapexBleService$sendRequest$3> continuation) {
        super(2, continuation);
        this.f3741a = eastapexBleService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new EastapexBleService$sendRequest$3(this.f3741a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new EastapexBleService$sendRequest$3(this.f3741a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        if (this.f3741a.getKhCurrentCommand() != null) {
            EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
            EastApexBaseReq khCurrentCommand = this.f3741a.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand);
            eastApexBaseRes.setBaseReq(khCurrentCommand);
            List<EntityEAActivityData> allUnProcessedActivityData = KHEAActivityRepository.Companion.getInstance(this.f3741a).getAllUnProcessedActivityData(this.f3741a.getMacAddress());
            String tag = this.f3741a.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("EastApexActivityDataReq dataToProcess size ");
            sb.append(allUnProcessedActivityData != null ? Boxing.boxInt(allUnProcessedActivityData.size()) : null);
            LogHelper.d(tag, sb.toString());
            if (!(allUnProcessedActivityData == null || allUnProcessedActivityData.isEmpty())) {
                eastApexBaseRes.setObj(allUnProcessedActivityData);
                EastApexBaseReq khCurrentCommand2 = this.f3741a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                this.f3741a.setKhCurrentCommand(null);
            } else {
                EastApexBaseReq khCurrentCommand3 = this.f3741a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand3);
                EastApexResponseListener responseListener2 = khCurrentCommand3.getResponseListener();
                Intrinsics.checkNotNull(responseListener2);
                responseListener2.onResponse(eastApexBaseRes);
                this.f3741a.setKhCurrentCommand(null);
            }
        } else {
            LogsHelper.d(this.f3741a.getTAG(), "khCurrent command is null");
        }
        return Unit.INSTANCE;
    }
}
