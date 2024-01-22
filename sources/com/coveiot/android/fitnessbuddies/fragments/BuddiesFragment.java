package com.coveiot.android.fitnessbuddies.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivity;
import com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel;
import com.coveiot.android.fitnessbuddies.models.FitnessAcceptedCloversGoalData;
import com.coveiot.android.fitnessbuddies.models.FitnessAceptRejectEvent;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.CoveEventBusManager;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BuddiesFragment extends Fragment implements ManageBuddiesAdapter.OnOptionSelectedListener, ManageBuddiesContaractor {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String h = "accept";
    @NotNull
    public final String i = "reject";
    @NotNull
    public final String j = "reinvite";
    @Nullable
    public ManageBuddiesAdapter k;
    public LoadingDialog l;
    @Nullable
    public ManageBuddiesViewModel m;

    public static final void b(BuddiesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), AddBuddiesActivity.class));
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void dismissPerogress() {
        if (getView() != null) {
            LoadingDialog loadingDialog = this.l;
            LoadingDialog loadingDialog2 = null;
            if (loadingDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
                loadingDialog = null;
            }
            if (loadingDialog.isShowing()) {
                LoadingDialog loadingDialog3 = this.l;
                if (loadingDialog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
                } else {
                    loadingDialog2 = loadingDialog3;
                }
                loadingDialog2.dismiss();
            }
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onAccept(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.handleFitnessRequest(i, this.h);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        this.l = new LoadingDialog(activity);
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        Application application = activity2.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "activity!!.application");
        this.m = new ManageBuddiesViewModel(application, this);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_buddies, viewGroup, false);
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onDelete(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.deleteBuddy(i);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onFitnessRequestNotification(@NotNull FitnessAcceptedCloversGoalData event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            ManageBuddiesViewModel manageBuddiesViewModel = this.m;
            Intrinsics.checkNotNull(manageBuddiesViewModel);
            manageBuddiesViewModel.manageAllBuddies();
        }
    }

    @Subscribe
    public final void onFitnessRequestUpdated(@NotNull FitnessAceptRejectEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            ManageBuddiesViewModel manageBuddiesViewModel = this.m;
            Intrinsics.checkNotNull(manageBuddiesViewModel);
            manageBuddiesViewModel.manageAllBuddies();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onReject(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.handleFitnessRequest(i, this.i);
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onResend(int i, @NotNull String mobileNumber, @NotNull String name, @NotNull Requests buddyInfo) {
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(buddyInfo, "buddyInfo");
        if (buddyInfo.inviteLocally) {
            CoveUtils coveUtils = CoveUtils.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            String string = getString(R.string.whatsapp_uri);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.whatsapp_uri)");
            if (coveUtils.isAppInstalled(context, string)) {
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                String str = buddyInfo.toUserMobileNumber;
                Intrinsics.checkNotNullExpressionValue(str, "buddyInfo.toUserMobileNumber");
                String str2 = buddyInfo.inviteText;
                Intrinsics.checkNotNullExpressionValue(str2, "buddyInfo.inviteText");
                coveUtils.openWhatsApp(context2, str, str2);
            }
        }
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.handleFitnessRequest(i, this.j);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.manageAllBuddies();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onUnfriend(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.m;
        Intrinsics.checkNotNull(manageBuddiesViewModel);
        manageBuddiesViewModel.unfreiendBuddy(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((TextView) _$_findCachedViewById(R.id.add_buddies)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BuddiesFragment.b(BuddiesFragment.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.BUDDY_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showContent(@NotNull LinkedHashMap<String, List<Requests>> data) {
        ExpandableListView expandableListView;
        Intrinsics.checkNotNullParameter(data, "data");
        if (isAdded()) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.emptyLayout);
            if (constraintLayout != null) {
                constraintLayout.setVisibility(8);
            }
            int i = R.id.listView;
            ExpandableListView expandableListView2 = (ExpandableListView) _$_findCachedViewById(i);
            if (expandableListView2 != null) {
                expandableListView2.setVisibility(0);
            }
            this.k = new ManageBuddiesAdapter(getActivity(), this, new ArrayList(data.keySet()), data);
            ExpandableListView expandableListView3 = (ExpandableListView) _$_findCachedViewById(i);
            if (expandableListView3 != null) {
                expandableListView3.setAdapter(this.k);
            }
            List<Requests> list = data.get(getResources().getString(R.string.fitness_buddies));
            Intrinsics.checkNotNull(list);
            if (list.size() <= 0 || (expandableListView = (ExpandableListView) _$_findCachedViewById(i)) == null) {
                return;
            }
            expandableListView.expandGroup(2);
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showEmptyView() {
        if (isAdded()) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.emptyLayout);
            if (constraintLayout != null) {
                constraintLayout.setVisibility(0);
            }
            ExpandableListView expandableListView = (ExpandableListView) _$_findCachedViewById(R.id.listView);
            if (expandableListView != null) {
                expandableListView.setVisibility(8);
            }
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showProgress() {
        LoadingDialog loadingDialog = this.l;
        LoadingDialog loadingDialog2 = null;
        if (loadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
            loadingDialog = null;
        }
        if (loadingDialog.isShowing()) {
            return;
        }
        LoadingDialog loadingDialog3 = this.l;
        if (loadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
        } else {
            loadingDialog2 = loadingDialog3;
        }
        loadingDialog2.show();
    }
}
