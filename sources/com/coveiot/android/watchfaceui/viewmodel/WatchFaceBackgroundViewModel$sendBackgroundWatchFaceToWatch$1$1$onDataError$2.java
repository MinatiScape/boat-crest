package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.CancelWatchFaceBackgroundUploadRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceBackgroundViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            CancelWatchFaceBackgroundUploadRequest cancelWatchFaceBackgroundUploadRequest = new CancelWatchFaceBackgroundUploadRequest();
            final WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.this$0;
            bleApi.setUserSettings(cancelWatchFaceBackgroundUploadRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2.1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2$1$onSettingsError$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2$1$a */
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceBackgroundViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.isUploadToWatchCanceled().postValue(Boxing.boxBoolean(false));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2$1$onSettingsResponse$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1$1$onDataError$2$1$b */
                /* loaded from: classes8.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceBackgroundViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.isUploadToWatchCanceled().postValue(Boxing.boxBoolean(true));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceBackgroundViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new b(WatchFaceBackgroundViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
