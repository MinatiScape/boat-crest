package com.coveiot.android.leonardo.more.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SetSportsPushRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2", f = "AdditionalActivitiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SetSportsPushRequest $request;
    public int label;
    public final /* synthetic */ AdditionalActivitiesViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2(AdditionalActivitiesViewModel additionalActivitiesViewModel, SetSportsPushRequest setSportsPushRequest, Continuation<? super AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2> continuation) {
        super(2, continuation);
        this.this$0 = additionalActivitiesViewModel;
        this.$request = setSportsPushRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2(this.this$0, this.$request, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            SetSportsPushRequest setSportsPushRequest = this.$request;
            final AdditionalActivitiesViewModel additionalActivitiesViewModel = this.this$0;
            bleApi.getData(setSportsPushRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2.1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$onDataError$1", f = "AdditionalActivitiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$a */
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ AdditionalActivitiesViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(AdditionalActivitiesViewModel additionalActivitiesViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = additionalActivitiesViewModel;
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
                            if (!value.getVisible()) {
                                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value2);
                                value2.setTitle(this.this$0.getContext().getString(R.string.uploading_activity));
                                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value3);
                                value3.setVisible(false);
                                ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value4);
                                value4.setProgress(0);
                                MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                                ProgressBean value5 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value5);
                                progressLiveData.postValue(value5);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$onDataResponse$1", f = "AdditionalActivitiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$b */
                /* loaded from: classes5.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ AdditionalActivitiesViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(AdditionalActivitiesViewModel additionalActivitiesViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = additionalActivitiesViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, continuation);
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
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.uploading_activity));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setVisible(false);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setProgress(100);
                            MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                            ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value4);
                            progressLiveData.postValue(value4);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$onProgressUpdate$1", f = "AdditionalActivitiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel$pushAdditionalActivityToWatch$2$1$c */
                /* loaded from: classes5.dex */
                public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProgressData $progress;
                    public int label;
                    public final /* synthetic */ AdditionalActivitiesViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public c(AdditionalActivitiesViewModel additionalActivitiesViewModel, ProgressData progressData, Continuation<? super c> continuation) {
                        super(2, continuation);
                        this.this$0 = additionalActivitiesViewModel;
                        this.$progress = progressData;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new c(this.this$0, this.$progress, continuation);
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
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.uploading_activity));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setVisible(true);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setProgress(this.$progress.getProgress());
                            MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                            ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value4);
                            progressLiveData.postValue(value4);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = AdditionalActivitiesViewModel.this.getTAG();
                    LogHelper.d(tag, "pushAdditionalActivityToWatch onDataError is " + error.getErrorMsg());
                    AdditionalActivitiesViewModel.this.getAdditionalActivityPushListener().onFailure(error.getErrorMsg());
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(AdditionalActivitiesViewModel.this), Dispatchers.getMain(), null, new a(AdditionalActivitiesViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(AdditionalActivitiesViewModel.this), Dispatchers.getMain(), null, new b(AdditionalActivitiesViewModel.this, null), 2, null);
                    AdditionalActivitiesViewModel.this.getAdditionalActivityPushListener().onSuccess();
                    String tag = AdditionalActivitiesViewModel.this.getTAG();
                    LogHelper.d(tag, "pushAdditionalActivityToWatch onDataResponse " + response.getResponseData());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(AdditionalActivitiesViewModel.this), Dispatchers.getMain(), null, new c(AdditionalActivitiesViewModel.this, progress, null), 2, null);
                    String tag = AdditionalActivitiesViewModel.this.getTAG();
                    LogHelper.d(tag, "progress is " + progress.getProgress());
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
