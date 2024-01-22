package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.CachedPagingDataKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingSource;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.ChangeWatchFaceRequest;
import com.coveiot.android.bleabstract.request.SetWatchFaceImageIdRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.watchfacecore.datasource.WatchFacePagingSource;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFaceDefaultViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6154a;
    public final String b;
    @Nullable
    public Flow<PagingData<WatchFaceBean>> c;
    @Nullable
    public Integer d;
    @NotNull
    public MutableLiveData<Boolean> e;
    @NotNull
    public MutableLiveData<Integer> f;
    @Nullable
    public WatchFaceBean g;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function0<PagingSource<Integer, WatchFaceBean>> {
        public final /* synthetic */ String $it;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str) {
            super(0);
            this.$it = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PagingSource<Integer, WatchFaceBean> invoke() {
            Context context = WatchFaceDefaultViewModel.this.getContext();
            String str = this.$it;
            Intrinsics.checkNotNull(str);
            return new WatchFacePagingSource(context, str, null, Constants.WATCH_FACE_TYPE_DEFAULT);
        }
    }

    public WatchFaceDefaultViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6154a = context;
        this.b = WatchFaceDefaultViewModel.class.getSimpleName();
        this.d = -1;
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
    }

    public final void changeDefaultWatchFace() {
        if (BleApiManager.getInstance(this.f6154a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            ChangeWatchFaceRequest.Builder builder = new ChangeWatchFaceRequest.Builder();
            Integer num = this.d;
            Intrinsics.checkNotNull(num);
            ChangeWatchFaceRequest changeWatchFaceRequest = builder.setWatchFacePosition(num.intValue()).build();
            showSaveBtn(false);
            BleApi bleApi = BleApiManager.getInstance(this.f6154a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(changeWatchFaceRequest, "changeWatchFaceRequest");
            bleApi.setUserSettings(changeWatchFaceRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel$changeDefaultWatchFace$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    WatchFaceDefaultViewModel.this.getViewModelOnFailureListener().onFailure(WatchFaceDefaultViewModel.this.getContext().getString(R.string.failed_message));
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    Utils utils = Utils.INSTANCE;
                    Context context = WatchFaceDefaultViewModel.this.getContext();
                    Intrinsics.checkNotNull(context);
                    if (!utils.isCZDevice(context)) {
                        Context context2 = WatchFaceDefaultViewModel.this.getContext();
                        Intrinsics.checkNotNull(context2);
                        if (!Utils.isCYDevice(context2)) {
                            if (WatchFaceDefaultViewModel.this.getWatchFaceBean() != null) {
                                WatchFacePreferenceManager.Companion.getInstance(WatchFaceDefaultViewModel.this.getContext()).saveSelectedDefaultWatchFace(WatchFaceDefaultViewModel.this.getWatchFaceBean());
                            }
                            WatchFaceDefaultViewModel.this.getViewModelOnSuccessListener().onSuccess(true);
                            return;
                        }
                    }
                    BleApi bleApi2 = BleApiManager.getInstance(WatchFaceDefaultViewModel.this.getContext()).getBleApi();
                    Integer defaultWatchFacePosition = WatchFaceDefaultViewModel.this.getDefaultWatchFacePosition();
                    Intrinsics.checkNotNull(defaultWatchFacePosition);
                    SetWatchFaceImageIdRequest setWatchFaceImageIdRequest = new SetWatchFaceImageIdRequest(65535, defaultWatchFacePosition.intValue());
                    final WatchFaceDefaultViewModel watchFaceDefaultViewModel = WatchFaceDefaultViewModel.this;
                    bleApi2.setUserSettings(setWatchFaceImageIdRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel$changeDefaultWatchFace$1$onSettingsResponse$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            WatchFaceDefaultViewModel.this.getViewModelOnFailureListener().onFailure(WatchFaceDefaultViewModel.this.getContext().getString(R.string.failed_message));
                            WatchFaceDefaultViewModel.this.showSaveBtn(true);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                            Intrinsics.checkNotNullParameter(response2, "response");
                            if (WatchFaceDefaultViewModel.this.getWatchFaceBean() != null) {
                                WatchFacePreferenceManager.Companion.getInstance(WatchFaceDefaultViewModel.this.getContext()).saveSelectedDefaultWatchFace(WatchFaceDefaultViewModel.this.getWatchFaceBean());
                            }
                            WatchFaceDefaultViewModel.this.getViewModelOnSuccessListener().onDefaultWatchFaceSuccess(true);
                        }
                    });
                }
            });
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6154a.getString(R.string.band_not_connected));
    }

    @NotNull
    public final Context getContext() {
        return this.f6154a;
    }

    @Nullable
    public final Integer getDefaultWatchFacePosition() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSaveEnableDisable() {
        return this.e;
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

    @Nullable
    public final WatchFaceBean getWatchFaceBean() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<Integer> getWatchFacePositionLiveData() {
        return this.f;
    }

    public final Flow<PagingData<WatchFaceBean>> h() {
        String firmwareRevision;
        LogHelper.d(this.b, "getPullWatchFaceResultStream");
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this.f6154a).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo == null || (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) == null) {
            return null;
        }
        return new Pager(new PagingConfig(20, 0, false, 0, 0, 0, 58, null), null, new a(firmwareRevision), 2, null).getFlow();
    }

    public final void onFail() {
        getViewModelOnFailureListener().onFailure("");
    }

    @Nullable
    public final Flow<PagingData<WatchFaceBean>> pullWatchFaces() {
        Flow<PagingData<WatchFaceBean>> h = h();
        Flow<PagingData<WatchFaceBean>> cachedIn = h != null ? CachedPagingDataKt.cachedIn(h, ViewModelKt.getViewModelScope(this)) : null;
        this.c = cachedIn;
        return cachedIn;
    }

    public final void setDefaultWatchFacePosition(@Nullable Integer num) {
        this.d = num;
    }

    public final void setSaveEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setViewModelOnFailureListener(@NotNull OnFailureListener onFailureListener) {
        Intrinsics.checkNotNullParameter(onFailureListener, "<set-?>");
        this.viewModelOnFailureListener = onFailureListener;
    }

    public final void setViewModelOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelOnSuccessListener = onSuccessListener;
    }

    public final void setWatchFaceBean(@Nullable WatchFaceBean watchFaceBean) {
        this.g = watchFaceBean;
    }

    public final void setWatchFacePositionLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void showSaveBtn(boolean z) {
        this.e.setValue(Boolean.valueOf(z));
        this.e.postValue(Boolean.valueOf(z));
    }
}
