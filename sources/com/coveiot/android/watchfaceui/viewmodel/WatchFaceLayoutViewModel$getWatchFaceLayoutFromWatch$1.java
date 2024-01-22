package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetCustomWatchFaceLayoutRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.CustomWatchFaceLayoutResponse;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ GetCustomWatchFaceLayoutRequest $getCustomWatchFaceLayoutRequest;
    public int label;
    public final /* synthetic */ WatchFaceLayoutViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1(WatchFaceLayoutViewModel watchFaceLayoutViewModel, GetCustomWatchFaceLayoutRequest getCustomWatchFaceLayoutRequest, Continuation<? super WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceLayoutViewModel;
        this.$getCustomWatchFaceLayoutRequest = getCustomWatchFaceLayoutRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1(this.this$0, this.$getCustomWatchFaceLayoutRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            GetCustomWatchFaceLayoutRequest getCustomWatchFaceLayoutRequest = this.$getCustomWatchFaceLayoutRequest;
            final WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.this$0;
            bleApi.getData(getCustomWatchFaceLayoutRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1.1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1$1$onDataResponse$1", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1$1$a */
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ WatchFaceLayoutViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(WatchFaceLayoutViewModel watchFaceLayoutViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceLayoutViewModel;
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
                            this.this$0.getWatchFaceLayoutFetchObserver().setValue(Boxing.boxBoolean(true));
                            this.this$0.getWatchFaceLayoutFetchObserver().postValue(Boxing.boxBoolean(true));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof CustomWatchFaceLayoutResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.CustomWatchFaceLayoutResponse");
                    CustomWatchFaceLayoutResponse customWatchFaceLayoutResponse = (CustomWatchFaceLayoutResponse) responseData;
                    WatchFaceLayoutViewModel.this.setWatchFaceLayoutInfoFromWatch(new WatchFaceLayoutInfo(0, customWatchFaceLayoutResponse.getPosition(), customWatchFaceLayoutResponse.getTopContent(), customWatchFaceLayoutResponse.getBottomContent(), customWatchFaceLayoutResponse.getTextColor(), customWatchFaceLayoutResponse.getBackgroundPictureMd5(), null, null, null, customWatchFaceLayoutResponse.getHeight(), customWatchFaceLayoutResponse.getWidth(), customWatchFaceLayoutResponse.getThumbHeight(), customWatchFaceLayoutResponse.getThumbWidth(), customWatchFaceLayoutResponse.getCompressionType(), null, 16833, null));
                    e.e(ViewModelKt.getViewModelScope(WatchFaceLayoutViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceLayoutViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
