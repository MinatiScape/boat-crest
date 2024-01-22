package com.coveiot.android.activitymodes.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.activitymodes.database.convertors.Covertors;
import com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public final class PreparationPlanDao_Impl implements PreparationPlanDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f2799a;
    public final EntityInsertionAdapter<EntityPreparationPlan> b;
    public final EntityInsertionAdapter<EntityPlanSchedule> c;
    public final EntityInsertionAdapter<EntityPreparationWeek> d;
    public final EntityInsertionAdapter<EntityPreparationDay> e;
    public final SharedSQLiteStatement f;

    /* loaded from: classes2.dex */
    public class a extends EntityInsertionAdapter<EntityPreparationPlan> {
        public a(PreparationPlanDao_Impl preparationPlanDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityPreparationPlan entityPreparationPlan) {
            String str = entityPreparationPlan.planId;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityPreparationPlan.userPlanId;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityPreparationPlan.shortTitle;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            String str4 = entityPreparationPlan.fulltitle;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str4);
            }
            String str5 = entityPreparationPlan.subtitle;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str5);
            }
            String str6 = entityPreparationPlan.shortDesc;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str6);
            }
            String fromArrayLisr = Covertors.fromArrayLisr(entityPreparationPlan.images);
            if (fromArrayLisr == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, fromArrayLisr);
            }
            String str7 = entityPreparationPlan.imageUrl;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, str7);
            }
            String fromArrayLisr2 = Covertors.fromArrayLisr(entityPreparationPlan.introText);
            if (fromArrayLisr2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, fromArrayLisr2);
            }
            String str8 = entityPreparationPlan.startDate;
            if (str8 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, str8);
            }
            String str9 = entityPreparationPlan.category;
            if (str9 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str9);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_preparation_plan` (`planId`,`userPlanId`,`shortTitle`,`fulltitle`,`subtitle`,`shortDesc`,`images`,`imageUrl`,`introText`,`startDate`,`category`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class b extends EntityInsertionAdapter<EntityPlanSchedule> {
        public b(PreparationPlanDao_Impl preparationPlanDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityPlanSchedule entityPlanSchedule) {
            supportSQLiteStatement.bindLong(1, entityPlanSchedule.getSchedule_id());
            String str = entityPlanSchedule.plan_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            String fromArrayLisr = Covertors.fromArrayLisr(entityPlanSchedule.overview);
            if (fromArrayLisr == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, fromArrayLisr);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR ABORT INTO `entity_plan_schedule` (`schedule_id`,`plan_id`,`overview`) VALUES (nullif(?, 0),?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class c extends EntityInsertionAdapter<EntityPreparationWeek> {
        public c(PreparationPlanDao_Impl preparationPlanDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityPreparationWeek entityPreparationWeek) {
            supportSQLiteStatement.bindLong(1, entityPreparationWeek.getWeek_number());
            String str = entityPreparationWeek.plan_id;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            supportSQLiteStatement.bindLong(3, entityPreparationWeek.getScheduleId());
            String str2 = entityPreparationWeek.name;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str2);
            }
            String str3 = entityPreparationWeek.shortDesc;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str3);
            }
            String fromArrayLisr = Covertors.fromArrayLisr(entityPreparationWeek.introTexts);
            if (fromArrayLisr == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, fromArrayLisr);
            }
            supportSQLiteStatement.bindLong(7, entityPreparationWeek.getWeeklyTarget());
            if (entityPreparationWeek.getTargetBaseUnit() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, entityPreparationWeek.getTargetBaseUnit());
            }
            String fromArrayLisr2 = Covertors.fromArrayLisr(entityPreparationWeek.daysRange);
            if (fromArrayLisr2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, fromArrayLisr2);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR ABORT INTO `entity_preparation_week` (`week_number`,`plan_id`,`scheduleId`,`name`,`shortDesc`,`introTexts`,`weeklyTarget`,`targetBaseUnit`,`daysRange`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class d extends EntityInsertionAdapter<EntityPreparationDay> {
        public d(PreparationPlanDao_Impl preparationPlanDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityPreparationDay entityPreparationDay) {
            supportSQLiteStatement.bindLong(1, entityPreparationDay.getWeek_number());
            String str = entityPreparationDay.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            supportSQLiteStatement.bindLong(3, entityPreparationDay.getScheduleId());
            String str2 = entityPreparationDay.plan_id;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str2);
            }
            supportSQLiteStatement.bindLong(5, entityPreparationDay.getDay_number());
            if (entityPreparationDay.getName() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityPreparationDay.getName());
            }
            String preparationPlanActivityListStringFron = Covertors.getPreparationPlanActivityListStringFron(entityPreparationDay.activities);
            if (preparationPlanActivityListStringFron == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, preparationPlanActivityListStringFron);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_preparation_day` (`week_number`,`date`,`scheduleId`,`plan_id`,`day_number`,`name`,`activities`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class e extends SharedSQLiteStatement {
        public e(PreparationPlanDao_Impl preparationPlanDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM entity_preparation_plan";
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<List<EntityPreparationWeek>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public f(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityPreparationWeek> call() throws Exception {
            Cursor query = DBUtil.query(PreparationPlanDao_Impl.this.f2799a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "shortDesc");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "introTexts");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "weeklyTarget");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "targetBaseUnit");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "daysRange");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityPreparationWeek entityPreparationWeek = new EntityPreparationWeek();
                    entityPreparationWeek.setWeek_number(query.getInt(columnIndexOrThrow));
                    entityPreparationWeek.plan_id = query.getString(columnIndexOrThrow2);
                    entityPreparationWeek.setScheduleId(query.getInt(columnIndexOrThrow3));
                    entityPreparationWeek.name = query.getString(columnIndexOrThrow4);
                    entityPreparationWeek.shortDesc = query.getString(columnIndexOrThrow5);
                    entityPreparationWeek.introTexts = Covertors.fromString(query.getString(columnIndexOrThrow6));
                    entityPreparationWeek.setWeeklyTarget(query.getInt(columnIndexOrThrow7));
                    entityPreparationWeek.setTargetBaseUnit(query.getString(columnIndexOrThrow8));
                    entityPreparationWeek.daysRange = Covertors.fromString(query.getString(columnIndexOrThrow9));
                    arrayList.add(entityPreparationWeek);
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

    /* loaded from: classes2.dex */
    public class g implements Callable<List<EntityPreparationDay>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public g(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityPreparationDay> call() throws Exception {
            Cursor query = DBUtil.query(PreparationPlanDao_Impl.this.f2799a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "day_number");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activities");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityPreparationDay entityPreparationDay = new EntityPreparationDay();
                    entityPreparationDay.setWeek_number(query.getInt(columnIndexOrThrow));
                    entityPreparationDay.date = query.getString(columnIndexOrThrow2);
                    entityPreparationDay.setScheduleId(query.getInt(columnIndexOrThrow3));
                    entityPreparationDay.plan_id = query.getString(columnIndexOrThrow4);
                    entityPreparationDay.setDay_number(query.getInt(columnIndexOrThrow5));
                    entityPreparationDay.setName(query.getString(columnIndexOrThrow6));
                    entityPreparationDay.activities = Covertors.getPreparationPlanActivityListFron(query.getString(columnIndexOrThrow7));
                    arrayList.add(entityPreparationDay);
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

    public PreparationPlanDao_Impl(RoomDatabase roomDatabase) {
        this.f2799a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
        this.f = new e(this, roomDatabase);
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public void deletePlan() {
        this.f2799a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f.acquire();
        this.f2799a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f2799a.setTransactionSuccessful();
        } finally {
            this.f2799a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public EntityPreparationPlan getCurrentPlanInfo() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_plan ORDER BY startDate DESC LIMIT 1", 0);
        this.f2799a.assertNotSuspendingTransaction();
        EntityPreparationPlan entityPreparationPlan = null;
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "planId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "userPlanId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fulltitle");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "subtitle");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "shortDesc");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "images");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "imageUrl");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "introText");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_CATEGORY);
            if (query.moveToFirst()) {
                entityPreparationPlan = new EntityPreparationPlan();
                entityPreparationPlan.planId = query.getString(columnIndexOrThrow);
                entityPreparationPlan.userPlanId = query.getString(columnIndexOrThrow2);
                entityPreparationPlan.shortTitle = query.getString(columnIndexOrThrow3);
                entityPreparationPlan.fulltitle = query.getString(columnIndexOrThrow4);
                entityPreparationPlan.subtitle = query.getString(columnIndexOrThrow5);
                entityPreparationPlan.shortDesc = query.getString(columnIndexOrThrow6);
                entityPreparationPlan.images = Covertors.fromString(query.getString(columnIndexOrThrow7));
                entityPreparationPlan.imageUrl = query.getString(columnIndexOrThrow8);
                entityPreparationPlan.introText = Covertors.fromString(query.getString(columnIndexOrThrow9));
                entityPreparationPlan.startDate = query.getString(columnIndexOrThrow10);
                entityPreparationPlan.category = query.getString(columnIndexOrThrow11);
            }
            return entityPreparationPlan;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public LiveData<List<EntityPreparationDay>> getDayLevelInfo(String str, int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_day WHERE plan_id=? AND week_number=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        return this.f2799a.getInvalidationTracker().createLiveData(new String[]{"entity_preparation_day"}, false, new g(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public EntityPreparationDay getDayLevelInfoBasedOnDate(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_day WHERE plan_id=? AND date=?", 2);
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
        this.f2799a.assertNotSuspendingTransaction();
        EntityPreparationDay entityPreparationDay = null;
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "day_number");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activities");
            if (query.moveToFirst()) {
                entityPreparationDay = new EntityPreparationDay();
                entityPreparationDay.setWeek_number(query.getInt(columnIndexOrThrow));
                entityPreparationDay.date = query.getString(columnIndexOrThrow2);
                entityPreparationDay.setScheduleId(query.getInt(columnIndexOrThrow3));
                entityPreparationDay.plan_id = query.getString(columnIndexOrThrow4);
                entityPreparationDay.setDay_number(query.getInt(columnIndexOrThrow5));
                entityPreparationDay.setName(query.getString(columnIndexOrThrow6));
                entityPreparationDay.activities = Covertors.getPreparationPlanActivityListFron(query.getString(columnIndexOrThrow7));
            }
            return entityPreparationDay;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public List<EntityPreparationDay> getDayLevelInfoWithOutLiveData(String str, int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_day WHERE plan_id=? AND week_number=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        this.f2799a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "day_number");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activities");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityPreparationDay entityPreparationDay = new EntityPreparationDay();
                entityPreparationDay.setWeek_number(query.getInt(columnIndexOrThrow));
                entityPreparationDay.date = query.getString(columnIndexOrThrow2);
                entityPreparationDay.setScheduleId(query.getInt(columnIndexOrThrow3));
                entityPreparationDay.plan_id = query.getString(columnIndexOrThrow4);
                entityPreparationDay.setDay_number(query.getInt(columnIndexOrThrow5));
                entityPreparationDay.setName(query.getString(columnIndexOrThrow6));
                entityPreparationDay.activities = Covertors.getPreparationPlanActivityListFron(query.getString(columnIndexOrThrow7));
                arrayList.add(entityPreparationDay);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public List<EntityPreparationDay> getDayLevelInfoWithinDateRange(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_day WHERE plan_id=? AND strftime('%Y-%m-%d',date) BETWEEN ? AND ?", 3);
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
        if (str3 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str3);
        }
        this.f2799a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "day_number");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activities");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityPreparationDay entityPreparationDay = new EntityPreparationDay();
                entityPreparationDay.setWeek_number(query.getInt(columnIndexOrThrow));
                entityPreparationDay.date = query.getString(columnIndexOrThrow2);
                entityPreparationDay.setScheduleId(query.getInt(columnIndexOrThrow3));
                entityPreparationDay.plan_id = query.getString(columnIndexOrThrow4);
                entityPreparationDay.setDay_number(query.getInt(columnIndexOrThrow5));
                entityPreparationDay.setName(query.getString(columnIndexOrThrow6));
                entityPreparationDay.activities = Covertors.getPreparationPlanActivityListFron(query.getString(columnIndexOrThrow7));
                arrayList.add(entityPreparationDay);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public String getPlanEndDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date FROM  entity_preparation_day WHERE plan_id=? ORDER BY date DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2799a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public LiveData<List<EntityPreparationWeek>> getWeekLevelInfo(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_week WHERE plan_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f2799a.getInvalidationTracker().createLiveData(new String[]{"entity_preparation_week"}, false, new f(acquire));
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public List<EntityPreparationWeek> getWeekLevelInfoWithoutLiveData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_week WHERE plan_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2799a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "shortDesc");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "introTexts");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "weeklyTarget");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "targetBaseUnit");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "daysRange");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityPreparationWeek entityPreparationWeek = new EntityPreparationWeek();
                entityPreparationWeek.setWeek_number(query.getInt(columnIndexOrThrow));
                entityPreparationWeek.plan_id = query.getString(columnIndexOrThrow2);
                entityPreparationWeek.setScheduleId(query.getInt(columnIndexOrThrow3));
                entityPreparationWeek.name = query.getString(columnIndexOrThrow4);
                entityPreparationWeek.shortDesc = query.getString(columnIndexOrThrow5);
                entityPreparationWeek.introTexts = Covertors.fromString(query.getString(columnIndexOrThrow6));
                entityPreparationWeek.setWeeklyTarget(query.getInt(columnIndexOrThrow7));
                entityPreparationWeek.setTargetBaseUnit(query.getString(columnIndexOrThrow8));
                entityPreparationWeek.daysRange = Covertors.fromString(query.getString(columnIndexOrThrow9));
                arrayList.add(entityPreparationWeek);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public void insertDailyPlan(List<EntityPreparationDay> list) {
        this.f2799a.assertNotSuspendingTransaction();
        this.f2799a.beginTransaction();
        try {
            this.e.insert(list);
            this.f2799a.setTransactionSuccessful();
        } finally {
            this.f2799a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public void insertPlan(EntityPreparationPlan entityPreparationPlan) {
        this.f2799a.assertNotSuspendingTransaction();
        this.f2799a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<EntityPreparationPlan>) entityPreparationPlan);
            this.f2799a.setTransactionSuccessful();
        } finally {
            this.f2799a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public void insertPlanSchedule(EntityPlanSchedule entityPlanSchedule) {
        this.f2799a.assertNotSuspendingTransaction();
        this.f2799a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter<EntityPlanSchedule>) entityPlanSchedule);
            this.f2799a.setTransactionSuccessful();
        } finally {
            this.f2799a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public void insertWeeklyPlan(List<EntityPreparationWeek> list) {
        this.f2799a.assertNotSuspendingTransaction();
        this.f2799a.beginTransaction();
        try {
            this.d.insert(list);
            this.f2799a.setTransactionSuccessful();
        } finally {
            this.f2799a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public int rowCountPlan() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM entity_preparation_plan", 0);
        this.f2799a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.database.dao.PreparationPlanDao
    public EntityPreparationWeek getWeekLevelInfo(String str, int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_preparation_week WHERE plan_id=? AND week_number=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        this.f2799a.assertNotSuspendingTransaction();
        EntityPreparationWeek entityPreparationWeek = null;
        Cursor query = DBUtil.query(this.f2799a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "week_number");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "plan_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "scheduleId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "shortDesc");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "introTexts");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "weeklyTarget");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "targetBaseUnit");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "daysRange");
            if (query.moveToFirst()) {
                entityPreparationWeek = new EntityPreparationWeek();
                entityPreparationWeek.setWeek_number(query.getInt(columnIndexOrThrow));
                entityPreparationWeek.plan_id = query.getString(columnIndexOrThrow2);
                entityPreparationWeek.setScheduleId(query.getInt(columnIndexOrThrow3));
                entityPreparationWeek.name = query.getString(columnIndexOrThrow4);
                entityPreparationWeek.shortDesc = query.getString(columnIndexOrThrow5);
                entityPreparationWeek.introTexts = Covertors.fromString(query.getString(columnIndexOrThrow6));
                entityPreparationWeek.setWeeklyTarget(query.getInt(columnIndexOrThrow7));
                entityPreparationWeek.setTargetBaseUnit(query.getString(columnIndexOrThrow8));
                entityPreparationWeek.daysRange = Covertors.fromString(query.getString(columnIndexOrThrow9));
            }
            return entityPreparationWeek;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
