package com.coveiot.android.sleepenergyscore.energymeter.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.SleepEnergyScoreInfoAdapter;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreEventData;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreFeedbackEventData;
import com.coveiot.android.sleepenergyscore.energymeter.activities.ActivityEnergyMeterHistory;
import com.coveiot.android.sleepenergyscore.energymeter.activities.ActivityShare;
import com.coveiot.android.sleepenergyscore.energymeter.adapters.DrainSessionAdapter;
import com.coveiot.android.sleepenergyscore.energymeter.adapters.EnergyScoreLegendGridAdapter;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterDashBoard;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel;
import com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList;
import com.coveiot.android.sleepenergyscore.feedback.DismissLoader;
import com.coveiot.android.sleepenergyscore.feedback.PagerAdapterFeedback;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.android.sleepenergyscore.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ViewPagerContainer;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.ImageIndicator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.squareup.otto.Subscribe;
import com.viewpagerindicator.CirclePageIndicator;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeter extends BaseFragment implements View.OnClickListener, ContractEnergyMeterDashBoard, ContractFeedBackQuestionsList {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public DatePickerDialog datePickerDialog;
    public View[] dateSelectionView;
    @Nullable
    public Calendar n;
    public FragmentEnergyMeterViewModel q;
    public SimpleDateFormat simpleDateFormat;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final SimpleDateFormat m = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
    @NotNull
    public final int[] o = {R.string.energy_info1, R.string.energy_info5, R.string.energy_info2, R.string.energy_info3};
    @NotNull
    public final int[] p = {R.drawable.energy_info_image1, R.drawable.energy_info_image2, R.drawable.energy_info_image3, R.drawable.energy_info_image4};
    public final int r = 1;
    public boolean s = true;
    @NotNull
    public int[] t = {Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
    @NotNull
    public int[] u = {Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
    @NotNull
    public int[] v = {Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
    @NotNull
    public int[] w = {Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
    @NotNull
    public final ArrayList<PieEntry> x = new ArrayList<>();

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentEnergyMeter newInstance() {
            return new FragmentEnergyMeter();
        }
    }

    public static final void A(FragmentEnergyMeter this$0, View view) {
        EnergyData.ContributingFactors.Dock dock;
        ArrayList<EnergyData> arrayList;
        EnergyData.ContributingFactors.Dock dock2;
        ArrayList<EnergyData> arrayList2;
        ArrayList<EnergyData> arrayList3;
        EnergyData.ContributingFactors.Dock dock3;
        List<EnergyData.ContributingFactors.Dock.Session> sessions;
        EnergyData.ContributingFactors.Dock dock4;
        ArrayList<EnergyData> arrayList4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EnergyScoreRepository.Companion companion = EnergyScoreRepository.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Calendar calendar = this$0.n;
        Intrinsics.checkNotNull(calendar);
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(selectedDate!!.time, \"yyyy-MM-dd\")");
        EnergyScoreDbData energyScoreData = companion.getInstance(requireContext).getEnergyScoreData(formatDate, BleApiManager.getInstance(this$0.getContext()).getBleApi().getMacAddress());
        Double d = null;
        EnergyData energyData = (energyScoreData == null || (arrayList4 = energyScoreData.data) == null) ? null : arrayList4.get(0);
        Intrinsics.checkNotNull(energyData);
        boolean z = true;
        if (energyData.getContributingFactors() != null) {
            ArrayList<EnergyData> arrayList5 = energyScoreData != null ? energyScoreData.data : null;
            Intrinsics.checkNotNull(arrayList5);
            EnergyData energyData2 = arrayList5.get(0);
            Intrinsics.checkNotNull(energyData2);
            EnergyData.ContributingFactors contributingFactors = energyData2.getContributingFactors();
            if (((contributingFactors == null || (dock4 = contributingFactors.getDock()) == null) ? null : dock4.getSessions()) != null) {
                ArrayList<EnergyData> arrayList6 = energyScoreData != null ? energyScoreData.data : null;
                Intrinsics.checkNotNull(arrayList6);
                EnergyData energyData3 = arrayList6.get(0);
                Intrinsics.checkNotNull(energyData3);
                EnergyData.ContributingFactors contributingFactors2 = energyData3.getContributingFactors();
                Integer valueOf = (contributingFactors2 == null || (dock3 = contributingFactors2.getDock()) == null || (sessions = dock3.getSessions()) == null) ? null : Integer.valueOf(sessions.size());
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.intValue() > 0) {
                    int i = R.id.energy_drain_cv;
                    CardView cardView = (CardView) this$0._$_findCachedViewById(i);
                    if (cardView == null || cardView.getVisibility() != 0) {
                        z = false;
                    }
                    if (z) {
                        CardView cardView2 = (CardView) this$0._$_findCachedViewById(i);
                        if (cardView2 != null) {
                            cardView2.setVisibility(8);
                        }
                        TextView textView = (TextView) this$0._$_findCachedViewById(R.id.drain_txt);
                        if (textView != null) {
                            textView.setTextColor(this$0.getResources().getColor(R.color.white_opacity_50_color));
                        }
                    } else {
                        int[] iArr = this$0.v;
                        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
                        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                        this$0.t = copyOf;
                        CardView cardView3 = (CardView) this$0._$_findCachedViewById(i);
                        if (cardView3 != null) {
                            cardView3.setVisibility(0);
                        }
                        TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.drain_txt);
                        if (textView2 != null) {
                            textView2.setTextColor(this$0.getResources().getColor(R.color.white));
                        }
                    }
                    this$0.F();
                    return;
                }
            }
        }
        EnergyData energyData4 = (energyScoreData == null || (arrayList3 = energyScoreData.data) == null) ? null : arrayList3.get(0);
        Intrinsics.checkNotNull(energyData4);
        if (energyData4.getContributingFactors() != null) {
            EnergyData energyData5 = (energyScoreData == null || (arrayList2 = energyScoreData.data) == null) ? null : arrayList2.get(0);
            Intrinsics.checkNotNull(energyData5);
            EnergyData.ContributingFactors contributingFactors3 = energyData5.getContributingFactors();
            if (((contributingFactors3 == null || (dock2 = contributingFactors3.getDock()) == null) ? null : dock2.getNonSessionCalorieContribution()) != null) {
                EnergyData energyData6 = (energyScoreData == null || (arrayList = energyScoreData.data) == null) ? null : arrayList.get(0);
                Intrinsics.checkNotNull(energyData6);
                EnergyData.ContributingFactors contributingFactors4 = energyData6.getContributingFactors();
                if (contributingFactors4 != null && (dock = contributingFactors4.getDock()) != null) {
                    d = dock.getNonSessionCalorieContribution();
                }
                Intrinsics.checkNotNull(d);
                if (d.doubleValue() > 0.0d) {
                    int i2 = R.id.energy_drain_cv;
                    CardView cardView4 = (CardView) this$0._$_findCachedViewById(i2);
                    if (cardView4 == null || cardView4.getVisibility() != 0) {
                        z = false;
                    }
                    if (z) {
                        CardView cardView5 = (CardView) this$0._$_findCachedViewById(i2);
                        if (cardView5 != null) {
                            cardView5.setVisibility(8);
                        }
                        TextView textView3 = (TextView) this$0._$_findCachedViewById(R.id.drain_txt);
                        if (textView3 != null) {
                            textView3.setTextColor(this$0.getResources().getColor(R.color.white_opacity_50_color));
                        }
                    } else {
                        int[] iArr2 = this$0.v;
                        int[] copyOf2 = Arrays.copyOf(iArr2, iArr2.length);
                        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                        this$0.t = copyOf2;
                        CardView cardView6 = (CardView) this$0._$_findCachedViewById(i2);
                        if (cardView6 != null) {
                            cardView6.setVisibility(0);
                        }
                        TextView textView4 = (TextView) this$0._$_findCachedViewById(R.id.drain_txt);
                        if (textView4 != null) {
                            textView4.setTextColor(this$0.getResources().getColor(R.color.white));
                        }
                    }
                    this$0.F();
                }
            }
        }
    }

    public static final void B(FragmentEnergyMeter this$0, View view) {
        EnergyData.ContributingFactors.Replenish replenish;
        EnergyData.ContributingFactors.Replenish replenish2;
        ArrayList<EnergyData> arrayList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EnergyScoreRepository.Companion companion = EnergyScoreRepository.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Calendar calendar = this$0.n;
        Intrinsics.checkNotNull(calendar);
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(selectedDate!!.time, \"yyyy-MM-dd\")");
        EnergyScoreDbData energyScoreData = companion.getInstance(requireContext).getEnergyScoreData(formatDate, BleApiManager.getInstance(this$0.getContext()).getBleApi().getMacAddress());
        Double d = null;
        EnergyData energyData = (energyScoreData == null || (arrayList = energyScoreData.data) == null) ? null : arrayList.get(0);
        Intrinsics.checkNotNull(energyData);
        if (energyData.getContributingFactors() != null) {
            ArrayList<EnergyData> arrayList2 = energyScoreData != null ? energyScoreData.data : null;
            Intrinsics.checkNotNull(arrayList2);
            EnergyData energyData2 = arrayList2.get(0);
            Intrinsics.checkNotNull(energyData2);
            EnergyData.ContributingFactors contributingFactors = energyData2.getContributingFactors();
            if (((contributingFactors == null || (replenish2 = contributingFactors.getReplenish()) == null) ? null : replenish2.getSleepScoreContribution()) != null) {
                ArrayList<EnergyData> arrayList3 = energyScoreData != null ? energyScoreData.data : null;
                Intrinsics.checkNotNull(arrayList3);
                EnergyData energyData3 = arrayList3.get(0);
                Intrinsics.checkNotNull(energyData3);
                EnergyData.ContributingFactors contributingFactors2 = energyData3.getContributingFactors();
                if (contributingFactors2 != null && (replenish = contributingFactors2.getReplenish()) != null) {
                    d = replenish.getSleepScoreContribution();
                }
                Intrinsics.checkNotNull(d);
                if (d.doubleValue() > 0.0d) {
                    int i = R.id.energ_gain_cv;
                    CardView cardView = (CardView) this$0._$_findCachedViewById(i);
                    if (cardView != null && cardView.getVisibility() == 0) {
                        CardView cardView2 = (CardView) this$0._$_findCachedViewById(i);
                        if (cardView2 != null) {
                            cardView2.setVisibility(8);
                        }
                        TextView textView = (TextView) this$0._$_findCachedViewById(R.id.gain_txt);
                        if (textView != null) {
                            textView.setTextColor(this$0.getResources().getColor(R.color.white_opacity_50_color));
                        }
                    } else {
                        CardView cardView3 = (CardView) this$0._$_findCachedViewById(i);
                        if (cardView3 != null) {
                            cardView3.setVisibility(0);
                        }
                        TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.gain_txt);
                        if (textView2 != null) {
                            textView2.setTextColor(this$0.getResources().getColor(R.color.white));
                        }
                        int[] iArr = this$0.u;
                        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
                        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                        this$0.t = copyOf;
                    }
                    this$0.F();
                }
            }
        }
    }

    public static final void D(FragmentEnergyMeter this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.H(newDate);
        TextView tv_date1 = (TextView) this$0._$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        this$0.I(tv_date1);
    }

    public static final void J(FragmentEnergyMeter this$0, ArrayList arrayList, ArrayList arrayList2, TreeMap treeMap, TreeMap treeMap2, TreeMap treeMap3, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getView() == null || !this$0.isAdded()) {
            return;
        }
        if (arrayList != null) {
            LineChart lineChart = (LineChart) this$0._$_findCachedViewById(R.id.linechart_energy_meter);
            if (lineChart != null) {
                lineChart.setVisibility(0);
            }
            TextView textView = (TextView) this$0._$_findCachedViewById(R.id.no_data_tv);
            if (textView != null) {
                textView.setVisibility(8);
            }
            if (arrayList2 != null) {
                this$0.E(arrayList, arrayList2);
            }
        } else {
            LineChart lineChart2 = (LineChart) this$0._$_findCachedViewById(R.id.linechart_energy_meter);
            if (lineChart2 != null) {
                lineChart2.setVisibility(8);
            }
            TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.no_data_tv);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
        }
        this$0.t(treeMap, treeMap2, treeMap3, i);
        SimpleDateFormat simpleDateFormat = this$0.m;
        Utils utils = Utils.INSTANCE;
        Calendar calendar = this$0.n;
        Intrinsics.checkNotNull(calendar);
        Date parse = simpleDateFormat.parse(utils.getDateFormatValue(calendar));
        if (!Utils.currentDayString().equals(this$0.getSimpleDateFormat().format(parse))) {
            if (Utils.previousDayString().equals(this$0.getSimpleDateFormat().format(parse))) {
                TextView textView3 = (TextView) this$0._$_findCachedViewById(R.id.tv_date);
                if (textView3 == null) {
                    return;
                }
                textView3.setText(this$0.getResources().getString(R.string.yesterday));
                return;
            }
            TextView textView4 = (TextView) this$0._$_findCachedViewById(R.id.tv_date);
            if (textView4 == null) {
                return;
            }
            textView4.setText(this$0.getSimpleDateFormat().format(parse));
            return;
        }
        TextView textView5 = (TextView) this$0._$_findCachedViewById(R.id.tv_date);
        if (textView5 == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this$0.getResources().getString(R.string.today));
        sb.append(' ');
        long currentTimeMillis = System.currentTimeMillis();
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(Utils.getCurrentHour(currentTimeMillis, requireContext));
        textView5.setText(sb.toString());
    }

    public static final void w(FragmentEnergyMeter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getContext(), ActivityEnergyMeterHistory.class));
    }

    public static final void x(FragmentEnergyMeter this$0, Integer num) {
        TextView textView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView2 = (TextView) this$0._$_findCachedViewById(R.id.tv_energy_meter);
        if (textView2 != null) {
            textView2.setText(String.valueOf(num));
        }
        SpeedView speedView = (SpeedView) this$0._$_findCachedViewById(R.id.speedView_energy_meter);
        if (speedView != null) {
            speedView.speedTo(num.intValue(), 0L);
        }
        Calendar calendar = this$0.n;
        if (RepositoryUtils.formatDate(calendar != null ? calendar.getTime() : null, "yyyy-MM-dd").equals(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
            int i = R.id.energy_level_ifno;
            TextView textView3 = (TextView) this$0._$_findCachedViewById(i);
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            if (num == null || (textView = (TextView) this$0._$_findCachedViewById(i)) == null) {
                return;
            }
            int intValue = num.intValue();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(Utils.getEnergyInfo(intValue, requireContext));
            return;
        }
        TextView textView4 = (TextView) this$0._$_findCachedViewById(R.id.energy_level_ifno);
        if (textView4 == null) {
            return;
        }
        textView4.setVisibility(8);
    }

    public static final void y(FragmentEnergyMeter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.session_list_recycler;
        RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(i);
        if (recyclerView != null && recyclerView.getVisibility() == 0) {
            RecyclerView recyclerView2 = (RecyclerView) this$0._$_findCachedViewById(i);
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(8);
            }
            RelativeLayout relativeLayout = (RelativeLayout) this$0._$_findCachedViewById(R.id.steps_drain_lay);
            if (relativeLayout == null) {
                return;
            }
            relativeLayout.setVisibility(8);
            return;
        }
        Calendar calendar = this$0.n;
        DailyWalkData dailyWalkDataWithDate = WalkDBRead.getInstance(this$0.requireContext()).getDailyWalkDataWithDate(RepositoryUtils.formatDate(calendar != null ? calendar.getTime() : null, "yyyy-MM-dd"), BleApiManager.getInstance(this$0.requireContext()).getBleApi().getMacAddress());
        if (dailyWalkDataWithDate != null) {
            RelativeLayout relativeLayout2 = (RelativeLayout) this$0._$_findCachedViewById(R.id.steps_drain_lay);
            if (relativeLayout2 != null) {
                relativeLayout2.setVisibility(8);
            }
            TextView textView = (TextView) this$0._$_findCachedViewById(R.id.steps_score_val);
            if (textView != null) {
                textView.setText(String.valueOf(dailyWalkDataWithDate.getValue()));
            }
        } else {
            RelativeLayout relativeLayout3 = (RelativeLayout) this$0._$_findCachedViewById(R.id.steps_drain_lay);
            if (relativeLayout3 != null) {
                relativeLayout3.setVisibility(8);
            }
        }
        RecyclerView recyclerView3 = (RecyclerView) this$0._$_findCachedViewById(i);
        if (recyclerView3 == null) {
            return;
        }
        recyclerView3.setVisibility(0);
    }

    public static final void z(FragmentEnergyMeter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.sleep_score_gain_lay;
        if (((RelativeLayout) this$0._$_findCachedViewById(i)).getVisibility() == 0) {
            RelativeLayout relativeLayout = (RelativeLayout) this$0._$_findCachedViewById(i);
            if (relativeLayout == null) {
                return;
            }
            relativeLayout.setVisibility(8);
            return;
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) this$0._$_findCachedViewById(i);
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(0);
        }
        TextView textView = (TextView) this$0._$_findCachedViewById(R.id.sleep_score_val);
        if (textView != null) {
            SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Calendar calendar = this$0.n;
            Intrinsics.checkNotNull(calendar);
            String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(selectedDate!!.time, \"yyyy-MM-dd\")");
            textView.setText(String.valueOf(companion.getInstance(requireActivity).getSleepScore(formatDate, BleApiManager.getInstance(this$0.getContext()).getBleApi().getMacAddress())));
        }
    }

    public final void C() {
        Calendar calendar = Calendar.getInstance();
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.a
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                FragmentEnergyMeter.D(FragmentEnergyMeter.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void E(ArrayList<Entry> arrayList, final ArrayList<String> arrayList2) {
        int i = R.id.linechart_energy_meter;
        if (((LineChart) _$_findCachedViewById(i)) == null || !isAdded()) {
            return;
        }
        LineChart lineChart = (LineChart) _$_findCachedViewById(i);
        if (lineChart != null) {
            lineChart.clear();
        }
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry next = it.next();
            if (next.getY() == -1.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        Resources resources = getResources();
        int i2 = R.color.colorPrimary;
        lineDataSet.setCircleColor(resources.getColor(i2));
        lineDataSet.setCircleSize(0.5f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawFilled(true);
        Resources resources2 = getResources();
        int i3 = R.color.secondary_text_color;
        lineDataSet.setValueTextColor(resources2.getColor(i3));
        lineDataSet.setColor(getResources().getColor(i2));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        lineDataSet.setFillColor(getResources().getColor(R.color.color_33E51C23));
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setCircleColor(getResources().getColor(R.color.transparent));
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setValueTextColor(getResources().getColor(17170445));
        lineDataSet2.setColor(getResources().getColor(17170445));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        lineData.setDrawValues(false);
        lineData.setValueTextColor(getResources().getColor(i3));
        int i4 = R.id.linechart_energy_meter;
        ((LineChart) _$_findCachedViewById(i4)).getXAxis().setDrawGridLines(false);
        LineChart lineChart2 = (LineChart) _$_findCachedViewById(i4);
        YAxis axisLeft = lineChart2 != null ? lineChart2.getAxisLeft() : null;
        Intrinsics.checkNotNull(axisLeft);
        axisLeft.setCenterAxisLabels(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setEnabled(true);
        axisLeft.setAxisMaximum(100.0f);
        Resources resources3 = getResources();
        int i5 = R.color.color_757575;
        axisLeft.setAxisLineColor(resources3.getColor(i5));
        LineChart lineChart3 = (LineChart) _$_findCachedViewById(i4);
        YAxis axisRight = lineChart3 != null ? lineChart3.getAxisRight() : null;
        Intrinsics.checkNotNull(axisRight);
        axisRight.setEnabled(false);
        LineChart lineChart4 = (LineChart) _$_findCachedViewById(i4);
        XAxis xAxis = lineChart4 != null ? lineChart4.getXAxis() : null;
        Intrinsics.checkNotNull(xAxis);
        xAxis.setEnabled(true);
        xAxis.setGranularity(1.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGridColor(getResources().getColor(i5));
        xAxis.setDrawLabels(true);
        xAxis.setAxisLineColor(getResources().getColor(i5));
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeter$setLineGrapValues$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                int roundToInt;
                if (f >= 0.0f && (roundToInt = kotlin.math.c.roundToInt(f)) >= 0 && roundToInt < arrayList2.size() && roundToInt == ((int) f)) {
                    String str = arrayList2.get(roundToInt);
                    Intrinsics.checkNotNullExpressionValue(str, "labels[index]");
                    return str;
                }
                return "";
            }
        });
        LineChart lineChart5 = (LineChart) _$_findCachedViewById(i4);
        Legend legend = lineChart5 != null ? lineChart5.getLegend() : null;
        Intrinsics.checkNotNull(legend);
        legend.setEnabled(false);
        ((LineChart) _$_findCachedViewById(i4)).setData(lineData);
        ((LineChart) _$_findCachedViewById(i4)).setDrawGridBackground(false);
        ((LineChart) _$_findCachedViewById(i4)).getDescription().setEnabled(false);
        ((LineChart) _$_findCachedViewById(i4)).setDrawBorders(false);
        ((LineChart) _$_findCachedViewById(i4)).setAutoScaleMinMaxEnabled(true);
        ((LineChart) _$_findCachedViewById(i4)).setPinchZoom(false);
        ((LineChart) _$_findCachedViewById(i4)).setVisibleXRangeMinimum(5.0f);
        LineChart lineChart6 = (LineChart) _$_findCachedViewById(i4);
        YAxis axisLeft2 = lineChart6 != null ? lineChart6.getAxisLeft() : null;
        Intrinsics.checkNotNull(axisLeft2);
        axisLeft2.setStartAtZero(true);
        LineChart lineChart7 = (LineChart) _$_findCachedViewById(i4);
        YAxis axisRight2 = lineChart7 != null ? lineChart7.getAxisRight() : null;
        Intrinsics.checkNotNull(axisRight2);
        axisRight2.setStartAtZero(true);
        LineChart lineChart8 = (LineChart) _$_findCachedViewById(i4);
        YAxis axisLeft3 = lineChart8 != null ? lineChart8.getAxisLeft() : null;
        Intrinsics.checkNotNull(axisLeft3);
        axisLeft3.setTextColor(getResources().getColor(i3));
        LineChart lineChart9 = (LineChart) _$_findCachedViewById(i4);
        XAxis xAxis2 = lineChart9 != null ? lineChart9.getXAxis() : null;
        Intrinsics.checkNotNull(xAxis2);
        xAxis2.setTextColor(getResources().getColor(i3));
        ((LineChart) _$_findCachedViewById(i4)).setDoubleTapToZoomEnabled(false);
        ((LineChart) _$_findCachedViewById(i4)).invalidate();
    }

    public final void F() {
        int i = R.id.pieChart;
        if (((PieChart) _$_findCachedViewById(i)) != null) {
            ((PieChart) _$_findCachedViewById(i)).clear();
            PieDataSet pieDataSet = new PieDataSet(this.x, "");
            int[] iArr = this.t;
            pieDataSet.setColors(Arrays.copyOf(iArr, iArr.length));
            pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieDataSet.setValueLinePart1OffsetPercentage(10.0f);
            pieDataSet.setValueLinePart1Length(0.3f);
            pieDataSet.setValueLinePart2Length(0.1f);
            Resources resources = getResources();
            int i2 = R.color.main_text_color;
            pieDataSet.setValueLineColor(resources.getColor(i2));
            pieDataSet.setValueTextColor(getResources().getColor(i2));
            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(true);
            pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##")));
            pieData.setValueTextSize(10.0f);
            ((PieChart) _$_findCachedViewById(i)).setData(pieData);
            ((PieChart) _$_findCachedViewById(i)).setRotationEnabled(false);
            ((PieChart) _$_findCachedViewById(i)).setHighlightPerTapEnabled(false);
            ((PieChart) _$_findCachedViewById(i)).setTransparentCircleColor(0);
            ((PieChart) _$_findCachedViewById(i)).setHoleRadius(60.0f);
            ((PieChart) _$_findCachedViewById(i)).setHoleColor(0);
            ((PieChart) _$_findCachedViewById(i)).setTransparentCircleRadius(0.0f);
            ((PieChart) _$_findCachedViewById(i)).getLegend().setWordWrapEnabled(true);
            ((PieChart) _$_findCachedViewById(i)).setDrawEntryLabels(false);
            ((PieChart) _$_findCachedViewById(i)).setDrawHoleEnabled(true);
            ((PieChart) _$_findCachedViewById(i)).setUsePercentValues(false);
            ((PieChart) _$_findCachedViewById(i)).setEntryLabelTextSize(5.0f);
            ((PieChart) _$_findCachedViewById(i)).setEntryLabelColor(getResources().getColor(i2));
            ((PieChart) _$_findCachedViewById(i)).setCenterTextSize(24.0f);
            ((PieChart) _$_findCachedViewById(i)).getDescription().setEnabled(false);
            setCustomLegend();
            ((PieChart) _$_findCachedViewById(i)).invalidate();
        }
    }

    public final void G(Calendar calendar, Calendar calendar2) {
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = this.q;
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel2 = null;
        if (fragmentEnergyMeterViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel = null;
        }
        Calendar calendar3 = this.n;
        Intrinsics.checkNotNull(calendar3);
        fragmentEnergyMeterViewModel.loadEnergyData(calendar3);
        if (AppUtils.isNetConnected(requireContext())) {
            SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getResources().getString(R.string.energy_meter);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.energy_meter)");
            sleepScoreApiCall.getSleepScoreBatchApiCall(calendar, calendar2, requireContext, string);
            Calendar calendar4 = this.n;
            if (Intrinsics.areEqual(RepositoryUtils.formatDate(calendar4 != null ? calendar4.getTime() : null, "yyyy-MM-dd"), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
                ImageView imageView = (ImageView) _$_findCachedViewById(R.id.share_image);
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
                FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel3 = this.q;
                if (fragmentEnergyMeterViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentEnergyMeterViewModel2 = fragmentEnergyMeterViewModel3;
                }
                fragmentEnergyMeterViewModel2.getFeedbackQuestionnarieList();
                return;
            }
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.view_pager_layout);
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.share_image);
            if (imageView2 == null) {
                return;
            }
            imageView2.setVisibility(4);
            return;
        }
        Toast.makeText(requireContext(), getResources().getString(R.string.please_check_network), 0).show();
    }

    public final void H(Calendar calendar) {
        if (isAdded()) {
            int i = R.id.tv_date1;
            TextView textView = (TextView) _$_findCachedViewById(i);
            if (textView != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView.setText(format);
            }
            TextView textView2 = (TextView) _$_findCachedViewById(i);
            if (textView2 != null) {
                textView2.setTag(calendar.clone());
            }
            calendar.add(5, -this.r);
            int i2 = R.id.tv_date2;
            TextView textView3 = (TextView) _$_findCachedViewById(i2);
            if (textView3 != null) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format(Locale.ENGLISH, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                textView3.setText(format2);
            }
            TextView textView4 = (TextView) _$_findCachedViewById(i2);
            if (textView4 != null) {
                textView4.setTag(calendar.clone());
            }
            calendar.add(5, -this.r);
            int i3 = R.id.tv_date3;
            TextView textView5 = (TextView) _$_findCachedViewById(i3);
            if (textView5 != null) {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String format3 = String.format(Locale.ENGLISH, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                textView5.setText(format3);
            }
            TextView textView6 = (TextView) _$_findCachedViewById(i3);
            if (textView6 != null) {
                textView6.setTag(calendar.clone());
            }
            calendar.add(5, -this.r);
            int i4 = R.id.tv_date4;
            TextView textView7 = (TextView) _$_findCachedViewById(i4);
            if (textView7 != null) {
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String format4 = String.format(Locale.ENGLISH, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                textView7.setText(format4);
            }
            TextView textView8 = (TextView) _$_findCachedViewById(i4);
            if (textView8 != null) {
                textView8.setTag(calendar.clone());
            }
            calendar.add(5, -this.r);
            int i5 = R.id.tv_date5;
            TextView textView9 = (TextView) _$_findCachedViewById(i5);
            if (textView9 != null) {
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String format5 = String.format(Locale.ENGLISH, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
                Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                textView9.setText(format5);
            }
            TextView textView10 = (TextView) _$_findCachedViewById(i5);
            if (textView10 == null) {
                return;
            }
            textView10.setTag(calendar.clone());
        }
    }

    public final void I(TextView textView) {
        int[] iArr = this.w;
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        this.t = copyOf;
        int[] iArr2 = this.w;
        int[] copyOf2 = Arrays.copyOf(iArr2, iArr2.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
        this.u = copyOf2;
        int[] iArr3 = this.w;
        int[] copyOf3 = Arrays.copyOf(iArr3, iArr3.length);
        Intrinsics.checkNotNullExpressionValue(copyOf3, "copyOf(this, size)");
        this.v = copyOf3;
        if (isAdded()) {
            int i = R.id.tv_date1;
            TextView textView2 = (TextView) _$_findCachedViewById(i);
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            int i2 = R.id.tv_date2;
            TextView textView3 = (TextView) _$_findCachedViewById(i2);
            if (textView3 != null) {
                textView3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            int i3 = R.id.tv_date3;
            TextView textView4 = (TextView) _$_findCachedViewById(i3);
            if (textView4 != null) {
                textView4.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            int i4 = R.id.tv_date4;
            TextView textView5 = (TextView) _$_findCachedViewById(i4);
            if (textView5 != null) {
                textView5.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            int i5 = R.id.tv_date5;
            TextView textView6 = (TextView) _$_findCachedViewById(i5);
            if (textView6 != null) {
                textView6.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            TextView textView7 = (TextView) _$_findCachedViewById(i);
            if (textView7 != null) {
                textView7.setBackgroundResource(R.drawable.un_selected_date);
            }
            TextView textView8 = (TextView) _$_findCachedViewById(i2);
            if (textView8 != null) {
                textView8.setBackgroundResource(R.drawable.un_selected_date);
            }
            TextView textView9 = (TextView) _$_findCachedViewById(i3);
            if (textView9 != null) {
                textView9.setBackgroundResource(R.drawable.un_selected_date);
            }
            TextView textView10 = (TextView) _$_findCachedViewById(i4);
            if (textView10 != null) {
                textView10.setBackgroundResource(R.drawable.un_selected_date);
            }
            TextView textView11 = (TextView) _$_findCachedViewById(i5);
            if (textView11 != null) {
                textView11.setBackgroundResource(R.drawable.un_selected_date);
            }
            if (textView != null) {
                textView.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.circle_with_primary_color_solid);
            }
            if ((textView != null ? textView.getTag() : null) == null) {
                this.n = Calendar.getInstance();
            } else {
                Object tag = textView != null ? textView.getTag() : null;
                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.Calendar");
                this.n = (Calendar) tag;
            }
            TextView textView12 = (TextView) _$_findCachedViewById(i5);
            Object tag2 = textView12 != null ? textView12.getTag() : null;
            Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) tag2;
            TextView textView13 = (TextView) _$_findCachedViewById(i);
            Object tag3 = textView13 != null ? textView13.getTag() : null;
            Intrinsics.checkNotNull(tag3, "null cannot be cast to non-null type java.util.Calendar");
            G(calendar, (Calendar) tag3);
        }
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

    @NotNull
    public final int[] getColor() {
        return this.t;
    }

    @NotNull
    public final int[] getColor1() {
        return this.u;
    }

    @NotNull
    public final int[] getColor2() {
        return this.v;
    }

    @NotNull
    public final int[] getColorDefault() {
        return this.w;
    }

    @NotNull
    public final DatePickerDialog getDatePickerDialog() {
        DatePickerDialog datePickerDialog = this.datePickerDialog;
        if (datePickerDialog != null) {
            return datePickerDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("datePickerDialog");
        return null;
    }

    @NotNull
    public final View[] getDateSelectionView() {
        View[] viewArr = this.dateSelectionView;
        if (viewArr != null) {
            return viewArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dateSelectionView");
        return null;
    }

    @NotNull
    public final ArrayList<PieEntry> getEntries() {
        return this.x;
    }

    public final int getPosition1() {
        return this.r;
    }

    public final boolean getResponsechange() {
        return this.s;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        boolean z = true;
        if (view != null && view.getId() == R.id.calendar_image) {
            C();
            getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            getDatePickerDialog().show();
            return;
        }
        if (view != null && view.getId() == R.id.share_image) {
            share();
            return;
        }
        if (view == null || view.getId() != R.id.tv_date5) {
            z = false;
        }
        this.s = z;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
        I((TextView) view);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_energy_meter, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onEnergyScoreCalculated(@NotNull EnergyScoreEventData energyScoreEventData) {
        Intrinsics.checkNotNullParameter(energyScoreEventData, "energyScoreEventData");
        Calendar calendar = this.n;
        if (calendar != null) {
            FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = this.q;
            if (fragmentEnergyMeterViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentEnergyMeterViewModel = null;
            }
            fragmentEnergyMeterViewModel.loadEnergyData(calendar);
        }
    }

    @Subscribe
    public final void onEnergyScoreFeedbackUiUpdate(@NotNull EnergyScoreFeedbackEventData energyScoreFeedbackEventData) {
        Intrinsics.checkNotNullParameter(energyScoreFeedbackEventData, "energyScoreFeedbackEventData");
        CoveEventBusManager.getInstance().getEventBus().post(new DismissLoader());
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = this.q;
        if (fragmentEnergyMeterViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel = null;
        }
        fragmentEnergyMeterViewModel.getFeedbackQuestionnarieList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList
    public void onReceiveQuestionList(@Nullable ArrayList<FeedbackQuetionnarieModel> arrayList) {
        if (isAdded() && isVisible()) {
            if (arrayList != null && arrayList.size() > 0) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.view_pager_layout);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
                u(arrayList);
                return;
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.view_pager_layout);
            if (linearLayout2 == null) {
                return;
            }
            linearLayout2.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        ArrayList<EnergyData> arrayList;
        EnergyData energyData;
        Integer sleepScore;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentEnergyMeterViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel = (FragmentEnergyMeterViewModel) viewModel;
        this.q = fragmentEnergyMeterViewModel;
        if (fragmentEnergyMeterViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel = null;
        }
        fragmentEnergyMeterViewModel.setContractEnergyMeterDashBoard$sleepenergyscore_prodRelease(this);
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel2 = this.q;
        if (fragmentEnergyMeterViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel2 = null;
        }
        fragmentEnergyMeterViewModel2.setQuestionsList$sleepenergyscore_prodRelease(this);
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel3 = this.q;
        if (fragmentEnergyMeterViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel3 = null;
        }
        fragmentEnergyMeterViewModel3.setMLifecycleOwner(this);
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        TextView tv_date1 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        TextView tv_date2 = (TextView) _$_findCachedViewById(R.id.tv_date2);
        Intrinsics.checkNotNullExpressionValue(tv_date2, "tv_date2");
        TextView tv_date3 = (TextView) _$_findCachedViewById(R.id.tv_date3);
        Intrinsics.checkNotNullExpressionValue(tv_date3, "tv_date3");
        TextView tv_date4 = (TextView) _$_findCachedViewById(R.id.tv_date4);
        Intrinsics.checkNotNullExpressionValue(tv_date4, "tv_date4");
        TextView tv_date5 = (TextView) _$_findCachedViewById(R.id.tv_date5);
        Intrinsics.checkNotNullExpressionValue(tv_date5, "tv_date5");
        setDateSelectionView(new View[]{tv_date1, tv_date2, tv_date3, tv_date4, tv_date5});
        int length = getDateSelectionView().length;
        for (int i = 0; i < length; i++) {
            getDateSelectionView()[i].setOnClickListener(this);
        }
        setDefaultDateUi();
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.calendar_image);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_date1);
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_date2);
        if (textView2 != null) {
            textView2.setOnClickListener(this);
        }
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tv_date3);
        if (textView3 != null) {
            textView3.setOnClickListener(this);
        }
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.tv_date4);
        if (textView4 != null) {
            textView4.setOnClickListener(this);
        }
        TextView textView5 = (TextView) _$_findCachedViewById(R.id.tv_date5);
        if (textView5 != null) {
            textView5.setOnClickListener(this);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.share_image);
        if (imageView2 != null) {
            imageView2.setOnClickListener(this);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.history_image);
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentEnergyMeter.w(FragmentEnergyMeter.this, view2);
                }
            });
        }
        EnergyScoreRepository.Companion companion = EnergyScoreRepository.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNull(calendar);
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …-MM-dd\"\n                )");
        EnergyScoreDbData energyScoreData = companion.getInstance(requireContext2).getEnergyScoreData(formatDate, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        if (energyScoreData != null && (arrayList = energyScoreData.data) != null && (energyData = arrayList.get(0)) != null) {
            FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel4 = this.q;
            if (fragmentEnergyMeterViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentEnergyMeterViewModel4 = null;
            }
            Calendar calendar2 = this.n;
            Date time = calendar2 != null ? calendar2.getTime() : null;
            FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel5 = this.q;
            if (fragmentEnergyMeterViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentEnergyMeterViewModel5 = null;
            }
            String formatDate2 = RepositoryUtils.formatDate(time, fragmentEnergyMeterViewModel5.getDateFormat());
            Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(selectedDate?…me, viewModel.dateFormat)");
            DailyWalkData walkDataForSelectedDate = fragmentEnergyMeterViewModel4.getWalkDataForSelectedDate(formatDate2);
            SleepScoreRepository.Companion companion2 = SleepScoreRepository.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            SleepScoreRepository singletonHolder = companion2.getInstance(requireContext3);
            Calendar calendar3 = this.n;
            Date time2 = calendar3 != null ? calendar3.getTime() : null;
            FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel6 = this.q;
            if (fragmentEnergyMeterViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentEnergyMeterViewModel6 = null;
            }
            String formatDate3 = RepositoryUtils.formatDate(time2, fragmentEnergyMeterViewModel6.getDateFormat());
            Intrinsics.checkNotNullExpressionValue(formatDate3, "formatDate(selectedDate?…me, viewModel.dateFormat)");
            SleepScoreData sleepScoreData = singletonHolder.getSleepScoreData(formatDate3, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
            if ((walkDataForSelectedDate != null && walkDataForSelectedDate.getValue() > 0) || (sleepScoreData != null && ((sleepScore = sleepScoreData.getSleepScore()) == null || sleepScore.intValue() != -1))) {
                FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel7 = this.q;
                if (fragmentEnergyMeterViewModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentEnergyMeterViewModel7 = null;
                }
                fragmentEnergyMeterViewModel7.getEnergyScoreLiveData().postValue(energyData.getCurrentEnergyLevel());
            } else {
                FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel8 = this.q;
                if (fragmentEnergyMeterViewModel8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentEnergyMeterViewModel8 = null;
                }
                fragmentEnergyMeterViewModel8.getEnergyScoreLiveData().postValue(0);
            }
        }
        FragmentEnergyMeterViewModel fragmentEnergyMeterViewModel9 = this.q;
        if (fragmentEnergyMeterViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModel9 = null;
        }
        MutableLiveData<Integer> energyScoreLiveData = fragmentEnergyMeterViewModel9.getEnergyScoreLiveData();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        energyScoreLiveData.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentEnergyMeter.x(FragmentEnergyMeter.this, (Integer) obj);
            }
        });
        int i2 = R.id.speedView_energy_meter;
        SpeedView speedView = (SpeedView) _$_findCachedViewById(i2);
        if (speedView != null) {
            speedView.makeSections(5, -16711681, Style.BUTT);
        }
        SpeedView speedView2 = (SpeedView) _$_findCachedViewById(i2);
        List<Section> sections = speedView2 != null ? speedView2.getSections() : null;
        Intrinsics.checkNotNull(sections);
        sections.get(0).setColor(getResources().getColor(R.color.color_fcc695));
        SpeedView speedView3 = (SpeedView) _$_findCachedViewById(i2);
        List<Section> sections2 = speedView3 != null ? speedView3.getSections() : null;
        Intrinsics.checkNotNull(sections2);
        sections2.get(1).setColor(getResources().getColor(R.color.color_fda659));
        SpeedView speedView4 = (SpeedView) _$_findCachedViewById(i2);
        List<Section> sections3 = speedView4 != null ? speedView4.getSections() : null;
        Intrinsics.checkNotNull(sections3);
        sections3.get(2).setColor(getResources().getColor(R.color.color_fc861d));
        SpeedView speedView5 = (SpeedView) _$_findCachedViewById(i2);
        List<Section> sections4 = speedView5 != null ? speedView5.getSections() : null;
        Intrinsics.checkNotNull(sections4);
        sections4.get(3).setColor(getResources().getColor(R.color.color_f47300));
        SpeedView speedView6 = (SpeedView) _$_findCachedViewById(i2);
        List<Section> sections5 = speedView6 != null ? speedView6.getSections() : null;
        Intrinsics.checkNotNull(sections5);
        sections5.get(4).setColor(getResources().getColor(R.color.color_fa6400));
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        Drawable drawable = getResources().getDrawable(R.drawable.indicator);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.indicator)");
        ImageIndicator imageIndicator = new ImageIndicator(requireContext4, drawable);
        SpeedView speedView7 = (SpeedView) _$_findCachedViewById(i2);
        if (speedView7 != null) {
            speedView7.setIndicator(imageIndicator);
        }
        s();
        ((RelativeLayout) _$_findCachedViewById(R.id.energy_drain_rl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentEnergyMeter.y(FragmentEnergyMeter.this, view2);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.energy_gain_rl);
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentEnergyMeter.z(FragmentEnergyMeter.this, view2);
                }
            });
        }
        TextView textView6 = (TextView) _$_findCachedViewById(R.id.drain_txt);
        if (textView6 != null) {
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentEnergyMeter.A(FragmentEnergyMeter.this, view2);
                }
            });
        }
        TextView textView7 = (TextView) _$_findCachedViewById(R.id.gain_txt);
        if (textView7 != null) {
            textView7.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentEnergyMeter.B(FragmentEnergyMeter.this, view2);
                }
            });
        }
    }

    public final void piechartColor() {
        int i = R.id.pieChart;
        if (((PieChart) _$_findCachedViewById(i)) != null) {
            Legend legend = ((PieChart) _$_findCachedViewById(i)).getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            legend.setEnabled(true);
            legend.setForm(Legend.LegendForm.NONE);
            legend.setTextColor(0);
            legend.setTextSize(0.0f);
            legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
            String[] labels = legend.getLabels();
            Intrinsics.checkNotNullExpressionValue(labels, "legend.labels");
            if (ArraysKt___ArraysKt.contains(labels, getResources().getString(R.string.sleep_score_gain))) {
                String[] labels2 = legend.getLabels();
                Intrinsics.checkNotNull(labels2);
                int length = labels2.length - 1;
                for (int i2 = 0; i2 < length; i2++) {
                    System.out.println((Object) ("labels index*** " + i2));
                    if (kotlin.text.m.equals(legend.getLabels()[i2], getResources().getString(R.string.sleep_score_gain), true)) {
                        String format = String.format("#%06X", Integer.valueOf(this.w[i2] & 16777215));
                        Intrinsics.checkNotNullExpressionValue(format, "format(\"#%06X\", 0xFFFFFF and color)");
                        String replace$default = kotlin.text.m.replace$default(format, MqttTopic.MULTI_LEVEL_WILDCARD, "", false, 4, (Object) null);
                        this.v[i2] = Color.parseColor("#99" + replace$default);
                    } else {
                        String format2 = String.format("#%06X", Integer.valueOf(this.w[i2] & 16777215));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(\"#%06X\", 0xFFFFFF and color)");
                        String replace$default2 = kotlin.text.m.replace$default(format2, MqttTopic.MULTI_LEVEL_WILDCARD, "", false, 4, (Object) null);
                        this.u[i2] = Color.parseColor("#99" + replace$default2);
                    }
                }
                return;
            }
            int[] iArr = this.w;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            this.t = copyOf;
        }
    }

    public final void s() {
        ViewPagerContainer viewPagerContainer = (ViewPagerContainer) _$_findCachedViewById(R.id.energy_info_pager);
        Intrinsics.checkNotNull(viewPagerContainer, "null cannot be cast to non-null type com.coveiot.android.theme.utils.ViewPagerContainer");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        viewPagerContainer.setAdapter(new SleepEnergyScoreInfoAdapter(requireActivity, childFragmentManager, this.p, this.o));
    }

    public final void setColor(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.t = iArr;
    }

    public final void setColor1(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.u = iArr;
    }

    public final void setColor2(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.v = iArr;
    }

    public final void setColorDefault(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.w = iArr;
    }

    public final void setCustomLegend() {
        int i = R.id.pieChart;
        if (((PieChart) _$_findCachedViewById(i)) != null) {
            int i2 = R.id.gridView;
            if (((GridView) _$_findCachedViewById(i2)) != null) {
                Legend legend = ((PieChart) _$_findCachedViewById(i)).getLegend();
                FragmentActivity requireActivity = requireActivity();
                int[] colors = legend.getColors();
                String[] labels = legend.getLabels();
                Intrinsics.checkNotNullExpressionValue(labels, "legend.labels");
                ((GridView) _$_findCachedViewById(i2)).setAdapter((ListAdapter) new EnergyScoreLegendGridAdapter(requireActivity, colors, ArraysKt___ArraysKt.toMutableList(labels)));
            }
        }
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setDateSelectionView(@NotNull View[] viewArr) {
        Intrinsics.checkNotNullParameter(viewArr, "<set-?>");
        this.dateSelectionView = viewArr;
    }

    public final void setDefaultDateUi() {
        Calendar newDate = Calendar.getInstance();
        newDate.set(newDate.get(1), newDate.get(2), newDate.get(5));
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        H(newDate);
        TextView tv_date1 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        I(tv_date1);
    }

    public final void setResponsechange(boolean z) {
        this.s = z;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareEnergyMeterData shareEnergyMeterData = new ShareEnergyMeterData();
        shareEnergyMeterData.setStartEnergyScore(Integer.parseInt(((TextView) _$_findCachedViewById(R.id.tv_energy_meter)).getText().toString()));
        shareEnergyMeterData.setEndEnergyScore(0);
        shareEnergyMeterData.setGraphType(getResources().getString(R.string.share_daily));
        shareEnergyMeterData.setDwmValue(((TextView) _$_findCachedViewById(R.id.tv_date)).getText().toString());
        Intent intent = new Intent(getContext(), ActivityShare.class);
        intent.putExtra(Constants.SHARE_DATA.getValue(), shareEnergyMeterData);
        Context context = getContext();
        if (context != null) {
            context.startActivity(intent);
        }
    }

    public final void t(TreeMap<String, Integer> treeMap, TreeMap<String, Integer> treeMap2, TreeMap<String, Integer> treeMap3, int i) {
        if (treeMap != null && treeMap.size() > 0) {
            int i2 = R.id.session_list_recycler;
            ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(requireActivity()));
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            DrainSessionAdapter drainSessionAdapter = new DrainSessionAdapter(requireContext);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
            if (recyclerView != null) {
                recyclerView.setAdapter(drainSessionAdapter);
            }
            drainSessionAdapter.setData(treeMap);
        }
        v(treeMap2, treeMap3, i);
    }

    public final void u(ArrayList<FeedbackQuetionnarieModel> arrayList) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        PagerAdapterFeedback pagerAdapterFeedback = new PagerAdapterFeedback(requireContext, childFragmentManager, arrayList);
        int i = R.id.energy_feedback_pager;
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(i);
        if (viewPager != null) {
            viewPager.setAdapter(pagerAdapterFeedback);
        }
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) _$_findCachedViewById(R.id.circlePageIndicator_feedback);
        if (circlePageIndicator != null) {
            circlePageIndicator.setViewPager((ViewPager) _$_findCachedViewById(i));
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterDashBoard
    public void updateEnergyMeterData(@Nullable final ArrayList<Entry> arrayList, @Nullable final ArrayList<String> arrayList2, @Nullable final TreeMap<String, Integer> treeMap, @Nullable final TreeMap<String, Integer> treeMap2, @Nullable final TreeMap<String, Integer> treeMap3, final int i) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.h
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentEnergyMeter.J(FragmentEnergyMeter.this, arrayList, arrayList2, treeMap, treeMap2, treeMap3, i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x023f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v(java.util.TreeMap<java.lang.String, java.lang.Integer> r10, java.util.TreeMap<java.lang.String, java.lang.Integer> r11, int r12) {
        /*
            Method dump skipped, instructions count: 908
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeter.v(java.util.TreeMap, java.util.TreeMap, int):void");
    }
}
