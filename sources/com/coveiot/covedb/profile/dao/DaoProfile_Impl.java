package com.coveiot.covedb.profile.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public final class DaoProfile_Impl implements DaoProfile {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6962a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityProfile> {
        public a(DaoProfile_Impl daoProfile_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityProfile entityProfile) {
            supportSQLiteStatement.bindLong(1, entityProfile.timestamp);
            supportSQLiteStatement.bindLong(2, entityProfile.height);
            supportSQLiteStatement.bindDouble(3, entityProfile.weight);
            supportSQLiteStatement.bindLong(4, entityProfile.stepsTarget);
            supportSQLiteStatement.bindLong(5, entityProfile.sleepTarget);
            supportSQLiteStatement.bindLong(6, entityProfile.age);
            supportSQLiteStatement.bindLong(7, entityProfile.walkStrideLength);
            supportSQLiteStatement.bindLong(8, entityProfile.runStrideLength);
            supportSQLiteStatement.bindLong(9, entityProfile.restingHr);
            supportSQLiteStatement.bindDouble(10, entityProfile.physicalActivityScore);
            supportSQLiteStatement.bindLong(11, entityProfile.updatedToBand ? 1L : 0L);
            supportSQLiteStatement.bindLong(12, entityProfile.gender);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR IGNORE INTO `profile`(`timestamp`,`height`,`weight`,`steps_target`,`sleep_target`,`age`,`walkStrideLength`,`runStrideLength`,`restingHr`,`physicalActivityScore`,`updatedToBand`,`gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityProfile> {
        public b(DaoProfile_Impl daoProfile_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityProfile entityProfile) {
            supportSQLiteStatement.bindLong(1, entityProfile.timestamp);
            supportSQLiteStatement.bindLong(2, entityProfile.height);
            supportSQLiteStatement.bindDouble(3, entityProfile.weight);
            supportSQLiteStatement.bindLong(4, entityProfile.stepsTarget);
            supportSQLiteStatement.bindLong(5, entityProfile.sleepTarget);
            supportSQLiteStatement.bindLong(6, entityProfile.age);
            supportSQLiteStatement.bindLong(7, entityProfile.walkStrideLength);
            supportSQLiteStatement.bindLong(8, entityProfile.runStrideLength);
            supportSQLiteStatement.bindLong(9, entityProfile.restingHr);
            supportSQLiteStatement.bindDouble(10, entityProfile.physicalActivityScore);
            supportSQLiteStatement.bindLong(11, entityProfile.updatedToBand ? 1L : 0L);
            supportSQLiteStatement.bindLong(12, entityProfile.gender);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `profile`(`timestamp`,`height`,`weight`,`steps_target`,`sleep_target`,`age`,`walkStrideLength`,`runStrideLength`,`restingHr`,`physicalActivityScore`,`updatedToBand`,`gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public DaoProfile_Impl(RoomDatabase roomDatabase) {
        this.f6962a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.profile.dao.DaoProfile
    public EntityProfile getTheLatestProfileData() {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityProfile entityProfile;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from profile order by timestamp desc limit 1", 0);
        Cursor query = this.f6962a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timestamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(Property.ICON_TEXT_FIT_HEIGHT);
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("weight");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("sleep_target");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("age");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("walkStrideLength");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("runStrideLength");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("restingHr");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("physicalActivityScore");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("updatedToBand");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("gender");
            if (query.moveToFirst()) {
                entityProfile = new EntityProfile();
                roomSQLiteQuery = acquire;
                try {
                    entityProfile.timestamp = query.getLong(columnIndexOrThrow);
                    entityProfile.height = query.getInt(columnIndexOrThrow2);
                    entityProfile.weight = query.getDouble(columnIndexOrThrow3);
                    entityProfile.stepsTarget = query.getInt(columnIndexOrThrow4);
                    entityProfile.sleepTarget = query.getInt(columnIndexOrThrow5);
                    entityProfile.age = query.getInt(columnIndexOrThrow6);
                    entityProfile.walkStrideLength = query.getInt(columnIndexOrThrow7);
                    entityProfile.runStrideLength = query.getInt(columnIndexOrThrow8);
                    entityProfile.restingHr = query.getInt(columnIndexOrThrow9);
                    entityProfile.physicalActivityScore = query.getDouble(columnIndexOrThrow10);
                    entityProfile.updatedToBand = query.getInt(columnIndexOrThrow11) != 0;
                    entityProfile.gender = query.getInt(columnIndexOrThrow12);
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = acquire;
                entityProfile = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityProfile;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.profile.dao.DaoProfile
    public EntityProfile getTheLatestProfileDataTillDate(Long l) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityProfile entityProfile;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from profile where timestamp<? order by timestamp desc limit 1", 1);
        if (l == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, l.longValue());
        }
        Cursor query = this.f6962a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timestamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(Property.ICON_TEXT_FIT_HEIGHT);
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("weight");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("sleep_target");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("age");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("walkStrideLength");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("runStrideLength");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("restingHr");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("physicalActivityScore");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("updatedToBand");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("gender");
            if (query.moveToFirst()) {
                entityProfile = new EntityProfile();
                roomSQLiteQuery = acquire;
                try {
                    entityProfile.timestamp = query.getLong(columnIndexOrThrow);
                    entityProfile.height = query.getInt(columnIndexOrThrow2);
                    entityProfile.weight = query.getDouble(columnIndexOrThrow3);
                    entityProfile.stepsTarget = query.getInt(columnIndexOrThrow4);
                    entityProfile.sleepTarget = query.getInt(columnIndexOrThrow5);
                    entityProfile.age = query.getInt(columnIndexOrThrow6);
                    entityProfile.walkStrideLength = query.getInt(columnIndexOrThrow7);
                    entityProfile.runStrideLength = query.getInt(columnIndexOrThrow8);
                    entityProfile.restingHr = query.getInt(columnIndexOrThrow9);
                    entityProfile.physicalActivityScore = query.getDouble(columnIndexOrThrow10);
                    entityProfile.updatedToBand = query.getInt(columnIndexOrThrow11) != 0;
                    entityProfile.gender = query.getInt(columnIndexOrThrow12);
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = acquire;
                entityProfile = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityProfile;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.profile.dao.DaoProfile
    public void insert(EntityProfile entityProfile) {
        this.f6962a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityProfile);
            this.f6962a.setTransactionSuccessful();
        } finally {
            this.f6962a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.profile.dao.DaoProfile
    public void insertOrUpdate(EntityProfile entityProfile) {
        this.f6962a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) entityProfile);
            this.f6962a.setTransactionSuccessful();
        } finally {
            this.f6962a.endTransaction();
        }
    }
}
