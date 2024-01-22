package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes5.dex */
public class GraphsUtility {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5423a = "GraphsUtility";
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;
    public static int e = 3;

    /* loaded from: classes5.dex */
    public class a implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* loaded from: classes5.dex */
    public class b implements OnChartValueSelectedListener {
        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onNothingSelected() {
        }

        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onValueSelected(Entry entry, Highlight highlight) {
            String str = GraphsUtility.f5423a;
            Log.i(str, "Value: " + Math.abs(((BarEntry) entry).getYVals()[highlight.getStackIndex()]));
        }
    }

    public static void b(Chart chart) {
        chart.animateY(1000);
    }

    public static int getTimeIndex(SleepDataModelForLastNight sleepDataModelForLastNight, int i) {
        int parseInt;
        int parseInt2;
        String[] split = sleepDataModelForLastNight.getStartTime().split(":");
        if (split.length > 1) {
            if (Integer.parseInt(split[0]) < 12) {
                parseInt = (Integer.parseInt(split[0]) + 12) * 60;
                parseInt2 = Integer.parseInt(split[1]);
            } else {
                parseInt = (Integer.parseInt(split[0]) - 12) * 60;
                parseInt2 = Integer.parseInt(split[1]);
            }
            return parseInt + parseInt2 + i;
        }
        return -1;
    }

