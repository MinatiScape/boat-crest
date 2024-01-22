package com.coveiot.android.activitymodes.activity1k.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivityAndCategoryViewModel extends AndroidViewModel {
    @NotNull
    public Context d;
    @NotNull
    public LiveData<List<ActivityCategoriesModel>> e;
    @NotNull
    public LiveData<List<ActivitiesListModel>> f;
    @NotNull
    public final String g;

    /* loaded from: classes2.dex */
    public interface WatchActivitiesListener {
        void onFailure(@NotNull String str);

        void onWatchActivitiesResult(@Nullable List<CategoryAndActivityModel> list);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel$updateActivityList$1", f = "FragmentActivityAndCategoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<CategoryAndActivityModel> $categoryAndActivityList;
        public final /* synthetic */ WatchActivitiesListener $listener;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WatchActivitiesListener watchActivitiesListener, List<CategoryAndActivityModel> list, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$listener = watchActivitiesListener;
            this.$categoryAndActivityList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$listener, this.$categoryAndActivityList, continuation);
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
                this.$listener.onWatchActivitiesResult(this.$categoryAndActivityList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentActivityAndCategoryViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        String simpleName = FragmentActivityAndCategoryViewModel.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.g = simpleName;
        this.d = application;
        this.e = new MutableLiveData();
        this.f = new MutableLiveData();
    }

    public final void a(ConfiguredActivities.ActivityInfo[] activityInfoArr, WatchActivitiesListener watchActivitiesListener) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ConfiguredActivities.ActivityInfo activityInfo : activityInfoArr) {
            Intrinsics.checkNotNull(activityInfo);
            arrayList2.add(Integer.valueOf(activityInfo.getActivityId()));
            arrayList.add(Integer.valueOf(activityInfo.getCategoryId()));
        }
        List<CategoryAndActivityModel> categoryAndActivityListFromDb = getCategoryAndActivityListFromDb(arrayList);
        Intrinsics.checkNotNull(categoryAndActivityListFromDb);
        List<CategoryAndActivityModel> categoryAndActivityListSorted = getCategoryAndActivityListSorted(categoryAndActivityListFromDb, activityInfoArr);
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context context = this.d;
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNull(categoryAndActivityListSorted);
        companion.getInstance(context).setCategoryAndActivityList(categoryAndActivityListSorted);
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(watchActivitiesListener, categoryAndActivityListSorted, null), 2, null);
    }

    @NotNull
    public final LiveData<List<ActivitiesListModel>> getActivityLiveData() {
        return this.f;
    }

    @NotNull
    public final List<CategoryAndActivityModel> getCategoryAndActivityListFromDb(@Nullable List<Integer> list) {
        return PhysicalActivityRepository.Companion.getInstance(this.d).getActivityAndCategoriesWithoutLiveData();
    }

    @Nullable
    public final List<CategoryAndActivityModel> getCategoryAndActivityListSorted(@NotNull List<CategoryAndActivityModel> list, @NotNull ConfiguredActivities.ActivityInfo[] activityInfoList) {
        int i;
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(activityInfoList, "activityInfoList");
        int size = list.size();
        CategoryAndActivityModel[] categoryAndActivityModelArr = new CategoryAndActivityModel[size];
        ArrayList arrayList = new ArrayList();
        Iterator<CategoryAndActivityModel> it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            CategoryAndActivityModel next = it.next();
            int length = activityInfoList.length;
            while (i < length) {
                ConfiguredActivities.ActivityInfo activityInfo = activityInfoList[i];
                Intrinsics.checkNotNull(activityInfo);
                int categoryId = activityInfo.getCategoryId();
                Integer categoryId2 = next.getCategoryId();
                if (categoryId2 != null && categoryId == categoryId2.intValue()) {
                    int activityId = activityInfo.getActivityId();
                    Integer activityId2 = next.getActivityId();
                    if ((activityId2 != null && activityId == activityId2.intValue()) || activityInfo.getActivityId() == next.getFwActId()) {
                        categoryAndActivityModelArr[activityInfo.getOrderId()] = next;
                    }
                }
                i++;
            }
        }
        while (i < size) {
            CategoryAndActivityModel categoryAndActivityModel = categoryAndActivityModelArr[i];
            if (categoryAndActivityModel != null) {
                arrayList.add(categoryAndActivityModel);
            }
            i++;
        }
        return arrayList;
    }

    @NotNull
    public final LiveData<List<ActivityCategoriesModel>> getCategoryLiveData() {
        return this.e;
    }

    public final void getConfiguredActivitiesFromWatch(@NotNull final WatchActivitiesListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (BleApiManager.getInstance(this.d).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.d).getBleApi().getData(new GetConfiguredActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel$getConfiguredActivitiesFromWatch$1

                @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel$getConfiguredActivitiesFromWatch$1$onDataResponse$1", f = "FragmentActivityAndCategoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ConfiguredActivities.ActivityInfo[] $activityInfoList;
                    public final /* synthetic */ FragmentActivityAndCategoryViewModel.WatchActivitiesListener $listener;
                    public int label;
                    public final /* synthetic */ FragmentActivityAndCategoryViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(FragmentActivityAndCategoryViewModel fragmentActivityAndCategoryViewModel, ConfiguredActivities.ActivityInfo[] activityInfoArr, FragmentActivityAndCategoryViewModel.WatchActivitiesListener watchActivitiesListener, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentActivityAndCategoryViewModel;
                        this.$activityInfoList = activityInfoArr;
                        this.$listener = watchActivitiesListener;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$activityInfoList, this.$listener, continuation);
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
                            this.this$0.a(this.$activityInfoList, this.$listener);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    String str;
                    Context context;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = FragmentActivityAndCategoryViewModel.this.g;
                    LogHelper.d(str, error.toString());
                    FragmentActivityAndCategoryViewModel.WatchActivitiesListener watchActivitiesListener = listener;
                    context = FragmentActivityAndCategoryViewModel.this.d;
                    String string = context.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.some_thing_went_wrong)");
                    watchActivitiesListener.onFailure(string);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    String str;
                    String str2;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof GetConfiguredActivityListResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse");
                    GetConfiguredActivityListResponse getConfiguredActivityListResponse = (GetConfiguredActivityListResponse) responseData;
                    List<ConfiguredActivities.ActivityInfo> activityInfoList = getConfiguredActivityListResponse.getConfiguredActivities().getActivityInfoList();
                    Intrinsics.checkNotNullExpressionValue(activityInfoList, "data.configuredActivities.activityInfoList");
                    ConfiguredActivities.ActivityInfo[] activityInfoArr = (ConfiguredActivities.ActivityInfo[]) activityInfoList.toArray(new ConfiguredActivities.ActivityInfo[0]);
                    str = FragmentActivityAndCategoryViewModel.this.g;
                    LogHelper.d(str, "watch activities from watch " + new Gson().toJson(activityInfoArr));
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(FragmentActivityAndCategoryViewModel.this, activityInfoArr, listener, null), 2, null);
                    str2 = FragmentActivityAndCategoryViewModel.this.g;
                    LogHelper.d(str2, getConfiguredActivityListResponse.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    String str;
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    str = FragmentActivityAndCategoryViewModel.this.g;
                    LogHelper.d(str, progress.toString());
                }
            });
        } else {
            Toast.makeText(this.d, R.string.band_not_connected, 1).show();
        }
    }

    public final void setActivityLiveData(@NotNull LiveData<List<ActivitiesListModel>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.f = liveData;
    }

    public final void setCategoryLiveData(@NotNull LiveData<List<ActivityCategoriesModel>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.e = liveData;
    }
}
