package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractAmbientSoundDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.repository.ambientsound.AmbientSoundRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentAmbientSoundLevel extends Fragment implements View.OnClickListener, ContractAmbientSoundDashboard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public DatePickerDialog datePickerDialog;
    public View[] dateSelectionView;
    @Nullable
    public Calendar h;
    public FragmentAmbientSoundViewModel j;
    public SimpleDateFormat simpleDateFormat;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final SimpleDateFormat i = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
    public final int k = 1;
    public boolean l = true;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentAmbientSoundLevel newInstance() {
            return new FragmentAmbientSoundLevel();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel$getLatestAmbientSoundValueWithTimestamp$1", f = "FragmentAmbientSoundLevel.kt", i = {}, l = {418, 428, 436}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel$getLatestAmbientSoundValueWithTimestamp$1$1", f = "FragmentAmbientSoundLevel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel$a$a  reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public static final class C0275a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.IntRef $ambientSound;
            public int label;
            public final /* synthetic */ FragmentAmbientSoundLevel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0275a(FragmentAmbientSoundLevel fragmentAmbientSoundLevel, Ref.IntRef intRef, Continuation<? super C0275a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentAmbientSoundLevel;
                this.$ambientSound = intRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0275a(this.this$0, this.$ambientSound, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0275a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_ambientSound);
                    if (textView != null) {
                        textView.setText(this.$ambientSound.element + ' ' + this.this$0.getString(R.string.decible_unit));
                    }
                    TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tv_ambient_sound_status);
                    if (textView2 != null) {
                        int i = this.$ambientSound.element;
                        Context requireContext = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        textView2.setText(PayUtils.getAmbientSoundInfo(i, requireContext));
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel$getLatestAmbientSoundValueWithTimestamp$1$2", f = "FragmentAmbientSoundLevel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes3.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentAmbientSoundLevel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentAmbientSoundLevel fragmentAmbientSoundLevel, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentAmbientSoundLevel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_ambientSound);
                    if (textView != null) {
                        textView.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + this.this$0.getString(R.string.decible_unit));
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel$getLatestAmbientSoundValueWithTimestamp$1$3", f = "FragmentAmbientSoundLevel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes3.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentAmbientSoundLevel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(FragmentAmbientSoundLevel fragmentAmbientSoundLevel, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = fragmentAmbientSoundLevel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                TextView textView;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    FragmentAmbientSoundLevel fragmentAmbientSoundLevel = this.this$0;
                    int i = R.id.tv_ambientSound;
                    if (((TextView) fragmentAmbientSoundLevel._$_findCachedViewById(i)) != null && (textView = (TextView) this.this$0._$_findCachedViewById(i)) != null) {
                        textView.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + this.this$0.getString(R.string.decible_unit));
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AmbientSoundRepository.Companion companion = AmbientSoundRepository.Companion;
                    Context requireContext = FragmentAmbientSoundLevel.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    EntityHourlyAmbientSoundData latestRecordHourly = companion.getInstance(requireContext).getLatestRecordHourly(BleApiManager.getInstance(FragmentAmbientSoundLevel.this.getContext()).getBleApi().getMacAddress(), this.$date);
                    if (latestRecordHourly != null) {
                        int size = 60 / latestRecordHourly.getCodedValues().size();
                        Ref.IntRef intRef = new Ref.IntRef();
                        int size2 = latestRecordHourly.getCodedValues().size();
                        int i2 = 0;
                        for (int i3 = 0; i3 < size2; i3++) {
                            Integer num = latestRecordHourly.getCodedValues().get(i3);
                            Intrinsics.checkNotNullExpressionValue(num, "hourlyAmbientSoundData.codedValues[i]");
                            if (num.intValue() > 0) {
                                Integer num2 = latestRecordHourly.getCodedValues().get(i3);
                                Intrinsics.checkNotNullExpressionValue(num2, "hourlyAmbientSoundData.codedValues[i]");
                                intRef.element = num2.intValue();
                                i2 = i3;
                            }
                        }
                        Date parseDate = AppUtils.parseDate(latestRecordHourly.getDate() + ' ' + latestRecordHourly.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(parseDate);
                        calendar.add(12, size * i2);
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C0275a c0275a = new C0275a(FragmentAmbientSoundLevel.this, intRef, null);
                        this.label = 1;
                        if (BuildersKt.withContext(main, c0275a, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        MainCoroutineDispatcher main2 = Dispatchers.getMain();
                        b bVar = new b(FragmentAmbientSoundLevel.this, null);
                        this.label = 2;
                        if (BuildersKt.withContext(main2, bVar, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i == 1 || i == 2) {
                    ResultKt.throwOnFailure(obj);
                } else if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
                MainCoroutineDispatcher main3 = Dispatchers.getMain();
                c cVar = new c(FragmentAmbientSoundLevel.this, null);
                this.label = 3;
                if (BuildersKt.withContext(main3, cVar, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final void d(FragmentAmbientSoundLevel this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.f(newDate);
        TextView tv_date1 = (TextView) this$0._$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        this$0.g(tv_date1);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void b(String str) {
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(str, null), 2, null);
    }

    public final void c() {
        Calendar calendar = Calendar.getInstance();
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.a
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                FragmentAmbientSoundLevel.d(FragmentAmbientSoundLevel.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void e(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry next = it.next();
            if (next.getY() == 0.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(CollectionsKt___CollectionsKt.toSet(arrayList3));
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setCircleColor(getResources().getColor(R.color.ambient_sound_graph_color));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(getResources().getColor(17170445));
        lineDataSet.setColor(getResources().getColor(R.color.ambient_sound_graph_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.ambient_sound_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.ambient_sound_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setCircleColor(getResources().getColor(17170445));
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setValueTextColor(getResources().getColor(17170445));
        lineDataSet2.setColor(getResources().getColor(17170445));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i2 = 0; i2 < entryCount2; i2++) {
                iArr2[i2] = getResources().getColor(17170445);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(17170445);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
        }
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        int i3 = R.id.lineChartAmbient;
        ((LineChart) _$_findCachedViewById(i3)).getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        ((LineChart) _$_findCachedViewById(i3)).setData(lineData);
        ((LineChart) _$_findCachedViewById(i3)).setDrawGridBackground(false);
        ((LineChart) _$_findCachedViewById(i3)).getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(17170445));
        ((LineChart) _$_findCachedViewById(i3)).getPaint(7).setColor(getResources().getColor(17170445));
        ((LineChart) _$_findCachedViewById(i3)).setDrawBorders(false);
        ((LineChart) _$_findCachedViewById(i3)).setAutoScaleMinMaxEnabled(false);
        ((LineChart) _$_findCachedViewById(i3)).setPinchZoom(false);
        YAxis axisLeft = ((LineChart) _$_findCachedViewById(i3)).getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 100.0f;
        axisLeft.mAxisMinimum = 1.0f;
        axisLeft.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        ((LineChart) _$_findCachedViewById(i3)).getAxisRight().setEnabled(false);
        XAxis xAxis = ((LineChart) _$_findCachedViewById(i3)).getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        ((LineChart) _$_findCachedViewById(i3)).getAxisLeft().setStartAtZero(true);
        ((LineChart) _$_findCachedViewById(i3)).getAxisRight().setStartAtZero(true);
        ((LineChart) _$_findCachedViewById(i3)).getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i3)).getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i3)).getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i3)).getLegend().setEnabled(false);
        ((LineChart) _$_findCachedViewById(i3)).animateY(2000);
        ((LineChart) _$_findCachedViewById(i3)).setVisibleXRangeMinimum(5.0f);
        ((LineChart) _$_findCachedViewById(i3)).setPinchZoom(false);
        ((LineChart) _$_findCachedViewById(i3)).setAutoScaleMinMaxEnabled(false);
        ((LineChart) _$_findCachedViewById(i3)).setScaleEnabled(false);
        ((LineChart) _$_findCachedViewById(i3)).setDoubleTapToZoomEnabled(false);
        ((LineChart) _$_findCachedViewById(i3)).invalidate();
    }

    public final void f(Calendar calendar) {
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
            calendar.add(5, -this.k);
            int i2 = R.id.tv_date2;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i2)).setText(format2);
            ((TextView) _$_findCachedViewById(i2)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i3 = R.id.tv_date3;
            String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i3)).setText(format3);
            ((TextView) _$_findCachedViewById(i3)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i4 = R.id.tv_date4;
            String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i4)).setText(format4);
            ((TextView) _$_findCachedViewById(i4)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i5 = R.id.tv_date5;
            String format5 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i5)).setText(format5);
            ((TextView) _$_findCachedViewById(i5)).setTag(calendar.clone());
        }
    }

    public final void g(TextView textView) {
        Calendar calendar;
        if (isAdded()) {
            int i = R.id.tv_date1;
            TextView textView2 = (TextView) _$_findCachedViewById(i);
            if (textView2 != null) {
                textView2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            }
            int i2 = R.id.tv_date2;
            ((TextView) _$_findCachedViewById(i2)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i3 = R.id.tv_date3;
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i4 = R.id.tv_date4;
            ((TextView) _$_findCachedViewById(i4)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i5 = R.id.tv_date5;
            ((TextView) _$_findCachedViewById(i5)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            TextView textView3 = (TextView) _$_findCachedViewById(i);
            if (textView3 != null) {
                textView3.setBackgroundResource(R.drawable.date_unselected_health);
            }
            ((TextView) _$_findCachedViewById(i2)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i3)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i4)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i5)).setBackgroundResource(R.drawable.date_unselected_health);
            textView.setTextColor(getResources().getColor(R.color.secondary_text_color));
            textView.setBackgroundResource(R.drawable.circle_with_primary_color_solid);
            FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel = null;
            if (textView.getTag() == null) {
                FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel2 = this.j;
                if (fragmentAmbientSoundViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentAmbientSoundViewModel2 = null;
                }
                Calendar calendar2 = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                fragmentAmbientSoundViewModel2.loadHourlyAmbientSoundData(calendar2);
                FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel3 = this.j;
                if (fragmentAmbientSoundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentAmbientSoundViewModel = fragmentAmbientSoundViewModel3;
                }
                Calendar calendar3 = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
                fragmentAmbientSoundViewModel.loadDailyData(calendar3);
                calendar = Calendar.getInstance();
            } else {
                FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel4 = this.j;
                if (fragmentAmbientSoundViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentAmbientSoundViewModel4 = null;
                }
                Object tag = textView.getTag();
                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.Calendar");
                fragmentAmbientSoundViewModel4.loadHourlyAmbientSoundData((Calendar) tag);
                FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel5 = this.j;
                if (fragmentAmbientSoundViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentAmbientSoundViewModel = fragmentAmbientSoundViewModel5;
                }
                Object tag2 = textView.getTag();
                Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type java.util.Calendar");
                fragmentAmbientSoundViewModel.loadDailyData((Calendar) tag2);
                Object tag3 = textView.getTag();
                Intrinsics.checkNotNull(tag3, "null cannot be cast to non-null type java.util.Calendar");
                calendar = (Calendar) tag3;
            }
            this.h = calendar;
        }
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

    public final int getPosition1() {
        return this.k;
    }

    public final boolean getResponsechange() {
        return this.l;
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

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractAmbientSoundDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel = (FragmentAmbientSoundViewModel) ViewModelProviders.of(this, new com.coveiot.android.leonardo.utils.ViewModelFactory(requireContext)).get(FragmentAmbientSoundViewModel.class);
        this.j = fragmentAmbientSoundViewModel;
        FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel2 = null;
        if (fragmentAmbientSoundViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentAmbientSoundViewModel = null;
        }
        fragmentAmbientSoundViewModel.setContractAmbientSoundDashboard$app_prodRelease(this);
        FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel3 = this.j;
        if (fragmentAmbientSoundViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentAmbientSoundViewModel3 = null;
        }
        fragmentAmbientSoundViewModel3.setMLifecycleOwner(this);
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
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_ambientSound);
        if (textView != null) {
            textView.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + getString(R.string.decible_unit));
        }
        setDefaultDateUi();
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.calendar_image);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        if (textView2 != null) {
            textView2.setOnClickListener(this);
        }
        ((TextView) _$_findCachedViewById(R.id.tv_date2)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date3)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date4)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date5)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.share_image)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.ic_history_image)).setOnClickListener(this);
        FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel4 = this.j;
        if (fragmentAmbientSoundViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentAmbientSoundViewModel4 = null;
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        fragmentAmbientSoundViewModel4.loadHourlyAmbientSoundData(calendar);
        FragmentAmbientSoundViewModel fragmentAmbientSoundViewModel5 = this.j;
        if (fragmentAmbientSoundViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentAmbientSoundViewModel2 = fragmentAmbientSoundViewModel5;
        }
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        fragmentAmbientSoundViewModel2.loadDailyData(calendar2);
        ((TextView) _$_findCachedViewById(R.id.tv_ambient_sound_level_static)).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        if (view.getId() == R.id.calendar_image) {
            c();
            getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            getDatePickerDialog().show();
        } else if (view.getId() == R.id.ic_history_image) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.navigateToAmbientSoundLevelHistory(requireContext);
        } else if (view.getId() == R.id.share_image) {
            share();
        } else if (view.getId() == R.id.tv_ambient_sound_level_static) {
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion2.navigateToAmbientSoundInfoScreen(requireContext2);
        } else {
            this.l = view.getId() == R.id.tv_date5;
            g((TextView) view);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_ambient_sound_level, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
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
        f(newDate);
        TextView tv_date1 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        g(tv_date1);
    }

    public final void setResponsechange(boolean z) {
        this.l = z;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_ambientSound);
        shareData.setAvg_ambient_sound(String.valueOf(textView != null ? textView.getText() : null));
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(((TextView) _$_findCachedViewById(R.id.tv_date)).getText().toString());
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.ambient_sound_level_txt);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st….ambient_sound_level_txt)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractAmbientSoundDashboard
    public void updateDailyLevelData(@Nullable EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
        if (entityDailyAmbientSoundData != null && entityDailyAmbientSoundData.getMinAmbientSound() > 0 && entityDailyAmbientSoundData.getMaxAmbientSound() > 0) {
            String date = entityDailyAmbientSoundData.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "dailyAmbientSoundData!!.date");
            b(date);
            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_total_time);
            if (textView != null) {
                textView.setText(entityDailyAmbientSoundData.getTotalTime() + " min");
            }
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_date);
            if (textView2 != null) {
                FragmentActivity activity = getActivity();
                Context baseContext = activity != null ? activity.getBaseContext() : null;
                String str = AppConstants.DAY.toString();
                String date2 = entityDailyAmbientSoundData.getDate();
                Intrinsics.checkNotNullExpressionValue(date2, "dailyAmbientSoundData.date");
                textView2.setText(PayUtils.getTodayYesterdayDate$default(baseContext, str, date2, 0, 8, null));
            }
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tv_min_ambient_sound);
            if (textView3 != null) {
                textView3.setText(entityDailyAmbientSoundData.getMinAmbientSound() + ' ' + getString(R.string.decible_unit));
            }
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tv_max_ambient_sound);
            if (textView4 == null) {
                return;
            }
            textView4.setText(entityDailyAmbientSoundData.getMaxAmbientSound() + ' ' + getString(R.string.decible_unit));
            return;
        }
        TextView textView5 = (TextView) _$_findCachedViewById(R.id.tv_ambientSound);
        if (textView5 != null) {
            textView5.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + getString(R.string.decible_unit));
        }
        TextView textView6 = (TextView) _$_findCachedViewById(R.id.tv_total_time);
        if (textView6 != null) {
            textView6.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + " min");
        }
        SimpleDateFormat simpleDateFormat = this.i;
        PayUtils payUtils = PayUtils.INSTANCE;
        Calendar calendar = this.h;
        Intrinsics.checkNotNull(calendar);
        Date parse = simpleDateFormat.parse(payUtils.getDateFormatValue(calendar));
        if (PayUtils.currentDayString().equals(getSimpleDateFormat().format(parse))) {
            TextView textView7 = (TextView) _$_findCachedViewById(R.id.tv_date);
            if (textView7 != null) {
                textView7.setText(getResources().getString(R.string.today));
            }
        } else if (PayUtils.previousDayString().equals(getSimpleDateFormat().format(parse))) {
            TextView textView8 = (TextView) _$_findCachedViewById(R.id.tv_date);
            if (textView8 != null) {
                textView8.setText(getResources().getString(R.string.yesterday));
            }
        } else {
            TextView textView9 = (TextView) _$_findCachedViewById(R.id.tv_date);
            if (textView9 != null) {
                textView9.setText(getSimpleDateFormat().format(parse));
            }
        }
        TextView textView10 = (TextView) _$_findCachedViewById(R.id.tv_ambient_sound_status);
        if (textView10 != null) {
            textView10.setText(String.valueOf(AppConstants.AMBIENT_SOUND_EMPTY.getValue()));
        }
        TextView textView11 = (TextView) _$_findCachedViewById(R.id.tv_min_ambient_sound);
        if (textView11 != null) {
            textView11.setText(String.valueOf(AppConstants.AMBIENT_SOUND_EMPTY.getValue()));
        }
        TextView textView12 = (TextView) _$_findCachedViewById(R.id.tv_max_ambient_sound);
        if (textView12 == null) {
            return;
        }
        textView12.setText(String.valueOf(AppConstants.AMBIENT_SOUND_EMPTY.getValue()));
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractAmbientSoundDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (arrayList != null) {
            ((LineChart) _$_findCachedViewById(R.id.lineChartAmbient)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(8);
            if (arrayList2 != null) {
                e(arrayList, arrayList2);
                return;
            }
            return;
        }
        ((LineChart) _$_findCachedViewById(R.id.lineChartAmbient)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(0);
    }
}
