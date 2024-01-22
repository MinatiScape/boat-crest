package com.coveiot.android.watchfaceui.viewmodel;

import android.app.Activity;
import android.content.Context;
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
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.watchfacecore.datasource.WatchFacePagingSourceCloud;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.Constants;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.model.Categories;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.coveaccess.watchface.model.WatchFaceCategoriesResponse;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
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
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFaceCloudViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6150a;
    public final String b;
    @Nullable
    public Flow<PagingData<WatchFaceBean>> c;
    @NotNull
    public MutableLiveData<Boolean> d;
    @Nullable
    public WatchFaceBean e;
    @NotNull
    public MutableLiveData<ProgressBean> f;
    @NotNull
    public MutableLiveData<Boolean> g;
    @NotNull
    public final MutableLiveData<List<Categories>> h;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel$getAndDeleteOlderCloudWatchFace$1", f = "WatchFaceCloudViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ProgressBean value = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                value.setTitle(WatchFaceCloudViewModel.this.getContext().getString(R.string.updating_watchface));
                ProgressBean value2 = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setProgress(0);
                ProgressBean value3 = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(true);
                WatchFaceCloudViewModel.this.getProgressLiveData().postValue(WatchFaceCloudViewModel.this.getProgressLiveData().getValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes8.dex */
    public static final class b extends Lambda implements Function0<PagingSource<Integer, WatchFaceBean>> {
        public final /* synthetic */ String $categoryId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str) {
            super(0);
            this.$categoryId = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PagingSource<Integer, WatchFaceBean> invoke() {
            Context context = WatchFaceCloudViewModel.this.getContext();
            String firmwareRevision = ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(WatchFaceCloudViewModel.this.getContext()).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision();
            Intrinsics.checkNotNullExpressionValue(firmwareRevision, "Gson().fromJson(\n       …       ).firmwareRevision");
            return new WatchFacePagingSourceCloud(context, firmwareRevision, this.$categoryId, null, Constants.WATCH_FACE_TYPE_CLOUD);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel$sendWatchFaceToWatch$1", f = "WatchFaceCloudViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ProgressBean value = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                value.setTitle(WatchFaceCloudViewModel.this.getContext().getString(R.string.updating_watchface));
                ProgressBean value2 = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setProgress(0);
                ProgressBean value3 = WatchFaceCloudViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(true);
                WatchFaceCloudViewModel.this.getProgressLiveData().postValue(WatchFaceCloudViewModel.this.getProgressLiveData().getValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel$sendWatchFaceToWatch$3", f = "WatchFaceCloudViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceCloudViewModel.this.getViewModelOnFailureListener().onFailure(WatchFaceCloudViewModel.this.getContext().getString(R.string.band_not_connected));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WatchFaceCloudViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6150a = context;
        this.b = WatchFaceCloudViewModel.class.getSimpleName();
        this.d = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.f.setValue(new ProgressBean(null, 0, false));
    }

    public static /* synthetic */ void getCloudWatchFaceLists$default(WatchFaceCloudViewModel watchFaceCloudViewModel, Activity activity, SuccessResultListener successResultListener, int i, Object obj) {
        if ((i & 2) != 0) {
            successResultListener = null;
        }
        watchFaceCloudViewModel.getCloudWatchFaceLists(activity, successResultListener);
    }

    public final void a(String str, WatchFaceBean watchFaceBean) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceCloudViewModel$getAndDeleteOlderCloudWatchFace$2(this, str, watchFaceBean, null), 2, null);
    }

    public final Flow<PagingData<WatchFaceBean>> c(String str) {
        LogHelper.d(this.b, "getPullWatchFaceResultStream");
        return new Pager(new PagingConfig(20, 0, false, 0, 0, 0, 58, null), null, new b(str), 2, null).getFlow();
    }

    public final void d(String str, WatchFaceBean watchFaceBean) {
        int i;
        if (BleApiManager.getInstance(this.f6150a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
            return;
        }
        if (this.e != null) {
            ProgressBean value = this.f.getValue();
            Intrinsics.checkNotNull(value);
            if (!value.getVisible()) {
                e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
                boolean z = true;
                if (watchFaceBean != null) {
                    String faceId = watchFaceBean.getFaceId();
                    if (!(faceId == null || faceId.length() == 0)) {
                        try {
                            i = Integer.parseInt(watchFaceBean.getFaceId(), kotlin.text.a.checkRadix(16));
                        } catch (NumberFormatException e) {
                            e.getStackTrace();
                        }
                        CustomWatchFaceFileImageRequest.Builder watchFaceID = new CustomWatchFaceFileImageRequest.Builder().setWatchFaceFilePath(str).setWatchFaceID(i);
                        if (DeviceUtils.Companion.isPS1Device(this.f6150a) || !m.endsWith$default(str, OTAManager.OTA_ZIP_SUFFIX, false, 2, null)) {
                            z = false;
                        }
                        CustomWatchFaceFileImageRequest build = watchFaceID.setShouldUseSDK(z).build();
                        LogHelper.d("isSending Via SDK", String.valueOf(build.isShouldUseSdk()));
                        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceCloudViewModel$sendWatchFaceToWatch$2(this, build, null), 2, null);
                        return;
                    }
                }
                i = 0;
                CustomWatchFaceFileImageRequest.Builder watchFaceID2 = new CustomWatchFaceFileImageRequest.Builder().setWatchFaceFilePath(str).setWatchFaceID(i);
                if (DeviceUtils.Companion.isPS1Device(this.f6150a)) {
                }
                z = false;
                CustomWatchFaceFileImageRequest build2 = watchFaceID2.setShouldUseSDK(z).build();
                LogHelper.d("isSending Via SDK", String.valueOf(build2.isShouldUseSdk()));
                e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceCloudViewModel$sendWatchFaceToWatch$2(this, build2, null), 2, null);
                return;
            }
        }
        getViewModelOnFailureListener().onFailure("");
    }

    public final void downloadWatchFaceFromServerSendToWatch(@NotNull String fileName, @NotNull String sUrl, @Nullable WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(sUrl, "sUrl");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceCloudViewModel$downloadWatchFaceFromServerSendToWatch$1(this, fileName, sUrl, watchFaceBean, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<List<Categories>> getCategoryTitle() {
        return this.h;
    }

    public final void getCloudWatchFaceLists(@NotNull final Activity context, @Nullable final SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        WatchFaceApiManager.getWatchFaceList(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel$getCloudWatchFaceLists$1
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
                    SessionManager.getInstance(context).saveCloudWatchFaceItems(sWatchFaceList.getData().getTotalItems());
                    if (sWatchFaceList.getData().getNewItems() != null) {
                        SessionManager.getInstance(context).saveCloudNewWatchFaceItems(sWatchFaceList.getData().getNewItems());
                    }
                }
                SuccessResultListener successResultListener2 = successResultListener;
                if (successResultListener2 != null) {
                    successResultListener2.onSuccess();
                }
            }
        }, null, Constants.WATCH_FACE_TYPE_CLOUD, ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision(), 0, 20);
    }

    @NotNull
    public final Context getContext() {
        return this.f6150a;
    }

    @NotNull
    public final MutableLiveData<ProgressBean> getProgressLiveData() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSaveEnableDisable() {
        return this.g;
    }

    public final String getTAG() {
        return this.b;
    }

    @Nullable
    public final WatchFaceBean getUserSelectedWatchFace() {
        return this.e;
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
    public final MutableLiveData<Boolean> isUploadToWatchCanceled() {
        return this.d;
    }

    public final void onFail() {
        getViewModelOnFailureListener().onFailure("");
    }

    @NotNull
    public final Flow<PagingData<WatchFaceBean>> pullWatchFaces(@Nullable String str) {
        Flow<PagingData<WatchFaceBean>> cachedIn = CachedPagingDataKt.cachedIn(c(str), ViewModelKt.getViewModelScope(this));
        this.c = cachedIn;
        return cachedIn;
    }

    public final void setProgressLiveData(@NotNull MutableLiveData<ProgressBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setSaveEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setUploadToWatchCanceled(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setUserSelectedWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.e = watchFaceBean;
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
        this.g.setValue(Boolean.valueOf(z));
        this.g.postValue(Boolean.valueOf(z));
    }

    public final void watchFaceCategory() {
        WatchFaceApiManager.getWatchFacesCategoryList(new CoveApiListener<WatchFaceCategoriesResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel$watchFaceCategory$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull WatchFaceCategoriesResponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                List<WatchFaceCategoriesResponse.DataBean.ItemsList> itemsLists = object.getData().getItemsLists();
                ArrayList arrayList = new ArrayList();
                int size = itemsLists.size();
                for (int i = 0; i < size; i++) {
                    Categories categories = new Categories(null, null, false, 7, null);
                    categories.setCategoryId(itemsLists.get(i).getCategoryId());
                    categories.setTitle(itemsLists.get(i).getTitle());
                    if (itemsLists.get(i).getNewCategory() != null) {
                        Boolean newCategory = itemsLists.get(i).getNewCategory();
                        Intrinsics.checkNotNullExpressionValue(newCategory, "list[i].newCategory");
                        categories.setNewCategory(newCategory.booleanValue());
                    } else {
                        categories.setNewCategory(false);
                    }
                    arrayList.add(categories);
                    LogHelper.d(WatchFaceCloudViewModel.this.getTAG(), itemsLists.get(i).getCategoryId());
                    LogHelper.d(WatchFaceCloudViewModel.this.getTAG(), itemsLists.get(i).getTitle());
                }
                WatchFaceCloudViewModel.this.getCategoryTitle().postValue(arrayList);
                LogHelper.d(WatchFaceCloudViewModel.this.getTAG(), object.toString());
            }
        }, Constants.WATCH_FACE_TYPE_CLOUD);
    }
}
