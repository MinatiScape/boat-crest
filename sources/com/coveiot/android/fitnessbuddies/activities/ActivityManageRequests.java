package com.coveiot.android.fitnessbuddies.activities;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter;
import com.coveiot.android.fitnessbuddies.adapters.RequestsReceivedListAdapter;
import com.coveiot.android.fitnessbuddies.adapters.RequestsSentListAdapter;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.databinding.ActivityManageRequestsBinding;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel;
import com.coveiot.android.fitnessbuddies.models.FitnessAceptRejectEvent;
import com.coveiot.android.fitnessbuddies.models.ManageBuddiesEvent;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.CoveEventBusManager;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityManageRequests extends BaseActivity implements ManageBuddiesContaractor, ManageBuddiesAdapter.OnOptionSelectedListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityManageRequestsBinding p;
    public ManageBuddiesViewModel q;
    @Nullable
    public RequestsReceivedListAdapter r;
    @Nullable
    public RequestsSentListAdapter s;

    public static final void r(ActivityManageRequests this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void dismissPerogress() {
        dismissProgress();
    }

    @Nullable
    public final RequestsReceivedListAdapter getRequestsReceivedAdapter() {
        return this.r;
    }

    @Nullable
    public final RequestsSentListAdapter getRequestsSentAdapter() {
        return this.s;
    }

    public final void initToolbar() {
        ActivityManageRequestsBinding activityManageRequestsBinding = this.p;
        ActivityManageRequestsBinding activityManageRequestsBinding2 = null;
        if (activityManageRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRequestsBinding = null;
        }
        TextView textView = (TextView) activityManageRequestsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityManageRequestsBinding activityManageRequestsBinding3 = this.p;
        if (activityManageRequestsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityManageRequestsBinding2 = activityManageRequestsBinding3;
        }
        textView.setText(getString(R.string.requests));
        ((TextView) activityManageRequestsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageRequests.r(ActivityManageRequests.this, view);
            }
        });
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onAccept(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.q;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
        String manageBuddiesType = ManageBuddiesEvent.ACCEPT.getManageBuddiesType();
        Intrinsics.checkNotNullExpressionValue(manageBuddiesType, "ACCEPT.manageBuddiesType");
        manageBuddiesViewModel.handleFitnessRequest(i, manageBuddiesType);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityManageRequestsBinding inflate = ActivityManageRequestsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityManageRequestsBinding activityManageRequestsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this@ActivityManageRequests.application");
        this.q = new ManageBuddiesViewModel(application, this);
        showProgress();
        ManageBuddiesViewModel manageBuddiesViewModel = this.q;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
        manageBuddiesViewModel.manageAllBuddies();
        initToolbar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ActivityManageRequestsBinding activityManageRequestsBinding2 = this.p;
        if (activityManageRequestsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRequestsBinding2 = null;
        }
        activityManageRequestsBinding2.rvRequestsReceived.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        ActivityManageRequestsBinding activityManageRequestsBinding3 = this.p;
        if (activityManageRequestsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityManageRequestsBinding = activityManageRequestsBinding3;
        }
        activityManageRequestsBinding.rvRequestsSent.setLayoutManager(linearLayoutManager2);
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onDelete(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.q;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
        manageBuddiesViewModel.deleteBuddy(i);
    }

    @Subscribe
    public final void onFitnessRequestUpdated(@NotNull FitnessAceptRejectEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ManageBuddiesViewModel manageBuddiesViewModel = this.q;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
        manageBuddiesViewModel.manageAllBuddies();
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onReject(int i) {
        ManageBuddiesViewModel manageBuddiesViewModel = this.q;
        if (manageBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
            manageBuddiesViewModel = null;
        }
        String manageBuddiesType = ManageBuddiesEvent.REJECT.getManageBuddiesType();
        Intrinsics.checkNotNullExpressionValue(manageBuddiesType, "REJECT.manageBuddiesType");
        manageBuddiesViewModel.handleFitnessRequest(i, manageBuddiesType);
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onResend(int i, @Nullable String str, @Nullable String str2, @Nullable Requests requests) {
        boolean z = true;
        if (requests == null || !requests.inviteLocally) {
            z = false;
        }
        ManageBuddiesViewModel manageBuddiesViewModel = null;
        if (z) {
            CoveUtils coveUtils = CoveUtils.INSTANCE;
            String string = getString(R.string.whatsapp_uri);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.whatsapp_uri)");
            if (coveUtils.isAppInstalled(this, string)) {
                String str3 = requests != null ? requests.toUserMobileNumber : null;
                Intrinsics.checkNotNull(str3);
                String str4 = requests != null ? requests.inviteText : null;
                Intrinsics.checkNotNull(str4);
                coveUtils.openWhatsApp(this, str3, str4);
            }
        }
        ManageBuddiesViewModel manageBuddiesViewModel2 = this.q;
        if (manageBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
        } else {
            manageBuddiesViewModel = manageBuddiesViewModel2;
        }
        String manageBuddiesType = ManageBuddiesEvent.RE_INVITE.getManageBuddiesType();
        Intrinsics.checkNotNullExpressionValue(manageBuddiesType, "RE_INVITE.manageBuddiesType");
        manageBuddiesViewModel.handleFitnessRequest(i, manageBuddiesType);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter.OnOptionSelectedListener
    public void onUnfriend(int i) {
    }

    public final void setRequestsReceivedAdapter(@Nullable RequestsReceivedListAdapter requestsReceivedListAdapter) {
        this.r = requestsReceivedListAdapter;
    }

    public final void setRequestsSentAdapter(@Nullable RequestsSentListAdapter requestsSentListAdapter) {
        this.s = requestsSentListAdapter;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showContent(@NotNull LinkedHashMap<String, List<Requests>> data) {
        Unit unit;
        Unit unit2;
        Intrinsics.checkNotNullParameter(data, "data");
        List<Requests> list = data.get(FitnessConstants.INVITE_RECEIVED);
        ActivityManageRequestsBinding activityManageRequestsBinding = null;
        if (list != null) {
            if (!list.isEmpty()) {
                ActivityManageRequestsBinding activityManageRequestsBinding2 = this.p;
                if (activityManageRequestsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding2 = null;
                }
                activityManageRequestsBinding2.clRequestsReceived.setVisibility(0);
                this.r = new RequestsReceivedListAdapter(this, list, this);
                ActivityManageRequestsBinding activityManageRequestsBinding3 = this.p;
                if (activityManageRequestsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding3 = null;
                }
                activityManageRequestsBinding3.rvRequestsReceived.setAdapter(this.r);
            } else {
                ActivityManageRequestsBinding activityManageRequestsBinding4 = this.p;
                if (activityManageRequestsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding4 = null;
                }
                activityManageRequestsBinding4.clRequestsReceived.setVisibility(8);
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            ActivityManageRequestsBinding activityManageRequestsBinding5 = this.p;
            if (activityManageRequestsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityManageRequestsBinding5 = null;
            }
            activityManageRequestsBinding5.clRequestsReceived.setVisibility(8);
        }
        List<Requests> list2 = data.get(FitnessConstants.INVITE_SENT);
        if (list2 != null) {
            if (!list2.isEmpty()) {
                ActivityManageRequestsBinding activityManageRequestsBinding6 = this.p;
                if (activityManageRequestsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding6 = null;
                }
                activityManageRequestsBinding6.clRequestsSent.setVisibility(0);
                this.s = new RequestsSentListAdapter(this, list2, this);
                ActivityManageRequestsBinding activityManageRequestsBinding7 = this.p;
                if (activityManageRequestsBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding7 = null;
                }
                activityManageRequestsBinding7.rvRequestsSent.setAdapter(this.s);
            } else {
                ActivityManageRequestsBinding activityManageRequestsBinding8 = this.p;
                if (activityManageRequestsBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityManageRequestsBinding8 = null;
                }
                activityManageRequestsBinding8.clRequestsSent.setVisibility(8);
            }
            unit2 = Unit.INSTANCE;
        } else {
            unit2 = null;
        }
        if (unit2 == null) {
            ActivityManageRequestsBinding activityManageRequestsBinding9 = this.p;
            if (activityManageRequestsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityManageRequestsBinding = activityManageRequestsBinding9;
            }
            activityManageRequestsBinding.clRequestsSent.setVisibility(8);
        }
        dismissProgress();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showEmptyView() {
        dismissProgress();
        ActivityManageRequestsBinding activityManageRequestsBinding = this.p;
        ActivityManageRequestsBinding activityManageRequestsBinding2 = null;
        if (activityManageRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRequestsBinding = null;
        }
        activityManageRequestsBinding.clRequests.setVisibility(8);
        ActivityManageRequestsBinding activityManageRequestsBinding3 = this.p;
        if (activityManageRequestsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityManageRequestsBinding2 = activityManageRequestsBinding3;
        }
        activityManageRequestsBinding2.emptyLayout.setVisibility(0);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BaseActivity.showCommonMessageDialog$default(this, message, false, 2, null);
    }
}
