package com.coveiot.android.leonardo.sensai.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySensAiCompareBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAICompareHeaderAdapter;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
import com.coveiot.android.leonardo.sensai.model.SensAICompareTitle;
import com.coveiot.android.theme.BaseActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAICompare extends BaseActivity {
    public ActivitySensAiCompareBinding p;
    public SensAICompareHeaderAdapter q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<SensAICompareData> r = new ArrayList();
    @NotNull
    public List<SensAICompareTitle> s = new ArrayList();

    public static final void r(ActivitySensAICompare this$0, View view) {
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

    public final void initToolbar() {
        ActivitySensAiCompareBinding activitySensAiCompareBinding = this.p;
        ActivitySensAiCompareBinding activitySensAiCompareBinding2 = null;
        if (activitySensAiCompareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding = null;
        }
        TextView textView = (TextView) activitySensAiCompareBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySensAiCompareBinding activitySensAiCompareBinding3 = this.p;
        if (activitySensAiCompareBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensAiCompareBinding2 = activitySensAiCompareBinding3;
        }
        textView.setText(getString(R.string.compare_sessions));
        ((TextView) activitySensAiCompareBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAICompare.r(ActivitySensAICompare.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySensAiCompareBinding inflate = ActivitySensAiCompareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        SensAICompareHeaderAdapter sensAICompareHeaderAdapter = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("ADD_TO_COMPARE");
        Intrinsics.checkNotNull(parcelableArrayListExtra);
        this.r = parcelableArrayListExtra;
        ArrayList parcelableArrayListExtra2 = getIntent().getParcelableArrayListExtra("ADD_TO_COMPARE_TITLE");
        Intrinsics.checkNotNull(parcelableArrayListExtra2);
        this.s = parcelableArrayListExtra2;
        RequestBuilder<Drawable> m28load = Glide.with((FragmentActivity) this).m28load(this.s.get(0).getImage());
        ActivitySensAiCompareBinding activitySensAiCompareBinding = this.p;
        if (activitySensAiCompareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding = null;
        }
        m28load.into(activitySensAiCompareBinding.ivCompare1);
        RequestBuilder<Drawable> m28load2 = Glide.with((FragmentActivity) this).m28load(this.s.get(1).getImage());
        ActivitySensAiCompareBinding activitySensAiCompareBinding2 = this.p;
        if (activitySensAiCompareBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding2 = null;
        }
        m28load2.into(activitySensAiCompareBinding2.ivCompare2);
        ActivitySensAiCompareBinding activitySensAiCompareBinding3 = this.p;
        if (activitySensAiCompareBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding3 = null;
        }
        activitySensAiCompareBinding3.tvCompareTitle1.setText(this.s.get(0).getName());
        ActivitySensAiCompareBinding activitySensAiCompareBinding4 = this.p;
        if (activitySensAiCompareBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding4 = null;
        }
        activitySensAiCompareBinding4.tvCompareTitle2.setText(this.s.get(1).getName());
        ActivitySensAiCompareBinding activitySensAiCompareBinding5 = this.p;
        if (activitySensAiCompareBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding5 = null;
        }
        activitySensAiCompareBinding5.tvCompareDate1.setText(this.s.get(0).getValue1());
        ActivitySensAiCompareBinding activitySensAiCompareBinding6 = this.p;
        if (activitySensAiCompareBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding6 = null;
        }
        activitySensAiCompareBinding6.tvCompareDate2.setText(this.s.get(1).getValue1());
        if (this.r.size() == 0) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ActivitySensAiCompareBinding activitySensAiCompareBinding7 = this.p;
        if (activitySensAiCompareBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding7 = null;
        }
        activitySensAiCompareBinding7.rvCompareList.setLayoutManager(linearLayoutManager);
        this.q = new SensAICompareHeaderAdapter(this, this.r);
        ActivitySensAiCompareBinding activitySensAiCompareBinding8 = this.p;
        if (activitySensAiCompareBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiCompareBinding8 = null;
        }
        RecyclerView recyclerView = activitySensAiCompareBinding8.rvCompareList;
        SensAICompareHeaderAdapter sensAICompareHeaderAdapter2 = this.q;
        if (sensAICompareHeaderAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sensAICompareAdapter");
        } else {
            sensAICompareHeaderAdapter = sensAICompareHeaderAdapter2;
        }
        recyclerView.setAdapter(sensAICompareHeaderAdapter);
    }
}
