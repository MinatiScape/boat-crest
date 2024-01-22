package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.GetCustomWatchFaceLayoutRequest;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
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
/* loaded from: classes8.dex */
public final class WatchFaceLayoutViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6159a;
    public final String b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @Nullable
    public WatchFaceLayoutInfo d;
    @Nullable
    public WatchFaceLayoutInfo e;
    @NotNull
    public MutableLiveData<Boolean> f;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$2", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
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
                WatchFaceLayoutViewModel.this.getViewModelOnFailureListener().onFailure(WatchFaceLayoutViewModel.this.getContext().getString(R.string.band_not_connected));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$showSaveBtn$1", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isShow;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$isShow = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$isShow, continuation);
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
                WatchFaceLayoutViewModel.this.getSaveEnableDisable().setValue(Boxing.boxBoolean(this.$isShow));
                WatchFaceLayoutViewModel.this.getSaveEnableDisable().postValue(Boxing.boxBoolean(this.$isShow));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WatchFaceLayoutViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6159a = context;
        this.b = WatchFaceLayoutViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f6159a;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSaveEnableDisable() {
        return this.c;
    }

    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final OnFailureListener getViewModelOnFailureListener() {
        OnFailureListener onFailureListener = this.viewModelOnFailureListener;
        if (onFailureListener != null) {
            return onFailureListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelOnFailureListener");
        return null;
    }

    @NotNull
    public final OnSuccessListener getViewModelOnSuccessListener() {
        OnSuccessListener onSuccessListener = this.viewModelOnSuccessListener;
        if (onSuccessListener != null) {
            return onSuccessListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelOnSuccessListener");
        return null;
    }

    @NotNull
    public final MutableLiveData<Boolean> getWatchFaceLayoutFetchObserver() {
        return this.f;
    }

    public final void getWatchFaceLayoutFromWatch() {
        if (BleApiManager.getInstance(this.f6159a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            return;
        }
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceLayoutViewModel$getWatchFaceLayoutFromWatch$1(this, new GetCustomWatchFaceLayoutRequest(), null), 2, null);
    }

    @Nullable
    public final WatchFaceLayoutInfo getWatchFaceLayoutInfo() {
        return this.d;
    }

    @Nullable
    public final WatchFaceLayoutInfo getWatchFaceLayoutInfoFromWatch() {
        return this.e;
    }

    public final void sendWatchFaceLayoutToWatch() {
        WatchFaceLayoutInfo watchFaceLayoutInfo;
        if (BleApiManager.getInstance(this.f6159a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED || (watchFaceLayoutInfo = this.d) == null) {
            return;
        }
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1(watchFaceLayoutInfo, this, null), 2, null);
    }

    public final void setSaveEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setViewModelOnFailureListener(@NotNull OnFailureListener onFailureListener) {
        Intrinsics.checkNotNullParameter(onFailureListener, "<set-?>");
        this.viewModelOnFailureListener = onFailureListener;
    }

    public final void setViewModelOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelOnSuccessListener = onSuccessListener;
    }

    public final void setWatchFaceLayoutFetchObserver(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setWatchFaceLayoutInfo(@Nullable WatchFaceLayoutInfo watchFaceLayoutInfo) {
        this.d = watchFaceLayoutInfo;
    }

    public final void setWatchFaceLayoutInfoFromWatch(@Nullable WatchFaceLayoutInfo watchFaceLayoutInfo) {
        this.e = watchFaceLayoutInfo;
    }

    public final void showSaveBtn(boolean z) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(z, null), 2, null);
    }
}
