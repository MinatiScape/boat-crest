package com.coveiot.covedb.deviceinfo;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public final class DeviceInfoDao_Impl implements DeviceInfoDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6944a;
    public final EntityInsertionAdapter b;
    public final EntityDeletionOrUpdateAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityDeviceInfo> {
        public a(DeviceInfoDao_Impl deviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDeviceInfo entityDeviceInfo) {
            if (entityDeviceInfo.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityDeviceInfo.getMacAddress());
            }
            supportSQLiteStatement.bindLong(2, entityDeviceInfo.isActive() ? 1L : 0L);
            supportSQLiteStatement.bindLong(3, entityDeviceInfo.getLasySyncTime());
            if (entityDeviceInfo.getLastSyncDateWalk() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityDeviceInfo.getLastSyncDateWalk());
            }
            if (entityDeviceInfo.getLastSyncDateSpo2() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityDeviceInfo.getLastSyncDateSpo2());
            }
            if (entityDeviceInfo.getLastSyncDateRawPPG() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityDeviceInfo.getLastSyncDateRawPPG());
            }
            if (entityDeviceInfo.getLastSyncDateAccelerometer() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, entityDeviceInfo.getLastSyncDateAccelerometer());
            }
            if (entityDeviceInfo.getLastSyncDateSedentary() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, entityDeviceInfo.getLastSyncDateSedentary());
            }
            if (entityDeviceInfo.getLastSyncDateSleep() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, entityDeviceInfo.getLastSyncDateSleep());
            }
            if (entityDeviceInfo.getLastSyncDateHr() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, entityDeviceInfo.getLastSyncDateHr());
            }
            if (entityDeviceInfo.getLastSyncDateBp() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, entityDeviceInfo.getLastSyncDateBp());
            }
            if (entityDeviceInfo.getLastSyncDateTemperature() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, entityDeviceInfo.getLastSyncDateTemperature());
            }
            if (entityDeviceInfo.getLastSyncDateSensAISummary() == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, entityDeviceInfo.getLastSyncDateSensAISummary());
            }
            if (entityDeviceInfo.getLastSyncDateDistanceData() == null) {
                supportSQLiteStatement.bindNull(14);
            } else {
                supportSQLiteStatement.bindString(14, entityDeviceInfo.getLastSyncDateDistanceData());
            }
            if (entityDeviceInfo.getLastSyncDateCalorieData() == null) {
                supportSQLiteStatement.bindNull(15);
            } else {
                supportSQLiteStatement.bindString(15, entityDeviceInfo.getLastSyncDateCalorieData());
            }
            if (entityDeviceInfo.getLastSyncDateRr() == null) {
                supportSQLiteStatement.bindNull(16);
            } else {
                supportSQLiteStatement.bindString(16, entityDeviceInfo.getLastSyncDateRr());
            }
            if (entityDeviceInfo.getLastServerSyncSteps() == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, entityDeviceInfo.getLastServerSyncSteps());
            }
            if (entityDeviceInfo.getLastServerSyncSleep() == null) {
                supportSQLiteStatement.bindNull(18);
            } else {
                supportSQLiteStatement.bindString(18, entityDeviceInfo.getLastServerSyncSleep());
            }
            if (entityDeviceInfo.getLastSyncDatePeriodicSpo2() == null) {
                supportSQLiteStatement.bindNull(19);
            } else {
                supportSQLiteStatement.bindString(19, entityDeviceInfo.getLastSyncDatePeriodicSpo2());
            }
            if (entityDeviceInfo.getLastServerSyncDatePeriodicSpo2() == null) {
                supportSQLiteStatement.bindNull(20);
            } else {
                supportSQLiteStatement.bindString(20, entityDeviceInfo.getLastServerSyncDatePeriodicSpo2());
            }
            if (entityDeviceInfo.getLastSyncDateAmbientSound() == null) {
                supportSQLiteStatement.bindNull(21);
            } else {
                supportSQLiteStatement.bindString(21, entityDeviceInfo.getLastSyncDateAmbientSound());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `device_info`(`macAddress`,`is_active`,`last_sync_timestamp`,`last_sync_date_walk`,`last_sync_date_spo2`,`last_sync_date_raw_ppg`,`last_sync_date_accelerometer`,`last_sync_date_sedentary`,`last_sync_date_sleep`,`last_sync_date_hr`,`last_sync_date_bp`,`last_sync_date_temperature`,`last_sync_date_sens_ai_summary`,`last_sync_date_distance_data`,`last_sync_date_calorie_data`,`last_sync_date_rr`,`last_server_sync_steps`,`last_server_sync_sleep`,`last_sync_date_periodic_spo2`,`last_server_sync_date_periodic_spo2`,`last_sync_date_ambient_sound`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityDeletionOrUpdateAdapter<EntityDeviceInfo> {
        public b(DeviceInfoDao_Impl deviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDeviceInfo entityDeviceInfo) {
            if (entityDeviceInfo.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityDeviceInfo.getMacAddress());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM `device_info` WHERE `macAddress` = ?";
        }
    }

    public DeviceInfoDao_Impl(RoomDatabase roomDatabase) {
        this.f6944a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.deviceinfo.DeviceInfoDao
    public void deleteDeviceInfo(EntityDeviceInfo entityDeviceInfo) {
        this.f6944a.beginTransaction();
        try {
            this.c.handle(entityDeviceInfo);
            this.f6944a.setTransactionSuccessful();
        } finally {
            this.f6944a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.deviceinfo.DeviceInfoDao
    public EntityDeviceInfo getDeviceInfoBy(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityDeviceInfo entityDeviceInfo;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM device_info WHERE macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6944a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(DeviceKey.MacAddress);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("is_active");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("last_sync_timestamp");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("last_sync_date_walk");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("last_sync_date_spo2");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("last_sync_date_raw_ppg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("last_sync_date_accelerometer");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("last_sync_date_sedentary");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("last_sync_date_sleep");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("last_sync_date_hr");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("last_sync_date_bp");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("last_sync_date_temperature");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("last_sync_date_sens_ai_summary");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("last_sync_date_distance_data");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("last_sync_date_calorie_data");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("last_sync_date_rr");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("last_server_sync_steps");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("last_server_sync_sleep");
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("last_sync_date_periodic_spo2");
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("last_server_sync_date_periodic_spo2");
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("last_sync_date_ambient_sound");
                if (query.moveToFirst()) {
                    entityDeviceInfo = new EntityDeviceInfo();
                    entityDeviceInfo.setMacAddress(query.getString(columnIndexOrThrow));
                    entityDeviceInfo.setActive(query.getInt(columnIndexOrThrow2) != 0);
                    entityDeviceInfo.setLasySyncTime(query.getLong(columnIndexOrThrow3));
                    entityDeviceInfo.setLastSyncDateWalk(query.getString(columnIndexOrThrow4));
                    entityDeviceInfo.setLastSyncDateSpo2(query.getString(columnIndexOrThrow5));
                    entityDeviceInfo.setLastSyncDateRawPPG(query.getString(columnIndexOrThrow6));
                    entityDeviceInfo.setLastSyncDateAccelerometer(query.getString(columnIndexOrThrow7));
                    entityDeviceInfo.setLastSyncDateSedentary(query.getString(columnIndexOrThrow8));
                    entityDeviceInfo.setLastSyncDateSleep(query.getString(columnIndexOrThrow9));
                    entityDeviceInfo.setLastSyncDateHr(query.getString(columnIndexOrThrow10));
                    entityDeviceInfo.setLastSyncDateBp(query.getString(columnIndexOrThrow11));
                    entityDeviceInfo.setLastSyncDateTemperature(query.getString(columnIndexOrThrow12));
                    entityDeviceInfo.setLastSyncDateSensAISummary(query.getString(columnIndexOrThrow13));
                    entityDeviceInfo.setLastSyncDateDistanceData(query.getString(columnIndexOrThrow14));
                    entityDeviceInfo.setLastSyncDateCalorieData(query.getString(columnIndexOrThrow15));
                    entityDeviceInfo.setLastSyncDateRr(query.getString(columnIndexOrThrow16));
                    entityDeviceInfo.setLastServerSyncSteps(query.getString(columnIndexOrThrow17));
                    entityDeviceInfo.setLastServerSyncSleep(query.getString(columnIndexOrThrow18));
                    entityDeviceInfo.setLastSyncDatePeriodicSpo2(query.getString(columnIndexOrThrow19));
                    entityDeviceInfo.setLastServerSyncDatePeriodicSpo2(query.getString(columnIndexOrThrow20));
                    entityDeviceInfo.setLastSyncDateAmbientSound(query.getString(columnIndexOrThrow21));
                } else {
                    entityDeviceInfo = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityDeviceInfo;
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

    @Override // com.coveiot.covedb.deviceinfo.DeviceInfoDao
    public void insert(EntityDeviceInfo entityDeviceInfo) {
        this.f6944a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityDeviceInfo);
            this.f6944a.setTransactionSuccessful();
        } finally {
            this.f6944a.endTransaction();
        }
    }
}
