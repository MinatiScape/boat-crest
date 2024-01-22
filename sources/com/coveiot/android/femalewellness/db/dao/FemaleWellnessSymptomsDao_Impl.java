package com.coveiot.android.femalewellness.db.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.utils.Converter;
import com.coveiot.android.femalewellness.wellnesscalendar.model.PhaseAndDateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes4.dex */
public final class FemaleWellnessSymptomsDao_Impl implements FemaleWellnessSymptomsDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f4393a;
    public final EntityInsertionAdapter<EntityFemaleWellnessSymptoms> b;
    public final Converter c = new Converter();
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    /* loaded from: classes4.dex */
    public class a extends EntityInsertionAdapter<EntityFemaleWellnessSymptoms> {
        public a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms) {
            String str = entityFemaleWellnessSymptoms.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityFemaleWellnessSymptoms.flow;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityFemaleWellnessSymptoms.pain;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            String str4 = entityFemaleWellnessSymptoms.mood;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str4);
            }
            String converter = FemaleWellnessSymptomsDao_Impl.this.c.toString(entityFemaleWellnessSymptoms.symptoms);
            if (converter == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, converter);
            }
            supportSQLiteStatement.bindLong(6, entityFemaleWellnessSymptoms.isActive ? 1L : 0L);
            String str5 = entityFemaleWellnessSymptoms.cycleStartDate;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str5);
            }
            Integer num = entityFemaleWellnessSymptoms.cycleLength;
            if (num == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindLong(8, num.intValue());
            }
            Integer num2 = entityFemaleWellnessSymptoms.periodLength;
            if (num2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindLong(9, num2.intValue());
            }
            Integer num3 = entityFemaleWellnessSymptoms.pmsLength;
            if (num3 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindLong(10, num3.intValue());
            }
            String str6 = entityFemaleWellnessSymptoms.phase;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str6);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `female_wellness_record_symptoms` (`date`,`flow`,`pain`,`mood`,`symptoms`,`isActive`,`cycleStartDate`,`cycleLength`,`periodLength`,`pmsLength`,`phase`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes4.dex */
    public class b extends SharedSQLiteStatement {
        public b(FemaleWellnessSymptomsDao_Impl femaleWellnessSymptomsDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE  INTO female_wellness_record_symptoms (date ,cycleStartDate,cycleLength,periodLength,phase, isActive)  VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes4.dex */
    public class c extends SharedSQLiteStatement {
        public c(FemaleWellnessSymptomsDao_Impl femaleWellnessSymptomsDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM female_wellness_record_symptoms WHERE DATE(date)>=DATE(?)";
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Callable<List<EntityFemaleWellnessSymptoms>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public d(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityFemaleWellnessSymptoms> call() throws Exception {
            Object obj;
            Cursor query = DBUtil.query(FemaleWellnessSymptomsDao_Impl.this.f4393a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "flow");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "pain");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mood");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "symptoms");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isActive");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cycleStartDate");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cycleLength");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "periodLength");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "pmsLength");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.CycleType.S_WAVE_PHASE);
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms = new EntityFemaleWellnessSymptoms();
                    entityFemaleWellnessSymptoms.date = query.getString(columnIndexOrThrow);
                    entityFemaleWellnessSymptoms.flow = query.getString(columnIndexOrThrow2);
                    entityFemaleWellnessSymptoms.pain = query.getString(columnIndexOrThrow3);
                    entityFemaleWellnessSymptoms.mood = query.getString(columnIndexOrThrow4);
                    int i = columnIndexOrThrow;
                    entityFemaleWellnessSymptoms.symptoms = FemaleWellnessSymptomsDao_Impl.this.c.fromString(query.getString(columnIndexOrThrow5));
                    entityFemaleWellnessSymptoms.isActive = query.getInt(columnIndexOrThrow6) != 0;
                    entityFemaleWellnessSymptoms.cycleStartDate = query.getString(columnIndexOrThrow7);
                    if (query.isNull(columnIndexOrThrow8)) {
                        entityFemaleWellnessSymptoms.cycleLength = null;
                    } else {
                        entityFemaleWellnessSymptoms.cycleLength = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        entityFemaleWellnessSymptoms.periodLength = null;
                    } else {
                        entityFemaleWellnessSymptoms.periodLength = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        obj = null;
                        entityFemaleWellnessSymptoms.pmsLength = null;
                    } else {
                        obj = null;
                        entityFemaleWellnessSymptoms.pmsLength = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                    }
                    entityFemaleWellnessSymptoms.phase = query.getString(columnIndexOrThrow11);
                    arrayList.add(entityFemaleWellnessSymptoms);
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

    public FemaleWellnessSymptomsDao_Impl(RoomDatabase roomDatabase) {
        this.f4393a = roomDatabase;
        this.b = new a(roomDatabase);
        this.d = new b(this, roomDatabase);
        this.e = new c(this, roomDatabase);
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public void deleteFutureRecordsIfAny(String str) {
        this.f4393a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4393a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f4393a.setTransactionSuccessful();
        } finally {
            this.f4393a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public PhaseAndDateModel fetchNextPeriodOvulationDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date, phase FROM female_wellness_record_symptoms WHERE phase IS NOT NULL   and DATE(date)>DATE(?) order by DATE(date) ASC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4393a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4393a, acquire, false, null);
        try {
            return query.moveToFirst() ? new PhaseAndDateModel(query.getString(CursorUtil.getColumnIndexOrThrow(query, "date")), query.getString(CursorUtil.getColumnIndexOrThrow(query, TypedValues.CycleType.S_WAVE_PHASE))) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public PhaseAndDateModel fetchPreviousPeriodOvulationDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date,phase FROM female_wellness_record_symptoms WHERE phase IS NOT NULL   and DATE(date)<DATE(?) order by DATE(date) DESC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f4393a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4393a, acquire, false, null);
        try {
            return query.moveToFirst() ? new PhaseAndDateModel(query.getString(CursorUtil.getColumnIndexOrThrow(query, "date")), query.getString(CursorUtil.getColumnIndexOrThrow(query, TypedValues.CycleType.S_WAVE_PHASE))) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public List<String> fetchRecordedOvulationDates(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date FROM female_wellness_record_symptoms WHERE date =? and  phase='OVULATION'  and DATE(date)<DATE(?)", 2);
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
        this.f4393a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4393a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public List<String> fetchRecordedPeriodDates(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date FROM female_wellness_record_symptoms WHERE date =? and  phase='PERIOD'   and DATE(date)<DATE(?)", 2);
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
        this.f4393a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4393a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public String getOldestLMD() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT cycleStartDate from female_wellness_record_symptoms order by cycleStartDate asc limit 1", 0);
        this.f4393a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f4393a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public LiveData<List<EntityFemaleWellnessSymptoms>> getTheRecordedSymptomsByDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from female_wellness_record_symptoms WHERE date = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f4393a.getInvalidationTracker().createLiveData(new String[]{"female_wellness_record_symptoms"}, false, new d(acquire));
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public void insert(EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms) {
        this.f4393a.assertNotSuspendingTransaction();
        this.f4393a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<EntityFemaleWellnessSymptoms>) entityFemaleWellnessSymptoms);
            this.f4393a.setTransactionSuccessful();
        } finally {
            this.f4393a.endTransaction();
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public void insertOrUpdateWellnessData(String str, String str2, int i, int i2, String str3, Boolean bool) {
        this.f4393a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
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
        acquire.bindLong(3, i);
        acquire.bindLong(4, i2);
        if (str3 == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, str3);
        }
        Integer valueOf = bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0);
        if (valueOf == null) {
            acquire.bindNull(6);
        } else {
            acquire.bindLong(6, valueOf.intValue());
        }
        this.f4393a.beginTransaction();
        try {
            acquire.executeInsert();
            this.f4393a.setTransactionSuccessful();
        } finally {
            this.f4393a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao
    public void updateFemaleWellnessSymptoms(String str, String str2, String str3, String str4, List<String> list) {
        this.f4393a.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE female_wellness_record_symptoms SET flow = ");
        newStringBuilder.append("?");
        newStringBuilder.append(", pain =");
        newStringBuilder.append("?");
        newStringBuilder.append(", mood =");
        newStringBuilder.append("?");
        newStringBuilder.append(", symptoms =");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(" WHERE date  = ");
        newStringBuilder.append("?");
        SupportSQLiteStatement compileStatement = this.f4393a.compileStatement(newStringBuilder.toString());
        if (str2 == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, str2);
        }
        if (str3 == null) {
            compileStatement.bindNull(2);
        } else {
            compileStatement.bindString(2, str3);
        }
        if (str4 == null) {
            compileStatement.bindNull(3);
        } else {
            compileStatement.bindString(3, str4);
        }
        int i = 4;
        for (String str5 : list) {
            if (str5 == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindString(i, str5);
            }
            i++;
        }
        int i2 = size + 4;
        if (str == null) {
            compileStatement.bindNull(i2);
        } else {
            compileStatement.bindString(i2, str);
        }
        this.f4393a.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.f4393a.setTransactionSuccessful();
        } finally {
            this.f4393a.endTransaction();
        }
    }
}
