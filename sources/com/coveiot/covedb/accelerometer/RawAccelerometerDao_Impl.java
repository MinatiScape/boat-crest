package com.coveiot.covedb.accelerometer;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import java.util.List;
/* loaded from: classes8.dex */
public final class RawAccelerometerDao_Impl implements RawAccelerometerDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6935a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityRawAccelerometerData> {
        public a(RawAccelerometerDao_Impl rawAccelerometerDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityRawAccelerometerData entityRawAccelerometerData) {
            supportSQLiteStatement.bindLong(1, entityRawAccelerometerData.getTimestamp());
            if (entityRawAccelerometerData.getSerial_number() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityRawAccelerometerData.getSerial_number());
            }
            supportSQLiteStatement.bindLong(3, entityRawAccelerometerData.getRecordNumber());
            if (entityRawAccelerometerData.getAccelerometerDataFilePath() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityRawAccelerometerData.getAccelerometerDataFilePath());
            }
            supportSQLiteStatement.bindLong(5, entityRawAccelerometerData.getSamplingRate());
            supportSQLiteStatement.bindLong(6, entityRawAccelerometerData.getInterval());
            supportSQLiteStatement.bindLong(7, entityRawAccelerometerData.getDuration());
            supportSQLiteStatement.bindLong(8, entityRawAccelerometerData.getIs_sync_to_server());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `rawAccelerometerDataHistory`(`timestamp`,`serial_number`,`recordNumber`,`accelerometerDataFilePath`,`samplingRate`,`interval`,`duration`,`is_sync_to_server`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    public RawAccelerometerDao_Impl(RoomDatabase roomDatabase) {
        this.f6935a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.accelerometer.RawAccelerometerDao
    public void insert(EntityRawAccelerometerData entityRawAccelerometerData) {
        this.f6935a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityRawAccelerometerData);
            this.f6935a.setTransactionSuccessful();
        } finally {
            this.f6935a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.accelerometer.RawAccelerometerDao
    public void insertAll(List<EntityRawAccelerometerData> list) {
        this.f6935a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6935a.setTransactionSuccessful();
        } finally {
            this.f6935a.endTransaction();
        }
    }
}
