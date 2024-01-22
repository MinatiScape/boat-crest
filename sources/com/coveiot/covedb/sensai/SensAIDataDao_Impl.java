package com.coveiot.covedb.sensai;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.covedb.Convertors;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class SensAIDataDao_Impl implements SensAIDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6968a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;
    public final EntityDeletionOrUpdateAdapter d;
    public final SharedSQLiteStatement e;
    public final SharedSQLiteStatement f;
    public final SharedSQLiteStatement g;
    public final SharedSQLiteStatement h;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<SensAIActivitySummary> {
        public a(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SensAIActivitySummary sensAIActivitySummary) {
            if (sensAIActivitySummary.getSessionId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, sensAIActivitySummary.getSessionId());
            }
            if (sensAIActivitySummary.getClientRefID() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, sensAIActivitySummary.getClientRefID());
            }
            if (sensAIActivitySummary.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, sensAIActivitySummary.getMacAddress());
            }
            if (sensAIActivitySummary.getTimestamp() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindLong(4, sensAIActivitySummary.getTimestamp().longValue());
            }
            supportSQLiteStatement.bindLong(5, sensAIActivitySummary.getActivityType());
            supportSQLiteStatement.bindLong(6, sensAIActivitySummary.getDurationSec());
            supportSQLiteStatement.bindLong(7, sensAIActivitySummary.getTotalSteps());
            supportSQLiteStatement.bindDouble(8, sensAIActivitySummary.getTotalCalories());
            supportSQLiteStatement.bindLong(9, sensAIActivitySummary.getHand());
            supportSQLiteStatement.bindLong(10, sensAIActivitySummary.getGoalType());
            supportSQLiteStatement.bindLong(11, sensAIActivitySummary.getTargetGoalValue());
            supportSQLiteStatement.bindLong(12, sensAIActivitySummary.getGoalCompletionPct());
            supportSQLiteStatement.bindLong(13, sensAIActivitySummary.getMaxHR());
            supportSQLiteStatement.bindLong(14, sensAIActivitySummary.getAvgHR());
            supportSQLiteStatement.bindLong(15, sensAIActivitySummary.getTotalSwings());
            supportSQLiteStatement.bindLong(16, sensAIActivitySummary.getPlayed());
            supportSQLiteStatement.bindLong(17, sensAIActivitySummary.getHitPct());
            supportSQLiteStatement.bindLong(18, sensAIActivitySummary.getMaxHandSpeed());
            supportSQLiteStatement.bindLong(19, sensAIActivitySummary.getAvgHandSpeed());
            supportSQLiteStatement.bindLong(20, sensAIActivitySummary.getBowlingType());
            supportSQLiteStatement.bindLong(21, sensAIActivitySummary.getTotalBallsBowled());
            supportSQLiteStatement.bindLong(22, sensAIActivitySummary.getMaxArmSpeed());
            supportSQLiteStatement.bindLong(23, sensAIActivitySummary.getMinArmSpeed());
            supportSQLiteStatement.bindLong(24, sensAIActivitySummary.getAvgArmSpeed());
            supportSQLiteStatement.bindLong(25, sensAIActivitySummary.isSavedServer() ? 1L : 0L);
            supportSQLiteStatement.bindLong(26, sensAIActivitySummary.isAddToCompare() ? 1L : 0L);
            supportSQLiteStatement.bindLong(27, sensAIActivitySummary.isDataAggregateSaved() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `sensai_activity_summary`(`session_id`,`client_ref_id`,`serial_no`,`timestamp`,`activity_type`,`duration_sec`,`total_steps`,`total_calories`,`hand`,`goal_type`,`target_goal_value`,`goal_completion_pct`,`max_hr`,`avg_hr`,`total_swings`,`played`,`hit_pct`,`max_hand_speed`,`avg_hand_speed`,`bowling_type`,`total_balls_bowled`,`max_arm_speed`,`min_arm_speed`,`avg_arm_speed`,`is_saved_server`,`is_add_to_compare`,`is_data_aggregate_saved`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<SensAIActivitySummaryDetails> {
        public b(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SensAIActivitySummaryDetails sensAIActivitySummaryDetails) {
            if (sensAIActivitySummaryDetails.getSessionId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, sensAIActivitySummaryDetails.getSessionId());
            }
            if (sensAIActivitySummaryDetails.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, sensAIActivitySummaryDetails.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, sensAIActivitySummaryDetails.getActivityType());
            supportSQLiteStatement.bindLong(4, sensAIActivitySummaryDetails.getDetailsDataNum());
            if (sensAIActivitySummaryDetails.getUnixTimeStamp() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindLong(5, sensAIActivitySummaryDetails.getUnixTimeStamp().longValue());
            }
            String frommArrayLisr = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getHandSpeed());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, frommArrayLisr);
            }
            String frommArrayLisr2 = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getHr());
            if (frommArrayLisr2 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, frommArrayLisr2);
            }
            String frommArrayLisr3 = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getSteps());
            if (frommArrayLisr3 == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, frommArrayLisr3);
            }
            String frommArrayLisr4 = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getDistance());
            if (frommArrayLisr4 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, frommArrayLisr4);
            }
            String frommArrayLisr5 = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getCalories());
            if (frommArrayLisr5 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, frommArrayLisr5);
            }
            String frommArrayLisr6 = Convertors.frommArrayLisr(sensAIActivitySummaryDetails.getHitOrMiss());
            if (frommArrayLisr6 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, frommArrayLisr6);
            }
            supportSQLiteStatement.bindLong(12, sensAIActivitySummaryDetails.isFeedbackSaved() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `sensai_activity_summary_details`(`session_id`,`serial_no`,`activity_type`,`details_data_num`,`unix_time_stamp`,`hand_speed`,`hr`,`steps`,`distance`,`calories`,`hit_miss`,`is_feedback_saved`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends EntityDeletionOrUpdateAdapter<SensAIActivitySummary> {
        public c(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SensAIActivitySummary sensAIActivitySummary) {
            if (sensAIActivitySummary.getSessionId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, sensAIActivitySummary.getSessionId());
            }
            if (sensAIActivitySummary.getClientRefID() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, sensAIActivitySummary.getClientRefID());
            }
            if (sensAIActivitySummary.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, sensAIActivitySummary.getMacAddress());
            }
            if (sensAIActivitySummary.getTimestamp() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindLong(4, sensAIActivitySummary.getTimestamp().longValue());
            }
            supportSQLiteStatement.bindLong(5, sensAIActivitySummary.getActivityType());
            supportSQLiteStatement.bindLong(6, sensAIActivitySummary.getDurationSec());
            supportSQLiteStatement.bindLong(7, sensAIActivitySummary.getTotalSteps());
            supportSQLiteStatement.bindDouble(8, sensAIActivitySummary.getTotalCalories());
            supportSQLiteStatement.bindLong(9, sensAIActivitySummary.getHand());
            supportSQLiteStatement.bindLong(10, sensAIActivitySummary.getGoalType());
            supportSQLiteStatement.bindLong(11, sensAIActivitySummary.getTargetGoalValue());
            supportSQLiteStatement.bindLong(12, sensAIActivitySummary.getGoalCompletionPct());
            supportSQLiteStatement.bindLong(13, sensAIActivitySummary.getMaxHR());
            supportSQLiteStatement.bindLong(14, sensAIActivitySummary.getAvgHR());
            supportSQLiteStatement.bindLong(15, sensAIActivitySummary.getTotalSwings());
            supportSQLiteStatement.bindLong(16, sensAIActivitySummary.getPlayed());
            supportSQLiteStatement.bindLong(17, sensAIActivitySummary.getHitPct());
            supportSQLiteStatement.bindLong(18, sensAIActivitySummary.getMaxHandSpeed());
            supportSQLiteStatement.bindLong(19, sensAIActivitySummary.getAvgHandSpeed());
            supportSQLiteStatement.bindLong(20, sensAIActivitySummary.getBowlingType());
            supportSQLiteStatement.bindLong(21, sensAIActivitySummary.getTotalBallsBowled());
            supportSQLiteStatement.bindLong(22, sensAIActivitySummary.getMaxArmSpeed());
            supportSQLiteStatement.bindLong(23, sensAIActivitySummary.getMinArmSpeed());
            supportSQLiteStatement.bindLong(24, sensAIActivitySummary.getAvgArmSpeed());
            supportSQLiteStatement.bindLong(25, sensAIActivitySummary.isSavedServer() ? 1L : 0L);
            supportSQLiteStatement.bindLong(26, sensAIActivitySummary.isAddToCompare() ? 1L : 0L);
            supportSQLiteStatement.bindLong(27, sensAIActivitySummary.isDataAggregateSaved() ? 1L : 0L);
            if (sensAIActivitySummary.getSessionId() == null) {
                supportSQLiteStatement.bindNull(28);
            } else {
                supportSQLiteStatement.bindString(28, sensAIActivitySummary.getSessionId());
            }
            if (sensAIActivitySummary.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(29);
            } else {
                supportSQLiteStatement.bindString(29, sensAIActivitySummary.getMacAddress());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR REPLACE `sensai_activity_summary` SET `session_id` = ?,`client_ref_id` = ?,`serial_no` = ?,`timestamp` = ?,`activity_type` = ?,`duration_sec` = ?,`total_steps` = ?,`total_calories` = ?,`hand` = ?,`goal_type` = ?,`target_goal_value` = ?,`goal_completion_pct` = ?,`max_hr` = ?,`avg_hr` = ?,`total_swings` = ?,`played` = ?,`hit_pct` = ?,`max_hand_speed` = ?,`avg_hand_speed` = ?,`bowling_type` = ?,`total_balls_bowled` = ?,`max_arm_speed` = ?,`min_arm_speed` = ?,`avg_arm_speed` = ?,`is_saved_server` = ?,`is_add_to_compare` = ?,`is_data_aggregate_saved` = ? WHERE `session_id` = ? AND `serial_no` = ?";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends SharedSQLiteStatement {
        public d(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sensai_activity_summary SET is_saved_server = ? , client_ref_id = ? WHERE serial_no=? AND session_id  =?";
        }
    }

    /* loaded from: classes8.dex */
    public class e extends SharedSQLiteStatement {
        public e(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sensai_activity_summary SET is_data_aggregate_saved = ? WHERE serial_no=? AND session_id  =?";
        }
    }

    /* loaded from: classes8.dex */
    public class f extends SharedSQLiteStatement {
        public f(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sensai_activity_summary SET is_add_to_compare = ? WHERE serial_no=? AND session_id  =?";
        }
    }

    /* loaded from: classes8.dex */
    public class g extends SharedSQLiteStatement {
        public g(SensAIDataDao_Impl sensAIDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sensai_activity_summary_details SET is_feedback_saved = ? WHERE serial_no=? AND session_id  =?";
        }
    }

    /* loaded from: classes8.dex */
    public class h extends ComputableLiveData<List<SensAIActivitySummary>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                h.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<SensAIActivitySummary> compute() {
            boolean z;
            boolean z2;
            boolean z3;
            if (this.g == null) {
                this.g = new a("sensai_activity_summary", new String[0]);
                SensAIDataDao_Impl.this.f6968a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SensAIDataDao_Impl.this.f6968a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
                int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
                int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
                int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
                int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
                int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
                int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                    ArrayList arrayList2 = arrayList;
                    sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                    sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                    sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                    sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                    sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                    int i2 = columnIndexOrThrow;
                    sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                    sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                    sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                    sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                    sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                    sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                    sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                    sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                    int i3 = i;
                    sensAIActivitySummary.setAvgHR(query.getInt(i3));
                    i = i3;
                    int i4 = columnIndexOrThrow15;
                    sensAIActivitySummary.setTotalSwings(query.getInt(i4));
                    columnIndexOrThrow15 = i4;
                    int i5 = columnIndexOrThrow16;
                    sensAIActivitySummary.setPlayed(query.getInt(i5));
                    columnIndexOrThrow16 = i5;
                    int i6 = columnIndexOrThrow17;
                    sensAIActivitySummary.setHitPct(query.getInt(i6));
                    columnIndexOrThrow17 = i6;
                    int i7 = columnIndexOrThrow18;
                    sensAIActivitySummary.setMaxHandSpeed(query.getInt(i7));
                    columnIndexOrThrow18 = i7;
                    int i8 = columnIndexOrThrow19;
                    sensAIActivitySummary.setAvgHandSpeed(query.getInt(i8));
                    columnIndexOrThrow19 = i8;
                    int i9 = columnIndexOrThrow20;
                    sensAIActivitySummary.setBowlingType(query.getInt(i9));
                    columnIndexOrThrow20 = i9;
                    int i10 = columnIndexOrThrow21;
                    sensAIActivitySummary.setTotalBallsBowled(query.getInt(i10));
                    columnIndexOrThrow21 = i10;
                    int i11 = columnIndexOrThrow22;
                    sensAIActivitySummary.setMaxArmSpeed(query.getInt(i11));
                    columnIndexOrThrow22 = i11;
                    int i12 = columnIndexOrThrow23;
                    sensAIActivitySummary.setMinArmSpeed(query.getInt(i12));
                    columnIndexOrThrow23 = i12;
                    int i13 = columnIndexOrThrow24;
                    sensAIActivitySummary.setAvgArmSpeed(query.getInt(i13));
                    int i14 = columnIndexOrThrow25;
                    if (query.getInt(i14) != 0) {
                        columnIndexOrThrow24 = i13;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i13;
                        z = false;
                    }
                    sensAIActivitySummary.setSavedServer(z);
                    int i15 = columnIndexOrThrow26;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow26 = i15;
                        z2 = true;
                    } else {
                        columnIndexOrThrow26 = i15;
                        z2 = false;
                    }
                    sensAIActivitySummary.setAddToCompare(z2);
                    int i16 = columnIndexOrThrow27;
                    if (query.getInt(i16) != 0) {
                        columnIndexOrThrow27 = i16;
                        z3 = true;
                    } else {
                        columnIndexOrThrow27 = i16;
                        z3 = false;
                    }
                    sensAIActivitySummary.setDataAggregateSaved(z3);
                    arrayList2.add(sensAIActivitySummary);
                    columnIndexOrThrow25 = i14;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
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

    /* loaded from: classes8.dex */
    public class i extends ComputableLiveData<List<SensAIActivitySummary>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                i.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<SensAIActivitySummary> compute() {
            boolean z;
            boolean z2;
            boolean z3;
            if (this.g == null) {
                this.g = new a("sensai_activity_summary", new String[0]);
                SensAIDataDao_Impl.this.f6968a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SensAIDataDao_Impl.this.f6968a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
                int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
                int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
                int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
                int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
                int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
                int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                    ArrayList arrayList2 = arrayList;
                    sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                    sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                    sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                    sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                    sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                    int i2 = columnIndexOrThrow;
                    sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                    sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                    sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                    sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                    sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                    sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                    sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                    sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                    int i3 = i;
                    sensAIActivitySummary.setAvgHR(query.getInt(i3));
                    i = i3;
                    int i4 = columnIndexOrThrow15;
                    sensAIActivitySummary.setTotalSwings(query.getInt(i4));
                    columnIndexOrThrow15 = i4;
                    int i5 = columnIndexOrThrow16;
                    sensAIActivitySummary.setPlayed(query.getInt(i5));
                    columnIndexOrThrow16 = i5;
                    int i6 = columnIndexOrThrow17;
                    sensAIActivitySummary.setHitPct(query.getInt(i6));
                    columnIndexOrThrow17 = i6;
                    int i7 = columnIndexOrThrow18;
                    sensAIActivitySummary.setMaxHandSpeed(query.getInt(i7));
                    columnIndexOrThrow18 = i7;
                    int i8 = columnIndexOrThrow19;
                    sensAIActivitySummary.setAvgHandSpeed(query.getInt(i8));
                    columnIndexOrThrow19 = i8;
                    int i9 = columnIndexOrThrow20;
                    sensAIActivitySummary.setBowlingType(query.getInt(i9));
                    columnIndexOrThrow20 = i9;
                    int i10 = columnIndexOrThrow21;
                    sensAIActivitySummary.setTotalBallsBowled(query.getInt(i10));
                    columnIndexOrThrow21 = i10;
                    int i11 = columnIndexOrThrow22;
                    sensAIActivitySummary.setMaxArmSpeed(query.getInt(i11));
                    columnIndexOrThrow22 = i11;
                    int i12 = columnIndexOrThrow23;
                    sensAIActivitySummary.setMinArmSpeed(query.getInt(i12));
                    columnIndexOrThrow23 = i12;
                    int i13 = columnIndexOrThrow24;
                    sensAIActivitySummary.setAvgArmSpeed(query.getInt(i13));
                    int i14 = columnIndexOrThrow25;
                    if (query.getInt(i14) != 0) {
                        columnIndexOrThrow24 = i13;
                        z = true;
                    } else {
                        columnIndexOrThrow24 = i13;
                        z = false;
                    }
                    sensAIActivitySummary.setSavedServer(z);
                    int i15 = columnIndexOrThrow26;
                    if (query.getInt(i15) != 0) {
                        columnIndexOrThrow26 = i15;
                        z2 = true;
                    } else {
                        columnIndexOrThrow26 = i15;
                        z2 = false;
                    }
                    sensAIActivitySummary.setAddToCompare(z2);
                    int i16 = columnIndexOrThrow27;
                    if (query.getInt(i16) != 0) {
                        columnIndexOrThrow27 = i16;
                        z3 = true;
                    } else {
                        columnIndexOrThrow27 = i16;
                        z3 = false;
                    }
                    sensAIActivitySummary.setDataAggregateSaved(z3);
                    arrayList2.add(sensAIActivitySummary);
                    columnIndexOrThrow25 = i14;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
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

    public SensAIDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6968a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
        this.f = new e(this, roomDatabase);
        this.g = new f(this, roomDatabase);
        this.h = new g(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public List<SensAIActivitySummary> getActivityBattingSummaryList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        boolean z;
        boolean z2;
        boolean z3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND is_saved_server = 0 AND activity_type = 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            int i2 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                ArrayList arrayList2 = arrayList;
                sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                int i3 = columnIndexOrThrow;
                sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                int i4 = i2;
                sensAIActivitySummary.setAvgHR(query.getInt(i4));
                int i5 = columnIndexOrThrow15;
                i2 = i4;
                sensAIActivitySummary.setTotalSwings(query.getInt(i5));
                columnIndexOrThrow15 = i5;
                int i6 = columnIndexOrThrow16;
                sensAIActivitySummary.setPlayed(query.getInt(i6));
                columnIndexOrThrow16 = i6;
                int i7 = columnIndexOrThrow17;
                sensAIActivitySummary.setHitPct(query.getInt(i7));
                columnIndexOrThrow17 = i7;
                int i8 = columnIndexOrThrow18;
                sensAIActivitySummary.setMaxHandSpeed(query.getInt(i8));
                columnIndexOrThrow18 = i8;
                int i9 = columnIndexOrThrow19;
                sensAIActivitySummary.setAvgHandSpeed(query.getInt(i9));
                columnIndexOrThrow19 = i9;
                int i10 = columnIndexOrThrow20;
                sensAIActivitySummary.setBowlingType(query.getInt(i10));
                columnIndexOrThrow20 = i10;
                int i11 = columnIndexOrThrow21;
                sensAIActivitySummary.setTotalBallsBowled(query.getInt(i11));
                columnIndexOrThrow21 = i11;
                int i12 = columnIndexOrThrow22;
                sensAIActivitySummary.setMaxArmSpeed(query.getInt(i12));
                columnIndexOrThrow22 = i12;
                int i13 = columnIndexOrThrow23;
                sensAIActivitySummary.setMinArmSpeed(query.getInt(i13));
                columnIndexOrThrow23 = i13;
                int i14 = columnIndexOrThrow24;
                sensAIActivitySummary.setAvgArmSpeed(query.getInt(i14));
                int i15 = columnIndexOrThrow25;
                if (query.getInt(i15) != 0) {
                    columnIndexOrThrow24 = i14;
                    z = true;
                } else {
                    columnIndexOrThrow24 = i14;
                    z = false;
                }
                sensAIActivitySummary.setSavedServer(z);
                int i16 = columnIndexOrThrow26;
                if (query.getInt(i16) != 0) {
                    columnIndexOrThrow26 = i16;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i16;
                    z2 = false;
                }
                sensAIActivitySummary.setAddToCompare(z2);
                int i17 = columnIndexOrThrow27;
                if (query.getInt(i17) != 0) {
                    columnIndexOrThrow27 = i17;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i17;
                    z3 = false;
                }
                sensAIActivitySummary.setDataAggregateSaved(z3);
                arrayList2.add(sensAIActivitySummary);
                columnIndexOrThrow25 = i15;
                arrayList = arrayList2;
                columnIndexOrThrow = i3;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public List<SensAIActivitySummary> getActivityBowlingSummaryList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        boolean z;
        boolean z2;
        boolean z3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND is_saved_server = 0 AND activity_type = 2", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            int i2 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                ArrayList arrayList2 = arrayList;
                sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                int i3 = columnIndexOrThrow;
                sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                int i4 = i2;
                sensAIActivitySummary.setAvgHR(query.getInt(i4));
                int i5 = columnIndexOrThrow15;
                i2 = i4;
                sensAIActivitySummary.setTotalSwings(query.getInt(i5));
                columnIndexOrThrow15 = i5;
                int i6 = columnIndexOrThrow16;
                sensAIActivitySummary.setPlayed(query.getInt(i6));
                columnIndexOrThrow16 = i6;
                int i7 = columnIndexOrThrow17;
                sensAIActivitySummary.setHitPct(query.getInt(i7));
                columnIndexOrThrow17 = i7;
                int i8 = columnIndexOrThrow18;
                sensAIActivitySummary.setMaxHandSpeed(query.getInt(i8));
                columnIndexOrThrow18 = i8;
                int i9 = columnIndexOrThrow19;
                sensAIActivitySummary.setAvgHandSpeed(query.getInt(i9));
                columnIndexOrThrow19 = i9;
                int i10 = columnIndexOrThrow20;
                sensAIActivitySummary.setBowlingType(query.getInt(i10));
                columnIndexOrThrow20 = i10;
                int i11 = columnIndexOrThrow21;
                sensAIActivitySummary.setTotalBallsBowled(query.getInt(i11));
                columnIndexOrThrow21 = i11;
                int i12 = columnIndexOrThrow22;
                sensAIActivitySummary.setMaxArmSpeed(query.getInt(i12));
                columnIndexOrThrow22 = i12;
                int i13 = columnIndexOrThrow23;
                sensAIActivitySummary.setMinArmSpeed(query.getInt(i13));
                columnIndexOrThrow23 = i13;
                int i14 = columnIndexOrThrow24;
                sensAIActivitySummary.setAvgArmSpeed(query.getInt(i14));
                int i15 = columnIndexOrThrow25;
                if (query.getInt(i15) != 0) {
                    columnIndexOrThrow24 = i14;
                    z = true;
                } else {
                    columnIndexOrThrow24 = i14;
                    z = false;
                }
                sensAIActivitySummary.setSavedServer(z);
                int i16 = columnIndexOrThrow26;
                if (query.getInt(i16) != 0) {
                    columnIndexOrThrow26 = i16;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i16;
                    z2 = false;
                }
                sensAIActivitySummary.setAddToCompare(z2);
                int i17 = columnIndexOrThrow27;
                if (query.getInt(i17) != 0) {
                    columnIndexOrThrow27 = i17;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i17;
                    z3 = false;
                }
                sensAIActivitySummary.setDataAggregateSaved(z3);
                arrayList2.add(sensAIActivitySummary);
                columnIndexOrThrow25 = i15;
                arrayList = arrayList2;
                columnIndexOrThrow = i3;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public SensAIActivitySummary getActivitySummary(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where session_id = ? AND serial_no = ?", 2);
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
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            SensAIActivitySummary sensAIActivitySummary = null;
            if (query.moveToFirst()) {
                SensAIActivitySummary sensAIActivitySummary2 = new SensAIActivitySummary();
                sensAIActivitySummary2.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary2.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary2.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary2.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary2.setActivityType(query.getInt(columnIndexOrThrow5));
                sensAIActivitySummary2.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary2.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary2.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary2.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary2.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary2.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary2.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary2.setMaxHR(query.getInt(columnIndexOrThrow13));
                sensAIActivitySummary2.setAvgHR(query.getInt(columnIndexOrThrow14));
                sensAIActivitySummary2.setTotalSwings(query.getInt(columnIndexOrThrow15));
                sensAIActivitySummary2.setPlayed(query.getInt(columnIndexOrThrow16));
                sensAIActivitySummary2.setHitPct(query.getInt(columnIndexOrThrow17));
                sensAIActivitySummary2.setMaxHandSpeed(query.getInt(columnIndexOrThrow18));
                sensAIActivitySummary2.setAvgHandSpeed(query.getInt(columnIndexOrThrow19));
                sensAIActivitySummary2.setBowlingType(query.getInt(columnIndexOrThrow20));
                sensAIActivitySummary2.setTotalBallsBowled(query.getInt(columnIndexOrThrow21));
                sensAIActivitySummary2.setMaxArmSpeed(query.getInt(columnIndexOrThrow22));
                sensAIActivitySummary2.setMinArmSpeed(query.getInt(columnIndexOrThrow23));
                sensAIActivitySummary2.setAvgArmSpeed(query.getInt(columnIndexOrThrow24));
                sensAIActivitySummary2.setSavedServer(query.getInt(columnIndexOrThrow25) != 0);
                sensAIActivitySummary2.setAddToCompare(query.getInt(columnIndexOrThrow26) != 0);
                sensAIActivitySummary2.setDataAggregateSaved(query.getInt(columnIndexOrThrow27) != 0);
                sensAIActivitySummary = sensAIActivitySummary2;
            }
            query.close();
            roomSQLiteQuery.release();
            return sensAIActivitySummary;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public SensAIActivitySummaryDetails getActivitySummaryDetails(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary_details where session_id = ? AND serial_no = ?", 2);
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
        Cursor query = this.f6968a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("activity_type");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("details_data_num");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("unix_time_stamp");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hand_speed");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hr");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("hit_miss");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("is_feedback_saved");
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails = null;
            if (query.moveToFirst()) {
                SensAIActivitySummaryDetails sensAIActivitySummaryDetails2 = new SensAIActivitySummaryDetails();
                sensAIActivitySummaryDetails2.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummaryDetails2.setMacAddress(query.getString(columnIndexOrThrow2));
                sensAIActivitySummaryDetails2.setActivityType(query.getInt(columnIndexOrThrow3));
                sensAIActivitySummaryDetails2.setDetailsDataNum(query.getInt(columnIndexOrThrow4));
                sensAIActivitySummaryDetails2.setUnixTimeStamp(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                sensAIActivitySummaryDetails2.setHandSpeed(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                sensAIActivitySummaryDetails2.setHr(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                sensAIActivitySummaryDetails2.setSteps(Convertors.frommString(query.getString(columnIndexOrThrow8)));
                sensAIActivitySummaryDetails2.setDistance(Convertors.frommString(query.getString(columnIndexOrThrow9)));
                sensAIActivitySummaryDetails2.setCalories(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                sensAIActivitySummaryDetails2.setHitOrMiss(Convertors.frommString(query.getString(columnIndexOrThrow11)));
                sensAIActivitySummaryDetails2.setFeedbackSaved(query.getInt(columnIndexOrThrow12) != 0);
                sensAIActivitySummaryDetails = sensAIActivitySummaryDetails2;
            }
            return sensAIActivitySummaryDetails;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public LiveData<List<SensAIActivitySummary>> getActivitySummaryLiveData(String str, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? ORDER BY CASE ? WHEN 1 THEN timestamp WHEN 2 THEN duration_sec WHEN 3 THEN goal_completion_pct END DESC ", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        return new h(this.f6968a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public List<SensAIActivitySummary> getAddToCompareBattingList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        boolean z;
        boolean z2;
        boolean z3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND is_add_to_compare = 1 AND activity_type = 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            int i2 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                ArrayList arrayList2 = arrayList;
                sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                int i3 = columnIndexOrThrow;
                sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                int i4 = i2;
                sensAIActivitySummary.setAvgHR(query.getInt(i4));
                int i5 = columnIndexOrThrow15;
                i2 = i4;
                sensAIActivitySummary.setTotalSwings(query.getInt(i5));
                columnIndexOrThrow15 = i5;
                int i6 = columnIndexOrThrow16;
                sensAIActivitySummary.setPlayed(query.getInt(i6));
                columnIndexOrThrow16 = i6;
                int i7 = columnIndexOrThrow17;
                sensAIActivitySummary.setHitPct(query.getInt(i7));
                columnIndexOrThrow17 = i7;
                int i8 = columnIndexOrThrow18;
                sensAIActivitySummary.setMaxHandSpeed(query.getInt(i8));
                columnIndexOrThrow18 = i8;
                int i9 = columnIndexOrThrow19;
                sensAIActivitySummary.setAvgHandSpeed(query.getInt(i9));
                columnIndexOrThrow19 = i9;
                int i10 = columnIndexOrThrow20;
                sensAIActivitySummary.setBowlingType(query.getInt(i10));
                columnIndexOrThrow20 = i10;
                int i11 = columnIndexOrThrow21;
                sensAIActivitySummary.setTotalBallsBowled(query.getInt(i11));
                columnIndexOrThrow21 = i11;
                int i12 = columnIndexOrThrow22;
                sensAIActivitySummary.setMaxArmSpeed(query.getInt(i12));
                columnIndexOrThrow22 = i12;
                int i13 = columnIndexOrThrow23;
                sensAIActivitySummary.setMinArmSpeed(query.getInt(i13));
                columnIndexOrThrow23 = i13;
                int i14 = columnIndexOrThrow24;
                sensAIActivitySummary.setAvgArmSpeed(query.getInt(i14));
                int i15 = columnIndexOrThrow25;
                if (query.getInt(i15) != 0) {
                    columnIndexOrThrow24 = i14;
                    z = true;
                } else {
                    columnIndexOrThrow24 = i14;
                    z = false;
                }
                sensAIActivitySummary.setSavedServer(z);
                int i16 = columnIndexOrThrow26;
                if (query.getInt(i16) != 0) {
                    columnIndexOrThrow26 = i16;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i16;
                    z2 = false;
                }
                sensAIActivitySummary.setAddToCompare(z2);
                int i17 = columnIndexOrThrow27;
                if (query.getInt(i17) != 0) {
                    columnIndexOrThrow27 = i17;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i17;
                    z3 = false;
                }
                sensAIActivitySummary.setDataAggregateSaved(z3);
                arrayList2.add(sensAIActivitySummary);
                columnIndexOrThrow25 = i15;
                arrayList = arrayList2;
                columnIndexOrThrow = i3;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public List<SensAIActivitySummary> getAddToCompareBowlingList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        boolean z;
        boolean z2;
        boolean z3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND is_add_to_compare = 1 AND activity_type = 2", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            int i2 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                ArrayList arrayList2 = arrayList;
                sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                int i3 = columnIndexOrThrow;
                sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                int i4 = i2;
                sensAIActivitySummary.setAvgHR(query.getInt(i4));
                int i5 = columnIndexOrThrow15;
                i2 = i4;
                sensAIActivitySummary.setTotalSwings(query.getInt(i5));
                columnIndexOrThrow15 = i5;
                int i6 = columnIndexOrThrow16;
                sensAIActivitySummary.setPlayed(query.getInt(i6));
                columnIndexOrThrow16 = i6;
                int i7 = columnIndexOrThrow17;
                sensAIActivitySummary.setHitPct(query.getInt(i7));
                columnIndexOrThrow17 = i7;
                int i8 = columnIndexOrThrow18;
                sensAIActivitySummary.setMaxHandSpeed(query.getInt(i8));
                columnIndexOrThrow18 = i8;
                int i9 = columnIndexOrThrow19;
                sensAIActivitySummary.setAvgHandSpeed(query.getInt(i9));
                columnIndexOrThrow19 = i9;
                int i10 = columnIndexOrThrow20;
                sensAIActivitySummary.setBowlingType(query.getInt(i10));
                columnIndexOrThrow20 = i10;
                int i11 = columnIndexOrThrow21;
                sensAIActivitySummary.setTotalBallsBowled(query.getInt(i11));
                columnIndexOrThrow21 = i11;
                int i12 = columnIndexOrThrow22;
                sensAIActivitySummary.setMaxArmSpeed(query.getInt(i12));
                columnIndexOrThrow22 = i12;
                int i13 = columnIndexOrThrow23;
                sensAIActivitySummary.setMinArmSpeed(query.getInt(i13));
                columnIndexOrThrow23 = i13;
                int i14 = columnIndexOrThrow24;
                sensAIActivitySummary.setAvgArmSpeed(query.getInt(i14));
                int i15 = columnIndexOrThrow25;
                if (query.getInt(i15) != 0) {
                    columnIndexOrThrow24 = i14;
                    z = true;
                } else {
                    columnIndexOrThrow24 = i14;
                    z = false;
                }
                sensAIActivitySummary.setSavedServer(z);
                int i16 = columnIndexOrThrow26;
                if (query.getInt(i16) != 0) {
                    columnIndexOrThrow26 = i16;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i16;
                    z2 = false;
                }
                sensAIActivitySummary.setAddToCompare(z2);
                int i17 = columnIndexOrThrow27;
                if (query.getInt(i17) != 0) {
                    columnIndexOrThrow27 = i17;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i17;
                    z3 = false;
                }
                sensAIActivitySummary.setDataAggregateSaved(z3);
                arrayList2.add(sensAIActivitySummary);
                columnIndexOrThrow25 = i15;
                arrayList = arrayList2;
                columnIndexOrThrow = i3;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public List<SensAIActivitySummary> getAggregateSummaryList(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        boolean z;
        boolean z2;
        boolean z3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND is_data_aggregate_saved = 0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6968a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("client_ref_id");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("timestamp");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("activity_type");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("duration_sec");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_steps");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("total_calories");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("hand");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("goal_type");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("target_goal_value");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("goal_completion_pct");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("max_hr");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("avg_hr");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("total_swings");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("played");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("hit_pct");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("max_hand_speed");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("avg_hand_speed");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("bowling_type");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("total_balls_bowled");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("max_arm_speed");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("min_arm_speed");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("avg_arm_speed");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("is_saved_server");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("is_add_to_compare");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("is_data_aggregate_saved");
            int i2 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                ArrayList arrayList2 = arrayList;
                sensAIActivitySummary.setSessionId(query.getString(columnIndexOrThrow));
                sensAIActivitySummary.setClientRefID(query.getString(columnIndexOrThrow2));
                sensAIActivitySummary.setMacAddress(query.getString(columnIndexOrThrow3));
                sensAIActivitySummary.setTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                sensAIActivitySummary.setActivityType(query.getInt(columnIndexOrThrow5));
                int i3 = columnIndexOrThrow;
                sensAIActivitySummary.setDurationSec(query.getLong(columnIndexOrThrow6));
                sensAIActivitySummary.setTotalSteps(query.getInt(columnIndexOrThrow7));
                sensAIActivitySummary.setTotalCalories(query.getDouble(columnIndexOrThrow8));
                sensAIActivitySummary.setHand(query.getInt(columnIndexOrThrow9));
                sensAIActivitySummary.setGoalType(query.getInt(columnIndexOrThrow10));
                sensAIActivitySummary.setTargetGoalValue(query.getInt(columnIndexOrThrow11));
                sensAIActivitySummary.setGoalCompletionPct(query.getInt(columnIndexOrThrow12));
                sensAIActivitySummary.setMaxHR(query.getInt(columnIndexOrThrow13));
                int i4 = i2;
                sensAIActivitySummary.setAvgHR(query.getInt(i4));
                int i5 = columnIndexOrThrow15;
                i2 = i4;
                sensAIActivitySummary.setTotalSwings(query.getInt(i5));
                columnIndexOrThrow15 = i5;
                int i6 = columnIndexOrThrow16;
                sensAIActivitySummary.setPlayed(query.getInt(i6));
                columnIndexOrThrow16 = i6;
                int i7 = columnIndexOrThrow17;
                sensAIActivitySummary.setHitPct(query.getInt(i7));
                columnIndexOrThrow17 = i7;
                int i8 = columnIndexOrThrow18;
                sensAIActivitySummary.setMaxHandSpeed(query.getInt(i8));
                columnIndexOrThrow18 = i8;
                int i9 = columnIndexOrThrow19;
                sensAIActivitySummary.setAvgHandSpeed(query.getInt(i9));
                columnIndexOrThrow19 = i9;
                int i10 = columnIndexOrThrow20;
                sensAIActivitySummary.setBowlingType(query.getInt(i10));
                columnIndexOrThrow20 = i10;
                int i11 = columnIndexOrThrow21;
                sensAIActivitySummary.setTotalBallsBowled(query.getInt(i11));
                columnIndexOrThrow21 = i11;
                int i12 = columnIndexOrThrow22;
                sensAIActivitySummary.setMaxArmSpeed(query.getInt(i12));
                columnIndexOrThrow22 = i12;
                int i13 = columnIndexOrThrow23;
                sensAIActivitySummary.setMinArmSpeed(query.getInt(i13));
                columnIndexOrThrow23 = i13;
                int i14 = columnIndexOrThrow24;
                sensAIActivitySummary.setAvgArmSpeed(query.getInt(i14));
                int i15 = columnIndexOrThrow25;
                if (query.getInt(i15) != 0) {
                    columnIndexOrThrow24 = i14;
                    z = true;
                } else {
                    columnIndexOrThrow24 = i14;
                    z = false;
                }
                sensAIActivitySummary.setSavedServer(z);
                int i16 = columnIndexOrThrow26;
                if (query.getInt(i16) != 0) {
                    columnIndexOrThrow26 = i16;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i16;
                    z2 = false;
                }
                sensAIActivitySummary.setAddToCompare(z2);
                int i17 = columnIndexOrThrow27;
                if (query.getInt(i17) != 0) {
                    columnIndexOrThrow27 = i17;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i17;
                    z3 = false;
                }
                sensAIActivitySummary.setDataAggregateSaved(z3);
                arrayList2.add(sensAIActivitySummary);
                columnIndexOrThrow25 = i15;
                arrayList = arrayList2;
                columnIndexOrThrow = i3;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public LiveData<List<SensAIActivitySummary>> getFilteredByActivityAndHandAndBowlingType(String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sensai_activity_summary where serial_no = ? AND ((activity_type = ? AND (hand = ? OR hand =?)) OR (activity_type =?) AND (hand = ? OR hand =?) AND (bowling_type = ? OR bowling_type = ? OR bowling_type = ?)) ORDER BY CASE ? WHEN 1 THEN timestamp WHEN 2 THEN duration_sec WHEN 3 THEN goal_completion_pct END DESC ", 11);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        long j = i4;
        acquire.bindLong(3, j);
        long j2 = i5;
        acquire.bindLong(4, j2);
        acquire.bindLong(5, i3);
        acquire.bindLong(6, j);
        acquire.bindLong(7, j2);
        acquire.bindLong(8, i6);
        acquire.bindLong(9, i7);
        acquire.bindLong(10, i8);
        acquire.bindLong(11, i9);
        return new i(this.f6968a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void insertActivitySummary(List<SensAIActivitySummary> list) {
        this.f6968a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void insertActivitySummaryDetails(SensAIActivitySummaryDetails sensAIActivitySummaryDetails) {
        this.f6968a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) sensAIActivitySummaryDetails);
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public int isSessionIdExists(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM sensai_activity_summary WHERE session_id=? AND serial_no = ?", 2);
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
        Cursor query = this.f6968a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void updateActivitySummary(SensAIActivitySummary sensAIActivitySummary) {
        this.f6968a.beginTransaction();
        try {
            this.d.handle(sensAIActivitySummary);
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void updateAddToCompareItem(boolean z, String str, String str2) {
        SupportSQLiteStatement acquire = this.g.acquire();
        this.f6968a.beginTransaction();
        try {
            acquire.bindLong(1, z ? 1L : 0L);
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
            acquire.executeUpdateDelete();
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
            this.g.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void updateAggregateItem(boolean z, String str, String str2) {
        SupportSQLiteStatement acquire = this.f.acquire();
        this.f6968a.beginTransaction();
        try {
            acquire.bindLong(1, z ? 1L : 0L);
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
            acquire.executeUpdateDelete();
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void updateFeedbackSavedToServerItem(boolean z, String str, String str2) {
        SupportSQLiteStatement acquire = this.h.acquire();
        this.f6968a.beginTransaction();
        try {
            acquire.bindLong(1, z ? 1L : 0L);
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
            acquire.executeUpdateDelete();
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
            this.h.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.sensai.SensAIDataDao
    public void updateSavedToServerItem(boolean z, String str, String str2, String str3) {
        SupportSQLiteStatement acquire = this.e.acquire();
        this.f6968a.beginTransaction();
        try {
            acquire.bindLong(1, z ? 1L : 0L);
            if (str3 == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindString(2, str3);
            }
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
            acquire.executeUpdateDelete();
            this.f6968a.setTransactionSuccessful();
        } finally {
            this.f6968a.endTransaction();
            this.e.release(acquire);
        }
    }
}
