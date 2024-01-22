package com.coveiot.android.dashboard2.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.smartgrid.CoveSmartGridApi;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
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
/* loaded from: classes4.dex */
public final class SmartGridViewModel extends AndroidViewModel {
    @NotNull
    public final SingleLiveEvent<GetSmartGridRes> d;
    @NotNull
    public final MutableLiveData<Boolean> e;
    @NotNull
    public Context f;

    /* loaded from: classes4.dex */
    public interface BestOffersListeners {
        void onFailure(@Nullable String str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartGridViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = new SingleLiveEvent<>();
        this.e = new MutableLiveData<>();
        this.f = application;
    }

    public static final void d(SmartGridViewModel this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.e.setValue(Boolean.FALSE);
        LogHelper.e("smartGridVisibility", "Remote Config Failed");
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final SmartGridViewModel this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.m
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    SmartGridViewModel.f(FirebaseRemoteConfig.this, this$0, task2);
                }
            });
            return;
        }
        this$0.e.setValue(Boolean.FALSE);
        LogHelper.e("smartGridVisibility", "Remote Config Failed");
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, SmartGridViewModel this$0, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.SMART_GRID_VISIBILITY.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…RT_GRID_VISIBILITY.value)");
        this$0.e.setValue(Boolean.valueOf(Boolean.parseBoolean(string)));
        LogHelper.d("smartGridVisibility", "Config " + Boolean.parseBoolean(string));
    }

    public final void getSmartGridVisibilityConfig() {
        if (AppUtils.isNetConnected(this.f)) {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.dashboard2.viewmodel.n
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SmartGridViewModel.d(SmartGridViewModel.this, exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.viewmodel.l
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    SmartGridViewModel.e(FirebaseRemoteConfig.this, this, task);
                }
            });
            return;
        }
        Context context = this.f;
        String string = context.getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.str…g.no_internet_connection)");
        ViewUtilsKt.toast(context, string);
    }

    @NotNull
    public final SingleLiveEvent<GetSmartGridRes> getTopFeatureList() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> isSmartGridVisible() {
        return this.e;
    }

    /* renamed from: getTopFeatureList  reason: collision with other method in class */
    public final void m104getTopFeatureList() {
        String firmwareRevision;
        String str = null;
        if (AppUtils.isNetConnected(this.f)) {
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this.f).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            if (bleDeviceInfo != null && (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) != null) {
                str = firmwareRevision.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
            }
            CoveSmartGridApi.getSmartGridItems(str, new CoveApiListener<GetSmartGridRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.SmartGridViewModel$getTopFeatureList$1

                @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.SmartGridViewModel$getTopFeatureList$1$onError$1", f = "SmartGridViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ SmartGridViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(SmartGridViewModel smartGridViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = smartGridViewModel;
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
                            this.this$0.getTopFeatureList().postValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.SmartGridViewModel$getTopFeatureList$1$onSuccess$1", f = "SmartGridViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes4.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ GetSmartGridRes $getSmartGridRes;
                    public int label;
                    public final /* synthetic */ SmartGridViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(GetSmartGridRes getSmartGridRes, SmartGridViewModel smartGridViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$getSmartGridRes = getSmartGridRes;
                        this.this$0 = smartGridViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.$getSmartGridRes, this.this$0, continuation);
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
                            if (this.$getSmartGridRes.getGridItems() != null) {
                                this.this$0.getTopFeatureList().postValue(this.$getSmartGridRes);
                            } else {
                                this.this$0.getTopFeatureList().postValue(null);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(SmartGridViewModel.this), Dispatchers.getMain(), null, new a(SmartGridViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull GetSmartGridRes getSmartGridRes) {
                    Intrinsics.checkNotNullParameter(getSmartGridRes, "getSmartGridRes");
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(SmartGridViewModel.this), Dispatchers.getMain(), null, new b(getSmartGridRes, SmartGridViewModel.this, null), 2, null);
                }
            });
            return;
        }
        Context context = this.f;
        String string = context.getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.str…g.no_internet_connection)");
        ViewUtilsKt.toast(context, string);
        this.d.postValue(null);
    }
}
