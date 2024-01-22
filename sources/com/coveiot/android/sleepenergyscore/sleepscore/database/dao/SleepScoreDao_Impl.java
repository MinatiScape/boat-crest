package com.coveiot.android.sleepenergyscore.sleepscore.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreFeedbackConverter;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class SleepScoreDao_Impl implements SleepScoreDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5743a;
    public final EntityInsertionAdapter<SleepScoreData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes6.dex */
    public class a extends EntityInsertionAdapter<SleepScoreData> {
        public a(SleepScoreDao_Impl sleepScoreDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, SleepScoreData sleepScoreData) {
            String str = sleepScoreData.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = sleepScoreData.macAddress;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            if (sleepScoreData.getSleepScore() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindLong(3, sleepScoreData.getSleepScore().intValue());
            }
            if (sleepScoreData.getTotalSleep() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, sleepScoreData.getTotalSleep());
            }
            if (sleepScoreData.getAlgoIdentifier() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, sleepScoreData.getAlgoIdentifier());
            }
            if (sleepScoreData.getComputedDate() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, sleepScoreData.getComputedDate());
            }
            if (sleepScoreData.getTimeToDeepSleep() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindLong(7, sleepScoreData.getTimeToDeepSleep().intValue());
            }
            if (sleepScoreData.getWascoCount() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindLong(8, sleepScoreData.getWascoCount().intValue());
            }
            if (sleepScoreData.getSleepConsistency() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, sleepScoreData.getSleepConsistency());
            }
            if (sleepScoreData.getLastSyncedDate() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindLong(10, sleepScoreData.getLastSyncedDate().longValue());
            }
            if (sleepScoreData.getQuestionnaireId() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, sleepScoreData.getQuestionnaireId());
            }
            String fromFeedbackDataList = SleepScoreFeedbackConverter.fromFeedbackDataList(sleepScoreData.getFeedbackList());
            if (fromFeedbackDataList == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, fromFeedbackDataList);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `sleep_score_table` (`date`,`macAddress`,`sleepScore`,`totalSleep`,`algoIdentifier`,`computedDate`,`timeToDeepSleep`,`wascoCount`,`sleepConsistency`,`lastSyncedDate`,`questionnaireId`,`feedbackList`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes6.dex */
    public class b extends SharedSQLiteStatement {
        public b(SleepScoreDao_Impl sleepScoreDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sleep_score_table SET feedbackList =? , questionnaireId=? WHERE macAddress=? AND date=?";
        }
    }

    public SleepScoreDao_Impl(RoomDatabase roomDatabase) {
        this.f5743a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public long getLastSyncedDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT lastSyncedDate from sleep_score_table WHERE macAddress=? ORDER BY lastSyncedDate DESC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5743a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5743a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public int getSleepScore(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT sleepScore  from sleep_score_table where macAddress=? AND date=? ", 2);
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
        this.f5743a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5743a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public SleepScoreData getSleepScoreData(String str, String str2) {
        SleepScoreData sleepScoreData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from sleep_score_table where macAddress=? AND date=? ", 2);
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
        this.f5743a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5743a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sleepScore");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "totalSleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "algoIdentifier");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "computedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "timeToDeepSleep");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "wascoCount");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "sleepConsistency");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
            if (query.moveToFirst()) {
                sleepScoreData = new SleepScoreData();
                sleepScoreData.date = query.getString(columnIndexOrThrow);
                sleepScoreData.macAddress = query.getString(columnIndexOrThrow2);
                sleepScoreData.setSleepScore(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                sleepScoreData.setTotalSleep(query.getString(columnIndexOrThrow4));
                sleepScoreData.setAlgoIdentifier(query.getString(columnIndexOrThrow5));
                sleepScoreData.setComputedDate(query.getString(columnIndexOrThrow6));
                sleepScoreData.setTimeToDeepSleep(query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)));
                sleepScoreData.setWascoCount(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                sleepScoreData.setSleepConsistency(query.getString(columnIndexOrThrow9));
                sleepScoreData.setLastSyncedDate(query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)));
                sleepScoreData.setQuestionnaireId(query.getString(columnIndexOrThrow11));
                sleepScoreData.setFeedbackList(SleepScoreFeedbackConverter.toFeedbackData(query.getString(columnIndexOrThrow12)));
            } else {
                sleepScoreData = null;
            }
            return sleepScoreData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public List<SleepScoreData> getSleepScoreDataListBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from sleep_score_table where macAddress=? AND  date BETWEEN ? AND ? ", 3);
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
        this.f5743a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5743a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sleepScore");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "totalSleep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "algoIdentifier");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "computedDate");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "timeToDeepSleep");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "wascoCount");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "sleepConsistency");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SleepScoreData sleepScoreData = new SleepScoreData();
                roomSQLiteQuery = acquire;
                try {
                    sleepScoreData.date = query.getString(columnIndexOrThrow);
                    sleepScoreData.macAddress = query.getString(columnIndexOrThrow2);
                    sleepScoreData.setSleepScore(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    sleepScoreData.setTotalSleep(query.getString(columnIndexOrThrow4));
                    sleepScoreData.setAlgoIdentifier(query.getString(columnIndexOrThrow5));
                    sleepScoreData.setComputedDate(query.getString(columnIndexOrThrow6));
                    sleepScoreData.setTimeToDeepSleep(query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)));
                    sleepScoreData.setWascoCount(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                    sleepScoreData.setSleepConsistency(query.getString(columnIndexOrThrow9));
                    sleepScoreData.setLastSyncedDate(query.isNull(columnIndexOrThrow10) ? null : Long.valueOf(query.getLong(columnIndexOrThrow10)));
                    sleepScoreData.setQuestionnaireId(query.getString(columnIndexOrThrow11));
                    sleepScoreData.setFeedbackList(SleepScoreFeedbackConverter.toFeedbackData(query.getString(columnIndexOrThrow12)));
                    arrayList.add(sleepScoreData);
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

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public long insert(SleepScoreData sleepScoreData) {
        this.f5743a.assertNotSuspendingTransaction();
        this.f5743a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(sleepScoreData);
            this.f5743a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f5743a.endTransaction();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao
    public void updateFeedbackList(ArrayList<QuestionAnswerSleepData> arrayList, String str, String str2, String str3) {
        this.f5743a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        String fromFeedbackDataList = SleepScoreFeedbackConverter.fromFeedbackDataList(arrayList);
        if (fromFeedbackDataList == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, fromFeedbackDataList);
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
        if (str3 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str3);
        }
        this.f5743a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5743a.setTransactionSuccessful();
        } finally {
            this.f5743a.endTransaction();
            this.c.release(acquire);
        }
    }
}
