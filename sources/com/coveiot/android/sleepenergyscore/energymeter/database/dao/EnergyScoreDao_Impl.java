package com.coveiot.android.sleepenergyscore.energymeter.database.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyDataConverter;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class EnergyScoreDao_Impl implements EnergyScoreDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5705a;
    public final EntityInsertionAdapter<EnergyScoreDbData> b;
    public final SharedSQLiteStatement c;
    public final SharedSQLiteStatement d;

    /* loaded from: classes6.dex */
    public class a extends EntityInsertionAdapter<EnergyScoreDbData> {
        public a(EnergyScoreDao_Impl energyScoreDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EnergyScoreDbData energyScoreDbData) {
            String str = energyScoreDbData.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = energyScoreDbData.macAddress;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String fromEnergyDataList = EnergyDataConverter.fromEnergyDataList(energyScoreDbData.data);
            if (fromEnergyDataList == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, fromEnergyDataList);
            }
            String fromFeedbackDataList = EnergyDataConverter.fromFeedbackDataList(energyScoreDbData.feedbackList);
            if (fromFeedbackDataList == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, fromFeedbackDataList);
            }
            String str3 = energyScoreDbData.message;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str3);
            }
            String str4 = energyScoreDbData.questionnaireId;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str4);
            }
            Long l = energyScoreDbData.lastSyncedDate;
            if (l == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindLong(7, l.longValue());
            }
            String str5 = energyScoreDbData.status;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, str5);
            }
            Integer num = energyScoreDbData.isSyncedWithServer;
            if (num == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindLong(9, num.intValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `energy_score_table` (`mDate`,`macAddress`,`data`,`feedbackList`,`message`,`questionnaireId`,`lastSyncedDate`,`status`,`isSyncedWithServer`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes6.dex */
    public class b extends SharedSQLiteStatement {
        public b(EnergyScoreDao_Impl energyScoreDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE energy_score_table SET isSyncedWithServer = 1 WHERE macAddress=? AND mDate=?";
        }
    }

    /* loaded from: classes6.dex */
    public class c extends SharedSQLiteStatement {
        public c(EnergyScoreDao_Impl energyScoreDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE energy_score_table SET feedbackList =? , questionnaireId=? WHERE macAddress=? AND mDate=?";
        }
    }

    /* loaded from: classes6.dex */
    public class d implements Callable<EnergyScoreDbData> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public d(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public EnergyScoreDbData call() throws Exception {
            EnergyScoreDbData energyScoreDbData = null;
            Cursor query = DBUtil.query(EnergyScoreDao_Impl.this.f5705a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mDate");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "data");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_MESSAGE);
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedWithServer");
                if (query.moveToFirst()) {
                    EnergyScoreDbData energyScoreDbData2 = new EnergyScoreDbData();
                    energyScoreDbData2.mDate = query.getString(columnIndexOrThrow);
                    energyScoreDbData2.macAddress = query.getString(columnIndexOrThrow2);
                    energyScoreDbData2.data = EnergyDataConverter.toEnergyData(query.getString(columnIndexOrThrow3));
                    energyScoreDbData2.feedbackList = EnergyDataConverter.toFeedbackData(query.getString(columnIndexOrThrow4));
                    energyScoreDbData2.message = query.getString(columnIndexOrThrow5);
                    energyScoreDbData2.questionnaireId = query.getString(columnIndexOrThrow6);
                    if (query.isNull(columnIndexOrThrow7)) {
                        energyScoreDbData2.lastSyncedDate = null;
                    } else {
                        energyScoreDbData2.lastSyncedDate = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    energyScoreDbData2.status = query.getString(columnIndexOrThrow8);
                    if (query.isNull(columnIndexOrThrow9)) {
                        energyScoreDbData2.isSyncedWithServer = null;
                    } else {
                        energyScoreDbData2.isSyncedWithServer = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    energyScoreDbData = energyScoreDbData2;
                }
                return energyScoreDbData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public EnergyScoreDao_Impl(RoomDatabase roomDatabase) {
        this.f5705a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public EnergyScoreDbData getEnergyScoreData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from energy_score_table where macAddress=? AND mDate=? ", 2);
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
        this.f5705a.assertNotSuspendingTransaction();
        EnergyScoreDbData energyScoreDbData = null;
        Cursor query = DBUtil.query(this.f5705a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mDate");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_MESSAGE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedWithServer");
            if (query.moveToFirst()) {
                EnergyScoreDbData energyScoreDbData2 = new EnergyScoreDbData();
                energyScoreDbData2.mDate = query.getString(columnIndexOrThrow);
                energyScoreDbData2.macAddress = query.getString(columnIndexOrThrow2);
                energyScoreDbData2.data = EnergyDataConverter.toEnergyData(query.getString(columnIndexOrThrow3));
                energyScoreDbData2.feedbackList = EnergyDataConverter.toFeedbackData(query.getString(columnIndexOrThrow4));
                energyScoreDbData2.message = query.getString(columnIndexOrThrow5);
                energyScoreDbData2.questionnaireId = query.getString(columnIndexOrThrow6);
                if (query.isNull(columnIndexOrThrow7)) {
                    energyScoreDbData2.lastSyncedDate = null;
                } else {
                    energyScoreDbData2.lastSyncedDate = Long.valueOf(query.getLong(columnIndexOrThrow7));
                }
                energyScoreDbData2.status = query.getString(columnIndexOrThrow8);
                if (query.isNull(columnIndexOrThrow9)) {
                    energyScoreDbData2.isSyncedWithServer = null;
                } else {
                    energyScoreDbData2.isSyncedWithServer = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                }
                energyScoreDbData = energyScoreDbData2;
            }
            return energyScoreDbData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public long getLastSyncedDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT lastSyncedDate from energy_score_table WHERE macAddress=? ORDER BY lastSyncedDate DESC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5705a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5705a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public LiveData<EnergyScoreDbData> getLastSyncedScoreLiveData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from energy_score_table WHERE macAddress=? ORDER BY mDate DESC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f5705a.getInvalidationTracker().createLiveData(new String[]{"energy_score_table"}, false, new d(acquire));
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public List<EnergyScoreDbData> getListOfEnergyScoreData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from energy_score_table where macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5705a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5705a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mDate");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_MESSAGE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedWithServer");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EnergyScoreDbData energyScoreDbData = new EnergyScoreDbData();
                energyScoreDbData.mDate = query.getString(columnIndexOrThrow);
                energyScoreDbData.macAddress = query.getString(columnIndexOrThrow2);
                energyScoreDbData.data = EnergyDataConverter.toEnergyData(query.getString(columnIndexOrThrow3));
                energyScoreDbData.feedbackList = EnergyDataConverter.toFeedbackData(query.getString(columnIndexOrThrow4));
                energyScoreDbData.message = query.getString(columnIndexOrThrow5);
                energyScoreDbData.questionnaireId = query.getString(columnIndexOrThrow6);
                if (query.isNull(columnIndexOrThrow7)) {
                    energyScoreDbData.lastSyncedDate = null;
                } else {
                    energyScoreDbData.lastSyncedDate = Long.valueOf(query.getLong(columnIndexOrThrow7));
                }
                energyScoreDbData.status = query.getString(columnIndexOrThrow8);
                if (query.isNull(columnIndexOrThrow9)) {
                    energyScoreDbData.isSyncedWithServer = null;
                } else {
                    energyScoreDbData.isSyncedWithServer = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                }
                arrayList.add(energyScoreDbData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public List<EnergyScoreDbData> getListOfUnSyncedEnergyScoreData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from energy_score_table where macAddress=? AND isSyncedWithServer=0 ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5705a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5705a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mDate");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "feedbackList");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_MESSAGE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "questionnaireId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "lastSyncedDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isSyncedWithServer");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EnergyScoreDbData energyScoreDbData = new EnergyScoreDbData();
                energyScoreDbData.mDate = query.getString(columnIndexOrThrow);
                energyScoreDbData.macAddress = query.getString(columnIndexOrThrow2);
                energyScoreDbData.data = EnergyDataConverter.toEnergyData(query.getString(columnIndexOrThrow3));
                energyScoreDbData.feedbackList = EnergyDataConverter.toFeedbackData(query.getString(columnIndexOrThrow4));
                energyScoreDbData.message = query.getString(columnIndexOrThrow5);
                energyScoreDbData.questionnaireId = query.getString(columnIndexOrThrow6);
                if (query.isNull(columnIndexOrThrow7)) {
                    energyScoreDbData.lastSyncedDate = null;
                } else {
                    energyScoreDbData.lastSyncedDate = Long.valueOf(query.getLong(columnIndexOrThrow7));
                }
                energyScoreDbData.status = query.getString(columnIndexOrThrow8);
                if (query.isNull(columnIndexOrThrow9)) {
                    energyScoreDbData.isSyncedWithServer = null;
                } else {
                    energyScoreDbData.isSyncedWithServer = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                }
                arrayList.add(energyScoreDbData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public long insert(EnergyScoreDbData energyScoreDbData) {
        this.f5705a.assertNotSuspendingTransaction();
        this.f5705a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(energyScoreDbData);
            this.f5705a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f5705a.endTransaction();
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public void updateFeedbackList(ArrayList<QuestionAnswerData> arrayList, String str, String str2, String str3) {
        this.f5705a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        String fromFeedbackDataList = EnergyDataConverter.fromFeedbackDataList(arrayList);
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
        this.f5705a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5705a.setTransactionSuccessful();
        } finally {
            this.f5705a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao
    public void updateSyncedDataToServer(String str, String str2) {
        this.f5705a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
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
        this.f5705a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5705a.setTransactionSuccessful();
        } finally {
            this.f5705a.endTransaction();
            this.c.release(acquire);
        }
    }
}
