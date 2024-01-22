package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.MoodAfterTheSession;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.SessionPlaceType;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.camera.ActivitySquareCamera;
import com.coveiot.android.camera.ActivitySquareCameraKt;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutFeedback extends BaseActivity {
    @Nullable
    public String p;
    @Nullable
    public ViewModelWorkoutFeedback q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int r = 202;

    public static final void v(ActivityWorkoutFeedback this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this$0.q;
        if (viewModelWorkoutFeedback != null) {
            viewModelWorkoutFeedback.setSessionMood(((Spinner) this$0._$_findCachedViewById(R.id.spinner_mood)).getSelectedItem().toString());
        }
        ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this$0.q;
        if (viewModelWorkoutFeedback2 != null) {
            viewModelWorkoutFeedback2.setSessionPlace(((Spinner) this$0._$_findCachedViewById(R.id.spinner_place)).getSelectedItem().toString());
        }
        ViewModelWorkoutFeedback viewModelWorkoutFeedback3 = this$0.q;
        if (viewModelWorkoutFeedback3 != null) {
            viewModelWorkoutFeedback3.submit();
        }
    }

    public static final void w(ActivityWorkoutFeedback this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0, ActivitySquareCamera.class), this$0.r);
    }

    public static final void x(ActivityWorkoutFeedback this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            this$0.finish();
        }
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.r && i2 == -1) {
            ((ImageView) _$_findCachedViewById(R.id.camerara)).setImageURI(intent != null ? (Uri) intent.getParcelableExtra(ActivitySquareCameraKt.EXTRA_SQUARE_IMAGE_URI) : null);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        MutableLiveData<Boolean> finishActivity;
        super.onCreate(bundle);
        setContentView(R.layout.activity_workout_feedback);
        this.q = (ViewModelWorkoutFeedback) ViewModelProviders.of(this).get(ViewModelWorkoutFeedback.class);
        ((Spinner) _$_findCachedViewById(R.id.spinner_mood)).setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367048, t()));
        ((Spinner) _$_findCachedViewById(R.id.spinner_place)).setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367048, u()));
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString(WorkoutConstants.SESSION_ID) : null;
        this.p = string;
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.q;
        if (viewModelWorkoutFeedback != null) {
            viewModelWorkoutFeedback.setSessionId(string);
        }
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutFeedback.v(ActivityWorkoutFeedback.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.camerara)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.d2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutFeedback.w(ActivityWorkoutFeedback.this, view);
            }
        });
        ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.q;
        if (viewModelWorkoutFeedback2 == null || (finishActivity = viewModelWorkoutFeedback2.getFinishActivity()) == null) {
            return;
        }
        finishActivity.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.f2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWorkoutFeedback.x(ActivityWorkoutFeedback.this, (Boolean) obj);
            }
        });
    }

    public final String[] t() {
        return new String[]{MoodAfterTheSession.TIRED.toString(), MoodAfterTheSession.CLUSTERED.toString(), MoodAfterTheSession.RELAXED.toString(), MoodAfterTheSession.FRESH.toString(), MoodAfterTheSession.AWESOME.toString()};
    }

    public final String[] u() {
        return new String[]{SessionPlaceType.ROAD.toString(), SessionPlaceType.TREADMILL.toString(), SessionPlaceType.TRAIL.toString(), SessionPlaceType.TRACK.toString()};
    }
}
