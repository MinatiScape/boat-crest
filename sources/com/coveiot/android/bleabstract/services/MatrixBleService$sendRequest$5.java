package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.formatter.matrix.MatrixSpO2Formatter;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2Repository;
import com.coveiot.android.matrixsdk.MatrixResponseListener;
import com.coveiot.android.matrixsdk.api.MatrixBaseReq;
import com.coveiot.android.matrixsdk.api.MatrixBaseRes;
import com.coveiot.sdk.ble.helper.LogsHelper;
import java.util.List;
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
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.MatrixBleService$sendRequest$5", f = "MatrixBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MatrixBleService$sendRequest$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MatrixBleService f3892a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixBleService$sendRequest$5(MatrixBleService matrixBleService, Continuation<? super MatrixBleService$sendRequest$5> continuation) {
        super(2, continuation);
        this.f3892a = matrixBleService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MatrixBleService$sendRequest$5(this.f3892a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new MatrixBleService$sendRequest$5(this.f3892a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String str;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        if (this.f3892a.getKhCurrentCommand() == null) {
            str = this.f3892a.f3858a;
            LogsHelper.d(str, "khCurrent command is null");
        } else {
            MatrixBaseRes matrixBaseRes = new MatrixBaseRes();
            MatrixBaseReq khCurrentCommand = this.f3892a.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand);
            matrixBaseRes.setBaseReq(khCurrentCommand);
            List<KhMatrixSpO2> allUnProcessedSpO2Data = KhMatrixSpO2Repository.Companion.getInstance(this.f3892a).getAllUnProcessedSpO2Data(this.f3892a.getMacAddress());
            if (allUnProcessedSpO2Data != null) {
                matrixBaseRes.setObj(MatrixSpO2Formatter.Companion.getInstance(this.f3892a).convertSpO2Data(allUnProcessedSpO2Data));
            }
            MatrixBaseReq khCurrentCommand2 = this.f3892a.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand2);
            MatrixResponseListener responseListener = khCurrentCommand2.getResponseListener();
            Intrinsics.checkNotNull(responseListener);
            responseListener.onResponse(matrixBaseRes);
            this.f3892a.setKhCurrentCommand(null);
        }
        return Unit.INSTANCE;
    }
}
