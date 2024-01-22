package com.coveiot.android.fitnessbuddies.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.activities.ActivityManageRequests;
import com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivityNew;
import com.coveiot.android.fitnessbuddies.adapters.ShowMessagesAdapter;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.MessagesViewModel;
import com.coveiot.android.fitnessbuddies.models.FitnessCheerEvent;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.CoveEventBusManager;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class MessagesFragment extends Fragment implements MessagesContractor, ManageBuddiesContaractor {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public LoadingDialog h;
    @Nullable
    public MessagesViewModel i;
    public ManageBuddiesViewModel j;
    @Nullable
    public ImageView k;
    public int l;

    public static final void c(MessagesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), AddBuddiesActivityNew.class));
    }

    public static final void d(MessagesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityManageRequests.class));
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
        dismissProgress();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor
    public void dismissProgress() {
        LoadingDialog loadingDialog = this.h;
        LoadingDialog loadingDialog2 = null;
        if (loadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
            loadingDialog = null;
        }
        if (loadingDialog.isShowing()) {
            LoadingDialog loadingDialog3 = this.h;
            if (loadingDialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
            } else {
                loadingDialog2 = loadingDialog3;
            }
            loadingDialog2.dismiss();
        }
    }

    @Nullable
    public final ImageView getMessageStatus() {
        return this.k;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        Application application = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "requireActivity().application");
        this.i = new MessagesViewModel(application, this);
        Application application2 = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "requireActivity().application");
        this.j = new ManageBuddiesViewModel(application2, this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.h = new LoadingDialog(requireActivity);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_messages, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onFitnessCheerNotification(@NotNull FitnessCheerEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        dismissProgress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        showProgress();
        MessagesViewModel messagesViewModel = this.i;
        if (messagesViewModel != null) {
            Intrinsics.checkNotNull(messagesViewModel);
            messagesViewModel.loadFitneesMessages();
        }
        if (this.j == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
        }
        ManageBuddiesViewModel manageBuddiesViewModel = this.j;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
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

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.k = (ImageView) view.findViewById(R.id.messageStatus);
        ((LinearLayout) _$_findCachedViewById(R.id.ll_add_buddies)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MessagesFragment.c(MessagesFragment.this, view2);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_manage_requests)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MessagesFragment.d(MessagesFragment.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.MESSAGE_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void setMessageStatus(@Nullable ImageView imageView) {
        this.k = imageView;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor
    public void showContent(@NotNull List<? extends Messages> messages) {
        Intrinsics.checkNotNullParameter(messages, "messages");
        if (isAdded()) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.emptyLayout);
            if (constraintLayout != null) {
                constraintLayout.setVisibility(8);
            }
            int i = R.id.messagesList;
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
            if (recyclerView != null) {
                recyclerView.setVisibility(0);
            }
            if (messages.size() > 0) {
                ShowMessagesAdapter showMessagesAdapter = new ShowMessagesAdapter(getActivity(), messages);
                RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
                if (recyclerView2 != null) {
                    recyclerView2.setAdapter(showMessagesAdapter);
                }
                RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(i);
                if (recyclerView3 != null) {
                    recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
                    return;
                }
                return;
            }
            showEmptyLayout();
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor
    public void showEmptyLayout() {
        if (isAdded()) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.emptyLayout);
            if (constraintLayout != null) {
                constraintLayout.setVisibility(0);
            }
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.messagesList);
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
            }
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showEmptyView() {
        this.l = 0;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_manage_requests);
        if (textView == null) {
            return;
        }
        textView.setText(requireContext().getString(R.string.manage_requests) + " (" + this.l + HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor, com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showProgress() {
        LoadingDialog loadingDialog = this.h;
        LoadingDialog loadingDialog2 = null;
        if (loadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
            loadingDialog = null;
        }
        if (loadingDialog.isShowing()) {
            return;
        }
        LoadingDialog loadingDialog3 = this.h;
        if (loadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingDialog");
        } else {
            loadingDialog2 = loadingDialog3;
        }
        loadingDialog2.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showContent(@NotNull LinkedHashMap<String, List<Requests>> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (isAdded()) {
            this.l = 0;
            List<Requests> list = data.get(FitnessConstants.INVITE_RECEIVED);
            if (list != null) {
                this.l += list.size();
            }
            List<Requests> list2 = data.get(FitnessConstants.INVITE_SENT);
            if (list2 != null) {
                this.l += list2.size();
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_manage_requests);
            if (textView == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            Context requireContext = requireContext();
            sb.append(requireContext != null ? requireContext.getString(R.string.manage_requests) : null);
            sb.append(" (");
            sb.append(this.l);
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            textView.setText(sb.toString());
        }
    }
}
