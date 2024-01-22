package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.GetWatchFacePositionRequest;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CurrentWatchFaceBean;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.util.Calendar;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityWatchFaceViewModel extends ViewModel {
    public static final int BACKGROUND = 2;
    public static final int CLOUD = 1;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT = 0;
    public static final int DIY = 2;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6145a;
    public final String b;
    public int c;
    public int d;
    @NotNull
    public final MutableLiveData<Boolean> e;
    @NotNull
    public final MutableLiveData<Boolean> f;
    @NotNull
    public final MutableLiveData<Boolean> g;
    @NotNull
    public final MutableLiveData<Boolean> h;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ActivityWatchFaceViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6145a = context;
        this.b = ActivityWatchFaceViewModel.class.getSimpleName();
        this.d = -1;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        Boolean bool = Boolean.TRUE;
        mutableLiveData.postValue(bool);
        this.e = mutableLiveData;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>();
        mutableLiveData2.postValue(bool);
        this.f = mutableLiveData2;
        MutableLiveData<Boolean> mutableLiveData3 = new MutableLiveData<>();
        Boolean bool2 = Boolean.FALSE;
        mutableLiveData3.postValue(bool2);
        this.g = mutableLiveData3;
        MutableLiveData<Boolean> mutableLiveData4 = new MutableLiveData<>();
        mutableLiveData4.postValue(bool2);
        this.h = mutableLiveData4;
    }

    public static /* synthetic */ void callDeviceSpecificSettingsApi$default(ActivityWatchFaceViewModel activityWatchFaceViewModel, WatchFaceBean watchFaceBean, int i, Object obj) {
        if ((i & 1) != 0) {
            watchFaceBean = null;
        }
        activityWatchFaceViewModel.callDeviceSpecificSettingsApi(watchFaceBean);
    }

    public final void callDeviceSpecificSettingsApi(@Nullable WatchFaceBean watchFaceBean) {
        if (AppUtils.isNetConnected(this.f6145a)) {
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f6145a).isLiftWristToViewEnable();
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            if (watchFaceBean != null) {
                CurrentWatchFaceBean currentWatchFaceBean = new CurrentWatchFaceBean();
                currentWatchFaceBean.setFaceId(watchFaceBean.getFaceId());
                currentWatchFaceBean.setUid(watchFaceBean.getUid());
                currentWatchFaceBean.setFaceType(watchFaceBean.getFaceType());
                currentWatchFaceBean.setAppliedDate(AppUtils.formatDateUTC(Calendar.getInstance().getTime(), UtilConstants.SERVER_TIME_FORMAT));
                saveDeviceSpecificParamsReq.setCurrentWatchFace(currentWatchFaceBean);
            }
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            saveDeviceSpecificParamsReq.setLiftWristToView(isLiftWristEnabled.booleanValue());
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$callDeviceSpecificSettingsApi$1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$callDeviceSpecificSettingsApi$1$onSuccess$1", f = "ActivityWatchFaceViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ActivityWatchFaceViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityWatchFaceViewModel activityWatchFaceViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = activityWatchFaceViewModel;
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
                            this.this$0.getViewModelOnSuccessListener().onSuccess(true);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ActivityWatchFaceViewModel.this.getViewModelOnFailureListener().onFailure(ActivityWatchFaceViewModel.this.getContext().getResources().getString(R.string.failure_message));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    e.e(ViewModelKt.getViewModelScope(ActivityWatchFaceViewModel.this), Dispatchers.getMain(), null, new a(ActivityWatchFaceViewModel.this, null), 2, null);
                }
            });
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6145a.getResources().getString(R.string.please_check_your_internet));
    }

    public final void callDeviceSpecificSettingsApiDiy() {
        if (AppUtils.isNetConnected(this.f6145a)) {
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f6145a).isLiftWristToViewEnable();
            WatchFaceBean selectedDiyWatchFace = WatchFacePreferenceManager.Companion.getInstance(this.f6145a).getSelectedDiyWatchFace();
            SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq = new SaveDeviceSpecificParamsReq();
            if (selectedDiyWatchFace != null) {
                CurrentWatchFaceBean currentWatchFaceBean = new CurrentWatchFaceBean();
                currentWatchFaceBean.setFaceId(selectedDiyWatchFace.getFaceId());
                currentWatchFaceBean.setUid(selectedDiyWatchFace.getUid());
                currentWatchFaceBean.setFaceType(selectedDiyWatchFace.getFaceType());
                currentWatchFaceBean.setAppliedDate(AppUtils.formatDateUTC(Calendar.getInstance().getTime(), UtilConstants.SERVER_TIME_FORMAT));
                saveDeviceSpecificParamsReq.setCurrentWatchFace(currentWatchFaceBean);
            }
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            saveDeviceSpecificParamsReq.setLiftWristToView(isLiftWristEnabled.booleanValue());
            CoveUserAppSettings.saveDeviceSpecificParameters(saveDeviceSpecificParamsReq, new CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$callDeviceSpecificSettingsApiDiy$1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel$callDeviceSpecificSettingsApiDiy$1$onSuccess$1", f = "ActivityWatchFaceViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ActivityWatchFaceViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityWatchFaceViewModel activityWatchFaceViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = activityWatchFaceViewModel;
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
                            this.this$0.getViewModelOnSuccessListener().onSuccess(true);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ActivityWatchFaceViewModel.this.getViewModelOnFailureListener().onFailure(ActivityWatchFaceViewModel.this.getContext().getResources().getString(R.string.failure_message));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDeviceSpecificParamsRes saveDeviceSpecificParamsRes) {
                    e.e(ViewModelKt.getViewModelScope(ActivityWatchFaceViewModel.this), Dispatchers.getMain(), null, new a(ActivityWatchFaceViewModel.this, null), 2, null);
                }
            });
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6145a.getResources().getString(R.string.please_check_your_internet));
    }

    @NotNull
    public final Context getContext() {
        return this.f6145a;
    }

    public final void getDisplayWatchFacePositionFromWatch() {
        if (BleApiManager.getInstance(this.f6145a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ActivityWatchFaceViewModel$getDisplayWatchFacePositionFromWatch$1(this, new GetWatchFacePositionRequest(), null), 2, null);
        }
    }

    public final int getDisplayedWatchFacePositionFromWatch() {
        return this.d;
    }

    @Nullable
    public final LiveData<Boolean> getIsBackgroundWatchFaceSelected() {
        return this.g;
    }

    @Nullable
    public final LiveData<Boolean> getIsCloudWatchFaceSelected() {
        return this.f;
    }

    @Nullable
    public final LiveData<Boolean> getIsDiyWatchFaceSelected() {
        return this.e;
    }

    @Nullable
    public final LiveData<Boolean> getIsShow() {
        return this.h;
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

    public final int getWatchFacePushType() {
        return this.c;
    }

    public final void isBackgroundWatchFaceSelected(boolean z) {
        this.g.setValue(Boolean.valueOf(z));
    }

    public final void isCloudwatchFaceSelected(boolean z) {
        this.f.setValue(Boolean.valueOf(z));
    }

    public final void isDiyWatchFaceSelected(boolean z) {
        this.e.setValue(Boolean.valueOf(z));
    }

    public final void isShowSave(boolean z) {
        this.h.setValue(Boolean.valueOf(z));
    }

    public final void setDisplayedWatchFacePositionFromWatch(int i) {
        this.d = i;
    }

    public final void setViewModelOnFailureListener(@NotNull OnFailureListener onFailureListener) {
        Intrinsics.checkNotNullParameter(onFailureListener, "<set-?>");
        this.viewModelOnFailureListener = onFailureListener;
    }

    public final void setViewModelOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelOnSuccessListener = onSuccessListener;
    }

    public final void setWatchFacePushType(int i) {
        this.c = i;
    }
}
