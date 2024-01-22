package com.coveiot.android.activitymodes.activity1k.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FragmentActivityInformationViewModel extends AndroidViewModel {
    @NotNull
    public Context d;
    @NotNull
    public LiveData<List<ActivityCategoriesModel>> e;
    @NotNull
    public LiveData<List<ActivitiesListModel>> f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentActivityInformationViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = application;
        this.e = new MutableLiveData();
        this.f = new MutableLiveData();
    }

    public final void getActivityListFromDb() {
        this.f = PhysicalActivityRepository.Companion.getInstance(this.d).getPhysicalActivityList();
    }

    @NotNull
    public final LiveData<List<ActivitiesListModel>> getActivityLiveData() {
        return this.f;
    }

    public final void getCategoryListFromDb() {
        this.e = PhysicalActivityRepository.Companion.getInstance(this.d).getActivitiesCategories();
    }

    @NotNull
    public final LiveData<List<ActivityCategoriesModel>> getCategoryLiveData() {
        return this.e;
    }

    @NotNull
    public final HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> getExpandableHashMap(@NotNull List<ActivitiesListModel> activityList, @NotNull ArrayList<ActivityCategoriesModel> categoryList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        Intrinsics.checkNotNullParameter(categoryList, "categoryList");
        HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = new HashMap<>();
        if (activityList.size() > 0 && categoryList.size() > 0) {
            Iterator<ActivityCategoriesModel> it = categoryList.iterator();
            while (it.hasNext()) {
                ActivityCategoriesModel next = it.next();
                ArrayList arrayList = new ArrayList();
                for (ActivitiesListModel activitiesListModel : activityList) {
                    if (Intrinsics.areEqual(next.getCategoryId(), activitiesListModel.getCategoryId())) {
                        arrayList.add(activitiesListModel);
                    }
                }
                hashMap.put(next, arrayList);
            }
        }
        return hashMap;
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
