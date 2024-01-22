package com.mappls.sdk.maps.covid;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import com.mappls.sdk.maps.R;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class SafetyStripView extends RelativeLayout implements View.OnClickListener {
    private static final int COLLAPSING = 0;
    private static final int EXPANDING = 1;
    public int DEFAULT_DURATION;
    public int STATUS_DANGER;
    public int STATUS_SAFE;
    private ImageButton actionButton;
    private TextView covidStripDistrictName;
    private TextView covidStripMapLink;
    private TextView covidStripSubSubtitle;
    private TextView covidStripSubtitle;
    private TextView covidStripTitle;
    public boolean isExpanded;

    /* loaded from: classes11.dex */
    public class a implements Callback<ZoneInfo> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<ZoneInfo> call, @NonNull Throwable th) {
            th.printStackTrace();
            SafetyStripView.this.showHide(false);
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<ZoneInfo> call, @NonNull Response<ZoneInfo> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            SafetyStripView.this.showHide(true);
            SafetyStripView.this.setSafetyStatus(response.body().isInsideContainmentZone() ? SafetyStripView.this.STATUS_DANGER : SafetyStripView.this.STATUS_SAFE, response.body());
        }
    }

    /* loaded from: classes11.dex */
    public class b extends Animation {
        public final /* synthetic */ View h;
        public final /* synthetic */ int i;

        public b(SafetyStripView safetyStripView, View view, int i) {
            this.h = view;
            this.i = i;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            this.h.getLayoutParams().height = f == 1.0f ? -2 : (int) (this.i * f);
            this.h.requestLayout();
        }

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Animation.AnimationListener {
        public c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SafetyStripView.this.covidStripSubSubtitle.setEllipsize(TextUtils.TruncateAt.END);
            SafetyStripView.this.covidStripSubSubtitle.setMaxLines(1);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes11.dex */
    public class d extends Animation {
        public final /* synthetic */ View h;
        public final /* synthetic */ int i;

        public d(SafetyStripView safetyStripView, View view, int i) {
            this.h = view;
            this.i = i;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            if (f == 1.0f) {
                this.h.setVisibility(8);
                return;
            }
            ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
            int i = this.i;
            layoutParams.height = i - ((int) (i * f));
            this.h.requestLayout();
        }

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SafetyStripView.this.covidStripSubSubtitle.setEllipsize(TextUtils.TruncateAt.END);
            SafetyStripView.this.covidStripSubSubtitle.setMaxLines(1);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public SafetyStripView(Context context) {
        this(context, null);
    }

    private String getDistanceFormat(long j) {
        if (j < 1000) {
            int i = (int) j;
            return getResources().getQuantityString(R.plurals.mappls_maps_distance_meter, i, Integer.valueOf(i));
        }
        String format = new DecimalFormat("#.0").format(j / 1000.0d);
        return format + "Km(s)";
    }

    private void getLocationSafety(Location location) {
        showHide(false);
        MapplsContainmentZoneInfo.a().keywords("HSPCOV;HSPTST;HSPSCC;HSPTMT;EVTIPL;COVRSN;FODHNG;HOTNST;EVTHLI;CLIFVR;TMPRSN").distance(5000).range(500).location(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())).build().enqueueCall(new a());
    }

    public void animateArrow(int i, int i2) {
        RotateAnimation rotateAnimation;
        if (i == 1) {
            rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        } else {
            rotateAnimation = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        }
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(i2);
        this.actionButton.startAnimation(rotateAnimation);
    }

    public void bind() {
        this.covidStripTitle = (TextView) findViewById(R.id.covid_strip_title);
        this.covidStripSubtitle = (TextView) findViewById(R.id.covid_strip_subtitle);
        this.covidStripSubSubtitle = (TextView) findViewById(R.id.covid_strip_sub_subtitle);
        this.covidStripMapLink = (TextView) findViewById(R.id.covid_strip_map_link);
        this.covidStripDistrictName = (TextView) findViewById(R.id.covid_strip_district_name);
        this.actionButton = (ImageButton) findViewById(R.id.action_button);
    }

    public void collapse(View view) {
        d dVar = new d(this, view, view.getMeasuredHeight());
        int i = this.DEFAULT_DURATION;
        dVar.setDuration(i);
        view.startAnimation(dVar);
        dVar.setAnimationListener(new e());
        animateArrow(0, i);
    }

    public void expand(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = view.getMeasuredHeight();
        view.getLayoutParams().height = 1;
        view.setVisibility(0);
        b bVar = new b(this, view, measuredHeight);
        int i = this.DEFAULT_DURATION;
        bVar.setDuration(i);
        view.startAnimation(bVar);
        bVar.setAnimationListener(new c());
        animateArrow(1, i);
    }

    public void initializeListeners() {
        findViewById(R.id.covid_striip).setOnClickListener(this);
    }

    public void initializeView() {
        RelativeLayout.inflate(getContext(), R.layout.mappls_maps_covid_strip, this);
        bind();
        initializeListeners();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.covid_striip) {
            if (this.isExpanded) {
                collapse(findViewById(R.id.container));
            } else {
                expand(findViewById(R.id.container));
            }
            this.isExpanded = !this.isExpanded;
        }
    }

    public void setSafetyStatus(int i, ZoneInfo zoneInfo) {
        if (i == this.STATUS_DANGER) {
            setBackgroundColor(Color.parseColor("#f8d7da"));
            this.covidStripTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mappls_maps_danger, 0, 0, 0);
            TextViewCompat.setTextAppearance(this.covidStripTitle, R.style.DangerSafetyTitleTextAppearance);
            TextViewCompat.setTextAppearance(this.covidStripSubtitle, R.style.DangerSafetySubTitleTextAppearance);
            TextView textView = this.covidStripSubSubtitle;
            int i2 = R.style.DangerSafetySubSubTitleTextAppearance;
            TextViewCompat.setTextAppearance(textView, i2);
            TextViewCompat.setTextAppearance(this.covidStripMapLink, i2);
            TextViewCompat.setTextAppearance(this.covidStripDistrictName, i2);
            this.covidStripTitle.setText(R.string.mappls_maps_txt_title_danger);
            this.covidStripSubtitle.setText(R.string.mappls_maps_txt_sub_title_danger);
        } else if (i == this.STATUS_SAFE) {
            setBackgroundColor(Color.parseColor("#d4edda"));
            this.covidStripTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mappls_maps_safe, 0, 0, 0);
            TextViewCompat.setTextAppearance(this.covidStripTitle, R.style.SafeSafetyTitleTextAppearance);
            TextViewCompat.setTextAppearance(this.covidStripSubtitle, R.style.SafeSafetySubTitleTextAppearance);
            TextView textView2 = this.covidStripSubSubtitle;
            int i3 = R.style.SafeSafetySubSubTitleTextAppearance;
            TextViewCompat.setTextAppearance(textView2, i3);
            TextViewCompat.setTextAppearance(this.covidStripMapLink, i3);
            TextViewCompat.setTextAppearance(this.covidStripDistrictName, i3);
            this.covidStripTitle.setText(getDistanceFormat(zoneInfo.getDistanceToNearestZone()));
            this.covidStripSubtitle.setText(R.string.mappls_maps_txt_sub_title_safe);
        }
        Pattern compile = Pattern.compile(zoneInfo.getMapLink());
        this.covidStripMapLink.setText(getResources().getString(R.string.mappls_maps_map_link, zoneInfo.getMapLink()));
        Linkify.addLinks(this.covidStripMapLink, compile, "http:");
        this.covidStripDistrictName.setText(getResources().getString(R.string.mappls_maps_txt_district_name, zoneInfo.getDistrictName()));
        this.covidStripSubSubtitle.setText(getResources().getString(R.string.mappls_maps_txt_location, zoneInfo.getContainmentZoneName()));
    }

    public void showHide(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    public void updatedSafetyStatus(Location location) {
        getLocationSafety(location);
    }

    public SafetyStripView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SafetyStripView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.STATUS_SAFE = 1;
        this.STATUS_DANGER = 2;
        this.DEFAULT_DURATION = 500;
        this.isExpanded = false;
        initializeView();
    }
}
