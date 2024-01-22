package com.coveiot.android.tappy.viewmodel;

import com.coveiot.android.tappy.model.ErrorLogInfo;
import com.coveiot.android.tappy.model.ExtraInfo;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SErrorLogRequest;
import com.coveiot.coveaccess.tappy.model.SErrorLogResponse;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$logError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$logError$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ErrorLogInfo $errorLogInfo;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$logError$1(ErrorLogInfo errorLogInfo, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$logError$1> continuation) {
        super(2, continuation);
        this.$errorLogInfo = errorLogInfo;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$logError$1(this.$errorLogInfo, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$logError$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SErrorLogRequest sErrorLogRequest = new SErrorLogRequest();
            sErrorLogRequest.setLogSeverity(this.$errorLogInfo.getLogSeverity());
            sErrorLogRequest.setErrorGUID(this.$errorLogInfo.getErrorGUID());
            sErrorLogRequest.setDateUTC(this.$errorLogInfo.getDateUTC());
            sErrorLogRequest.setDetails(this.$errorLogInfo.getDetails());
            sErrorLogRequest.setModule(this.$errorLogInfo.getModule());
            if (this.$errorLogInfo.getExtraInfo() != null) {
                SErrorLogRequest.ExtraInfo extraInfo = new SErrorLogRequest.ExtraInfo();
                ExtraInfo extraInfo2 = this.$errorLogInfo.getExtraInfo();
                Intrinsics.checkNotNull(extraInfo2);
                extraInfo.setExtra(extraInfo2.getExtra());
                sErrorLogRequest.setExtraInfo(extraInfo);
            }
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.logError(sErrorLogRequest, new CoveApiListener<SErrorLogResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$logError$1.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(NfcStrapViewModel.this.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SErrorLogResponse sErrorLogResponse) {
                    LogHelper.d(NfcStrapViewModel.this.getTAG(), "error logged successfully.");
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
