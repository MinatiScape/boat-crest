package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.formatter.touch.TouchStepsFormatter;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.touchsdk.TouchELXResponseListener;
import com.coveiot.android.touchsdk.api.TouchELXBaseReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.api.TouchStepsReq;
import com.coveiot.khtouchdb.walk.EntityTGStepData;
import com.coveiot.khtouchdb.walk.KHTGStepsRepository;
import com.coveiot.sdk.ble.helper.LogsHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.TouchELXService$onHealthDataListener$1$onCompleted$1", f = "TouchELXService.kt", i = {}, l = {1564}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class TouchELXService$onHealthDataListener$1$onCompleted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f3953a;
    public final /* synthetic */ TouchELXService b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchELXService$onHealthDataListener$1$onCompleted$1(TouchELXService touchELXService, Continuation<? super TouchELXService$onHealthDataListener$1$onCompleted$1> continuation) {
        super(2, continuation);
        this.b = touchELXService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TouchELXService$onHealthDataListener$1$onCompleted$1(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new TouchELXService$onHealthDataListener$1$onCompleted$1(this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        TouchELXResponseListener responseListener;
        TouchELXResponseListener responseListener2;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.f3953a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.f3953a = 1;
            if (DelayKt.delay(500L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        if (this.b.getKhCurrentCommand() != null) {
            if (this.b.getKhCurrentCommand() instanceof TouchStepsReq) {
                TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
                TouchELXBaseReq khCurrentCommand = this.b.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                touchELXBaseRes.setBaseReq(khCurrentCommand);
                List<EntityTGStepData> allUnProcessedStepsData = KHTGStepsRepository.Companion.getInstance(this.b).getAllUnProcessedStepsData(this.b.getMacAddress());
                if (!(allUnProcessedStepsData == null || allUnProcessedStepsData.isEmpty())) {
                    ArrayList arrayList = new ArrayList();
                    int size = allUnProcessedStepsData.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        StepsResponse stepResponse = TouchStepsFormatter.Companion.getInstance(this.b).getStepResponse(allUnProcessedStepsData.get(i2));
                        if (i2 == allUnProcessedStepsData.size() - 1) {
                            stepResponse.setComplete(true);
                        }
                        arrayList.add(stepResponse);
                    }
                    touchELXBaseRes.setObj(arrayList);
                    TouchELXBaseReq khCurrentCommand2 = this.b.getKhCurrentCommand();
                    if (khCurrentCommand2 != null && (responseListener2 = khCurrentCommand2.getResponseListener()) != null) {
                        responseListener2.onResponse(touchELXBaseRes);
                    }
                } else {
                    TouchELXBaseReq khCurrentCommand3 = this.b.getKhCurrentCommand();
                    if (khCurrentCommand3 != null && (responseListener = khCurrentCommand3.getResponseListener()) != null) {
                        responseListener.onResponse(touchELXBaseRes);
                    }
                }
            } else {
                LogsHelper.d(this.b.getTAG(), "khCurrent command is not TouchStepsReq");
            }
        } else {
            LogsHelper.d(this.b.getTAG(), "khCurrent command is null");
        }
        return Unit.INSTANCE;
    }
}