    public static SleepDataModel prepareLastNightGraph(List<SleepDataModelForLastNight> list, HorizontalBarChart horizontalBarChart, Context context, boolean z, boolean z2) {
        byte b2;
        int sleepStartHour;
        int sleepEndHour;
        byte b3;
        horizontalBarChart.setNoDataText(context.getString(R.string.no_data_found));
        horizontalBarChart.setNoDataTextColor(R.color.text_color_dark);
        horizontalBarChart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
        if (DeviceUtils.isSmaDevice(context)) {
            c = 2;
            d = 1;
            e = 3;
        } else if (!DeviceUtils.isIDODevice(context) && !DeviceUtils.isTouchELXDevice(context)) {
            if (DeviceUtils.isEastApexDevice(context)) {
                b = 2;
                c = 4;
                d = 5;
                e = 3;
            }
        } else {
            b = 1;
            c = 2;
            d = 3;
            e = 4;
        }
        horizontalBarChart.clear();
        SleepDataModel sleepDataModel = null;
        if (!list.isEmpty()) {
            sleepDataModel = SleepDataHelper.getSleepDataModel(context, list);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int color = ContextCompat.getColor(context, R.color.awake_color);
            int color2 = ContextCompat.getColor(context, R.color.deepsleep_color);
            int color3 = ContextCompat.getColor(context, R.color.lightsleep_color);
            int color4 = ContextCompat.getColor(context, R.color.remsleep_color);
            horizontalBarChart.setNoDataText(context.getString(R.string.no_data_found));
            horizontalBarChart.setNoDataTextColor(R.color.text_color_dark);
            horizontalBarChart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
            if (sleepDataModel == null) {
                b2 = -1;
            } else if (sleepDataModel.getCountTotalSleep() <= 0) {
                horizontalBarChart.setNoDataText(context.getString(R.string.no_data_found));
                horizontalBarChart.setNoDataTextColor(R.color.white);
                return sleepDataModel;
            } else {
                if (sleepDataModel.getSleepStartHour() > 12) {
                    sleepStartHour = Math.abs(12 - sleepDataModel.getSleepStartHour());
                } else {
                    sleepStartHour = sleepDataModel.getSleepStartHour() < 12 ? sleepDataModel.getSleepStartHour() + 12 : 0;
                }
                if (sleepDataModel.getSleepEndHour() > 12) {
                    sleepEndHour = Math.abs(12 - sleepDataModel.getSleepEndHour());
                } else {
                    sleepEndHour = sleepDataModel.getSleepEndHour() < 12 ? 12 + sleepDataModel.getSleepEndHour() : 0;
                }
                int sleepEndMinute = (sleepEndHour * 60) + sleepDataModel.getSleepEndMinute();
                b2 = -1;
                for (int sleepStartMinute = (sleepStartHour * 60) + sleepDataModel.getSleepStartMinute(); sleepStartMinute < sleepEndMinute; sleepStartMinute++) {
                    if (b2 == -1) {
                        if (z) {
                            if (sleepDataModel.getFilteredSleepData()[sleepStartMinute] != -1) {
                                byte b4 = sleepDataModel.getFilteredSleepData()[sleepStartMinute];
                            }
                        } else if (sleepDataModel.getFilteredSleepData()[sleepStartMinute] != -1) {
                            byte b5 = sleepDataModel.getFilteredSleepData()[sleepStartMinute];
                        }
                    }
                    if (z) {
                        b3 = sleepDataModel.getFilteredSleepData()[sleepStartMinute] != -1 ? sleepDataModel.getFilteredSleepData()[sleepStartMinute] : (byte) -1;
                    } else {
                        b3 = sleepDataModel.getFilteredSleepData()[sleepStartMinute] != -1 ? sleepDataModel.getFilteredSleepData()[sleepStartMinute] : (byte) 0;
                    }
                    arrayList.add(1);
                    if (z) {
                        if (z2) {
                            if (b3 >= JStyleSleepData.JSTYLE_VALUE_AWAKE_START_WITH_REM && b3 <= JStyleSleepData.JSTYLE_VALUE_AWAKE_END_WITH_REM) {
                                arrayList2.add(Integer.valueOf(color));
                            } else if (b3 >= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM && b3 <= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM) {
                                arrayList2.add(Integer.valueOf(color3));
                            } else if (b3 >= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM && b3 <= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM) {
                                arrayList2.add(Integer.valueOf(color2));
                            } else if (b3 >= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM && b3 <= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM) {
                                arrayList2.add(Integer.valueOf(color4));
                            }
                        } else if (b3 == -1) {
                            arrayList2.add(Integer.valueOf(color));
                        } else if (b3 >= 15 && b3 <= 200) {
                            arrayList2.add(Integer.valueOf(color3));
                        } else if (b3 >= 0 && b3 <= 14) {
                            arrayList2.add(Integer.valueOf(color2));
                        }
                    } else if (b3 == b) {
                        arrayList2.add(Integer.valueOf(color));
                    } else if (b3 == e) {
                        arrayList2.add(Integer.valueOf(color4));
                    } else if (b3 == c) {
                        arrayList2.add(Integer.valueOf(color3));
                    } else if (b3 == d) {
                        arrayList2.add(Integer.valueOf(color2));
                    }
                    b2 = sleepDataModel.getFilteredSleepData()[sleepStartMinute] != -1 ? sleepDataModel.getFilteredSleepData()[sleepStartMinute] : (byte) 0;
                }
            }
            arrayList.add(0);
            if (z) {
                if (z2) {
                    if (b2 >= JStyleSleepData.JSTYLE_VALUE_AWAKE_START_WITH_REM && b2 <= JStyleSleepData.JSTYLE_VALUE_AWAKE_END_WITH_REM) {
                        arrayList2.add(Integer.valueOf(color));
                    } else if (b2 >= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM && b2 <= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM) {
                        arrayList2.add(Integer.valueOf(color3));
                    } else if (b2 >= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM && b2 <= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM) {
                        arrayList2.add(Integer.valueOf(color2));
                    } else if (b2 >= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM && b2 <= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM) {
                        arrayList2.add(Integer.valueOf(color4));
                    }
                } else if (b2 == -1) {
                    arrayList2.add(Integer.valueOf(color));
                } else if (b2 >= 15 && b2 <= 200) {
                    arrayList2.add(Integer.valueOf(color3));
                } else if (b2 >= 0 && b2 <= 14) {
                    arrayList2.add(Integer.valueOf(color2));
                }
            } else if (b2 == b) {
                arrayList2.add(Integer.valueOf(color));
            } else if (b2 == e) {
                arrayList2.add(Integer.valueOf(color4));
            } else if (b2 == c) {
                arrayList2.add(Integer.valueOf(color3));
            } else if (b2 == d) {
                arrayList2.add(Integer.valueOf(color2));
            }
            horizontalBarChart.setDrawGridBackground(false);
            horizontalBarChart.getDescription().setEnabled(false);
            horizontalBarChart.setPinchZoom(false);
            horizontalBarChart.setDrawBarShadow(false);
            horizontalBarChart.setDrawValueAboveBar(false);
            horizontalBarChart.setHighlightFullBarEnabled(false);
            horizontalBarChart.getAxisLeft().setEnabled(false);
            horizontalBarChart.getAxisRight().setEnabled(false);
            horizontalBarChart.getAxisRight().setDrawGridLines(false);
            XAxis xAxis = horizontalBarChart.getXAxis();
            xAxis.setEnabled(false);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawAxisLine(false);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new BarEntry(1.0f, Floats.toArray(arrayList)));
            BarDataSet barDataSet = new BarDataSet(arrayList3, "");
            barDataSet.setDrawValues(false);
            if (!arrayList2.isEmpty()) {
                barDataSet.setColors(Ints.toArray(arrayList2));
            }
            horizontalBarChart.setData(new BarData(barDataSet));
            horizontalBarChart.getDescription().setEnabled(false);
            horizontalBarChart.getLegend().setEnabled(false);
            horizontalBarChart.setPinchZoom(false);
            horizontalBarChart.setDoubleTapToZoomEnabled(false);
            horizontalBarChart.setScaleEnabled(false);
            horizontalBarChart.setVisibleXRangeMaximum(1.0f);
            horizontalBarChart.getXAxis().setGranularityEnabled(false);
            horizontalBarChart.setOnTouchListener(new a());
            b(horizontalBarChart);
            horizontalBarChart.setOnChartValueSelectedListener(new b());
        }
        return sleepDataModel;
    }
}
