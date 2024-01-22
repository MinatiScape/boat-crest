package com.coveiot.android.healthbuddies.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.adapters.PendingRequestsAdapter;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesViewModelFactory;
import com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel;
import com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.model.CoveContact;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityPendingRequests extends BaseActivity implements PendingRequestsAdapter.OnOptionSelectedListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ManageHealthBuddiesViewModel p;
    public ManageDoctorViewModel q;
    @Nullable
    public PendingRequestsAdapter r;
    public boolean s;

    public static final void r(ActivityPendingRequests this$0, View view) {
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

    @Override // com.coveiot.android.healthbuddies.adapters.PendingRequestsAdapter.OnOptionSelectedListener
    public void onCancel(int i) {
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = null;
        ManageDoctorViewModel manageDoctorViewModel = null;
        if (this.s) {
            ManageDoctorViewModel manageDoctorViewModel2 = this.q;
            if (manageDoctorViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDoctorViewModel");
            } else {
                manageDoctorViewModel = manageDoctorViewModel2;
            }
            manageDoctorViewModel.cancelSentHealthBuddyRequest(i);
            finish();
            return;
        }
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel2 = this.p;
        if (manageHealthBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            manageHealthBuddiesViewModel = manageHealthBuddiesViewModel2;
        }
        manageHealthBuddiesViewModel.cancelSentHealthBuddyRequest(i);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.util.List] */
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ArrayList arrayList = new ArrayList();
        this.s = getIntent().getBooleanExtra(Constants.FROM_DOCTOR.getValue(), false);
        setContentView(R.layout.activity_pending_requests);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(getString(R.string.manage_requests));
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPendingRequests.r(ActivityPendingRequests.this, view);
            }
        });
        if (SessionManager.getInstance(this).isShowIndusInd()) {
            ImageView ivIndusIndLogo = (ImageView) _$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo, "ivIndusIndLogo");
            visible(ivIndusIndLogo);
        } else {
            ImageView ivIndusIndLogo2 = (ImageView) _$_findCachedViewById(R.id.ivIndusIndLogo);
            Intrinsics.checkNotNullExpressionValue(ivIndusIndLogo2, "ivIndusIndLogo");
            gone(ivIndusIndLogo2);
        }
        if (this.s) {
            ViewModel viewModel = ViewModelProviders.of(this, new HealthBuddiesViewModelFactory(this)).get(ManageDoctorViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, HealthBuddiesVi…torViewModel::class.java)");
            this.q = (ManageDoctorViewModel) viewModel;
            ?? asMutableList = TypeIntrinsics.asMutableList(HealthBuddiesPreferenceManager.Companion.getSentDoctorHealthBuddiesRequests(this));
            if (asMutableList != 0) {
                arrayList = asMutableList;
            }
            this.r = new PendingRequestsAdapter(this, arrayList, this);
            int i = R.id.rvManageRequests;
            ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
            ((RecyclerView) _$_findCachedViewById(i)).setAdapter(this.r);
            return;
        }
        ViewModel viewModel2 = ViewModelProviders.of(this, new HealthBuddiesViewModelFactory(this)).get(ManageHealthBuddiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(this, HealthBuddiesVi…iesViewModel::class.java)");
        this.p = (ManageHealthBuddiesViewModel) viewModel2;
        ?? asMutableList2 = TypeIntrinsics.asMutableList(HealthBuddiesPreferenceManager.Companion.getSentHealthBuddiesRequests(this));
        if (asMutableList2 != 0) {
            arrayList = asMutableList2;
        }
        this.r = new PendingRequestsAdapter(this, arrayList, this);
        int i2 = R.id.rvManageRequests;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.r);
    }

    @Override // com.coveiot.android.healthbuddies.adapters.PendingRequestsAdapter.OnOptionSelectedListener
    public void onDelete(int i) {
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = null;
        ManageDoctorViewModel manageDoctorViewModel = null;
        if (this.s) {
            ManageDoctorViewModel manageDoctorViewModel2 = this.q;
            if (manageDoctorViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDoctorViewModel");
            } else {
                manageDoctorViewModel = manageDoctorViewModel2;
            }
            manageDoctorViewModel.deleteHealthBuddy(i);
            return;
        }
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel2 = this.p;
        if (manageHealthBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            manageHealthBuddiesViewModel = manageHealthBuddiesViewModel2;
        }
        manageHealthBuddiesViewModel.deleteHealthBuddy(i);
    }

    @Override // com.coveiot.android.healthbuddies.adapters.PendingRequestsAdapter.OnOptionSelectedListener
    public void onReInvite(int i, @Nullable String str, @Nullable String str2) {
        new ArrayList().add(new CoveContact("1", str2, str, 0L));
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = null;
        ManageDoctorViewModel manageDoctorViewModel = null;
        if (this.s) {
            ManageDoctorViewModel manageDoctorViewModel2 = this.q;
            if (manageDoctorViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDoctorViewModel");
            } else {
                manageDoctorViewModel = manageDoctorViewModel2;
            }
            manageDoctorViewModel.reInviteHealthBuddy(i);
            return;
        }
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel2 = this.p;
        if (manageHealthBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            manageHealthBuddiesViewModel = manageHealthBuddiesViewModel2;
        }
        manageHealthBuddiesViewModel.reInviteHealthBuddy(i);
    }
}
