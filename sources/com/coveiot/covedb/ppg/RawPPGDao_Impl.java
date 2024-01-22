package com.coveiot.covedb.ppg;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import java.util.List;
/* loaded from: classes8.dex */
public final class RawPPGDao_Impl implements RawPPGDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6960a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityRawPPGData> {
        public a(RawPPGDao_Impl rawPPGDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityRawPPGData entityRawPPGData) {
            supportSQLiteStatement.bindLong(1, entityRawPPGData.getTimestamp());
            if (entityRawPPGData.getSerial_number() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityRawPPGData.getSerial_number());
            }
            supportSQLiteStatement.bindLong(3, entityRawPPGData.getRecordNumber());
            if (entityRawPPGData.getPpgDataFilePath() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityRawPPGData.getPpgDataFilePath());
            }
            supportSQLiteStatement.bindLong(5, entityRawPPGData.getSamplingRate());
            supportSQLiteStatement.bindLong(6, entityRawPPGData.getPpgType());
            supportSQLiteStatement.bindLong(7, entityRawPPGData.getInterval());
            supportSQLiteStatement.bindLong(8, entityRawPPGData.getDuration());
            supportSQLiteStatement.bindLong(9, entityRawPPGData.getMovementLevel());
            supportSQLiteStatement.bindLong(10, entityRawPPGData.getIs_sync_to_server());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `rawPPGDataHistory`(`timestamp`,`serial_number`,`recordNumber`,`ppgDataFilePath`,`samplingRate`,`ppgType`,`interval`,`duration`,`movementLevel`,`is_sync_to_server`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public RawPPGDao_Impl(RoomDatabase roomDatabase) {
        this.f6960a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.ppg.RawPPGDao
    public void insert(EntityRawPPGData entityRawPPGData) {
        this.f6960a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityRawPPGData);
            this.f6960a.setTransactionSuccessful();
        } finally {
            this.f6960a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ppg.RawPPGDao
    public void insertAll(List<EntityRawPPGData> list) {
        this.f6960a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6960a.setTransactionSuccessful();
        } finally {
            this.f6960a.endTransaction();
        }
    }
}
