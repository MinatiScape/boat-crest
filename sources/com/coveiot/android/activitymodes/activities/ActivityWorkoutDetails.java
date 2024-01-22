package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutDetails;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.StrideLengthAnimationActivity;
import com.coveiot.covepreferences.BaseUnitType;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UNIT_TYPE;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutDetails extends BaseActivity implements OnMapReadyCallback {
    public int q;
    @Nullable
    public ViewModelWorkoutDetails r;
    @Nullable
    public String s;
    @Nullable
    public GoogleMap t;
    @Nullable
    public BottomSheetBehavior<ConstraintLayout> u;
    public int w;
    public int x;
    public int y;
    public float z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityWorkoutDetails";
    @NotNull
    public ActivityMode v = ActivityMode.RUN;
    @NotNull
    public final Observer<EntityWorkoutSession> A = new Observer() { // from class: com.coveiot.android.activitymodes.activities.y1
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityWorkoutDetails.I(ActivityWorkoutDetails.this, (EntityWorkoutSession) obj);
        }
    };
    @NotNull
    public final Observer<List<WalkSample>> B = new Observer() { // from class: com.coveiot.android.activitymodes.activities.a2
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityWorkoutDetails.J(ActivityWorkoutDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<RunSample>> C = new Observer() { // from class: com.coveiot.android.activitymodes.activities.z1
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityWorkoutDetails.G(ActivityWorkoutDetails.this, (List) obj);
        }
    };

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.RUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void D(final ActivityWorkoutDetails this$0, PolylineOptions polylineOptions, final LatLngBounds.Builder builder) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(polylineOptions, "$polylineOptions");
        Intrinsics.checkNotNullParameter(builder, "$builder");
        GoogleMap googleMap = this$0.t;
        if (googleMap != null) {
            if (googleMap != null) {
                googleMap.clear();
            }
            GoogleMap googleMap2 = this$0.t;
            if (googleMap2 != null) {
                googleMap2.addPolyline(polylineOptions);
            }
            if (polylineOptions.getPoints().size() > 0) {
                GoogleMap googleMap3 = this$0.t;
                if (googleMap3 != null) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    List<LatLng> points = polylineOptions.getPoints();
                    Intrinsics.checkNotNullExpressionValue(points, "polylineOptions.points");
                    googleMap3.addMarker(markerOptions.position((LatLng) CollectionsKt___CollectionsKt.first((List<? extends Object>) points)).icon(BitmapDescriptorFactory.fromResource(R.drawable.start_location_marker)));
                }
                GoogleMap googleMap4 = this$0.t;
                if (googleMap4 != null) {
                    MarkerOptions markerOptions2 = new MarkerOptions();
                    List<LatLng> points2 = polylineOptions.getPoints();
                    Intrinsics.checkNotNullExpressionValue(points2, "polylineOptions.points");
                    googleMap4.addMarker(markerOptions2.position((LatLng) CollectionsKt___CollectionsKt.last((List<? extends Object>) points2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.end_location_marker)));
                }
                GoogleMap googleMap5 = this$0.t;
                if (googleMap5 != null) {
                    googleMap5.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: com.coveiot.android.activitymodes.activities.c2
                        @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
                        public final void onMapLoaded() {
                            ActivityWorkoutDetails.E(ActivityWorkoutDetails.this, builder);
                        }
                    });
                }
            }
        }
    }

    public static final void E(ActivityWorkoutDetails this$0, LatLngBounds.Builder builder) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(builder, "$builder");
        GoogleMap googleMap = this$0.t;
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200));
        }
    }

    public static final void G(final ActivityWorkoutDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.s1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutDetails.H(list, this$0);
            }
        });
    }

    public static final void H(List list, ActivityWorkoutDetails this$0) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            boolean z = true;
            if (!list.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList<Entry> arrayList4 = new ArrayList();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(ContextCompat.getColor(this$0, R.color.colorPrimary));
                int size = list.size();
                float f = 0.0f;
                float f2 = 0.0f;
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    arrayList2.add(new BarEntry(f2, ((RunSample) list.get(i)).getStepCount()));
                    i2 += ((RunSample) list.get(i)).getSampleData().getDistance();
                    if (i2 != 0) {
                        arrayList3.add(new Entry(f2, f2 / (i2 / 100000.0f)));
                    } else {
                        arrayList3.add(new Entry(f2, f));
                    }
                    float hr_value = ((RunSample) list.get(i)).getSampleData().getHr_value();
                    if ((hr_value == f ? z : false) && i > 0) {
                        hr_value = ((Entry) arrayList4.get(i - 1)).getY();
                    }
                    arrayList4.add(new Entry(f2, hr_value));
                    if (!(((RunSample) list.get(i)).getSampleData().getLatitude() == 0.0d ? z : false)) {
                        if (!(((RunSample) list.get(i)).getSampleData().getLongitude() == 0.0d ? z : false)) {
                            arrayList = arrayList3;
                            polylineOptions.add(new LatLng(((RunSample) list.get(i)).getSampleData().getLatitude(), ((RunSample) list.get(i)).getSampleData().getLongitude()));
                            builder.include(new LatLng(((RunSample) list.get(i)).getSampleData().getLatitude(), ((RunSample) list.get(i)).getSampleData().getLongitude()));
                            f2 += this$0.q;
                            i++;
                            arrayList3 = arrayList;
                            z = true;
                            f = 0.0f;
                        }
                    }
                    arrayList = arrayList3;
                    f2 += this$0.q;
                    i++;
                    arrayList3 = arrayList;
                    z = true;
                    f = 0.0f;
                }
                ArrayList arrayList5 = arrayList3;
                if (polylineOptions.getPoints() != null && polylineOptions.getPoints().size() > 0) {
                    this$0.drawPolyLineOnMap(polylineOptions, builder);
                }
                BarDataSet barDataSet = new BarDataSet(arrayList2, "");
                Resources resources = this$0.getResources();
                int i3 = R.color.colorPrimary;
                barDataSet.setColor(ResourcesCompat.getColor(resources, i3, null));
                BarData barData = new BarData(barDataSet);
                barData.setDrawValues(false);
                int i4 = R.id.steps_chart;
                ((BarChart) this$0._$_findCachedViewById(i4)).setData(barData);
                BarChart steps_chart = (BarChart) this$0._$_findCachedViewById(i4);
                Intrinsics.checkNotNullExpressionValue(steps_chart, "steps_chart");
                this$0.setupBarChart(steps_chart);
                LineDataSet lineDataSet = new LineDataSet(arrayList5, "");
                lineDataSet.setColor(ResourcesCompat.getColor(this$0.getResources(), i3, null));
                lineDataSet.setDrawCircles(false);
                LineData lineData = new LineData(lineDataSet);
                lineData.setDrawValues(false);
                int i5 = R.id.pace_chart;
                ((LineChart) this$0._$_findCachedViewById(i5)).setData(lineData);
                LineChart pace_chart = (LineChart) this$0._$_findCachedViewById(i5);
                Intrinsics.checkNotNullExpressionValue(pace_chart, "pace_chart");
                this$0.setupLineChart(pace_chart);
                ArrayList arrayList6 = new ArrayList();
                for (Entry entry : arrayList4) {
                    if (entry.getY() == 0.0f) {
                        arrayList6.add(entry);
                    }
                }
                arrayList4.removeAll(arrayList6);
                LineDataSet lineDataSet2 = new LineDataSet(arrayList4, "");
                lineDataSet2.setColor(ResourcesCompat.getColor(this$0.getResources(), R.color.colorPrimary, null));
                lineDataSet2.setDrawCircles(false);
                LineDataSet lineDataSet3 = new LineDataSet(arrayList6, "");
                lineDataSet3.setColor(ResourcesCompat.getColor(this$0.getResources(), 17170445, null));
                lineDataSet3.setDrawCircles(false);
                LineData lineData2 = new LineData(lineDataSet3, lineDataSet2);
                lineData2.setDrawValues(false);
                int i6 = R.id.heart_rate_chart;
                ((LineChart) this$0._$_findCachedViewById(i6)).setData(lineData2);
                LineChart heart_rate_chart = (LineChart) this$0._$_findCachedViewById(i6);
                Intrinsics.checkNotNullExpressionValue(heart_rate_chart, "heart_rate_chart");
                this$0.setupLineChart(heart_rate_chart);
            }
        }
    }

    public static final void I(ActivityWorkoutDetails this$0, EntityWorkoutSession entityWorkoutSession) {
        String string;
        String string2;
        String str;
        LiveData<List<RunSample>> liveDataRunSamples;
        ViewModelWorkoutDetails viewModelWorkoutDetails;
        LiveData<List<WalkSample>> liveDataWalkSamples;
        WorkoutUtils workoutUtils;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (entityWorkoutSession != null) {
            this$0.q = entityWorkoutSession.getSteps_sampling_rate();
            DecimalFormat decimalFormat = new DecimalFormat("#00.00");
            Locale locale = Locale.ENGLISH;
            decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this$0).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                StringBuilder sb = new StringBuilder();
                sb.append(decimalFormat.format(WorkoutUtils.INSTANCE.convertKMToMiles(workoutUtils.convertCmToKm(entityWorkoutSession.getTotal_distance()))));
                sb.append(' ');
                sb.append(this$0.getResources().getString(R.string.mil));
                ((TextView) this$0._$_findCachedViewById(R.id.tv_distance)).setText(sb.toString());
            } else {
                ((TextView) this$0._$_findCachedViewById(R.id.tv_distance)).setText(decimalFormat.format(Float.valueOf(WorkoutUtils.INSTANCE.convertCmToKm(entityWorkoutSession.getTotal_distance()))) + ' ' + this$0.getResources().getString(R.string.kms));
            }
            if (entityWorkoutSession.getTotal_steps() > 0) {
                if (BaseUnitType.INSTANCE.getUnitTypeStrideLength() == UNIT_TYPE.METRIC) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string3 = this$0.getString(R.string.your_stride_length_for);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.your_stride_length_for)");
                    String format = String.format(string3, Arrays.copyOf(new Object[]{(entityWorkoutSession.getTotal_distance() / entityWorkoutSession.getTotal_steps()) + " cms"}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    ((TextView) this$0._$_findCachedViewById(R.id.tv_stridelength)).setText(format);
                } else {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String string4 = this$0.getString(R.string.your_stride_length_for);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.your_stride_length_for)");
                    String format2 = String.format(string4, Arrays.copyOf(new Object[]{AppUtils.convertCmInches(entityWorkoutSession.getTotal_distance() / entityWorkoutSession.getTotal_steps()) + " in"}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    ((TextView) this$0._$_findCachedViewById(R.id.tv_stridelength)).setText(format2);
                }
                ((ImageButton) this$0._$_findCachedViewById(R.id.info)).setVisibility(0);
            } else {
                ((ImageButton) this$0._$_findCachedViewById(R.id.info)).setVisibility(8);
            }
            TextView textView = (TextView) this$0._$_findCachedViewById(R.id.tv_max_hr);
            if (entityWorkoutSession.getMax_hr() != 0) {
                string = entityWorkoutSession.getMax_hr() + ' ' + this$0.getResources().getString(R.string.bpm_small);
            } else {
                string = this$0.getResources().getString(R.string.empty_bpm);
            }
            textView.setText(string);
            TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.tv_min_hr);
            if (entityWorkoutSession.getMin_hr() != 0) {
                string2 = entityWorkoutSession.getMin_hr() + ' ' + this$0.getResources().getString(R.string.bpm_small);
            } else {
                string2 = this$0.getResources().getString(R.string.empty_bpm);
            }
            textView2.setText(string2);
            TextView textView3 = (TextView) this$0._$_findCachedViewById(R.id.tv_calories);
            if (entityWorkoutSession.getTotal_calories() == 0.0f) {
                str = "--";
            } else {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                str = String.format(locale, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(entityWorkoutSession.getTotal_calories())}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
            }
            textView3.setText(str);
            WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
            ((TextView) this$0._$_findCachedViewById(R.id.tv_duration)).setText(workoutUtils2.getFormattedDuration(entityWorkoutSession.getSession_duration()));
            ((TextView) this$0._$_findCachedViewById(R.id.tv_pace)).setText(workoutUtils2.getFormattedPace(entityWorkoutSession.getPace()));
            this$0.setHRZoneDataToUi(entityWorkoutSession);
            ((TextView) this$0._$_findCachedViewById(R.id.steps_value)).setText(String.valueOf(entityWorkoutSession.getTotal_steps()));
            this$0.w = entityWorkoutSession.getTotal_steps();
            ((TextView) this$0._$_findCachedViewById(R.id.heart_rate_value)).setText(entityWorkoutSession.getMin_hr() + " - " + entityWorkoutSession.getMax_hr() + ' ' + this$0.getResources().getString(R.string.bpm));
            this$0.x = entityWorkoutSession.getMin_hr();
            this$0.y = entityWorkoutSession.getMax_hr();
            ((TextView) this$0._$_findCachedViewById(R.id.pace_value)).setText(workoutUtils2.getFormattedPace(entityWorkoutSession.getPace()));
            this$0.z = entityWorkoutSession.getPace();
            int i = WhenMappings.$EnumSwitchMapping$0[this$0.v.ordinal()];
            if (i != 1) {
                if (i != 2 || (viewModelWorkoutDetails = this$0.r) == null || (liveDataWalkSamples = viewModelWorkoutDetails.getLiveDataWalkSamples()) == null) {
                    return;
                }
                liveDataWalkSamples.observe(this$0, this$0.B);
                return;
            }
            ViewModelWorkoutDetails viewModelWorkoutDetails2 = this$0.r;
            if (viewModelWorkoutDetails2 == null || (liveDataRunSamples = viewModelWorkoutDetails2.getLiveDataRunSamples()) == null) {
                return;
            }
            liveDataRunSamples.observe(this$0, this$0.C);
        }
    }

    public static final void J(final ActivityWorkoutDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.t1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutDetails.K(list, this$0);
            }
        });
    }

    public static final void K(List list, ActivityWorkoutDetails this$0) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            boolean z = true;
            if (!list.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList<Entry> arrayList4 = new ArrayList();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(ContextCompat.getColor(this$0, R.color.colorPrimary));
                int size = list.size();
                float f = 0.0f;
                float f2 = 0.0f;
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    arrayList2.add(new BarEntry(f2, ((WalkSample) list.get(i)).getStepCount()));
                    i2 += ((WalkSample) list.get(i)).getSampleData().getDistance();
                    if (i2 != 0) {
                        arrayList3.add(new Entry(f2, f2 / (i2 / 100000.0f)));
                    } else {
                        arrayList3.add(new Entry(f2, f));
                    }
                    float hr_value = ((WalkSample) list.get(i)).getSampleData().getHr_value();
                    if ((hr_value == f ? z : false) && i > 0) {
                        hr_value = ((Entry) arrayList4.get(i - 1)).getY();
                    }
                    arrayList4.add(new Entry(f2, hr_value));
                    if (!(((WalkSample) list.get(i)).getSampleData().getLatitude() == 0.0d ? z : false)) {
                        if (!(((WalkSample) list.get(i)).getSampleData().getLongitude() == 0.0d ? z : false)) {
                            arrayList = arrayList3;
                            polylineOptions.add(new LatLng(((WalkSample) list.get(i)).getSampleData().getLatitude(), ((WalkSample) list.get(i)).getSampleData().getLongitude()));
                            builder.include(new LatLng(((WalkSample) list.get(i)).getSampleData().getLatitude(), ((WalkSample) list.get(i)).getSampleData().getLongitude()));
                            f2 += this$0.q;
                            i++;
                            arrayList3 = arrayList;
                            z = true;
                            f = 0.0f;
                        }
                    }
                    arrayList = arrayList3;
                    f2 += this$0.q;
                    i++;
                    arrayList3 = arrayList;
                    z = true;
                    f = 0.0f;
                }
                ArrayList arrayList5 = arrayList3;
                if (polylineOptions.getPoints() != null && polylineOptions.getPoints().size() > 0) {
                    this$0.drawPolyLineOnMap(polylineOptions, builder);
                }
                BarDataSet barDataSet = new BarDataSet(arrayList2, "");
                Resources resources = this$0.getResources();
                int i3 = R.color.colorPrimary;
                barDataSet.setColor(ResourcesCompat.getColor(resources, i3, null));
                BarData barData = new BarData(barDataSet);
                barData.setDrawValues(false);
                int i4 = R.id.steps_chart;
                ((BarChart) this$0._$_findCachedViewById(i4)).setData(barData);
                BarChart steps_chart = (BarChart) this$0._$_findCachedViewById(i4);
                Intrinsics.checkNotNullExpressionValue(steps_chart, "steps_chart");
                this$0.setupBarChart(steps_chart);
                LineDataSet lineDataSet = new LineDataSet(arrayList5, "");
                lineDataSet.setColor(ResourcesCompat.getColor(this$0.getResources(), i3, null));
                lineDataSet.setDrawCircles(false);
                LineData lineData = new LineData(lineDataSet);
                lineData.setDrawValues(false);
                int i5 = R.id.pace_chart;
                ((LineChart) this$0._$_findCachedViewById(i5)).setData(lineData);
                LineChart pace_chart = (LineChart) this$0._$_findCachedViewById(i5);
                Intrinsics.checkNotNullExpressionValue(pace_chart, "pace_chart");
                this$0.setupLineChart(pace_chart);
                ArrayList arrayList6 = new ArrayList();
                for (Entry entry : arrayList4) {
                    if (entry.getY() == 0.0f) {
                        arrayList6.add(entry);
                    }
                }
                arrayList4.removeAll(arrayList6);
                LineDataSet lineDataSet2 = new LineDataSet(arrayList4, "");
                lineDataSet2.setColor(ResourcesCompat.getColor(this$0.getResources(), R.color.colorPrimary, null));
                lineDataSet2.setDrawCircles(false);
                LineDataSet lineDataSet3 = new LineDataSet(arrayList6, "");
                lineDataSet3.setColor(ResourcesCompat.getColor(this$0.getResources(), 17170445, null));
                lineDataSet3.setDrawCircles(false);
                LineData lineData2 = new LineData(lineDataSet3, lineDataSet2);
                lineData2.setDrawValues(false);
                int i6 = R.id.heart_rate_chart;
                ((LineChart) this$0._$_findCachedViewById(i6)).setData(lineData2);
                LineChart heart_rate_chart = (LineChart) this$0._$_findCachedViewById(i6);
                Intrinsics.checkNotNullExpressionValue(heart_rate_chart, "heart_rate_chart");
                this$0.setupLineChart(heart_rate_chart);
            }
        }
    }

    public static final void L(ActivityWorkoutDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void M(ActivityWorkoutDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, HeartRateZoneInfoActivity.class));
    }

    public static final void N(ActivityWorkoutDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((CardView) this$0._$_findCachedViewById(R.id.cv_stride_length_info)).setVisibility(0);
    }

    public static final void O(ActivityWorkoutDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, StrideLengthAnimationActivity.class));
    }

    public static final void P(ActivityWorkoutDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((CardView) this$0._$_findCachedViewById(R.id.cv_stride_length_info)).setVisibility(8);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_SUMMARY_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final String Q(ActivityWorkoutDetails this$0, float f, AxisBase axisBase) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getFormattedPaceForGraph(f);
    }

    public final void F() {
        LiveData<EntityWorkoutSession> liveDataWorkoutSession;
        String str = this.s;
        if (str != null) {
            ViewModelWorkoutDetails viewModelWorkoutDetails = this.r;
            if (viewModelWorkoutDetails != null) {
                Intrinsics.checkNotNull(str);
                viewModelWorkoutDetails.getWorkoutSessionAndSamplesFromDB(str, this.v);
            }
            ViewModelWorkoutDetails viewModelWorkoutDetails2 = this.r;
            if (viewModelWorkoutDetails2 == null || (liveDataWorkoutSession = viewModelWorkoutDetails2.getLiveDataWorkoutSession()) == null) {
                return;
            }
            liveDataWorkoutSession.observe(this, this.A);
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

    public final void drawPolyLineOnMap(@NotNull final PolylineOptions polylineOptions, @NotNull final LatLngBounds.Builder builder) {
        Intrinsics.checkNotNullParameter(polylineOptions, "polylineOptions");
        Intrinsics.checkNotNullParameter(builder, "builder");
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.r1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutDetails.D(ActivityWorkoutDetails.this, polylineOptions, builder);
            }
        });
    }

    @Nullable
    public final BottomSheetBehavior<ConstraintLayout> getBottomSheetBehavior() {
        return this.u;
    }

    @NotNull
    public final String getFormattedPaceForGraph(float f) {
        if (f == 0.0f) {
            return "0'0''";
        }
        int i = (int) (f / 60.0f);
        int i2 = (int) (f % 60.0f);
        if (i > 99) {
            i = 99;
        }
        if (i != 0) {
            if (i != 99) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "%d'%d''", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                return format;
            }
            return "99'99''";
        }
        return WorkoutConstants.EMPTY_PACE_VALUE;
    }

    @Nullable
    public final GoogleMap getGoogleMap() {
        return this.t;
    }

    public final int getHrMax() {
        return this.y;
    }

    public final int getHrMin() {
        return this.x;
    }

    public final float getPace() {
        return this.z;
    }

    public final int getSteps() {
        return this.w;
    }

    @NotNull
    public final String getTimeSpentHRZone(@NotNull EntityWorkoutSession entityWorkoutSession) {
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        TimeSpentHeartRateZone timespent_per_heartratezone = entityWorkoutSession.getTimespent_per_heartratezone();
        if (timespent_per_heartratezone != null) {
            return "Zone1: " + timespent_per_heartratezone.getZone1Time() + " Zone2: " + timespent_per_heartratezone.getZone2Time() + " Zone3: " + timespent_per_heartratezone.getZone3Time() + " Zone4: " + timespent_per_heartratezone.getZone4Time() + " Zone5: " + timespent_per_heartratezone.getZone5Time();
        }
        return "";
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_workout_details);
        this.r = (ViewModelWorkoutDetails) ViewModelProviders.of(this).get(ViewModelWorkoutDetails.class);
        Serializable serializableExtra = getIntent().getSerializableExtra(WorkoutConstants.ACTIVITY_MODE);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.ActivityMode");
        this.v = (ActivityMode) serializableExtra;
        Bundle extras = getIntent().getExtras();
        this.s = extras != null ? extras.getString(WorkoutConstants.SESSION_ID) : null;
        this.u = BottomSheetBehavior.from((ConstraintLayout) _$_findCachedViewById(R.id.bottom_sheet));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.summary);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.summary)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{this.v.toString()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        ((TextView) _$_findCachedViewById(R.id.type_text)).setText(format);
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(R.id.close);
        Intrinsics.checkNotNull(imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutDetails.L(ActivityWorkoutDetails.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_hr_zone_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutDetails.M(ActivityWorkoutDetails.this, view);
            }
        });
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        ((SupportMapFragment) findFragmentById).getMapAsync(this);
        String str = this.s;
        Intrinsics.checkNotNull(str);
        WorkoutSessionRepository.Companion.getInstance(this).getSessionDetails(str);
        F();
        ((ImageButton) _$_findCachedViewById(R.id.info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.x1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutDetails.N(ActivityWorkoutDetails.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.calculate_stride_length_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutDetails.O(ActivityWorkoutDetails.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_close_stridelength_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.w1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutDetails.P(ActivityWorkoutDetails.this, view);
            }
        });
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Log.d(this.p, "onMapReady: called");
        this.t = p0;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            GoogleMap googleMap = this.t;
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(false);
            }
            GoogleMap googleMap2 = this.t;
            UiSettings uiSettings = googleMap2 != null ? googleMap2.getUiSettings() : null;
            if (uiSettings != null) {
                uiSettings.setMyLocationButtonEnabled(false);
            }
            GoogleMap googleMap3 = this.t;
            UiSettings uiSettings2 = googleMap3 != null ? googleMap3.getUiSettings() : null;
            if (uiSettings2 != null) {
                uiSettings2.setScrollGesturesEnabled(true);
            }
            GoogleMap googleMap4 = this.t;
            UiSettings uiSettings3 = googleMap4 != null ? googleMap4.getUiSettings() : null;
            if (uiSettings3 == null) {
                return;
            }
            uiSettings3.setZoomGesturesEnabled(true);
        }
    }

    public final void setBottomSheetBehavior(@Nullable BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior) {
        this.u = bottomSheetBehavior;
    }

    public final void setGoogleMap(@Nullable GoogleMap googleMap) {
        this.t = googleMap;
    }

    public final void setHRZoneDataToUi(@NotNull EntityWorkoutSession entityWorkoutSession) {
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        TimeSpentHeartRateZone timespent_per_heartratezone = entityWorkoutSession.getTimespent_per_heartratezone();
        if (timespent_per_heartratezone != null) {
            int zone1Time = timespent_per_heartratezone.getZone1Time() + timespent_per_heartratezone.getZone2Time() + timespent_per_heartratezone.getZone3Time() + timespent_per_heartratezone.getZone4Time() + timespent_per_heartratezone.getZone5Time();
            StringBuilder sb = new StringBuilder();
            float f = zone1Time;
            float f2 = 100;
            sb.append((int) ((timespent_per_heartratezone.getZone1Time() / f) * f2));
            sb.append(" %");
            ((TextView) _$_findCachedViewById(R.id.tv_progress_warm)).setText(sb.toString());
            ((TextView) _$_findCachedViewById(R.id.tv_progress_fat_burn)).setText(((int) ((timespent_per_heartratezone.getZone2Time() / f) * f2)) + " %");
            ((TextView) _$_findCachedViewById(R.id.tv_progress_cardio)).setText(((int) ((((float) timespent_per_heartratezone.getZone3Time()) / f) * f2)) + " %");
            ((TextView) _$_findCachedViewById(R.id.tv_progress_threshold)).setText(((int) ((((float) timespent_per_heartratezone.getZone4Time()) / f) * f2)) + " %");
            ((TextView) _$_findCachedViewById(R.id.tv_progress_peak)).setText(((int) ((((float) timespent_per_heartratezone.getZone5Time()) / f) * f2)) + " %");
            ((ProgressBar) _$_findCachedViewById(R.id.pb_warm)).setProgress((int) ((((float) timespent_per_heartratezone.getZone1Time()) / f) * f2));
            ((ProgressBar) _$_findCachedViewById(R.id.pb_fat_burn)).setProgress((int) ((((float) timespent_per_heartratezone.getZone2Time()) / f) * f2));
            ((ProgressBar) _$_findCachedViewById(R.id.pb_cardio)).setProgress((int) ((((float) timespent_per_heartratezone.getZone3Time()) / f) * f2));
            ((ProgressBar) _$_findCachedViewById(R.id.pb_threshold)).setProgress((int) ((timespent_per_heartratezone.getZone4Time() / f) * f2));
            ((ProgressBar) _$_findCachedViewById(R.id.pb_peak)).setProgress((int) ((timespent_per_heartratezone.getZone5Time() / f) * f2));
        }
    }

    public final void setHrMax(int i) {
        this.y = i;
    }

    public final void setHrMin(int i) {
        this.x = i;
    }

    public final void setPace(float f) {
        this.z = f;
    }

    public final void setSteps(int i) {
        this.w = i;
    }

    public final void setupBarChart(@NotNull BarChart chart) {
        Intrinsics.checkNotNullParameter(chart, "chart");
        chart.getDescription().setText("");
        chart.setGridBackgroundColor(17170445);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setAutoScaleMinMaxEnabled(true);
        chart.setScaleEnabled(true);
        chart.getLegend().setEnabled(false);
        chart.setScrollContainer(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutDetails$setupBarChart$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                if (f < 0.0f) {
                    return "";
                }
                int roundToInt = kotlin.math.c.roundToInt(f);
                if (roundToInt < 60) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format(Locale.ENGLISH, "00:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(roundToInt)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    return format;
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format(Locale.ENGLISH, "%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(roundToInt / 60), Integer.valueOf(roundToInt % 60)}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                return format2.toString();
            }
        });
        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawGridLines(false);
        if (this.w == 0) {
            axisLeft.mAxisMinimum = 0.0f;
            axisLeft.setAxisMaximum(100.0f);
        }
        chart.getAxisRight().setEnabled(false);
        chart.setClickable(false);
        chart.invalidate();
    }

    public final void setupLineChart(@NotNull LineChart chart) {
        Intrinsics.checkNotNullParameter(chart, "chart");
        chart.getDescription().setText("");
        chart.setGridBackgroundColor(17170445);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setAutoScaleMinMaxEnabled(false);
        chart.setScaleEnabled(false);
        chart.getLegend().setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutDetails$setupLineChart$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                if (f < 0.0f) {
                    return "";
                }
                int roundToInt = kotlin.math.c.roundToInt(f);
                if (roundToInt < 60) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format(Locale.ENGLISH, "00:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(roundToInt)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    return format;
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format(Locale.ENGLISH, "%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(roundToInt / 60), Integer.valueOf(roundToInt % 60)}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                return format2.toString();
            }
        });
        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawGridLines(false);
        if (chart.equals((LineChart) _$_findCachedViewById(R.id.pace_chart))) {
            if (this.z == 0.0f) {
                axisLeft.setAxisMinimum(0.0f);
                axisLeft.setAxisMaximum(100.0f);
            }
            axisLeft.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.activitymodes.activities.b2
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                public final String getFormattedValue(float f, AxisBase axisBase) {
                    String Q;
                    Q = ActivityWorkoutDetails.Q(ActivityWorkoutDetails.this, f, axisBase);
                    return Q;
                }
            });
        } else if (chart.equals((LineChart) _$_findCachedViewById(R.id.heart_rate_chart))) {
            if (this.y == 0 && this.x == 0) {
                axisLeft.setAxisMinimum(0.0f);
                axisLeft.setAxisMaximum(100.0f);
            }
            axisLeft.setGranularity(50.0f);
        }
        chart.getAxisRight().setEnabled(false);
        chart.setClickable(false);
        chart.invalidate();
    }
}
