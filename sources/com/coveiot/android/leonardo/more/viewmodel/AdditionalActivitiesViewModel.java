package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetSportsPushRequest;
import com.coveiot.android.boat.R;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AdditionalActivitiesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5170a;
    public AdditionalActivityPushListener additionalActivityPushListener;
    public final String b;
    @NotNull
    public MutableLiveData<Integer> c;
    @NotNull
    public MutableLiveData<ProgressBean> d;

    /* loaded from: classes5.dex */
    public interface AdditionalActivityPushListener {
        void onFailure(@NotNull String str);

        void onSuccess();
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$1", f = "AdditionalActivitiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                ProgressBean value = AdditionalActivitiesViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = AdditionalActivitiesViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(AdditionalActivitiesViewModel.this.getContext().getString(R.string.uploading_activity));
                    ProgressBean value3 = AdditionalActivitiesViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = AdditionalActivitiesViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = AdditionalActivitiesViewModel.this.getProgressLiveData();
                    ProgressBean value5 = AdditionalActivitiesViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public AdditionalActivitiesViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5170a = context;
        this.b = AdditionalActivitiesViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        MutableLiveData<ProgressBean> mutableLiveData = new MutableLiveData<>();
        this.d = mutableLiveData;
        mutableLiveData.setValue(new ProgressBean(null, 0, false));
    }

    @NotNull
    public final AdditionalActivityPushListener getAdditionalActivityPushListener() {
        AdditionalActivityPushListener additionalActivityPushListener = this.additionalActivityPushListener;
        if (additionalActivityPushListener != null) {
            return additionalActivityPushListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("additionalActivityPushListener");
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f5170a;
    }

    @NotNull
    public final MutableLiveData<ProgressBean> getProgressLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Integer> getProgressWatchFaceUpdate() {
        return this.c;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void pushAdditionalActivityToWatch(@Nullable String str) {
        if (BleApiManager.getInstance(this.f5170a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            if (str != null) {
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2(this, new SetSportsPushRequest(str), null), 2, null);
                return;
            }
            return;
        }
        AdditionalActivityPushListener additionalActivityPushListener = getAdditionalActivityPushListener();
        String string = this.f5170a.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        additionalActivityPushListener.onFailure(string);
        LogHelper.d(this.b, "pushAdditionalActivityToWatch Watch not connected");
    }

    public final void setAdditionalActivityPushListener(@NotNull AdditionalActivityPushListener additionalActivityPushListener) {
        Intrinsics.checkNotNullParameter(additionalActivityPushListener, "<set-?>");
        this.additionalActivityPushListener = additionalActivityPushListener;
    }

    public final void setProgressLiveData(@NotNull MutableLiveData<ProgressBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setProgressWatchFaceUpdate(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }
}
