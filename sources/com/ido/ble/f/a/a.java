package com.ido.ble.f.a;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12224a = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";

    private String a(Class<?> cls) {
        if (cls.equals(String.class)) {
            return "TEXT";
        }
        if (cls.equals(Long.class) || cls.equals(Integer.class) || cls.equals(Long.TYPE) || cls.equals(Integer.TYPE)) {
            return "INTEGER  DEFAULT 0";
        }
        if (cls.equals(Boolean.class)) {
            return "BOOLEAN";
        }
        throw new Exception(f12224a.concat(" - Class: ").concat(cls.toString()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0043, code lost:
        if (r1 == null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0046, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<java.lang.String> a(org.greenrobot.greendao.database.Database r4, java.lang.String r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r2.<init>()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r2.append(r5)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r3 = " limit 1"
            r2.append(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            android.database.Cursor r1 = r4.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            if (r1 == 0) goto L30
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String[] r2 = r1.getColumnNames()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r0 = r4
        L30:
            if (r1 == 0) goto L46
        L32:
            r1.close()
            goto L46
        L36:
            r4 = move-exception
            goto L47
        L38:
            r4 = move-exception
            java.lang.String r2 = r4.getMessage()     // Catch: java.lang.Throwable -> L36
            android.util.Log.v(r5, r2, r4)     // Catch: java.lang.Throwable -> L36
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L46
            goto L32
        L46:
            return r0
        L47:
            if (r1 == 0) goto L4c
            r1.close()
        L4c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.f.a.a.a(org.greenrobot.greendao.database.Database, java.lang.String):java.util.List");
    }

    private static void a(Database database, String str, boolean z, @NonNull Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr.length < 1) {
            return;
        }
        try {
            for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
                cls.getDeclaredMethod(str, Database.class, Boolean.TYPE).invoke(null, database, Boolean.valueOf(z));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    private void a(Database database, boolean z, Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr != null) {
            a(database, "createTable", z, clsArr);
        }
    }

    private void b(Database database, boolean z, Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr != null) {
            a(database, "dropTable", z, clsArr);
        }
    }

    private void c(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String concat = str.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ");
            sb.append(concat);
            sb.append(" (");
            String str2 = "";
            int i = 0;
            while (true) {
                Property[] propertyArr = daoConfig.properties;
                if (i >= propertyArr.length) {
                    break;
                }
                String str3 = propertyArr[i].columnName;
                if (a(database, str).contains(str3)) {
                    arrayList.add(str3);
                    String str4 = null;
                    try {
                        try {
                            str4 = a(daoConfig.properties[i].type);
                        } catch (Exception unused) {
                        }
                    } catch (Exception unused2) {
                    }
                    sb.append(str2);
                    sb.append(str3);
                    sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    sb.append(str4);
                    if (daoConfig.properties[i].primaryKey) {
                        sb.append(" PRIMARY KEY");
                    }
                    str2 = Constants.SEPARATOR_COMMA;
                }
                i++;
            }
            sb.append(");");
            if (arrayList.size() == 0) {
                return;
            }
            database.execSQL(sb.toString());
            database.execSQL("INSERT INTO " + concat + " (" + TextUtils.join(Constants.SEPARATOR_COMMA, arrayList) + ") SELECT " + TextUtils.join(Constants.SEPARATOR_COMMA, arrayList) + " FROM " + str + ";");
        }
    }

    private void d(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String concat = str.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                Property[] propertyArr = daoConfig.properties;
                if (i >= propertyArr.length) {
                    break;
                }
                String str2 = propertyArr[i].columnName;
                if (a(database, concat).contains(str2)) {
                    arrayList.add(str2);
                } else {
                    StringBuilder sb = new StringBuilder();
                    try {
                        sb.append("ALTER TABLE " + concat + " ADD COLUMN " + str2 + HexStringBuilder.DEFAULT_SEPARATOR + a(daoConfig.properties[i].type));
                        database.execSQL(sb.toString());
                        arrayList.add(str2);
                    } catch (Exception e) {
                        LogTool.b("DBMigHelper", e.getMessage());
                    }
                }
                i++;
            }
            if (arrayList.size() == 0) {
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("DROP TABLE IF EXISTS ");
            sb2.append(concat);
            sb2.append(";");
            database.execSQL("INSERT INTO " + str + " (" + TextUtils.join(Constants.SEPARATOR_COMMA, arrayList) + ") SELECT " + TextUtils.join(Constants.SEPARATOR_COMMA, arrayList) + " FROM " + concat + ";");
            database.execSQL(sb2.toString());
        }
    }

    public void a(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        a(database, true, clsArr);
    }

    public void b(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        c(database, clsArr);
        b(database, true, clsArr);
        a(database, false, clsArr);
        d(database, clsArr);
    }
}
