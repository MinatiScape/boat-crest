package com.coveiot.android.activitymodes.activity1k.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.ResponseListener;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onekactivity.ActivityCategoryData;
import com.coveiot.coveaccess.onekactivity.CategoryItem;
import com.coveiot.coveaccess.onekactivity.DeviceIcon;
import com.coveiot.coveaccess.onekactivity.PhysicalActivityData;
import com.coveiot.coveaccess.onekactivity.PhysicalActivityItem;
import com.coveiot.coveaccess.onekactivity.PhysicalActivityManager;
import com.coveiot.coveaccess.onekactivity.SPhysicalActivityCategoriesRes;
import com.coveiot.coveaccess.onekactivity.SPhysicalActivityListRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class OneKActivityViewModel extends AndroidViewModel {
    public ConfiguredActivities.ActivityInfo[] d;
    @NotNull
    public Context e;
    @NotNull
    public LiveData<List<EntityActivityCategory>> f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneKActivityViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.e = application;
        this.f = new MutableLiveData();
    }

    public static /* synthetic */ void saveCategoryListFromServer$default(OneKActivityViewModel oneKActivityViewModel, ResponseListener responseListener, int i, Object obj) {
        if ((i & 1) != 0) {
            responseListener = null;
        }
        oneKActivityViewModel.saveCategoryListFromServer(responseListener);
    }

    public final Integer a(List<DeviceIconModel> list, Boolean bool) {
        Intrinsics.checkNotNull(list);
        for (DeviceIconModel deviceIconModel : list) {
            if (m.equals$default(deviceIconModel.getUse(), "lg", false, 2, null)) {
                return deviceIconModel.getRefId();
            }
        }
        for (DeviceIconModel deviceIconModel2 : list) {
            if (m.equals$default(deviceIconModel2.getUse(), "md", false, 2, null)) {
                return deviceIconModel2.getRefId();
            }
        }
        return -1;
    }

    public final Integer b(List<DeviceIconModel> list, Boolean bool) {
        Intrinsics.checkNotNull(list);
        for (DeviceIconModel deviceIconModel : list) {
            if (m.equals$default(deviceIconModel.getUse(), "md", false, 2, null)) {
                return deviceIconModel.getRefId();
            }
        }
        for (DeviceIconModel deviceIconModel2 : list) {
            if (m.equals$default(deviceIconModel2.getUse(), "lg", false, 2, null)) {
                return deviceIconModel2.getRefId();
            }
        }
        return -1;
    }

    public final ArrayList<String> c(int i, int i2, ArrayList<String> arrayList) {
        if (i == 17 && i2 == 262) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, "STEPS", "DISTANCE", FitnessChallengeConstants.CALORIES, "PACE");
        }
        if (i == 12 && i2 == 25) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, "STEPS", "DISTANCE", FitnessChallengeConstants.CALORIES, "PACE");
        }
        if (i == 12 && i2 == 20) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, "STEPS", "DISTANCE", FitnessChallengeConstants.CALORIES, "PACE");
        }
        if (i == 15 && i2 == 30) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 15 && i2 == 230) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 15 && i2 == 55) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 15 && i2 == 675) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 2 && i2 == 180) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 3 && i2 == 31) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        if (i == 2 && i2 == 19) {
            return CollectionsKt__CollectionsKt.arrayListOf(EcgStyleReportUtil.UserInfoKey.HR, FitnessChallengeConstants.CALORIES);
        }
        Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        return arrayList;
    }

    @NotNull
    public final ConfiguredActivities.ActivityInfo[] getActivityInfoList() {
        ConfiguredActivities.ActivityInfo[] activityInfoArr = this.d;
        if (activityInfoArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityInfoList");
            return null;
        }
        return activityInfoArr;
    }

    public final void getActivityListFromServer(@NotNull final ArrayList<EntityActivityCategory> categoryId, @Nullable final ResponseListener responseListener) {
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        final Ref.IntRef intRef = new Ref.IntRef();
        Iterator<EntityActivityCategory> it = categoryId.iterator();
        while (it.hasNext()) {
            final EntityActivityCategory next = it.next();
            CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel> coveApiListener = new CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel$getActivityListFromServer$1

                @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel$getActivityListFromServer$1$onSuccess$1", f = "OneKActivityViewModel.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ArrayList<EntityPhysicalActivities> $activityList;
                    public int label;
                    public final /* synthetic */ OneKActivityViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(OneKActivityViewModel oneKActivityViewModel, ArrayList<EntityPhysicalActivities> arrayList, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = oneKActivityViewModel;
                        this.$activityList = arrayList;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$activityList, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Context context;
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            PhysicalActivityRepository.Companion companion = PhysicalActivityRepository.Companion;
                            context = this.this$0.e;
                            ArrayList<EntityPhysicalActivities> arrayList = this.$activityList;
                            Intrinsics.checkNotNull(arrayList);
                            this.label = 1;
                            if (companion.getInstance(context).insertPhysicalActivity(arrayList, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.d("res err", String.valueOf(coveApiErrorModel));
                    ResponseListener responseListener2 = responseListener;
                    if (responseListener2 != null) {
                        responseListener2.onError();
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SPhysicalActivityListRes sPhysicalActivityListRes) {
                    ResponseListener responseListener2;
                    ArrayList c;
                    boolean booleanValue;
                    Integer b;
                    Integer a2;
                    Integer b2;
                    Integer a3;
                    ArrayList arrayList = new ArrayList();
                    Intrinsics.checkNotNull(sPhysicalActivityListRes);
                    PhysicalActivityData data = sPhysicalActivityListRes.getData();
                    Intrinsics.checkNotNull(data);
                    for (PhysicalActivityItem physicalActivityItem : data.getItems()) {
                        EntityPhysicalActivities entityPhysicalActivities = new EntityPhysicalActivities(0, 0, 0, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 2097151, null);
                        Integer categoryId2 = physicalActivityItem.getCategoryId();
                        Intrinsics.checkNotNullExpressionValue(categoryId2, "item.categoryId");
                        entityPhysicalActivities.setCategoryId(categoryId2.intValue());
                        Integer activityId = physicalActivityItem.getActivityId();
                        Intrinsics.checkNotNullExpressionValue(activityId, "item.activityId");
                        entityPhysicalActivities.setActivityId(activityId.intValue());
                        entityPhysicalActivities.setFwActId(physicalActivityItem.getFwActId());
                        String titleInMetric = physicalActivityItem.getTitleInMetric();
                        entityPhysicalActivities.setTitleInMetric(titleInMetric != null ? m.capitalize(titleInMetric) : null);
                        String titleInImperial = physicalActivityItem.getTitleInImperial();
                        entityPhysicalActivities.setTitleInImperial(titleInImperial != null ? m.capitalize(titleInImperial) : null);
                        entityPhysicalActivities.setActivityCode(physicalActivityItem.getActivityCode());
                        entityPhysicalActivities.setCpaCode(physicalActivityItem.getCpaCode());
                        entityPhysicalActivities.setDefaultMets(physicalActivityItem.getDefaultMets());
                        entityPhysicalActivities.setDescInImperial(physicalActivityItem.getDescInImperial());
                        entityPhysicalActivities.setDescInMetric(physicalActivityItem.getDescInMetric());
                        OneKActivityViewModel oneKActivityViewModel = OneKActivityViewModel.this;
                        Integer categoryId3 = physicalActivityItem.getCategoryId();
                        Intrinsics.checkNotNullExpressionValue(categoryId3, "item.categoryId");
                        int intValue = categoryId3.intValue();
                        Integer activityId2 = physicalActivityItem.getActivityId();
                        Intrinsics.checkNotNullExpressionValue(activityId2, "item.activityId");
                        int intValue2 = activityId2.intValue();
                        List<String> metrics = physicalActivityItem.getMetrics();
                        Intrinsics.checkNotNull(metrics, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
                        c = oneKActivityViewModel.c(intValue, intValue2, (ArrayList) metrics);
                        entityPhysicalActivities.setMetrics(c);
                        entityPhysicalActivities.setCal_func(physicalActivityItem.getCalorieFunc());
                        if (!AppUtils.isEmpty(physicalActivityItem.getIconUrl())) {
                            entityPhysicalActivities.setIconUrl(physicalActivityItem.getIconUrl());
                        } else {
                            entityPhysicalActivities.setIconUrl(next.getIconUrl());
                        }
                        if (physicalActivityItem.getDvcTitleInMetric() != null) {
                            entityPhysicalActivities.setDvcTitleInMetric(physicalActivityItem.getDvcTitleInMetric());
                        }
                        if (physicalActivityItem.getDvcTitleInImperial() != null) {
                            entityPhysicalActivities.setDvcTitleInImperial(physicalActivityItem.getDvcTitleInImperial());
                        }
                        if (physicalActivityItem.getInbuilt() == null) {
                            booleanValue = false;
                        } else {
                            Boolean inbuilt = physicalActivityItem.getInbuilt();
                            Intrinsics.checkNotNullExpressionValue(inbuilt, "item.inbuilt");
                            booleanValue = inbuilt.booleanValue();
                        }
                        entityPhysicalActivities.setInbuilt(booleanValue);
                        entityPhysicalActivities.setDefaultActivityName(physicalActivityItem.getActivityName());
                        ArrayList arrayList2 = new ArrayList();
                        if (!AppUtils.isEmpty(physicalActivityItem.getDeviceIcons())) {
                            for (DeviceIcon deviceIcon : physicalActivityItem.getDeviceIcons()) {
                                DeviceIconModel deviceIconModel = new DeviceIconModel(null, null, null, null, null, null, null, 127, null);
                                deviceIconModel.setRefId(deviceIcon.getRefId());
                                deviceIconModel.setType(deviceIcon.getType());
                                deviceIconModel.setUrl(deviceIcon.getUrl());
                                deviceIconModel.setCompressed(deviceIcon.getCompressed());
                                deviceIconModel.setSize(deviceIcon.getSize());
                                deviceIconModel.setTransparent(deviceIcon.getTransparent());
                                deviceIconModel.setUse(deviceIcon.getUse());
                                arrayList2.add(deviceIconModel);
                            }
                        } else if (!AppUtils.isEmpty(next.getDeviceIconModels())) {
                            List<DeviceIconModel> deviceIconModels = next.getDeviceIconModels();
                            Intrinsics.checkNotNull(deviceIconModels);
                            for (DeviceIconModel deviceIconModel2 : deviceIconModels) {
                                DeviceIconModel deviceIconModel3 = new DeviceIconModel(null, null, null, null, null, null, null, 127, null);
                                deviceIconModel3.setRefId(deviceIconModel2.getRefId());
                                deviceIconModel3.setType(deviceIconModel2.getType());
                                deviceIconModel3.setUrl(deviceIconModel2.getUrl());
                                deviceIconModel3.setCompressed(deviceIconModel2.getCompressed());
                                deviceIconModel3.setSize(deviceIconModel2.getSize());
                                deviceIconModel3.setTransparent(deviceIconModel2.getTransparent());
                                deviceIconModel3.setUse(deviceIconModel2.getUse());
                                arrayList2.add(deviceIconModel3);
                            }
                        }
                        entityPhysicalActivities.setDeviceIconModels(arrayList2);
                        if (!AppUtils.isEmpty(CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2))) {
                            b2 = OneKActivityViewModel.this.b(arrayList2, physicalActivityItem.getInbuilt());
                            entityPhysicalActivities.setDefaultCategoryIcon(b2);
                            a3 = OneKActivityViewModel.this.a(arrayList2, physicalActivityItem.getInbuilt());
                            entityPhysicalActivities.setDefaultActivityIcon(a3);
                        } else {
                            OneKActivityViewModel oneKActivityViewModel2 = OneKActivityViewModel.this;
                            EntityActivityCategory entityActivityCategory = next;
                            b = oneKActivityViewModel2.b(entityActivityCategory != null ? entityActivityCategory.getDeviceIconModels() : null, physicalActivityItem.getInbuilt());
                            entityPhysicalActivities.setDefaultCategoryIcon(b);
                            OneKActivityViewModel oneKActivityViewModel3 = OneKActivityViewModel.this;
                            EntityActivityCategory entityActivityCategory2 = next;
                            a2 = oneKActivityViewModel3.a(entityActivityCategory2 != null ? entityActivityCategory2.getDeviceIconModels() : null, physicalActivityItem.getInbuilt());
                            entityPhysicalActivities.setDefaultActivityIcon(a2);
                        }
                        arrayList.add(entityPhysicalActivities);
                    }
                    e.e(GlobalScope.INSTANCE, null, null, new a(OneKActivityViewModel.this, arrayList, null), 3, null);
                    Ref.IntRef intRef2 = intRef;
                    int i = intRef2.element + 1;
                    intRef2.element = i;
                    if (i != categoryId.size() || (responseListener2 = responseListener) == null) {
                        return;
                    }
                    responseListener2.onResponse();
                }
            };
            Integer categoryId2 = next.getCategoryId();
            Intrinsics.checkNotNull(categoryId2);
            PhysicalActivityManager.getPhysicalActivityList(coveApiListener, categoryId2.intValue());
        }
    }

    @NotNull
    public final LiveData<List<EntityActivityCategory>> getCategoryLiveData() {
        return this.f;
    }

    public final void getCurrentActivitiesOnWatch(@NotNull final ResponseListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        if (BleApiManager.getInstance(this.e).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.e).getBleApi().getData(new GetConfiguredActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel$getCurrentActivitiesOnWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    listner.onError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof GetConfiguredActivityListResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse");
                    OneKActivityViewModel oneKActivityViewModel = OneKActivityViewModel.this;
                    List<ConfiguredActivities.ActivityInfo> activityInfoList = ((GetConfiguredActivityListResponse) responseData).getConfiguredActivities().getActivityInfoList();
                    Intrinsics.checkNotNullExpressionValue(activityInfoList, "data.configuredActivities.activityInfoList");
                    oneKActivityViewModel.d = (ConfiguredActivities.ActivityInfo[]) activityInfoList.toArray(new ConfiguredActivities.ActivityInfo[0]);
                    listner.onResponse();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        listner.onError();
        Toast.makeText(this.e, R.string.band_not_connected, 1).show();
    }

    public final void saveCategoryListFromServer(@Nullable final ResponseListener responseListener) {
        PhysicalActivityManager.getPhysicalActivityCategories(new CoveApiListener<SPhysicalActivityCategoriesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel$saveCategoryListFromServer$1

            @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel$saveCategoryListFromServer$1$onSuccess$isInserted$1", f = "OneKActivityViewModel.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ ArrayList<EntityActivityCategory> $categoryList;
                public int label;
                public final /* synthetic */ OneKActivityViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(OneKActivityViewModel oneKActivityViewModel, ArrayList<EntityActivityCategory> arrayList, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = oneKActivityViewModel;
                    this.$categoryList = arrayList;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$categoryList, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Context context;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PhysicalActivityRepository.Companion companion = PhysicalActivityRepository.Companion;
                        context = this.this$0.e;
                        ArrayList<EntityActivityCategory> arrayList = this.$categoryList;
                        Intrinsics.checkNotNull(arrayList);
                        this.label = 1;
                        if (companion.getInstance(context).insertActivityCategory(arrayList, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d(" err", String.valueOf(coveApiErrorModel));
                ResponseListener responseListener2 = responseListener;
                if (responseListener2 != null) {
                    responseListener2.onError();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SPhysicalActivityCategoriesRes sPhysicalActivityCategoriesRes) {
                Context context;
                Context context2;
                Job e;
                ArrayList<EntityActivityCategory> arrayList = new ArrayList<>();
                PhysicalActivityRepository.Companion companion = PhysicalActivityRepository.Companion;
                context = OneKActivityViewModel.this.e;
                companion.getInstance(context).clearCategoryTable();
                context2 = OneKActivityViewModel.this.e;
                companion.getInstance(context2).clearActivityTable();
                Intrinsics.checkNotNull(sPhysicalActivityCategoriesRes);
                ActivityCategoryData data = sPhysicalActivityCategoriesRes.getData();
                Intrinsics.checkNotNull(data);
                for (CategoryItem categoryItem : data.getItems()) {
                    ArrayList arrayList2 = new ArrayList();
                    EntityActivityCategory entityActivityCategory = new EntityActivityCategory(null, null, null, null, null, 31, null);
                    entityActivityCategory.setCategoryId(categoryItem.getCategoryId());
                    entityActivityCategory.setDescription(categoryItem.getDescription());
                    entityActivityCategory.setIconUrl(categoryItem.getIconUrl());
                    String title = categoryItem.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "item.title");
                    entityActivityCategory.setTitle(m.capitalize(title));
                    for (DeviceIcon deviceIcon : categoryItem.getDeviceIcons()) {
                        DeviceIconModel deviceIconModel = new DeviceIconModel(null, null, null, null, null, null, null, 127, null);
                        deviceIconModel.setRefId(deviceIcon.getRefId());
                        deviceIconModel.setType(deviceIcon.getType());
                        deviceIconModel.setUrl(deviceIcon.getUrl());
                        deviceIconModel.setCompressed(deviceIcon.getCompressed());
                        deviceIconModel.setSize(deviceIcon.getSize());
                        deviceIconModel.setTransparent(deviceIcon.getTransparent());
                        deviceIconModel.setUse(deviceIcon.getUse());
                        arrayList2.add(deviceIconModel);
                    }
                    entityActivityCategory.setDeviceIconModels(arrayList2);
                    arrayList.add(entityActivityCategory);
                }
                e = e.e(GlobalScope.INSTANCE, null, null, new a(OneKActivityViewModel.this, arrayList, null), 3, null);
                LogHelper.d("insrted", e.toString());
                OneKActivityViewModel.this.getActivityListFromServer(arrayList, responseListener);
            }
        });
    }

    public final void setCategoryLiveData(@NotNull LiveData<List<EntityActivityCategory>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.f = liveData;
    }
}
