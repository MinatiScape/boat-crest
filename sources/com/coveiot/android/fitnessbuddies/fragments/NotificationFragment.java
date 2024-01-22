package com.coveiot.android.fitnessbuddies.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.activities.ActivityBuddyDetails;
import com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivityNew;
import com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels;
import com.coveiot.android.fitnessbuddies.databinding.FragmentNotificationBuddiesBinding;
import com.coveiot.android.fitnessbuddies.dialogs.FitnessBuddyLabelDialog;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel;
import com.coveiot.android.fitnessbuddies.models.FitnessAcceptedCloversGoalData;
import com.coveiot.android.fitnessbuddies.models.FitnessAceptRejectEvent;
import com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyGoalsDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import com.squareup.otto.Subscribe;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class NotificationFragment extends BaseFragment implements NotificationsContarctor, ShowBuddiesListAdapter.OnItemClickListener, LabelBuddiesContractor {
    @NotNull
    public static final String BUDDY_ITEM = "buddy_item";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public LoadingDialog m;
    @Nullable
    public NotificationsViewModel n;
    @Nullable
    public BuddyDetailsViewModel o;
    public FragmentNotificationBuddiesBinding p;
    public ShowBuddiesListAdapter q;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final void B(FitnessBuddiesLabels fitnessBuddiesLabels, GetBuddyItems buddyItem, NotificationFragment this$0, FitnessBuddyLabelDialog fitnessBuddyLabelDialog, View view) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "$fitnessBuddiesLabels");
        Intrinsics.checkNotNullParameter(buddyItem, "$buddyItem");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fitnessBuddyLabelDialog, "$fitnessBuddyLabelDialog");
        if (fitnessBuddiesLabels == FitnessBuddiesLabels.CHEER) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.CHEER_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else if (fitnessBuddiesLabels == FitnessBuddiesLabels.APPLAUD) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.APPLAUD_POPUP.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        } else {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.NUDGE_POPUP.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
        }
        int i = 0;
        for (BuddyGoalsDetails buddyGoalsDetails : buddyItem.getGoalsList()) {
            if (m.equals(buddyGoalsDetails.activityType, "WALK", true)) {
                String str = buddyGoalsDetails.goalId;
                Intrinsics.checkNotNullExpressionValue(str, "buddyItemData.goalId");
                i = Integer.parseInt(str);
            }
        }
        BuddyDetailsViewModel buddyDetailsViewModel = this$0.o;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        buddyDetailsViewModel.sendBuddyReaction(i, fitnessBuddiesLabels, fitnessBuddyLabelDialog.getUpdateMsgEditText().getText().toString(), buddyItem);
        fitnessBuddyLabelDialog.dismiss();
    }

    public static final void t(NotificationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), AddBuddiesActivityNew.class));
    }

    public static final void u(NotificationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), AddBuddiesActivityNew.class));
    }

    public static final void v(NotificationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), AddBuddiesActivityNew.class));
    }

    public static final void x() {
    }

    public static final void y(NotificationFragment this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (num != null && num.intValue() == 0) {
            TextView textView = this$0.r().tvBadge;
            textView.setText("-- " + this$0.requireContext().getString(R.string.badges));
        } else if (num != null && num.intValue() == 1) {
            TextView textView2 = this$0.r().tvBadge;
            textView2.setText(num + ' ' + this$0.requireContext().getString(R.string.badge));
        } else {
            TextView textView3 = this$0.r().tvBadge;
            textView3.setText(num + ' ' + this$0.requireContext().getString(R.string.badges));
        }
    }

    public static final void z(NotificationFragment this$0, MyRankModel.DataBean.RankBean rankBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (rankBean != null) {
            rankBean.getRank();
            if (rankBean.getRank() == 0) {
                TextView textView = this$0.r().tvRank;
                textView.setText("-- " + this$0.requireContext().getString(R.string.rank));
                return;
            }
            TextView textView2 = this$0.r().tvRank;
            textView2.setText(rankBean.getRank() + ' ' + this$0.requireContext().getString(R.string.rank));
            return;
        }
        TextView textView3 = this$0.r().tvRank;
        textView3.setText("-- " + this$0.requireContext().getString(R.string.rank));
    }

    public final void A(final GetBuddyItems getBuddyItems, final FitnessBuddiesLabels fitnessBuddiesLabels) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final FitnessBuddyLabelDialog fitnessBuddyLabelDialog = new FitnessBuddyLabelDialog(requireContext, getBuddyItems, fitnessBuddiesLabels);
        fitnessBuddyLabelDialog.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationFragment.B(FitnessBuddiesLabels.this, getBuddyItems, this, fitnessBuddyLabelDialog, view);
            }
        });
        fitnessBuddyLabelDialog.show();
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

    @Override // com.coveiot.android.theme.BaseFragment, com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor, com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void dismissProgress() {
        if (isAdded()) {
            LoadingDialog loadingDialog = this.m;
            LoadingDialog loadingDialog2 = null;
            if (loadingDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
                loadingDialog = null;
            }
            if (loadingDialog.isShowing()) {
                LoadingDialog loadingDialog3 = this.m;
                if (loadingDialog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
                } else {
                    loadingDialog2 = loadingDialog3;
                }
                loadingDialog2.dismiss();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.m = new LoadingDialog(requireActivity);
        Application application = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "requireActivity().application");
        this.n = new NotificationsViewModel(application, this);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        BuddyDetailsViewModel buddyDetailsViewModel = new BuddyDetailsViewModel(requireContext);
        this.o = buddyDetailsViewModel;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        buddyDetailsViewModel.setMLabelBuddiesContractor(this);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentNotificationBuddiesBinding inflate = FragmentNotificationBuddiesBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.p = inflate;
        return r().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void onError(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BaseFragment.showCommonMessageDialog$default(this, message, false, 2, null);
    }

    @Subscribe
    public final void onFitnessRequestNotification(@NotNull FitnessAcceptedCloversGoalData event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            NotificationsViewModel notificationsViewModel = this.n;
            Intrinsics.checkNotNull(notificationsViewModel);
            notificationsViewModel.loadBuddiesDetailsInformation();
        }
    }

    @Subscribe
    public final void onFitnessRequestUpdated(@NotNull FitnessAceptRejectEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            NotificationsViewModel notificationsViewModel = this.n;
            Intrinsics.checkNotNull(notificationsViewModel);
            notificationsViewModel.loadBuddiesDetailsInformation();
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter.OnItemClickListener
    public void onItemClickListener(@NotNull GetBuddyItems buddyItem) {
        Intrinsics.checkNotNullParameter(buddyItem, "buddyItem");
        Intent intent = new Intent(requireContext(), ActivityBuddyDetails.class);
        intent.putExtra(BUDDY_ITEM, buddyItem);
        startActivity(intent);
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter.OnItemClickListener
    public void onLabelItemClicked(@NotNull GetBuddyItems buddyItem, @NotNull FitnessBuddiesLabels fitnessBuddiesLabels) {
        Intrinsics.checkNotNullParameter(buddyItem, "buddyItem");
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "fitnessBuddiesLabels");
        A(buddyItem, fitnessBuddiesLabels);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        showProgress();
        NotificationsViewModel notificationsViewModel = this.n;
        Intrinsics.checkNotNull(notificationsViewModel);
        notificationsViewModel.loadBuddiesDetailsInformation();
        NotificationsViewModel notificationsViewModel2 = this.n;
        Intrinsics.checkNotNull(notificationsViewModel2);
        notificationsViewModel2.getFitnessBuddies();
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

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        r().llAddBuddies.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NotificationFragment.t(NotificationFragment.this, view2);
            }
        });
        r().btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NotificationFragment.u(NotificationFragment.this, view2);
            }
        });
        r().tvAddBuddies.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NotificationFragment.v(NotificationFragment.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        w();
    }

    public final FragmentNotificationBuddiesBinding r() {
        FragmentNotificationBuddiesBinding fragmentNotificationBuddiesBinding = this.p;
        if (fragmentNotificationBuddiesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentNotificationBuddiesBinding;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void removeBuddySuccess() {
    }

    public final void s() {
        Double calories;
        StepsDataModel liveStepsData = UserDataManager.getInstance(requireContext()).getLiveStepsData(Calendar.getInstance(), SessionManager.getInstance(requireContext()).getConnectedDeviceMacAddress());
        int i = 0;
        int steps = liveStepsData != null ? liveStepsData.getSteps() : 0;
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
        Integer num = fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null;
        int intValue = num == null ? 10000 : num.intValue();
        r().tvStepsGoalValue.setText(String.valueOf(steps));
        r().tvStepsGoalTarget.setText('/' + intValue + ' ' + requireContext().getString(R.string.steps));
        r().tvCalorieValue.setText(String.valueOf((liveStepsData == null || (calories = liveStepsData.getCalories()) == null) ? 0 : kotlin.math.c.roundToInt(calories.doubleValue())));
        double d = steps / intValue;
        if (!Double.isNaN(d) && (i = kotlin.math.c.roundToInt(d * 100)) >= 100) {
            i = 100;
        }
        r().progressBarSteps.setProgress(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (isAdded() && z) {
            showProgress();
            NotificationsViewModel notificationsViewModel = this.n;
            Intrinsics.checkNotNull(notificationsViewModel);
            notificationsViewModel.loadBuddiesDetailsInformation();
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showBuddyContents(@NotNull List<GetBuddyItems> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
        if (isAdded()) {
            r().emptyLayout.setVisibility(8);
            r().clBuddiesMain.setVisibility(0);
            r().clMyDetails.setVisibility(0);
            r().tvAddBuddies.setVisibility(0);
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.q = new ShowBuddiesListAdapter(requireContext, buddiesGoals, this);
            RecyclerView recyclerView = r().progressList;
            ShowBuddiesListAdapter showBuddiesListAdapter = this.q;
            if (showBuddiesListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("showBuddiesProgressAdapter");
                showBuddiesListAdapter = null;
            }
            recyclerView.setAdapter(showBuddiesListAdapter);
            r().progressList.setHasFixedSize(true);
            r().progressList.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showContents(@NotNull List<? extends BuddiesGoal> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showEmptyLayout() {
        if (isAdded()) {
            r().emptyLayout.setVisibility(0);
            r().tvAddBuddies.setVisibility(8);
            r().clBuddiesMain.setVisibility(8);
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void showLabelSuccessMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        ShowBuddiesListAdapter showBuddiesListAdapter = null;
        BaseFragment.showCommonMessageDialog$default(this, message, false, 2, null);
        ShowBuddiesListAdapter showBuddiesListAdapter2 = this.q;
        if (showBuddiesListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("showBuddiesProgressAdapter");
        } else {
            showBuddiesListAdapter = showBuddiesListAdapter2;
        }
        showBuddiesListAdapter.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesListAdapter.OnItemClickListener
    public void showMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BaseFragment.showCommonMessageDialog$default(this, message, false, 2, null);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showProgress() {
        LoadingDialog loadingDialog = this.m;
        LoadingDialog loadingDialog2 = null;
        if (loadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
            loadingDialog = null;
        }
        if (loadingDialog.isShowing()) {
            return;
        }
        LoadingDialog loadingDialog3 = this.m;
        if (loadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
        } else {
            loadingDialog2 = loadingDialog3;
        }
        loadingDialog2.show();
    }

    public final void w() {
        ProfileData userDetails = SessionManager.getInstance(requireContext()).getUserDetails();
        UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
        String dpUrl = userDetails != null ? userDetails.getDpUrl() : null;
        if (!(dpUrl == null || dpUrl.length() == 0)) {
            GlideUtils.loadCircularImage(requireContext(), userDetails.getDpUrl(), r().ivProfile, new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.j
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    NotificationFragment.x();
                }
            });
        } else {
            r().ivProfile.setImageResource(R.drawable.default_avatar);
        }
        BuddyDetailsViewModel buddyDetailsViewModel = this.o;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        buddyDetailsViewModel.getMyBadges();
        BuddyDetailsViewModel buddyDetailsViewModel2 = this.o;
        Intrinsics.checkNotNull(buddyDetailsViewModel2);
        buddyDetailsViewModel2.getMyRankDetails();
        BuddyDetailsViewModel buddyDetailsViewModel3 = this.o;
        Intrinsics.checkNotNull(buddyDetailsViewModel3);
        buddyDetailsViewModel3.getGetMyBadgesCount().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.fitnessbuddies.fragments.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NotificationFragment.y(NotificationFragment.this, (Integer) obj);
            }
        });
        BuddyDetailsViewModel buddyDetailsViewModel4 = this.o;
        Intrinsics.checkNotNull(buddyDetailsViewModel4);
        buddyDetailsViewModel4.getGetBestRankBean().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.fitnessbuddies.fragments.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NotificationFragment.z(NotificationFragment.this, (MyRankModel.DataBean.RankBean) obj);
            }
        });
        s();
    }
}
