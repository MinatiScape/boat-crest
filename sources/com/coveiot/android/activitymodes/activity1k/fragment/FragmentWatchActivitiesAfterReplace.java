package com.coveiot.android.activitymodes.activity1k.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentWatchActivitiesViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWatchActivitiesAfterReplace extends BaseFragment implements BaseListener, WatchActivityAdapter.ItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentWatchActivitiesViewModel m;
    public WatchActivityAdapter o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public List<CategoryAndActivityModel> n = new ArrayList();
    @NotNull
    public GetConfiguredActivityListRequest p = new GetConfiguredActivityListRequest();
    public final String q = FragmentWatchActivitiesAfterReplace.class.getSimpleName();
    @NotNull
    public ConfiguredActivities.ActivityInfo[] r = new ConfiguredActivities.ActivityInfo[10];
    @Nullable
    public List<Integer> s = new ArrayList();
    @Nullable
    public List<Integer> t = new ArrayList();
    @Nullable
    public Map<Integer, Integer> u = new HashMap();
    @NotNull
    public final Observer<List<CategoryAndActivityModel>> v = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.g0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentWatchActivitiesAfterReplace.l(FragmentWatchActivitiesAfterReplace.this, (List) obj);
        }
    };

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentWatchActivitiesAfterReplace newInstance() {
            return new FragmentWatchActivitiesAfterReplace();
        }
    }

    public static final void l(FragmentWatchActivitiesAfterReplace this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.dismissProgress();
            FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel = this$0.m;
            WatchActivityAdapter watchActivityAdapter = null;
            if (fragmentWatchActivitiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentWatchActivitiesViewModel = null;
            }
            this$0.n = fragmentWatchActivitiesViewModel.getCategoryAndActivityListSorted(list, this$0.r);
            WatchActivityAdapter watchActivityAdapter2 = this$0.o;
            if (watchActivityAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            } else {
                watchActivityAdapter = watchActivityAdapter2;
            }
            List<CategoryAndActivityModel> list2 = this$0.n;
            Intrinsics.checkNotNull(list2);
            watchActivityAdapter.notifyAdapter(list2);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final List<Integer> getActivityIdList() {
        return this.t;
    }

    @NotNull
    public final ConfiguredActivities.ActivityInfo[] getActivityInfoList() {
        return this.r;
    }

    @Nullable
    public final Map<Integer, Integer> getCategoryAndActivityMap() {
        return this.u;
    }

    @Nullable
    public final List<Integer> getCategoryIdList() {
        return this.s;
    }

    @NotNull
    public final GetConfiguredActivityListRequest getGetConfiguredActivityListRequest() {
        return this.p;
    }

    public final String getTAG() {
        return this.q;
    }

    public final void m() {
        ConfiguredActivities.ActivityInfo[] activityInfoArr;
        List<Integer> list = this.t;
        Intrinsics.checkNotNull(list);
        list.clear();
        List<Integer> list2 = this.s;
        Intrinsics.checkNotNull(list2);
        list2.clear();
        for (ConfiguredActivities.ActivityInfo activityInfo : this.r) {
            List<Integer> list3 = this.t;
            Intrinsics.checkNotNull(list3);
            Intrinsics.checkNotNull(activityInfo);
            list3.add(Integer.valueOf(activityInfo.getActivityId()));
            List<Integer> list4 = this.s;
            Intrinsics.checkNotNull(list4);
            list4.add(Integer.valueOf(activityInfo.getCategoryId()));
        }
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel = this.m;
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel2 = null;
        if (fragmentWatchActivitiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentWatchActivitiesViewModel = null;
        }
        fragmentWatchActivitiesViewModel.getCategoryAndActivityListFromDb(this.s);
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel3 = this.m;
        if (fragmentWatchActivitiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentWatchActivitiesViewModel2 = fragmentWatchActivitiesViewModel3;
        }
        LiveData<List<CategoryAndActivityModel>> categoryActivityLiveData = fragmentWatchActivitiesViewModel2.getCategoryActivityLiveData();
        if (categoryActivityLiveData != null) {
            categoryActivityLiveData.observe(this, this.v);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        ViewModel viewModel = ViewModelProviders.of(activity).get(FragmentWatchActivitiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…iesViewModel::class.java)");
        this.m = (FragmentWatchActivitiesViewModel) viewModel;
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity2).setupToolbar(Companion.newInstance());
        WatchActivityAdapter watchActivityAdapter = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        int i = R.id.rv_activity;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<CategoryAndActivityModel> list = this.n;
        Intrinsics.checkNotNull(list);
        this.o = new WatchActivityAdapter(list, this);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView2);
        WatchActivityAdapter watchActivityAdapter2 = this.o;
        if (watchActivityAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            watchActivityAdapter = watchActivityAdapter2;
        }
        recyclerView2.setAdapter(watchActivityAdapter);
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(getContext()).getBleApi().getData(new GetConfiguredActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchActivitiesAfterReplace$onActivityCreated$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(FragmentWatchActivitiesAfterReplace.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof GetConfiguredActivityListResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse");
                    GetConfiguredActivityListResponse getConfiguredActivityListResponse = (GetConfiguredActivityListResponse) responseData;
                    FragmentWatchActivitiesAfterReplace fragmentWatchActivitiesAfterReplace = FragmentWatchActivitiesAfterReplace.this;
                    List<ConfiguredActivities.ActivityInfo> activityInfoList = getConfiguredActivityListResponse.getConfiguredActivities().getActivityInfoList();
                    Intrinsics.checkNotNullExpressionValue(activityInfoList, "data.configuredActivities.activityInfoList");
                    fragmentWatchActivitiesAfterReplace.setActivityInfoList((ConfiguredActivities.ActivityInfo[]) activityInfoList.toArray(new ConfiguredActivities.ActivityInfo[0]));
                    String tag = FragmentWatchActivitiesAfterReplace.this.getTAG();
                    LogHelper.d(tag, "watch activities from watch " + new Gson().toJson(FragmentWatchActivitiesAfterReplace.this.getActivityInfoList()));
                    FragmentWatchActivitiesAfterReplace.this.m();
                    LogHelper.d(FragmentWatchActivitiesAfterReplace.this.getTAG(), getConfiguredActivityListResponse.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    LogHelper.d(FragmentWatchActivitiesAfterReplace.this.getTAG(), progress.toString());
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
    public void onClicked(@Nullable String str, boolean z, @NotNull CategoryAndActivityModel categoryAndActivityModel) {
        Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_activities_after_replace, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
    public void onInfoClicked(@NotNull CategoryAndActivityModel categoryAndActivityModel) {
        Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
    public void onLongClicked(int i) {
    }

    public final void setActivityIdList(@Nullable List<Integer> list) {
        this.t = list;
    }

    public final void setActivityInfoList(@NotNull ConfiguredActivities.ActivityInfo[] activityInfoArr) {
        Intrinsics.checkNotNullParameter(activityInfoArr, "<set-?>");
        this.r = activityInfoArr;
    }

    public final void setCategoryAndActivityMap(@Nullable Map<Integer, Integer> map) {
        this.u = map;
    }

    public final void setCategoryIdList(@Nullable List<Integer> list) {
        this.s = list;
    }

    public final void setGetConfiguredActivityListRequest(@NotNull GetConfiguredActivityListRequest getConfiguredActivityListRequest) {
        Intrinsics.checkNotNullParameter(getConfiguredActivityListRequest, "<set-?>");
        this.p = getConfiguredActivityListRequest;
    }
}
