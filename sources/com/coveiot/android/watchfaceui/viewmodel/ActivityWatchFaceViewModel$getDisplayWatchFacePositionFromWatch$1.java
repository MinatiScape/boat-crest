package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.WatchFaceBackgroundInfo;
import com.coveiot.android.bleabstract.request.GetWatchFacePositionRequest;
import com.coveiot.android.bleabstract.request.GetWatchfaceBackgroundRequest;
import com.coveiot.android.bleabstract.request.GetWatchfaceBackgroundResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Iterator;
import kotlin.NotImplementedError;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1", f = "ActivityWatchFaceViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ GetWatchFacePositionRequest $getWatchFacePositionRequest;
    public int label;
    public final /* synthetic */ ActivityWatchFaceViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1(ActivityWatchFaceViewModel activityWatchFaceViewModel, GetWatchFacePositionRequest getWatchFacePositionRequest, Continuation<? super ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = activityWatchFaceViewModel;
        this.$getWatchFacePositionRequest = getWatchFacePositionRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1(this.this$0, this.$getWatchFacePositionRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            GetWatchFacePositionRequest getWatchFacePositionRequest = this.$getWatchFacePositionRequest;
            final ActivityWatchFaceViewModel activityWatchFaceViewModel = this.this$0;
            bleApi.getData(getWatchFacePositionRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1.1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = ActivityWatchFaceViewModel.this.getTAG();
                    LogHelper.d(tag, "getDisplayWatchFacePositionFromWatch error " + error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof WatchFacePositionResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.WatchFacePositionResponse");
                    if (((WatchFacePositionResponse) responseData).getWatchFacePosition() != null) {
                        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = ActivityWatchFaceViewModel.this;
                        Object responseData2 = response.getResponseData();
                        Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.WatchFacePositionResponse");
                        Integer watchFacePosition = ((WatchFacePositionResponse) responseData2).getWatchFacePosition();
                        Intrinsics.checkNotNull(watchFacePosition);
                        activityWatchFaceViewModel2.setDisplayedWatchFacePositionFromWatch(watchFacePosition.intValue());
                        Utils utils = Utils.INSTANCE;
                        Context context = ActivityWatchFaceViewModel.this.getContext();
                        Intrinsics.checkNotNull(context);
                        if (!utils.isCZDevice(context)) {
                            Context context2 = ActivityWatchFaceViewModel.this.getContext();
                            Intrinsics.checkNotNull(context2);
                            if (!Utils.isCYDevice(context2)) {
                                String tag = ActivityWatchFaceViewModel.this.getTAG();
                                LogHelper.d(tag, "crpDisplayWatchFacePositionFromWatch->" + ActivityWatchFaceViewModel.this.getDisplayedWatchFacePositionFromWatch());
                                ActivityWatchFaceViewModel.this.getViewModelOnSuccessListener().onCurrentWatchFetchPositionFetchSuccess(ActivityWatchFaceViewModel.this.getDisplayedWatchFacePositionFromWatch());
                                return;
                            }
                        }
                        BleApi bleApi2 = BleApiManager.getInstance(ActivityWatchFaceViewModel.this.getContext()).getBleApi();
                        GetWatchfaceBackgroundRequest getWatchfaceBackgroundRequest = new GetWatchfaceBackgroundRequest();
                        final ActivityWatchFaceViewModel activityWatchFaceViewModel3 = ActivityWatchFaceViewModel.this;
                        bleApi2.getData(getWatchfaceBackgroundRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1$1$onDataResponse$1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataResponse(@NotNull BleBaseResponse response2) {
                                MutableLiveData mutableLiveData;
                                MutableLiveData mutableLiveData2;
                                Intrinsics.checkNotNullParameter(response2, "response");
                                if (response2.getResponseData() instanceof GetWatchfaceBackgroundResponse) {
                                    Object responseData3 = response2.getResponseData();
                                    Intrinsics.checkNotNull(responseData3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.request.GetWatchfaceBackgroundResponse");
                                    Iterator<WatchFaceBackgroundInfo> it = ((GetWatchfaceBackgroundResponse) responseData3).getWatchFaceBackgroundInfos().iterator();
                                    while (it.hasNext()) {
                                        WatchFaceBackgroundInfo next = it.next();
                                        if (next.getWatchFaceId() == ActivityWatchFaceViewModel.this.getDisplayedWatchFacePositionFromWatch()) {
                                            if (next.getImageId() != 65535) {
                                                ActivityWatchFaceViewModel.this.setDisplayedWatchFacePositionFromWatch(-1);
                                            }
                                            String tag2 = ActivityWatchFaceViewModel.this.getTAG();
                                            LogHelper.d(tag2, "crpDisplayWatchFacePositionFromWatch->" + ActivityWatchFaceViewModel.this.getDisplayedWatchFacePositionFromWatch());
                                            ActivityWatchFaceViewModel.this.getViewModelOnSuccessListener().onCurrentWatchFetchPositionFetchSuccess(ActivityWatchFaceViewModel.this.getDisplayedWatchFacePositionFromWatch());
                                            mutableLiveData = ActivityWatchFaceViewModel.this.f;
                                            Boolean bool = Boolean.FALSE;
                                            mutableLiveData.postValue(bool);
                                            mutableLiveData2 = ActivityWatchFaceViewModel.this.e;
                                            mutableLiveData2.postValue(bool);
                                            return;
                                        }
                                    }
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
                            }
                        });
                    }
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
