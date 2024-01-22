package com.coveiot.android.watchfaceui.viewmodel;

import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.CancelWatchFaceUploadRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceBackgroundViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            CancelWatchFaceUploadRequest cancelWatchFaceUploadRequest = new CancelWatchFaceUploadRequest();
            final WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.this$0;
            bleApi.setUserSettings(cancelWatchFaceUploadRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2.1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    WatchFaceBackgroundViewModel.this.isUploadToWatchCanceled().postValue(Boolean.FALSE);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    WatchFaceBackgroundViewModel.this.isUploadToWatchCanceled().postValue(Boolean.TRUE);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}