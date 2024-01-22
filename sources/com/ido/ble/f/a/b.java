package com.ido.ble.f.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.e;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.data.manage.database.DaoMaster;
import com.ido.ble.data.manage.database.DaoSession;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityDao;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateDao;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondDao;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureDao;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthPressureItemDao;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepDao;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Dao;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSpO2ItemDao;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthSwimmingDao;
import com.ido.ble.f.a.e.d;
import com.ido.ble.f.a.e.f;
import com.ido.ble.f.a.e.g;
import com.ido.ble.f.a.e.h;
import com.ido.ble.f.a.e.i;
import com.ido.ble.f.a.e.j;
import com.ido.ble.f.a.e.k;
import com.ido.ble.f.a.e.l;
import com.ido.ble.f.a.e.m;
import com.ido.ble.f.a.e.n;
import com.ido.ble.f.a.e.o;
import com.ido.ble.gps.database.HealthGpsDao;
import com.ido.ble.gps.database.HealthGpsItemDao;
import com.ido.ble.logs.LogTool;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class b {
    private static final String b = "idoLib.db";
    private static final String c = "A*&DF90,Kudc123";
    private static b d;

    /* renamed from: a  reason: collision with root package name */
    private DaoSession f12225a;

    /* loaded from: classes11.dex */
    public static class a extends DaoMaster.OpenHelper {
        public a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        private void a(Database database) {
            com.ido.ble.f.a.a aVar = new com.ido.ble.f.a.a();
            aVar.a(database, HealthGpsDao.class);
            aVar.a(database, HealthGpsItemDao.class);
        }

        private void b(Database database) {
            com.ido.ble.f.a.a aVar = new com.ido.ble.f.a.a();
            aVar.a(database, HealthSpO2Dao.class);
            aVar.a(database, HealthSpO2ItemDao.class);
            aVar.a(database, HealthPressureDao.class);
            aVar.a(database, HealthPressureItemDao.class);
        }

        private void c(Database database) {
            com.ido.ble.f.a.a aVar = new com.ido.ble.f.a.a();
            aVar.a(database, HealthHeartRateSecondDao.class);
            aVar.a(database, HealthSwimmingDao.class);
            aVar.b(database, HealthSleepDao.class);
            aVar.b(database, HealthHeartRateDao.class);
        }

        private void d(Database database) {
            com.ido.ble.f.a.a aVar = new com.ido.ble.f.a.a();
            aVar.b(database, HealthActivityDao.class);
            aVar.b(database, HealthSwimmingDao.class);
        }

        private void e(Database database) {
            new com.ido.ble.f.a.a().b(database, HealthActivityDao.class);
        }

        private void f(Database database) {
            new com.ido.ble.f.a.a().b(database, HealthActivityDao.class);
        }

        @Override // com.ido.ble.data.manage.database.DaoMaster.OpenHelper, org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onCreate(Database database) {
            super.onCreate(database);
            if (database.getRawDatabase() instanceof SQLiteDatabase) {
                LogTool.d("DB_Helper", "db version is " + ((SQLiteDatabase) database.getRawDatabase()).getVersion());
            }
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onUpgrade(Database database, int i, int i2) {
            LogTool.d("DB_Helper", i + Constants.SEPARATOR_COMMA + i2);
            if (i == 1) {
                a(database);
            } else if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            if (i != 6) {
                                return;
                            }
                            f(database);
                        }
                        e(database);
                        f(database);
                    }
                    d(database);
                    e(database);
                    f(database);
                }
                c(database);
                d(database);
                e(database);
                f(database);
            }
            b(database);
            c(database);
            d(database);
            e(database);
            f(database);
        }
    }

    private b() {
    }

    private long c() {
        return com.ido.ble.c.a() ? com.ido.ble.f.a.f.b.e().c().mDeviceId : e();
    }

    public static synchronized b d() {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                b bVar2 = new b();
                d = bVar2;
                bVar2.f();
            }
            bVar = d;
        }
        return bVar;
    }

    private long e() {
        String str;
        BLEDevice c2 = com.ido.ble.f.a.f.b.e().c();
        if (c2 == null) {
            str = "getBindId:bleDevice is null.";
        } else {
            String str2 = c2.mDeviceAddress;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    return Long.parseLong(str2.replaceAll("[a-zA-Z:]", ""));
                } catch (Exception e) {
                    LogTool.b("DataBaseManager", e.getMessage());
                    return -1L;
                }
            }
            str = "getBindId:macAddress is null.";
        }
        LogTool.b("DataBaseManager", str);
        return -1L;
    }

    private void f() {
        a aVar = new a(e.a(), !TextUtils.isEmpty(CustomConfig.getConfig().getDatabaseName()) ? CustomConfig.getConfig().getDatabaseName() : b, null);
        this.f12225a = (CustomConfig.getConfig().isEncryptedDBData() ? new DaoMaster(aVar.getEncryptedWritableDb(c)) : new DaoMaster(aVar.getWritableDatabase())).newSession();
    }

    private boolean g() {
        return !CustomConfig.getConfig().isSaveDeviceDataToDB();
    }

    public synchronized List<HealthSportItem> A(int i) {
        return m.c().a(c(), i);
    }

    public synchronized List<HealthSportItem> A(int i, int i2) {
        return m.c().b(c(), i, i2);
    }

    public synchronized List<HealthSpO2Item> A(int i, int i2, int i3) {
        return k.c().a(c(), i, i2, i3);
    }

    public synchronized HealthSport B(int i, int i2, int i3) {
        return n.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSwimming> B(int i) {
        return o.c().a(c(), i);
    }

    public synchronized List<HealthSportItem> B(int i, int i2) {
        return m.c().a(c(), i, i2);
    }

    public synchronized List<HealthSwimming> C(int i, int i2) {
        return o.c().b(c(), i, i2);
    }

    public synchronized List<HealthSportItem> C(int i, int i2, int i3) {
        return m.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSwimming> D(int i, int i2) {
        return o.c().a(c(), i, i2);
    }

    public synchronized List<HealthSwimming> D(int i, int i2, int i3) {
        return o.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthActivity> a(int i) {
        return com.ido.ble.f.a.e.a.c().a(c(), i);
    }

    public synchronized List<HealthActivity> a(int i, int i2) {
        return com.ido.ble.f.a.e.a.c().b(c(), i, i2);
    }

    public synchronized List<HealthActivity> a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.e.a.c().a(c(), whereCondition, whereConditionArr);
    }

    public void a() {
        DaoSession daoSession = this.f12225a;
        if (daoSession != null) {
            DaoMaster.dropAllTables(daoSession.getDatabase(), true);
            DaoMaster.createAllTables(this.f12225a.getDatabase(), true);
        }
    }

    public synchronized void a(int i, int i2, int i3) {
        com.ido.ble.f.a.e.a.c().b(c(), i, i2, i3);
    }

    public synchronized void a(int i, int i2, int i3, int i4, int i5, int i6) {
        com.ido.ble.f.a.e.a.c().a(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized void a(HealthActivity healthActivity) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.a.c().b(c(), healthActivity);
    }

    public synchronized void a(HealthBloodPressed healthBloodPressed) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.c.c().b(c(), healthBloodPressed);
    }

    public synchronized void a(HealthBloodPressedItem healthBloodPressedItem) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.b.c().b(c(), healthBloodPressedItem);
    }

    public synchronized void a(HealthHeartRate healthHeartRate) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.e.c().b(c(), healthHeartRate);
    }

    public synchronized void a(HealthHeartRateItem healthHeartRateItem) {
        if (g()) {
            return;
        }
        d.c().b(c(), healthHeartRateItem);
    }

    public synchronized void a(HealthHeartRateSecond healthHeartRateSecond) {
        if (g()) {
            return;
        }
        f.c().b(c(), healthHeartRateSecond);
    }

    public synchronized void a(HealthPressure healthPressure) {
        if (g()) {
            return;
        }
        h.c().b(c(), healthPressure);
    }

    public synchronized void a(HealthPressureItem healthPressureItem) {
        if (g()) {
            return;
        }
        g.c().b(c(), healthPressureItem);
    }

    public synchronized void a(HealthSleep healthSleep) {
        if (g()) {
            return;
        }
        j.c().b(c(), healthSleep);
    }

    public synchronized void a(HealthSleepItem healthSleepItem) {
        if (g()) {
            return;
        }
        i.c().b(c(), healthSleepItem);
    }

    public synchronized void a(HealthSpO2 healthSpO2) {
        if (g()) {
            return;
        }
        l.c().b(c(), healthSpO2);
    }

    public synchronized void a(HealthSpO2Item healthSpO2Item) {
        if (g()) {
            return;
        }
        k.c().b(c(), healthSpO2Item);
    }

    public synchronized void a(HealthSport healthSport) {
        if (g()) {
            return;
        }
        n.c().b(c(), healthSport);
    }

    public synchronized void a(HealthSportItem healthSportItem) {
        if (g()) {
            return;
        }
        m.c().b(c(), healthSportItem);
    }

    public synchronized void a(HealthSwimming healthSwimming) {
        if (g()) {
            return;
        }
        o.c().b(c(), healthSwimming);
    }

    public synchronized void a(Date date, Date date2) {
        i.c().a(c(), date, date2);
    }

    public synchronized void a(List<HealthBloodPressedItem> list) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.b.c().a(c(), list);
    }

    public DaoSession b() {
        return this.f12225a;
    }

    public synchronized List<HealthBloodPressed> b(int i) {
        return com.ido.ble.f.a.e.c.c().a(c(), i, 0);
    }

    public synchronized List<HealthActivity> b(int i, int i2) {
        return com.ido.ble.f.a.e.a.c().a(c(), i, i2);
    }

    public synchronized List<HealthBloodPressed> b(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.e.c.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void b(int i, int i2, int i3) {
        com.ido.ble.f.a.e.c.c().b(c(), i, i2, i3);
    }

    public synchronized void b(int i, int i2, int i3, int i4, int i5, int i6) {
        i.c().a(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized void b(HealthActivity healthActivity) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.a.c().a(c(), healthActivity);
    }

    public synchronized void b(HealthBloodPressed healthBloodPressed) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.c.c().a(c(), healthBloodPressed);
    }

    public synchronized void b(HealthBloodPressedItem healthBloodPressedItem) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.b.c().a(c(), healthBloodPressedItem);
    }

    public synchronized void b(HealthHeartRate healthHeartRate) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.e.c().a(c(), healthHeartRate);
    }

    public synchronized void b(HealthHeartRateItem healthHeartRateItem) {
        if (g()) {
            return;
        }
        d.c().a(c(), healthHeartRateItem);
    }

    public synchronized void b(HealthHeartRateSecond healthHeartRateSecond) {
        if (g()) {
            return;
        }
        f.c().a(c(), healthHeartRateSecond);
    }

    public synchronized void b(HealthPressure healthPressure) {
        if (g()) {
            return;
        }
        h.c().a(c(), healthPressure);
    }

    public synchronized void b(HealthPressureItem healthPressureItem) {
        if (g()) {
            return;
        }
        g.c().a(c(), healthPressureItem);
    }

    public synchronized void b(HealthSleep healthSleep) {
        if (g()) {
            return;
        }
        j.c().a(c(), healthSleep);
    }

    public synchronized void b(HealthSleepItem healthSleepItem) {
        if (g()) {
            return;
        }
        i.c().a(c(), healthSleepItem);
    }

    public synchronized void b(HealthSpO2 healthSpO2) {
        if (g()) {
            return;
        }
        l.c().a(c(), healthSpO2);
    }

    public synchronized void b(HealthSpO2Item healthSpO2Item) {
        if (g()) {
            return;
        }
        k.c().a(c(), healthSpO2Item);
    }

    public synchronized void b(HealthSport healthSport) {
        if (g()) {
            return;
        }
        n.c().a(c(), healthSport);
    }

    public synchronized void b(HealthSportItem healthSportItem) {
        if (g()) {
            return;
        }
        m.c().a(c(), healthSportItem);
    }

    public synchronized void b(HealthSwimming healthSwimming) {
        if (g()) {
            return;
        }
        o.c().a(c(), healthSwimming);
    }

    public synchronized void b(Date date, Date date2) {
        m.c().a(c(), date, date2);
    }

    public synchronized void b(List<HealthHeartRateItem> list) {
        if (g()) {
            return;
        }
        d.c().a(c(), list);
    }

    public synchronized List<HealthBloodPressed> c(int i) {
        return com.ido.ble.f.a.e.c.c().a(c(), i);
    }

    public synchronized List<HealthBloodPressed> c(int i, int i2) {
        return com.ido.ble.f.a.e.c.c().b(c(), i, i2);
    }

    public synchronized List<HealthSleepItem> c(Date date, Date date2) {
        return i.c().b(c(), date, date2);
    }

    public synchronized List<HealthBloodPressedItem> c(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.e.b.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void c(int i, int i2, int i3) {
        com.ido.ble.f.a.e.b.c().b(c(), i, i2, i3);
    }

    public synchronized void c(int i, int i2, int i3, int i4, int i5, int i6) {
        m.c().a(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized void c(List<HealthPressureItem> list) {
        if (g()) {
            return;
        }
        g.c().a(c(), list);
    }

    public synchronized List<HealthBloodPressedItem> d(int i) {
        return com.ido.ble.f.a.e.b.c().a(c(), i, 0);
    }

    public synchronized List<HealthBloodPressed> d(int i, int i2) {
        return com.ido.ble.f.a.e.c.c().a(c(), i, i2);
    }

    public synchronized List<HealthSportItem> d(Date date, Date date2) {
        return m.c().b(c(), date, date2);
    }

    public synchronized List<HealthHeartRate> d(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.e.e.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void d(int i, int i2, int i3) {
        com.ido.ble.f.a.e.e.c().b(c(), i, i2, i3);
    }

    public synchronized void d(int i, int i2, int i3, int i4, int i5, int i6) {
        o.c().a(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized void d(List<HealthSleepItem> list) {
        if (g()) {
            return;
        }
        i.c().a(c(), list);
    }

    public synchronized HealthActivity e(int i, int i2, int i3, int i4, int i5, int i6) {
        return com.ido.ble.f.a.e.a.c().b(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized List<HealthBloodPressedItem> e(int i) {
        return com.ido.ble.f.a.e.b.c().a(c(), i);
    }

    public synchronized List<HealthBloodPressedItem> e(int i, int i2) {
        return com.ido.ble.f.a.e.b.c().b(c(), i, i2);
    }

    public synchronized List<HealthHeartRateItem> e(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return d.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void e(int i, int i2, int i3) {
        d.c().b(c(), i, i2, i3);
    }

    public synchronized void e(List<HealthSpO2Item> list) {
        if (g()) {
            return;
        }
        k.c().a(c(), list);
    }

    public synchronized List<HealthHeartRate> f(int i) {
        return com.ido.ble.f.a.e.e.c().a(c(), i, 0);
    }

    public synchronized List<HealthBloodPressedItem> f(int i, int i2) {
        return com.ido.ble.f.a.e.b.c().a(c(), i, i2);
    }

    public synchronized List<HealthSleepItem> f(int i, int i2, int i3, int i4, int i5, int i6) {
        return i.c().b(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized List<HealthHeartRateSecond> f(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return f.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void f(int i, int i2, int i3) {
        f.c().b(c(), i, i2, i3);
    }

    public synchronized void f(List<HealthSportItem> list) {
        if (g()) {
            return;
        }
        m.c().a(c(), list);
    }

    public synchronized List<HealthHeartRate> g(int i) {
        return com.ido.ble.f.a.e.e.c().a(c(), i);
    }

    public synchronized List<HealthHeartRate> g(int i, int i2) {
        return com.ido.ble.f.a.e.e.c().b(c(), i, i2);
    }

    public synchronized List<HealthSportItem> g(int i, int i2, int i3, int i4, int i5, int i6) {
        return m.c().b(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized List<HealthPressure> g(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return h.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void g(int i, int i2, int i3) {
        h.c().b(c(), i, i2, i3);
    }

    public synchronized void g(List<HealthBloodPressedItem> list) {
        if (g()) {
            return;
        }
        com.ido.ble.f.a.e.b.c().b(c(), list);
    }

    public synchronized HealthSwimming h(int i, int i2, int i3, int i4, int i5, int i6) {
        return o.c().b(c(), i, i2, i3, i4, i5, i6);
    }

    public synchronized List<HealthHeartRateItem> h(int i) {
        return d.c().a(c(), i, 0);
    }

    public synchronized List<HealthHeartRate> h(int i, int i2) {
        return com.ido.ble.f.a.e.e.c().a(c(), i, i2);
    }

    public synchronized List<HealthPressureItem> h(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return g.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void h(int i, int i2, int i3) {
        g.c().b(c(), i, i2, i3);
    }

    public synchronized void h(List<HealthHeartRateItem> list) {
        if (g()) {
            return;
        }
        d.c().b(c(), list);
    }

    public synchronized List<HealthHeartRateItem> i(int i) {
        return d.c().a(c(), i);
    }

    public synchronized List<HealthHeartRateItem> i(int i, int i2) {
        return d.c().b(c(), i, i2);
    }

    public synchronized List<HealthSleep> i(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return j.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void i(int i, int i2, int i3) {
        j.c().b(c(), i, i2, i3);
    }

    public synchronized void i(List<HealthPressureItem> list) {
        if (g()) {
            return;
        }
        g.c().b(c(), list);
    }

    public synchronized List<HealthHeartRateSecond> j(int i) {
        return f.c().a(c(), i, 0);
    }

    public synchronized List<HealthHeartRateItem> j(int i, int i2) {
        return d.c().a(c(), i, i2);
    }

    public synchronized List<HealthSleepItem> j(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return i.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void j(int i, int i2, int i3) {
        i.c().b(c(), i, i2, i3);
    }

    public synchronized void j(List<HealthSleepItem> list) {
        if (g()) {
            return;
        }
        i.c().b(c(), list);
    }

    public synchronized List<HealthHeartRateSecond> k(int i) {
        return f.c().a(c(), i);
    }

    public synchronized List<HealthHeartRateSecond> k(int i, int i2) {
        return f.c().b(c(), i, i2);
    }

    public synchronized List<HealthSpO2> k(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return l.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void k(int i, int i2, int i3) {
        l.c().b(c(), i, i2, i3);
    }

    public synchronized void k(List<HealthSpO2Item> list) {
        if (g()) {
            return;
        }
        k.c().b(c(), list);
    }

    public synchronized List<HealthPressure> l(int i) {
        return h.c().a(c(), i, 0);
    }

    public synchronized List<HealthHeartRateSecond> l(int i, int i2) {
        return f.c().a(c(), i, i2);
    }

    public synchronized List<HealthSpO2Item> l(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return k.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void l(int i, int i2, int i3) {
        k.c().b(c(), i, i2, i3);
    }

    public synchronized void l(List<HealthSportItem> list) {
        if (g()) {
            return;
        }
        m.c().b(c(), list);
    }

    public synchronized List<HealthPressure> m(int i) {
        return h.c().a(c(), i);
    }

    public synchronized List<HealthPressure> m(int i, int i2) {
        return h.c().b(c(), i, i2);
    }

    public synchronized List<HealthSport> m(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return n.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void m(int i, int i2, int i3) {
        n.c().b(c(), i, i2, i3);
    }

    public synchronized List<HealthPressureItem> n(int i) {
        return g.c().a(c(), i, 0);
    }

    public synchronized List<HealthPressure> n(int i, int i2) {
        return h.c().a(c(), i, i2);
    }

    public synchronized List<HealthSportItem> n(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return m.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void n(int i, int i2, int i3) {
        m.c().b(c(), i, i2, i3);
    }

    public synchronized List<HealthPressureItem> o(int i) {
        return g.c().a(c(), i);
    }

    public synchronized List<HealthPressureItem> o(int i, int i2) {
        return g.c().b(c(), i, i2);
    }

    public synchronized List<HealthSwimming> o(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return o.c().a(c(), whereCondition, whereConditionArr);
    }

    public synchronized void o(int i, int i2, int i3) {
        o.c().b(c(), i, i2, i3);
    }

    public synchronized List<HealthSleep> p(int i) {
        return j.c().a(c(), i, 0);
    }

    public synchronized List<HealthPressureItem> p(int i, int i2) {
        return g.c().a(c(), i, i2);
    }

    public synchronized List<HealthActivity> p(int i, int i2, int i3) {
        return com.ido.ble.f.a.e.a.c().a(c(), i, i2, i3);
    }

    public synchronized HealthBloodPressed q(int i, int i2, int i3) {
        return com.ido.ble.f.a.e.c.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSleep> q(int i) {
        return j.c().a(c(), i);
    }

    public synchronized List<HealthSleep> q(int i, int i2) {
        return j.c().b(c(), i, i2);
    }

    public synchronized List<HealthSleepItem> r(int i) {
        return i.c().a(c(), i, 0);
    }

    public synchronized List<HealthSleep> r(int i, int i2) {
        return j.c().a(c(), i, i2);
    }

    public synchronized List<HealthBloodPressedItem> r(int i, int i2, int i3) {
        return com.ido.ble.f.a.e.b.c().a(c(), i, i2, i3);
    }

    public synchronized HealthHeartRate s(int i, int i2, int i3) {
        return com.ido.ble.f.a.e.e.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSleepItem> s(int i) {
        return i.c().a(c(), i);
    }

    public synchronized List<HealthSleepItem> s(int i, int i2) {
        return i.c().b(c(), i, i2);
    }

    public synchronized List<HealthSpO2> t(int i) {
        return l.c().a(c(), i, 0);
    }

    public synchronized List<HealthSleepItem> t(int i, int i2) {
        return i.c().a(c(), i, i2);
    }

    public synchronized List<HealthHeartRateItem> t(int i, int i2, int i3) {
        return d.c().a(c(), i, i2, i3);
    }

    public synchronized HealthHeartRateSecond u(int i, int i2, int i3) {
        return f.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSpO2> u(int i) {
        return l.c().a(c(), i);
    }

    public synchronized List<HealthSpO2> u(int i, int i2) {
        return l.c().b(c(), i, i2);
    }

    public synchronized HealthPressure v(int i, int i2, int i3) {
        return h.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSpO2Item> v(int i) {
        return k.c().a(c(), i, 0);
    }

    public synchronized List<HealthSpO2> v(int i, int i2) {
        return l.c().a(c(), i, i2);
    }

    public synchronized List<HealthSpO2Item> w(int i) {
        return k.c().a(c(), i);
    }

    public synchronized List<HealthSpO2Item> w(int i, int i2) {
        return k.c().b(c(), i, i2);
    }

    public synchronized List<HealthPressureItem> w(int i, int i2, int i3) {
        return g.c().a(c(), i, i2, i3);
    }

    public synchronized HealthSleep x(int i, int i2, int i3) {
        return j.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSport> x(int i) {
        return n.c().a(c(), i, 0);
    }

    public synchronized List<HealthSpO2Item> x(int i, int i2) {
        return k.c().a(c(), i, i2);
    }

    public synchronized List<HealthSport> y(int i) {
        return n.c().a(c(), i);
    }

    public synchronized List<HealthSport> y(int i, int i2) {
        return n.c().b(c(), i, i2);
    }

    public synchronized List<HealthSleepItem> y(int i, int i2, int i3) {
        return i.c().a(c(), i, i2, i3);
    }

    public synchronized HealthSpO2 z(int i, int i2, int i3) {
        return l.c().a(c(), i, i2, i3);
    }

    public synchronized List<HealthSportItem> z(int i) {
        return m.c().a(c(), i, 0);
    }

    public synchronized List<HealthSport> z(int i, int i2) {
        return n.c().a(c(), i, i2);
    }
}
