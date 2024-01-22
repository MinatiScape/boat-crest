package com.coveiot.android.activitymodes.database.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.activitymodes.database.convertors.Covertors;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.models.FitnessChallengeStatsData;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public final class SessionDAO_Impl implements SessionDAO {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f2800a;
    public final EntityInsertionAdapter<EntityWorkoutSession> b;
    public final EntityInsertionAdapter<EntityWorkoutSessionSegment> c;
    public final EntityInsertionAdapter<WalkSample> d;
    public final EntityInsertionAdapter<PhysicalActivitySample> e;
    public final EntityInsertionAdapter<RunSample> f;
    public final EntityInsertionAdapter<BadmintonSample> g;
    public final EntityInsertionAdapter<BasketBallSample> h;
    public final EntityInsertionAdapter<CyclingSample> i;
    public final EntityInsertionAdapter<MeditationSample> j;
    public final EntityInsertionAdapter<DanceSample> k;
    public final EntityInsertionAdapter<FootballSample> l;
    public final EntityInsertionAdapter<HikingSample> m;
    public final EntityInsertionAdapter<TennisSample> n;
    public final EntityInsertionAdapter<WorkoutSample> o;
    public final EntityInsertionAdapter<YogaSample> p;
    public final EntityInsertionAdapter<ClimbingSample> q;
    public final EntityInsertionAdapter<TreadmillSample> r;
    public final EntityInsertionAdapter<SkippingSample> s;
    public final EntityInsertionAdapter<FreeExerciseSample> t;
    public final EntityInsertionAdapter<EllipticalSample> u;
    public final EntityInsertionAdapter<RowingMachineSample> v;
    public final EntityInsertionAdapter<ActivityDataSample> w;
    public final EntityDeletionOrUpdateAdapter<EntityWorkoutSession> x;
    public final EntityDeletionOrUpdateAdapter<EntityWorkoutSession> y;
    public final SharedSQLiteStatement z;

    /* loaded from: classes2.dex */
    public class a extends EntityInsertionAdapter<DanceSample> {
        public a(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DanceSample danceSample) {
            supportSQLiteStatement.bindLong(1, danceSample.get_id());
            if (danceSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, danceSample.getSeg_id());
            }
            String str = danceSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, danceSample.getStepCount());
            SampleData sampleData = danceSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `dance_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class a0 implements Callable<List<EntityWorkoutSession>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public a0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSession> call() throws Exception {
            boolean z;
            int i;
            Integer valueOf;
            Integer valueOf2;
            Float valueOf3;
            Float valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Float valueOf7;
            Float valueOf8;
            Integer valueOf9;
            int i2;
            Integer valueOf10;
            Integer valueOf11;
            Integer valueOf12;
            Integer valueOf13;
            Integer valueOf14;
            Float valueOf15;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i4 = columnIndexOrThrow;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i5 = i3;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i5));
                    i3 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow24 = i15;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i15;
                        z = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z);
                    columnIndexOrThrow23 = i14;
                    int i16 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i18));
                    int i19 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i19)));
                    int i20 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i20;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i20)));
                    int i21 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i = i21;
                        valueOf = null;
                    } else {
                        i = i21;
                        valueOf = Integer.valueOf(query.getInt(i22));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = Integer.valueOf(query.getInt(i23));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = Float.valueOf(query.getFloat(i24));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = Float.valueOf(query.getFloat(i25));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i31 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i31));
                    int i32 = columnIndexOrThrow41;
                    if (query.isNull(i32)) {
                        i2 = i31;
                        valueOf10 = null;
                    } else {
                        i2 = i31;
                        valueOf10 = Integer.valueOf(query.getInt(i32));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i33 = columnIndexOrThrow42;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i34 = columnIndexOrThrow43;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i35 = columnIndexOrThrow44;
                    if (query.isNull(i35)) {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i36 = columnIndexOrThrow45;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i37 = columnIndexOrThrow46;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = Float.valueOf(query.getFloat(i37));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i2;
                    columnIndexOrThrow41 = i32;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    columnIndexOrThrow28 = i19;
                    columnIndexOrThrow27 = i18;
                    int i38 = i;
                    columnIndexOrThrow31 = i22;
                    columnIndexOrThrow30 = i38;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class a1 extends EntityInsertionAdapter<BasketBallSample> {
        public a1(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, BasketBallSample basketBallSample) {
            supportSQLiteStatement.bindLong(1, basketBallSample.get_id());
            if (basketBallSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, basketBallSample.getSeg_id());
            }
            String str = basketBallSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, basketBallSample.getStepCount());
            SampleData sampleData = basketBallSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `basketball_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class b extends EntityInsertionAdapter<FootballSample> {
        public b(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, FootballSample footballSample) {
            supportSQLiteStatement.bindLong(1, footballSample.get_id());
            if (footballSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, footballSample.getSeg_id());
            }
            String str = footballSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, footballSample.getStepCount());
            SampleData sampleData = footballSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `football_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class b0 implements Callable<List<RunSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public b0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<RunSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        RunSample runSample = new RunSample();
                        runSample.set_id(query.getInt(columnIndexOrThrow));
                        runSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        runSample.sess_id = query.getString(columnIndexOrThrow3);
                        runSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        runSample.setSampleData(sampleData);
                        arrayList.add(runSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    RunSample runSample2 = new RunSample();
                    runSample2.set_id(query.getInt(columnIndexOrThrow));
                    runSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    runSample2.sess_id = query.getString(columnIndexOrThrow3);
                    runSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    runSample2.setSampleData(sampleData);
                    arrayList.add(runSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class b1 extends EntityInsertionAdapter<CyclingSample> {
        public b1(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, CyclingSample cyclingSample) {
            supportSQLiteStatement.bindLong(1, cyclingSample.get_id());
            if (cyclingSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, cyclingSample.getSeg_id());
            }
            String str = cyclingSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, cyclingSample.getStepCount());
            SampleData sampleData = cyclingSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `cycling_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class c extends EntityInsertionAdapter<HikingSample> {
        public c(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HikingSample hikingSample) {
            supportSQLiteStatement.bindLong(1, hikingSample.get_id());
            if (hikingSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, hikingSample.getSeg_id());
            }
            String str = hikingSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, hikingSample.getStepCount());
            SampleData sampleData = hikingSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hiking_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class c0 implements Callable<List<WalkSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public c0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<WalkSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        WalkSample walkSample = new WalkSample();
                        walkSample.set_id(query.getInt(columnIndexOrThrow));
                        walkSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        walkSample.sess_id = query.getString(columnIndexOrThrow3);
                        walkSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        walkSample.setSampleData(sampleData);
                        arrayList.add(walkSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    WalkSample walkSample2 = new WalkSample();
                    walkSample2.set_id(query.getInt(columnIndexOrThrow));
                    walkSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    walkSample2.sess_id = query.getString(columnIndexOrThrow3);
                    walkSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    walkSample2.setSampleData(sampleData);
                    arrayList.add(walkSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class c1 extends EntityInsertionAdapter<MeditationSample> {
        public c1(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, MeditationSample meditationSample) {
            supportSQLiteStatement.bindLong(1, meditationSample.get_id());
            if (meditationSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, meditationSample.getSeg_id());
            }
            String str = meditationSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, meditationSample.getStepCount());
            SampleData sampleData = meditationSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `meditation_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class d extends EntityInsertionAdapter<TennisSample> {
        public d(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, TennisSample tennisSample) {
            supportSQLiteStatement.bindLong(1, tennisSample.get_id());
            if (tennisSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, tennisSample.getSeg_id());
            }
            String str = tennisSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, tennisSample.getStepCount());
            SampleData sampleData = tennisSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `tennis_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class d0 implements Callable<List<PhysicalActivitySample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public d0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<PhysicalActivitySample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                        physicalActivitySample.set_id(query.getInt(columnIndexOrThrow));
                        physicalActivitySample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        physicalActivitySample.sess_id = query.getString(columnIndexOrThrow3);
                        physicalActivitySample.setStepCount(query.getInt(columnIndexOrThrow4));
                        physicalActivitySample.setSampleData(sampleData);
                        arrayList.add(physicalActivitySample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    PhysicalActivitySample physicalActivitySample2 = new PhysicalActivitySample();
                    physicalActivitySample2.set_id(query.getInt(columnIndexOrThrow));
                    physicalActivitySample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    physicalActivitySample2.sess_id = query.getString(columnIndexOrThrow3);
                    physicalActivitySample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    physicalActivitySample2.setSampleData(sampleData);
                    arrayList.add(physicalActivitySample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class e extends EntityInsertionAdapter<WorkoutSample> {
        public e(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkoutSample workoutSample) {
            supportSQLiteStatement.bindLong(1, workoutSample.get_id());
            if (workoutSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, workoutSample.getSeg_id());
            }
            String str = workoutSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, workoutSample.getStepCount());
            SampleData sampleData = workoutSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `workout_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class e0 implements Callable<List<BadmintonSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public e0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<BadmintonSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        BadmintonSample badmintonSample = new BadmintonSample();
                        badmintonSample.set_id(query.getInt(columnIndexOrThrow));
                        badmintonSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        badmintonSample.sess_id = query.getString(columnIndexOrThrow3);
                        badmintonSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        badmintonSample.setSampleData(sampleData);
                        arrayList.add(badmintonSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    BadmintonSample badmintonSample2 = new BadmintonSample();
                    badmintonSample2.set_id(query.getInt(columnIndexOrThrow));
                    badmintonSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    badmintonSample2.sess_id = query.getString(columnIndexOrThrow3);
                    badmintonSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    badmintonSample2.setSampleData(sampleData);
                    arrayList.add(badmintonSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class f extends EntityInsertionAdapter<YogaSample> {
        public f(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, YogaSample yogaSample) {
            supportSQLiteStatement.bindLong(1, yogaSample.get_id());
            if (yogaSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, yogaSample.getSeg_id());
            }
            String str = yogaSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, yogaSample.getStepCount());
            SampleData sampleData = yogaSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `yoga_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class f0 implements Callable<List<BasketBallSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public f0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<BasketBallSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        BasketBallSample basketBallSample = new BasketBallSample();
                        basketBallSample.set_id(query.getInt(columnIndexOrThrow));
                        basketBallSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        basketBallSample.sess_id = query.getString(columnIndexOrThrow3);
                        basketBallSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        basketBallSample.setSampleData(sampleData);
                        arrayList.add(basketBallSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    BasketBallSample basketBallSample2 = new BasketBallSample();
                    basketBallSample2.set_id(query.getInt(columnIndexOrThrow));
                    basketBallSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    basketBallSample2.sess_id = query.getString(columnIndexOrThrow3);
                    basketBallSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    basketBallSample2.setSampleData(sampleData);
                    arrayList.add(basketBallSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class g extends EntityInsertionAdapter<ClimbingSample> {
        public g(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, ClimbingSample climbingSample) {
            supportSQLiteStatement.bindLong(1, climbingSample.get_id());
            if (climbingSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, climbingSample.getSeg_id());
            }
            String str = climbingSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, climbingSample.getStepCount());
            SampleData sampleData = climbingSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `climbing_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class g0 extends EntityInsertionAdapter<WalkSample> {
        public g0(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, WalkSample walkSample) {
            supportSQLiteStatement.bindLong(1, walkSample.get_id());
            if (walkSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, walkSample.getSeg_id());
            }
            String str = walkSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, walkSample.getStepCount());
            SampleData sampleData = walkSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `walk_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class h extends EntityInsertionAdapter<TreadmillSample> {
        public h(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, TreadmillSample treadmillSample) {
            supportSQLiteStatement.bindLong(1, treadmillSample.get_id());
            if (treadmillSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, treadmillSample.getSeg_id());
            }
            String str = treadmillSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, treadmillSample.getStepCount());
            SampleData sampleData = treadmillSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `treadmill_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class h0 implements Callable<List<CyclingSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public h0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<CyclingSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        CyclingSample cyclingSample = new CyclingSample();
                        cyclingSample.set_id(query.getInt(columnIndexOrThrow));
                        cyclingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        cyclingSample.sess_id = query.getString(columnIndexOrThrow3);
                        cyclingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        cyclingSample.setSampleData(sampleData);
                        arrayList.add(cyclingSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    CyclingSample cyclingSample2 = new CyclingSample();
                    cyclingSample2.set_id(query.getInt(columnIndexOrThrow));
                    cyclingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    cyclingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    cyclingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    cyclingSample2.setSampleData(sampleData);
                    arrayList.add(cyclingSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class i extends EntityInsertionAdapter<SkippingSample> {
        public i(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SkippingSample skippingSample) {
            supportSQLiteStatement.bindLong(1, skippingSample.get_id());
            if (skippingSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, skippingSample.getSeg_id());
            }
            String str = skippingSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, skippingSample.getStepCount());
            SampleData sampleData = skippingSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `skipping_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class i0 implements Callable<List<MeditationSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public i0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<MeditationSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        MeditationSample meditationSample = new MeditationSample();
                        meditationSample.set_id(query.getInt(columnIndexOrThrow));
                        meditationSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        meditationSample.sess_id = query.getString(columnIndexOrThrow3);
                        meditationSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        meditationSample.setSampleData(sampleData);
                        arrayList.add(meditationSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    MeditationSample meditationSample2 = new MeditationSample();
                    meditationSample2.set_id(query.getInt(columnIndexOrThrow));
                    meditationSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    meditationSample2.sess_id = query.getString(columnIndexOrThrow3);
                    meditationSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    meditationSample2.setSampleData(sampleData);
                    arrayList.add(meditationSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class j extends EntityInsertionAdapter<FreeExerciseSample> {
        public j(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, FreeExerciseSample freeExerciseSample) {
            supportSQLiteStatement.bindLong(1, freeExerciseSample.get_id());
            if (freeExerciseSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, freeExerciseSample.getSeg_id());
            }
            String str = freeExerciseSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, freeExerciseSample.getStepCount());
            SampleData sampleData = freeExerciseSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `free_exercise_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class j0 implements Callable<List<DanceSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public j0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<DanceSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        DanceSample danceSample = new DanceSample();
                        danceSample.set_id(query.getInt(columnIndexOrThrow));
                        danceSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        danceSample.sess_id = query.getString(columnIndexOrThrow3);
                        danceSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        danceSample.setSampleData(sampleData);
                        arrayList.add(danceSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    DanceSample danceSample2 = new DanceSample();
                    danceSample2.set_id(query.getInt(columnIndexOrThrow));
                    danceSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    danceSample2.sess_id = query.getString(columnIndexOrThrow3);
                    danceSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    danceSample2.setSampleData(sampleData);
                    arrayList.add(danceSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class k extends EntityInsertionAdapter<EntityWorkoutSession> {
        public k(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityWorkoutSession entityWorkoutSession) {
            String str = entityWorkoutSession.session_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityWorkoutSession.getCategoryId() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindLong(2, entityWorkoutSession.getCategoryId().intValue());
            }
            if (entityWorkoutSession.getActivityId() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindLong(3, entityWorkoutSession.getActivityId().intValue());
            }
            if (entityWorkoutSession.getSerialNo() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityWorkoutSession.getSerialNo());
            }
            if (entityWorkoutSession.getClient_ref_id() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityWorkoutSession.getClient_ref_id());
            }
            if (entityWorkoutSession.getSession_date() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityWorkoutSession.getSession_date());
            }
            supportSQLiteStatement.bindLong(7, entityWorkoutSession.getStart_time());
            supportSQLiteStatement.bindLong(8, entityWorkoutSession.getEnd_time());
            if (entityWorkoutSession.getActivity_type() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, entityWorkoutSession.getActivity_type());
            }
            if (entityWorkoutSession.getTarget() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, entityWorkoutSession.getTarget());
            }
            if (entityWorkoutSession.getTarget_baseunit() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, entityWorkoutSession.getTarget_baseunit());
            }
            if (entityWorkoutSession.getIndoor_outdoor() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, entityWorkoutSession.getIndoor_outdoor());
            }
            supportSQLiteStatement.bindLong(13, entityWorkoutSession.getSession_duration());
            supportSQLiteStatement.bindLong(14, entityWorkoutSession.getSteps_sampling_rate());
            supportSQLiteStatement.bindLong(15, entityWorkoutSession.getHr_sampling_rate());
            supportSQLiteStatement.bindLong(16, entityWorkoutSession.getTotal_steps());
            supportSQLiteStatement.bindDouble(17, entityWorkoutSession.getTotal_calories());
            supportSQLiteStatement.bindLong(18, entityWorkoutSession.getTotal_distance());
            supportSQLiteStatement.bindLong(19, entityWorkoutSession.getMax_hr());
            supportSQLiteStatement.bindLong(20, entityWorkoutSession.getMin_hr());
            supportSQLiteStatement.bindLong(21, entityWorkoutSession.getAvg_hr());
            supportSQLiteStatement.bindDouble(22, entityWorkoutSession.getPace());
            supportSQLiteStatement.bindLong(23, entityWorkoutSession.getFatigue_level());
            supportSQLiteStatement.bindLong(24, entityWorkoutSession.getIssenttoserver() ? 1L : 0L);
            if (entityWorkoutSession.getSession_place() == null) {
                supportSQLiteStatement.bindNull(25);
            } else {
                supportSQLiteStatement.bindString(25, entityWorkoutSession.getSession_place());
            }
            if (entityWorkoutSession.getMoodaftersession() == null) {
                supportSQLiteStatement.bindNull(26);
            } else {
                supportSQLiteStatement.bindString(26, entityWorkoutSession.getMoodaftersession());
            }
            if (entityWorkoutSession.getFeedback() == null) {
                supportSQLiteStatement.bindNull(27);
            } else {
                supportSQLiteStatement.bindString(27, entityWorkoutSession.getFeedback());
            }
            String heartRateZoneRangesStringFrom = Covertors.getHeartRateZoneRangesStringFrom(entityWorkoutSession.getHrZoneRanges());
            if (heartRateZoneRangesStringFrom == null) {
                supportSQLiteStatement.bindNull(28);
            } else {
                supportSQLiteStatement.bindString(28, heartRateZoneRangesStringFrom);
            }
            String heartRateZoneTimeStringFrom = Covertors.getHeartRateZoneTimeStringFrom(entityWorkoutSession.getTimespent_per_heartratezone());
            if (heartRateZoneTimeStringFrom == null) {
                supportSQLiteStatement.bindNull(29);
            } else {
                supportSQLiteStatement.bindString(29, heartRateZoneTimeStringFrom);
            }
            if (entityWorkoutSession.getAppConnectivityCode() == null) {
                supportSQLiteStatement.bindNull(30);
            } else {
                supportSQLiteStatement.bindString(30, entityWorkoutSession.getAppConnectivityCode());
            }
            if (entityWorkoutSession.getAvgStepFrequency() == null) {
                supportSQLiteStatement.bindNull(31);
            } else {
                supportSQLiteStatement.bindLong(31, entityWorkoutSession.getAvgStepFrequency().intValue());
            }
            if (entityWorkoutSession.getMaxStepFrequency() == null) {
                supportSQLiteStatement.bindNull(32);
            } else {
                supportSQLiteStatement.bindLong(32, entityWorkoutSession.getMaxStepFrequency().intValue());
            }
            if (entityWorkoutSession.getAvgSpeed() == null) {
                supportSQLiteStatement.bindNull(33);
            } else {
                supportSQLiteStatement.bindDouble(33, entityWorkoutSession.getAvgSpeed().floatValue());
            }
            if (entityWorkoutSession.getMaxSpeed() == null) {
                supportSQLiteStatement.bindNull(34);
            } else {
                supportSQLiteStatement.bindDouble(34, entityWorkoutSession.getMaxSpeed().floatValue());
            }
            if (entityWorkoutSession.getAvgStrideLength() == null) {
                supportSQLiteStatement.bindNull(35);
            } else {
                supportSQLiteStatement.bindLong(35, entityWorkoutSession.getAvgStrideLength().intValue());
            }
            if (entityWorkoutSession.getMaxStrideLength() == null) {
                supportSQLiteStatement.bindNull(36);
            } else {
                supportSQLiteStatement.bindLong(36, entityWorkoutSession.getMaxStrideLength().intValue());
            }
            if (entityWorkoutSession.getAvgPace() == null) {
                supportSQLiteStatement.bindNull(37);
            } else {
                supportSQLiteStatement.bindDouble(37, entityWorkoutSession.getAvgPace().floatValue());
            }
            if (entityWorkoutSession.getMaxPace() == null) {
                supportSQLiteStatement.bindNull(38);
            } else {
                supportSQLiteStatement.bindDouble(38, entityWorkoutSession.getMaxPace().floatValue());
            }
            if (entityWorkoutSession.getTotalStrokes() == null) {
                supportSQLiteStatement.bindNull(39);
            } else {
                supportSQLiteStatement.bindLong(39, entityWorkoutSession.getTotalStrokes().intValue());
            }
            if (entityWorkoutSession.getSwimmingStyle() == null) {
                supportSQLiteStatement.bindNull(40);
            } else {
                supportSQLiteStatement.bindString(40, entityWorkoutSession.getSwimmingStyle());
            }
            if (entityWorkoutSession.getPoolLength() == null) {
                supportSQLiteStatement.bindNull(41);
            } else {
                supportSQLiteStatement.bindLong(41, entityWorkoutSession.getPoolLength().intValue());
            }
            if (entityWorkoutSession.getTotalLaps() == null) {
                supportSQLiteStatement.bindNull(42);
            } else {
                supportSQLiteStatement.bindLong(42, entityWorkoutSession.getTotalLaps().intValue());
            }
            if (entityWorkoutSession.getAvgSwolf() == null) {
                supportSQLiteStatement.bindNull(43);
            } else {
                supportSQLiteStatement.bindLong(43, entityWorkoutSession.getAvgSwolf().intValue());
            }
            if (entityWorkoutSession.getAvgStrokeFreq() == null) {
                supportSQLiteStatement.bindNull(44);
            } else {
                supportSQLiteStatement.bindLong(44, entityWorkoutSession.getAvgStrokeFreq().intValue());
            }
            if (entityWorkoutSession.getFromHAR() == null) {
                supportSQLiteStatement.bindNull(45);
            } else {
                supportSQLiteStatement.bindLong(45, entityWorkoutSession.getFromHAR().intValue());
            }
            if (entityWorkoutSession.getMinPace() == null) {
                supportSQLiteStatement.bindNull(46);
            } else {
                supportSQLiteStatement.bindDouble(46, entityWorkoutSession.getMinPace().floatValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR IGNORE INTO `workout_session` (`session_id`,`categoryId`,`activityId`,`serial_no`,`client_ref_id`,`date`,`start_time`,`end_time`,`activity_type`,`target`,`target_baseunit`,`indoor_outdoor`,`session_duration`,`steps_sampling_rate`,`hr_sampling_rate`,`total_steps`,`total_calories`,`total_distance`,`max_hr`,`min_hr`,`avg_hr`,`pace`,`fatigue_level`,`sent_to_server`,`session_place`,`mood_after_session`,`feedback`,`hrZoneRanges`,`timespent_per_heartratezone`,`appConnectivity`,`avgStepFrequency`,`maxStepFrequency`,`avgSpeed`,`maxSpeed`,`avgStrideLength`,`maxStrideLength`,`avgPace`,`maxPace`,`totalStrokes`,`swimmingStyle`,`poolLength`,`totalLaps`,`avgSwolf`,`avgStrokeFreq`,`isFromHAR`,`minPace`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class k0 implements Callable<List<FootballSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public k0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<FootballSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        FootballSample footballSample = new FootballSample();
                        footballSample.set_id(query.getInt(columnIndexOrThrow));
                        footballSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        footballSample.sess_id = query.getString(columnIndexOrThrow3);
                        footballSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        footballSample.setSampleData(sampleData);
                        arrayList.add(footballSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    FootballSample footballSample2 = new FootballSample();
                    footballSample2.set_id(query.getInt(columnIndexOrThrow));
                    footballSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    footballSample2.sess_id = query.getString(columnIndexOrThrow3);
                    footballSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    footballSample2.setSampleData(sampleData);
                    arrayList.add(footballSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class l extends EntityInsertionAdapter<EllipticalSample> {
        public l(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EllipticalSample ellipticalSample) {
            supportSQLiteStatement.bindLong(1, ellipticalSample.get_id());
            if (ellipticalSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, ellipticalSample.getSeg_id());
            }
            String str = ellipticalSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, ellipticalSample.getStepCount());
            SampleData sampleData = ellipticalSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `elliptical_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class l0 implements Callable<List<HikingSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public l0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<HikingSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        HikingSample hikingSample = new HikingSample();
                        hikingSample.set_id(query.getInt(columnIndexOrThrow));
                        hikingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        hikingSample.sess_id = query.getString(columnIndexOrThrow3);
                        hikingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        hikingSample.setSampleData(sampleData);
                        arrayList.add(hikingSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    HikingSample hikingSample2 = new HikingSample();
                    hikingSample2.set_id(query.getInt(columnIndexOrThrow));
                    hikingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    hikingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    hikingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    hikingSample2.setSampleData(sampleData);
                    arrayList.add(hikingSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class m extends EntityInsertionAdapter<RowingMachineSample> {
        public m(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, RowingMachineSample rowingMachineSample) {
            supportSQLiteStatement.bindLong(1, rowingMachineSample.get_id());
            if (rowingMachineSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, rowingMachineSample.getSeg_id());
            }
            String str = rowingMachineSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, rowingMachineSample.getStepCount());
            SampleData sampleData = rowingMachineSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `rowing_machine_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class m0 implements Callable<List<TennisSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public m0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<TennisSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        TennisSample tennisSample = new TennisSample();
                        tennisSample.set_id(query.getInt(columnIndexOrThrow));
                        tennisSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        tennisSample.sess_id = query.getString(columnIndexOrThrow3);
                        tennisSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        tennisSample.setSampleData(sampleData);
                        arrayList.add(tennisSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    TennisSample tennisSample2 = new TennisSample();
                    tennisSample2.set_id(query.getInt(columnIndexOrThrow));
                    tennisSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    tennisSample2.sess_id = query.getString(columnIndexOrThrow3);
                    tennisSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    tennisSample2.setSampleData(sampleData);
                    arrayList.add(tennisSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class n extends EntityInsertionAdapter<ActivityDataSample> {
        public n(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, ActivityDataSample activityDataSample) {
            supportSQLiteStatement.bindLong(1, activityDataSample.get_id());
            if (activityDataSample.getSegmentID() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, activityDataSample.getSegmentID());
            }
            String str = activityDataSample.sessionID;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            if (activityDataSample.getActivityType() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, activityDataSample.getActivityType());
            }
            supportSQLiteStatement.bindLong(5, activityDataSample.getTimeStamp());
            supportSQLiteStatement.bindLong(6, activityDataSample.getStepCount());
            supportSQLiteStatement.bindDouble(7, activityDataSample.getCalories());
            supportSQLiteStatement.bindLong(8, activityDataSample.getDistance());
            supportSQLiteStatement.bindLong(9, activityDataSample.getHrValue());
            supportSQLiteStatement.bindDouble(10, activityDataSample.getSpeedValue());
            supportSQLiteStatement.bindDouble(11, activityDataSample.getLatitude());
            supportSQLiteStatement.bindDouble(12, activityDataSample.getLongitude());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `activity_data_sample` (`_id`,`segmentID`,`sessionID`,`activityType`,`timestamp`,`step_count`,`calories`,`distance`,`hrValue`,`speedValue`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class n0 implements Callable<List<WorkoutSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public n0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<WorkoutSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        WorkoutSample workoutSample = new WorkoutSample();
                        workoutSample.set_id(query.getInt(columnIndexOrThrow));
                        workoutSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        workoutSample.sess_id = query.getString(columnIndexOrThrow3);
                        workoutSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        workoutSample.setSampleData(sampleData);
                        arrayList.add(workoutSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    WorkoutSample workoutSample2 = new WorkoutSample();
                    workoutSample2.set_id(query.getInt(columnIndexOrThrow));
                    workoutSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    workoutSample2.sess_id = query.getString(columnIndexOrThrow3);
                    workoutSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    workoutSample2.setSampleData(sampleData);
                    arrayList.add(workoutSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class o extends EntityDeletionOrUpdateAdapter<EntityWorkoutSession> {
        public o(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityWorkoutSession entityWorkoutSession) {
            String str = entityWorkoutSession.session_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM `workout_session` WHERE `session_id` = ?";
        }
    }

    /* loaded from: classes2.dex */
    public class o0 implements Callable<List<YogaSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public o0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<YogaSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        YogaSample yogaSample = new YogaSample();
                        yogaSample.set_id(query.getInt(columnIndexOrThrow));
                        yogaSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        yogaSample.sess_id = query.getString(columnIndexOrThrow3);
                        yogaSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        yogaSample.setSampleData(sampleData);
                        arrayList.add(yogaSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    YogaSample yogaSample2 = new YogaSample();
                    yogaSample2.set_id(query.getInt(columnIndexOrThrow));
                    yogaSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    yogaSample2.sess_id = query.getString(columnIndexOrThrow3);
                    yogaSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    yogaSample2.setSampleData(sampleData);
                    arrayList.add(yogaSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class p extends EntityDeletionOrUpdateAdapter<EntityWorkoutSession> {
        public p(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityWorkoutSession entityWorkoutSession) {
            String str = entityWorkoutSession.session_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityWorkoutSession.getCategoryId() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindLong(2, entityWorkoutSession.getCategoryId().intValue());
            }
            if (entityWorkoutSession.getActivityId() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindLong(3, entityWorkoutSession.getActivityId().intValue());
            }
            if (entityWorkoutSession.getSerialNo() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityWorkoutSession.getSerialNo());
            }
            if (entityWorkoutSession.getClient_ref_id() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityWorkoutSession.getClient_ref_id());
            }
            if (entityWorkoutSession.getSession_date() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityWorkoutSession.getSession_date());
            }
            supportSQLiteStatement.bindLong(7, entityWorkoutSession.getStart_time());
            supportSQLiteStatement.bindLong(8, entityWorkoutSession.getEnd_time());
            if (entityWorkoutSession.getActivity_type() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, entityWorkoutSession.getActivity_type());
            }
            if (entityWorkoutSession.getTarget() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, entityWorkoutSession.getTarget());
            }
            if (entityWorkoutSession.getTarget_baseunit() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, entityWorkoutSession.getTarget_baseunit());
            }
            if (entityWorkoutSession.getIndoor_outdoor() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, entityWorkoutSession.getIndoor_outdoor());
            }
            supportSQLiteStatement.bindLong(13, entityWorkoutSession.getSession_duration());
            supportSQLiteStatement.bindLong(14, entityWorkoutSession.getSteps_sampling_rate());
            supportSQLiteStatement.bindLong(15, entityWorkoutSession.getHr_sampling_rate());
            supportSQLiteStatement.bindLong(16, entityWorkoutSession.getTotal_steps());
            supportSQLiteStatement.bindDouble(17, entityWorkoutSession.getTotal_calories());
            supportSQLiteStatement.bindLong(18, entityWorkoutSession.getTotal_distance());
            supportSQLiteStatement.bindLong(19, entityWorkoutSession.getMax_hr());
            supportSQLiteStatement.bindLong(20, entityWorkoutSession.getMin_hr());
            supportSQLiteStatement.bindLong(21, entityWorkoutSession.getAvg_hr());
            supportSQLiteStatement.bindDouble(22, entityWorkoutSession.getPace());
            supportSQLiteStatement.bindLong(23, entityWorkoutSession.getFatigue_level());
            supportSQLiteStatement.bindLong(24, entityWorkoutSession.getIssenttoserver() ? 1L : 0L);
            if (entityWorkoutSession.getSession_place() == null) {
                supportSQLiteStatement.bindNull(25);
            } else {
                supportSQLiteStatement.bindString(25, entityWorkoutSession.getSession_place());
            }
            if (entityWorkoutSession.getMoodaftersession() == null) {
                supportSQLiteStatement.bindNull(26);
            } else {
                supportSQLiteStatement.bindString(26, entityWorkoutSession.getMoodaftersession());
            }
            if (entityWorkoutSession.getFeedback() == null) {
                supportSQLiteStatement.bindNull(27);
            } else {
                supportSQLiteStatement.bindString(27, entityWorkoutSession.getFeedback());
            }
            String heartRateZoneRangesStringFrom = Covertors.getHeartRateZoneRangesStringFrom(entityWorkoutSession.getHrZoneRanges());
            if (heartRateZoneRangesStringFrom == null) {
                supportSQLiteStatement.bindNull(28);
            } else {
                supportSQLiteStatement.bindString(28, heartRateZoneRangesStringFrom);
            }
            String heartRateZoneTimeStringFrom = Covertors.getHeartRateZoneTimeStringFrom(entityWorkoutSession.getTimespent_per_heartratezone());
            if (heartRateZoneTimeStringFrom == null) {
                supportSQLiteStatement.bindNull(29);
            } else {
                supportSQLiteStatement.bindString(29, heartRateZoneTimeStringFrom);
            }
            if (entityWorkoutSession.getAppConnectivityCode() == null) {
                supportSQLiteStatement.bindNull(30);
            } else {
                supportSQLiteStatement.bindString(30, entityWorkoutSession.getAppConnectivityCode());
            }
            if (entityWorkoutSession.getAvgStepFrequency() == null) {
                supportSQLiteStatement.bindNull(31);
            } else {
                supportSQLiteStatement.bindLong(31, entityWorkoutSession.getAvgStepFrequency().intValue());
            }
            if (entityWorkoutSession.getMaxStepFrequency() == null) {
                supportSQLiteStatement.bindNull(32);
            } else {
                supportSQLiteStatement.bindLong(32, entityWorkoutSession.getMaxStepFrequency().intValue());
            }
            if (entityWorkoutSession.getAvgSpeed() == null) {
                supportSQLiteStatement.bindNull(33);
            } else {
                supportSQLiteStatement.bindDouble(33, entityWorkoutSession.getAvgSpeed().floatValue());
            }
            if (entityWorkoutSession.getMaxSpeed() == null) {
                supportSQLiteStatement.bindNull(34);
            } else {
                supportSQLiteStatement.bindDouble(34, entityWorkoutSession.getMaxSpeed().floatValue());
            }
            if (entityWorkoutSession.getAvgStrideLength() == null) {
                supportSQLiteStatement.bindNull(35);
            } else {
                supportSQLiteStatement.bindLong(35, entityWorkoutSession.getAvgStrideLength().intValue());
            }
            if (entityWorkoutSession.getMaxStrideLength() == null) {
                supportSQLiteStatement.bindNull(36);
            } else {
                supportSQLiteStatement.bindLong(36, entityWorkoutSession.getMaxStrideLength().intValue());
            }
            if (entityWorkoutSession.getAvgPace() == null) {
                supportSQLiteStatement.bindNull(37);
            } else {
                supportSQLiteStatement.bindDouble(37, entityWorkoutSession.getAvgPace().floatValue());
            }
            if (entityWorkoutSession.getMaxPace() == null) {
                supportSQLiteStatement.bindNull(38);
            } else {
                supportSQLiteStatement.bindDouble(38, entityWorkoutSession.getMaxPace().floatValue());
            }
            if (entityWorkoutSession.getTotalStrokes() == null) {
                supportSQLiteStatement.bindNull(39);
            } else {
                supportSQLiteStatement.bindLong(39, entityWorkoutSession.getTotalStrokes().intValue());
            }
            if (entityWorkoutSession.getSwimmingStyle() == null) {
                supportSQLiteStatement.bindNull(40);
            } else {
                supportSQLiteStatement.bindString(40, entityWorkoutSession.getSwimmingStyle());
            }
            if (entityWorkoutSession.getPoolLength() == null) {
                supportSQLiteStatement.bindNull(41);
            } else {
                supportSQLiteStatement.bindLong(41, entityWorkoutSession.getPoolLength().intValue());
            }
            if (entityWorkoutSession.getTotalLaps() == null) {
                supportSQLiteStatement.bindNull(42);
            } else {
                supportSQLiteStatement.bindLong(42, entityWorkoutSession.getTotalLaps().intValue());
            }
            if (entityWorkoutSession.getAvgSwolf() == null) {
                supportSQLiteStatement.bindNull(43);
            } else {
                supportSQLiteStatement.bindLong(43, entityWorkoutSession.getAvgSwolf().intValue());
            }
            if (entityWorkoutSession.getAvgStrokeFreq() == null) {
                supportSQLiteStatement.bindNull(44);
            } else {
                supportSQLiteStatement.bindLong(44, entityWorkoutSession.getAvgStrokeFreq().intValue());
            }
            if (entityWorkoutSession.getFromHAR() == null) {
                supportSQLiteStatement.bindNull(45);
            } else {
                supportSQLiteStatement.bindLong(45, entityWorkoutSession.getFromHAR().intValue());
            }
            if (entityWorkoutSession.getMinPace() == null) {
                supportSQLiteStatement.bindNull(46);
            } else {
                supportSQLiteStatement.bindDouble(46, entityWorkoutSession.getMinPace().floatValue());
            }
            String str2 = entityWorkoutSession.session_id;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(47);
            } else {
                supportSQLiteStatement.bindString(47, str2);
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR REPLACE `workout_session` SET `session_id` = ?,`categoryId` = ?,`activityId` = ?,`serial_no` = ?,`client_ref_id` = ?,`date` = ?,`start_time` = ?,`end_time` = ?,`activity_type` = ?,`target` = ?,`target_baseunit` = ?,`indoor_outdoor` = ?,`session_duration` = ?,`steps_sampling_rate` = ?,`hr_sampling_rate` = ?,`total_steps` = ?,`total_calories` = ?,`total_distance` = ?,`max_hr` = ?,`min_hr` = ?,`avg_hr` = ?,`pace` = ?,`fatigue_level` = ?,`sent_to_server` = ?,`session_place` = ?,`mood_after_session` = ?,`feedback` = ?,`hrZoneRanges` = ?,`timespent_per_heartratezone` = ?,`appConnectivity` = ?,`avgStepFrequency` = ?,`maxStepFrequency` = ?,`avgSpeed` = ?,`maxSpeed` = ?,`avgStrideLength` = ?,`maxStrideLength` = ?,`avgPace` = ?,`maxPace` = ?,`totalStrokes` = ?,`swimmingStyle` = ?,`poolLength` = ?,`totalLaps` = ?,`avgSwolf` = ?,`avgStrokeFreq` = ?,`isFromHAR` = ?,`minPace` = ? WHERE `session_id` = ?";
        }
    }

    /* loaded from: classes2.dex */
    public class p0 implements Callable<List<SkippingSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public p0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<SkippingSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        SkippingSample skippingSample = new SkippingSample();
                        skippingSample.set_id(query.getInt(columnIndexOrThrow));
                        skippingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        skippingSample.sess_id = query.getString(columnIndexOrThrow3);
                        skippingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        skippingSample.setSampleData(sampleData);
                        arrayList.add(skippingSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    SkippingSample skippingSample2 = new SkippingSample();
                    skippingSample2.set_id(query.getInt(columnIndexOrThrow));
                    skippingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    skippingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    skippingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    skippingSample2.setSampleData(sampleData);
                    arrayList.add(skippingSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class q extends SharedSQLiteStatement {
        public q(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM workout_session";
        }
    }

    /* loaded from: classes2.dex */
    public class q0 implements Callable<List<ClimbingSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public q0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<ClimbingSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        ClimbingSample climbingSample = new ClimbingSample();
                        climbingSample.set_id(query.getInt(columnIndexOrThrow));
                        climbingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        climbingSample.sess_id = query.getString(columnIndexOrThrow3);
                        climbingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        climbingSample.setSampleData(sampleData);
                        arrayList.add(climbingSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    ClimbingSample climbingSample2 = new ClimbingSample();
                    climbingSample2.set_id(query.getInt(columnIndexOrThrow));
                    climbingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    climbingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    climbingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    climbingSample2.setSampleData(sampleData);
                    arrayList.add(climbingSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class r implements Callable<EntityWorkoutSession> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public r(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public EntityWorkoutSession call() throws Exception {
            EntityWorkoutSession entityWorkoutSession;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                return entityWorkoutSession;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class r0 extends EntityInsertionAdapter<PhysicalActivitySample> {
        public r0(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, PhysicalActivitySample physicalActivitySample) {
            supportSQLiteStatement.bindLong(1, physicalActivitySample.get_id());
            if (physicalActivitySample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, physicalActivitySample.getSeg_id());
            }
            String str = physicalActivitySample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, physicalActivitySample.getStepCount());
            SampleData sampleData = physicalActivitySample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `physical_activity_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class s implements Callable<List<EntityWorkoutSessionSegment>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public s(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSessionSegment> call() throws Exception {
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "segment_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "segment_duration");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
                    entityWorkoutSessionSegment.segment_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSessionSegment.setSess_id(query.getString(columnIndexOrThrow2));
                    entityWorkoutSessionSegment.setStart_time(query.getLong(columnIndexOrThrow3));
                    entityWorkoutSessionSegment.setEnd_time(query.getLong(columnIndexOrThrow4));
                    entityWorkoutSessionSegment.setSegment_duration(query.getInt(columnIndexOrThrow5));
                    arrayList.add(entityWorkoutSessionSegment);
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class s0 implements Callable<List<TreadmillSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public s0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<TreadmillSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        TreadmillSample treadmillSample = new TreadmillSample();
                        treadmillSample.set_id(query.getInt(columnIndexOrThrow));
                        treadmillSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        treadmillSample.sess_id = query.getString(columnIndexOrThrow3);
                        treadmillSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        treadmillSample.setSampleData(sampleData);
                        arrayList.add(treadmillSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    TreadmillSample treadmillSample2 = new TreadmillSample();
                    treadmillSample2.set_id(query.getInt(columnIndexOrThrow));
                    treadmillSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    treadmillSample2.sess_id = query.getString(columnIndexOrThrow3);
                    treadmillSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    treadmillSample2.setSampleData(sampleData);
                    arrayList.add(treadmillSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class t implements Callable<List<EntityWorkoutSession>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public t(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSession> call() throws Exception {
            boolean z;
            int i;
            Integer valueOf;
            Integer valueOf2;
            Float valueOf3;
            Float valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Float valueOf7;
            Float valueOf8;
            Integer valueOf9;
            int i2;
            Integer valueOf10;
            Integer valueOf11;
            Integer valueOf12;
            Integer valueOf13;
            Integer valueOf14;
            Float valueOf15;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i4 = columnIndexOrThrow;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i5 = i3;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i5));
                    i3 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow24 = i15;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i15;
                        z = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z);
                    columnIndexOrThrow23 = i14;
                    int i16 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i18));
                    int i19 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i19)));
                    int i20 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i20;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i20)));
                    int i21 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i = i21;
                        valueOf = null;
                    } else {
                        i = i21;
                        valueOf = Integer.valueOf(query.getInt(i22));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = Integer.valueOf(query.getInt(i23));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = Float.valueOf(query.getFloat(i24));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = Float.valueOf(query.getFloat(i25));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i31 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i31));
                    int i32 = columnIndexOrThrow41;
                    if (query.isNull(i32)) {
                        i2 = i31;
                        valueOf10 = null;
                    } else {
                        i2 = i31;
                        valueOf10 = Integer.valueOf(query.getInt(i32));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i33 = columnIndexOrThrow42;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i34 = columnIndexOrThrow43;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i35 = columnIndexOrThrow44;
                    if (query.isNull(i35)) {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i36 = columnIndexOrThrow45;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i37 = columnIndexOrThrow46;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = Float.valueOf(query.getFloat(i37));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i2;
                    columnIndexOrThrow41 = i32;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    columnIndexOrThrow28 = i19;
                    columnIndexOrThrow27 = i18;
                    int i38 = i;
                    columnIndexOrThrow31 = i22;
                    columnIndexOrThrow30 = i38;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class t0 implements Callable<List<SkippingSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public t0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<SkippingSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        SkippingSample skippingSample = new SkippingSample();
                        skippingSample.set_id(query.getInt(columnIndexOrThrow));
                        skippingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        skippingSample.sess_id = query.getString(columnIndexOrThrow3);
                        skippingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        skippingSample.setSampleData(sampleData);
                        arrayList.add(skippingSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    SkippingSample skippingSample2 = new SkippingSample();
                    skippingSample2.set_id(query.getInt(columnIndexOrThrow));
                    skippingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    skippingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    skippingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    skippingSample2.setSampleData(sampleData);
                    arrayList.add(skippingSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class u implements Callable<List<EntityWorkoutSession>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public u(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSession> call() throws Exception {
            boolean z;
            int i;
            Integer valueOf;
            Integer valueOf2;
            Float valueOf3;
            Float valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Float valueOf7;
            Float valueOf8;
            Integer valueOf9;
            int i2;
            Integer valueOf10;
            Integer valueOf11;
            Integer valueOf12;
            Integer valueOf13;
            Integer valueOf14;
            Float valueOf15;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i4 = columnIndexOrThrow;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i5 = i3;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i5));
                    i3 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow24 = i15;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i15;
                        z = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z);
                    columnIndexOrThrow23 = i14;
                    int i16 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i18));
                    int i19 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i19)));
                    int i20 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i20;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i20)));
                    int i21 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i = i21;
                        valueOf = null;
                    } else {
                        i = i21;
                        valueOf = Integer.valueOf(query.getInt(i22));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = Integer.valueOf(query.getInt(i23));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = Float.valueOf(query.getFloat(i24));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = Float.valueOf(query.getFloat(i25));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i31 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i31));
                    int i32 = columnIndexOrThrow41;
                    if (query.isNull(i32)) {
                        i2 = i31;
                        valueOf10 = null;
                    } else {
                        i2 = i31;
                        valueOf10 = Integer.valueOf(query.getInt(i32));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i33 = columnIndexOrThrow42;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i34 = columnIndexOrThrow43;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i35 = columnIndexOrThrow44;
                    if (query.isNull(i35)) {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i36 = columnIndexOrThrow45;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i37 = columnIndexOrThrow46;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = Float.valueOf(query.getFloat(i37));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i2;
                    columnIndexOrThrow41 = i32;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    columnIndexOrThrow28 = i19;
                    columnIndexOrThrow27 = i18;
                    int i38 = i;
                    columnIndexOrThrow31 = i22;
                    columnIndexOrThrow30 = i38;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class u0 implements Callable<List<FreeExerciseSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public u0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<FreeExerciseSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                        freeExerciseSample.set_id(query.getInt(columnIndexOrThrow));
                        freeExerciseSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        freeExerciseSample.sess_id = query.getString(columnIndexOrThrow3);
                        freeExerciseSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        freeExerciseSample.setSampleData(sampleData);
                        arrayList.add(freeExerciseSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    FreeExerciseSample freeExerciseSample2 = new FreeExerciseSample();
                    freeExerciseSample2.set_id(query.getInt(columnIndexOrThrow));
                    freeExerciseSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    freeExerciseSample2.sess_id = query.getString(columnIndexOrThrow3);
                    freeExerciseSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    freeExerciseSample2.setSampleData(sampleData);
                    arrayList.add(freeExerciseSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class v extends EntityInsertionAdapter<EntityWorkoutSessionSegment> {
        public v(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityWorkoutSessionSegment entityWorkoutSessionSegment) {
            String str = entityWorkoutSessionSegment.segment_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityWorkoutSessionSegment.getSess_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityWorkoutSessionSegment.getSess_id());
            }
            supportSQLiteStatement.bindLong(3, entityWorkoutSessionSegment.getStart_time());
            supportSQLiteStatement.bindLong(4, entityWorkoutSessionSegment.getEnd_time());
            supportSQLiteStatement.bindLong(5, entityWorkoutSessionSegment.getSegment_duration());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `workout_session_segment` (`segment_id`,`sess_id`,`start_time`,`end_time`,`segment_duration`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class v0 implements Callable<List<EllipticalSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public v0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EllipticalSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        EllipticalSample ellipticalSample = new EllipticalSample();
                        ellipticalSample.set_id(query.getInt(columnIndexOrThrow));
                        ellipticalSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        ellipticalSample.sess_id = query.getString(columnIndexOrThrow3);
                        ellipticalSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        ellipticalSample.setSampleData(sampleData);
                        arrayList.add(ellipticalSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    EllipticalSample ellipticalSample2 = new EllipticalSample();
                    ellipticalSample2.set_id(query.getInt(columnIndexOrThrow));
                    ellipticalSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    ellipticalSample2.sess_id = query.getString(columnIndexOrThrow3);
                    ellipticalSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    ellipticalSample2.setSampleData(sampleData);
                    arrayList.add(ellipticalSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class w implements Callable<List<EntityWorkoutSession>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public w(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSession> call() throws Exception {
            boolean z;
            int i;
            Integer valueOf;
            Integer valueOf2;
            Float valueOf3;
            Float valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Float valueOf7;
            Float valueOf8;
            Integer valueOf9;
            int i2;
            Integer valueOf10;
            Integer valueOf11;
            Integer valueOf12;
            Integer valueOf13;
            Integer valueOf14;
            Float valueOf15;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i4 = columnIndexOrThrow;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i5 = i3;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i5));
                    i3 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow24 = i15;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i15;
                        z = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z);
                    columnIndexOrThrow23 = i14;
                    int i16 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i18));
                    int i19 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i19)));
                    int i20 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i20;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i20)));
                    int i21 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i = i21;
                        valueOf = null;
                    } else {
                        i = i21;
                        valueOf = Integer.valueOf(query.getInt(i22));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = Integer.valueOf(query.getInt(i23));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = Float.valueOf(query.getFloat(i24));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = Float.valueOf(query.getFloat(i25));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i31 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i31));
                    int i32 = columnIndexOrThrow41;
                    if (query.isNull(i32)) {
                        i2 = i31;
                        valueOf10 = null;
                    } else {
                        i2 = i31;
                        valueOf10 = Integer.valueOf(query.getInt(i32));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i33 = columnIndexOrThrow42;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i34 = columnIndexOrThrow43;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i35 = columnIndexOrThrow44;
                    if (query.isNull(i35)) {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i36 = columnIndexOrThrow45;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i37 = columnIndexOrThrow46;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = Float.valueOf(query.getFloat(i37));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i2;
                    columnIndexOrThrow41 = i32;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    columnIndexOrThrow28 = i19;
                    columnIndexOrThrow27 = i18;
                    int i38 = i;
                    columnIndexOrThrow31 = i22;
                    columnIndexOrThrow30 = i38;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class w0 implements Callable<List<RowingMachineSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public w0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<RowingMachineSample> call() throws Exception {
            SampleData sampleData;
            int i;
            SampleData sampleData2 = null;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        sampleData = sampleData2;
                        i = columnIndexOrThrow3;
                        RowingMachineSample rowingMachineSample = new RowingMachineSample();
                        rowingMachineSample.set_id(query.getInt(columnIndexOrThrow));
                        rowingMachineSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        columnIndexOrThrow3 = i;
                        int i2 = columnIndexOrThrow;
                        rowingMachineSample.sess_id = query.getString(columnIndexOrThrow3);
                        rowingMachineSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        rowingMachineSample.setSampleData(sampleData);
                        arrayList.add(rowingMachineSample);
                        columnIndexOrThrow = i2;
                        sampleData2 = null;
                    }
                    sampleData = new SampleData();
                    i = columnIndexOrThrow3;
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    RowingMachineSample rowingMachineSample2 = new RowingMachineSample();
                    rowingMachineSample2.set_id(query.getInt(columnIndexOrThrow));
                    rowingMachineSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    columnIndexOrThrow3 = i;
                    int i22 = columnIndexOrThrow;
                    rowingMachineSample2.sess_id = query.getString(columnIndexOrThrow3);
                    rowingMachineSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    rowingMachineSample2.setSampleData(sampleData);
                    arrayList.add(rowingMachineSample2);
                    columnIndexOrThrow = i22;
                    sampleData2 = null;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class x implements Callable<List<EntityWorkoutSession>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public x(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityWorkoutSession> call() throws Exception {
            boolean z;
            int i;
            Integer valueOf;
            Integer valueOf2;
            Float valueOf3;
            Float valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Float valueOf7;
            Float valueOf8;
            Integer valueOf9;
            int i2;
            Integer valueOf10;
            Integer valueOf11;
            Integer valueOf12;
            Integer valueOf13;
            Integer valueOf14;
            Float valueOf15;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i4 = columnIndexOrThrow;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i5 = i3;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i5));
                    i3 = i5;
                    int i6 = columnIndexOrThrow15;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i6));
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i7));
                    columnIndexOrThrow16 = i7;
                    int i8 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i8));
                    columnIndexOrThrow17 = i8;
                    int i9 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i9));
                    columnIndexOrThrow18 = i9;
                    int i10 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i10));
                    columnIndexOrThrow19 = i10;
                    int i11 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i11));
                    columnIndexOrThrow20 = i11;
                    int i12 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i12));
                    columnIndexOrThrow21 = i12;
                    int i13 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i13));
                    columnIndexOrThrow22 = i13;
                    int i14 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i14));
                    int i15 = columnIndexOrThrow24;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow24 = i15;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i15;
                        z = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z);
                    columnIndexOrThrow23 = i14;
                    int i16 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i18));
                    int i19 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i19)));
                    int i20 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i20;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i20)));
                    int i21 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i21));
                    int i22 = columnIndexOrThrow31;
                    if (query.isNull(i22)) {
                        i = i21;
                        valueOf = null;
                    } else {
                        i = i21;
                        valueOf = Integer.valueOf(query.getInt(i22));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i23 = columnIndexOrThrow32;
                    if (query.isNull(i23)) {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i23;
                        valueOf2 = Integer.valueOf(query.getInt(i23));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i24 = columnIndexOrThrow33;
                    if (query.isNull(i24)) {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i24;
                        valueOf3 = Float.valueOf(query.getFloat(i24));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i25 = columnIndexOrThrow34;
                    if (query.isNull(i25)) {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i25;
                        valueOf4 = Float.valueOf(query.getFloat(i25));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i26 = columnIndexOrThrow35;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i26;
                        valueOf5 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i27 = columnIndexOrThrow36;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i27;
                        valueOf6 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i28 = columnIndexOrThrow37;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i28;
                        valueOf7 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i29 = columnIndexOrThrow38;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i29;
                        valueOf8 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i30 = columnIndexOrThrow39;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i30;
                        valueOf9 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i31 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i31));
                    int i32 = columnIndexOrThrow41;
                    if (query.isNull(i32)) {
                        i2 = i31;
                        valueOf10 = null;
                    } else {
                        i2 = i31;
                        valueOf10 = Integer.valueOf(query.getInt(i32));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i33 = columnIndexOrThrow42;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i33;
                        valueOf11 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i34 = columnIndexOrThrow43;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i34;
                        valueOf12 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i35 = columnIndexOrThrow44;
                    if (query.isNull(i35)) {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i35;
                        valueOf13 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i36 = columnIndexOrThrow45;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i36;
                        valueOf14 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i37 = columnIndexOrThrow46;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i37;
                        valueOf15 = Float.valueOf(query.getFloat(i37));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i2;
                    columnIndexOrThrow41 = i32;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    columnIndexOrThrow28 = i19;
                    columnIndexOrThrow27 = i18;
                    int i38 = i;
                    columnIndexOrThrow31 = i22;
                    columnIndexOrThrow30 = i38;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class x0 implements Callable<List<ActivityDataSample>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public x0(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<ActivityDataSample> call() throws Exception {
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "segmentID");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sessionID");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "activityType");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "calories");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "hrValue");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "speedValue");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ActivityDataSample activityDataSample = new ActivityDataSample();
                    activityDataSample.set_id(query.getInt(columnIndexOrThrow));
                    activityDataSample.setSegmentID(query.getString(columnIndexOrThrow2));
                    activityDataSample.sessionID = query.getString(columnIndexOrThrow3);
                    activityDataSample.setActivityType(query.getString(columnIndexOrThrow4));
                    int i = columnIndexOrThrow;
                    activityDataSample.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    activityDataSample.setStepCount(query.getInt(columnIndexOrThrow6));
                    activityDataSample.setCalories(query.getFloat(columnIndexOrThrow7));
                    activityDataSample.setDistance(query.getInt(columnIndexOrThrow8));
                    activityDataSample.setHrValue(query.getInt(columnIndexOrThrow9));
                    activityDataSample.setSpeedValue(query.getFloat(columnIndexOrThrow10));
                    activityDataSample.setLatitude(query.getDouble(columnIndexOrThrow11));
                    activityDataSample.setLongitude(query.getDouble(columnIndexOrThrow12));
                    arrayList.add(activityDataSample);
                    columnIndexOrThrow = i;
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class y implements Callable<EntityWorkoutSession> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public y(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public EntityWorkoutSession call() throws Exception {
            EntityWorkoutSession entityWorkoutSession;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                return entityWorkoutSession;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class y0 extends EntityInsertionAdapter<RunSample> {
        public y0(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, RunSample runSample) {
            supportSQLiteStatement.bindLong(1, runSample.get_id());
            if (runSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, runSample.getSeg_id());
            }
            String str = runSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, runSample.getStepCount());
            SampleData sampleData = runSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `run_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class z implements Callable<EntityWorkoutSession> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public z(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public EntityWorkoutSession call() throws Exception {
            EntityWorkoutSession entityWorkoutSession;
            Cursor query = DBUtil.query(SessionDAO_Impl.this.f2800a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                return entityWorkoutSession;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes2.dex */
    public class z0 extends EntityInsertionAdapter<BadmintonSample> {
        public z0(SessionDAO_Impl sessionDAO_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, BadmintonSample badmintonSample) {
            supportSQLiteStatement.bindLong(1, badmintonSample.get_id());
            if (badmintonSample.getSeg_id() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, badmintonSample.getSeg_id());
            }
            String str = badmintonSample.sess_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str);
            }
            supportSQLiteStatement.bindLong(4, badmintonSample.getStepCount());
            SampleData sampleData = badmintonSample.getSampleData();
            if (sampleData != null) {
                supportSQLiteStatement.bindLong(5, sampleData.getTimeStamp());
                supportSQLiteStatement.bindDouble(6, sampleData.getCalories());
                supportSQLiteStatement.bindLong(7, sampleData.getDistance());
                supportSQLiteStatement.bindLong(8, sampleData.getHr_value());
                supportSQLiteStatement.bindDouble(9, sampleData.getSpeed_value());
                supportSQLiteStatement.bindDouble(10, sampleData.getLatitude());
                supportSQLiteStatement.bindDouble(11, sampleData.getLongitude());
                return;
            }
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `badminton_sample` (`_id`,`seg_id`,`sess_id`,`step_count`,`timestamp`,`calories`,`distance`,`hr_value`,`speed_value`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public SessionDAO_Impl(RoomDatabase roomDatabase) {
        this.f2800a = roomDatabase;
        this.b = new k(this, roomDatabase);
        this.c = new v(this, roomDatabase);
        this.d = new g0(this, roomDatabase);
        this.e = new r0(this, roomDatabase);
        this.f = new y0(this, roomDatabase);
        this.g = new z0(this, roomDatabase);
        this.h = new a1(this, roomDatabase);
        this.i = new b1(this, roomDatabase);
        this.j = new c1(this, roomDatabase);
        this.k = new a(this, roomDatabase);
        this.l = new b(this, roomDatabase);
        this.m = new c(this, roomDatabase);
        this.n = new d(this, roomDatabase);
        this.o = new e(this, roomDatabase);
        this.p = new f(this, roomDatabase);
        this.q = new g(this, roomDatabase);
        this.r = new h(this, roomDatabase);
        this.s = new i(this, roomDatabase);
        this.t = new j(this, roomDatabase);
        this.u = new l(this, roomDatabase);
        this.v = new m(this, roomDatabase);
        this.w = new n(this, roomDatabase);
        this.x = new o(this, roomDatabase);
        this.y = new p(this, roomDatabase);
        this.z = new q(this, roomDatabase);
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void deleteSession(EntityWorkoutSession entityWorkoutSession) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.x.handle(entityWorkoutSession);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void deleteWorkOutSession() {
        this.f2800a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.z.acquire();
        this.f2800a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
            this.z.release(acquire);
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<SkippingSample>> geSkippingSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM skipping_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"skipping_sample"}, false, new p0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSession> getActivitiesByActivityAndCategoryID() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z2;
        int i2;
        Integer valueOf;
        Integer valueOf2;
        Float valueOf3;
        Float valueOf4;
        Integer valueOf5;
        Integer valueOf6;
        Float valueOf7;
        Float valueOf8;
        Integer valueOf9;
        int i3;
        Integer valueOf10;
        Integer valueOf11;
        Integer valueOf12;
        Integer valueOf13;
        Integer valueOf14;
        Float valueOf15;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session GROUP BY categoryId, activityId", 0);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i5 = columnIndexOrThrow2;
                    int i6 = columnIndexOrThrow3;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i7 = i4;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    int i9 = columnIndexOrThrow;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i8));
                    int i10 = columnIndexOrThrow16;
                    int i11 = columnIndexOrThrow12;
                    entityWorkoutSession.setTotal_steps(query.getInt(i10));
                    int i12 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i12));
                    int i13 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i13));
                    int i14 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i14));
                    int i15 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i15));
                    int i16 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i16));
                    int i17 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i17));
                    int i18 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i18));
                    int i19 = columnIndexOrThrow24;
                    if (query.getInt(i19) != 0) {
                        columnIndexOrThrow24 = i19;
                        z2 = true;
                    } else {
                        columnIndexOrThrow24 = i19;
                        z2 = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z2);
                    int i20 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i20));
                    int i21 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i21));
                    int i22 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i22));
                    int i23 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i23)));
                    int i24 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i24;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i24)));
                    columnIndexOrThrow28 = i23;
                    int i25 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i25));
                    int i26 = columnIndexOrThrow31;
                    if (query.isNull(i26)) {
                        i2 = i25;
                        valueOf = null;
                    } else {
                        i2 = i25;
                        valueOf = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i27 = columnIndexOrThrow32;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i28 = columnIndexOrThrow33;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i29 = columnIndexOrThrow34;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i30 = columnIndexOrThrow35;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i31 = columnIndexOrThrow36;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = Integer.valueOf(query.getInt(i31));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i32 = columnIndexOrThrow37;
                    if (query.isNull(i32)) {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = Float.valueOf(query.getFloat(i32));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i33 = columnIndexOrThrow38;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = Float.valueOf(query.getFloat(i33));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i34 = columnIndexOrThrow39;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i35 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i35));
                    int i36 = columnIndexOrThrow41;
                    if (query.isNull(i36)) {
                        i3 = i35;
                        valueOf10 = null;
                    } else {
                        i3 = i35;
                        valueOf10 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i37 = columnIndexOrThrow42;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = Integer.valueOf(query.getInt(i37));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i38 = columnIndexOrThrow43;
                    if (query.isNull(i38)) {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = Integer.valueOf(query.getInt(i38));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i39 = columnIndexOrThrow44;
                    if (query.isNull(i39)) {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = Integer.valueOf(query.getInt(i39));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i40 = columnIndexOrThrow45;
                    if (query.isNull(i40)) {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = Integer.valueOf(query.getInt(i40));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i41 = columnIndexOrThrow46;
                    if (query.isNull(i41)) {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = Float.valueOf(query.getFloat(i41));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i3;
                    columnIndexOrThrow41 = i36;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow17 = i12;
                    columnIndexOrThrow18 = i13;
                    columnIndexOrThrow19 = i14;
                    columnIndexOrThrow20 = i15;
                    columnIndexOrThrow21 = i16;
                    columnIndexOrThrow22 = i17;
                    columnIndexOrThrow23 = i18;
                    columnIndexOrThrow25 = i20;
                    columnIndexOrThrow26 = i21;
                    columnIndexOrThrow27 = i22;
                    i4 = i7;
                    columnIndexOrThrow2 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow3 = i6;
                    int i42 = i2;
                    columnIndexOrThrow31 = i26;
                    columnIndexOrThrow30 = i42;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSession> getActivitiesByCategoryID() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z2;
        int i2;
        Integer valueOf;
        Integer valueOf2;
        Float valueOf3;
        Float valueOf4;
        Integer valueOf5;
        Integer valueOf6;
        Float valueOf7;
        Float valueOf8;
        Integer valueOf9;
        int i3;
        Integer valueOf10;
        Integer valueOf11;
        Integer valueOf12;
        Integer valueOf13;
        Integer valueOf14;
        Float valueOf15;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session GROUP BY categoryId", 0);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i5 = columnIndexOrThrow2;
                    int i6 = columnIndexOrThrow3;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i7 = i4;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    int i9 = columnIndexOrThrow;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i8));
                    int i10 = columnIndexOrThrow16;
                    int i11 = columnIndexOrThrow12;
                    entityWorkoutSession.setTotal_steps(query.getInt(i10));
                    int i12 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i12));
                    int i13 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i13));
                    int i14 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i14));
                    int i15 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i15));
                    int i16 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i16));
                    int i17 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i17));
                    int i18 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i18));
                    int i19 = columnIndexOrThrow24;
                    if (query.getInt(i19) != 0) {
                        columnIndexOrThrow24 = i19;
                        z2 = true;
                    } else {
                        columnIndexOrThrow24 = i19;
                        z2 = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z2);
                    int i20 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i20));
                    int i21 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i21));
                    int i22 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i22));
                    int i23 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i23)));
                    int i24 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i24;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i24)));
                    columnIndexOrThrow28 = i23;
                    int i25 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i25));
                    int i26 = columnIndexOrThrow31;
                    if (query.isNull(i26)) {
                        i2 = i25;
                        valueOf = null;
                    } else {
                        i2 = i25;
                        valueOf = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i27 = columnIndexOrThrow32;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i28 = columnIndexOrThrow33;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i29 = columnIndexOrThrow34;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i30 = columnIndexOrThrow35;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i31 = columnIndexOrThrow36;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = Integer.valueOf(query.getInt(i31));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i32 = columnIndexOrThrow37;
                    if (query.isNull(i32)) {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = Float.valueOf(query.getFloat(i32));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i33 = columnIndexOrThrow38;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = Float.valueOf(query.getFloat(i33));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i34 = columnIndexOrThrow39;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i35 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i35));
                    int i36 = columnIndexOrThrow41;
                    if (query.isNull(i36)) {
                        i3 = i35;
                        valueOf10 = null;
                    } else {
                        i3 = i35;
                        valueOf10 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i37 = columnIndexOrThrow42;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = Integer.valueOf(query.getInt(i37));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i38 = columnIndexOrThrow43;
                    if (query.isNull(i38)) {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = Integer.valueOf(query.getInt(i38));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i39 = columnIndexOrThrow44;
                    if (query.isNull(i39)) {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = Integer.valueOf(query.getInt(i39));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i40 = columnIndexOrThrow45;
                    if (query.isNull(i40)) {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = Integer.valueOf(query.getInt(i40));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i41 = columnIndexOrThrow46;
                    if (query.isNull(i41)) {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = Float.valueOf(query.getFloat(i41));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i3;
                    columnIndexOrThrow41 = i36;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow17 = i12;
                    columnIndexOrThrow18 = i13;
                    columnIndexOrThrow19 = i14;
                    columnIndexOrThrow20 = i15;
                    columnIndexOrThrow21 = i16;
                    columnIndexOrThrow22 = i17;
                    columnIndexOrThrow23 = i18;
                    columnIndexOrThrow25 = i20;
                    columnIndexOrThrow26 = i21;
                    columnIndexOrThrow27 = i22;
                    i4 = i7;
                    columnIndexOrThrow2 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow3 = i6;
                    int i42 = i2;
                    columnIndexOrThrow31 = i26;
                    columnIndexOrThrow30 = i42;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<ActivityDataSample> getActivityDataSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM activity_data_sample WHERE sessionID=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "segmentID");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sessionID");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "activityType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "hrValue");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "speedValue");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ActivityDataSample activityDataSample = new ActivityDataSample();
                roomSQLiteQuery = acquire;
                try {
                    activityDataSample.set_id(query.getInt(columnIndexOrThrow));
                    activityDataSample.setSegmentID(query.getString(columnIndexOrThrow2));
                    activityDataSample.sessionID = query.getString(columnIndexOrThrow3);
                    activityDataSample.setActivityType(query.getString(columnIndexOrThrow4));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    activityDataSample.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    activityDataSample.setStepCount(query.getInt(columnIndexOrThrow6));
                    activityDataSample.setCalories(query.getFloat(columnIndexOrThrow7));
                    activityDataSample.setDistance(query.getInt(columnIndexOrThrow8));
                    activityDataSample.setHrValue(query.getInt(columnIndexOrThrow9));
                    activityDataSample.setSpeedValue(query.getFloat(columnIndexOrThrow10));
                    activityDataSample.setLatitude(query.getDouble(columnIndexOrThrow11));
                    activityDataSample.setLongitude(query.getDouble(columnIndexOrThrow12));
                    arrayList.add(activityDataSample);
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow3 = i3;
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<ActivityDataSample> getActivityDataSamplesBySessionIdAndSegmentId(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM activity_data_sample WHERE segmentID=? AND sessionID=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "segmentID");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sessionID");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "activityType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "hrValue");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "speedValue");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ActivityDataSample activityDataSample = new ActivityDataSample();
                roomSQLiteQuery = acquire;
                try {
                    activityDataSample.set_id(query.getInt(columnIndexOrThrow));
                    activityDataSample.setSegmentID(query.getString(columnIndexOrThrow2));
                    activityDataSample.sessionID = query.getString(columnIndexOrThrow3);
                    activityDataSample.setActivityType(query.getString(columnIndexOrThrow4));
                    int i2 = columnIndexOrThrow2;
                    activityDataSample.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    activityDataSample.setStepCount(query.getInt(columnIndexOrThrow6));
                    activityDataSample.setCalories(query.getFloat(columnIndexOrThrow7));
                    activityDataSample.setDistance(query.getInt(columnIndexOrThrow8));
                    activityDataSample.setHrValue(query.getInt(columnIndexOrThrow9));
                    activityDataSample.setSpeedValue(query.getFloat(columnIndexOrThrow10));
                    activityDataSample.setLatitude(query.getDouble(columnIndexOrThrow11));
                    activityDataSample.setLongitude(query.getDouble(columnIndexOrThrow12));
                    arrayList.add(activityDataSample);
                    columnIndexOrThrow2 = i2;
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<ActivityDataSample>> getActivityDataSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM activity_data_sample WHERE sessionID=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"activity_data_sample"}, false, new x0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public FitnessChallengeStatsData getAllSessionDataByDate(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) as totalSteps, SUM(total_calories) as totalCalories, SUM(total_distance) as totalDistance FROM workout_session where serial_no=? AND date=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        FitnessChallengeStatsData fitnessChallengeStatsData = null;
        Integer valueOf = null;
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "totalSteps");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "totalCalories");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "totalDistance");
            if (query.moveToFirst()) {
                Integer valueOf2 = query.isNull(columnIndexOrThrow) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow));
                Integer valueOf3 = query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2));
                if (!query.isNull(columnIndexOrThrow3)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow3));
                }
                fitnessChallengeStatsData = new FitnessChallengeStatsData(valueOf2, valueOf3, valueOf);
            }
            return fitnessChallengeStatsData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<BadmintonSample> getBadmintonSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM badminton_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        BadmintonSample badmintonSample = new BadmintonSample();
                        badmintonSample.set_id(query.getInt(columnIndexOrThrow));
                        badmintonSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        badmintonSample.sess_id = query.getString(columnIndexOrThrow3);
                        badmintonSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        badmintonSample.setSampleData(sampleData);
                        arrayList.add(badmintonSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    BadmintonSample badmintonSample2 = new BadmintonSample();
                    badmintonSample2.set_id(query.getInt(columnIndexOrThrow));
                    badmintonSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    badmintonSample2.sess_id = query.getString(columnIndexOrThrow3);
                    badmintonSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    badmintonSample2.setSampleData(sampleData);
                    arrayList.add(badmintonSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<BadmintonSample> getBadmintonSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM badminton_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    BadmintonSample badmintonSample = new BadmintonSample();
                    badmintonSample.set_id(query.getInt(columnIndexOrThrow));
                    badmintonSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    badmintonSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    badmintonSample.setStepCount(query.getInt(i6));
                    badmintonSample.setSampleData(sampleData);
                    arrayList.add(badmintonSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                BadmintonSample badmintonSample2 = new BadmintonSample();
                badmintonSample2.set_id(query.getInt(columnIndexOrThrow));
                badmintonSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                badmintonSample2.sess_id = query.getString(i42);
                int i62 = i3;
                badmintonSample2.setStepCount(query.getInt(i62));
                badmintonSample2.setSampleData(sampleData);
                arrayList.add(badmintonSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<BadmintonSample>> getBadmintonSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM badminton_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"badminton_sample"}, false, new e0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<BasketBallSample> getBasketBallSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM basketball_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        BasketBallSample basketBallSample = new BasketBallSample();
                        basketBallSample.set_id(query.getInt(columnIndexOrThrow));
                        basketBallSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        basketBallSample.sess_id = query.getString(columnIndexOrThrow3);
                        basketBallSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        basketBallSample.setSampleData(sampleData);
                        arrayList.add(basketBallSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    BasketBallSample basketBallSample2 = new BasketBallSample();
                    basketBallSample2.set_id(query.getInt(columnIndexOrThrow));
                    basketBallSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    basketBallSample2.sess_id = query.getString(columnIndexOrThrow3);
                    basketBallSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    basketBallSample2.setSampleData(sampleData);
                    arrayList.add(basketBallSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<BasketBallSample> getBasketBallSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM basketball_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    BasketBallSample basketBallSample = new BasketBallSample();
                    basketBallSample.set_id(query.getInt(columnIndexOrThrow));
                    basketBallSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    basketBallSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    basketBallSample.setStepCount(query.getInt(i6));
                    basketBallSample.setSampleData(sampleData);
                    arrayList.add(basketBallSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                BasketBallSample basketBallSample2 = new BasketBallSample();
                basketBallSample2.set_id(query.getInt(columnIndexOrThrow));
                basketBallSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                basketBallSample2.sess_id = query.getString(i42);
                int i62 = i3;
                basketBallSample2.setStepCount(query.getInt(i62));
                basketBallSample2.setSampleData(sampleData);
                arrayList.add(basketBallSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<BasketBallSample>> getBasketBallSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM basketball_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"basketball_sample"}, false, new f0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getBestSessionByActivityAndCategoryID(int i2, int i3) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE categoryId=? AND  activityId=? ORDER BY total_calories DESC LIMIT 1", 2);
        acquire.bindLong(1, i3);
        acquire.bindLong(2, i2);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getBestSessionByActivityType(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE activity_type=? ORDER BY total_calories DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getBestSessionByCategoryID(int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE categoryId=? ORDER BY total_calories DESC LIMIT 1", 1);
        acquire.bindLong(1, i2);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getCaloriesByActivityNCategoryIDs(String str, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) = ? AND categoryId =? AND activityId =?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getCaloriesByActivityType(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE activity_type=? AND strftime('%Y-%m-%d',date)=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getCaloriesForWeekByActivityNCategoryIDs(int i2, int i3, String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE categoryId =? AND activityId =? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 4);
        acquire.bindLong(1, i2);
        acquire.bindLong(2, i3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        if (str2 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getCaloriesForWeekByType(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE activity_type=? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 3);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getCaloriesWithoutActivityMapping(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<ClimbingSample> getClimbingSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM climbing_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        ClimbingSample climbingSample = new ClimbingSample();
                        climbingSample.set_id(query.getInt(columnIndexOrThrow));
                        climbingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        climbingSample.sess_id = query.getString(columnIndexOrThrow3);
                        climbingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        climbingSample.setSampleData(sampleData);
                        arrayList.add(climbingSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    ClimbingSample climbingSample2 = new ClimbingSample();
                    climbingSample2.set_id(query.getInt(columnIndexOrThrow));
                    climbingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    climbingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    climbingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    climbingSample2.setSampleData(sampleData);
                    arrayList.add(climbingSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<ClimbingSample> getClimbingSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM climbing_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    ClimbingSample climbingSample = new ClimbingSample();
                    climbingSample.set_id(query.getInt(columnIndexOrThrow));
                    climbingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    climbingSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    climbingSample.setStepCount(query.getInt(i6));
                    climbingSample.setSampleData(sampleData);
                    arrayList.add(climbingSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                ClimbingSample climbingSample2 = new ClimbingSample();
                climbingSample2.set_id(query.getInt(columnIndexOrThrow));
                climbingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                climbingSample2.sess_id = query.getString(i42);
                int i62 = i3;
                climbingSample2.setStepCount(query.getInt(i62));
                climbingSample2.setSampleData(sampleData);
                arrayList.add(climbingSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<ClimbingSample>> getClimbingSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM climbing_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"climbing_sample"}, false, new q0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<CyclingSample> getCyclingSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM cycling_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        CyclingSample cyclingSample = new CyclingSample();
                        cyclingSample.set_id(query.getInt(columnIndexOrThrow));
                        cyclingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        cyclingSample.sess_id = query.getString(columnIndexOrThrow3);
                        cyclingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        cyclingSample.setSampleData(sampleData);
                        arrayList.add(cyclingSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    CyclingSample cyclingSample2 = new CyclingSample();
                    cyclingSample2.set_id(query.getInt(columnIndexOrThrow));
                    cyclingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    cyclingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    cyclingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    cyclingSample2.setSampleData(sampleData);
                    arrayList.add(cyclingSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<CyclingSample> getCyclingSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM cycling_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    CyclingSample cyclingSample = new CyclingSample();
                    cyclingSample.set_id(query.getInt(columnIndexOrThrow));
                    cyclingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    cyclingSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    cyclingSample.setStepCount(query.getInt(i6));
                    cyclingSample.setSampleData(sampleData);
                    arrayList.add(cyclingSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                CyclingSample cyclingSample2 = new CyclingSample();
                cyclingSample2.set_id(query.getInt(columnIndexOrThrow));
                cyclingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                cyclingSample2.sess_id = query.getString(i42);
                int i62 = i3;
                cyclingSample2.setStepCount(query.getInt(i62));
                cyclingSample2.setSampleData(sampleData);
                arrayList.add(cyclingSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<CyclingSample>> getCyclingSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM cycling_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"cycling_sample"}, false, new h0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<DanceSample> getDanceSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dance_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        DanceSample danceSample = new DanceSample();
                        danceSample.set_id(query.getInt(columnIndexOrThrow));
                        danceSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        danceSample.sess_id = query.getString(columnIndexOrThrow3);
                        danceSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        danceSample.setSampleData(sampleData);
                        arrayList.add(danceSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    DanceSample danceSample2 = new DanceSample();
                    danceSample2.set_id(query.getInt(columnIndexOrThrow));
                    danceSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    danceSample2.sess_id = query.getString(columnIndexOrThrow3);
                    danceSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    danceSample2.setSampleData(sampleData);
                    arrayList.add(danceSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<DanceSample> getDanceSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dance_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    DanceSample danceSample = new DanceSample();
                    danceSample.set_id(query.getInt(columnIndexOrThrow));
                    danceSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    danceSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    danceSample.setStepCount(query.getInt(i6));
                    danceSample.setSampleData(sampleData);
                    arrayList.add(danceSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                DanceSample danceSample2 = new DanceSample();
                danceSample2.set_id(query.getInt(columnIndexOrThrow));
                danceSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                danceSample2.sess_id = query.getString(i42);
                int i62 = i3;
                danceSample2.setStepCount(query.getInt(i62));
                danceSample2.setSampleData(sampleData);
                arrayList.add(danceSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<DanceSample>> getDanceSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dance_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"dance_sample"}, false, new j0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getDistanceByActivityNCategoryIDs(String str, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) = ? AND categoryId =? AND activityId =?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getDistanceByType(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) = ? AND activity_type =?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getDistanceForWeekByActivityNCategoryIDs(int i2, int i3, String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE categoryId =? AND activityId =? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 4);
        acquire.bindLong(1, i2);
        acquire.bindLong(2, i3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        if (str2 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getDistanceForWeekByType(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE activity_type=? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 3);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getDistanceWithoutActivityMapping(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EllipticalSample> getEllipticalSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM elliptical_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        EllipticalSample ellipticalSample = new EllipticalSample();
                        ellipticalSample.set_id(query.getInt(columnIndexOrThrow));
                        ellipticalSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        ellipticalSample.sess_id = query.getString(columnIndexOrThrow3);
                        ellipticalSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        ellipticalSample.setSampleData(sampleData);
                        arrayList.add(ellipticalSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    EllipticalSample ellipticalSample2 = new EllipticalSample();
                    ellipticalSample2.set_id(query.getInt(columnIndexOrThrow));
                    ellipticalSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    ellipticalSample2.sess_id = query.getString(columnIndexOrThrow3);
                    ellipticalSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    ellipticalSample2.setSampleData(sampleData);
                    arrayList.add(ellipticalSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EllipticalSample> getEllipticalSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM elliptical_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    EllipticalSample ellipticalSample = new EllipticalSample();
                    ellipticalSample.set_id(query.getInt(columnIndexOrThrow));
                    ellipticalSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    ellipticalSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    ellipticalSample.setStepCount(query.getInt(i6));
                    ellipticalSample.setSampleData(sampleData);
                    arrayList.add(ellipticalSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                EllipticalSample ellipticalSample2 = new EllipticalSample();
                ellipticalSample2.set_id(query.getInt(columnIndexOrThrow));
                ellipticalSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                ellipticalSample2.sess_id = query.getString(i42);
                int i62 = i3;
                ellipticalSample2.setStepCount(query.getInt(i62));
                ellipticalSample2.setSampleData(sampleData);
                arrayList.add(ellipticalSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EllipticalSample>> getEllipticalSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM elliptical_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"elliptical_sample"}, false, new v0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<FootballSample> getFootBallSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM football_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    FootballSample footballSample = new FootballSample();
                    footballSample.set_id(query.getInt(columnIndexOrThrow));
                    footballSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    footballSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    footballSample.setStepCount(query.getInt(i6));
                    footballSample.setSampleData(sampleData);
                    arrayList.add(footballSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                FootballSample footballSample2 = new FootballSample();
                footballSample2.set_id(query.getInt(columnIndexOrThrow));
                footballSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                footballSample2.sess_id = query.getString(i42);
                int i62 = i3;
                footballSample2.setStepCount(query.getInt(i62));
                footballSample2.setSampleData(sampleData);
                arrayList.add(footballSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<FootballSample> getFootballSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM football_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        FootballSample footballSample = new FootballSample();
                        footballSample.set_id(query.getInt(columnIndexOrThrow));
                        footballSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        footballSample.sess_id = query.getString(columnIndexOrThrow3);
                        footballSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        footballSample.setSampleData(sampleData);
                        arrayList.add(footballSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    FootballSample footballSample2 = new FootballSample();
                    footballSample2.set_id(query.getInt(columnIndexOrThrow));
                    footballSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    footballSample2.sess_id = query.getString(columnIndexOrThrow3);
                    footballSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    footballSample2.setSampleData(sampleData);
                    arrayList.add(footballSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<FootballSample>> getFootballSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM football_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"football_sample"}, false, new k0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<FreeExerciseSample> getFreeExerciseSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM free_exercise_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                        freeExerciseSample.set_id(query.getInt(columnIndexOrThrow));
                        freeExerciseSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        freeExerciseSample.sess_id = query.getString(columnIndexOrThrow3);
                        freeExerciseSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        freeExerciseSample.setSampleData(sampleData);
                        arrayList.add(freeExerciseSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    FreeExerciseSample freeExerciseSample2 = new FreeExerciseSample();
                    freeExerciseSample2.set_id(query.getInt(columnIndexOrThrow));
                    freeExerciseSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    freeExerciseSample2.sess_id = query.getString(columnIndexOrThrow3);
                    freeExerciseSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    freeExerciseSample2.setSampleData(sampleData);
                    arrayList.add(freeExerciseSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<FreeExerciseSample> getFreeExerciseSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM free_exercise_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                    freeExerciseSample.set_id(query.getInt(columnIndexOrThrow));
                    freeExerciseSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    freeExerciseSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    freeExerciseSample.setStepCount(query.getInt(i6));
                    freeExerciseSample.setSampleData(sampleData);
                    arrayList.add(freeExerciseSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                FreeExerciseSample freeExerciseSample2 = new FreeExerciseSample();
                freeExerciseSample2.set_id(query.getInt(columnIndexOrThrow));
                freeExerciseSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                freeExerciseSample2.sess_id = query.getString(i42);
                int i62 = i3;
                freeExerciseSample2.setStepCount(query.getInt(i62));
                freeExerciseSample2.setSampleData(sampleData);
                arrayList.add(freeExerciseSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<FreeExerciseSample>> getFreeExerciseSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM free_exercise_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"free_exercise_sample"}, false, new u0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<HikingSample> getHikingSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hiking_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        HikingSample hikingSample = new HikingSample();
                        hikingSample.set_id(query.getInt(columnIndexOrThrow));
                        hikingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        hikingSample.sess_id = query.getString(columnIndexOrThrow3);
                        hikingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        hikingSample.setSampleData(sampleData);
                        arrayList.add(hikingSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    HikingSample hikingSample2 = new HikingSample();
                    hikingSample2.set_id(query.getInt(columnIndexOrThrow));
                    hikingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    hikingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    hikingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    hikingSample2.setSampleData(sampleData);
                    arrayList.add(hikingSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<HikingSample> getHikingSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hiking_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    HikingSample hikingSample = new HikingSample();
                    hikingSample.set_id(query.getInt(columnIndexOrThrow));
                    hikingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    hikingSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    hikingSample.setStepCount(query.getInt(i6));
                    hikingSample.setSampleData(sampleData);
                    arrayList.add(hikingSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                HikingSample hikingSample2 = new HikingSample();
                hikingSample2.set_id(query.getInt(columnIndexOrThrow));
                hikingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                hikingSample2.sess_id = query.getString(i42);
                int i62 = i3;
                hikingSample2.setStepCount(query.getInt(i62));
                hikingSample2.setSampleData(sampleData);
                arrayList.add(hikingSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<HikingSample>> getHikingSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hiking_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"hiking_sample"}, false, new l0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getLastSessionData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session where serial_no=? ORDER BY start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getLastSessionDataByDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session where serial_no=? AND date=? ORDER BY start_time DESC LIMIT 1", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<EntityWorkoutSession> getLastSessionLiveData() {
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new y(RoomSQLiteQuery.acquire("SELECT * FROM workout_session ORDER BY start_time DESC LIMIT 1", 0)));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<EntityWorkoutSession> getLastSessionLiveDataForConnectedDevice(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE serial_no =? ORDER BY start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new z(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getLatestSession() {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session ORDER BY end_time DESC LIMIT 1", 0);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getLatestSessionByActivityType(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE activity_type=? ORDER BY end_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<MeditationSample> getMeditationSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM meditation_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        MeditationSample meditationSample = new MeditationSample();
                        meditationSample.set_id(query.getInt(columnIndexOrThrow));
                        meditationSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        meditationSample.sess_id = query.getString(columnIndexOrThrow3);
                        meditationSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        meditationSample.setSampleData(sampleData);
                        arrayList.add(meditationSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    MeditationSample meditationSample2 = new MeditationSample();
                    meditationSample2.set_id(query.getInt(columnIndexOrThrow));
                    meditationSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    meditationSample2.sess_id = query.getString(columnIndexOrThrow3);
                    meditationSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    meditationSample2.setSampleData(sampleData);
                    arrayList.add(meditationSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<MeditationSample> getMeditationSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM meditation_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    MeditationSample meditationSample = new MeditationSample();
                    meditationSample.set_id(query.getInt(columnIndexOrThrow));
                    meditationSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    meditationSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    meditationSample.setStepCount(query.getInt(i6));
                    meditationSample.setSampleData(sampleData);
                    arrayList.add(meditationSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                MeditationSample meditationSample2 = new MeditationSample();
                meditationSample2.set_id(query.getInt(columnIndexOrThrow));
                meditationSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                meditationSample2.sess_id = query.getString(i42);
                int i62 = i3;
                meditationSample2.setStepCount(query.getInt(i62));
                meditationSample2.setSampleData(sampleData);
                arrayList.add(meditationSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<MeditationSample>> getMeditationSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM meditation_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"meditation_sample"}, false, new i0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<PhysicalActivitySample> getPhysicalActivitySamplesBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM physical_activity_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                        physicalActivitySample.set_id(query.getInt(columnIndexOrThrow));
                        physicalActivitySample.setSeg_id(query.getString(columnIndexOrThrow2));
                        physicalActivitySample.sess_id = query.getString(columnIndexOrThrow3);
                        physicalActivitySample.setStepCount(query.getInt(columnIndexOrThrow4));
                        physicalActivitySample.setSampleData(sampleData);
                        arrayList.add(physicalActivitySample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    PhysicalActivitySample physicalActivitySample2 = new PhysicalActivitySample();
                    physicalActivitySample2.set_id(query.getInt(columnIndexOrThrow));
                    physicalActivitySample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    physicalActivitySample2.sess_id = query.getString(columnIndexOrThrow3);
                    physicalActivitySample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    physicalActivitySample2.setSampleData(sampleData);
                    arrayList.add(physicalActivitySample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<PhysicalActivitySample> getPhysicalActivitySamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM physical_activity_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                    physicalActivitySample.set_id(query.getInt(columnIndexOrThrow));
                    physicalActivitySample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    physicalActivitySample.sess_id = query.getString(i4);
                    int i6 = i3;
                    physicalActivitySample.setStepCount(query.getInt(i6));
                    physicalActivitySample.setSampleData(sampleData);
                    arrayList.add(physicalActivitySample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                PhysicalActivitySample physicalActivitySample2 = new PhysicalActivitySample();
                physicalActivitySample2.set_id(query.getInt(columnIndexOrThrow));
                physicalActivitySample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                physicalActivitySample2.sess_id = query.getString(i42);
                int i62 = i3;
                physicalActivitySample2.setStepCount(query.getInt(i62));
                physicalActivitySample2.setSampleData(sampleData);
                arrayList.add(physicalActivitySample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<PhysicalActivitySample>> getPhysicalActivitySamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM physical_activity_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"physical_activity_sample"}, false, new d0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<RowingMachineSample> getRowingMachineSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM rowing_machine_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        RowingMachineSample rowingMachineSample = new RowingMachineSample();
                        rowingMachineSample.set_id(query.getInt(columnIndexOrThrow));
                        rowingMachineSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        rowingMachineSample.sess_id = query.getString(columnIndexOrThrow3);
                        rowingMachineSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        rowingMachineSample.setSampleData(sampleData);
                        arrayList.add(rowingMachineSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    RowingMachineSample rowingMachineSample2 = new RowingMachineSample();
                    rowingMachineSample2.set_id(query.getInt(columnIndexOrThrow));
                    rowingMachineSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    rowingMachineSample2.sess_id = query.getString(columnIndexOrThrow3);
                    rowingMachineSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    rowingMachineSample2.setSampleData(sampleData);
                    arrayList.add(rowingMachineSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<RowingMachineSample> getRowingMachineSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM rowing_machine_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    RowingMachineSample rowingMachineSample = new RowingMachineSample();
                    rowingMachineSample.set_id(query.getInt(columnIndexOrThrow));
                    rowingMachineSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    rowingMachineSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    rowingMachineSample.setStepCount(query.getInt(i6));
                    rowingMachineSample.setSampleData(sampleData);
                    arrayList.add(rowingMachineSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                RowingMachineSample rowingMachineSample2 = new RowingMachineSample();
                rowingMachineSample2.set_id(query.getInt(columnIndexOrThrow));
                rowingMachineSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                rowingMachineSample2.sess_id = query.getString(i42);
                int i62 = i3;
                rowingMachineSample2.setStepCount(query.getInt(i62));
                rowingMachineSample2.setSampleData(sampleData);
                arrayList.add(rowingMachineSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<RowingMachineSample>> getRowingMachineSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM rowing_machine_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"rowing_machine_sample"}, false, new w0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<RunSample> getRunSamplesBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM run_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        RunSample runSample = new RunSample();
                        runSample.set_id(query.getInt(columnIndexOrThrow));
                        runSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        runSample.sess_id = query.getString(columnIndexOrThrow3);
                        runSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        runSample.setSampleData(sampleData);
                        arrayList.add(runSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    RunSample runSample2 = new RunSample();
                    runSample2.set_id(query.getInt(columnIndexOrThrow));
                    runSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    runSample2.sess_id = query.getString(columnIndexOrThrow3);
                    runSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    runSample2.setSampleData(sampleData);
                    arrayList.add(runSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<RunSample> getRunSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM run_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    RunSample runSample = new RunSample();
                    runSample.set_id(query.getInt(columnIndexOrThrow));
                    runSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    runSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    runSample.setStepCount(query.getInt(i6));
                    runSample.setSampleData(sampleData);
                    arrayList.add(runSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                RunSample runSample2 = new RunSample();
                runSample2.set_id(query.getInt(columnIndexOrThrow));
                runSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                runSample2.sess_id = query.getString(i42);
                int i62 = i3;
                runSample2.setStepCount(query.getInt(i62));
                runSample2.setSampleData(sampleData);
                arrayList.add(runSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<RunSample>> getRunSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM run_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"run_sample"}, false, new b0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSessionSegment> getSegmentsListBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session_segment WHERE  sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "segment_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "segment_duration");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
                entityWorkoutSessionSegment.segment_id = query.getString(columnIndexOrThrow);
                entityWorkoutSessionSegment.setSess_id(query.getString(columnIndexOrThrow2));
                entityWorkoutSessionSegment.setStart_time(query.getLong(columnIndexOrThrow3));
                entityWorkoutSessionSegment.setEnd_time(query.getLong(columnIndexOrThrow4));
                entityWorkoutSessionSegment.setSegment_duration(query.getInt(columnIndexOrThrow5));
                arrayList.add(entityWorkoutSessionSegment);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getSessionBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE  session_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<EntityWorkoutSession> getSessionLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE  session_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new r(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSessionSegment> getSessionSegmentListBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session_segment WHERE  sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "segment_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "segment_duration");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
                entityWorkoutSessionSegment.segment_id = query.getString(columnIndexOrThrow);
                entityWorkoutSessionSegment.setSess_id(query.getString(columnIndexOrThrow2));
                entityWorkoutSessionSegment.setStart_time(query.getLong(columnIndexOrThrow3));
                entityWorkoutSessionSegment.setEnd_time(query.getLong(columnIndexOrThrow4));
                entityWorkoutSessionSegment.setSegment_duration(query.getInt(columnIndexOrThrow5));
                arrayList.add(entityWorkoutSessionSegment);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSessionSegment>> getSessionSegmentListLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session_segment WHERE  sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session_segment"}, false, new s(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSession> getSessionsList() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z2;
        int i2;
        Integer valueOf;
        Integer valueOf2;
        Float valueOf3;
        Float valueOf4;
        Integer valueOf5;
        Integer valueOf6;
        Float valueOf7;
        Float valueOf8;
        Integer valueOf9;
        int i3;
        Integer valueOf10;
        Integer valueOf11;
        Integer valueOf12;
        Integer valueOf13;
        Integer valueOf14;
        Float valueOf15;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session", 0);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i5 = columnIndexOrThrow2;
                    int i6 = columnIndexOrThrow3;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i7 = i4;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    int i9 = columnIndexOrThrow;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i8));
                    int i10 = columnIndexOrThrow16;
                    int i11 = columnIndexOrThrow12;
                    entityWorkoutSession.setTotal_steps(query.getInt(i10));
                    int i12 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i12));
                    int i13 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i13));
                    int i14 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i14));
                    int i15 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i15));
                    int i16 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i16));
                    int i17 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i17));
                    int i18 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i18));
                    int i19 = columnIndexOrThrow24;
                    if (query.getInt(i19) != 0) {
                        columnIndexOrThrow24 = i19;
                        z2 = true;
                    } else {
                        columnIndexOrThrow24 = i19;
                        z2 = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z2);
                    int i20 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i20));
                    int i21 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i21));
                    int i22 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i22));
                    int i23 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i23)));
                    int i24 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i24;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i24)));
                    columnIndexOrThrow28 = i23;
                    int i25 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i25));
                    int i26 = columnIndexOrThrow31;
                    if (query.isNull(i26)) {
                        i2 = i25;
                        valueOf = null;
                    } else {
                        i2 = i25;
                        valueOf = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i27 = columnIndexOrThrow32;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i28 = columnIndexOrThrow33;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i29 = columnIndexOrThrow34;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i30 = columnIndexOrThrow35;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i31 = columnIndexOrThrow36;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = Integer.valueOf(query.getInt(i31));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i32 = columnIndexOrThrow37;
                    if (query.isNull(i32)) {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = Float.valueOf(query.getFloat(i32));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i33 = columnIndexOrThrow38;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = Float.valueOf(query.getFloat(i33));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i34 = columnIndexOrThrow39;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i35 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i35));
                    int i36 = columnIndexOrThrow41;
                    if (query.isNull(i36)) {
                        i3 = i35;
                        valueOf10 = null;
                    } else {
                        i3 = i35;
                        valueOf10 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i37 = columnIndexOrThrow42;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = Integer.valueOf(query.getInt(i37));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i38 = columnIndexOrThrow43;
                    if (query.isNull(i38)) {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = Integer.valueOf(query.getInt(i38));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i39 = columnIndexOrThrow44;
                    if (query.isNull(i39)) {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = Integer.valueOf(query.getInt(i39));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i40 = columnIndexOrThrow45;
                    if (query.isNull(i40)) {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = Integer.valueOf(query.getInt(i40));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i41 = columnIndexOrThrow46;
                    if (query.isNull(i41)) {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = Float.valueOf(query.getFloat(i41));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i3;
                    columnIndexOrThrow41 = i36;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow17 = i12;
                    columnIndexOrThrow18 = i13;
                    columnIndexOrThrow19 = i14;
                    columnIndexOrThrow20 = i15;
                    columnIndexOrThrow21 = i16;
                    columnIndexOrThrow22 = i17;
                    columnIndexOrThrow23 = i18;
                    columnIndexOrThrow25 = i20;
                    columnIndexOrThrow26 = i21;
                    columnIndexOrThrow27 = i22;
                    i4 = i7;
                    columnIndexOrThrow2 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow3 = i6;
                    int i42 = i2;
                    columnIndexOrThrow31 = i26;
                    columnIndexOrThrow30 = i42;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSession>> getSessionsListLiveData() {
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new t(RoomSQLiteQuery.acquire("SELECT * FROM workout_session ORDER BY start_time DESC", 0)));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataByDescStartTime() {
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new a0(RoomSQLiteQuery.acquire("SELECT * FROM workout_session ORDER BY start_time DESC", 0)));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE activity_type=? ORDER BY start_time DESC", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new w(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataForConnectedDevice(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE serial_no =? ORDER BY start_time DESC ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new u(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSession> getSessionsOfParticularDay(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z2;
        int i2;
        Integer valueOf;
        Integer valueOf2;
        Float valueOf3;
        Float valueOf4;
        Integer valueOf5;
        Integer valueOf6;
        Float valueOf7;
        Float valueOf8;
        Integer valueOf9;
        int i3;
        Integer valueOf10;
        Integer valueOf11;
        Integer valueOf12;
        Integer valueOf13;
        Integer valueOf14;
        Float valueOf15;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE strftime('%Y-%m-%d',date) = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i5 = columnIndexOrThrow2;
                    int i6 = columnIndexOrThrow3;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i7 = i4;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    int i9 = columnIndexOrThrow;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i8));
                    int i10 = columnIndexOrThrow16;
                    entityWorkoutSession.setTotal_steps(query.getInt(i10));
                    int i11 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i11));
                    int i12 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i12));
                    int i13 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i13));
                    int i14 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i14));
                    int i15 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i15));
                    int i16 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i16));
                    int i17 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i17));
                    int i18 = columnIndexOrThrow24;
                    if (query.getInt(i18) != 0) {
                        columnIndexOrThrow24 = i18;
                        z2 = true;
                    } else {
                        columnIndexOrThrow24 = i18;
                        z2 = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z2);
                    int i19 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i19));
                    int i20 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i20));
                    int i21 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i21));
                    int i22 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i22)));
                    int i23 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i23;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i23)));
                    columnIndexOrThrow28 = i22;
                    int i24 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i24));
                    int i25 = columnIndexOrThrow31;
                    if (query.isNull(i25)) {
                        i2 = i24;
                        valueOf = null;
                    } else {
                        i2 = i24;
                        valueOf = Integer.valueOf(query.getInt(i25));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i26 = columnIndexOrThrow32;
                    if (query.isNull(i26)) {
                        columnIndexOrThrow32 = i26;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i26;
                        valueOf2 = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i27 = columnIndexOrThrow33;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow33 = i27;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i27;
                        valueOf3 = Float.valueOf(query.getFloat(i27));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i28 = columnIndexOrThrow34;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow34 = i28;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i28;
                        valueOf4 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i29 = columnIndexOrThrow35;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow35 = i29;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i29;
                        valueOf5 = Integer.valueOf(query.getInt(i29));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i30 = columnIndexOrThrow36;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow36 = i30;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i30;
                        valueOf6 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i31 = columnIndexOrThrow37;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow37 = i31;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i31;
                        valueOf7 = Float.valueOf(query.getFloat(i31));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i32 = columnIndexOrThrow38;
                    if (query.isNull(i32)) {
                        columnIndexOrThrow38 = i32;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i32;
                        valueOf8 = Float.valueOf(query.getFloat(i32));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i33 = columnIndexOrThrow39;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow39 = i33;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i33;
                        valueOf9 = Integer.valueOf(query.getInt(i33));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i34 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i34));
                    int i35 = columnIndexOrThrow41;
                    if (query.isNull(i35)) {
                        i3 = i34;
                        valueOf10 = null;
                    } else {
                        i3 = i34;
                        valueOf10 = Integer.valueOf(query.getInt(i35));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i36 = columnIndexOrThrow42;
                    if (query.isNull(i36)) {
                        columnIndexOrThrow42 = i36;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i36;
                        valueOf11 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i37 = columnIndexOrThrow43;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow43 = i37;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i37;
                        valueOf12 = Integer.valueOf(query.getInt(i37));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i38 = columnIndexOrThrow44;
                    if (query.isNull(i38)) {
                        columnIndexOrThrow44 = i38;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i38;
                        valueOf13 = Integer.valueOf(query.getInt(i38));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i39 = columnIndexOrThrow45;
                    if (query.isNull(i39)) {
                        columnIndexOrThrow45 = i39;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i39;
                        valueOf14 = Integer.valueOf(query.getInt(i39));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i40 = columnIndexOrThrow46;
                    if (query.isNull(i40)) {
                        columnIndexOrThrow46 = i40;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i40;
                        valueOf15 = Float.valueOf(query.getFloat(i40));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i3;
                    columnIndexOrThrow41 = i35;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow3 = i6;
                    i4 = i7;
                    columnIndexOrThrow2 = i5;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow17 = i11;
                    columnIndexOrThrow18 = i12;
                    columnIndexOrThrow19 = i13;
                    columnIndexOrThrow20 = i14;
                    columnIndexOrThrow21 = i15;
                    columnIndexOrThrow22 = i16;
                    columnIndexOrThrow23 = i17;
                    columnIndexOrThrow25 = i19;
                    columnIndexOrThrow26 = i20;
                    columnIndexOrThrow27 = i21;
                    int i41 = i2;
                    columnIndexOrThrow31 = i25;
                    columnIndexOrThrow30 = i41;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getSingleDayCalorieWithoutActivityMapping(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_calories) FROM workout_session WHERE strftime('%Y-%m-%d',date) =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getSingleDayDistanceWithoutActivityMapping(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_distance) FROM workout_session WHERE strftime('%Y-%m-%d',date) =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getSingleDayStepWithoutActivityMapping(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) =?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<SkippingSample> getSkippingSamplesBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM skipping_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        SkippingSample skippingSample = new SkippingSample();
                        skippingSample.set_id(query.getInt(columnIndexOrThrow));
                        skippingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        skippingSample.sess_id = query.getString(columnIndexOrThrow3);
                        skippingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        skippingSample.setSampleData(sampleData);
                        arrayList.add(skippingSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    SkippingSample skippingSample2 = new SkippingSample();
                    skippingSample2.set_id(query.getInt(columnIndexOrThrow));
                    skippingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    skippingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    skippingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    skippingSample2.setSampleData(sampleData);
                    arrayList.add(skippingSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<SkippingSample> getSkippingSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM skipping_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        SkippingSample skippingSample = new SkippingSample();
                        skippingSample.set_id(query.getInt(columnIndexOrThrow));
                        skippingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        skippingSample.sess_id = query.getString(columnIndexOrThrow3);
                        skippingSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        skippingSample.setSampleData(sampleData);
                        arrayList.add(skippingSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    SkippingSample skippingSample2 = new SkippingSample();
                    skippingSample2.set_id(query.getInt(columnIndexOrThrow));
                    skippingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    skippingSample2.sess_id = query.getString(columnIndexOrThrow3);
                    skippingSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    skippingSample2.setSampleData(sampleData);
                    arrayList.add(skippingSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<SkippingSample> getSkippingSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM skipping_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    SkippingSample skippingSample = new SkippingSample();
                    skippingSample.set_id(query.getInt(columnIndexOrThrow));
                    skippingSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    skippingSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    skippingSample.setStepCount(query.getInt(i6));
                    skippingSample.setSampleData(sampleData);
                    arrayList.add(skippingSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                SkippingSample skippingSample2 = new SkippingSample();
                skippingSample2.set_id(query.getInt(columnIndexOrThrow));
                skippingSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                skippingSample2.sess_id = query.getString(i42);
                int i62 = i3;
                skippingSample2.setStepCount(query.getInt(i62));
                skippingSample2.setSampleData(sampleData);
                arrayList.add(skippingSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<SkippingSample>> getSkippingSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM skipping_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"skipping_sample"}, false, new t0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getStepsByActivityNCategoryIDs(String str, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) = ? AND categoryId =? AND activityId =?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getStepsByActivityType(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE activity_type=? AND strftime('%Y-%m-%d',date)=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getStepsForWeekByActivityNCategoryIDs(int i2, int i3, String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE categoryId =? AND activityId =? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 4);
        acquire.bindLong(1, i2);
        acquire.bindLong(2, i3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        if (str2 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getStepsForWeekByType(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE activity_type=? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 3);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int getStepsWithoutActivityMapping(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(total_steps) FROM workout_session WHERE strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<TennisSample> getTennisSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tennis_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        TennisSample tennisSample = new TennisSample();
                        tennisSample.set_id(query.getInt(columnIndexOrThrow));
                        tennisSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        tennisSample.sess_id = query.getString(columnIndexOrThrow3);
                        tennisSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        tennisSample.setSampleData(sampleData);
                        arrayList.add(tennisSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    TennisSample tennisSample2 = new TennisSample();
                    tennisSample2.set_id(query.getInt(columnIndexOrThrow));
                    tennisSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    tennisSample2.sess_id = query.getString(columnIndexOrThrow3);
                    tennisSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    tennisSample2.setSampleData(sampleData);
                    arrayList.add(tennisSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<TennisSample> getTennisSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tennis_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    TennisSample tennisSample = new TennisSample();
                    tennisSample.set_id(query.getInt(columnIndexOrThrow));
                    tennisSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    tennisSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    tennisSample.setStepCount(query.getInt(i6));
                    tennisSample.setSampleData(sampleData);
                    arrayList.add(tennisSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                TennisSample tennisSample2 = new TennisSample();
                tennisSample2.set_id(query.getInt(columnIndexOrThrow));
                tennisSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                tennisSample2.sess_id = query.getString(i42);
                int i62 = i3;
                tennisSample2.setStepCount(query.getInt(i62));
                tennisSample2.setSampleData(sampleData);
                arrayList.add(tennisSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<TennisSample>> getTennisSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tennis_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"tennis_sample"}, false, new m0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<TreadmillSample> getTreadMillSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM treadmill_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    TreadmillSample treadmillSample = new TreadmillSample();
                    treadmillSample.set_id(query.getInt(columnIndexOrThrow));
                    treadmillSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    treadmillSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    treadmillSample.setStepCount(query.getInt(i6));
                    treadmillSample.setSampleData(sampleData);
                    arrayList.add(treadmillSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                TreadmillSample treadmillSample2 = new TreadmillSample();
                treadmillSample2.set_id(query.getInt(columnIndexOrThrow));
                treadmillSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                treadmillSample2.sess_id = query.getString(i42);
                int i62 = i3;
                treadmillSample2.setStepCount(query.getInt(i62));
                treadmillSample2.setSampleData(sampleData);
                arrayList.add(treadmillSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<TreadmillSample> getTreadmillSamplesBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM treadmill_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        TreadmillSample treadmillSample = new TreadmillSample();
                        treadmillSample.set_id(query.getInt(columnIndexOrThrow));
                        treadmillSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        treadmillSample.sess_id = query.getString(columnIndexOrThrow3);
                        treadmillSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        treadmillSample.setSampleData(sampleData);
                        arrayList.add(treadmillSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    TreadmillSample treadmillSample2 = new TreadmillSample();
                    treadmillSample2.set_id(query.getInt(columnIndexOrThrow));
                    treadmillSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    treadmillSample2.sess_id = query.getString(columnIndexOrThrow3);
                    treadmillSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    treadmillSample2.setSampleData(sampleData);
                    arrayList.add(treadmillSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<TreadmillSample>> getTreadmillSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM treadmill_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"treadmill_sample"}, false, new s0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<EntityWorkoutSession> getUnSyncedSessions() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z2;
        int i2;
        Integer valueOf;
        Integer valueOf2;
        Float valueOf3;
        Float valueOf4;
        Integer valueOf5;
        Integer valueOf6;
        Float valueOf7;
        Float valueOf8;
        Integer valueOf9;
        int i3;
        Integer valueOf10;
        Integer valueOf11;
        Integer valueOf12;
        Integer valueOf13;
        Integer valueOf14;
        Float valueOf15;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE sent_to_server=0", 0);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
                    ArrayList arrayList2 = arrayList;
                    entityWorkoutSession.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession.setSession_date(query.getString(columnIndexOrThrow6));
                    int i5 = columnIndexOrThrow2;
                    int i6 = columnIndexOrThrow3;
                    entityWorkoutSession.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession.setSession_duration(query.getInt(columnIndexOrThrow13));
                    int i7 = i4;
                    entityWorkoutSession.setSteps_sampling_rate(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    int i9 = columnIndexOrThrow;
                    entityWorkoutSession.setHr_sampling_rate(query.getInt(i8));
                    int i10 = columnIndexOrThrow16;
                    int i11 = columnIndexOrThrow12;
                    entityWorkoutSession.setTotal_steps(query.getInt(i10));
                    int i12 = columnIndexOrThrow17;
                    entityWorkoutSession.setTotal_calories(query.getFloat(i12));
                    int i13 = columnIndexOrThrow18;
                    entityWorkoutSession.setTotal_distance(query.getInt(i13));
                    int i14 = columnIndexOrThrow19;
                    entityWorkoutSession.setMax_hr(query.getInt(i14));
                    int i15 = columnIndexOrThrow20;
                    entityWorkoutSession.setMin_hr(query.getInt(i15));
                    int i16 = columnIndexOrThrow21;
                    entityWorkoutSession.setAvg_hr(query.getInt(i16));
                    int i17 = columnIndexOrThrow22;
                    entityWorkoutSession.setPace(query.getFloat(i17));
                    int i18 = columnIndexOrThrow23;
                    entityWorkoutSession.setFatigue_level(query.getInt(i18));
                    int i19 = columnIndexOrThrow24;
                    if (query.getInt(i19) != 0) {
                        columnIndexOrThrow24 = i19;
                        z2 = true;
                    } else {
                        columnIndexOrThrow24 = i19;
                        z2 = false;
                    }
                    entityWorkoutSession.setIssenttoserver(z2);
                    int i20 = columnIndexOrThrow25;
                    entityWorkoutSession.setSession_place(query.getString(i20));
                    int i21 = columnIndexOrThrow26;
                    entityWorkoutSession.setMoodaftersession(query.getString(i21));
                    int i22 = columnIndexOrThrow27;
                    entityWorkoutSession.setFeedback(query.getString(i22));
                    int i23 = columnIndexOrThrow28;
                    entityWorkoutSession.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(i23)));
                    int i24 = columnIndexOrThrow29;
                    columnIndexOrThrow29 = i24;
                    entityWorkoutSession.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(i24)));
                    columnIndexOrThrow28 = i23;
                    int i25 = columnIndexOrThrow30;
                    entityWorkoutSession.setAppConnectivityCode(query.getString(i25));
                    int i26 = columnIndexOrThrow31;
                    if (query.isNull(i26)) {
                        i2 = i25;
                        valueOf = null;
                    } else {
                        i2 = i25;
                        valueOf = Integer.valueOf(query.getInt(i26));
                    }
                    entityWorkoutSession.setAvgStepFrequency(valueOf);
                    int i27 = columnIndexOrThrow32;
                    if (query.isNull(i27)) {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow32 = i27;
                        valueOf2 = Integer.valueOf(query.getInt(i27));
                    }
                    entityWorkoutSession.setMaxStepFrequency(valueOf2);
                    int i28 = columnIndexOrThrow33;
                    if (query.isNull(i28)) {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow33 = i28;
                        valueOf3 = Float.valueOf(query.getFloat(i28));
                    }
                    entityWorkoutSession.setAvgSpeed(valueOf3);
                    int i29 = columnIndexOrThrow34;
                    if (query.isNull(i29)) {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow34 = i29;
                        valueOf4 = Float.valueOf(query.getFloat(i29));
                    }
                    entityWorkoutSession.setMaxSpeed(valueOf4);
                    int i30 = columnIndexOrThrow35;
                    if (query.isNull(i30)) {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = null;
                    } else {
                        columnIndexOrThrow35 = i30;
                        valueOf5 = Integer.valueOf(query.getInt(i30));
                    }
                    entityWorkoutSession.setAvgStrideLength(valueOf5);
                    int i31 = columnIndexOrThrow36;
                    if (query.isNull(i31)) {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = null;
                    } else {
                        columnIndexOrThrow36 = i31;
                        valueOf6 = Integer.valueOf(query.getInt(i31));
                    }
                    entityWorkoutSession.setMaxStrideLength(valueOf6);
                    int i32 = columnIndexOrThrow37;
                    if (query.isNull(i32)) {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = null;
                    } else {
                        columnIndexOrThrow37 = i32;
                        valueOf7 = Float.valueOf(query.getFloat(i32));
                    }
                    entityWorkoutSession.setAvgPace(valueOf7);
                    int i33 = columnIndexOrThrow38;
                    if (query.isNull(i33)) {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = null;
                    } else {
                        columnIndexOrThrow38 = i33;
                        valueOf8 = Float.valueOf(query.getFloat(i33));
                    }
                    entityWorkoutSession.setMaxPace(valueOf8);
                    int i34 = columnIndexOrThrow39;
                    if (query.isNull(i34)) {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = null;
                    } else {
                        columnIndexOrThrow39 = i34;
                        valueOf9 = Integer.valueOf(query.getInt(i34));
                    }
                    entityWorkoutSession.setTotalStrokes(valueOf9);
                    int i35 = columnIndexOrThrow40;
                    entityWorkoutSession.setSwimmingStyle(query.getString(i35));
                    int i36 = columnIndexOrThrow41;
                    if (query.isNull(i36)) {
                        i3 = i35;
                        valueOf10 = null;
                    } else {
                        i3 = i35;
                        valueOf10 = Integer.valueOf(query.getInt(i36));
                    }
                    entityWorkoutSession.setPoolLength(valueOf10);
                    int i37 = columnIndexOrThrow42;
                    if (query.isNull(i37)) {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = null;
                    } else {
                        columnIndexOrThrow42 = i37;
                        valueOf11 = Integer.valueOf(query.getInt(i37));
                    }
                    entityWorkoutSession.setTotalLaps(valueOf11);
                    int i38 = columnIndexOrThrow43;
                    if (query.isNull(i38)) {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = null;
                    } else {
                        columnIndexOrThrow43 = i38;
                        valueOf12 = Integer.valueOf(query.getInt(i38));
                    }
                    entityWorkoutSession.setAvgSwolf(valueOf12);
                    int i39 = columnIndexOrThrow44;
                    if (query.isNull(i39)) {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = null;
                    } else {
                        columnIndexOrThrow44 = i39;
                        valueOf13 = Integer.valueOf(query.getInt(i39));
                    }
                    entityWorkoutSession.setAvgStrokeFreq(valueOf13);
                    int i40 = columnIndexOrThrow45;
                    if (query.isNull(i40)) {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = null;
                    } else {
                        columnIndexOrThrow45 = i40;
                        valueOf14 = Integer.valueOf(query.getInt(i40));
                    }
                    entityWorkoutSession.setFromHAR(valueOf14);
                    int i41 = columnIndexOrThrow46;
                    if (query.isNull(i41)) {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = null;
                    } else {
                        columnIndexOrThrow46 = i41;
                        valueOf15 = Float.valueOf(query.getFloat(i41));
                    }
                    entityWorkoutSession.setMinPace(valueOf15);
                    arrayList2.add(entityWorkoutSession);
                    columnIndexOrThrow40 = i3;
                    columnIndexOrThrow41 = i36;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow17 = i12;
                    columnIndexOrThrow18 = i13;
                    columnIndexOrThrow19 = i14;
                    columnIndexOrThrow20 = i15;
                    columnIndexOrThrow21 = i16;
                    columnIndexOrThrow22 = i17;
                    columnIndexOrThrow23 = i18;
                    columnIndexOrThrow25 = i20;
                    columnIndexOrThrow26 = i21;
                    columnIndexOrThrow27 = i22;
                    i4 = i7;
                    columnIndexOrThrow2 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow3 = i6;
                    int i42 = i2;
                    columnIndexOrThrow31 = i26;
                    columnIndexOrThrow30 = i42;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<WalkSample> getWalkSamplesBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM walk_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        WalkSample walkSample = new WalkSample();
                        walkSample.set_id(query.getInt(columnIndexOrThrow));
                        walkSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        walkSample.sess_id = query.getString(columnIndexOrThrow3);
                        walkSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        walkSample.setSampleData(sampleData);
                        arrayList.add(walkSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    WalkSample walkSample2 = new WalkSample();
                    walkSample2.set_id(query.getInt(columnIndexOrThrow));
                    walkSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    walkSample2.sess_id = query.getString(columnIndexOrThrow3);
                    walkSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    walkSample2.setSampleData(sampleData);
                    arrayList.add(walkSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<WalkSample> getWalkSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM walk_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    WalkSample walkSample = new WalkSample();
                    walkSample.set_id(query.getInt(columnIndexOrThrow));
                    walkSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    walkSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    walkSample.setStepCount(query.getInt(i6));
                    walkSample.setSampleData(sampleData);
                    arrayList.add(walkSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                WalkSample walkSample2 = new WalkSample();
                walkSample2.set_id(query.getInt(columnIndexOrThrow));
                walkSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                walkSample2.sess_id = query.getString(i42);
                int i62 = i3;
                walkSample2.setStepCount(query.getInt(i62));
                walkSample2.setSampleData(sampleData);
                arrayList.add(walkSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<WalkSample>> getWalkSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM walk_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"walk_sample"}, false, new c0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<WorkoutSample> getWorkoutSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        WorkoutSample workoutSample = new WorkoutSample();
                        workoutSample.set_id(query.getInt(columnIndexOrThrow));
                        workoutSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        workoutSample.sess_id = query.getString(columnIndexOrThrow3);
                        workoutSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        workoutSample.setSampleData(sampleData);
                        arrayList.add(workoutSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    WorkoutSample workoutSample2 = new WorkoutSample();
                    workoutSample2.set_id(query.getInt(columnIndexOrThrow));
                    workoutSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    workoutSample2.sess_id = query.getString(columnIndexOrThrow3);
                    workoutSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    workoutSample2.setSampleData(sampleData);
                    arrayList.add(workoutSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<WorkoutSample> getWorkoutSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    WorkoutSample workoutSample = new WorkoutSample();
                    workoutSample.set_id(query.getInt(columnIndexOrThrow));
                    workoutSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    workoutSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    workoutSample.setStepCount(query.getInt(i6));
                    workoutSample.setSampleData(sampleData);
                    arrayList.add(workoutSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                WorkoutSample workoutSample2 = new WorkoutSample();
                workoutSample2.set_id(query.getInt(columnIndexOrThrow));
                workoutSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                workoutSample2.sess_id = query.getString(i42);
                int i62 = i3;
                workoutSample2.setStepCount(query.getInt(i62));
                workoutSample2.setSampleData(sampleData);
                arrayList.add(workoutSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<WorkoutSample>> getWorkoutSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_sample"}, false, new n0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<YogaSample> getYogaSamplesBySessionId(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        SampleData sampleData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM yoga_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                try {
                    if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                        roomSQLiteQuery = acquire;
                        sampleData = null;
                        YogaSample yogaSample = new YogaSample();
                        yogaSample.set_id(query.getInt(columnIndexOrThrow));
                        yogaSample.setSeg_id(query.getString(columnIndexOrThrow2));
                        yogaSample.sess_id = query.getString(columnIndexOrThrow3);
                        yogaSample.setStepCount(query.getInt(columnIndexOrThrow4));
                        yogaSample.setSampleData(sampleData);
                        arrayList.add(yogaSample);
                        acquire = roomSQLiteQuery;
                    }
                    sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                    sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                    sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                    sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                    sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                    sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                    sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                    YogaSample yogaSample2 = new YogaSample();
                    yogaSample2.set_id(query.getInt(columnIndexOrThrow));
                    yogaSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                    yogaSample2.sess_id = query.getString(columnIndexOrThrow3);
                    yogaSample2.setStepCount(query.getInt(columnIndexOrThrow4));
                    yogaSample2.setSampleData(sampleData);
                    arrayList.add(yogaSample2);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
                sampleData = new SampleData();
                roomSQLiteQuery = acquire;
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public List<YogaSample> getYogaSamplesBySessionIdAndSegmentId(String str, String str2) {
        SampleData sampleData;
        int i2;
        int i3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM yoga_sample WHERE seg_id=? AND sess_id=?", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "seg_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sess_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "step_count");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "calories");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "distance");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "hr_value");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "speed_value");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10) && query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow3;
                    i3 = columnIndexOrThrow4;
                    sampleData = null;
                    YogaSample yogaSample = new YogaSample();
                    yogaSample.set_id(query.getInt(columnIndexOrThrow));
                    yogaSample.setSeg_id(query.getString(columnIndexOrThrow2));
                    int i4 = i2;
                    int i5 = columnIndexOrThrow;
                    yogaSample.sess_id = query.getString(i4);
                    int i6 = i3;
                    yogaSample.setStepCount(query.getInt(i6));
                    yogaSample.setSampleData(sampleData);
                    arrayList.add(yogaSample);
                    columnIndexOrThrow3 = i4;
                    columnIndexOrThrow4 = i6;
                    columnIndexOrThrow = i5;
                }
                sampleData = new SampleData();
                i2 = columnIndexOrThrow3;
                i3 = columnIndexOrThrow4;
                sampleData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                sampleData.setCalories(query.getFloat(columnIndexOrThrow6));
                sampleData.setDistance(query.getInt(columnIndexOrThrow7));
                sampleData.setHr_value(query.getInt(columnIndexOrThrow8));
                sampleData.setSpeed_value(query.getFloat(columnIndexOrThrow9));
                sampleData.setLatitude(query.getDouble(columnIndexOrThrow10));
                sampleData.setLongitude(query.getDouble(columnIndexOrThrow11));
                YogaSample yogaSample2 = new YogaSample();
                yogaSample2.set_id(query.getInt(columnIndexOrThrow));
                yogaSample2.setSeg_id(query.getString(columnIndexOrThrow2));
                int i42 = i2;
                int i52 = columnIndexOrThrow;
                yogaSample2.sess_id = query.getString(i42);
                int i62 = i3;
                yogaSample2.setStepCount(query.getInt(i62));
                yogaSample2.setSampleData(sampleData);
                arrayList.add(yogaSample2);
                columnIndexOrThrow3 = i42;
                columnIndexOrThrow4 = i62;
                columnIndexOrThrow = i52;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<YogaSample>> getYogaSamplesLiveDataBy(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM yoga_sample WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"yoga_sample"}, false, new o0(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertActivityDataSamples(List<ActivityDataSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.w.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertBadmintonSamples(List<BadmintonSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.g.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertBasketBallSamples(List<BasketBallSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.h.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertClimbingSamples(List<ClimbingSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.q.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertCyclingSamples(List<CyclingSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.i.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertDanceSamples(List<DanceSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.k.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertEllipticalSamples(List<EllipticalSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.u.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertFootballSamples(List<FootballSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.l.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertFreeExerciseSamples(List<FreeExerciseSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.t.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertHikingSamples(List<HikingSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.m.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertMeditationSamples(List<MeditationSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.j.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertPhysicalActivitySamples(List<PhysicalActivitySample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.e.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertRowingMachineSamples(List<RowingMachineSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.v.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertRunSamples(List<RunSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.f.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertSession(EntityWorkoutSession entityWorkoutSession) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<EntityWorkoutSession>) entityWorkoutSession);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertSessionSegment(EntityWorkoutSessionSegment entityWorkoutSessionSegment) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter<EntityWorkoutSessionSegment>) entityWorkoutSessionSegment);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertSessionSegments(List<EntityWorkoutSessionSegment> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.c.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertSkippingSamples(List<SkippingSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.s.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertTennisSamples(List<TennisSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.n.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertTreadmillSamples(List<TreadmillSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.r.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertWalkSamples(List<WalkSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.d.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertWorkoutSamples(List<WorkoutSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.o.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void insertYogaSamples(List<YogaSample> list) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.p.insert(list);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int isClientRefIdExists(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM workout_session WHERE client_ref_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int isDataPresentInDb(String str, String str2, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM workout_session WHERE  serial_no=? AND activity_type=? AND start_time=?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        acquire.bindLong(3, j2);
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int isSampleDataExists(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM workout_session_segment WHERE sess_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public int isSessionIdExists(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM workout_session WHERE session_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public void updateSession(EntityWorkoutSession entityWorkoutSession) {
        this.f2800a.assertNotSuspendingTransaction();
        this.f2800a.beginTransaction();
        try {
            this.y.handle(entityWorkoutSession);
            this.f2800a.setTransactionSuccessful();
        } finally {
            this.f2800a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE activity_type=? AND serial_no=COALESCE(?,serial_no) ORDER BY start_time DESC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return this.f2800a.getInvalidationTracker().createLiveData(new String[]{"workout_session"}, false, new x(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.SessionDAO
    public EntityWorkoutSession getBestSessionByActivityType(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityWorkoutSession entityWorkoutSession;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workout_session WHERE activity_type=? AND  indoor_outdoor=? ORDER BY total_calories DESC LIMIT 1", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.f2800a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2800a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "serial_no");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "client_ref_id");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "end_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "activity_type");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "target_baseunit");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, WorkoutConstants.INDOOR_OUTDOOR);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "session_duration");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "steps_sampling_rate");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "hr_sampling_rate");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "total_steps");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "total_calories");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "total_distance");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "max_hr");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "min_hr");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "avg_hr");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "pace");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "fatigue_level");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "sent_to_server");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "session_place");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "mood_after_session");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, com.ido.ble.event.stat.one.d.O);
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "hrZoneRanges");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "timespent_per_heartratezone");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "appConnectivity");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "avgStepFrequency");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "maxStepFrequency");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "avgSpeed");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "maxSpeed");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "avgStrideLength");
                int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "maxStrideLength");
                int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "avgPace");
                int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "maxPace");
                int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "totalStrokes");
                int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "swimmingStyle");
                int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "poolLength");
                int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "totalLaps");
                int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, "avgSwolf");
                int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "avgStrokeFreq");
                int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "isFromHAR");
                int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "minPace");
                if (query.moveToFirst()) {
                    EntityWorkoutSession entityWorkoutSession2 = new EntityWorkoutSession();
                    entityWorkoutSession2.session_id = query.getString(columnIndexOrThrow);
                    entityWorkoutSession2.setCategoryId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    entityWorkoutSession2.setActivityId(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    entityWorkoutSession2.setSerialNo(query.getString(columnIndexOrThrow4));
                    entityWorkoutSession2.setClient_ref_id(query.getString(columnIndexOrThrow5));
                    entityWorkoutSession2.setSession_date(query.getString(columnIndexOrThrow6));
                    entityWorkoutSession2.setStart_time(query.getLong(columnIndexOrThrow7));
                    entityWorkoutSession2.setEnd_time(query.getLong(columnIndexOrThrow8));
                    entityWorkoutSession2.setActivity_type(query.getString(columnIndexOrThrow9));
                    entityWorkoutSession2.setTarget(query.getString(columnIndexOrThrow10));
                    entityWorkoutSession2.setTarget_baseunit(query.getString(columnIndexOrThrow11));
                    entityWorkoutSession2.setIndoor_outdoor(query.getString(columnIndexOrThrow12));
                    entityWorkoutSession2.setSession_duration(query.getInt(columnIndexOrThrow13));
                    entityWorkoutSession2.setSteps_sampling_rate(query.getInt(columnIndexOrThrow14));
                    entityWorkoutSession2.setHr_sampling_rate(query.getInt(columnIndexOrThrow15));
                    entityWorkoutSession2.setTotal_steps(query.getInt(columnIndexOrThrow16));
                    entityWorkoutSession2.setTotal_calories(query.getFloat(columnIndexOrThrow17));
                    entityWorkoutSession2.setTotal_distance(query.getInt(columnIndexOrThrow18));
                    entityWorkoutSession2.setMax_hr(query.getInt(columnIndexOrThrow19));
                    entityWorkoutSession2.setMin_hr(query.getInt(columnIndexOrThrow20));
                    entityWorkoutSession2.setAvg_hr(query.getInt(columnIndexOrThrow21));
                    entityWorkoutSession2.setPace(query.getFloat(columnIndexOrThrow22));
                    entityWorkoutSession2.setFatigue_level(query.getInt(columnIndexOrThrow23));
                    entityWorkoutSession2.setIssenttoserver(query.getInt(columnIndexOrThrow24) != 0);
                    entityWorkoutSession2.setSession_place(query.getString(columnIndexOrThrow25));
                    entityWorkoutSession2.setMoodaftersession(query.getString(columnIndexOrThrow26));
                    entityWorkoutSession2.setFeedback(query.getString(columnIndexOrThrow27));
                    entityWorkoutSession2.setHrZoneRanges(Covertors.geHeartRateZoneRangesFrom(query.getString(columnIndexOrThrow28)));
                    entityWorkoutSession2.setTimespent_per_heartratezone(Covertors.getHeartRateZoneTimeListFrom(query.getString(columnIndexOrThrow29)));
                    entityWorkoutSession2.setAppConnectivityCode(query.getString(columnIndexOrThrow30));
                    entityWorkoutSession2.setAvgStepFrequency(query.isNull(columnIndexOrThrow31) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow31)));
                    entityWorkoutSession2.setMaxStepFrequency(query.isNull(columnIndexOrThrow32) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow32)));
                    entityWorkoutSession2.setAvgSpeed(query.isNull(columnIndexOrThrow33) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow33)));
                    entityWorkoutSession2.setMaxSpeed(query.isNull(columnIndexOrThrow34) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow34)));
                    entityWorkoutSession2.setAvgStrideLength(query.isNull(columnIndexOrThrow35) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow35)));
                    entityWorkoutSession2.setMaxStrideLength(query.isNull(columnIndexOrThrow36) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow36)));
                    entityWorkoutSession2.setAvgPace(query.isNull(columnIndexOrThrow37) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow37)));
                    entityWorkoutSession2.setMaxPace(query.isNull(columnIndexOrThrow38) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow38)));
                    entityWorkoutSession2.setTotalStrokes(query.isNull(columnIndexOrThrow39) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow39)));
                    entityWorkoutSession2.setSwimmingStyle(query.getString(columnIndexOrThrow40));
                    entityWorkoutSession2.setPoolLength(query.isNull(columnIndexOrThrow41) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow41)));
                    entityWorkoutSession2.setTotalLaps(query.isNull(columnIndexOrThrow42) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow42)));
                    entityWorkoutSession2.setAvgSwolf(query.isNull(columnIndexOrThrow43) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow43)));
                    entityWorkoutSession2.setAvgStrokeFreq(query.isNull(columnIndexOrThrow44) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow44)));
                    entityWorkoutSession2.setFromHAR(query.isNull(columnIndexOrThrow45) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow45)));
                    entityWorkoutSession2.setMinPace(query.isNull(columnIndexOrThrow46) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow46)));
                    entityWorkoutSession = entityWorkoutSession2;
                } else {
                    entityWorkoutSession = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityWorkoutSession;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }
}
