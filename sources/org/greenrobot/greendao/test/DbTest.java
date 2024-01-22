package org.greenrobot.greendao.test;

import android.app.Application;
import android.app.Instrumentation;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import java.util.Random;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.DbUtils;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;
/* loaded from: classes13.dex */
public abstract class DbTest extends AndroidTestCase {
    public static final String DB_NAME = "greendao-unittest-db.temp";

    /* renamed from: a  reason: collision with root package name */
    public Application f15494a;
    public Database db;
    public final boolean inMemory;
    public final Random random;

    public DbTest() {
        this(true);
    }

    public <T extends Application> T createApplication(Class<T> cls) {
        AndroidTestCase.assertNull("Application already created", this.f15494a);
        try {
            T t = (T) Instrumentation.newApplication(cls, getContext());
            t.onCreate();
            this.f15494a = t;
            return t;
        } catch (Exception e) {
            throw new RuntimeException("Could not create application " + cls, e);
        }
    }

    public Database createDatabase() {
        SQLiteDatabase openOrCreateDatabase;
        if (this.inMemory) {
            openOrCreateDatabase = SQLiteDatabase.create(null);
        } else {
            getContext().deleteDatabase(DB_NAME);
            openOrCreateDatabase = getContext().openOrCreateDatabase(DB_NAME, 0, null);
        }
        return new StandardDatabase(openOrCreateDatabase);
    }

    public <T extends Application> T getApplication() {
        AndroidTestCase.assertNotNull("Application not yet created", this.f15494a);
        return (T) this.f15494a;
    }

    public void logTableDump(String str) {
        Database database = this.db;
        if (database instanceof StandardDatabase) {
            DbUtils.logTableDump(((StandardDatabase) database).getSQLiteDatabase(), str);
            return;
        }
        DaoLog.w("Table dump unsupported for " + this.db);
    }

    public void setUp() throws Exception {
        super.setUp();
        this.db = createDatabase();
    }

    public void tearDown() throws Exception {
        if (this.f15494a != null) {
            terminateApplication();
        }
        this.db.close();
        if (!this.inMemory) {
            getContext().deleteDatabase(DB_NAME);
        }
        super.tearDown();
    }

    public void terminateApplication() {
        AndroidTestCase.assertNotNull("Application not yet created", this.f15494a);
        this.f15494a.onTerminate();
        this.f15494a = null;
    }

    public DbTest(boolean z) {
        this.inMemory = z;
        this.random = new Random();
    }
}
