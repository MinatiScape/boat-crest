package com.coveiot.android.activitymodes.activity1k.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWatchActivitiesViewModel extends AndroidViewModel {
    @NotNull
    public Context d;
    @NotNull
    public LiveData<List<CategoryAndActivityModel>> e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentWatchActivitiesViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = application;
        this.e = new MutableLiveData();
    }

    public final void cleanUpImageIDs(@NotNull final SyncResultListner listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        BleApiManager.getInstance(this.d).getBleApi().getData(new GetImageIdListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentWatchActivitiesViewModel$cleanUpImageIDs$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SyncResultListner.this.onSuccess();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Context context;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof GetImageListResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData);
                    List<Integer> imageIdList = ((GetImageListResponse) responseData).getImageIdList();
                    if (imageIdList == null) {
                        SyncResultListner.this.onSuccess();
                        return;
                    }
                    if (imageIdList.contains(997)) {
                        imageIdList.remove((Object) 997);
                    }
                    if (imageIdList.contains(65535)) {
                        imageIdList.remove((Object) 65535);
                    }
                    if (imageIdList.contains(996)) {
                        imageIdList.remove((Object) 996);
                    }
                    if (imageIdList.isEmpty()) {
                        SyncResultListner.this.onSuccess();
                        return;
                    }
                    final Ref.IntRef intRef = new Ref.IntRef();
                    intRef.element = imageIdList.size();
                    for (Integer imageId : imageIdList) {
                        context = this.d;
                        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                        Intrinsics.checkNotNullExpressionValue(imageId, "imageId");
                        DeleteImageRequest deleteImageRequest = new DeleteImageRequest(imageId.intValue());
                        final SyncResultListner syncResultListner = SyncResultListner.this;
                        bleApi.setUserSettings(deleteImageRequest, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentWatchActivitiesViewModel$cleanUpImageIDs$1$onDataResponse$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                Ref.IntRef intRef2 = Ref.IntRef.this;
                                int i = intRef2.element - 1;
                                intRef2.element = i;
                                if (i == 0) {
                                    syncResultListner.onSuccess();
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                                Intrinsics.checkNotNullParameter(response2, "response");
                                Ref.IntRef intRef2 = Ref.IntRef.this;
                                int i = intRef2.element - 1;
                                intRef2.element = i;
                                if (i == 0) {
                                    syncResultListner.onSuccess();
                                }
                            }
                        });
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final LiveData<List<CategoryAndActivityModel>> getCategoryActivityLiveData() {
        return this.e;
    }

    public final void getCategoryAndActivityListFromDb(@Nullable List<Integer> list) {
        this.e = PhysicalActivityRepository.Companion.getInstance(this.d).getActivityAndCategories();
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
                if (categoryId2 != null && categoryId == categoryId2.intValue() && activityInfo.getActivityId() == next.getFwActId()) {
                    categoryAndActivityModelArr[activityInfo.getOrderId()] = next;
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

    public final void setCategoryActivityLiveData(@NotNull LiveData<List<CategoryAndActivityModel>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.e = liveData;
    }
}
