package com.coveiot.android.activitymodes.activity1k.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.coveiot.android.activitymodes.activity1k.db.convertors.Convertors;
import com.coveiot.android.activitymodes.activity1k.db.convertors.DeviceIconConvertor;
import com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
@TypeConverters({Convertors.class, DeviceIconConvertor.class})
@Database(entities = {EntityActivityCategory.class, EntityPhysicalActivities.class}, exportSchema = false, version = 14)
/* loaded from: classes2.dex */
public abstract class PhysicalActivityDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static final Migration f2747a;
    public static final String activityDatabase = "physical_activity_database";
    public static final Migration b;
    public static PhysicalActivityDatabase c;

    /* loaded from: classes2.dex */
    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'cal_func' TEXT   DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'inbuilt' INTEGER NOT NULL  DEFAULT 0");
        }
    }

    /* loaded from: classes2.dex */
    public class b extends Migration {
        public b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'fwActId' INTEGER NOT NULL  DEFAULT -1");
        }
    }

    /* loaded from: classes2.dex */
    public class c extends Migration {
        public c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'iconUrl' TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'deviceIconModels' TEXT DEFAULT NULL");
        }
    }

    /* loaded from: classes2.dex */
    public class d extends Migration {
        public d(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'titleInMetric' TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'titleInImperial' TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'dvcTitleInMetric' TEXT DEFAULT NULL");
            supportSQLiteDatabase.execSQL("ALTER TABLE 'entity_physical_activities' ADD COLUMN 'dvcTitleInImperial' TEXT DEFAULT NULL");
        }
    }

    static {
        new a(1, 2);
        new b(2, 3);
        f2747a = new c(12, 13);
        b = new d(13, 14);
    }

    public static PhysicalActivityDatabase getDatabase(Context context) {
        if (c == null) {
            c = (PhysicalActivityDatabase) Room.databaseBuilder(context, PhysicalActivityDatabase.class, activityDatabase).allowMainThreadQueries().fallbackToDestructiveMigration().addMigrations(f2747a).addMigrations(b).build();
        }
        return c;
    }

    public abstract PhysicalActivitiesDao physicalActivityDao();
}
