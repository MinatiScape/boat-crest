package com.coveiot.android.activitymodes.activity1k.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityReArrangeAdapter;
import com.coveiot.android.activitymodes.activity1k.adapter.DragManageAdapter;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.android.theme.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivityReArrange extends BaseFragment implements ActivityReArrangeAdapter.ItemClickListener, FragmentReArrangeViewModel.ActivityUpdateListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentReArrangeViewModel m;
    public ActivityReArrangeAdapter n;
    @Nullable
    public List<CategoryAndActivityModel> o;
    @Nullable
    public ActivitiesListModel p;
    @Nullable
    public ActivityCategoriesModel q;
    @Nullable
    public View r;
    @Nullable
    public AlertDialog s;
    @Nullable
    public CategoryAndActivityModel t;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentActivityReArrange newInstance() {
            return new FragmentActivityReArrange();
        }
    }

    public FragmentActivityReArrange() {
        new ArrayList();
        this.o = new ArrayList();
    }

    public static final void n(final FragmentActivityReArrange this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this$0.getContext()).getBleApi().getData(new GetImageIdListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivityReArrange$onActivityCreated$1$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    CategoryAndActivityModel categoryAndActivityModel;
                    FragmentReArrangeViewModel fragmentReArrangeViewModel;
                    List<CategoryAndActivityModel> list;
                    CategoryAndActivityModel categoryAndActivityModel2;
                    CategoryAndActivityModel categoryAndActivityModel3;
                    boolean m;
                    FragmentReArrangeViewModel fragmentReArrangeViewModel2;
                    CategoryAndActivityModel categoryAndActivityModel4;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof GetImageListResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData);
                        List<Integer> imageIdList = ((GetImageListResponse) responseData).getImageIdList();
                        Intrinsics.checkNotNull(imageIdList);
                        categoryAndActivityModel = FragmentActivityReArrange.this.t;
                        Intrinsics.checkNotNull(categoryAndActivityModel);
                        if (!imageIdList.contains(categoryAndActivityModel.getDefaultActivityIcon())) {
                            categoryAndActivityModel2 = FragmentActivityReArrange.this.t;
                            Intrinsics.checkNotNull(categoryAndActivityModel2);
                            if (!imageIdList.contains(categoryAndActivityModel2.getDefaultCategoryIcon())) {
                                FragmentActivityReArrange fragmentActivityReArrange = FragmentActivityReArrange.this;
                                categoryAndActivityModel3 = fragmentActivityReArrange.t;
                                Intrinsics.checkNotNull(categoryAndActivityModel3);
                                m = fragmentActivityReArrange.m(categoryAndActivityModel3);
                                if (!m) {
                                    fragmentReArrangeViewModel2 = FragmentActivityReArrange.this.m;
                                    if (fragmentReArrangeViewModel2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        fragmentReArrangeViewModel2 = null;
                                    }
                                    categoryAndActivityModel4 = FragmentActivityReArrange.this.t;
                                    Intrinsics.checkNotNull(categoryAndActivityModel4);
                                    fragmentReArrangeViewModel2.saveImageToInternal(categoryAndActivityModel4, 0);
                                    BaseFragment.showProgress$default(FragmentActivityReArrange.this, false, 1, null);
                                }
                            }
                        }
                        fragmentReArrangeViewModel = FragmentActivityReArrange.this.m;
                        if (fragmentReArrangeViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            fragmentReArrangeViewModel = null;
                        }
                        list = FragmentActivityReArrange.this.o;
                        Intrinsics.checkNotNull(list);
                        fragmentReArrangeViewModel.setConfigureActivityListRequest(list);
                        BaseFragment.showProgress$default(FragmentActivityReArrange.this, false, 1, null);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }
    }

    public static final void p(FragmentActivityReArrange this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog alertDialog = this$0.s;
        Intrinsics.checkNotNull(alertDialog);
        if (alertDialog.isShowing()) {
            AlertDialog alertDialog2 = this$0.s;
            Intrinsics.checkNotNull(alertDialog2);
            alertDialog2.dismiss();
        }
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.finish();
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

    public final boolean m(CategoryAndActivityModel categoryAndActivityModel) {
        Integer categoryId = categoryAndActivityModel.getCategoryId();
        return ((((categoryId != null && categoryId.intValue() == 2) || (categoryId != null && categoryId.intValue() == 3)) || (categoryId != null && categoryId.intValue() == 12)) || (categoryId != null && categoryId.intValue() == 15)) || (categoryId != null && categoryId.intValue() == 17);
    }

    public final void o() {
        AlertDialog alertDialog = this.s;
        if (alertDialog != null) {
            Intrinsics.checkNotNull(alertDialog);
            if (alertDialog.isShowing()) {
                AlertDialog alertDialog2 = this.s;
                Intrinsics.checkNotNull(alertDialog2);
                alertDialog2.dismiss();
            }
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_activity_confirm, (ViewGroup) null);
        this.r = inflate;
        builder.setView(inflate);
        View view = this.r;
        Intrinsics.checkNotNull(view);
        AlertDialog create = builder.create();
        this.s = create;
        Intrinsics.checkNotNull(create);
        create.show();
        ((Button) view.findViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentActivityReArrange.p(FragmentActivityReArrange.this, view2);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity).setupToolbar(Companion.newInstance());
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        this.q = companion.getInstance(context).getActivityCategoriesModel();
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        this.o = companion.getInstance(context2).getCategoryAndActivityList();
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        this.p = companion.getInstance(context3).getPhysicalActivity();
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        ViewModel viewModel = ViewModelProviders.of(activity2).get(FragmentReArrangeViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…ngeViewModel::class.java)");
        FragmentReArrangeViewModel fragmentReArrangeViewModel = (FragmentReArrangeViewModel) viewModel;
        this.m = fragmentReArrangeViewModel;
        ActivityReArrangeAdapter activityReArrangeAdapter = null;
        if (fragmentReArrangeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentReArrangeViewModel = null;
        }
        fragmentReArrangeViewModel.setActivityUpdateListener(this);
        FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this.m;
        if (fragmentReArrangeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentReArrangeViewModel2 = null;
        }
        this.t = fragmentReArrangeViewModel2.getCategoryAndActivityModel(this.q, this.p);
        List<CategoryAndActivityModel> list = this.o;
        Intrinsics.checkNotNull(list);
        CategoryAndActivityModel categoryAndActivityModel = this.t;
        Intrinsics.checkNotNull(categoryAndActivityModel);
        list.add(0, categoryAndActivityModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        int i = R.id.rv_activity;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<CategoryAndActivityModel> list2 = this.o;
        Intrinsics.checkNotNull(list2);
        this.n = new ActivityReArrangeAdapter(list2, this);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView2);
        ActivityReArrangeAdapter activityReArrangeAdapter2 = this.n;
        if (activityReArrangeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            activityReArrangeAdapter2 = null;
        }
        recyclerView2.setAdapter(activityReArrangeAdapter2);
        ActivityReArrangeAdapter activityReArrangeAdapter3 = this.n;
        if (activityReArrangeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            activityReArrangeAdapter = activityReArrangeAdapter3;
        }
        Context context4 = getContext();
        Intrinsics.checkNotNull(context4);
        new ItemTouchHelper(new DragManageAdapter(activityReArrangeAdapter, context4, 3, 12)).attachToRecyclerView((RecyclerView) _$_findCachedViewById(i));
        ((Button) _$_findCachedViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivityReArrange.n(FragmentActivityReArrange.this, view);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onActivityUpload() {
        dismissProgress();
        o();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityReArrangeAdapter.ItemClickListener
    public void onClicked(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_activity_rearrange, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
        List<CategoryAndActivityModel> list = this.o;
        Intrinsics.checkNotNull(list);
        List<CategoryAndActivityModel> list2 = this.o;
        Intrinsics.checkNotNull(list2);
        list.remove(CollectionsKt___CollectionsKt.indexOf((List<? extends CategoryAndActivityModel>) list2, this.t));
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        List<CategoryAndActivityModel> list3 = this.o;
        Intrinsics.checkNotNull(list3);
        companion.getInstance(context).setCategoryAndActivityList(list3);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onImageUpload(int i) {
        List<DeviceIconModel> deviceIconModels;
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel = null;
        if (i == 0) {
            CategoryAndActivityModel categoryAndActivityModel = this.t;
            Integer valueOf = (categoryAndActivityModel == null || (deviceIconModels = categoryAndActivityModel.getDeviceIconModels()) == null) ? null : Integer.valueOf(deviceIconModels.size());
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 1) {
                FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this.m;
                if (fragmentReArrangeViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentReArrangeViewModel = fragmentReArrangeViewModel2;
                }
                CategoryAndActivityModel categoryAndActivityModel2 = this.t;
                Intrinsics.checkNotNull(categoryAndActivityModel2);
                fragmentReArrangeViewModel.saveImageToInternal(categoryAndActivityModel2, 1);
                return;
            }
            FragmentReArrangeViewModel fragmentReArrangeViewModel3 = this.m;
            if (fragmentReArrangeViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentReArrangeViewModel = fragmentReArrangeViewModel3;
            }
            List<CategoryAndActivityModel> list = this.o;
            Intrinsics.checkNotNull(list);
            fragmentReArrangeViewModel.setConfigureActivityListRequest(list);
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel4 = this.m;
        if (fragmentReArrangeViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentReArrangeViewModel = fragmentReArrangeViewModel4;
        }
        List<CategoryAndActivityModel> list2 = this.o;
        Intrinsics.checkNotNull(list2);
        fragmentReArrangeViewModel.setConfigureActivityListRequest(list2);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityReArrangeAdapter.ItemClickListener
    public void onLongClicked(int i) {
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onUpdateFailed() {
        if (getActivity() != null) {
            dismissProgress();
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            activity.getSupportFragmentManager().popBackStack();
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            activity2.getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentWatchActivities.Companion.newInstance()).addToBackStack(null).commit();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onWatchBusyStatusReceived() {
    }
}
