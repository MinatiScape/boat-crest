package com.coveiot.android.activitymodes.activity1k.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentWatchActivitiesViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWatchRestoreToDefaults extends BaseFragment implements BaseListener, WatchActivityAdapterNew.ItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Snackbar m;
    public FragmentWatchActivitiesViewModel n;
    public WatchActivityAdapterNew p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public List<CategoryAndActivityModel> o = new ArrayList();
    @NotNull
    public GetConfiguredActivityListRequest q = new GetConfiguredActivityListRequest();
    public final String r = FragmentWatchRestoreToDefaults.class.getSimpleName();
    @NotNull
    public ConfiguredActivities.ActivityInfo[] s = new ConfiguredActivities.ActivityInfo[10];
    @Nullable
    public List<Integer> t = new ArrayList();
    @Nullable
    public List<Integer> u = new ArrayList();
    @Nullable
    public Map<Integer, Integer> v = new HashMap();
    @NotNull
    public final Observer<List<CategoryAndActivityModel>> w = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.n0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentWatchRestoreToDefaults.o(FragmentWatchRestoreToDefaults.this, (List) obj);
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
        public final FragmentWatchRestoreToDefaults newInstance() {
            return new FragmentWatchRestoreToDefaults();
        }
    }

    public static final void o(FragmentWatchRestoreToDefaults this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.dismissProgress();
            FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel = this$0.n;
            WatchActivityAdapterNew watchActivityAdapterNew = null;
            if (fragmentWatchActivitiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentWatchActivitiesViewModel = null;
            }
            this$0.o = fragmentWatchActivitiesViewModel.getCategoryAndActivityListSorted(list, this$0.s);
            HashMap<Integer, ArrayList<CategoryAndActivityModel>> hashMap = new HashMap<>();
            List<CategoryAndActivityModel> list2 = this$0.o;
            Intrinsics.checkNotNull(list2);
            for (CategoryAndActivityModel categoryAndActivityModel : list2) {
                if (hashMap.containsKey(categoryAndActivityModel.getCategoryId())) {
                    ArrayList<CategoryAndActivityModel> arrayList = hashMap.get(categoryAndActivityModel.getCategoryId());
                    if (arrayList != null) {
                        arrayList.add(categoryAndActivityModel);
                    }
                    Integer categoryId = categoryAndActivityModel.getCategoryId();
                    Intrinsics.checkNotNull(categoryId);
                    Intrinsics.checkNotNull(arrayList);
                    hashMap.put(categoryId, arrayList);
                } else {
                    ArrayList<CategoryAndActivityModel> arrayList2 = new ArrayList<>();
                    arrayList2.add(categoryAndActivityModel);
                    Integer categoryId2 = categoryAndActivityModel.getCategoryId();
                    Intrinsics.checkNotNull(categoryId2);
                    hashMap.put(categoryId2, arrayList2);
                }
            }
            WatchActivityAdapterNew watchActivityAdapterNew2 = this$0.p;
            if (watchActivityAdapterNew2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            } else {
                watchActivityAdapterNew = watchActivityAdapterNew2;
            }
            Set<Integer> keySet = hashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "catergeryHashMap!!.keys");
            watchActivityAdapterNew.notifyAdapter(CollectionsKt___CollectionsKt.toList(keySet), hashMap);
        }
    }

    public static final void p(final FragmentWatchRestoreToDefaults this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (SyncManager.getInstance().isSyncInProgress()) {
                Toast.makeText(this$0.getContext(), R.string.syncing_please_wait, 0).show();
                return;
            }
            SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            List<CategoryAndActivityModel> list = this$0.o;
            Intrinsics.checkNotNull(list);
            companion.getInstance(context2).setCategoryAndActivityList(list);
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
            ((OneKActivity) activity).showRestoreToDefaultDialog(new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchRestoreToDefaults$onActivityCreated$1$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    FragmentWatchRestoreToDefaults.this.dismissProgress();
                    Toast.makeText(FragmentWatchRestoreToDefaults.this.requireContext(), R.string.some_thing_went_wrong, 0).show();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel;
                    Intrinsics.checkNotNullParameter(response, "response");
                    FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel2 = null;
                    BaseFragment.showProgress$default(FragmentWatchRestoreToDefaults.this, false, 1, null);
                    fragmentWatchActivitiesViewModel = FragmentWatchRestoreToDefaults.this.n;
                    if (fragmentWatchActivitiesViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        fragmentWatchActivitiesViewModel2 = fragmentWatchActivitiesViewModel;
                    }
                    final FragmentWatchRestoreToDefaults fragmentWatchRestoreToDefaults = FragmentWatchRestoreToDefaults.this;
                    fragmentWatchActivitiesViewModel2.cleanUpImageIDs(new SyncResultListner() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchRestoreToDefaults$onActivityCreated$1$1$onSettingsResponse$1
                        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                        public void onFailure(@Nullable Error error) {
                            FragmentWatchRestoreToDefaults.this.dismissProgress();
                            FragmentWatchRestoreToDefaults.this.r();
                        }

                        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                        public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                        }

                        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                        public void onSuccess() {
                            FragmentWatchRestoreToDefaults.this.dismissProgress();
                            FragmentWatchRestoreToDefaults.this.r();
                        }
                    });
                }
            });
            return;
        }
        FragmentActivity activity2 = this$0.getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity2).showBandNotConnected(false);
    }

    public static final void q(FragmentWatchRestoreToDefaults this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackbar = this$0.m;
        if (snackbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            snackbar = null;
        }
        snackbar.getDuration();
    }

    public static final void s(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentWatchRestoreToDefaults this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.requireActivity().finish();
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
        return this.u;
    }

    @NotNull
    public final ConfiguredActivities.ActivityInfo[] getActivityInfoList() {
        return this.s;
    }

    @Nullable
    public final Map<Integer, Integer> getCategoryAndActivityMap() {
        return this.v;
    }

    @Nullable
    public final List<Integer> getCategoryIdList() {
        return this.t;
    }

    @NotNull
    public final GetConfiguredActivityListRequest getGetConfiguredActivityListRequest() {
        return this.q;
    }

    public final String getTAG() {
        return this.r;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        ViewModel viewModel = ViewModelProviders.of(activity).get(FragmentWatchActivitiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…iesViewModel::class.java)");
        this.n = (FragmentWatchActivitiesViewModel) viewModel;
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity2).setupToolbar(Companion.newInstance());
        ((TextView) _$_findCachedViewById(R.id.watch_activities_action_text)).setText(getString(R.string.activities_reset_existing_activities));
        int i = R.id.next_button;
        ((Button) _$_findCachedViewById(i)).setVisibility(8);
        ((Button) _$_findCachedViewById(i)).setText(R.string.reset_to_default);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchRestoreToDefaults.p(FragmentWatchRestoreToDefaults.this, view);
            }
        });
        WatchActivityAdapterNew watchActivityAdapterNew = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        new LinearLayoutManager(getContext());
        HashMap hashMap = new HashMap();
        List<CategoryAndActivityModel> list = this.o;
        Intrinsics.checkNotNull(list);
        for (CategoryAndActivityModel categoryAndActivityModel : list) {
            if (hashMap.containsKey(categoryAndActivityModel.getCategoryId())) {
                ArrayList arrayList = (ArrayList) hashMap.get(categoryAndActivityModel.getCategoryId());
                if (arrayList != null) {
                    arrayList.add(categoryAndActivityModel);
                }
                Integer categoryId = categoryAndActivityModel.getCategoryId();
                Intrinsics.checkNotNull(categoryId);
                Intrinsics.checkNotNull(arrayList);
                hashMap.put(categoryId, arrayList);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(categoryAndActivityModel);
                Integer categoryId2 = categoryAndActivityModel.getCategoryId();
                Intrinsics.checkNotNull(categoryId2);
                hashMap.put(categoryId2, arrayList2);
            }
        }
        Set keySet = hashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "catergeryHashMap.keys");
        List list2 = CollectionsKt___CollectionsKt.toList(keySet);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.p = new WatchActivityAdapterNew(list2, hashMap, this, requireContext);
        int i2 = R.id.rv_activity;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNull(recyclerView2);
        WatchActivityAdapterNew watchActivityAdapterNew2 = this.p;
        if (watchActivityAdapterNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            watchActivityAdapterNew = watchActivityAdapterNew2;
        }
        recyclerView2.setAdapter(watchActivityAdapterNew);
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(getContext()).getBleApi().getData(new GetConfiguredActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchRestoreToDefaults$onActivityCreated$2
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(FragmentWatchRestoreToDefaults.this.getTAG(), error.toString());
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
                    FragmentWatchRestoreToDefaults fragmentWatchRestoreToDefaults = FragmentWatchRestoreToDefaults.this;
                    List<ConfiguredActivities.ActivityInfo> activityInfoList = getConfiguredActivityListResponse.getConfiguredActivities().getActivityInfoList();
                    Intrinsics.checkNotNullExpressionValue(activityInfoList, "data.configuredActivities.activityInfoList");
                    fragmentWatchRestoreToDefaults.setActivityInfoList((ConfiguredActivities.ActivityInfo[]) activityInfoList.toArray(new ConfiguredActivities.ActivityInfo[0]));
                    String tag = FragmentWatchRestoreToDefaults.this.getTAG();
                    LogHelper.d(tag, "watch activities from watch " + new Gson().toJson(FragmentWatchRestoreToDefaults.this.getActivityInfoList()));
                    ((Button) FragmentWatchRestoreToDefaults.this._$_findCachedViewById(R.id.next_button)).setVisibility(0);
                    FragmentWatchRestoreToDefaults.this.t();
                    LogHelper.d(FragmentWatchRestoreToDefaults.this.getTAG(), getConfiguredActivityListResponse.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    LogHelper.d(FragmentWatchRestoreToDefaults.this.getTAG(), progress.toString());
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew.ItemClickListener
    public void onClicked(@Nullable String str, boolean z, @NotNull CategoryAndActivityModel categoryAndActivityModel) {
        Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_activities_new, viewGroup, false);
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

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew.ItemClickListener
    public void onInfoClicked(@NotNull CategoryAndActivityModel categoryAndActivityModel) {
        Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
        String descInMetric = categoryAndActivityModel.getDescInMetric();
        Intrinsics.checkNotNull(descInMetric);
        Snackbar make = Snackbar.make((ConstraintLayout) _$_findCachedViewById(R.id.container), descInMetric, -2);
        Intrinsics.checkNotNullExpressionValue(make, "make(\n                co…_INDEFINITE\n            )");
        this.m = make;
        Snackbar snackbar = null;
        if (make == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            make = null;
        }
        make.setAction("Close", new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchRestoreToDefaults.q(FragmentWatchRestoreToDefaults.this, view);
            }
        });
        Snackbar snackbar2 = this.m;
        if (snackbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            snackbar2 = null;
        }
        snackbar2.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        Snackbar snackbar3 = this.m;
        if (snackbar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            snackbar3 = null;
        }
        snackbar3.getView().setBackgroundColor(getResources().getColor(R.color.dialog_bg_color));
        Snackbar snackbar4 = this.m;
        if (snackbar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            snackbar4 = null;
        }
        View findViewById = snackbar4.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setTextColor(getResources().getColor(R.color.white));
        Snackbar snackbar5 = this.m;
        if (snackbar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
            snackbar5 = null;
        }
        if (snackbar5.isShown()) {
            return;
        }
        Snackbar snackbar6 = this.m;
        if (snackbar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbar");
        } else {
            snackbar = snackbar6;
        }
        snackbar.show();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew.ItemClickListener
    public void onLongClicked(int i) {
    }

    public final void r() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.activities_restored_successfully);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…es_restored_successfully)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        bottomSheetDialogOneButtonOneTitle.setCancelable(false);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchRestoreToDefaults.s(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void setActivityIdList(@Nullable List<Integer> list) {
        this.u = list;
    }

    public final void setActivityInfoList(@NotNull ConfiguredActivities.ActivityInfo[] activityInfoArr) {
        Intrinsics.checkNotNullParameter(activityInfoArr, "<set-?>");
        this.s = activityInfoArr;
    }

    public final void setCategoryAndActivityMap(@Nullable Map<Integer, Integer> map) {
        this.v = map;
    }

    public final void setCategoryIdList(@Nullable List<Integer> list) {
        this.t = list;
    }

    public final void setGetConfiguredActivityListRequest(@NotNull GetConfiguredActivityListRequest getConfiguredActivityListRequest) {
        Intrinsics.checkNotNullParameter(getConfiguredActivityListRequest, "<set-?>");
        this.q = getConfiguredActivityListRequest;
    }

    public final void t() {
        ConfiguredActivities.ActivityInfo[] activityInfoArr;
        List<Integer> list = this.u;
        Intrinsics.checkNotNull(list);
        list.clear();
        List<Integer> list2 = this.t;
        Intrinsics.checkNotNull(list2);
        list2.clear();
        for (ConfiguredActivities.ActivityInfo activityInfo : this.s) {
            List<Integer> list3 = this.u;
            Intrinsics.checkNotNull(list3);
            Intrinsics.checkNotNull(activityInfo);
            list3.add(Integer.valueOf(activityInfo.getActivityId()));
            List<Integer> list4 = this.t;
            Intrinsics.checkNotNull(list4);
            list4.add(Integer.valueOf(activityInfo.getCategoryId()));
        }
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel = this.n;
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel2 = null;
        if (fragmentWatchActivitiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentWatchActivitiesViewModel = null;
        }
        fragmentWatchActivitiesViewModel.getCategoryAndActivityListFromDb(this.t);
        FragmentWatchActivitiesViewModel fragmentWatchActivitiesViewModel3 = this.n;
        if (fragmentWatchActivitiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentWatchActivitiesViewModel2 = fragmentWatchActivitiesViewModel3;
        }
        fragmentWatchActivitiesViewModel2.getCategoryActivityLiveData().observe(this, this.w);
    }
}
