package com.coveiot.android.dashboard2;

import com.coveiot.android.dashboard2.LastDataHelper;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.livedata.LiveDataApiManager;
import com.coveiot.coveaccess.livedata.SaveLiveHealthDataReq;
import com.coveiot.coveaccess.livedata.SaveLiveHealthDataRes;
import com.coveiot.coveaccess.livedata.model.LiveHealthDataModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
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
@DebugMetadata(c = "com.coveiot.android.dashboard2.LastDataHelper$saveLastDataInfoToServer$1", f = "LastDataHelper.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class LastDataHelper$saveLastDataInfoToServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LastDataHelper.UploadCompletionListner $listner;
    public int label;
    public final /* synthetic */ LastDataHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LastDataHelper$saveLastDataInfoToServer$1(LastDataHelper lastDataHelper, LastDataHelper.UploadCompletionListner uploadCompletionListner, Continuation<? super LastDataHelper$saveLastDataInfoToServer$1> continuation) {
        super(2, continuation);
        this.this$0 = lastDataHelper;
        this.$listner = uploadCompletionListner;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new LastDataHelper$saveLastDataInfoToServer$1(this.this$0, this.$listner, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((LastDataHelper$saveLastDataInfoToServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LastDataHelper lastDataHelper = this.this$0;
            this.label = 1;
            obj = lastDataHelper.getLastMeasuredData(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        SaveLiveHealthDataReq saveLiveHealthDataReq = new SaveLiveHealthDataReq((LiveHealthDataModel) obj);
        LogHelper.d("LastDataHelper", this.this$0.getGson().toJson(saveLiveHealthDataReq));
        final LastDataHelper.UploadCompletionListner uploadCompletionListner = this.$listner;
        LiveDataApiManager.saveLiveHealthData(saveLiveHealthDataReq, new CoveApiListener<SaveLiveHealthDataRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.LastDataHelper$saveLastDataInfoToServer$1.1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d("LastDataHelper", ErrorConstants.GENERIC_ERROR);
                LastDataHelper.UploadCompletionListner uploadCompletionListner2 = LastDataHelper.UploadCompletionListner.this;
                if (uploadCompletionListner2 != null) {
                    Intrinsics.checkNotNull(uploadCompletionListner2);
                    uploadCompletionListner2.onUploadFailed();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SaveLiveHealthDataRes saveLiveHealthDataRes) {
                LogHelper.d("LastDataHelper", "Success");
                LastDataHelper.UploadCompletionListner uploadCompletionListner2 = LastDataHelper.UploadCompletionListner.this;
                if (uploadCompletionListner2 != null) {
                    Intrinsics.checkNotNull(uploadCompletionListner2);
                    uploadCompletionListner2.onDataUploadeComplete();
                }
            }
        });
        return Unit.INSTANCE;
    }
}
