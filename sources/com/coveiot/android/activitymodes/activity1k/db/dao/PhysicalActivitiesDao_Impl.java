package com.coveiot.android.activitymodes.activity1k.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.activitymodes.activity1k.db.convertors.Convertors;
import com.coveiot.android.activitymodes.activity1k.db.convertors.DeviceIconConvertor;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public final class PhysicalActivitiesDao_Impl implements PhysicalActivitiesDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f2749a;
    public final EntityInsertionAdapter<EntityActivityCategory> b;
    public final EntityInsertionAdapter<EntityPhysicalActivities> c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    /* loaded from: classes2.dex */
    public class a implements Callable<List<CategoryAndActivityModel>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public a(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<CategoryAndActivityModel> call() throws Exception {
            int i;
            Integer valueOf;
            Double valueOf2;
            int i2;
            Integer valueOf3;
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i3 = columnIndexOrThrow25;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    CategoryAndActivityModel categoryAndActivityModel = new CategoryAndActivityModel();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        valueOf = null;
                    } else {
                        i = columnIndexOrThrow;
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                    }
                    categoryAndActivityModel.setCategoryId(valueOf);
                    categoryAndActivityModel.setTitle(query.getString(columnIndexOrThrow2));
                    categoryAndActivityModel.setDescription(query.getString(columnIndexOrThrow3));
                    categoryAndActivityModel.setIconUrl(query.getString(columnIndexOrThrow4));
                    categoryAndActivityModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                    categoryAndActivityModel.setCategoryId(query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)));
                    categoryAndActivityModel.setActivityId(query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)));
                    categoryAndActivityModel.setFwActId(query.getInt(columnIndexOrThrow8));
                    categoryAndActivityModel.setCal_func(query.getString(columnIndexOrThrow9));
                    categoryAndActivityModel.setInbuilt(query.getInt(columnIndexOrThrow10) != 0);
                    categoryAndActivityModel.setActivityCode(query.getString(columnIndexOrThrow11));
                    categoryAndActivityModel.setCpaCode(query.getString(columnIndexOrThrow12));
                    int i4 = columnIndexOrThrow13;
                    int i5 = columnIndexOrThrow6;
                    categoryAndActivityModel.setShortTitle(query.getString(i4));
                    int i6 = columnIndexOrThrow14;
                    categoryAndActivityModel.setTitleInMetric(query.getString(i6));
                    int i7 = columnIndexOrThrow15;
                    categoryAndActivityModel.setTitleInImperial(query.getString(i7));
                    int i8 = columnIndexOrThrow16;
                    categoryAndActivityModel.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(i8)));
                    int i9 = columnIndexOrThrow17;
                    columnIndexOrThrow17 = i9;
                    categoryAndActivityModel.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(i9)));
                    int i10 = columnIndexOrThrow18;
                    categoryAndActivityModel.setDescInMetric(query.getString(i10));
                    columnIndexOrThrow18 = i10;
                    int i11 = columnIndexOrThrow19;
                    categoryAndActivityModel.setDescInImperial(query.getString(i11));
                    int i12 = columnIndexOrThrow20;
                    if (query.isNull(i12)) {
                        columnIndexOrThrow20 = i12;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow20 = i12;
                        valueOf2 = Double.valueOf(query.getDouble(i12));
                    }
                    categoryAndActivityModel.setDefaultMets(valueOf2);
                    int i13 = columnIndexOrThrow21;
                    columnIndexOrThrow21 = i13;
                    categoryAndActivityModel.setMetrics(Convertors.convertStringToMetricList(query.getString(i13)));
                    columnIndexOrThrow19 = i11;
                    int i14 = columnIndexOrThrow22;
                    categoryAndActivityModel.setDefaultActivityName(query.getString(i14));
                    int i15 = columnIndexOrThrow23;
                    if (query.isNull(i15)) {
                        i2 = i14;
                        valueOf3 = null;
                    } else {
                        i2 = i14;
                        valueOf3 = Integer.valueOf(query.getInt(i15));
                    }
                    categoryAndActivityModel.setDefaultCategoryIcon(valueOf3);
                    categoryAndActivityModel.setDefaultActivityIcon(query.isNull(columnIndexOrThrow24) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow24)));
                    int i16 = columnIndexOrThrow24;
                    int i17 = i3;
                    categoryAndActivityModel.setIconUrl(query.getString(i17));
                    int i18 = columnIndexOrThrow26;
                    categoryAndActivityModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i18)));
                    arrayList.add(categoryAndActivityModel);
                    columnIndexOrThrow24 = i16;
                    columnIndexOrThrow22 = i2;
                    columnIndexOrThrow26 = i18;
                    i3 = i17;
                    columnIndexOrThrow23 = i15;
                    columnIndexOrThrow6 = i5;
                    columnIndexOrThrow13 = i4;
                    columnIndexOrThrow14 = i6;
                    columnIndexOrThrow15 = i7;
                    columnIndexOrThrow16 = i8;
                    columnIndexOrThrow = i;
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
    public class b extends EntityInsertionAdapter<EntityActivityCategory> {
        public b(PhysicalActivitiesDao_Impl physicalActivitiesDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityActivityCategory entityActivityCategory) {
            if (entityActivityCategory.getCategoryId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindLong(1, entityActivityCategory.getCategoryId().intValue());
            }
            if (entityActivityCategory.getTitle() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityActivityCategory.getTitle());
            }
            if (entityActivityCategory.getDescription() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityActivityCategory.getDescription());
            }
            if (entityActivityCategory.getIconUrl() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityActivityCategory.getIconUrl());
            }
            String convertDeviceIconsListToString = DeviceIconConvertor.convertDeviceIconsListToString(entityActivityCategory.getDeviceIconModels());
            if (convertDeviceIconsListToString == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, convertDeviceIconsListToString);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_activity_category` (`categoryId`,`title`,`description`,`iconUrl`,`deviceIconModels`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class c extends EntityInsertionAdapter<EntityPhysicalActivities> {
        public c(PhysicalActivitiesDao_Impl physicalActivitiesDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityPhysicalActivities entityPhysicalActivities) {
            supportSQLiteStatement.bindLong(1, entityPhysicalActivities.getCategoryId());
            supportSQLiteStatement.bindLong(2, entityPhysicalActivities.getActivityId());
            supportSQLiteStatement.bindLong(3, entityPhysicalActivities.getFwActId());
            if (entityPhysicalActivities.getCal_func() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityPhysicalActivities.getCal_func());
            }
            supportSQLiteStatement.bindLong(5, entityPhysicalActivities.getInbuilt() ? 1L : 0L);
            if (entityPhysicalActivities.getActivityCode() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityPhysicalActivities.getActivityCode());
            }
            if (entityPhysicalActivities.getCpaCode() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, entityPhysicalActivities.getCpaCode());
            }
            if (entityPhysicalActivities.getShortTitle() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, entityPhysicalActivities.getShortTitle());
            }
            if (entityPhysicalActivities.getTitleInMetric() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, entityPhysicalActivities.getTitleInMetric());
            }
            if (entityPhysicalActivities.getTitleInImperial() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, entityPhysicalActivities.getTitleInImperial());
            }
            String convertMetricListToString = Convertors.convertMetricListToString(entityPhysicalActivities.getDvcTitleInMetric());
            if (convertMetricListToString == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, convertMetricListToString);
            }
            String convertMetricListToString2 = Convertors.convertMetricListToString(entityPhysicalActivities.getDvcTitleInImperial());
            if (convertMetricListToString2 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, convertMetricListToString2);
            }
            if (entityPhysicalActivities.getDescInMetric() == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, entityPhysicalActivities.getDescInMetric());
            }
            if (entityPhysicalActivities.getDescInImperial() == null) {
                supportSQLiteStatement.bindNull(14);
            } else {
                supportSQLiteStatement.bindString(14, entityPhysicalActivities.getDescInImperial());
            }
            if (entityPhysicalActivities.getDefaultMets() == null) {
                supportSQLiteStatement.bindNull(15);
            } else {
                supportSQLiteStatement.bindDouble(15, entityPhysicalActivities.getDefaultMets().doubleValue());
            }
            String convertMetricListToString3 = Convertors.convertMetricListToString(entityPhysicalActivities.getMetrics());
            if (convertMetricListToString3 == null) {
                supportSQLiteStatement.bindNull(16);
            } else {
                supportSQLiteStatement.bindString(16, convertMetricListToString3);
            }
            if (entityPhysicalActivities.getDefaultActivityName() == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, entityPhysicalActivities.getDefaultActivityName());
            }
            if (entityPhysicalActivities.getDefaultCategoryIcon() == null) {
                supportSQLiteStatement.bindNull(18);
            } else {
                supportSQLiteStatement.bindLong(18, entityPhysicalActivities.getDefaultCategoryIcon().intValue());
            }
            if (entityPhysicalActivities.getDefaultActivityIcon() == null) {
                supportSQLiteStatement.bindNull(19);
            } else {
                supportSQLiteStatement.bindLong(19, entityPhysicalActivities.getDefaultActivityIcon().intValue());
            }
            if (entityPhysicalActivities.getIconUrl() == null) {
                supportSQLiteStatement.bindNull(20);
            } else {
                supportSQLiteStatement.bindString(20, entityPhysicalActivities.getIconUrl());
            }
            String convertDeviceIconsListToString = DeviceIconConvertor.convertDeviceIconsListToString(entityPhysicalActivities.getDeviceIconModels());
            if (convertDeviceIconsListToString == null) {
                supportSQLiteStatement.bindNull(21);
            } else {
                supportSQLiteStatement.bindString(21, convertDeviceIconsListToString);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_physical_activities` (`categoryId`,`activityId`,`fwActId`,`cal_func`,`inbuilt`,`activityCode`,`cpaCode`,`shortTitle`,`titleInMetric`,`titleInImperial`,`dvcTitleInMetric`,`dvcTitleInImperial`,`descInMetric`,`descInImperial`,`defaultMets`,`metrics`,`defaultActivityName`,`defaultCategoryIcon`,`defaultActivityIcon`,`iconUrl`,`deviceIconModels`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes2.dex */
    public class d extends SharedSQLiteStatement {
        public d(PhysicalActivitiesDao_Impl physicalActivitiesDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM entity_activity_category";
        }
    }

    /* loaded from: classes2.dex */
    public class e extends SharedSQLiteStatement {
        public e(PhysicalActivitiesDao_Impl physicalActivitiesDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM entity_physical_activities";
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<List<ActivityCategoriesModel>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public f(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<ActivityCategoriesModel> call() throws Exception {
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ActivityCategoriesModel activityCategoriesModel = new ActivityCategoriesModel();
                    activityCategoriesModel.setCategoryId(query.isNull(columnIndexOrThrow) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow)));
                    activityCategoriesModel.setTitle(query.getString(columnIndexOrThrow2));
                    activityCategoriesModel.setDescription(query.getString(columnIndexOrThrow3));
                    activityCategoriesModel.setIconUrl(query.getString(columnIndexOrThrow4));
                    activityCategoriesModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                    arrayList.add(activityCategoriesModel);
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
    public class g implements Callable<List<EntityPhysicalActivities>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public g(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityPhysicalActivities> call() throws Exception {
            int i;
            Double valueOf;
            int i2;
            Integer valueOf2;
            Integer valueOf3;
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityPhysicalActivities entityPhysicalActivities = new EntityPhysicalActivities();
                    ArrayList arrayList2 = arrayList;
                    entityPhysicalActivities.setCategoryId(query.getInt(columnIndexOrThrow));
                    entityPhysicalActivities.setActivityId(query.getInt(columnIndexOrThrow2));
                    entityPhysicalActivities.setFwActId(query.getInt(columnIndexOrThrow3));
                    entityPhysicalActivities.setCal_func(query.getString(columnIndexOrThrow4));
                    entityPhysicalActivities.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    entityPhysicalActivities.setActivityCode(query.getString(columnIndexOrThrow6));
                    entityPhysicalActivities.setCpaCode(query.getString(columnIndexOrThrow7));
                    entityPhysicalActivities.setShortTitle(query.getString(columnIndexOrThrow8));
                    entityPhysicalActivities.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    entityPhysicalActivities.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    entityPhysicalActivities.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    entityPhysicalActivities.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    entityPhysicalActivities.setDescInMetric(query.getString(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    entityPhysicalActivities.setDescInImperial(query.getString(i4));
                    int i6 = columnIndexOrThrow15;
                    if (query.isNull(i6)) {
                        i = i6;
                        valueOf = null;
                    } else {
                        i = i6;
                        valueOf = Double.valueOf(query.getDouble(i6));
                    }
                    entityPhysicalActivities.setDefaultMets(valueOf);
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    entityPhysicalActivities.setMetrics(Convertors.convertStringToMetricList(query.getString(i7)));
                    int i8 = columnIndexOrThrow17;
                    entityPhysicalActivities.setDefaultActivityName(query.getString(i8));
                    int i9 = columnIndexOrThrow18;
                    if (query.isNull(i9)) {
                        i2 = i8;
                        valueOf2 = null;
                    } else {
                        i2 = i8;
                        valueOf2 = Integer.valueOf(query.getInt(i9));
                    }
                    entityPhysicalActivities.setDefaultCategoryIcon(valueOf2);
                    int i10 = columnIndexOrThrow19;
                    if (query.isNull(i10)) {
                        columnIndexOrThrow19 = i10;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow19 = i10;
                        valueOf3 = Integer.valueOf(query.getInt(i10));
                    }
                    entityPhysicalActivities.setDefaultActivityIcon(valueOf3);
                    int i11 = columnIndexOrThrow20;
                    entityPhysicalActivities.setIconUrl(query.getString(i11));
                    int i12 = columnIndexOrThrow21;
                    entityPhysicalActivities.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i12)));
                    arrayList2.add(entityPhysicalActivities);
                    columnIndexOrThrow20 = i11;
                    columnIndexOrThrow21 = i12;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow15 = i;
                    int i13 = i2;
                    columnIndexOrThrow18 = i9;
                    columnIndexOrThrow17 = i13;
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
    public class h implements Callable<List<ActivitiesListModel>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public h(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<ActivitiesListModel> call() throws Exception {
            int i;
            Integer valueOf;
            int i2;
            Double valueOf2;
            int i3;
            Integer valueOf3;
            Integer valueOf4;
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ActivitiesListModel activitiesListModel = new ActivitiesListModel();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        valueOf = null;
                    } else {
                        i = columnIndexOrThrow;
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                    }
                    activitiesListModel.setCategoryId(valueOf);
                    activitiesListModel.setActivityId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    activitiesListModel.setFwActId(query.getInt(columnIndexOrThrow3));
                    activitiesListModel.setCal_func(query.getString(columnIndexOrThrow4));
                    activitiesListModel.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    activitiesListModel.setActivityCode(query.getString(columnIndexOrThrow6));
                    activitiesListModel.setCpaCode(query.getString(columnIndexOrThrow7));
                    activitiesListModel.setShortTitle(query.getString(columnIndexOrThrow8));
                    activitiesListModel.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    activitiesListModel.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    activitiesListModel.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    activitiesListModel.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    activitiesListModel.setDescInMetric(query.getString(columnIndexOrThrow13));
                    int i5 = i4;
                    int i6 = columnIndexOrThrow13;
                    activitiesListModel.setDescInImperial(query.getString(i5));
                    int i7 = columnIndexOrThrow15;
                    if (query.isNull(i7)) {
                        i2 = i5;
                        valueOf2 = null;
                    } else {
                        i2 = i5;
                        valueOf2 = Double.valueOf(query.getDouble(i7));
                    }
                    activitiesListModel.setDefaultMets(valueOf2);
                    int i8 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i8;
                    activitiesListModel.setMetrics(Convertors.convertStringToMetricList(query.getString(i8)));
                    columnIndexOrThrow15 = i7;
                    int i9 = columnIndexOrThrow17;
                    activitiesListModel.setDefaultActivityName(query.getString(i9));
                    int i10 = columnIndexOrThrow18;
                    if (query.isNull(i10)) {
                        i3 = i9;
                        valueOf3 = null;
                    } else {
                        i3 = i9;
                        valueOf3 = Integer.valueOf(query.getInt(i10));
                    }
                    activitiesListModel.setDefaultCategoryIcon(valueOf3);
                    int i11 = columnIndexOrThrow19;
                    if (query.isNull(i11)) {
                        columnIndexOrThrow19 = i11;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow19 = i11;
                        valueOf4 = Integer.valueOf(query.getInt(i11));
                    }
                    activitiesListModel.setDefaultActivityIcon(valueOf4);
                    int i12 = columnIndexOrThrow20;
                    activitiesListModel.setIconUrl(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    activitiesListModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i13)));
                    arrayList.add(activitiesListModel);
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow = i;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow13 = i6;
                    i4 = i2;
                    int i14 = i3;
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow17 = i14;
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
    public class i implements Callable<List<ActivitiesListModel>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public i(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<ActivitiesListModel> call() throws Exception {
            int i;
            Integer valueOf;
            int i2;
            Double valueOf2;
            int i3;
            Integer valueOf3;
            Integer valueOf4;
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ActivitiesListModel activitiesListModel = new ActivitiesListModel();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        valueOf = null;
                    } else {
                        i = columnIndexOrThrow;
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                    }
                    activitiesListModel.setCategoryId(valueOf);
                    activitiesListModel.setActivityId(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    activitiesListModel.setFwActId(query.getInt(columnIndexOrThrow3));
                    activitiesListModel.setCal_func(query.getString(columnIndexOrThrow4));
                    activitiesListModel.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    activitiesListModel.setActivityCode(query.getString(columnIndexOrThrow6));
                    activitiesListModel.setCpaCode(query.getString(columnIndexOrThrow7));
                    activitiesListModel.setShortTitle(query.getString(columnIndexOrThrow8));
                    activitiesListModel.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    activitiesListModel.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    activitiesListModel.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    activitiesListModel.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    activitiesListModel.setDescInMetric(query.getString(columnIndexOrThrow13));
                    int i5 = i4;
                    int i6 = columnIndexOrThrow13;
                    activitiesListModel.setDescInImperial(query.getString(i5));
                    int i7 = columnIndexOrThrow15;
                    if (query.isNull(i7)) {
                        i2 = i5;
                        valueOf2 = null;
                    } else {
                        i2 = i5;
                        valueOf2 = Double.valueOf(query.getDouble(i7));
                    }
                    activitiesListModel.setDefaultMets(valueOf2);
                    int i8 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i8;
                    activitiesListModel.setMetrics(Convertors.convertStringToMetricList(query.getString(i8)));
                    columnIndexOrThrow15 = i7;
                    int i9 = columnIndexOrThrow17;
                    activitiesListModel.setDefaultActivityName(query.getString(i9));
                    int i10 = columnIndexOrThrow18;
                    if (query.isNull(i10)) {
                        i3 = i9;
                        valueOf3 = null;
                    } else {
                        i3 = i9;
                        valueOf3 = Integer.valueOf(query.getInt(i10));
                    }
                    activitiesListModel.setDefaultCategoryIcon(valueOf3);
                    int i11 = columnIndexOrThrow19;
                    if (query.isNull(i11)) {
                        columnIndexOrThrow19 = i11;
                        valueOf4 = null;
                    } else {
                        columnIndexOrThrow19 = i11;
                        valueOf4 = Integer.valueOf(query.getInt(i11));
                    }
                    activitiesListModel.setDefaultActivityIcon(valueOf4);
                    int i12 = columnIndexOrThrow20;
                    activitiesListModel.setIconUrl(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    activitiesListModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i13)));
                    arrayList.add(activitiesListModel);
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow = i;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow13 = i6;
                    i4 = i2;
                    int i14 = i3;
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow17 = i14;
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
    public class j implements Callable<List<EntityActivityCategory>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public j(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<EntityActivityCategory> call() throws Exception {
            Cursor query = DBUtil.query(PhysicalActivitiesDao_Impl.this.f2749a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityActivityCategory entityActivityCategory = new EntityActivityCategory();
                    entityActivityCategory.setCategoryId(query.isNull(columnIndexOrThrow) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow)));
                    entityActivityCategory.setTitle(query.getString(columnIndexOrThrow2));
                    entityActivityCategory.setDescription(query.getString(columnIndexOrThrow3));
                    entityActivityCategory.setIconUrl(query.getString(columnIndexOrThrow4));
                    entityActivityCategory.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                    arrayList.add(entityActivityCategory);
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

    public PhysicalActivitiesDao_Impl(RoomDatabase roomDatabase) {
        this.f2749a = roomDatabase;
        this.b = new b(this, roomDatabase);
        this.c = new c(this, roomDatabase);
        this.d = new d(this, roomDatabase);
        this.e = new e(this, roomDatabase);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public void clearActivityTable() {
        this.f2749a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        this.f2749a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f2749a.setTransactionSuccessful();
        } finally {
            this.f2749a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public void clearCategoryTable() {
        this.f2749a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        this.f2749a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f2749a.setTransactionSuccessful();
        } finally {
            this.f2749a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<ActivityCategoriesModel>> getActivitiesCategories() {
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_activity_category"}, false, new f(RoomSQLiteQuery.acquire("SELECT * FROM entity_activity_category", 0)));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public ActivityCategoriesModel getActivitiesCategory(int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_activity_category where categoryId=?", 1);
        acquire.bindLong(1, i2);
        this.f2749a.assertNotSuspendingTransaction();
        ActivityCategoriesModel activityCategoriesModel = null;
        Integer valueOf = null;
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
            if (query.moveToFirst()) {
                ActivityCategoriesModel activityCategoriesModel2 = new ActivityCategoriesModel();
                if (!query.isNull(columnIndexOrThrow)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                }
                activityCategoriesModel2.setCategoryId(valueOf);
                activityCategoriesModel2.setTitle(query.getString(columnIndexOrThrow2));
                activityCategoriesModel2.setDescription(query.getString(columnIndexOrThrow3));
                activityCategoriesModel2.setIconUrl(query.getString(columnIndexOrThrow4));
                activityCategoriesModel2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                activityCategoriesModel = activityCategoriesModel2;
            }
            return activityCategoriesModel;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<CategoryAndActivityModel>> getActivityAndCategories() {
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_activity_category", "entity_physical_activities"}, false, new a(RoomSQLiteQuery.acquire("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId ", 0)));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public List<CategoryAndActivityModel> getActivityAndCategoriesWithoutLiveData() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i2;
        Integer valueOf;
        Double valueOf2;
        int i3;
        Integer valueOf3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId ", 0);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i4 = columnIndexOrThrow25;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    CategoryAndActivityModel categoryAndActivityModel = new CategoryAndActivityModel();
                    if (query.isNull(columnIndexOrThrow)) {
                        i2 = columnIndexOrThrow;
                        valueOf = null;
                    } else {
                        i2 = columnIndexOrThrow;
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow));
                    }
                    categoryAndActivityModel.setCategoryId(valueOf);
                    categoryAndActivityModel.setTitle(query.getString(columnIndexOrThrow2));
                    categoryAndActivityModel.setDescription(query.getString(columnIndexOrThrow3));
                    categoryAndActivityModel.setIconUrl(query.getString(columnIndexOrThrow4));
                    categoryAndActivityModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                    categoryAndActivityModel.setCategoryId(query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)));
                    categoryAndActivityModel.setActivityId(query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)));
                    categoryAndActivityModel.setFwActId(query.getInt(columnIndexOrThrow8));
                    categoryAndActivityModel.setCal_func(query.getString(columnIndexOrThrow9));
                    categoryAndActivityModel.setInbuilt(query.getInt(columnIndexOrThrow10) != 0);
                    categoryAndActivityModel.setActivityCode(query.getString(columnIndexOrThrow11));
                    categoryAndActivityModel.setCpaCode(query.getString(columnIndexOrThrow12));
                    int i5 = columnIndexOrThrow13;
                    int i6 = columnIndexOrThrow12;
                    categoryAndActivityModel.setShortTitle(query.getString(i5));
                    int i7 = columnIndexOrThrow14;
                    int i8 = columnIndexOrThrow6;
                    categoryAndActivityModel.setTitleInMetric(query.getString(i7));
                    int i9 = columnIndexOrThrow15;
                    categoryAndActivityModel.setTitleInImperial(query.getString(i9));
                    int i10 = columnIndexOrThrow16;
                    categoryAndActivityModel.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(i10)));
                    int i11 = columnIndexOrThrow17;
                    columnIndexOrThrow17 = i11;
                    categoryAndActivityModel.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(i11)));
                    int i12 = columnIndexOrThrow18;
                    categoryAndActivityModel.setDescInMetric(query.getString(i12));
                    columnIndexOrThrow18 = i12;
                    int i13 = columnIndexOrThrow19;
                    categoryAndActivityModel.setDescInImperial(query.getString(i13));
                    int i14 = columnIndexOrThrow20;
                    if (query.isNull(i14)) {
                        columnIndexOrThrow20 = i14;
                        valueOf2 = null;
                    } else {
                        columnIndexOrThrow20 = i14;
                        valueOf2 = Double.valueOf(query.getDouble(i14));
                    }
                    categoryAndActivityModel.setDefaultMets(valueOf2);
                    int i15 = columnIndexOrThrow21;
                    columnIndexOrThrow21 = i15;
                    categoryAndActivityModel.setMetrics(Convertors.convertStringToMetricList(query.getString(i15)));
                    columnIndexOrThrow19 = i13;
                    int i16 = columnIndexOrThrow22;
                    categoryAndActivityModel.setDefaultActivityName(query.getString(i16));
                    int i17 = columnIndexOrThrow23;
                    if (query.isNull(i17)) {
                        i3 = i16;
                        valueOf3 = null;
                    } else {
                        i3 = i16;
                        valueOf3 = Integer.valueOf(query.getInt(i17));
                    }
                    categoryAndActivityModel.setDefaultCategoryIcon(valueOf3);
                    categoryAndActivityModel.setDefaultActivityIcon(query.isNull(columnIndexOrThrow24) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow24)));
                    int i18 = i4;
                    categoryAndActivityModel.setIconUrl(query.getString(i18));
                    int i19 = columnIndexOrThrow26;
                    i4 = i18;
                    categoryAndActivityModel.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i19)));
                    arrayList.add(categoryAndActivityModel);
                    columnIndexOrThrow26 = i19;
                    columnIndexOrThrow12 = i6;
                    columnIndexOrThrow6 = i8;
                    columnIndexOrThrow14 = i7;
                    columnIndexOrThrow15 = i9;
                    columnIndexOrThrow16 = i10;
                    columnIndexOrThrow13 = i5;
                    columnIndexOrThrow = i2;
                    int i20 = i3;
                    columnIndexOrThrow23 = i17;
                    columnIndexOrThrow22 = i20;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public CategoryAndActivityModel getActivityAndCategoryList(int i2, int i3) {
        RoomSQLiteQuery roomSQLiteQuery;
        CategoryAndActivityModel categoryAndActivityModel;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId where a.categoryId=? and a.activityId=?", 2);
        acquire.bindLong(1, i3);
        acquire.bindLong(2, i2);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SavingTrackHelper.POINT_COL_DESCRIPTION);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                if (query.moveToFirst()) {
                    CategoryAndActivityModel categoryAndActivityModel2 = new CategoryAndActivityModel();
                    categoryAndActivityModel2.setCategoryId(query.isNull(columnIndexOrThrow) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow)));
                    categoryAndActivityModel2.setTitle(query.getString(columnIndexOrThrow2));
                    categoryAndActivityModel2.setDescription(query.getString(columnIndexOrThrow3));
                    categoryAndActivityModel2.setIconUrl(query.getString(columnIndexOrThrow4));
                    categoryAndActivityModel2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow5)));
                    categoryAndActivityModel2.setCategoryId(query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)));
                    categoryAndActivityModel2.setActivityId(query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)));
                    categoryAndActivityModel2.setFwActId(query.getInt(columnIndexOrThrow8));
                    categoryAndActivityModel2.setCal_func(query.getString(columnIndexOrThrow9));
                    categoryAndActivityModel2.setInbuilt(query.getInt(columnIndexOrThrow10) != 0);
                    categoryAndActivityModel2.setActivityCode(query.getString(columnIndexOrThrow11));
                    categoryAndActivityModel2.setCpaCode(query.getString(columnIndexOrThrow12));
                    categoryAndActivityModel2.setShortTitle(query.getString(columnIndexOrThrow13));
                    categoryAndActivityModel2.setTitleInMetric(query.getString(columnIndexOrThrow14));
                    categoryAndActivityModel2.setTitleInImperial(query.getString(columnIndexOrThrow15));
                    categoryAndActivityModel2.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow16)));
                    categoryAndActivityModel2.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow17)));
                    categoryAndActivityModel2.setDescInMetric(query.getString(columnIndexOrThrow18));
                    categoryAndActivityModel2.setDescInImperial(query.getString(columnIndexOrThrow19));
                    categoryAndActivityModel2.setDefaultMets(query.isNull(columnIndexOrThrow20) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow20)));
                    categoryAndActivityModel2.setMetrics(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow21)));
                    categoryAndActivityModel2.setDefaultActivityName(query.getString(columnIndexOrThrow22));
                    categoryAndActivityModel2.setDefaultCategoryIcon(query.isNull(columnIndexOrThrow23) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow23)));
                    categoryAndActivityModel2.setDefaultActivityIcon(query.isNull(columnIndexOrThrow24) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow24)));
                    categoryAndActivityModel2.setIconUrl(query.getString(columnIndexOrThrow25));
                    categoryAndActivityModel2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow26)));
                    categoryAndActivityModel = categoryAndActivityModel2;
                } else {
                    categoryAndActivityModel = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return categoryAndActivityModel;
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

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<ActivitiesListModel>> getAllPhysicalActivityList() {
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_physical_activities"}, false, new h(RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities", 0)));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public List<EntityPhysicalActivities> getDefaultActivities() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i2;
        Double valueOf;
        int i3;
        Integer valueOf2;
        Integer valueOf3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities WHERE inbuilt=1", 0);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityPhysicalActivities entityPhysicalActivities = new EntityPhysicalActivities();
                    ArrayList arrayList2 = arrayList;
                    entityPhysicalActivities.setCategoryId(query.getInt(columnIndexOrThrow));
                    entityPhysicalActivities.setActivityId(query.getInt(columnIndexOrThrow2));
                    entityPhysicalActivities.setFwActId(query.getInt(columnIndexOrThrow3));
                    entityPhysicalActivities.setCal_func(query.getString(columnIndexOrThrow4));
                    entityPhysicalActivities.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    entityPhysicalActivities.setActivityCode(query.getString(columnIndexOrThrow6));
                    entityPhysicalActivities.setCpaCode(query.getString(columnIndexOrThrow7));
                    entityPhysicalActivities.setShortTitle(query.getString(columnIndexOrThrow8));
                    entityPhysicalActivities.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    entityPhysicalActivities.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    entityPhysicalActivities.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    entityPhysicalActivities.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    entityPhysicalActivities.setDescInMetric(query.getString(columnIndexOrThrow13));
                    int i5 = i4;
                    int i6 = columnIndexOrThrow;
                    entityPhysicalActivities.setDescInImperial(query.getString(i5));
                    int i7 = columnIndexOrThrow15;
                    if (query.isNull(i7)) {
                        i2 = i7;
                        valueOf = null;
                    } else {
                        i2 = i7;
                        valueOf = Double.valueOf(query.getDouble(i7));
                    }
                    entityPhysicalActivities.setDefaultMets(valueOf);
                    int i8 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i8;
                    entityPhysicalActivities.setMetrics(Convertors.convertStringToMetricList(query.getString(i8)));
                    int i9 = columnIndexOrThrow12;
                    int i10 = columnIndexOrThrow17;
                    entityPhysicalActivities.setDefaultActivityName(query.getString(i10));
                    int i11 = columnIndexOrThrow18;
                    if (query.isNull(i11)) {
                        i3 = i10;
                        valueOf2 = null;
                    } else {
                        i3 = i10;
                        valueOf2 = Integer.valueOf(query.getInt(i11));
                    }
                    entityPhysicalActivities.setDefaultCategoryIcon(valueOf2);
                    int i12 = columnIndexOrThrow19;
                    if (query.isNull(i12)) {
                        columnIndexOrThrow19 = i12;
                        valueOf3 = null;
                    } else {
                        columnIndexOrThrow19 = i12;
                        valueOf3 = Integer.valueOf(query.getInt(i12));
                    }
                    entityPhysicalActivities.setDefaultActivityIcon(valueOf3);
                    int i13 = columnIndexOrThrow20;
                    entityPhysicalActivities.setIconUrl(query.getString(i13));
                    int i14 = columnIndexOrThrow21;
                    entityPhysicalActivities.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(i14)));
                    arrayList2.add(entityPhysicalActivities);
                    columnIndexOrThrow20 = i13;
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow15 = i2;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i6;
                    i4 = i5;
                    int i15 = i3;
                    columnIndexOrThrow18 = i11;
                    columnIndexOrThrow17 = i15;
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

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public EntityPhysicalActivities getPhysicalActivity(int i2, int i3) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityPhysicalActivities entityPhysicalActivities;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities WHERE categoryId=? AND activityId=?", 2);
        acquire.bindLong(1, i2);
        acquire.bindLong(2, i3);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                if (query.moveToFirst()) {
                    EntityPhysicalActivities entityPhysicalActivities2 = new EntityPhysicalActivities();
                    entityPhysicalActivities2.setCategoryId(query.getInt(columnIndexOrThrow));
                    entityPhysicalActivities2.setActivityId(query.getInt(columnIndexOrThrow2));
                    entityPhysicalActivities2.setFwActId(query.getInt(columnIndexOrThrow3));
                    entityPhysicalActivities2.setCal_func(query.getString(columnIndexOrThrow4));
                    entityPhysicalActivities2.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    entityPhysicalActivities2.setActivityCode(query.getString(columnIndexOrThrow6));
                    entityPhysicalActivities2.setCpaCode(query.getString(columnIndexOrThrow7));
                    entityPhysicalActivities2.setShortTitle(query.getString(columnIndexOrThrow8));
                    entityPhysicalActivities2.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    entityPhysicalActivities2.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    entityPhysicalActivities2.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    entityPhysicalActivities2.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    entityPhysicalActivities2.setDescInMetric(query.getString(columnIndexOrThrow13));
                    entityPhysicalActivities2.setDescInImperial(query.getString(columnIndexOrThrow14));
                    entityPhysicalActivities2.setDefaultMets(query.isNull(columnIndexOrThrow15) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow15)));
                    entityPhysicalActivities2.setMetrics(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow16)));
                    entityPhysicalActivities2.setDefaultActivityName(query.getString(columnIndexOrThrow17));
                    entityPhysicalActivities2.setDefaultCategoryIcon(query.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow18)));
                    entityPhysicalActivities2.setDefaultActivityIcon(query.isNull(columnIndexOrThrow19) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow19)));
                    entityPhysicalActivities2.setIconUrl(query.getString(columnIndexOrThrow20));
                    entityPhysicalActivities2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow21)));
                    entityPhysicalActivities = entityPhysicalActivities2;
                } else {
                    entityPhysicalActivities = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityPhysicalActivities;
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

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public EntityPhysicalActivities getPhysicalActivityByFWActivityId(int i2, int i3) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityPhysicalActivities entityPhysicalActivities;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities WHERE categoryId=? AND fwActId=?", 2);
        acquire.bindLong(1, i2);
        acquire.bindLong(2, i3);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                if (query.moveToFirst()) {
                    EntityPhysicalActivities entityPhysicalActivities2 = new EntityPhysicalActivities();
                    entityPhysicalActivities2.setCategoryId(query.getInt(columnIndexOrThrow));
                    entityPhysicalActivities2.setActivityId(query.getInt(columnIndexOrThrow2));
                    entityPhysicalActivities2.setFwActId(query.getInt(columnIndexOrThrow3));
                    entityPhysicalActivities2.setCal_func(query.getString(columnIndexOrThrow4));
                    entityPhysicalActivities2.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    entityPhysicalActivities2.setActivityCode(query.getString(columnIndexOrThrow6));
                    entityPhysicalActivities2.setCpaCode(query.getString(columnIndexOrThrow7));
                    entityPhysicalActivities2.setShortTitle(query.getString(columnIndexOrThrow8));
                    entityPhysicalActivities2.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    entityPhysicalActivities2.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    entityPhysicalActivities2.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    entityPhysicalActivities2.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    entityPhysicalActivities2.setDescInMetric(query.getString(columnIndexOrThrow13));
                    entityPhysicalActivities2.setDescInImperial(query.getString(columnIndexOrThrow14));
                    entityPhysicalActivities2.setDefaultMets(query.isNull(columnIndexOrThrow15) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow15)));
                    entityPhysicalActivities2.setMetrics(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow16)));
                    entityPhysicalActivities2.setDefaultActivityName(query.getString(columnIndexOrThrow17));
                    entityPhysicalActivities2.setDefaultCategoryIcon(query.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow18)));
                    entityPhysicalActivities2.setDefaultActivityIcon(query.isNull(columnIndexOrThrow19) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow19)));
                    entityPhysicalActivities2.setIconUrl(query.getString(columnIndexOrThrow20));
                    entityPhysicalActivities2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow21)));
                    entityPhysicalActivities = entityPhysicalActivities2;
                } else {
                    entityPhysicalActivities = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityPhysicalActivities;
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

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public String getPhysicalActivityCategoryName(int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT title FROM entity_activity_category WHERE categoryId=?", 1);
        acquire.bindLong(1, i2);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<EntityPhysicalActivities>> getPhysicalActivityList(int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities where categoryId=?", 1);
        acquire.bindLong(1, i2);
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_physical_activities"}, false, new g(acquire));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public EntityPhysicalActivities getPhysicalActivityN(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        EntityPhysicalActivities entityPhysicalActivities;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities WHERE activityCode=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
            if (query.moveToFirst()) {
                EntityPhysicalActivities entityPhysicalActivities2 = new EntityPhysicalActivities();
                entityPhysicalActivities2.setCategoryId(query.getInt(columnIndexOrThrow));
                entityPhysicalActivities2.setActivityId(query.getInt(columnIndexOrThrow2));
                entityPhysicalActivities2.setFwActId(query.getInt(columnIndexOrThrow3));
                entityPhysicalActivities2.setCal_func(query.getString(columnIndexOrThrow4));
                entityPhysicalActivities2.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                entityPhysicalActivities2.setActivityCode(query.getString(columnIndexOrThrow6));
                entityPhysicalActivities2.setCpaCode(query.getString(columnIndexOrThrow7));
                entityPhysicalActivities2.setShortTitle(query.getString(columnIndexOrThrow8));
                entityPhysicalActivities2.setTitleInMetric(query.getString(columnIndexOrThrow9));
                entityPhysicalActivities2.setTitleInImperial(query.getString(columnIndexOrThrow10));
                entityPhysicalActivities2.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                entityPhysicalActivities2.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                entityPhysicalActivities2.setDescInMetric(query.getString(columnIndexOrThrow13));
                entityPhysicalActivities2.setDescInImperial(query.getString(columnIndexOrThrow14));
                entityPhysicalActivities2.setDefaultMets(query.isNull(columnIndexOrThrow15) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow15)));
                entityPhysicalActivities2.setMetrics(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow16)));
                entityPhysicalActivities2.setDefaultActivityName(query.getString(columnIndexOrThrow17));
                entityPhysicalActivities2.setDefaultCategoryIcon(query.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow18)));
                entityPhysicalActivities2.setDefaultActivityIcon(query.isNull(columnIndexOrThrow19) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow19)));
                entityPhysicalActivities2.setIconUrl(query.getString(columnIndexOrThrow20));
                entityPhysicalActivities2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow21)));
                entityPhysicalActivities = entityPhysicalActivities2;
            } else {
                entityPhysicalActivities = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityPhysicalActivities;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public String getPhysicalActivityName(int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT titleInMetric FROM entity_physical_activities WHERE categoryId=? AND activityId=?", 2);
        acquire.bindLong(1, i3);
        acquire.bindLong(2, i2);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public long[] insertActivityCategories(List<EntityActivityCategory> list) {
        this.f2749a.assertNotSuspendingTransaction();
        this.f2749a.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.b.insertAndReturnIdsArray(list);
            this.f2749a.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.f2749a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public long[] insertPhysicalActivities(List<EntityPhysicalActivities> list) {
        this.f2749a.assertNotSuspendingTransaction();
        this.f2749a.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.c.insertAndReturnIdsArray(list);
            this.f2749a.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.f2749a.endTransaction();
        }
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<EntityActivityCategory>> getActivitiesCategories(List<Integer> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT ");
        newStringBuilder.append(Marker.ANY_MARKER);
        newStringBuilder.append(" FROM entity_activity_category where categoryId in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i2 = 1;
        for (Integer num : list) {
            if (num == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, num.intValue());
            }
            i2++;
        }
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_activity_category"}, false, new j(acquire));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public LiveData<List<ActivitiesListModel>> getAllPhysicalActivityList(int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities  where categoryId=?", 1);
        acquire.bindLong(1, i2);
        return this.f2749a.getInvalidationTracker().createLiveData(new String[]{"entity_physical_activities"}, false, new i(acquire));
    }

    @Override // com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao
    public EntityPhysicalActivities getPhysicalActivity(int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        EntityPhysicalActivities entityPhysicalActivities;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entity_physical_activities WHERE fwActId=?", 1);
        acquire.bindLong(1, i2);
        this.f2749a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f2749a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "categoryId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "activityId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "fwActId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "cal_func");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "inbuilt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "activityCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cpaCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "shortTitle");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "titleInMetric");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "titleInImperial");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInMetric");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "dvcTitleInImperial");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "descInMetric");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "descInImperial");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "defaultMets");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "metrics");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "defaultCategoryIcon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "defaultActivityIcon");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "iconUrl");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "deviceIconModels");
                if (query.moveToFirst()) {
                    EntityPhysicalActivities entityPhysicalActivities2 = new EntityPhysicalActivities();
                    entityPhysicalActivities2.setCategoryId(query.getInt(columnIndexOrThrow));
                    entityPhysicalActivities2.setActivityId(query.getInt(columnIndexOrThrow2));
                    entityPhysicalActivities2.setFwActId(query.getInt(columnIndexOrThrow3));
                    entityPhysicalActivities2.setCal_func(query.getString(columnIndexOrThrow4));
                    entityPhysicalActivities2.setInbuilt(query.getInt(columnIndexOrThrow5) != 0);
                    entityPhysicalActivities2.setActivityCode(query.getString(columnIndexOrThrow6));
                    entityPhysicalActivities2.setCpaCode(query.getString(columnIndexOrThrow7));
                    entityPhysicalActivities2.setShortTitle(query.getString(columnIndexOrThrow8));
                    entityPhysicalActivities2.setTitleInMetric(query.getString(columnIndexOrThrow9));
                    entityPhysicalActivities2.setTitleInImperial(query.getString(columnIndexOrThrow10));
                    entityPhysicalActivities2.setDvcTitleInMetric(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow11)));
                    entityPhysicalActivities2.setDvcTitleInImperial(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow12)));
                    entityPhysicalActivities2.setDescInMetric(query.getString(columnIndexOrThrow13));
                    entityPhysicalActivities2.setDescInImperial(query.getString(columnIndexOrThrow14));
                    entityPhysicalActivities2.setDefaultMets(query.isNull(columnIndexOrThrow15) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow15)));
                    entityPhysicalActivities2.setMetrics(Convertors.convertStringToMetricList(query.getString(columnIndexOrThrow16)));
                    entityPhysicalActivities2.setDefaultActivityName(query.getString(columnIndexOrThrow17));
                    entityPhysicalActivities2.setDefaultCategoryIcon(query.isNull(columnIndexOrThrow18) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow18)));
                    entityPhysicalActivities2.setDefaultActivityIcon(query.isNull(columnIndexOrThrow19) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow19)));
                    entityPhysicalActivities2.setIconUrl(query.getString(columnIndexOrThrow20));
                    entityPhysicalActivities2.setDeviceIconModels(DeviceIconConvertor.convertStringToDeviceIconsList(query.getString(columnIndexOrThrow21)));
                    entityPhysicalActivities = entityPhysicalActivities2;
                } else {
                    entityPhysicalActivities = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return entityPhysicalActivities;
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
}
