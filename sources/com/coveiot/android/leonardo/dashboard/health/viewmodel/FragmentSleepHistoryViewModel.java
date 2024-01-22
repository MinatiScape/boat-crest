package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.health.RemoveDuplicateDailyDataComparator;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentSleepHistory;
import com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate;
import com.coveiot.android.leonardo.dashboard.health.model.SleepMonthWiseData;
import com.coveiot.android.leonardo.dashboard.health.model.SleepWeekWiseData;
import com.coveiot.android.leonardo.dashboard.model.SleepScoreDataModel;
import com.coveiot.android.leonardo.dashboard.vitals.model.SleepInsightsModel;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.utils.GraphsUtility;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sleepalgorithm.filtering.CZ0ParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.IDOParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MatrixParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MoyangParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SmaParsedSleepDataF2;
import com.coveiot.android.sleepalgorithm.filtering.StrideParsedSleepDataV2NoAlgo;
import com.coveiot.android.sleepalgorithm.filtering.TouchELXParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepAlgoWithREM;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiManager;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiReq;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiRes;
import com.coveiot.coveaccess.sleepscore.model.SleepHistory;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.BarEntry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSleepHistoryViewModel extends ViewModel {
    @NotNull
    public MutableLiveData<SleepInsightsModel> A;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4760a;
    @Nullable
    public HashMap<String, SleepMonthWiseData> b;
    @Nullable
    public HashMap<String, SleepWeekWiseData> c;
    public ContractFragmentSleepHistory contractFragmentSleepHistory;
    @NotNull
    public HashMap<String, List<SleepDataModelForLastNight>> d;
    @Nullable
    public List<? extends SleepDataModelWithDate> e;
    @NotNull
    public final String f;
    @NotNull
    public MutableLiveData<SleepScoreDataModel> g;
    @NotNull
    public MutableLiveData<Integer> h;
    @NotNull
    public String i;
    public final SimpleDateFormat j;
    public final SimpleDateFormat k;
    public final SimpleDateFormat l;
    @NotNull
    public MutableLiveData<Boolean> m;
    public LifecycleOwner mLifecycleOwner;
    public PermissionListener mListener;
    @NotNull
    public HashMap<String, List<SleepDataModelForLastNight>> n;
    @NotNull
    public HashMap<String, Integer> o;
    @NotNull
    public MutableLiveData<Boolean> p;
    @NotNull
    public ArrayList<BarEntry> q;
    @NotNull
    public ArrayList<String> r;
    @Nullable
    public String s;
    public int t;
    @NotNull
    public MutableLiveData<Integer> u;
    @NotNull
    public MutableLiveData<Integer> v;
    public int w;
    public int x;
    public int y;
    @NotNull
    public MutableLiveData<SleepInsightsModel> z;

    public FragmentSleepHistoryViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4760a = context;
        this.d = new HashMap<>();
        this.f = "FragmentSleepHistoryViewModel";
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = "yyyy-MM-dd";
        this.j = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        this.k = AppUtils.getSimpleDateFormat("dd/MM");
        this.l = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        this.m = new MutableLiveData<>();
        this.n = new HashMap<>();
        this.o = new HashMap<>();
        this.p = new MutableLiveData<>();
        this.q = new ArrayList<>();
        this.r = new ArrayList<>();
        this.u = new MutableLiveData<>();
        this.v = new MutableLiveData<>();
        this.z = new MutableLiveData<>();
        this.A = new MutableLiveData<>();
    }

    public final void a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<SleepScoreData> sleepScoreDataList = SleepScoreRepository.Companion.getInstance(this.f4760a).getSleepScoreDataList(str, str2, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
        if (sleepScoreDataList != null && sleepScoreDataList.size() > 0) {
            for (SleepScoreData sleepScoreData : sleepScoreDataList) {
                Integer sleepScore = sleepScoreData.getSleepScore();
                Intrinsics.checkNotNull(sleepScore);
                if (sleepScore.intValue() > 0) {
                    Integer sleepScore2 = sleepScoreData.getSleepScore();
                    Intrinsics.checkNotNull(sleepScore2);
                    arrayList.add(Integer.valueOf(sleepScore2.intValue()));
                    String date = sleepScoreData.getDate();
                    Intrinsics.checkNotNull(date);
                    arrayList2.add(date);
                }
            }
        }
        if (arrayList.size() > 0) {
            Integer num = (Integer) Collections.min(arrayList);
            Integer num2 = (Integer) Collections.max(arrayList);
            int size = arrayList.size();
            String str3 = null;
            String str4 = null;
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(num, arrayList.get(i))) {
                    str3 = (String) arrayList2.get(i);
                }
                if (Intrinsics.areEqual(num2, arrayList.get(i))) {
                    str4 = (String) arrayList2.get(i);
                }
            }
            if (str3 != null && str4 != null) {
                Intrinsics.checkNotNull(num);
                int intValue = num.intValue();
                Intrinsics.checkNotNull(num2);
                this.g.postValue(new SleepScoreDataModel(str3, intValue, str4, num2.intValue()));
                return;
            }
            j(str2);
            return;
        }
        j(str2);
    }

    public final void b(Date date, Date date2) {
        String sleepDataEndDate = RepositoryUtils.formatDate(date2, this.i);
        String sleepDataStartDate = RepositoryUtils.formatDate(date, this.i);
        String str = this.f;
        LogsHelper.d(str, "sleepDatastarttime*** " + sleepDataStartDate);
        String str2 = this.f;
        LogsHelper.d(str2, "sleepDataendtime*** " + sleepDataEndDate);
        List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(this.f4760a).getDailySleepDataBetweenDates(sleepDataStartDate, sleepDataEndDate, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
        if (dailySleepDataBetweenDates != null && dailySleepDataBetweenDates.size() > 0) {
            for (DailySleepData dailySleepData : dailySleepDataBetweenDates) {
                if (dailySleepData.getSleepScore() > 0) {
                    SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
                    String date3 = dailySleepData.getDate();
                    Intrinsics.checkNotNullExpressionValue(date3, "dailySleepData.date");
                    if (companion.getInstance(this.f4760a).getSleepScoreData(date3, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress()) == null) {
                        SleepScoreData sleepScoreData = new SleepScoreData();
                        sleepScoreData.setSleepScore(Integer.valueOf(dailySleepData.getSleepScore()));
                        sleepScoreData.setMacAddress(BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
                        String date4 = dailySleepData.getDate();
                        Intrinsics.checkNotNullExpressionValue(date4, "dailySleepData.date");
                        sleepScoreData.setDate(date4);
                        companion.getInstance(this.f4760a).insert(sleepScoreData);
                    }
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(sleepDataStartDate, "sleepDataStartDate");
        Intrinsics.checkNotNullExpressionValue(sleepDataEndDate, "sleepDataEndDate");
        a(sleepDataStartDate, sleepDataEndDate);
    }

    public final int c(Map.Entry<String, SleepMonthWiseData> entry) {
        if (BleApiManager.getInstance(this.f4760a).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
            String month = entry.getValue().getMonth();
            Intrinsics.checkNotNullExpressionValue(month, "monthlyItem.value.month");
            List split$default = StringsKt__StringsKt.split$default((CharSequence) month, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt((String) split$default.get(1)), Integer.parseInt((String) split$default.get(0)), 0);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Integer.parseInt((String) split$default.get(1)), Integer.parseInt((String) split$default.get(0)) - 1, 1);
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat(this.i);
            return d(simpleDateFormat.format(calendar2.getTime()).toString(), simpleDateFormat.format(calendar.getTime()).toString());
        }
        return 0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.LiveData, T] */
    public final void computeDataFromDb() {
        Calendar fromDate = Calendar.getInstance();
        fromDate.add(2, -3);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Intrinsics.checkNotNullExpressionValue(fromDate, "fromDate");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4760a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        ?? dayWiseList = SleepRepository.Companion.getInstance(this.f4760a).getDayWiseList(fromDate, calendar, connectedDeviceMacAddress);
        objectRef.element = dayWiseList;
        ((LiveData) dayWiseList).observe(getMLifecycleOwner(), new Observer<List<? extends DailySleepDataAlias>>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$computeDataFromDb$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$computeDataFromDb$1$onChanged$1", f = "FragmentSleepHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes3.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ List<DailySleepDataAlias> $dailySleepDataAlias;
                public int label;
                public final /* synthetic */ FragmentSleepHistoryViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel, List<? extends DailySleepDataAlias> list, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentSleepHistoryViewModel;
                    this.$dailySleepDataAlias = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$dailySleepDataAlias, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    HashMap hashMap;
                    HashMap hashMap2;
                    HashMap hashMap3;
                    HashMap hashMap4;
                    HashMap hashMap5;
                    List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData;
                    HashMap hashMap6;
                    SmaParsedSleepDataF2 smaParsedSleepDataF2;
                    MoyangParsedSleepData moyangParsedSleepData;
                    IDOParsedSleepData iDOParsedSleepData;
                    TouchELXParsedSleepData touchELXParsedSleepData;
                    MatrixParsedSleepData matrixParsedSleepData;
                    StrideParsedSleepDataV2NoAlgo strideParsedSleepDataV2NoAlgo;
                    HashMap hashMap7;
                    HashMap hashMap8;
                    HashMap hashMap9;
                    HashMap hashMap10;
                    int i;
                    HashMap hashMap11;
                    HashMap hashMap12;
                    HashMap hashMap13;
                    HashMap hashMap14;
                    JStyleParsedSleepData jStyleParsedSleepData;
                    JStyleSleepAlgoWithREM jStyleSleepAlgoWithREM;
                    HashMap hashMap15;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.setMSleepDataList(new ArrayList());
                        hashMap = this.this$0.c;
                        if (hashMap == null) {
                            this.this$0.c = new HashMap();
                        } else {
                            hashMap2 = this.this$0.c;
                            Intrinsics.checkNotNull(hashMap2);
                            hashMap2.clear();
                        }
                        hashMap3 = this.this$0.b;
                        if (hashMap3 == null) {
                            this.this$0.b = new HashMap();
                        } else {
                            hashMap4 = this.this$0.b;
                            Intrinsics.checkNotNull(hashMap4);
                            hashMap4.clear();
                        }
                        List<DailySleepDataAlias> list = this.$dailySleepDataAlias;
                        Intrinsics.checkNotNull(list);
                        int size = list.size();
                        int i2 = 0;
                        int i3 = 0;
                        while (i3 < size) {
                            String date = this.$dailySleepDataAlias.get(i3).getDate();
                            Date parse = AppUtils.getSimpleDateFormat(this.this$0.getDateFormat()).parse(date);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setFirstDayOfWeek(2);
                            calendar.setTime(parse);
                            if (calendar.get(1) >= 2020) {
                                hashMap5 = this.this$0.d;
                                if (hashMap5.containsKey(date)) {
                                    hashMap15 = this.this$0.d;
                                    Object obj2 = hashMap15.get(date);
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.covedb.sleep.model.SleepDataModelForLastNight>");
                                    lastNignthSleepDataWithOutLiveData = TypeIntrinsics.asMutableList(obj2);
                                } else {
                                    Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
                                    lastNignthSleepDataWithOutLiveData = SleepRepository.Companion.getInstance(this.this$0.getContext()).getLastNignthSleepDataWithOutLiveData(calendar, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                                    hashMap6 = this.this$0.d;
                                    Intrinsics.checkNotNull(lastNignthSleepDataWithOutLiveData);
                                    hashMap6.put(date, lastNignthSleepDataWithOutLiveData);
                                }
                                Intrinsics.checkNotNull(lastNignthSleepDataWithOutLiveData);
                                if (!lastNignthSleepDataWithOutLiveData.isEmpty()) {
                                    int i4 = 1440;
                                    byte[] bArr = new byte[1440];
                                    Arrays.fill(bArr, i2, 1440, (byte) -1);
                                    int size2 = lastNignthSleepDataWithOutLiveData.size();
                                    int i5 = i2;
                                    while (i5 < size2) {
                                        int size3 = lastNignthSleepDataWithOutLiveData.get(i5).getCodevalue().size();
                                        int i6 = i2;
                                        while (i6 < size3) {
                                            int timeIndex = GraphsUtility.getTimeIndex(lastNignthSleepDataWithOutLiveData.get(i5), i6);
                                            if (timeIndex < i4) {
                                                Integer num = lastNignthSleepDataWithOutLiveData.get(i5).getCodevalue().get(i6);
                                                Intrinsics.checkNotNullExpressionValue(num, "lastNightSleep.get(i).ge…                        )");
                                                int intValue = num.intValue();
                                                if (intValue > -1) {
                                                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                                                    Context context = this.this$0.getContext();
                                                    Intrinsics.checkNotNull(context);
                                                    if (!companion.isJstyleDevice(context)) {
                                                        Context context2 = this.this$0.getContext();
                                                        Intrinsics.checkNotNull(context2);
                                                        if (!companion.isSmaDevice(context2)) {
                                                            Context context3 = this.this$0.getContext();
                                                            Intrinsics.checkNotNull(context3);
                                                            if (!companion.isMoyangDevice(context3)) {
                                                                Context context4 = this.this$0.getContext();
                                                                Intrinsics.checkNotNull(context4);
                                                                if (!companion.isCZDevice(context4)) {
                                                                    Context context5 = this.this$0.getContext();
                                                                    Intrinsics.checkNotNull(context5);
                                                                    if (!companion.isCADevice(context5)) {
                                                                        Context context6 = this.this$0.getContext();
                                                                        Intrinsics.checkNotNull(context6);
                                                                        if (!companion.isCYDevice(context6)) {
                                                                            Context context7 = this.this$0.getContext();
                                                                            Intrinsics.checkNotNull(context7);
                                                                            if (!companion.isMatrixDevice(context7)) {
                                                                                Context context8 = this.this$0.getContext();
                                                                                Intrinsics.checkNotNull(context8);
                                                                                if (!companion.isIDODevice(context8)) {
                                                                                    Context context9 = this.this$0.getContext();
                                                                                    Intrinsics.checkNotNull(context9);
                                                                                    if (!companion.isTouchELXDevice(context9)) {
                                                                                        Context context10 = this.this$0.getContext();
                                                                                        Intrinsics.checkNotNull(context10);
                                                                                        if (!companion.isPS1Device(context10) && !companion.isBESDevice(this.this$0.getContext())) {
                                                                                            if (intValue != 3) {
                                                                                                bArr[timeIndex] = (byte) intValue;
                                                                                            } else {
                                                                                                bArr[timeIndex] = -1;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    bArr[timeIndex] = (byte) intValue;
                                                }
                                            }
                                            i6++;
                                            i4 = 1440;
                                        }
                                        i5++;
                                        i4 = 1440;
                                        i2 = 0;
                                    }
                                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                                    Context context11 = this.this$0.getContext();
                                    Intrinsics.checkNotNull(context11);
                                    SleepDataModel sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    CZ0ParsedSleepData cZ0ParsedSleepData = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    sleepDataModel = null;
                                    if (companion2.isJstyleDevice(context11)) {
                                        if (BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                                            try {
                                                jStyleSleepAlgoWithREM = new JStyleSleepAlgoWithREM(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                jStyleSleepAlgoWithREM = null;
                                            }
                                            if (jStyleSleepAlgoWithREM != null && jStyleSleepAlgoWithREM.getSleepDataModel() != null) {
                                                sleepDataModel = jStyleSleepAlgoWithREM.getSleepDataModel();
                                            }
                                        } else {
                                            try {
                                                jStyleParsedSleepData = new JStyleParsedSleepData(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                                jStyleParsedSleepData = null;
                                            }
                                            if (jStyleParsedSleepData != null && jStyleParsedSleepData.getSleepDataModel() != null) {
                                                sleepDataModel = jStyleParsedSleepData.getSleepDataModel();
                                            }
                                        }
                                    } else if (companion2.isSmaDevice(this.this$0.getContext())) {
                                        try {
                                            smaParsedSleepDataF2 = new SmaParsedSleepDataF2(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                            smaParsedSleepDataF2 = null;
                                        }
                                        if (smaParsedSleepDataF2 != null && smaParsedSleepDataF2.getSleepDataModel() != null) {
                                            sleepDataModel = smaParsedSleepDataF2.getSleepDataModel();
                                        }
                                    } else if (companion2.isMoyangDevice(this.this$0.getContext())) {
                                        try {
                                            moyangParsedSleepData = new MoyangParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e4) {
                                            e4.printStackTrace();
                                            moyangParsedSleepData = null;
                                        }
                                        if (moyangParsedSleepData != null && moyangParsedSleepData.getSleepDataModel() != null) {
                                            sleepDataModel = moyangParsedSleepData.getSleepDataModel();
                                        }
                                    } else if (companion2.isIDODevice(this.this$0.getContext())) {
                                        try {
                                            iDOParsedSleepData = new IDOParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                            iDOParsedSleepData = null;
                                        }
                                        if (iDOParsedSleepData != null && iDOParsedSleepData.getSleepDataModel() != null) {
                                            sleepDataModel = iDOParsedSleepData.getSleepDataModel();
                                        }
                                    } else if (companion2.isTouchELXDevice(this.this$0.getContext())) {
                                        try {
                                            touchELXParsedSleepData = new TouchELXParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e6) {
                                            e6.printStackTrace();
                                            touchELXParsedSleepData = null;
                                        }
                                        if (touchELXParsedSleepData != null && touchELXParsedSleepData.getSleepDataModel() != null) {
                                            sleepDataModel = touchELXParsedSleepData.getSleepDataModel();
                                        }
                                    } else if (companion2.isMatrixDevice(this.this$0.getContext())) {
                                        try {
                                            matrixParsedSleepData = new MatrixParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e7) {
                                            e7.printStackTrace();
                                            matrixParsedSleepData = null;
                                        }
                                        if (matrixParsedSleepData != null && matrixParsedSleepData.getSleepDataModel() != null) {
                                            sleepDataModel = matrixParsedSleepData.getSleepDataModel();
                                        }
                                    } else if (companion2.isKaHaDeviceWithRem(this.this$0.getContext())) {
                                        try {
                                            cZ0ParsedSleepData = new CZ0ParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e8) {
                                            e8.printStackTrace();
                                        }
                                        Intrinsics.checkNotNull(cZ0ParsedSleepData);
                                        sleepDataModel = cZ0ParsedSleepData.getSleepDataModel();
                                    } else {
                                        try {
                                            strideParsedSleepDataV2NoAlgo = new StrideParsedSleepDataV2NoAlgo(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                                        } catch (Exception e9) {
                                            e9.printStackTrace();
                                            strideParsedSleepDataV2NoAlgo = null;
                                        }
                                        if (strideParsedSleepDataV2NoAlgo != null && strideParsedSleepDataV2NoAlgo.getSleepDataModel() != null) {
                                            sleepDataModel = strideParsedSleepDataV2NoAlgo.getSleepDataModel();
                                        }
                                    }
                                    Intrinsics.checkNotNull(sleepDataModel);
                                    SleepDataModelWithDate sleepDataModelWithDate = new SleepDataModelWithDate(sleepDataModel.getFilteredSleepData(), sleepDataModel.getSleepStartHour(), sleepDataModel.getSleepStartMinute(), sleepDataModel.getSleepEndHour(), sleepDataModel.getSleepEndMinute(), sleepDataModel.getCountOfLightSleepMinutes(), sleepDataModel.getCountOfDeepSleepMinutes(), sleepDataModel.getCountOfAwakeMinutes(), sleepDataModel.getCountTotalSleep(), sleepDataModel.getCountOfREMMinutes());
                                    sleepDataModelWithDate.setLastNightSleepData(lastNignthSleepDataWithOutLiveData);
                                    sleepDataModelWithDate.setDate(calendar);
                                    List<SleepDataModelWithDate> mSleepDataList = this.this$0.getMSleepDataList();
                                    Intrinsics.checkNotNull(mSleepDataList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate> }");
                                    if (!((ArrayList) mSleepDataList).contains(sleepDataModelWithDate)) {
                                        List<SleepDataModelWithDate> mSleepDataList2 = this.this$0.getMSleepDataList();
                                        Intrinsics.checkNotNull(mSleepDataList2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate> }");
                                        ((ArrayList) mSleepDataList2).add(sleepDataModelWithDate);
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                    Locale locale = Locale.ENGLISH;
                                    String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Boxing.boxInt(sleepDataModelWithDate.getDate().get(3))}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                                    sb.append(format);
                                    sb.append(' ');
                                    sb.append(sleepDataModelWithDate.getDate().get(1));
                                    String sb2 = sb.toString();
                                    hashMap7 = this.this$0.c;
                                    Intrinsics.checkNotNull(hashMap7);
                                    if (hashMap7.containsKey(sb2)) {
                                        hashMap8 = this.this$0.c;
                                        Intrinsics.checkNotNull(hashMap8);
                                        SleepWeekWiseData sleepWeekWiseData = (SleepWeekWiseData) hashMap8.get(sb2);
                                        SleepWeekWiseData sleepWeekWiseData2 = new SleepWeekWiseData();
                                        int countOfDeepSleepMinutes = sleepDataModelWithDate.getCountOfDeepSleepMinutes();
                                        Intrinsics.checkNotNull(sleepWeekWiseData);
                                        sleepWeekWiseData2.setTotalDeepSleep(countOfDeepSleepMinutes + sleepWeekWiseData.getTotalDeepSleep());
                                        sleepWeekWiseData2.setTotalLightSleep(sleepDataModelWithDate.getCountOfLightSleepMinutes() + sleepWeekWiseData.getTotalLightSleep());
                                        sleepWeekWiseData2.setAwake(sleepDataModelWithDate.getCountOfAwakeMinutes() + sleepWeekWiseData.getAwake());
                                        sleepWeekWiseData2.setTotalRemSleep(sleepDataModelWithDate.getCountOfREMMinutes() + sleepWeekWiseData.getTotalRemSleep());
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append('W');
                                        sb3.append(sleepDataModelWithDate.getDate().get(3));
                                        sleepWeekWiseData2.setWeek(sb3.toString());
                                        sleepWeekWiseData2.setYear(sleepDataModelWithDate.getDate().get(1));
                                        if (sleepDataModelWithDate.getCountOfDeepSleepMinutes() + sleepDataModelWithDate.getCountOfLightSleepMinutes() > 0) {
                                            sleepWeekWiseData2.setNoOfDaysCount(sleepWeekWiseData.getNoOfDaysCount() + 1);
                                        } else {
                                            sleepWeekWiseData2.setNoOfDaysCount(sleepWeekWiseData.getNoOfDaysCount());
                                        }
                                        hashMap9 = this.this$0.c;
                                        Intrinsics.checkNotNull(hashMap9);
                                        hashMap9.put(sb2, sleepWeekWiseData2);
                                    } else {
                                        SleepWeekWiseData sleepWeekWiseData3 = new SleepWeekWiseData();
                                        sleepWeekWiseData3.setTotalDeepSleep(sleepDataModelWithDate.getCountOfDeepSleepMinutes());
                                        sleepWeekWiseData3.setTotalLightSleep(sleepDataModelWithDate.getCountOfLightSleepMinutes());
                                        sleepWeekWiseData3.setAwake(sleepDataModelWithDate.getCountOfAwakeMinutes());
                                        sleepWeekWiseData3.setTotalRemSleep(sleepDataModelWithDate.getCountOfREMMinutes());
                                        StringBuilder sb4 = new StringBuilder();
                                        sb4.append('W');
                                        sb4.append(sleepDataModelWithDate.getDate().get(3));
                                        sleepWeekWiseData3.setWeek(sb4.toString());
                                        sleepWeekWiseData3.setYear(sleepDataModelWithDate.getDate().get(1));
                                        if (sleepDataModelWithDate.getCountOfDeepSleepMinutes() + sleepDataModelWithDate.getCountOfLightSleepMinutes() > 0) {
                                            sleepWeekWiseData3.setNoOfDaysCount(1);
                                        }
                                        hashMap14 = this.this$0.c;
                                        Intrinsics.checkNotNull(hashMap14);
                                        hashMap14.put(sb2, sleepWeekWiseData3);
                                    }
                                    StringBuilder sb5 = new StringBuilder();
                                    String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Boxing.boxInt(sleepDataModelWithDate.getDate().get(2) + 1)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                                    sb5.append(format2);
                                    sb5.append(' ');
                                    sb5.append(sleepDataModelWithDate.getDate().get(1));
                                    String sb6 = sb5.toString();
                                    hashMap10 = this.this$0.b;
                                    Intrinsics.checkNotNull(hashMap10);
                                    if (hashMap10.containsKey(sb6)) {
                                        i = 0;
                                        hashMap11 = this.this$0.b;
                                        Intrinsics.checkNotNull(hashMap11);
                                        SleepMonthWiseData sleepMonthWiseData = (SleepMonthWiseData) hashMap11.get(sb6);
                                        Intrinsics.checkNotNull(sleepMonthWiseData, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.SleepMonthWiseData");
                                        sleepMonthWiseData.setTotalDeepSleep(sleepDataModelWithDate.getCountOfDeepSleepMinutes() + sleepMonthWiseData.getTotalDeepSleep());
                                        sleepMonthWiseData.setTotalLightSleep(sleepDataModelWithDate.getCountOfLightSleepMinutes() + sleepMonthWiseData.getTotalLightSleep());
                                        sleepMonthWiseData.setAwake(sleepDataModelWithDate.getCountOfAwakeMinutes() + sleepMonthWiseData.getAwake());
                                        sleepMonthWiseData.setTotalRemSleep(sleepDataModelWithDate.getCountOfREMMinutes() + sleepMonthWiseData.getTotalRemSleep());
                                        if (sleepDataModelWithDate.getCountOfDeepSleepMinutes() + sleepDataModelWithDate.getCountOfLightSleepMinutes() > 0) {
                                            sleepMonthWiseData.setNoOfDaysCount(sleepMonthWiseData.getNoOfDaysCount() + 1);
                                        }
                                        hashMap12 = this.this$0.b;
                                        Intrinsics.checkNotNull(hashMap12);
                                        hashMap12.put(sb6, sleepMonthWiseData);
                                    } else {
                                        SleepMonthWiseData sleepMonthWiseData2 = new SleepMonthWiseData();
                                        sleepMonthWiseData2.setTotalDeepSleep(sleepDataModelWithDate.getCountOfDeepSleepMinutes());
                                        sleepMonthWiseData2.setTotalRemSleep(sleepDataModelWithDate.getCountOfREMMinutes());
                                        sleepMonthWiseData2.setTotalLightSleep(sleepDataModelWithDate.getCountOfLightSleepMinutes());
                                        sleepMonthWiseData2.setAwake(sleepDataModelWithDate.getCountOfAwakeMinutes());
                                        StringBuilder sb7 = new StringBuilder();
                                        i = 0;
                                        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Boxing.boxInt(sleepDataModelWithDate.getDate().get(2) + 1)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                                        sb7.append(format3);
                                        sb7.append(' ');
                                        sb7.append(sleepDataModelWithDate.getDate().get(1));
                                        sleepMonthWiseData2.setMonth(sb7.toString());
                                        if (sleepDataModelWithDate.getCountOfDeepSleepMinutes() + sleepDataModelWithDate.getCountOfLightSleepMinutes() > 0) {
                                            sleepMonthWiseData2.setNoOfDaysCount(sleepMonthWiseData2.getNoOfDaysCount() + 1);
                                        }
                                        hashMap13 = this.this$0.b;
                                        Intrinsics.checkNotNull(hashMap13);
                                        hashMap13.put(sb6, sleepMonthWiseData2);
                                    }
                                    TreeSet treeSet = new TreeSet(new RemoveDuplicateDailyDataComparator());
                                    List<SleepDataModelWithDate> mSleepDataList3 = this.this$0.getMSleepDataList();
                                    Intrinsics.checkNotNull(mSleepDataList3, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.dashboard.health.model.SleepDataModelWithDate> }");
                                    treeSet.addAll((ArrayList) mSleepDataList3);
                                    i3++;
                                    i2 = i;
                                }
                            }
                            i = i2;
                            i3++;
                            i2 = i;
                        }
                        this.this$0.getComputeDataFromDbStatusLiveData().postValue(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // androidx.lifecycle.Observer
            public void onChanged(@androidx.annotation.Nullable @Nullable List<? extends DailySleepDataAlias> list) {
                e.e(ViewModelKt.getViewModelScope(FragmentSleepHistoryViewModel.this), Dispatchers.getIO(), null, new a(FragmentSleepHistoryViewModel.this, list, null), 2, null);
                objectRef.element.removeObserver(this);
            }
        });
    }

    public final int d(String str, String str2) {
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f4760a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        List<HourlySleepData> remSleepListBetweenDates = SleepRepository.Companion.getInstance(this.f4760a).getRemSleepListBetweenDates(str, str2, connectedDeviceMacAddress);
        new ArrayList();
        Intrinsics.checkNotNull(remSleepListBetweenDates);
        int size = remSleepListBetweenDates.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List<Integer> codevalue = remSleepListBetweenDates.get(i2).getCodevalue();
            int size2 = codevalue.size();
            for (int i3 = 0; i3 < size2; i3++) {
                int i4 = JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM;
                int i5 = JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM;
                Integer num = codevalue.get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "codedArrayInt[k]");
                int intValue = num.intValue();
                if (i4 <= intValue && intValue <= i5) {
                    i++;
                }
            }
        }
        return i;
    }

    public final void e(Calendar calendar) {
        Integer sleepScore;
        if (UserDataManager.getInstance(this.f4760a).isEnableSleepEnergyScoreFeature(this.f4760a)) {
            Object clone = calendar.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(6, -1);
            final String currentDate = RepositoryUtils.formatDate(calendar.getTime(), this.i);
            String formatDate = RepositoryUtils.formatDate(calendar2.getTime(), this.i);
            String str = this.f;
            LogsHelper.d(str, "sleepDatastarttime*** " + currentDate);
            String str2 = this.f;
            LogsHelper.d(str2, "sleepDataendtime*** " + formatDate);
            Intrinsics.checkNotNullExpressionValue(currentDate, "currentDate");
            SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(this.f4760a).getSleepScoreData(currentDate, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
            if (sleepScoreData != null && ((sleepScore = sleepScoreData.getSleepScore()) == null || sleepScore.intValue() != -1)) {
                Integer sleepScore2 = sleepScoreData.getSleepScore();
                if (sleepScore2 != null) {
                    this.h.postValue(Integer.valueOf(sleepScore2.intValue()));
                    return;
                }
                return;
            }
            List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(this.f4760a).getDailySleepDataBetweenDates(formatDate, currentDate, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
            if (dailySleepDataBetweenDates != null && dailySleepDataBetweenDates.size() > 0) {
                SleepScoreApiReq sleepScoreApiReq = new SleepScoreApiReq();
                ArrayList arrayList = new ArrayList();
                for (DailySleepData dailySleepData : dailySleepDataBetweenDates) {
                    SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
                    Context context = this.f4760a;
                    Intrinsics.checkNotNullExpressionValue(dailySleepData, "dailySleepData");
                    arrayList.add(sleepScoreApiCall.getFormattedDailySleepData(context, dailySleepData));
                }
                if (arrayList.size() > 0) {
                    Date time = calendar2.getTime();
                    Intrinsics.checkNotNullExpressionValue(time, "calendarPreviousDay.time");
                    ArrayList<SleepHistory> sleepHistoryArray = Utils.getSleepHistoryArray(time, this.f4760a, this.i);
                    if (sleepHistoryArray != null && sleepHistoryArray.size() > 0) {
                        sleepScoreApiReq.setSleepHistory(sleepHistoryArray);
                    }
                    sleepScoreApiReq.setSleepData(arrayList);
                    sleepScoreApiReq.setUserInfo(Utils.getUserInfo(this.f4760a));
                    SleepScoreApiManager.sendDataForSleepQualityCal(sleepScoreApiReq, new CoveApiListener<SleepScoreApiRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$getSleepScoreApiCall$3
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            FragmentSleepHistoryViewModel.this.getSleepScoreLiveData().postValue(0);
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@NotNull SleepScoreApiRes sleepScoreApiRes) {
                            String str3;
                            String str4;
                            Intrinsics.checkNotNullParameter(sleepScoreApiRes, "sleepScoreApiRes");
                            LogsHelper.d("sleepRes*** ", new Gson().toJson(sleepScoreApiRes));
                            int size = sleepScoreApiRes.getData().size();
                            for (int i = 0; i < size; i++) {
                                SleepScoreApiRes.Data data = sleepScoreApiRes.getData().get(i);
                                str3 = FragmentSleepHistoryViewModel.this.f;
                                LogsHelper.d(str3, "sleepKEy ** " + data.getDate());
                                str4 = FragmentSleepHistoryViewModel.this.f;
                                LogsHelper.d(str4, "sleepvalue ** " + data.getSleepQuality());
                                if (Intrinsics.areEqual(currentDate, data.getDate())) {
                                    Integer sleepQuality = data.getSleepQuality();
                                    Intrinsics.checkNotNullExpressionValue(sleepQuality, "sleepData.sleepQuality");
                                    FragmentSleepHistoryViewModel.this.getSleepScoreLiveData().postValue(sleepQuality.intValue() > 0 ? data.getSleepQuality() : 0);
                                }
                            }
                        }
                    });
                    return;
                }
                this.h.postValue(0);
                return;
            }
            this.h.postValue(0);
        } else if (BleApiManager.getInstance(this.f4760a).getBleApi().getDeviceSupportedFeatures().isSleepScoreSupportsFromBand()) {
            String currentDate2 = RepositoryUtils.formatDate(calendar.getTime(), this.i);
            DailySleepData dailySleepDatafortheDate = SleepDBRead.getInstance(this.f4760a).getDailySleepDatafortheDate(currentDate2, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
            if (dailySleepDatafortheDate != null) {
                SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
                String date = dailySleepDatafortheDate.getDate();
                Intrinsics.checkNotNullExpressionValue(date, "dailySleepData.date");
                if (companion.getInstance(this.f4760a).getSleepScoreData(date, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress()) == null) {
                    SleepScoreData sleepScoreData2 = new SleepScoreData();
                    sleepScoreData2.setSleepScore(Integer.valueOf(dailySleepDatafortheDate.getSleepScore()));
                    sleepScoreData2.setMacAddress(BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
                    String date2 = dailySleepDatafortheDate.getDate();
                    Intrinsics.checkNotNullExpressionValue(date2, "dailySleepData.date");
                    sleepScoreData2.setDate(date2);
                    companion.getInstance(this.f4760a).insert(sleepScoreData2);
                }
            }
            Intrinsics.checkNotNullExpressionValue(currentDate2, "currentDate");
            h(currentDate2);
        }
    }

    public final void f(Date date, Date date2) {
        if (UserDataManager.getInstance(this.f4760a).isEnableSleepEnergyScoreFeature(this.f4760a)) {
            SleepScoreApiReq sleepScoreApiReq = new SleepScoreApiReq();
            final String sleepDataEndDate = RepositoryUtils.formatDate(date2, this.i);
            String formatDate = RepositoryUtils.formatDate(date, this.i);
            String str = this.f;
            LogsHelper.d(str, "sleepDatastarttime*** " + formatDate);
            String str2 = this.f;
            LogsHelper.d(str2, "sleepDataendtime*** " + sleepDataEndDate);
            List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(this.f4760a).getDailySleepDataBetweenDates(formatDate, sleepDataEndDate, BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
            ArrayList arrayList = new ArrayList();
            if (dailySleepDataBetweenDates.size() > 0) {
                for (DailySleepData dailySleepData : dailySleepDataBetweenDates) {
                    SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
                    Context context = this.f4760a;
                    Intrinsics.checkNotNullExpressionValue(dailySleepData, "dailySleepData");
                    arrayList.add(sleepScoreApiCall.getFormattedDailySleepData(context, dailySleepData));
                }
                if (arrayList.size() > 0) {
                    ArrayList<SleepHistory> sleepHistoryArray = Utils.getSleepHistoryArray(date, this.f4760a, this.i);
                    if (sleepHistoryArray != null && sleepHistoryArray.size() > 0) {
                        sleepScoreApiReq.setSleepHistory(sleepHistoryArray);
                    }
                    sleepScoreApiReq.setSleepData(arrayList);
                    sleepScoreApiReq.setUserInfo(Utils.getUserInfo(this.f4760a));
                    SleepScoreApiManager.sendDataForSleepQualityCal(sleepScoreApiReq, new CoveApiListener<SleepScoreApiRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$getSleepScoreBatchApiCall$2
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = FragmentSleepHistoryViewModel.this;
                            String sleepDataEndDate2 = sleepDataEndDate;
                            Intrinsics.checkNotNullExpressionValue(sleepDataEndDate2, "sleepDataEndDate");
                            fragmentSleepHistoryViewModel.j(sleepDataEndDate2);
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@NotNull SleepScoreApiRes sleepScoreApiRes) {
                            Intrinsics.checkNotNullParameter(sleepScoreApiRes, "sleepScoreApiRes");
                            LogsHelper.d("sleepRes*** ", new Gson().toJson(sleepScoreApiRes));
                            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = FragmentSleepHistoryViewModel.this;
                            String sleepDataEndDate2 = sleepDataEndDate;
                            Intrinsics.checkNotNullExpressionValue(sleepDataEndDate2, "sleepDataEndDate");
                            fragmentSleepHistoryViewModel.i(sleepScoreApiRes, sleepDataEndDate2);
                        }
                    });
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(sleepDataEndDate, "sleepDataEndDate");
                j(sleepDataEndDate);
                return;
            }
            Intrinsics.checkNotNullExpressionValue(sleepDataEndDate, "sleepDataEndDate");
            j(sleepDataEndDate);
        }
    }

    public final int g(Map.Entry<String, SleepWeekWiseData> entry) {
        if (BleApiManager.getInstance(this.f4760a).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, entry.getValue().getYear());
            calendar.setFirstDayOfWeek(2);
            String week = entry.getValue().getWeek();
            Intrinsics.checkNotNullExpressionValue(week, "sleepWeeklyData.value.week");
            calendar.set(3, Integer.parseInt(m.replace$default(week, ExifInterface.LONGITUDE_WEST, "", false, 4, (Object) null)));
            calendar.add(5, -(calendar.get(7) - 2));
            Date time = calendar.getTime();
            calendar.add(5, 6);
            Date time2 = calendar.getTime();
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat(this.i);
            String format = simpleDateFormat.format(time);
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(startTime)");
            String format2 = simpleDateFormat.format(time2);
            Intrinsics.checkNotNullExpressionValue(format2, "simpleDateFormat.format(endTime)");
            return d(format, format2);
        }
        return 0;
    }

    @NotNull
    public final MutableLiveData<Integer> getAvgSleepData() {
        return this.u;
    }

    @NotNull
    public final ArrayList<BarEntry> getBarEntries() {
        return this.q;
    }

    @NotNull
    public final ArrayList<String> getBarLabels() {
        return this.r;
    }

    @NotNull
    public final MutableLiveData<Boolean> getComputeDataFromDbStatusLiveData() {
        return this.m;
    }

    @NotNull
    public final Context getContext() {
        return this.f4760a;
    }

    @NotNull
    public final ContractFragmentSleepHistory getContractFragmentSleepHistory() {
        ContractFragmentSleepHistory contractFragmentSleepHistory = this.contractFragmentSleepHistory;
        if (contractFragmentSleepHistory != null) {
            return contractFragmentSleepHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractFragmentSleepHistory");
        return null;
    }

    @NotNull
    public final MutableLiveData<SleepInsightsModel> getCurrentSleepInsightsData() {
        return this.z;
    }

    @NotNull
    public final String getDateFormat() {
        return this.i;
    }

    @Nullable
    public final String getLatestDateKey() {
        return this.s;
    }

    @NotNull
    public final HashMap<String, List<SleepDataModelForLastNight>> getMLastQueriedSleepData() {
        return this.n;
    }

    @NotNull
    public final HashMap<String, Integer> getMLastQueriedTotalSleepHourDayWise() {
        return this.o;
    }

    @NotNull
    public final LifecycleOwner getMLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner != null) {
            return lifecycleOwner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLifecycleOwner");
        return null;
    }

    @NotNull
    public final PermissionListener getMListener() {
        PermissionListener permissionListener = this.mListener;
        if (permissionListener != null) {
            return permissionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @Nullable
    public final List<SleepDataModelWithDate> getMSleepDataList() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<SleepInsightsModel> getPreviousSleepInsightsData() {
        return this.A;
    }

    public final SimpleDateFormat getSimpleDateFormat() {
        return this.j;
    }

    public final SimpleDateFormat getSimpleDateFormat1() {
        return this.k;
    }

    public final SimpleDateFormat getSimpleDateFormat2() {
        return this.l;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSleepRangeQueryLiveData() {
        return this.p;
    }

    @NotNull
    public final MutableLiveData<Integer> getSleepScoreLiveData() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<SleepScoreDataModel> getSleepScoreLiveDataBatch() {
        return this.g;
    }

    public final int getTotalAwakeTime() {
        return this.x;
    }

    public final int getTotalDeepTime() {
        return this.y;
    }

    @NotNull
    public final MutableLiveData<Integer> getTotalSleepData() {
        return this.v;
    }

    public final int getTotalSleepHour() {
        return this.t;
    }

    public final int getTotalSleepTime() {
        return this.w;
    }

    public final void h(String str) {
        SleepScoreData sleepScoreFromDbWithDate = PayUtils.INSTANCE.getSleepScoreFromDbWithDate(this.f4760a, str);
        if (sleepScoreFromDbWithDate == null) {
            this.h.postValue(0);
            return;
        }
        Integer sleepScore = sleepScoreFromDbWithDate.getSleepScore();
        Intrinsics.checkNotNull(sleepScore);
        int sleepScore2 = sleepScore.intValue() > 0 ? sleepScoreFromDbWithDate.getSleepScore() : 0;
        MutableLiveData<Integer> mutableLiveData = this.h;
        Intrinsics.checkNotNull(sleepScore2);
        mutableLiveData.postValue(sleepScore2);
    }

    public final void i(SleepScoreApiRes sleepScoreApiRes, String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = sleepScoreApiRes.getData().size();
        for (int i = 0; i < size; i++) {
            SleepScoreData sleepScoreData = new SleepScoreData();
            SleepScoreApiRes.Data data = sleepScoreApiRes.getData().get(i);
            LogsHelper.d(this.f, "sleepKEy ** " + data.getDate());
            LogsHelper.d(this.f, "sleepvalue ** " + data.getSleepQuality());
            String date = data.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "sleepData.date");
            sleepScoreData.setDate(date);
            sleepScoreData.setSleepScore(data.getSleepQuality());
            sleepScoreData.setComputedDate(data.getComputedDate());
            sleepScoreData.setAlgoIdentifier(data.getAlgoIdentifier());
            sleepScoreData.setMacAddress(BleApiManager.getInstance(this.f4760a).getBleApi().getMacAddress());
            if (data.getContributingFactors() != null) {
                sleepScoreData.setWascoCount(data.getContributingFactors().getWaso());
                sleepScoreData.setTimeToDeepSleep(data.getContributingFactors().getTimeToDeepSleep());
                sleepScoreData.setTotalSleep(String.valueOf(data.getContributingFactors().getSleepDuration()));
                sleepScoreData.setSleepConsistency(data.getContributingFactors().getSleepConsistency());
            }
            Integer sleepQuality = data.getSleepQuality();
            Intrinsics.checkNotNullExpressionValue(sleepQuality, "sleepData.sleepQuality");
            if (sleepQuality.intValue() > 0) {
                arrayList.add(data.getSleepQuality());
                arrayList2.add(data.getDate());
            }
        }
        if (arrayList.size() > 0) {
            Integer num = (Integer) Collections.min(arrayList);
            Integer num2 = (Integer) Collections.max(arrayList);
            int size2 = arrayList.size();
            String str2 = null;
            String str3 = null;
            for (int i2 = 0; i2 < size2; i2++) {
                if (Intrinsics.areEqual(num, arrayList.get(i2))) {
                    str2 = (String) arrayList2.get(i2);
                }
                if (Intrinsics.areEqual(num2, arrayList.get(i2))) {
                    str3 = (String) arrayList2.get(i2);
                }
            }
            if (str2 != null && str3 != null) {
                Intrinsics.checkNotNull(num);
                int intValue = num.intValue();
                Intrinsics.checkNotNull(num2);
                this.g.postValue(new SleepScoreDataModel(str2, intValue, str3, num2.intValue()));
                return;
            }
            j(str);
            return;
        }
        j(str);
    }

    public final void j(String str) {
        Intrinsics.checkNotNull(str);
        this.g.postValue(new SleepScoreDataModel(str, 0, str, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void loadSelectedDAteRange(@org.jetbrains.annotations.NotNull com.coveiot.android.leonardo.dashboard.health.model.SleepData r20) {
        /*
            Method dump skipped, instructions count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel.loadSelectedDAteRange(com.coveiot.android.leonardo.dashboard.health.model.SleepData):void");
    }

    public final void queryLastNightSleepDataFor(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new FragmentSleepHistoryViewModel$queryLastNightSleepDataFor$1(this, fromDate, toDate, null), 2, null);
    }

    public final void queryRangeInsightsSleepDataFor(@NotNull Calendar fromDate, @NotNull Calendar toDate, boolean z) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new FragmentSleepHistoryViewModel$queryRangeInsightsSleepDataFor$1(this, fromDate, toDate, z, null), 2, null);
    }

    public final void selectDayView() {
        String str;
        String format;
        String format2;
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat(this.i);
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd/MM");
        List<? extends SleepDataModelWithDate> list = this.e;
        if (!(list == null || list.isEmpty())) {
            Collections.sort(this.e, new SleepDataComparator());
            List<? extends SleepDataModelWithDate> list2 = this.e;
            Intrinsics.checkNotNull(list2);
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                List<? extends SleepDataModelWithDate> list3 = this.e;
                Intrinsics.checkNotNull(list3);
                SleepDataModelWithDate sleepDataModelWithDate = list3.get(i);
                Intrinsics.checkNotNull(sleepDataModelWithDate);
                BarEntry barEntry = new BarEntry(i, sleepDataModelWithDate.getCountTotalSleep() / 60.0f);
                String str2 = "";
                try {
                    Calendar date = sleepDataModelWithDate.getDate();
                    format = simpleDateFormat2.format(date.getTime());
                    format2 = simpleDateFormat.format(date.getTime());
                    Intrinsics.checkNotNullExpressionValue(format2, "simpleDateFormat.format(date.time)");
                } catch (ParseException e) {
                    e = e;
                }
                try {
                    arrayList2.add(format);
                    str = format2;
                } catch (ParseException e2) {
                    e = e2;
                    str2 = format2;
                    e.printStackTrace();
                    str = str2;
                    com.coveiot.android.leonardo.dashboard.health.model.SleepData type = new com.coveiot.android.leonardo.dashboard.health.model.SleepData(String.valueOf(sleepDataModelWithDate.getCountOfDeepSleepMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfLightSleepMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfAwakeMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfREMMinutes()), str).setType("Day");
                    type.setSleepDataModelForLastNights(sleepDataModelWithDate.getLastNightSleepData());
                    barEntry.setData(type);
                    arrayList.add(barEntry);
                }
                com.coveiot.android.leonardo.dashboard.health.model.SleepData type2 = new com.coveiot.android.leonardo.dashboard.health.model.SleepData(String.valueOf(sleepDataModelWithDate.getCountOfDeepSleepMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfLightSleepMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfAwakeMinutes()), String.valueOf(sleepDataModelWithDate.getCountOfREMMinutes()), str).setType("Day");
                type2.setSleepDataModelForLastNights(sleepDataModelWithDate.getLastNightSleepData());
                barEntry.setData(type2);
                arrayList.add(barEntry);
            }
        }
        getContractFragmentSleepHistory().onDataLoaded(arrayList, arrayList2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:20|(1:22)|23|(6:30|31|32|33|35|36)|40|31|32|33|35|36|18) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0190, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0191, code lost:
        r5.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void selectMonthlyView() {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel.selectMonthlyView():void");
    }

    public final void selectWeeklyView() {
        String valueOf;
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        HashMap<String, SleepWeekWiseData> hashMap = this.c;
        if (hashMap != null) {
            Intrinsics.checkNotNull(hashMap);
            Intrinsics.checkNotNullExpressionValue(hashMap.entrySet(), "mWeeklyList!!.entries");
            HashMap<String, SleepWeekWiseData> hashMap2 = this.c;
            Intrinsics.checkNotNull(hashMap2);
            Set<Map.Entry<String, SleepWeekWiseData>> entrySet = hashMap2.entrySet();
            Intrinsics.checkNotNullExpressionValue(entrySet, "mWeeklyList!!.entries");
            List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) entrySet);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
            if (mutableList.size() > 1) {
                h.sortWith(mutableList, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel$selectWeeklyView$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return f.compareValues(simpleDateFormat.parse((String) ((Map.Entry) t).getKey()), simpleDateFormat.parse((String) ((Map.Entry) t2).getKey()));
                    }
                });
            }
            float f = 0.0f;
            for (Object obj : mutableList) {
                Intrinsics.checkNotNullExpressionValue(obj, "iterator.next()");
                Map.Entry<String, SleepWeekWiseData> entry = (Map.Entry) obj;
                float totalDeepSleep = entry.getValue().getTotalDeepSleep() + entry.getValue().getTotalLightSleep() + entry.getValue().getAwake();
                if (entry.getValue().getNoOfDaysCount() == 0) {
                    entry.getValue().setNoOfDaysCount(1);
                }
                BarEntry barEntry = new BarEntry(f, (totalDeepSleep / entry.getValue().getNoOfDaysCount()) / 60.0f);
                f += 1.0f;
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (!companion.isSmaDevice(this.f4760a) && !companion.isMoyangDevice(this.f4760a) && !companion.isKaHaDeviceWithRem(this.f4760a)) {
                    valueOf = String.valueOf(g(entry) / entry.getValue().getNoOfDaysCount());
                } else {
                    valueOf = String.valueOf(entry.getValue().getTotalRemSleep() / entry.getValue().getNoOfDaysCount());
                }
                com.coveiot.android.leonardo.dashboard.health.model.SleepData type = new com.coveiot.android.leonardo.dashboard.health.model.SleepData(String.valueOf(entry.getValue().getTotalDeepSleep() / entry.getValue().getNoOfDaysCount()), String.valueOf(entry.getValue().getTotalLightSleep() / entry.getValue().getNoOfDaysCount()), String.valueOf(entry.getValue().getAwake() / entry.getValue().getNoOfDaysCount()), valueOf, entry.getValue().getWeek()).setType(this.f4760a.getResources().getString(R.string.week));
                type.setYear(entry.getValue().getYear());
                barEntry.setData(type);
                arrayList.add(barEntry);
                arrayList2.add(entry.getValue().getWeek());
            }
            getContractFragmentSleepHistory().onDataLoaded(arrayList, arrayList2);
        }
    }

    public final void setAvgSleepData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.u = mutableLiveData;
    }

    public final void setBarEntries(@NotNull ArrayList<BarEntry> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.q = arrayList;
    }

    public final void setBarLabels(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void setComputeDataFromDbStatusLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.m = mutableLiveData;
    }

    public final void setContractFragmentSleepHistory(@NotNull ContractFragmentSleepHistory contractFragmentSleepHistory) {
        Intrinsics.checkNotNullParameter(contractFragmentSleepHistory, "<set-?>");
        this.contractFragmentSleepHistory = contractFragmentSleepHistory;
    }

    public final void setCurrentSleepInsightsData(@NotNull MutableLiveData<SleepInsightsModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.z = mutableLiveData;
    }

    public final void setDateFormat(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.i = str;
    }

    public final void setLatestDateKey(@Nullable String str) {
        this.s = str;
    }

    public final void setMLastQueriedSleepData(@NotNull HashMap<String, List<SleepDataModelForLastNight>> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.n = hashMap;
    }

    public final void setMLastQueriedTotalSleepHourDayWise(@NotNull HashMap<String, Integer> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.o = hashMap;
    }

    public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
        this.mLifecycleOwner = lifecycleOwner;
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }

    public final void setMSleepDataList(@Nullable List<? extends SleepDataModelWithDate> list) {
        this.e = list;
    }

    public final void setPreviousSleepInsightsData(@NotNull MutableLiveData<SleepInsightsModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.A = mutableLiveData;
    }

    public final void setSleepRangeQueryLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.p = mutableLiveData;
    }

    public final void setSleepScoreLiveData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setSleepScoreLiveDataBatch(@NotNull MutableLiveData<SleepScoreDataModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setTotalAwakeTime(int i) {
        this.x = i;
    }

    public final void setTotalDeepTime(int i) {
        this.y = i;
    }

    public final void setTotalSleepData(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.v = mutableLiveData;
    }

    public final void setTotalSleepHour(int i) {
        this.t = i;
    }

    public final void setTotalSleepTime(int i) {
        this.w = i;
    }
}
