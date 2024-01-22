package com.coveiot.android.theme.compundview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.FitnessBuddiesConstants;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CustomMarkerViewVitals extends MarkerView {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final TextView h;
    @NotNull
    public final TextView i;
    @NotNull
    public final LinearLayout j;
    @Nullable
    public CandleEntry k;
    @NotNull
    public final String l;
    public final int m;
    @NotNull
    public final ArrayList<String> n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomMarkerViewVitals(@Nullable Context context, int i, @NotNull String type, int i2, @NotNull ArrayList<String> labels) {
        super(context, i);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(labels, "labels");
        this._$_findViewCache = new LinkedHashMap();
        View findViewById = findViewById(R.id.tvTime);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.h = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tvValue);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.i = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.custom_view_layout);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.j = (LinearLayout) findViewById3;
        this.l = type;
        this.m = i2;
        this.n = labels;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void draw(@NotNull Canvas canvas, float f, float f2) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.draw(canvas, f, f2);
        getOffsetForDrawingAtPoint(f, f2);
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @NotNull
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @SuppressLint({"SetTextI18n"})
    public void refreshContent(@NotNull Entry e, @NotNull Highlight highlight) {
        double d;
        int i;
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        if (e instanceof BarEntry) {
            BarEntry barEntry = (BarEntry) e;
            barEntry.getX();
            int i2 = this.m;
            if (i2 == 0) {
                setStartEndTime((int) barEntry.getX());
            } else if (i2 == 6) {
                TextView textView = this.h;
                ArrayList<String> arrayList = this.n;
                Intrinsics.checkNotNull(arrayList);
                textView.setText(arrayList.get(((int) barEntry.getX()) - 1));
            } else {
                TextView textView2 = this.h;
                ArrayList<String> arrayList2 = this.n;
                Intrinsics.checkNotNull(arrayList2);
                textView2.setText(arrayList2.get((int) barEntry.getX()));
            }
            barEntry.getY();
            if (m.equals(this.l, getContext().getString(R.string.steps), true)) {
                if (barEntry.getY() > 0.0f) {
                    this.i.setText(((int) e.getY()) + ' ' + getResources().getString(i));
                    return;
                }
                this.i.setText("-- " + getResources().getString(i));
            } else if (m.equals(this.l, getContext().getString(R.string.spo2), true)) {
                if (barEntry.getY() > 0.0f) {
                    this.i.setText(((int) e.getY()) + " %");
                    return;
                }
                this.i.setText("-- %");
            } else if (m.equals(this.l, getContext().getString(R.string.stress), true)) {
                if (barEntry.getY() > 0.0f) {
                    TextView textView3 = this.i;
                    StringBuilder sb = new StringBuilder();
                    sb.append((int) e.getY());
                    sb.append(' ');
                    Context context = getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    sb.append(DeviceUtils.Companion.getStressRange((int) e.getY(), context));
                    textView3.setText(sb.toString());
                    return;
                }
                this.i.setText("--");
            } else if (m.equals(this.l, getContext().getString(R.string.sens_ai), true)) {
                TextView textView4 = this.i;
                StringBuilder sb2 = new StringBuilder();
                sb2.append((int) e.getY());
                sb2.append('%');
                textView4.setText(sb2.toString());
            } else if (m.equals(this.l, getContext().getString(R.string.sens_ai_details), true)) {
                if (barEntry.getY() > 0.0f) {
                    Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
                    Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                    if (isDistanceUnitInMile.booleanValue()) {
                        this.i.setText(((int) e.getY()) + getContext().getString(R.string.mi_hr));
                        return;
                    }
                    this.i.setText(((int) e.getY()) + getContext().getString(R.string.km_hr));
                    return;
                }
                this.i.setText("--");
            } else if (m.equals(this.l, FitnessBuddiesConstants.BUDDY_DETAILS, true)) {
                if (barEntry.getY() > 0.0f) {
                    this.i.setText(((int) e.getY()) + "  " + getContext().getString(i));
                    return;
                }
                this.i.setText("--");
            }
        } else if (e instanceof CandleEntry) {
            CandleEntry candleEntry = (CandleEntry) e;
            this.k = candleEntry;
            Intrinsics.checkNotNull(candleEntry);
            if (!(candleEntry.getHigh() == 0.0f)) {
                if (Intrinsics.areEqual(this.l, getContext().getString(R.string.heart_rate))) {
                    TextView textView5 = this.i;
                    StringBuilder sb3 = new StringBuilder();
                    CandleEntry candleEntry2 = this.k;
                    Intrinsics.checkNotNull(candleEntry2);
                    sb3.append((int) candleEntry2.getOpen());
                    sb3.append(" - ");
                    CandleEntry candleEntry3 = this.k;
                    Intrinsics.checkNotNull(candleEntry3);
                    sb3.append((int) candleEntry3.getClose());
                    sb3.append(' ');
                    sb3.append(getResources().getString(R.string.bpm_small));
                    textView5.setText(sb3.toString());
                } else if (Intrinsics.areEqual(this.l, getContext().getString(R.string.nightly_breathing_rate))) {
                    TextView textView6 = this.i;
                    StringBuilder sb4 = new StringBuilder();
                    CandleEntry candleEntry4 = this.k;
                    Intrinsics.checkNotNull(candleEntry4);
                    sb4.append((int) candleEntry4.getOpen());
                    sb4.append(" - ");
                    CandleEntry candleEntry5 = this.k;
                    Intrinsics.checkNotNull(candleEntry5);
                    sb4.append((int) candleEntry5.getClose());
                    sb4.append(' ');
                    sb4.append(getResources().getString(R.string.brpm_small));
                    textView6.setText(sb4.toString());
                }
            } else {
                this.i.setText(getResources().getString(R.string.no_data));
            }
            if (this.m == 0) {
                setStartEndTime((int) candleEntry.getX());
                return;
            }
            TextView textView7 = this.h;
            ArrayList<String> arrayList3 = this.n;
            Intrinsics.checkNotNull(arrayList3);
            textView7.setText(arrayList3.get((int) candleEntry.getX()));
        } else {
            e.getX();
            setStartEndTime((int) e.getX());
            e.getY();
            e.getY();
            if (Intrinsics.areEqual(this.l, getContext().getString(R.string.energy_meter))) {
                this.i.setText(String.valueOf((int) e.getY()));
            } else if (Intrinsics.areEqual(this.l, getContext().getString(R.string.temperature))) {
                if (e.getY() > 0.0f) {
                    float y = e.getY();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(ThemesUtils.getTemperatureInCelsius(y));
                    sb5.append("");
                    String sb6 = sb5.toString();
                    String str = ThemesUtils.getTemperatureInFahrenheit(d) + "";
                    Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(getContext()).isTemperatureUnitInFahrenheit();
                    Intrinsics.checkNotNullExpressionValue(isTemperatureUnitInFahrenheit, "getInstance(context).isTemperatureUnitInFahrenheit");
                    if (isTemperatureUnitInFahrenheit.booleanValue()) {
                        this.i.setText(sb6 + " °C / " + e.getY() + "°F");
                        return;
                    }
                    this.i.setText(e.getY() + " °C / " + str + "°F");
                    return;
                }
                this.i.setText("--");
            } else if (Intrinsics.areEqual(this.l, getContext().getString(R.string.nightly_breathing_rate))) {
                if (e.getY() > 0.0f) {
                    this.i.setText(((int) e.getY()) + ' ' + getContext().getString(R.string.brpm_small));
                    return;
                }
                this.i.setText("-- " + getContext().getString(R.string.brpm_small));
            } else if (Intrinsics.areEqual(this.l, getContext().getString(R.string.hrv))) {
                if (e.getY() > 1.0f) {
                    this.i.setText(((int) e.getY()) + ' ' + getContext().getString(R.string.ms));
                    return;
                }
                this.i.setText("-- " + getContext().getString(R.string.ms));
            }
        }
    }

    public final void setStartEndTime(int i) {
        if (this.m == 0) {
            if (this.n != null) {
                if (m.equals(this.l, getContext().getString(R.string.energy_meter), true)) {
                    if (i == 0) {
                        TextView textView = this.h;
                        ArrayList<String> arrayList = this.n;
                        Intrinsics.checkNotNull(arrayList);
                        textView.setText(arrayList.get(i));
                    } else {
                        TextView textView2 = this.h;
                        StringBuilder sb = new StringBuilder();
                        ArrayList<String> arrayList2 = this.n;
                        Intrinsics.checkNotNull(arrayList2);
                        sb.append(arrayList2.get(i - 1));
                        sb.append(Soundex.SILENT_MARKER);
                        ArrayList<String> arrayList3 = this.n;
                        Intrinsics.checkNotNull(arrayList3);
                        sb.append(arrayList3.get(i));
                        textView2.setText(sb.toString());
                    }
                } else if (m.equals(this.l, getContext().getString(R.string.nightly_breathing_rate), true)) {
                    if (i == 0) {
                        TextView textView3 = this.h;
                        ArrayList<String> arrayList4 = this.n;
                        Intrinsics.checkNotNull(arrayList4);
                        textView3.setText(arrayList4.get(i));
                    } else {
                        TextView textView4 = this.h;
                        StringBuilder sb2 = new StringBuilder();
                        ArrayList<String> arrayList5 = this.n;
                        Intrinsics.checkNotNull(arrayList5);
                        sb2.append(arrayList5.get(i - 1));
                        sb2.append(Soundex.SILENT_MARKER);
                        ArrayList<String> arrayList6 = this.n;
                        Intrinsics.checkNotNull(arrayList6);
                        sb2.append(arrayList6.get(i));
                        textView4.setText(sb2.toString());
                    }
                } else if (i != 23) {
                    TextView textView5 = this.h;
                    StringBuilder sb3 = new StringBuilder();
                    ArrayList<String> arrayList7 = this.n;
                    Intrinsics.checkNotNull(arrayList7);
                    sb3.append(arrayList7.get(i));
                    sb3.append(Soundex.SILENT_MARKER);
                    ArrayList<String> arrayList8 = this.n;
                    Intrinsics.checkNotNull(arrayList8);
                    sb3.append(arrayList8.get(i + 1));
                    textView5.setText(sb3.toString());
                } else {
                    TextView textView6 = this.h;
                    StringBuilder sb4 = new StringBuilder();
                    ArrayList<String> arrayList9 = this.n;
                    Intrinsics.checkNotNull(arrayList9);
                    sb4.append(arrayList9.get(i));
                    sb4.append("-12 AM");
                    textView6.setText(sb4.toString());
                }
            }
            if (m.equals(this.l, getContext().getString(R.string.heart_rate), true)) {
                if (i == 0) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_left_2));
                    return;
                } else if (i != 23) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_bg));
                    return;
                } else {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right));
                    return;
                }
            } else if (m.equals(this.l, getContext().getString(R.string.steps), true)) {
                if (i == 23) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_1));
                    return;
                } else {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_bg));
                    return;
                }
            } else if (m.equals(this.l, getContext().getString(R.string.spo2), true)) {
                if (i == 0) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_left_2));
                    return;
                } else if (i == 22) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_2));
                    return;
                } else if (i != 23) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_bg));
                    return;
                } else {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_1));
                    return;
                }
            } else if (m.equals(this.l, getContext().getString(R.string.stress), true)) {
                if (i == 0) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_left_2));
                    return;
                } else if (i == 22) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_2));
                    return;
                } else if (i != 23) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_bg));
                    return;
                } else {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_1));
                    return;
                }
            } else if (m.equals(this.l, getContext().getString(R.string.temperature), true)) {
                if (i == 0) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_left_2));
                    return;
                } else if (i == 22) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right_2));
                    return;
                } else if (i != 23) {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_bg));
                    return;
                } else {
                    this.j.setBackground(getContext().getResources().getDrawable(R.drawable.custom_marker_right));
                    return;
                }
            } else {
                return;
            }
        }
        TextView textView7 = this.h;
        ArrayList<String> arrayList10 = this.n;
        Intrinsics.checkNotNull(arrayList10);
        textView7.setText(arrayList10.get(i));
    }

    public final void setWeeklyXLabels(@NotNull String graphTime) {
        Intrinsics.checkNotNullParameter(graphTime, "graphTime");
        ArrayList<String> arrayList = this.n;
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = i + 1;
                it.next();
                if (i == 0) {
                    this.n.set(0, "MON");
                }
                if (i == 1) {
                    this.n.set(1, "TUE");
                }
                if (i == 2) {
                    this.n.set(2, "WED");
                }
                if (i == 3) {
                    this.n.set(3, "THU");
                }
                if (i == 4) {
                    this.n.set(4, "FRI");
                }
                if (i == 5) {
                    this.n.set(5, "SAT");
                }
                if (i == 6) {
                    this.n.set(6, "SUN");
                }
                i = i2;
            }
        }
    }

    public /* synthetic */ CustomMarkerViewVitals(Context context, int i, String str, int i2, ArrayList arrayList, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, i, str, (i3 & 8) != 0 ? 0 : i2, arrayList);
    }
}
