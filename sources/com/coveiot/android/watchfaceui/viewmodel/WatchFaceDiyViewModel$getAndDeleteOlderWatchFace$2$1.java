package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.WatchFace;
import com.coveiot.android.bleabstract.request.DeleteWatchFaceRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetWatchFaceListResponse;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFaceDiyViewModel$getAndDeleteOlderWatchFace$2$1 implements DataResultListener {
    public final /* synthetic */ WatchFaceDiyViewModel h;
    public final /* synthetic */ String i;
    public final /* synthetic */ WatchFaceBean j;

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$getAndDeleteOlderWatchFace$2$1$onDataError$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ WatchFaceDiyViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WatchFaceDiyViewModel watchFaceDiyViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = watchFaceDiyViewModel;
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
                ProgressBean value = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setProgress(0);
                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(false);
                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        e.e(ViewModelKt.getViewModelScope(this.h), Dispatchers.getMain(), null, new a(this.h, null), 2, null);
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataResponse(@NotNull BleBaseResponse response) {
        ArrayList<WatchFace> watchFaceList;
        Intrinsics.checkNotNullParameter(response, "response");
        Object responseData = response.getResponseData();
        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetWatchFaceListResponse");
        GetWatchFaceListResponse getWatchFaceListResponse = (GetWatchFaceListResponse) responseData;
        Intrinsics.checkNotNull(getWatchFaceListResponse.getWatchFaceList());
        if (!watchFaceList.isEmpty()) {
            ArrayList<WatchFace> watchFaceList2 = getWatchFaceListResponse.getWatchFaceList();
            Intrinsics.checkNotNull(watchFaceList2);
            Iterator<WatchFace> it = watchFaceList2.iterator();
            String str = null;
            while (it.hasNext()) {
                WatchFace next = it.next();
                String watchFaceName = next.getWatchFaceName();
                Intrinsics.checkNotNull(watchFaceName);
                if (m.endsWith$default(watchFaceName, ".iwf", false, 2, null)) {
                    str = next.getWatchFaceName();
                    Intrinsics.checkNotNull(str);
                }
            }
            if (str != null && m.endsWith$default(str, ".iwf", false, 2, null)) {
                DeleteWatchFaceRequest deleteWatchFaceRequest = new DeleteWatchFaceRequest(1);
                deleteWatchFaceRequest.setWatchFaceName(str);
                BleApi bleApi = BleApiManager.getInstance(this.h.getContext()).getBleApi();
                final WatchFaceDiyViewModel watchFaceDiyViewModel = this.h;
                final String str2 = this.i;
                final WatchFaceBean watchFaceBean = this.j;
                bleApi.setUserSettings(deleteWatchFaceRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$getAndDeleteOlderWatchFace$2$1$onDataResponse$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                        Intrinsics.checkNotNullParameter(response2, "response");
                        ProgressBean value = WatchFaceDiyViewModel.this.getProgressLiveData().getValue();
                        Intrinsics.checkNotNull(value);
                        value.setVisible(false);
                        WatchFaceDiyViewModel.this.i(str2, watchFaceBean);
                    }
                });
                return;
            }
            ProgressBean value = this.h.getProgressLiveData().getValue();
            Intrinsics.checkNotNull(value);
            value.setVisible(false);
            this.h.i(this.i, this.j);
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onProgressUpdate(@NotNull ProgressData progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
    }
}
