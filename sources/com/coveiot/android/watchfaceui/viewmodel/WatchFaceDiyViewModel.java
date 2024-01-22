package com.coveiot.android.watchfaceui.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.CachedPagingDataKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingSource;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.model.server.WatchFaceRequest;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFaceDiyViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6155a;
    public final String b;
    @Nullable
    public Flow<PagingData<WatchFaceBean>> c;
    @NotNull
    public MutableLiveData<Boolean> d;
    @NotNull
    public MutableLiveData<Boolean> e;
    @Nullable
    public WatchFaceBean f;
    @NotNull
    public MutableLiveData<ProgressBean> g;
    @NotNull
    public MutableLiveData<Boolean> h;
    @NotNull
    public MutableLiveData<Boolean> i;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function0<PagingSource<Integer, WatchFaceBean>> {
        public a() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PagingSource<Integer, WatchFaceBean> invoke() {
            Context context = WatchFaceDiyViewModel.this.getContext();
            String firmwareRevision = ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(WatchFaceDiyViewModel.this.getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision();
            Intrinsics.checkNotNullExpressionValue(firmwareRevision, "Gson().fromJson(\n       …       ).firmwareRevision");
            return new WatchFacePagingSource(context, firmwareRevision, null, "DIY");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
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
                ProgressBean value = WatchFaceDiyViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                value.setTitle(WatchFaceDiyViewModel.this.getContext().getString(R.string.updating_watchface));
                ProgressBean value2 = WatchFaceDiyViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setProgress(0);
                ProgressBean value3 = WatchFaceDiyViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(true);
                WatchFaceDiyViewModel.this.getProgressLiveData().postValue(WatchFaceDiyViewModel.this.getProgressLiveData().getValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$3", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceDiyViewModel.this.getViewModelOnFailureListener().onFailure(WatchFaceDiyViewModel.this.getContext().getString(R.string.band_not_connected));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WatchFaceDiyViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6155a = context;
        this.b = WatchFaceDiyViewModel.class.getSimpleName();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.g.setValue(new ProgressBean(null, 0, false));
    }

    public static /* synthetic */ void getDiyWatchFaceLists$default(WatchFaceDiyViewModel watchFaceDiyViewModel, Activity activity, SuccessResultListener successResultListener, int i, Object obj) {
        if ((i & 2) != 0) {
            successResultListener = null;
        }
        watchFaceDiyViewModel.getDiyWatchFaceLists(activity, successResultListener);
    }

    public final void deleteWatchFaces(@NotNull List<WatchFaceBean> watchfaces) {
        Intrinsics.checkNotNullParameter(watchfaces, "watchfaces");
        ArrayList arrayList = new ArrayList();
        int size = watchfaces.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(watchfaces.get(i).getUid());
        }
        WatchFaceRequest watchFaceRequest = new WatchFaceRequest();
        watchFaceRequest.setUids(arrayList);
        CoveSocial.deleteWatchFace(watchFaceRequest, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$deleteWatchFaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Toast.makeText(WatchFaceDiyViewModel.this.getContext(), WatchFaceDiyViewModel.this.getContext().getString(R.string.failed_message), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull CommonResponseClass object) {
                Intrinsics.checkNotNullParameter(object, "object");
                LogHelper.d("Watchafce", "successfullydeleted");
                WatchFaceDiyViewModel.this.isDeleteWatchfaceSuccess().postValue(Boolean.TRUE);
            }
        });
    }

    public final void downloadWatchFaceFromServerSendToWatch(@NotNull String fileName, @NotNull String sUrl, @Nullable WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(sUrl, "sUrl");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1(this, fileName, sUrl, watchFaceBean, null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f6155a;
    }

    public final void getDiyWatchFaceLists(@NotNull final Activity context, @Nullable final SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        WatchFaceApiManager.getWatchFaceList(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$getDiyWatchFaceLists$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                SuccessResultListener successResultListener2 = successResultListener;
                if (successResultListener2 != null) {
                    successResultListener2.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWatchFaceList sWatchFaceList) {
                if (sWatchFaceList != null && sWatchFaceList.getData() != null && sWatchFaceList.getData().getTotalItems() != null) {
                    SessionManager.getInstance(context).saveDiyWatchFaceListItems(sWatchFaceList.getData().getTotalItems());
                }
                SuccessResultListener successResultListener2 = successResultListener;
                if (successResultListener2 != null) {
                    successResultListener2.onSuccess();
                }
            }
        }, null, "DIY", ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision(), 0, 20);
    }

    @NotNull
    public final MutableLiveData<Boolean> getEditEnableDisable() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<ProgressBean> getProgressLiveData() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSaveEnableDisable() {
        return this.h;
    }

    public final String getTAG() {
        return this.b;
    }

    @Nullable
    public final WatchFaceBean getUserSelectedWatchFace() {
        return this.f;
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

    public final Flow<PagingData<WatchFaceBean>> h() {
        return new Pager(new PagingConfig(20, 0, false, 0, 0, 0, 58, null), null, new a(), 2, null).getFlow();
    }

    public final void i(String str, WatchFaceBean watchFaceBean) {
        if (BleApiManager.getInstance(this.f6155a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
        } else if (this.f != null) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
            int i = 0;
            if (watchFaceBean != null) {
                String faceId = watchFaceBean.getFaceId();
                if (!(faceId == null || faceId.length() == 0)) {
                    try {
                        i = Integer.parseInt(watchFaceBean.getFaceId(), kotlin.text.a.checkRadix(16));
                    } catch (NumberFormatException e) {
                        e.getStackTrace();
                    }
                }
            }
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceDiyViewModel$sendWatchFaceToWatch$2(this, new CustomWatchFaceFileImageRequest.Builder().setWatchFaceFilePath(str).setWatchFaceID(i).build(), null), 2, null);
        } else {
            getViewModelOnFailureListener().onFailure("");
        }
    }

    @NotNull
    public final MutableLiveData<Boolean> isDeleteWatchfaceSuccess() {
        return this.e;
    }

    public final void isEditEnable(boolean z) {
        this.i.postValue(Boolean.valueOf(z));
    }

    @NotNull
    public final MutableLiveData<Boolean> isUploadToWatchCanceled() {
        return this.d;
    }

    public final void onFail() {
        getViewModelOnFailureListener().onFailure("");
    }

    @NotNull
    public final Flow<PagingData<WatchFaceBean>> pullWatchFaces() {
        Flow<PagingData<WatchFaceBean>> cachedIn = CachedPagingDataKt.cachedIn(h(), ViewModelKt.getViewModelScope(this));
        this.c = cachedIn;
        return cachedIn;
    }

    public final void setDeleteWatchfaceSuccess(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setEditEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setProgressLiveData(@NotNull MutableLiveData<ProgressBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setSaveEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setUploadToWatchCanceled(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setUserSelectedWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.f = watchFaceBean;
    }

    public final void setViewModelOnFailureListener(@NotNull OnFailureListener onFailureListener) {
        Intrinsics.checkNotNullParameter(onFailureListener, "<set-?>");
        this.viewModelOnFailureListener = onFailureListener;
    }

    public final void setViewModelOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelOnSuccessListener = onSuccessListener;
    }

    public final void showSaveBtn(boolean z) {
        this.h.postValue(Boolean.valueOf(z));
    }
}
