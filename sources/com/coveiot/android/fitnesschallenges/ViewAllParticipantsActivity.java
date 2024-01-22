package com.coveiot.android.fitnesschallenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.fitnessbuddies.databinding.ActivityAddBuddiesNewBinding;
import com.coveiot.android.fitnessbuddies.viewmodels.AddBuddiesViewModel;
import com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsAdapter;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.utils.model.CoveContact;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewAllParticipantsActivity extends BaseActivity implements SuccessResultListener {
    public ActivityAddBuddiesNewBinding p;
    @Nullable
    public ViewAllParticipantsAdapter r;
    @Nullable
    public ViewAllParticipantsAdapter s;
    public boolean t;
    @Nullable
    public String u;
    public AddBuddiesViewModel x;
    @Nullable
    public String y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<CoveContact> q = new ArrayList<>();
    @NotNull
    public ArrayList<CoveContact> v = new ArrayList<>();
    @NotNull
    public ArrayList<CoveContact> w = new ArrayList<>();

    public static final void A(ViewAllParticipantsActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            this$0.w = (ArrayList) list;
        }
        this$0.I();
    }

    public static final void B(ViewAllParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this$0.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        ConstraintLayout constraintLayout = activityAddBuddiesNewBinding.clMenu;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clMenu");
        constraintLayout.setVisibility(constraintLayout.getVisibility() == 0 ? 8 : 0);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this$0.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        View findViewById = activityAddBuddiesNewBinding2.search.findViewById(com.coveiot.android.fitnessbuddies.R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        ((EditText) findViewById).setEnabled(constraintLayout.getVisibility() != 0);
    }

    public static final void C(ViewAllParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.onBackPressed();
    }

    public static final void D(ViewAllParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this$0.p;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        ConstraintLayout constraintLayout = activityAddBuddiesNewBinding.clMenu;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clMenu");
        this$0.gone(constraintLayout);
    }

    public static final void F(ViewAllParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this$0.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.clMenu.setVisibility(8);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this$0.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        View findViewById = activityAddBuddiesNewBinding2.search.findViewById(com.coveiot.android.fitnessbuddies.R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        ((EditText) findViewById).setEnabled(true);
        Intent intent = new Intent(this$0, AddParticipantsActivity.class);
        intent.putExtra("challengeId", this$0.y);
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 2);
        this$0.startActivity(intent);
    }

    public static final void G(ViewAllParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this$0.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.clMenu.setVisibility(8);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this$0.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        View findViewById = activityAddBuddiesNewBinding2.search.findViewById(com.coveiot.android.fitnessbuddies.R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        ((EditText) findViewById).setEnabled(true);
        Intent intent = new Intent(this$0, AddParticipantsActivity.class);
        intent.putExtra(FitnessChallengeConstants.EDIT_REMOVE_PARTICIPANT, true);
        this$0.startActivity(intent);
    }

    public static final boolean K(ViewAllParticipantsActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewAllParticipantsAdapter viewAllParticipantsAdapter = this$0.r;
        Intrinsics.checkNotNull(viewAllParticipantsAdapter);
        viewAllParticipantsAdapter.filter(null);
        ViewAllParticipantsAdapter viewAllParticipantsAdapter2 = this$0.s;
        Intrinsics.checkNotNull(viewAllParticipantsAdapter2);
        viewAllParticipantsAdapter2.filter(null);
        return false;
    }

    public static final void z(ViewAllParticipantsActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            this$0.v = (ArrayList) list;
        }
        this$0.I();
    }

    public final void E() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.tvAddParticipant.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAllParticipantsActivity.F(ViewAllParticipantsActivity.this, view);
            }
        });
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        activityAddBuddiesNewBinding2.tvRemoveParticipant.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAllParticipantsActivity.G(ViewAllParticipantsActivity.this, view);
            }
        });
    }

    public final void H() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        View findViewById = activityAddBuddiesNewBinding.search.findViewById(com.coveiot.android.fitnessbuddies.R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(com.coveiot.android.fitnessbuddies.R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(com.coveiot.android.fitnessbuddies.R.color.color_666666));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.search.setIconifiedByDefault(false);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding4;
        }
        activityAddBuddiesNewBinding2.search.setQueryHint(getResources().getString(com.coveiot.android.fitnessbuddies.R.string.search_buddy_name_here));
    }

    public final void I() {
        ArrayList<CoveContact> arrayList = this.v;
        boolean z = true;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = null;
        if (!(arrayList == null || arrayList.isEmpty())) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
            if (activityAddBuddiesNewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding2 = null;
            }
            activityAddBuddiesNewBinding2.tvActiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
            if (activityAddBuddiesNewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding3 = null;
            }
            activityAddBuddiesNewBinding3.rvActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
            if (activityAddBuddiesNewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding4 = null;
            }
            activityAddBuddiesNewBinding4.tvActiveHeader.setText(getString(com.coveiot.android.fitnessbuddies.R.string.my_buddies) + " (" + this.v.size() + HexStringBuilder.COMMENT_END_CHAR);
            this.r = new ViewAllParticipantsAdapter(this, CollectionsKt___CollectionsKt.toMutableList((Collection) this.v));
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
            if (activityAddBuddiesNewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding5 = null;
            }
            activityAddBuddiesNewBinding5.rvActiveContactsList.setAdapter(this.r);
            J();
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
            if (activityAddBuddiesNewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding6 = null;
            }
            activityAddBuddiesNewBinding6.tvActiveHeader.setVisibility(8);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding7 = this.p;
            if (activityAddBuddiesNewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding7 = null;
            }
            activityAddBuddiesNewBinding7.rvActiveContactsList.setVisibility(8);
        }
        ArrayList<CoveContact> arrayList2 = this.w;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            z = false;
        }
        if (!z) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding8 = this.p;
            if (activityAddBuddiesNewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding8 = null;
            }
            activityAddBuddiesNewBinding8.tvInactiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding9 = this.p;
            if (activityAddBuddiesNewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding9 = null;
            }
            activityAddBuddiesNewBinding9.rvInActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding10 = this.p;
            if (activityAddBuddiesNewBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding10 = null;
            }
            activityAddBuddiesNewBinding10.tvInactiveHeader.setText(getString(com.coveiot.android.fitnessbuddies.R.string.other_contacts) + " (" + this.w.size() + HexStringBuilder.COMMENT_END_CHAR);
            this.s = new ViewAllParticipantsAdapter(this, CollectionsKt___CollectionsKt.toMutableList((Collection) this.w));
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding11 = this.p;
            if (activityAddBuddiesNewBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddBuddiesNewBinding = activityAddBuddiesNewBinding11;
            }
            activityAddBuddiesNewBinding.rvInActiveContactsList.setAdapter(this.s);
            J();
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding12 = this.p;
            if (activityAddBuddiesNewBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding12 = null;
            }
            activityAddBuddiesNewBinding12.tvInactiveHeader.setVisibility(8);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding13 = this.p;
            if (activityAddBuddiesNewBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddBuddiesNewBinding = activityAddBuddiesNewBinding13;
            }
            activityAddBuddiesNewBinding.rvInActiveContactsList.setVisibility(8);
        }
        dismissProgress();
    }

    public final void J() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.search.setIconifiedByDefault(false);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.fitnesschallenges.ViewAllParticipantsActivity$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ViewAllParticipantsAdapter viewAllParticipantsAdapter;
                ViewAllParticipantsAdapter viewAllParticipantsAdapter2;
                ViewAllParticipantsAdapter viewAllParticipantsAdapter3;
                ViewAllParticipantsAdapter viewAllParticipantsAdapter4;
                Intrinsics.checkNotNullParameter(s, "s");
                viewAllParticipantsAdapter = ViewAllParticipantsActivity.this.r;
                if (viewAllParticipantsAdapter != null) {
                    viewAllParticipantsAdapter4 = ViewAllParticipantsActivity.this.r;
                    Intrinsics.checkNotNull(viewAllParticipantsAdapter4);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = s.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    viewAllParticipantsAdapter4.filter(lowerCase);
                }
                viewAllParticipantsAdapter2 = ViewAllParticipantsActivity.this.s;
                if (viewAllParticipantsAdapter2 != null) {
                    viewAllParticipantsAdapter3 = ViewAllParticipantsActivity.this.s;
                    Intrinsics.checkNotNull(viewAllParticipantsAdapter3);
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = s.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    viewAllParticipantsAdapter3.filter(lowerCase2);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding4;
        }
        activityAddBuddiesNewBinding2.search.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.fitnesschallenges.n1
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean K;
                K = ViewAllParticipantsActivity.K(ViewAllParticipantsActivity.this);
                return K;
            }
        });
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

    @Nullable
    public final String getMyChallengeType() {
        return this.u;
    }

    public final void initToolbar() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        TextView textView = (TextView) activityAddBuddiesNewBinding.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_title);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
        if (activityAddBuddiesNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding2 = null;
        }
        TextView textView2 = (TextView) activityAddBuddiesNewBinding2.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_back_arrow);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        ImageView imageView = (ImageView) activityAddBuddiesNewBinding3.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.ivButton);
        imageView.setVisibility(kotlin.text.m.equals$default(this.u, FitnessChallengeConstants.MY_CREATED_CHALLENGES, false, 2, null) ? 0 : 8);
        imageView.setImageResource(com.coveiot.android.fitnessbuddies.R.drawable.ic_option_menu);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.i1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAllParticipantsActivity.B(ViewAllParticipantsActivity.this, view);
            }
        });
        textView.setText("All Participants");
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAllParticipantsActivity.C(ViewAllParticipantsActivity.this, view);
            }
        });
    }

    public final boolean isCreatedChallenge() {
        return this.t;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddBuddiesNewBinding inflate = ActivityAddBuddiesNewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        AddBuddiesViewModel addBuddiesViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        AddBuddiesViewModel addBuddiesViewModel2 = new AddBuddiesViewModel(this);
        this.x = addBuddiesViewModel2;
        addBuddiesViewModel2.setMListener(this);
        Intent intent = getIntent();
        FitnessChallengeConstants.Companion companion = FitnessChallengeConstants.Companion;
        if (intent.hasExtra(companion.getPARTICIPANTS_LIST())) {
            Serializable serializableExtra = getIntent().getSerializableExtra(companion.getPARTICIPANTS_LIST());
            ArrayList<CoveContact> arrayList = serializableExtra instanceof ArrayList ? (ArrayList) serializableExtra : null;
            Intrinsics.checkNotNull(arrayList);
            this.q = arrayList;
        }
        boolean z = false;
        if (getIntent().hasExtra(companion.getIS_CREATED_CHALLENGE())) {
            this.t = getIntent().getBooleanExtra(companion.getIS_CREATED_CHALLENGE(), false);
        }
        if (getIntent().hasExtra("challengeId")) {
            this.y = getIntent().getStringExtra("challengeId");
        }
        this.u = getIntent().getStringExtra(FitnessChallengeConstants.CHALLENGE_SUB_TYPE);
        initToolbar();
        H();
        E();
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.clRootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.k1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAllParticipantsActivity.D(ViewAllParticipantsActivity.this, view);
            }
        });
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
        if (activityAddBuddiesNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding2 = null;
        }
        activityAddBuddiesNewBinding2.rvActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.rvActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding4 = null;
        }
        activityAddBuddiesNewBinding4.rvInActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
        if (activityAddBuddiesNewBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding5 = null;
        }
        activityAddBuddiesNewBinding5.rvInActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
        if (activityAddBuddiesNewBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding6 = null;
        }
        activityAddBuddiesNewBinding6.refresh.setVisibility(4);
        ArrayList<CoveContact> arrayList2 = this.q;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            z = true;
        }
        if (!z) {
            AddBuddiesViewModel addBuddiesViewModel3 = this.x;
            if (addBuddiesViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                addBuddiesViewModel = addBuddiesViewModel3;
            }
            addBuddiesViewModel.loadCoveBuddies(this.q);
        }
        y();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
    }

    public final void setCreatedChallenge(boolean z) {
        this.t = z;
    }

    public final void setMyChallengeType(@Nullable String str) {
        this.u = str;
    }

    public final void y() {
        AddBuddiesViewModel addBuddiesViewModel = this.x;
        AddBuddiesViewModel addBuddiesViewModel2 = null;
        if (addBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            addBuddiesViewModel = null;
        }
        addBuddiesViewModel.getGetActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnesschallenges.o1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ViewAllParticipantsActivity.z(ViewAllParticipantsActivity.this, (List) obj);
            }
        });
        AddBuddiesViewModel addBuddiesViewModel3 = this.x;
        if (addBuddiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            addBuddiesViewModel2 = addBuddiesViewModel3;
        }
        addBuddiesViewModel2.getGetInActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnesschallenges.p1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ViewAllParticipantsActivity.A(ViewAllParticipantsActivity.this, (List) obj);
            }
        });
    }
}
