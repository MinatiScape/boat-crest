package com.coveiot.covedb.ecg;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class ECGDao_Impl implements ECGDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6947a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityECGSummaryData> {
        public a(ECGDao_Impl eCGDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityECGSummaryData entityECGSummaryData) {
            String str = entityECGSummaryData.session_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityECGSummaryData.serial_no;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityECGSummaryData.startDateTime;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            String str4 = entityECGSummaryData.endDateTime;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str4);
            }
            supportSQLiteStatement.bindLong(5, entityECGSummaryData.heart_rate);
            supportSQLiteStatement.bindLong(6, entityECGSummaryData.hrv_value);
            supportSQLiteStatement.bindLong(7, entityECGSummaryData.stress);
            supportSQLiteStatement.bindLong(8, entityECGSummaryData.high_bp);
            supportSQLiteStatement.bindLong(9, entityECGSummaryData.low_bp);
            supportSQLiteStatement.bindLong(10, entityECGSummaryData.rr_interval);
            String str5 = entityECGSummaryData.json_data_URL;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str5);
            }
            String str6 = entityECGSummaryData.mediaID;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str6);
            }
            String str7 = entityECGSummaryData.mediaURL;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, str7);
            }
            supportSQLiteStatement.bindLong(14, entityECGSummaryData.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `ecg_summary_table`(`session_id`,`serial_no`,`startDateTime`,`endDateTime`,`heart_rate`,`hrv_value`,`stress`,`high_bp`,`low_bp`,`rr_interval`,`json_data_URL`,`mediaID`,`mediaURL`,`is_sync_server`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public ECGDao_Impl(RoomDatabase roomDatabase) {
        this.f6947a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.ecg.ECGDao
    public List<EntityECGSummaryData> getECGSummaryDataList() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ecg_summary_table WHERE is_sync_server=0", 0);
        Cursor query = this.f6947a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("startDateTime");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("endDateTime");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_value");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("high_bp");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("low_bp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("rr_interval");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("json_data_URL");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("mediaID");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("mediaURL");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityECGSummaryData entityECGSummaryData = new EntityECGSummaryData();
                    ArrayList arrayList2 = arrayList;
                    entityECGSummaryData.session_id = query.getString(columnIndexOrThrow);
                    entityECGSummaryData.serial_no = query.getString(columnIndexOrThrow2);
                    entityECGSummaryData.startDateTime = query.getString(columnIndexOrThrow3);
                    entityECGSummaryData.endDateTime = query.getString(columnIndexOrThrow4);
                    entityECGSummaryData.heart_rate = query.getInt(columnIndexOrThrow5);
                    entityECGSummaryData.hrv_value = query.getInt(columnIndexOrThrow6);
                    entityECGSummaryData.stress = query.getInt(columnIndexOrThrow7);
                    entityECGSummaryData.high_bp = query.getInt(columnIndexOrThrow8);
                    entityECGSummaryData.low_bp = query.getInt(columnIndexOrThrow9);
                    entityECGSummaryData.rr_interval = query.getInt(columnIndexOrThrow10);
                    entityECGSummaryData.json_data_URL = query.getString(columnIndexOrThrow11);
                    entityECGSummaryData.mediaID = query.getString(columnIndexOrThrow12);
                    entityECGSummaryData.mediaURL = query.getString(columnIndexOrThrow13);
                    int i2 = columnIndexOrThrow14;
                    if (query.getInt(i2) != 0) {
                        i = columnIndexOrThrow;
                        z = true;
                    } else {
                        i = columnIndexOrThrow;
                        z = false;
                    }
                    entityECGSummaryData.is_sync_server = z;
                    arrayList2.add(entityECGSummaryData);
                    arrayList = arrayList2;
                    columnIndexOrThrow = i;
                    columnIndexOrThrow14 = i2;
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

    @Override // com.coveiot.covedb.ecg.ECGDao
    public EntityECGSummaryData getLastECGSummaryData() {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityECGSummaryData entityECGSummaryData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ecg_summary_table ORDER BY startDateTime DESC LIMIT 1", 0);
        Cursor query = this.f6947a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(WorkoutConstants.SESSION_ID);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("startDateTime");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("endDateTime");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_value");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("high_bp");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("low_bp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("rr_interval");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("json_data_URL");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("mediaID");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("mediaURL");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("is_sync_server");
            if (query.moveToFirst()) {
                roomSQLiteQuery = acquire;
                try {
                    entityECGSummaryData = new EntityECGSummaryData();
                    entityECGSummaryData.session_id = query.getString(columnIndexOrThrow);
                    entityECGSummaryData.serial_no = query.getString(columnIndexOrThrow2);
                    entityECGSummaryData.startDateTime = query.getString(columnIndexOrThrow3);
                    entityECGSummaryData.endDateTime = query.getString(columnIndexOrThrow4);
                    entityECGSummaryData.heart_rate = query.getInt(columnIndexOrThrow5);
                    entityECGSummaryData.hrv_value = query.getInt(columnIndexOrThrow6);
                    entityECGSummaryData.stress = query.getInt(columnIndexOrThrow7);
                    entityECGSummaryData.high_bp = query.getInt(columnIndexOrThrow8);
                    entityECGSummaryData.low_bp = query.getInt(columnIndexOrThrow9);
                    entityECGSummaryData.rr_interval = query.getInt(columnIndexOrThrow10);
                    entityECGSummaryData.json_data_URL = query.getString(columnIndexOrThrow11);
                    entityECGSummaryData.mediaID = query.getString(columnIndexOrThrow12);
                    entityECGSummaryData.mediaURL = query.getString(columnIndexOrThrow13);
                    entityECGSummaryData.is_sync_server = query.getInt(columnIndexOrThrow14) != 0;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = acquire;
                entityECGSummaryData = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityECGSummaryData;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.ecg.ECGDao
    public void insertECGResultData(EntityECGSummaryData entityECGSummaryData) {
        this.f6947a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityECGSummaryData);
            this.f6947a.setTransactionSuccessful();
        } finally {
            this.f6947a.endTransaction();
        }
    }
}
