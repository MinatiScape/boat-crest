package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils;
import com.coveiot.khtouchdb.activities.EntityTGWorkoutRecord;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutEventBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutFootBallAvgPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutGpsBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutHeartRateBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutKeepTrackBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutKeepTrackItemBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRealTimeDataBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRealTimeDataItemBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRowingBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutSwimBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutSwimItemBean;
import com.touchgui.sdk.bean.TGFootballAvgPace;
import com.touchgui.sdk.bean.TGKeepTrack;
import com.touchgui.sdk.bean.TGSportRealTimeData;
import com.touchgui.sdk.bean.TGSportRecord;
import com.touchgui.sdk.bean.TGSyncSwim;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchActivityFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3376a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchActivityFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchActivityFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchActivityFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3377a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchActivityFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchActivityFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchActivityFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3377a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchActivityFormatter(Context context) {
        this.f3376a = context;
    }

    public /* synthetic */ TouchActivityFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityTGWorkoutRecord convertTGWorkoutRecordToEntity(@NotNull TGWorkoutRecord tgWorkoutRecord) {
        List<TGWorkoutRecord.HeartRate> hearts;
        List<TGSportRecord.HeartRateItem> hearts2;
        Intrinsics.checkNotNullParameter(tgWorkoutRecord, "tgWorkoutRecord");
        if (tgWorkoutRecord.getSummary() != null) {
            String date = tgWorkoutRecord.getSummary().getDate().toString();
            Intrinsics.checkNotNullExpressionValue(date, "tgWorkoutRecord.summary.date.toString()");
            EntityTGWorkoutRecord entityTGWorkoutRecord = new EntityTGWorkoutRecord(date, BleApiManager.getInstance(this.f3376a).getBleApi().getMacAddress());
            entityTGWorkoutRecord.setType(tgWorkoutRecord.getSummary().getType());
            entityTGWorkoutRecord.setStep(tgWorkoutRecord.getSummary().getStep());
            entityTGWorkoutRecord.setDuration(tgWorkoutRecord.getSummary().getDuration());
            entityTGWorkoutRecord.setCalories(tgWorkoutRecord.getSummary().getCalories());
            entityTGWorkoutRecord.setDistance(tgWorkoutRecord.getSummary().getDistance());
            entityTGWorkoutRecord.setAvgHr(tgWorkoutRecord.getSummary().getAvgHr());
            entityTGWorkoutRecord.setMaxHr(tgWorkoutRecord.getSummary().getMaxHr());
            entityTGWorkoutRecord.setMinHr(tgWorkoutRecord.getSummary().getMinHr());
            entityTGWorkoutRecord.setRelax(tgWorkoutRecord.getSummary().getRelax());
            entityTGWorkoutRecord.setWarmUp(tgWorkoutRecord.getSummary().getWarmUp());
            entityTGWorkoutRecord.setFatBurning(tgWorkoutRecord.getSummary().getFatBurning());
            entityTGWorkoutRecord.setAerobicExercise(tgWorkoutRecord.getSummary().getAerobicExercise());
            entityTGWorkoutRecord.setAnaerobicExercise(tgWorkoutRecord.getSummary().getAnaerobicExercise());
            entityTGWorkoutRecord.setExtremeExercise(tgWorkoutRecord.getSummary().getExtremeExercise());
            entityTGWorkoutRecord.setAvgStrideFrequency(tgWorkoutRecord.getSummary().getAvgStrideFrequency());
            entityTGWorkoutRecord.setAvgStrideLength(tgWorkoutRecord.getSummary().getAvgStrideLength());
            entityTGWorkoutRecord.setAvgSpeed(tgWorkoutRecord.getSummary().getAvgSpeed());
            entityTGWorkoutRecord.setMaxSpeed(tgWorkoutRecord.getSummary().getMaxSpeed());
            entityTGWorkoutRecord.setMinSpeed(tgWorkoutRecord.getSummary().getMinSpeed());
            entityTGWorkoutRecord.setAvgPaceSecs(tgWorkoutRecord.getSummary().getAvgPaceSecs());
            entityTGWorkoutRecord.setMaxPace(tgWorkoutRecord.getSummary().getMaxPace());
            entityTGWorkoutRecord.setMinPace(tgWorkoutRecord.getSummary().getMinPace());
            entityTGWorkoutRecord.setPaddleNum(tgWorkoutRecord.getSummary().getPaddleNum());
            entityTGWorkoutRecord.setPaddleFrq(tgWorkoutRecord.getSummary().getPaddleFrq());
            entityTGWorkoutRecord.setBoxingNum(tgWorkoutRecord.getSummary().getBoxingNum());
            entityTGWorkoutRecord.setAvgSkipFrq(tgWorkoutRecord.getSummary().getAvgSkipFrq());
            entityTGWorkoutRecord.setSkipNum(tgWorkoutRecord.getSummary().getSkipNum());
            entityTGWorkoutRecord.setDumbbellNum(tgWorkoutRecord.getSummary().getDumbbellNum());
            if (BleApiManager.getInstance(this.f3376a).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
                if (tgWorkoutRecord.getSummary().getHearts() != null) {
                    Intrinsics.checkNotNull(tgWorkoutRecord.getSummary().getHearts());
                    if (!hearts2.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        List<TGSportRecord.HeartRateItem> hearts3 = tgWorkoutRecord.getSummary().getHearts();
                        Intrinsics.checkNotNull(hearts3);
                        int size = hearts3.size();
                        for (int i = 0; i < size; i++) {
                            KHTGWorkoutHeartRateBean kHTGWorkoutHeartRateBean = new KHTGWorkoutHeartRateBean();
                            List<TGSportRecord.HeartRateItem> hearts4 = tgWorkoutRecord.getSummary().getHearts();
                            Intrinsics.checkNotNull(hearts4);
                            kHTGWorkoutHeartRateBean.setValue(hearts4.get(i).getHeartrate());
                            arrayList.add(kHTGWorkoutHeartRateBean);
                        }
                        entityTGWorkoutRecord.setHeartRateItems(arrayList);
                    }
                }
            } else if (tgWorkoutRecord.getHearts() != null) {
                Intrinsics.checkNotNull(tgWorkoutRecord.getHearts());
                if (!hearts.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    List<TGWorkoutRecord.HeartRate> hearts5 = tgWorkoutRecord.getHearts();
                    Intrinsics.checkNotNull(hearts5);
                    int size2 = hearts5.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        KHTGWorkoutHeartRateBean kHTGWorkoutHeartRateBean2 = new KHTGWorkoutHeartRateBean();
                        List<TGWorkoutRecord.HeartRate> hearts6 = tgWorkoutRecord.getHearts();
                        Intrinsics.checkNotNull(hearts6);
                        kHTGWorkoutHeartRateBean2.setOffset(hearts6.get(i2).getOffset());
                        List<TGWorkoutRecord.HeartRate> hearts7 = tgWorkoutRecord.getHearts();
                        Intrinsics.checkNotNull(hearts7);
                        kHTGWorkoutHeartRateBean2.setValue(hearts7.get(i2).getValue());
                        arrayList2.add(kHTGWorkoutHeartRateBean2);
                    }
                    entityTGWorkoutRecord.setHeartRateItems(arrayList2);
                }
            }
            if (tgWorkoutRecord.getEvents() != null) {
                ArrayList arrayList3 = new ArrayList();
                List<TGWorkoutRecord.Event> events = tgWorkoutRecord.getEvents();
                Intrinsics.checkNotNull(events);
                int size3 = events.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    KHTGWorkoutEventBean kHTGWorkoutEventBean = new KHTGWorkoutEventBean();
                    List<TGWorkoutRecord.Event> events2 = tgWorkoutRecord.getEvents();
                    Intrinsics.checkNotNull(events2);
                    kHTGWorkoutEventBean.setOffset(events2.get(i3).getOffset());
                    List<TGWorkoutRecord.Event> events3 = tgWorkoutRecord.getEvents();
                    Intrinsics.checkNotNull(events3);
                    kHTGWorkoutEventBean.setEventType(events3.get(i3).getEventType());
                    arrayList3.add(kHTGWorkoutEventBean);
                }
                entityTGWorkoutRecord.setEventItems(arrayList3);
            }
            if (tgWorkoutRecord.getPaceData() != null) {
                ArrayList arrayList4 = new ArrayList();
                List<TGWorkoutRecord.Pace> paceData = tgWorkoutRecord.getPaceData();
                Intrinsics.checkNotNull(paceData);
                int size4 = paceData.size();
                for (int i4 = 0; i4 < size4; i4++) {
                    KHTGWorkoutPaceBean kHTGWorkoutPaceBean = new KHTGWorkoutPaceBean();
                    List<TGWorkoutRecord.Pace> paceData2 = tgWorkoutRecord.getPaceData();
                    Intrinsics.checkNotNull(paceData2);
                    kHTGWorkoutPaceBean.setType(paceData2.get(i4).getType());
                    List<TGWorkoutRecord.Pace> paceData3 = tgWorkoutRecord.getPaceData();
                    Intrinsics.checkNotNull(paceData3);
                    kHTGWorkoutPaceBean.setOffset(paceData3.get(i4).getOffset());
                    List<TGWorkoutRecord.Pace> paceData4 = tgWorkoutRecord.getPaceData();
                    Intrinsics.checkNotNull(paceData4);
                    kHTGWorkoutPaceBean.setValue(paceData4.get(i4).getValue());
                    arrayList4.add(kHTGWorkoutPaceBean);
                }
                entityTGWorkoutRecord.setPaceItems(arrayList4);
            }
            if (tgWorkoutRecord.getRowingData() != null) {
                ArrayList arrayList5 = new ArrayList();
                List<TGWorkoutRecord.Rowing> rowingData = tgWorkoutRecord.getRowingData();
                Intrinsics.checkNotNull(rowingData);
                int size5 = rowingData.size();
                for (int i5 = 0; i5 < size5; i5++) {
                    KHTGWorkoutRowingBean kHTGWorkoutRowingBean = new KHTGWorkoutRowingBean();
                    List<TGWorkoutRecord.Rowing> rowingData2 = tgWorkoutRecord.getRowingData();
                    Intrinsics.checkNotNull(rowingData2);
                    kHTGWorkoutRowingBean.setOffset(rowingData2.get(i5).getOffset());
                    List<TGWorkoutRecord.Rowing> rowingData3 = tgWorkoutRecord.getRowingData();
                    Intrinsics.checkNotNull(rowingData3);
                    kHTGWorkoutRowingBean.setStrokeFrq(rowingData3.get(i5).getStrokeFrq());
                    arrayList5.add(kHTGWorkoutRowingBean);
                }
                entityTGWorkoutRecord.setRowingItems(arrayList5);
            }
            if (tgWorkoutRecord.getGpsData() != null) {
                ArrayList arrayList6 = new ArrayList();
                List<? extends TGWorkoutRecord.Gps> gpsData = tgWorkoutRecord.getGpsData();
                Intrinsics.checkNotNull(gpsData);
                int size6 = gpsData.size();
                for (int i6 = 0; i6 < size6; i6++) {
                    KHTGWorkoutGpsBean kHTGWorkoutGpsBean = new KHTGWorkoutGpsBean();
                    List<? extends TGWorkoutRecord.Gps> gpsData2 = tgWorkoutRecord.getGpsData();
                    Intrinsics.checkNotNull(gpsData2);
                    kHTGWorkoutGpsBean.setOffset(gpsData2.get(i6).getOffset());
                    List<? extends TGWorkoutRecord.Gps> gpsData3 = tgWorkoutRecord.getGpsData();
                    Intrinsics.checkNotNull(gpsData3);
                    kHTGWorkoutGpsBean.setLongitude(gpsData3.get(i6).getLongitude());
                    List<? extends TGWorkoutRecord.Gps> gpsData4 = tgWorkoutRecord.getGpsData();
                    Intrinsics.checkNotNull(gpsData4);
                    kHTGWorkoutGpsBean.setLatitude(gpsData4.get(i6).getLatitude());
                    List<? extends TGWorkoutRecord.Gps> gpsData5 = tgWorkoutRecord.getGpsData();
                    Intrinsics.checkNotNull(gpsData5);
                    kHTGWorkoutGpsBean.setAltitude(gpsData5.get(i6).getAltitude());
                    List<? extends TGWorkoutRecord.Gps> gpsData6 = tgWorkoutRecord.getGpsData();
                    Intrinsics.checkNotNull(gpsData6);
                    kHTGWorkoutGpsBean.setSpeed(gpsData6.get(i6).getSpeed());
                    arrayList6.add(kHTGWorkoutGpsBean);
                }
                entityTGWorkoutRecord.setGpsData(arrayList6);
            }
            if (tgWorkoutRecord.getSwimData() != null) {
                KHTGWorkoutSwimBean kHTGWorkoutSwimBean = new KHTGWorkoutSwimBean();
                TGSyncSwim swimData = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData);
                kHTGWorkoutSwimBean.setOffset(swimData.getOffset());
                TGSyncSwim swimData2 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData2);
                kHTGWorkoutSwimBean.setHaveMoreData(swimData2.isHaveMoreData());
                TGSyncSwim swimData3 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData3);
                kHTGWorkoutSwimBean.setDate(swimData3.getDate().toString());
                TGSyncSwim swimData4 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData4);
                kHTGWorkoutSwimBean.setType(swimData4.getType());
                TGSyncSwim swimData5 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData5);
                kHTGWorkoutSwimBean.setCalories(swimData5.getCalories());
                TGSyncSwim swimData6 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData6);
                kHTGWorkoutSwimBean.setDistance(swimData6.getDistance());
                TGSyncSwim swimData7 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData7);
                kHTGWorkoutSwimBean.setConfirmDistance(swimData7.getConfirmDistance());
                TGSyncSwim swimData8 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData8);
                kHTGWorkoutSwimBean.setTrips(swimData8.getTrips());
                TGSyncSwim swimData9 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData9);
                kHTGWorkoutSwimBean.setDuration(swimData9.getDuration());
                TGSyncSwim swimData10 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData10);
                kHTGWorkoutSwimBean.setAvgSwolf(swimData10.getAvgSwolf());
                TGSyncSwim swimData11 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData11);
                kHTGWorkoutSwimBean.setTotalStrokes(swimData11.getTotalStrokes());
                TGSyncSwim swimData12 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData12);
                kHTGWorkoutSwimBean.setPosture(swimData12.getPosture());
                TGSyncSwim swimData13 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData13);
                kHTGWorkoutSwimBean.setPoolDistance(swimData13.getAvgSwolf());
                TGSyncSwim swimData14 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData14);
                kHTGWorkoutSwimBean.setAvgArmpull(swimData14.getTotalStrokes());
                TGSyncSwim swimData15 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData15);
                kHTGWorkoutSwimBean.setMaxArmpull(swimData15.getPosture());
                TGSyncSwim swimData16 = tgWorkoutRecord.getSwimData();
                Intrinsics.checkNotNull(swimData16);
                if (swimData16.getItems() != null) {
                    ArrayList arrayList7 = new ArrayList();
                    TGSyncSwim swimData17 = tgWorkoutRecord.getSwimData();
                    Intrinsics.checkNotNull(swimData17);
                    List<TGSyncSwim.ItemBean> items = swimData17.getItems();
                    Intrinsics.checkNotNull(items);
                    int size7 = items.size();
                    for (int i7 = 0; i7 < size7; i7++) {
                        KHTGWorkoutSwimItemBean kHTGWorkoutSwimItemBean = new KHTGWorkoutSwimItemBean();
                        TGSyncSwim swimData18 = tgWorkoutRecord.getSwimData();
                        Intrinsics.checkNotNull(swimData18);
                        List<TGSyncSwim.ItemBean> items2 = swimData18.getItems();
                        Intrinsics.checkNotNull(items2);
                        kHTGWorkoutSwimItemBean.setSwolf(items2.get(i7).getSwolf());
                        TGSyncSwim swimData19 = tgWorkoutRecord.getSwimData();
                        Intrinsics.checkNotNull(swimData19);
                        List<TGSyncSwim.ItemBean> items3 = swimData19.getItems();
                        Intrinsics.checkNotNull(items3);
                        kHTGWorkoutSwimItemBean.setPosture(items3.get(i7).getPosture());
                        TGSyncSwim swimData20 = tgWorkoutRecord.getSwimData();
                        Intrinsics.checkNotNull(swimData20);
                        List<TGSyncSwim.ItemBean> items4 = swimData20.getItems();
                        Intrinsics.checkNotNull(items4);
                        kHTGWorkoutSwimItemBean.setStrokesNum(items4.get(i7).getStrokesNum());
                        TGSyncSwim swimData21 = tgWorkoutRecord.getSwimData();
                        Intrinsics.checkNotNull(swimData21);
                        List<TGSyncSwim.ItemBean> items5 = swimData21.getItems();
                        Intrinsics.checkNotNull(items5);
                        kHTGWorkoutSwimItemBean.setDuration(items5.get(i7).getDuration());
                        TGSyncSwim swimData22 = tgWorkoutRecord.getSwimData();
                        Intrinsics.checkNotNull(swimData22);
                        List<TGSyncSwim.ItemBean> items6 = swimData22.getItems();
                        Intrinsics.checkNotNull(items6);
                        kHTGWorkoutSwimItemBean.setDistance(items6.get(i7).getDistance());
                        arrayList7.add(kHTGWorkoutSwimItemBean);
                    }
                    KHTGWorkoutSwimBean swimData23 = entityTGWorkoutRecord.getSwimData();
                    Intrinsics.checkNotNull(swimData23);
                    swimData23.setSwimItemBeans(arrayList7);
                }
                entityTGWorkoutRecord.setSwimData(kHTGWorkoutSwimBean);
            }
            if (tgWorkoutRecord.getFootballFieldGpsData() != null) {
                ArrayList arrayList8 = new ArrayList();
                List<? extends TGWorkoutRecord.Gps> footballFieldGpsData = tgWorkoutRecord.getFootballFieldGpsData();
                Intrinsics.checkNotNull(footballFieldGpsData);
                int size8 = footballFieldGpsData.size();
                for (int i8 = 0; i8 < size8; i8++) {
                    KHTGWorkoutGpsBean kHTGWorkoutGpsBean2 = new KHTGWorkoutGpsBean();
                    List<? extends TGWorkoutRecord.Gps> footballFieldGpsData2 = tgWorkoutRecord.getFootballFieldGpsData();
                    Intrinsics.checkNotNull(footballFieldGpsData2);
                    kHTGWorkoutGpsBean2.setOffset(footballFieldGpsData2.get(i8).getOffset());
                    List<? extends TGWorkoutRecord.Gps> footballFieldGpsData3 = tgWorkoutRecord.getFootballFieldGpsData();
                    Intrinsics.checkNotNull(footballFieldGpsData3);
                    kHTGWorkoutGpsBean2.setLongitude(footballFieldGpsData3.get(i8).getLongitude());
                    List<? extends TGWorkoutRecord.Gps> footballFieldGpsData4 = tgWorkoutRecord.getFootballFieldGpsData();
                    Intrinsics.checkNotNull(footballFieldGpsData4);
                    kHTGWorkoutGpsBean2.setLatitude(footballFieldGpsData4.get(i8).getLatitude());
                    List<? extends TGWorkoutRecord.Gps> footballFieldGpsData5 = tgWorkoutRecord.getFootballFieldGpsData();
                    Intrinsics.checkNotNull(footballFieldGpsData5);
                    kHTGWorkoutGpsBean2.setAltitude(footballFieldGpsData5.get(i8).getAltitude());
                    List<? extends TGWorkoutRecord.Gps> footballFieldGpsData6 = tgWorkoutRecord.getFootballFieldGpsData();
                    Intrinsics.checkNotNull(footballFieldGpsData6);
                    kHTGWorkoutGpsBean2.setSpeed(footballFieldGpsData6.get(i8).getSpeed());
                    arrayList8.add(kHTGWorkoutGpsBean2);
                }
                entityTGWorkoutRecord.setFootballFieldGpsData(arrayList8);
            }
            if (tgWorkoutRecord.getFootballAvgPace() != null) {
                KHTGWorkoutFootBallAvgPaceBean kHTGWorkoutFootBallAvgPaceBean = new KHTGWorkoutFootBallAvgPaceBean();
                TGFootballAvgPace footballAvgPace = tgWorkoutRecord.getFootballAvgPace();
                Intrinsics.checkNotNull(footballAvgPace);
                kHTGWorkoutFootBallAvgPaceBean.setOffset(footballAvgPace.getOffset());
                TGFootballAvgPace footballAvgPace2 = tgWorkoutRecord.getFootballAvgPace();
                Intrinsics.checkNotNull(footballAvgPace2);
                kHTGWorkoutFootBallAvgPaceBean.setHaveMoreData(footballAvgPace2.isHaveMoreData());
                TGFootballAvgPace footballAvgPace3 = tgWorkoutRecord.getFootballAvgPace();
                Intrinsics.checkNotNull(footballAvgPace3);
                kHTGWorkoutFootBallAvgPaceBean.setDate(footballAvgPace3.getDate().toString());
                TGFootballAvgPace footballAvgPace4 = tgWorkoutRecord.getFootballAvgPace();
                Intrinsics.checkNotNull(footballAvgPace4);
                if (footballAvgPace4.getItems() != null) {
                    ArrayList arrayList9 = new ArrayList();
                    TGFootballAvgPace footballAvgPace5 = tgWorkoutRecord.getFootballAvgPace();
                    Intrinsics.checkNotNull(footballAvgPace5);
                    List<Integer> items7 = footballAvgPace5.getItems();
                    Intrinsics.checkNotNull(items7);
                    int size9 = items7.size();
                    for (int i9 = 0; i9 < size9; i9++) {
                        TGFootballAvgPace footballAvgPace6 = tgWorkoutRecord.getFootballAvgPace();
                        Intrinsics.checkNotNull(footballAvgPace6);
                        List<Integer> items8 = footballAvgPace6.getItems();
                        Intrinsics.checkNotNull(items8);
                        arrayList9.add(items8.get(i9));
                    }
                    KHTGWorkoutFootBallAvgPaceBean footballAvgPace7 = entityTGWorkoutRecord.getFootballAvgPace();
                    Intrinsics.checkNotNull(footballAvgPace7);
                    footballAvgPace7.setItems(arrayList9);
                }
                entityTGWorkoutRecord.setFootballAvgPace(kHTGWorkoutFootBallAvgPaceBean);
            }
            if (tgWorkoutRecord.getRealTimeData() != null) {
                KHTGWorkoutRealTimeDataBean kHTGWorkoutRealTimeDataBean = new KHTGWorkoutRealTimeDataBean();
                TGSportRealTimeData realTimeData = tgWorkoutRecord.getRealTimeData();
                Intrinsics.checkNotNull(realTimeData);
                kHTGWorkoutRealTimeDataBean.setOffset(realTimeData.getOffset());
                TGSportRealTimeData realTimeData2 = tgWorkoutRecord.getRealTimeData();
                Intrinsics.checkNotNull(realTimeData2);
                kHTGWorkoutRealTimeDataBean.setHaveMoreData(realTimeData2.isHaveMoreData());
                TGSportRealTimeData realTimeData3 = tgWorkoutRecord.getRealTimeData();
                Intrinsics.checkNotNull(realTimeData3);
                kHTGWorkoutRealTimeDataBean.setDate(realTimeData3.getDate().toString());
                TGSportRealTimeData realTimeData4 = tgWorkoutRecord.getRealTimeData();
                Intrinsics.checkNotNull(realTimeData4);
                if (realTimeData4.getItems() != null) {
                    ArrayList arrayList10 = new ArrayList();
                    TGSportRealTimeData realTimeData5 = tgWorkoutRecord.getRealTimeData();
                    Intrinsics.checkNotNull(realTimeData5);
                    List<TGSportRealTimeData.ItemBean> items9 = realTimeData5.getItems();
                    Intrinsics.checkNotNull(items9);
                    int size10 = items9.size();
                    for (int i10 = 0; i10 < size10; i10++) {
                        KHTGWorkoutRealTimeDataItemBean kHTGWorkoutRealTimeDataItemBean = new KHTGWorkoutRealTimeDataItemBean();
                        TGSportRealTimeData realTimeData6 = tgWorkoutRecord.getRealTimeData();
                        Intrinsics.checkNotNull(realTimeData6);
                        List<TGSportRealTimeData.ItemBean> items10 = realTimeData6.getItems();
                        Intrinsics.checkNotNull(items10);
                        kHTGWorkoutRealTimeDataItemBean.setHeartRate(items10.get(i10).getHeartRate());
                        TGSportRealTimeData realTimeData7 = tgWorkoutRecord.getRealTimeData();
                        Intrinsics.checkNotNull(realTimeData7);
                        List<TGSportRealTimeData.ItemBean> items11 = realTimeData7.getItems();
                        Intrinsics.checkNotNull(items11);
                        kHTGWorkoutRealTimeDataItemBean.setPace(items11.get(i10).getPace());
                        TGSportRealTimeData realTimeData8 = tgWorkoutRecord.getRealTimeData();
                        Intrinsics.checkNotNull(realTimeData8);
                        List<TGSportRealTimeData.ItemBean> items12 = realTimeData8.getItems();
                        Intrinsics.checkNotNull(items12);
                        kHTGWorkoutRealTimeDataItemBean.setSpeed(items12.get(i10).getSpeed());
                        TGSportRealTimeData realTimeData9 = tgWorkoutRecord.getRealTimeData();
                        Intrinsics.checkNotNull(realTimeData9);
                        List<TGSportRealTimeData.ItemBean> items13 = realTimeData9.getItems();
                        Intrinsics.checkNotNull(items13);
                        kHTGWorkoutRealTimeDataItemBean.setSwolf(items13.get(i10).getSwolf());
                        arrayList10.add(kHTGWorkoutRealTimeDataItemBean);
                    }
                    KHTGWorkoutRealTimeDataBean realTimeData10 = entityTGWorkoutRecord.getRealTimeData();
                    Intrinsics.checkNotNull(realTimeData10);
                    realTimeData10.setItems(arrayList10);
                }
                entityTGWorkoutRecord.setRealTimeData(kHTGWorkoutRealTimeDataBean);
            }
            if (tgWorkoutRecord.getKeepTrack() != null) {
                KHTGWorkoutKeepTrackBean kHTGWorkoutKeepTrackBean = new KHTGWorkoutKeepTrackBean();
                TGKeepTrack keepTrack = tgWorkoutRecord.getKeepTrack();
                Intrinsics.checkNotNull(keepTrack);
                kHTGWorkoutKeepTrackBean.setOffset(keepTrack.getOffset());
                TGKeepTrack keepTrack2 = tgWorkoutRecord.getKeepTrack();
                Intrinsics.checkNotNull(keepTrack2);
                kHTGWorkoutKeepTrackBean.setHaveMoreData(keepTrack2.isHaveMoreData());
                TGKeepTrack keepTrack3 = tgWorkoutRecord.getKeepTrack();
                Intrinsics.checkNotNull(keepTrack3);
                kHTGWorkoutKeepTrackBean.setDate(keepTrack3.getDate().toString());
                TGKeepTrack keepTrack4 = tgWorkoutRecord.getKeepTrack();
                Intrinsics.checkNotNull(keepTrack4);
                if (keepTrack4.getItems() != null) {
                    ArrayList arrayList11 = new ArrayList();
                    TGKeepTrack keepTrack5 = tgWorkoutRecord.getKeepTrack();
                    Intrinsics.checkNotNull(keepTrack5);
                    List<TGKeepTrack.ItemBean> items14 = keepTrack5.getItems();
                    Intrinsics.checkNotNull(items14);
                    int size11 = items14.size();
                    for (int i11 = 0; i11 < size11; i11++) {
                        KHTGWorkoutKeepTrackItemBean kHTGWorkoutKeepTrackItemBean = new KHTGWorkoutKeepTrackItemBean();
                        TGKeepTrack keepTrack6 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack6);
                        List<TGKeepTrack.ItemBean> items15 = keepTrack6.getItems();
                        Intrinsics.checkNotNull(items15);
                        kHTGWorkoutKeepTrackItemBean.setOffset(items15.get(i11).getOffset());
                        TGKeepTrack keepTrack7 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack7);
                        List<TGKeepTrack.ItemBean> items16 = keepTrack7.getItems();
                        Intrinsics.checkNotNull(items16);
                        kHTGWorkoutKeepTrackItemBean.setLongitude(items16.get(i11).getLongitude());
                        TGKeepTrack keepTrack8 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack8);
                        List<TGKeepTrack.ItemBean> items17 = keepTrack8.getItems();
                        Intrinsics.checkNotNull(items17);
                        kHTGWorkoutKeepTrackItemBean.setLatitude(items17.get(i11).getLatitude());
                        TGKeepTrack keepTrack9 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack9);
                        List<TGKeepTrack.ItemBean> items18 = keepTrack9.getItems();
                        Intrinsics.checkNotNull(items18);
                        kHTGWorkoutKeepTrackItemBean.setHeartRate(items18.get(i11).getHeartRate());
                        TGKeepTrack keepTrack10 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack10);
                        List<TGKeepTrack.ItemBean> items19 = keepTrack10.getItems();
                        Intrinsics.checkNotNull(items19);
                        kHTGWorkoutKeepTrackItemBean.setSpeed(items19.get(i11).getSpeed());
                        TGKeepTrack keepTrack11 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack11);
                        List<TGKeepTrack.ItemBean> items20 = keepTrack11.getItems();
                        Intrinsics.checkNotNull(items20);
                        kHTGWorkoutKeepTrackItemBean.setDistance(items20.get(i11).getDistance());
                        TGKeepTrack keepTrack12 = tgWorkoutRecord.getKeepTrack();
                        Intrinsics.checkNotNull(keepTrack12);
                        List<TGKeepTrack.ItemBean> items21 = keepTrack12.getItems();
                        Intrinsics.checkNotNull(items21);
                        kHTGWorkoutKeepTrackItemBean.setSteps(items21.get(i11).getSteps());
                        arrayList11.add(kHTGWorkoutKeepTrackItemBean);
                    }
                    KHTGWorkoutKeepTrackBean keepTrack13 = entityTGWorkoutRecord.getKeepTrack();
                    Intrinsics.checkNotNull(keepTrack13);
                    keepTrack13.setItems(arrayList11);
                }
                entityTGWorkoutRecord.setKeepTrack(kHTGWorkoutKeepTrackBean);
            }
            entityTGWorkoutRecord.setTimeStamp(tgWorkoutRecord.getSummary().getDate().getTime());
            return entityTGWorkoutRecord;
        }
        return null;
    }

    @NotNull
    public final ActivityModeSummaryResponse getActivityModeSummaryData(@NotNull EntityTGWorkoutRecord activityResponseData) {
        List<KHTGWorkoutHeartRateBean> heartRateItems;
        Intrinsics.checkNotNullParameter(activityResponseData, "activityResponseData");
        ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
        activityModeSummaryResponse.setMacAddress(activityResponseData.getMacAddress());
        activityModeSummaryResponse.setActivityDuration(activityResponseData.getDuration());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(activityResponseData.getTimeStamp());
        if (activityResponseData.getDuration() != 0) {
            calendar.add(13, activityResponseData.getDuration());
        }
        activityModeSummaryResponse.setStartDateTime(Long.valueOf(activityResponseData.getTimeStamp()));
        activityModeSummaryResponse.setEndDateTime(Long.valueOf(calendar.getTimeInMillis()));
        activityModeSummaryResponse.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(activityResponseData.getTimeStamp()));
        activityModeSummaryResponse.setTotalSteps(activityResponseData.getStep());
        activityModeSummaryResponse.setTotalCalories(activityResponseData.getCalories());
        activityModeSummaryResponse.setTotalDistance(activityResponseData.getDistance() / 1000);
        TouchELXUtils touchELXUtils = TouchELXUtils.INSTANCE;
        activityModeSummaryResponse.setActivityMode(touchELXUtils.getActivityMode(activityResponseData.getType()));
        activityModeSummaryResponse.setActivitySite(touchELXUtils.getActivitySite(activityResponseData.getType()));
        ArrayList arrayList = new ArrayList();
        if (activityResponseData.getHeartRateItems() != null) {
            Intrinsics.checkNotNull(activityResponseData.getHeartRateItems());
            if (!heartRateItems.isEmpty()) {
                List<KHTGWorkoutHeartRateBean> heartRateItems2 = activityResponseData.getHeartRateItems();
                Intrinsics.checkNotNull(heartRateItems2);
                int size = heartRateItems2.size();
                for (int i = 0; i < size; i++) {
                    ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTimeInMillis(activityResponseData.getTimeStamp());
                    if (BleApiManager.getInstance(this.f3376a).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
                        calendar2.add(12, i);
                    } else {
                        calendar2.add(13, i);
                    }
                    activityHeartRateSample.setHrTimeStamp(calendar2.getTimeInMillis());
                    activityHeartRateSample.setHrValue(heartRateItems2.get(i).getValue());
                    arrayList.add(activityHeartRateSample);
                }
            }
        }
        activityModeSummaryResponse.setHeartRateSampleList(arrayList);
        ArrayList arrayList2 = new ArrayList();
        List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
        Intrinsics.checkNotNull(heartRateSampleList);
        int size2 = heartRateSampleList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            List<ActivityHeartRateSample> heartRateSampleList2 = activityModeSummaryResponse.getHeartRateSampleList();
            Intrinsics.checkNotNull(heartRateSampleList2);
            arrayList2.add(Integer.valueOf(heartRateSampleList2.get(i2).getHrValue()));
        }
        if (arrayList2.size() > 0) {
            Integer minValueFromList = BleApiUtils.INSTANCE.getMinValueFromList(arrayList2);
            Intrinsics.checkNotNull(minValueFromList);
            activityModeSummaryResponse.setMinHeartRate(minValueFromList.intValue());
        }
        if (arrayList2.size() > 0) {
            Integer maxValueFromList = BleApiUtils.INSTANCE.getMaxValueFromList(arrayList2);
            Intrinsics.checkNotNull(maxValueFromList);
            activityModeSummaryResponse.setMaxHeartRate(maxValueFromList.intValue());
        }
        if (arrayList2.size() > 0) {
            Integer avgValueFromList = BleApiUtils.INSTANCE.getAvgValueFromList(arrayList2);
            Intrinsics.checkNotNull(avgValueFromList);
            activityModeSummaryResponse.setHeartRate(avgValueFromList.intValue());
        }
        activityModeSummaryResponse.setLowSamplingRate(1);
        activityModeSummaryResponse.setAvgStepFrequency(activityResponseData.getAvgStrideFrequency());
        if (BleApiManager.getInstance(this.f3376a).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            if (activityResponseData.getDistance() > 0) {
                activityModeSummaryResponse.setAvgSpeed((int) TouchELXUtils.INSTANCE.getAverageSpeed(activityResponseData.getDistance(), activityResponseData.getDuration()));
            } else {
                activityModeSummaryResponse.setAvgSpeed(activityResponseData.getAvgSpeed());
            }
            if (activityResponseData.getDistance() <= 0 && activityResponseData.getStep() <= 0) {
                activityModeSummaryResponse.setAvgStrideLength(activityResponseData.getAvgStrideLength());
            } else {
                activityModeSummaryResponse.setAvgStrideLength(TouchELXUtils.INSTANCE.getAverageStrideLength(activityResponseData.getDistance(), activityResponseData.getStep()));
            }
            activityModeSummaryResponse.setMaxSpeed(activityResponseData.getMaxSpeed());
        } else {
            activityModeSummaryResponse.setAvgSpeed(activityResponseData.getAvgSpeed());
            activityModeSummaryResponse.setMaxSpeed(activityResponseData.getMaxSpeed());
            activityModeSummaryResponse.setAvgStrideLength(activityResponseData.getAvgStrideLength());
        }
        activityModeSummaryResponse.setAvgPace(activityResponseData.getAvgPaceSecs());
        activityModeSummaryResponse.setMaxPace(activityResponseData.getMaxPace());
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        timeSpentHeartRateZone.setZone0TimeInSecs(activityResponseData.getWarmUp());
        timeSpentHeartRateZone.setZone1TimeInSecs(activityResponseData.getFatBurning());
        timeSpentHeartRateZone.setZone2TimeInSecs(activityResponseData.getAerobicExercise());
        timeSpentHeartRateZone.setZone3TimeInSecs(activityResponseData.getAnaerobicExercise());
        timeSpentHeartRateZone.setZone4TimeInSecs(activityResponseData.getExtremeExercise());
        activityModeSummaryResponse.setHeartRateTimeZone(timeSpentHeartRateZone);
        if (activityResponseData.getType() == 57) {
            activityModeSummaryResponse.setFrequency(activityResponseData.getPaddleFrq());
            activityModeSummaryResponse.setCounter(activityResponseData.getPaddleNum());
        } else if (activityResponseData.getType() == 83) {
            activityModeSummaryResponse.setAvgFrequency(activityResponseData.getAvgSkipFrq());
            activityModeSummaryResponse.setCounter(activityResponseData.getSkipNum());
        }
        return activityModeSummaryResponse;
    }

    @NotNull
    public final Context getContext() {
        return this.f3376a;
    }
}
