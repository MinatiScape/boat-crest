package com.coveiot.android.activitymodes.activity1k.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityCategoryViewModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel;
import com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentCategories extends BaseFragment implements ActivityCategoryAdapterNew.SearchAction, ActivityCategoryAdapterNew.ItemClickListener, ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener, FragmentReArrangeViewModel.ActivityUpdateListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public CategoryAndActivityModel A;
    public FragmentReArrangeViewModel B;
    public ActivityAutoRecognitionViewModelWithOneK C;
    @Nullable
    public View m;
    @Nullable
    public AlertDialog n;
    public boolean o;
    public FragmentActivityCategoryViewModel p;
    public ActivityCategoryAdapterNew r;
    @Nullable
    public ActivityCategoriesModel v;
    @Nullable
    public ActivitiesListModel w;
    @Nullable
    public ActivitiesListModel x;
    public CategoryAndActivityModel z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<ActivityCategoriesModel> q = new ArrayList<>();
    @Nullable
    public ArrayList<ActivityCategoriesModel> s = new ArrayList<>();
    @Nullable
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> t = new HashMap<>();
    @NotNull
    public List<ActivitiesListModel> u = new ArrayList();
    @Nullable
    public List<CategoryAndActivityModel> y = new ArrayList();
    @NotNull
    public final Observer<List<ActivityCategoriesModel>> D = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.t
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentCategories.r(FragmentCategories.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<ActivitiesListModel>> E = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.s
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentCategories.q(FragmentCategories.this, (List) obj);
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
        public final FragmentCategories newInstance(boolean z) {
            FragmentCategories fragmentCategories = new FragmentCategories();
            Bundle bundle = new Bundle();
            bundle.putBoolean("IS_FROM_FTU", z);
            fragmentCategories.setArguments(bundle);
            return fragmentCategories;
        }
    }

    public static final void q(FragmentCategories this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.dismissProgress();
            this$0.u.clear();
            this$0.u.addAll(list);
            if (this$0.u.size() > 0) {
                ArrayList<ActivityCategoriesModel> arrayList = this$0.s;
                Intrinsics.checkNotNull(arrayList);
                if (arrayList.size() > 0) {
                    FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel = this$0.p;
                    ActivityCategoryAdapterNew activityCategoryAdapterNew = null;
                    if (fragmentActivityCategoryViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentActivityCategoryViewModel = null;
                    }
                    List<ActivitiesListModel> list2 = this$0.u;
                    ArrayList<ActivityCategoriesModel> arrayList2 = this$0.s;
                    Intrinsics.checkNotNull(arrayList2);
                    this$0.t = fragmentActivityCategoryViewModel.getExpandableHashMap(list2, arrayList2);
                    Context context = this$0.getContext();
                    Intrinsics.checkNotNull(context);
                    ArrayList<ActivityCategoriesModel> arrayList3 = this$0.q;
                    HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap = this$0.t;
                    Intrinsics.checkNotNull(hashMap);
                    this$0.r = new ActivityCategoryAdapterNew(context, arrayList3, hashMap, this$0, this$0);
                    FragmentActivity activity = this$0.getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
                    OneKActivityViewModel viewModel = ((OneKActivity) activity).getViewModel();
                    ConfiguredActivities.ActivityInfo[] activityInfoList = viewModel != null ? viewModel.getActivityInfoList() : null;
                    if (activityInfoList != null && activityInfoList.length > 0) {
                        ActivityCategoryAdapterNew activityCategoryAdapterNew2 = this$0.r;
                        if (activityCategoryAdapterNew2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("expandableListAdapter");
                            activityCategoryAdapterNew2 = null;
                        }
                        activityCategoryAdapterNew2.setActivityInfoList(activityInfoList);
                    }
                    int i = R.id.rv_activity;
                    ExpandableListView expandableListView = (ExpandableListView) this$0._$_findCachedViewById(i);
                    Intrinsics.checkNotNull(expandableListView);
                    ActivityCategoryAdapterNew activityCategoryAdapterNew3 = this$0.r;
                    if (activityCategoryAdapterNew3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("expandableListAdapter");
                        activityCategoryAdapterNew3 = null;
                    }
                    expandableListView.setAdapter(activityCategoryAdapterNew3);
                    ((ExpandableListView) this$0._$_findCachedViewById(i)).setGroupIndicator(null);
                    ActivityCategoryAdapterNew activityCategoryAdapterNew4 = this$0.r;
                    if (activityCategoryAdapterNew4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("expandableListAdapter");
                    } else {
                        activityCategoryAdapterNew = activityCategoryAdapterNew4;
                    }
                    ArrayList<ActivityCategoriesModel> arrayList4 = this$0.s;
                    Intrinsics.checkNotNull(arrayList4);
                    HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap2 = this$0.t;
                    Intrinsics.checkNotNull(hashMap2);
                    activityCategoryAdapterNew.notifyAdapter(arrayList4, hashMap2);
                    this$0.dismissProgress();
                }
            }
        }
    }

    public static final void r(FragmentCategories this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            ArrayList<ActivityCategoriesModel> arrayList = this$0.s;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
            ArrayList<ActivityCategoriesModel> arrayList2 = this$0.s;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.addAll(list);
            FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel = this$0.p;
            FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel2 = null;
            if (fragmentActivityCategoryViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentActivityCategoryViewModel = null;
            }
            fragmentActivityCategoryViewModel.getActivityListFromDb();
            FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel3 = this$0.p;
            if (fragmentActivityCategoryViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentActivityCategoryViewModel2 = fragmentActivityCategoryViewModel3;
            }
            LiveData<List<ActivitiesListModel>> activityLiveData = fragmentActivityCategoryViewModel2.getActivityLiveData();
            if (activityLiveData != null) {
                activityLiveData.observe(this$0, this$0.E);
            }
        }
    }

    public static final void u(FragmentCategories this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_SEARCH_ACTIVITY.getValue());
        if (!this$0.o) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ACTIVITY_TAB_ON_HOME_DASH.getValue());
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_FTU_SCREEN_TWO.getValue());
        }
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_1K_CATEGORY_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final void v(final FragmentCategories this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.getContext())) {
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context);
            if (AppUtils.isBluetoothEnabled(context)) {
                ConnectionStatus connectionStatus = BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus();
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    ActivityCategoriesModel activityCategoriesModel = this$0.v;
                    if (activityCategoriesModel == null && this$0.x == null) {
                        Toast.makeText(this$0.getContext(), R.string.please_select_category, 1).show();
                        return;
                    }
                    if (activityCategoriesModel != null && this$0.x == null) {
                        FragmentActivitySelectionNew newInstance = FragmentActivitySelectionNew.Companion.newInstance();
                        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
                        Context context2 = this$0.getContext();
                        Intrinsics.checkNotNull(context2);
                        ActivityCategoriesModel activityCategoriesModel2 = this$0.v;
                        Intrinsics.checkNotNull(activityCategoriesModel2);
                        companion.getInstance(context2).setActivityCategoriesModel(activityCategoriesModel2);
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_CAT_NEXT.getValue());
                        if (!this$0.o) {
                            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ACTIVITY_TAB_ON_HOME_DASH.getValue());
                        } else {
                            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_FTU_SCREEN_TWO.getValue());
                        }
                        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_1K_CATEGORY_SCREEN.getValue());
                        ActivityCategoriesModel activityCategoriesModel3 = this$0.v;
                        Intrinsics.checkNotNull(activityCategoriesModel3);
                        Integer categoryId = activityCategoriesModel3.getCategoryId();
                        Intrinsics.checkNotNull(categoryId);
                        analyticsLog.setCategoryId(String.valueOf(categoryId.intValue()));
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        FragmentActivity activity = this$0.getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
                        OneKActivity.replaceFragment$default((OneKActivity) activity, newInstance, false, 2, null);
                        return;
                    } else if (this$0.x == null || activityCategoriesModel == null) {
                        return;
                    } else {
                        FragmentReplaceActivity.Companion.newInstance();
                        SingletonOneKActivity.Companion companion2 = SingletonOneKActivity.Companion;
                        Context context3 = this$0.getContext();
                        Intrinsics.checkNotNull(context3);
                        ActivitiesListModel activitiesListModel = this$0.x;
                        Intrinsics.checkNotNull(activitiesListModel);
                        companion2.getInstance(context3).setPhysicalActivity(activitiesListModel);
                        Context context4 = this$0.getContext();
                        Intrinsics.checkNotNull(context4);
                        ActivityCategoriesModel activityCategoriesModel4 = this$0.v;
                        Intrinsics.checkNotNull(activityCategoriesModel4);
                        companion2.getInstance(context4).setActivityCategoriesModel(activityCategoriesModel4);
                        ((EditText) this$0._$_findCachedViewById(R.id.edt_search)).getText().clear();
                        AnalyticsLog analyticsLog2 = new AnalyticsLog();
                        analyticsLog2.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_SUBCAT_NEXT.getValue());
                        if (!this$0.o) {
                            analyticsLog2.setCVPrevScreenName(FirebaseEventParams.ScreenName.ACTIVITY_TAB_ON_HOME_DASH.getValue());
                        } else {
                            analyticsLog2.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_FTU_SCREEN_TWO.getValue());
                        }
                        analyticsLog2.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_1K_CATEGORY_SCREEN.getValue());
                        ActivitiesListModel activitiesListModel2 = this$0.x;
                        Intrinsics.checkNotNull(activitiesListModel2);
                        String activityCode = activitiesListModel2.getActivityCode();
                        Intrinsics.checkNotNull(activityCode);
                        analyticsLog2.setActivityCode(activityCode.toString());
                        CoveAnalyticsManager.Companion companion3 = CoveAnalyticsManager.Companion;
                        companion3.getInstance().logEvent(analyticsLog2);
                        List<CategoryAndActivityModel> list = this$0.y;
                        Intrinsics.checkNotNull(list);
                        CategoryAndActivityModel categoryAndActivityModel = this$0.z;
                        if (categoryAndActivityModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                            categoryAndActivityModel = null;
                        }
                        list.remove(categoryAndActivityModel);
                        Context requireContext = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        List<CategoryAndActivityModel> list2 = this$0.y;
                        Intrinsics.checkNotNull(list2);
                        companion2.getInstance(requireContext).setCategoryAndActivityList(list2);
                        Context requireContext2 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        this$0.v = companion2.getInstance(requireContext2).getActivityCategoriesModel();
                        Context requireContext3 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        this$0.y = companion2.getInstance(requireContext3).getCategoryAndActivityList();
                        Context requireContext4 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        this$0.w = companion2.getInstance(requireContext4).getPhysicalActivity();
                        FragmentActivity activity2 = this$0.getActivity();
                        Intrinsics.checkNotNull(activity2);
                        ViewModel viewModel = ViewModelProviders.of(activity2).get(FragmentReArrangeViewModel.class);
                        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!)\n         …ngeViewModel::class.java)");
                        FragmentReArrangeViewModel fragmentReArrangeViewModel = (FragmentReArrangeViewModel) viewModel;
                        this$0.B = fragmentReArrangeViewModel;
                        if (fragmentReArrangeViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                            fragmentReArrangeViewModel = null;
                        }
                        fragmentReArrangeViewModel.setActivityUpdateListener(this$0);
                        FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this$0.B;
                        if (fragmentReArrangeViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                            fragmentReArrangeViewModel2 = null;
                        }
                        this$0.A = fragmentReArrangeViewModel2.getCategoryAndActivityModel(this$0.v, this$0.w);
                        List<CategoryAndActivityModel> list3 = this$0.y;
                        Intrinsics.checkNotNull(list3);
                        CategoryAndActivityModel categoryAndActivityModel2 = this$0.A;
                        if (categoryAndActivityModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                            categoryAndActivityModel2 = null;
                        }
                        list3.add(0, categoryAndActivityModel2);
                        AnalyticsLog analyticsLog3 = new AnalyticsLog();
                        analyticsLog3.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_EXISITING_CONFIRM.getValue());
                        analyticsLog3.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_ACTIVITY_LIST_SCREEN.getValue());
                        analyticsLog3.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN.getValue());
                        CategoryAndActivityModel categoryAndActivityModel3 = this$0.z;
                        if (categoryAndActivityModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                            categoryAndActivityModel3 = null;
                        }
                        analyticsLog3.setOldActivityCode(String.valueOf(categoryAndActivityModel3.getActivityCode()));
                        ActivitiesListModel activitiesListModel3 = this$0.w;
                        analyticsLog3.setNewActivityCode(String.valueOf(activitiesListModel3 != null ? activitiesListModel3.getActivityCode() : null));
                        companion3.getInstance().logEvent(analyticsLog3);
                        if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            BleApiManager.getInstance(this$0.getContext()).getBleApi().getData(new GetImageIdListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories$onActivityCreated$4$1
                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onDataError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    FragmentCategories fragmentCategories = FragmentCategories.this;
                                    int i = R.id.btn_next;
                                    if (((Button) fragmentCategories._$_findCachedViewById(i)) != null) {
                                        ((Button) FragmentCategories.this._$_findCachedViewById(i)).setEnabled(true);
                                    }
                                }

                                /* JADX WARN: Removed duplicated region for block: B:32:0x009d  */
                                /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                    To view partially-correct add '--show-bad-code' argument
                                */
                                public void onDataResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseResponse r6) {
                                    /*
                                        r5 = this;
                                        java.lang.String r0 = "response"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                        java.lang.Object r0 = r6.getResponseData()
                                        boolean r0 = r0 instanceof com.coveiot.android.bleabstract.response.GetImageListResponse
                                        if (r0 == 0) goto La3
                                        java.lang.Object r6 = r6.getResponseData()
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                                        com.coveiot.android.bleabstract.response.GetImageListResponse r6 = (com.coveiot.android.bleabstract.response.GetImageListResponse) r6
                                        java.util.List r6 = r6.getImageIdList()
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getNewActivityCategory$p(r0)
                                        java.lang.String r1 = "newActivityCategory"
                                        r2 = 0
                                        if (r0 != 0) goto L2c
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                                        r0 = r2
                                    L2c:
                                        java.lang.Integer r0 = r0.getDefaultActivityIcon()
                                        boolean r0 = r6.contains(r0)
                                        r3 = 0
                                        java.lang.String r4 = "viewModelRearrange"
                                        if (r0 != 0) goto L7d
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getNewActivityCategory$p(r0)
                                        if (r0 != 0) goto L45
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                                        r0 = r2
                                    L45:
                                        java.lang.Integer r0 = r0.getDefaultCategoryIcon()
                                        boolean r6 = r6.contains(r0)
                                        if (r6 != 0) goto L7d
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getNewActivityCategory$p(r6)
                                        if (r0 != 0) goto L5b
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                                        r0 = r2
                                    L5b:
                                        boolean r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$isDefaultCategory(r6, r0)
                                        if (r6 != 0) goto L7d
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getViewModelRearrange$p(r6)
                                        if (r6 != 0) goto L6d
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
                                        r6 = r2
                                    L6d:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getNewActivityCategory$p(r0)
                                        if (r0 != 0) goto L79
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                                        r0 = r2
                                    L79:
                                        r6.saveImageToInternal(r0, r3)
                                        goto L95
                                    L7d:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getViewModelRearrange$p(r6)
                                        if (r6 != 0) goto L89
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
                                        r6 = r2
                                    L89:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        java.util.List r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.access$getCategoryAndActivityList$p(r0)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        r6.setConfigureActivityListRequest(r0)
                                    L95:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        boolean r6 = r6.isAdded()
                                        if (r6 == 0) goto La3
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories r6 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories.this
                                        r0 = 1
                                        com.coveiot.android.theme.BaseFragment.showProgress$default(r6, r3, r0, r2)
                                    La3:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories$onActivityCreated$4$1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onProgressUpdate(@NotNull ProgressData progress) {
                                    Intrinsics.checkNotNullParameter(progress, "progress");
                                }
                            });
                            return;
                        }
                        return;
                    }
                }
                FragmentActivity activity3 = this$0.getActivity();
                Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
                ((OneKActivity) activity3).showBandNotConnected(false);
                return;
            }
            this$0.showNoBluetoothDialog();
            return;
        }
        this$0.showNoInternetDialog();
    }

    public static final void x(final FragmentCategories this$0, Dialog mAlertDialog, View view) {
        DeviceIconModel deviceIconModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        if (!AppUtils.isNetConnected(this$0.getContext())) {
            Toast.makeText(this$0.requireContext(), R.string.please_check_network, 0).show();
        } else if (!AppUtils.isBluetoothEnabled(this$0.getContext())) {
            Toast.makeText(this$0.requireContext(), R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0.requireContext(), R.string.band_not_connected, 0).show();
        } else {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            if (this$0.z == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
            }
            CategoryAndActivityModel categoryAndActivityModel = this$0.z;
            Integer num = null;
            if (categoryAndActivityModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                categoryAndActivityModel = null;
            }
            if (!categoryAndActivityModel.getInbuilt() && BleApiManager.getInstance(this$0.getContext()).getBleApi() != null) {
                BaseFragment.showProgress$default(this$0, false, 1, null);
                BleApi bleApi = BleApiManager.getInstance(this$0.getContext()).getBleApi();
                CategoryAndActivityModel categoryAndActivityModel2 = this$0.z;
                if (categoryAndActivityModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                    categoryAndActivityModel2 = null;
                }
                List<DeviceIconModel> deviceIconModels = categoryAndActivityModel2.getDeviceIconModels();
                if (deviceIconModels != null && (deviceIconModel = deviceIconModels.get(0)) != null) {
                    num = deviceIconModel.getRefId();
                }
                Intrinsics.checkNotNull(num);
                bleApi.setUserSettings(new DeleteImageRequest(num.intValue()), new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories$showBluetoothDialog$2$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        FragmentCategories.this.dismissProgress();
                        FragmentCategories.this.t();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        CategoryAndActivityModel categoryAndActivityModel3;
                        CategoryAndActivityModel categoryAndActivityModel4;
                        DeviceIconModel deviceIconModel2;
                        Intrinsics.checkNotNullParameter(response, "response");
                        categoryAndActivityModel3 = FragmentCategories.this.z;
                        Integer num2 = null;
                        if (categoryAndActivityModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                            categoryAndActivityModel3 = null;
                        }
                        List<DeviceIconModel> deviceIconModels2 = categoryAndActivityModel3.getDeviceIconModels();
                        Integer valueOf = deviceIconModels2 != null ? Integer.valueOf(deviceIconModels2.size()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 1) {
                            BleApi bleApi2 = BleApiManager.getInstance(FragmentCategories.this.getContext()).getBleApi();
                            categoryAndActivityModel4 = FragmentCategories.this.z;
                            if (categoryAndActivityModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                                categoryAndActivityModel4 = null;
                            }
                            List<DeviceIconModel> deviceIconModels3 = categoryAndActivityModel4.getDeviceIconModels();
                            if (deviceIconModels3 != null && (deviceIconModel2 = deviceIconModels3.get(1)) != null) {
                                num2 = deviceIconModel2.getRefId();
                            }
                            Intrinsics.checkNotNull(num2);
                            DeleteImageRequest deleteImageRequest = new DeleteImageRequest(num2.intValue());
                            final FragmentCategories fragmentCategories = FragmentCategories.this;
                            bleApi2.setUserSettings(deleteImageRequest, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories$showBluetoothDialog$2$1$onSettingsResponse$1
                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    FragmentCategories.this.dismissProgress();
                                    FragmentCategories.this.t();
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                                    Intrinsics.checkNotNullParameter(response2, "response");
                                    FragmentCategories.this.dismissProgress();
                                    FragmentCategories.this.t();
                                }
                            });
                            return;
                        }
                        FragmentCategories.this.dismissProgress();
                        FragmentCategories.this.t();
                    }
                });
                return;
            }
            this$0.t();
        }
    }

    public static final void y(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, FragmentCategories this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
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

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew.SearchAction
    public void collapseGroup(int i) {
        int i2 = R.id.rv_activity;
        if (((ExpandableListView) _$_findCachedViewById(i2)) == null || ((ExpandableListView) _$_findCachedViewById(i2)).getExpandableListAdapter() == null) {
            return;
        }
        ((ExpandableListView) _$_findCachedViewById(i2)).collapseGroup(i);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew.SearchAction
    public void expandGroup(int i) {
        int i2 = R.id.rv_activity;
        if (((ExpandableListView) _$_findCachedViewById(i2)) == null || ((ExpandableListView) _$_findCachedViewById(i2)).getExpandableListAdapter() == null) {
            return;
        }
        ((ExpandableListView) _$_findCachedViewById(i2)).expandGroup(i);
    }

    @Nullable
    public final HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> getExpandableListDetail() {
        return this.t;
    }

    @Nullable
    public final ArrayList<ActivityCategoriesModel> getExpandableListTitle() {
        return this.s;
    }

    public final boolean isFromFTU() {
        return this.o;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        Companion companion = Companion;
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity).setupToolbar(companion.newInstance(((OneKActivity) activity2).getISFROMFTU()));
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        ViewModel viewModel = ViewModelProviders.of(activity3).get(FragmentActivityCategoryViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…oryViewModel::class.java)");
        this.p = (FragmentActivityCategoryViewModel) viewModel;
        new LinearLayoutManager(getContext());
        SingletonOneKActivity.Companion companion2 = SingletonOneKActivity.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.y = companion2.getInstance(requireContext).getCategoryAndActivityList();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        CategoryAndActivityModel selectedActivity = companion2.getInstance(requireContext2).getSelectedActivity();
        Intrinsics.checkNotNull(selectedActivity);
        this.z = selectedActivity;
        FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel = this.p;
        FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel2 = null;
        if (fragmentActivityCategoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivityCategoryViewModel = null;
        }
        fragmentActivityCategoryViewModel.getCategoryListFromDb();
        FragmentActivity activity4 = getActivity();
        Intrinsics.checkNotNull(activity4);
        ViewModel viewModel2 = ViewModelProviders.of(activity4).get(ActivityAutoRecognitionViewModelWithOneK.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(activity!!)\n         …odelWithOneK::class.java)");
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = (ActivityAutoRecognitionViewModelWithOneK) viewModel2;
        this.C = activityAutoRecognitionViewModelWithOneK;
        if (activityAutoRecognitionViewModelWithOneK == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelAutoActivityDetection");
            activityAutoRecognitionViewModelWithOneK = null;
        }
        activityAutoRecognitionViewModelWithOneK.setSettingUpdateListener(this);
        BaseFragment.showProgress$default(this, false, 1, null);
        FragmentActivityCategoryViewModel fragmentActivityCategoryViewModel3 = this.p;
        if (fragmentActivityCategoryViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentActivityCategoryViewModel2 = fragmentActivityCategoryViewModel3;
        }
        LiveData<List<ActivityCategoriesModel>> categoryLiveData = fragmentActivityCategoryViewModel2.getCategoryLiveData();
        if (categoryLiveData != null) {
            categoryLiveData.observe(this, this.D);
        }
        int i = R.id.edt_search;
        EditText editText = (EditText) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(editText);
        editText.addTextChangedListener(new FragmentCategories$onActivityCreated$1(this));
        ((EditText) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCategories.u(FragmentCategories.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(i)).setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories$onActivityCreated$3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(@Nullable TextView textView, int i2, @Nullable KeyEvent keyEvent) {
                if (i2 == 3) {
                    FragmentCategories.this.hideKeyBoard();
                    return true;
                }
                return false;
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCategories.v(FragmentCategories.this, view);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onActivityUpload() {
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.getInstance(requireContext).getIsSelectedForAutoActivityDetection()) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            AutoActivityDetectionData autoActivityDetectionData = UserDataManager.getInstance(activity).getAutoActivityDetectionData();
            if (autoActivityDetectionData != null) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                String autoActivityDetectionCode = companion.getInstance(requireContext2).getAutoActivityDetectionCode();
                Unit unit = null;
                ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = null;
                if (autoActivityDetectionCode != null) {
                    autoActivityDetectionData.getActivities()[WorkoutUtils.INSTANCE.getAutoActivityDetectionFWOrderForAnActivity(autoActivityDetectionCode)] = 0;
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = this.C;
                    if (activityAutoRecognitionViewModelWithOneK2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelAutoActivityDetection");
                    } else {
                        activityAutoRecognitionViewModelWithOneK = activityAutoRecognitionViewModelWithOneK2;
                    }
                    activityAutoRecognitionViewModelWithOneK.setAutoRecognitionToWatch(autoActivityDetectionData);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    dismissProgress();
                    int i = R.id.btn_next;
                    if (((Button) _$_findCachedViewById(i)) != null) {
                        ((Button) _$_findCachedViewById(i)).setEnabled(true);
                    }
                    w();
                    return;
                }
                return;
            }
            dismissProgress();
            int i2 = R.id.btn_next;
            if (((Button) _$_findCachedViewById(i2)) != null) {
                ((Button) _$_findCachedViewById(i2)).setEnabled(true);
            }
            w();
            return;
        }
        dismissProgress();
        int i3 = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i3)) != null) {
            ((Button) _$_findCachedViewById(i3)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener
    public void onAutoDetectionFailure(@Nullable String str) {
        dismissProgress();
        int i = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i)) != null) {
            ((Button) _$_findCachedViewById(i)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener
    public void onAutoDetectionSuccess() {
        dismissProgress();
        int i = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i)) != null) {
            ((Button) _$_findCachedViewById(i)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew.ItemClickListener
    public void onChildClicked(@NotNull ActivityCategoriesModel activityCategory, @Nullable ActivitiesListModel activitiesListModel) {
        Intrinsics.checkNotNullParameter(activityCategory, "activityCategory");
        this.v = activityCategory;
        this.x = activitiesListModel;
        int i = R.id.btn_next;
        ((Button) _$_findCachedViewById(i)).setEnabled(true);
        ((Button) _$_findCachedViewById(i)).performClick();
        hideKeyBoard();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("IS_FROM_FTU")) : null;
            Intrinsics.checkNotNull(valueOf);
            this.o = valueOf.booleanValue();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_activities_category, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew.ItemClickListener
    public void onHeaderClicked(@Nullable ActivityCategoriesModel activityCategoriesModel, int i) {
        this.v = activityCategoriesModel;
        this.x = null;
        int i2 = R.id.btn_next;
        ((Button) _$_findCachedViewById(i2)).setEnabled(true);
        hideKeyBoard();
        ((Button) _$_findCachedViewById(i2)).performClick();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onImageUpload(int i) {
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel = null;
        CategoryAndActivityModel categoryAndActivityModel = null;
        FragmentReArrangeViewModel fragmentReArrangeViewModel2 = null;
        if (i == 0) {
            CategoryAndActivityModel categoryAndActivityModel2 = this.A;
            if (categoryAndActivityModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                categoryAndActivityModel2 = null;
            }
            List<DeviceIconModel> deviceIconModels = categoryAndActivityModel2.getDeviceIconModels();
            Integer valueOf = deviceIconModels != null ? Integer.valueOf(deviceIconModels.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 1) {
                FragmentReArrangeViewModel fragmentReArrangeViewModel3 = this.B;
                if (fragmentReArrangeViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                    fragmentReArrangeViewModel3 = null;
                }
                CategoryAndActivityModel categoryAndActivityModel3 = this.A;
                if (categoryAndActivityModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                } else {
                    categoryAndActivityModel = categoryAndActivityModel3;
                }
                fragmentReArrangeViewModel3.saveImageToInternal(categoryAndActivityModel, 1);
                return;
            }
            FragmentReArrangeViewModel fragmentReArrangeViewModel4 = this.B;
            if (fragmentReArrangeViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
            } else {
                fragmentReArrangeViewModel2 = fragmentReArrangeViewModel4;
            }
            List<CategoryAndActivityModel> list = this.y;
            Intrinsics.checkNotNull(list);
            fragmentReArrangeViewModel2.setConfigureActivityListRequest(list);
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel5 = this.B;
        if (fragmentReArrangeViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
        } else {
            fragmentReArrangeViewModel = fragmentReArrangeViewModel5;
        }
        List<CategoryAndActivityModel> list2 = this.y;
        Intrinsics.checkNotNull(list2);
        fragmentReArrangeViewModel.setConfigureActivityListRequest(list2);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew.ItemClickListener
    public void onLongClicked(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.SELECT_1K_CATEGORY_SCREEN.getValue());
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onUpdateFailed() {
        if (getActivity() != null) {
            dismissProgress();
            int i = R.id.btn_next;
            if (((Button) _$_findCachedViewById(i)) != null) {
                ((Button) _$_findCachedViewById(i)).setEnabled(true);
            }
            Toast.makeText(getContext(), getString(R.string.activity_config_failed), 0).show();
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            activity.finish();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onWatchBusyStatusReceived() {
        if (getActivity() != null && isAdded()) {
            dismissProgress();
        }
        showWatchBusyDialog();
    }

    public final boolean s(CategoryAndActivityModel categoryAndActivityModel) {
        return false;
    }

    public final void setExpandableListDetail(@Nullable HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap) {
        this.t = hashMap;
    }

    public final void setExpandableListTitle(@Nullable ArrayList<ActivityCategoriesModel> arrayList) {
        this.s = arrayList;
    }

    public final void setFromFTU(boolean z) {
        this.o = z;
    }

    public final void showWatchBusyDialog() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.activity_config_failed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.activity_config_failed)");
        String string2 = getString(R.string.make_sure_no_running_activity);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.make_sure_no_running_activity)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = requireActivity().getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "requireActivity().getStr…ndroid.theme.R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCategories.y(BottomSheetDialogOneButtonTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void t() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public final void w() {
        if (isAdded()) {
            AlertDialog alertDialog = this.n;
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                this.n = null;
            }
            final Dialog dialog = new Dialog(requireContext(), R.style.DialogTheme);
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
            this.m = layoutInflater.inflate(R.layout.dialog_activity_confirm, (ViewGroup) null);
            SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            CategoryAndActivityModel selectedActivity = companion.getInstance(requireContext).getSelectedActivity();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ActivitiesListModel physicalActivity = companion.getInstance(requireContext2).getPhysicalActivity();
            View view = this.m;
            Intrinsics.checkNotNull(view);
            TextView textView = (TextView) view.findViewById(R.id.txt_msg);
            int i = R.string.activity_available_on_watch;
            Object[] objArr = new Object[2];
            objArr[0] = selectedActivity != null ? selectedActivity.getTitleInMetric() : null;
            objArr[1] = physicalActivity != null ? physicalActivity.getTitleInMetric() : null;
            textView.setText(getString(i, objArr));
            View view2 = this.m;
            Intrinsics.checkNotNull(view2);
            TextView textView2 = (TextView) view2.findViewById(R.id.tv_warning_auto_detection);
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            if (companion.getInstance(requireContext3).getIsSelectedForAutoActivityDetection()) {
                textView2.setVisibility(0);
                int i2 = R.string.walking_will_not_be_auto_detected_as_it_has_been_removed_from_the_watch;
                Object[] objArr2 = new Object[1];
                objArr2[0] = selectedActivity != null ? selectedActivity.getTitleInMetric() : null;
                textView2.setText(getString(i2, objArr2));
            } else {
                textView2.setVisibility(8);
            }
            View view3 = this.m;
            Intrinsics.checkNotNull(view3);
            dialog.setContentView(view3);
            View view4 = this.m;
            Intrinsics.checkNotNull(view4);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            ((Button) view4.findViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view5) {
                    FragmentCategories.x(FragmentCategories.this, dialog, view5);
                }
            });
        }
    }
}
