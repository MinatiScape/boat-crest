package com.jieli.bluetooth_connect.data.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.event.stat.one.d;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class HistoryRecordDao_Impl implements HistoryRecordDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<HistoryRecord> __deletionAdapterOfHistoryRecord;
    private final EntityInsertionAdapter<HistoryRecord> __insertionAdapterOfHistoryRecord;
    private final EntityDeletionOrUpdateAdapter<HistoryRecord> __updateAdapterOfHistoryRecord;

    public HistoryRecordDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfHistoryRecord = new EntityInsertionAdapter<HistoryRecord>(roomDatabase) { // from class: com.jieli.bluetooth_connect.data.dao.HistoryRecordDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `HistoryRecord` (`id`,`name`,`address`,`mapped_address`,`dev_type`,`connect_type`,`sdk_flag`,`vid`,`uid`,`pid`,`left_dev_lat`,`left_dev_lon`,`left_dev_update_time`,`right_dev_lat`,`right_dev_lon`,`right_dev_update_time`,`online_time`,`update_address`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, HistoryRecord historyRecord) {
                supportSQLiteStatement.bindLong(1, historyRecord.getId());
                if (historyRecord.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, historyRecord.getName());
                }
                if (historyRecord.getAddress() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, historyRecord.getAddress());
                }
                if (historyRecord.getMappedAddress() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, historyRecord.getMappedAddress());
                }
                supportSQLiteStatement.bindLong(5, historyRecord.getDevType());
                supportSQLiteStatement.bindLong(6, historyRecord.getConnectType());
                supportSQLiteStatement.bindLong(7, historyRecord.getSdkFlag());
                supportSQLiteStatement.bindLong(8, historyRecord.getVid());
                supportSQLiteStatement.bindLong(9, historyRecord.getUid());
                supportSQLiteStatement.bindLong(10, historyRecord.getPid());
                supportSQLiteStatement.bindDouble(11, historyRecord.getLeftDevLatitude());
                supportSQLiteStatement.bindDouble(12, historyRecord.getLeftDevLongitude());
                supportSQLiteStatement.bindLong(13, historyRecord.getLeftDevUpdateTime());
                supportSQLiteStatement.bindDouble(14, historyRecord.getRightDevLatitude());
                supportSQLiteStatement.bindDouble(15, historyRecord.getRightDevLongitude());
                supportSQLiteStatement.bindLong(16, historyRecord.getRightDevUpdateTime());
                supportSQLiteStatement.bindLong(17, historyRecord.getOnlineTime());
                if (historyRecord.getUpdateAddress() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, historyRecord.getUpdateAddress());
                }
            }
        };
        this.__deletionAdapterOfHistoryRecord = new EntityDeletionOrUpdateAdapter<HistoryRecord>(roomDatabase) { // from class: com.jieli.bluetooth_connect.data.dao.HistoryRecordDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `HistoryRecord` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, HistoryRecord historyRecord) {
                supportSQLiteStatement.bindLong(1, historyRecord.getId());
            }
        };
        this.__updateAdapterOfHistoryRecord = new EntityDeletionOrUpdateAdapter<HistoryRecord>(roomDatabase) { // from class: com.jieli.bluetooth_connect.data.dao.HistoryRecordDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `HistoryRecord` SET `id` = ?,`name` = ?,`address` = ?,`mapped_address` = ?,`dev_type` = ?,`connect_type` = ?,`sdk_flag` = ?,`vid` = ?,`uid` = ?,`pid` = ?,`left_dev_lat` = ?,`left_dev_lon` = ?,`left_dev_update_time` = ?,`right_dev_lat` = ?,`right_dev_lon` = ?,`right_dev_update_time` = ?,`online_time` = ?,`update_address` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, HistoryRecord historyRecord) {
                supportSQLiteStatement.bindLong(1, historyRecord.getId());
                if (historyRecord.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, historyRecord.getName());
                }
                if (historyRecord.getAddress() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, historyRecord.getAddress());
                }
                if (historyRecord.getMappedAddress() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, historyRecord.getMappedAddress());
                }
                supportSQLiteStatement.bindLong(5, historyRecord.getDevType());
                supportSQLiteStatement.bindLong(6, historyRecord.getConnectType());
                supportSQLiteStatement.bindLong(7, historyRecord.getSdkFlag());
                supportSQLiteStatement.bindLong(8, historyRecord.getVid());
                supportSQLiteStatement.bindLong(9, historyRecord.getUid());
                supportSQLiteStatement.bindLong(10, historyRecord.getPid());
                supportSQLiteStatement.bindDouble(11, historyRecord.getLeftDevLatitude());
                supportSQLiteStatement.bindDouble(12, historyRecord.getLeftDevLongitude());
                supportSQLiteStatement.bindLong(13, historyRecord.getLeftDevUpdateTime());
                supportSQLiteStatement.bindDouble(14, historyRecord.getRightDevLatitude());
                supportSQLiteStatement.bindDouble(15, historyRecord.getRightDevLongitude());
                supportSQLiteStatement.bindLong(16, historyRecord.getRightDevUpdateTime());
                supportSQLiteStatement.bindLong(17, historyRecord.getOnlineTime());
                if (historyRecord.getUpdateAddress() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, historyRecord.getUpdateAddress());
                }
                supportSQLiteStatement.bindLong(19, historyRecord.getId());
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public void addHistoryRecord(HistoryRecord historyRecord) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfHistoryRecord.insert((EntityInsertionAdapter<HistoryRecord>) historyRecord);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public HistoryRecord getHistoryRecord(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        HistoryRecord historyRecord;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HistoryRecord WHERE address LIKE ? OR mapped_address LIKE ? OR update_address LIKE ? ORDER BY online_time DESC LIMIT 1", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "address");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mapped_address");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "dev_type");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, d.F);
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "sdk_flag");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "vid");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "uid");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "pid");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_lat");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_lon");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_update_time");
                roomSQLiteQuery = acquire;
                try {
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_lat");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_lon");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_update_time");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, d.q);
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "update_address");
                        if (query.moveToFirst()) {
                            HistoryRecord historyRecord2 = new HistoryRecord();
                            historyRecord2.setId(query.getInt(columnIndexOrThrow));
                            historyRecord2.setName(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                            historyRecord2.setAddress(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                            historyRecord2.setMappedAddress(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                            historyRecord2.setDevType(query.getInt(columnIndexOrThrow5));
                            historyRecord2.setConnectType(query.getInt(columnIndexOrThrow6));
                            historyRecord2.setSdkFlag(query.getInt(columnIndexOrThrow7));
                            historyRecord2.setVid(query.getInt(columnIndexOrThrow8));
                            historyRecord2.setUid(query.getInt(columnIndexOrThrow9));
                            historyRecord2.setPid(query.getInt(columnIndexOrThrow10));
                            historyRecord2.setLeftDevLatitude(query.getDouble(columnIndexOrThrow11));
                            historyRecord2.setLeftDevLongitude(query.getDouble(columnIndexOrThrow12));
                            historyRecord2.setLeftDevUpdateTime(query.getLong(columnIndexOrThrow13));
                            historyRecord2.setRightDevLatitude(query.getDouble(columnIndexOrThrow14));
                            historyRecord2.setRightDevLongitude(query.getDouble(columnIndexOrThrow15));
                            historyRecord2.setRightDevUpdateTime(query.getLong(columnIndexOrThrow16));
                            historyRecord2.setOnlineTime(query.getLong(columnIndexOrThrow17));
                            historyRecord2.setUpdateAddress(query.isNull(columnIndexOrThrow18) ? null : query.getString(columnIndexOrThrow18));
                            historyRecord = historyRecord2;
                        } else {
                            historyRecord = null;
                        }
                        this.__db.setTransactionSuccessful();
                        query.close();
                        roomSQLiteQuery.release();
                        return historyRecord;
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        roomSQLiteQuery.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                roomSQLiteQuery = acquire;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.room.RoomSQLiteQuery, androidx.sqlite.db.SupportSQLiteQuery] */
    /* JADX WARN: Type inference failed for: r3v1 */
    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public List<HistoryRecord> getHistoryRecordList() {
        RoomSQLiteQuery roomSQLiteQuery;
        HistoryRecordDao_Impl acquire = RoomSQLiteQuery.acquire("SELECT * FROM HistoryRecord ORDER BY online_time DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            try {
                Cursor query = DBUtil.query(this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "address");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mapped_address");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "dev_type");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, d.F);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "sdk_flag");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "vid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "uid");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "pid");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_lat");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_lon");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "left_dev_update_time");
                    roomSQLiteQuery = acquire;
                    try {
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_lat");
                        try {
                            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_lon");
                            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "right_dev_update_time");
                            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, d.q);
                            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "update_address");
                            int i = columnIndexOrThrow14;
                            ArrayList arrayList = new ArrayList(query.getCount());
                            while (query.moveToNext()) {
                                HistoryRecord historyRecord = new HistoryRecord();
                                ArrayList arrayList2 = arrayList;
                                historyRecord.setId(query.getInt(columnIndexOrThrow));
                                historyRecord.setName(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                                historyRecord.setAddress(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                                historyRecord.setMappedAddress(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                                historyRecord.setDevType(query.getInt(columnIndexOrThrow5));
                                historyRecord.setConnectType(query.getInt(columnIndexOrThrow6));
                                historyRecord.setSdkFlag(query.getInt(columnIndexOrThrow7));
                                historyRecord.setVid(query.getInt(columnIndexOrThrow8));
                                historyRecord.setUid(query.getInt(columnIndexOrThrow9));
                                historyRecord.setPid(query.getInt(columnIndexOrThrow10));
                                int i2 = columnIndexOrThrow;
                                historyRecord.setLeftDevLatitude(query.getDouble(columnIndexOrThrow11));
                                historyRecord.setLeftDevLongitude(query.getDouble(columnIndexOrThrow12));
                                historyRecord.setLeftDevUpdateTime(query.getLong(columnIndexOrThrow13));
                                int i3 = columnIndexOrThrow2;
                                int i4 = i;
                                int i5 = columnIndexOrThrow13;
                                historyRecord.setRightDevLatitude(query.getDouble(i4));
                                int i6 = columnIndexOrThrow15;
                                historyRecord.setRightDevLongitude(query.getDouble(i6));
                                int i7 = columnIndexOrThrow16;
                                historyRecord.setRightDevUpdateTime(query.getLong(i7));
                                int i8 = columnIndexOrThrow17;
                                historyRecord.setOnlineTime(query.getLong(i8));
                                int i9 = columnIndexOrThrow18;
                                historyRecord.setUpdateAddress(query.isNull(i9) ? null : query.getString(i9));
                                arrayList = arrayList2;
                                arrayList.add(historyRecord);
                                columnIndexOrThrow18 = i9;
                                columnIndexOrThrow = i2;
                                columnIndexOrThrow17 = i8;
                                columnIndexOrThrow13 = i5;
                                i = i4;
                                columnIndexOrThrow16 = i7;
                                columnIndexOrThrow2 = i3;
                                columnIndexOrThrow15 = i6;
                            }
                            try {
                                this.__db.setTransactionSuccessful();
                                query.close();
                                roomSQLiteQuery.release();
                                this.__db.endTransaction();
                                return arrayList;
                            } catch (Throwable th) {
                                th = th;
                                query.close();
                                roomSQLiteQuery.release();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        query.close();
                        roomSQLiteQuery.release();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    roomSQLiteQuery = acquire;
                }
            } catch (Throwable th5) {
                th = th5;
                acquire.__db.endTransaction();
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            acquire = this;
            acquire.__db.endTransaction();
            throw th;
        }
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public void removeHistoryRecord(HistoryRecord historyRecord) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHistoryRecord.handle(historyRecord);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public void removeHistoryRecords(List<HistoryRecord> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHistoryRecord.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public void updateHistoryRecord(HistoryRecord historyRecord) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfHistoryRecord.handle(historyRecord);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jieli.bluetooth_connect.data.dao.HistoryRecordDao
    public void updateHistoryRecords(List<HistoryRecord> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfHistoryRecord.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
