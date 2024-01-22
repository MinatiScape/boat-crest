package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    @VisibleForTesting
    public static final TreeMap<Integer, RoomSQLiteQuery> p = new TreeMap<>();
    public volatile String h;
    @VisibleForTesting
    public final long[] i;
    @VisibleForTesting
    public final double[] j;
    @VisibleForTesting
    public final String[] k;
    @VisibleForTesting
    public final byte[][] l;
    public final int[] m;
    @VisibleForTesting
    public final int n;
    @VisibleForTesting
    public int o;

    /* loaded from: classes.dex */
    public static class a implements SupportSQLiteProgram {
        public a() {
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindBlob(int i, byte[] bArr) {
            RoomSQLiteQuery.this.bindBlob(i, bArr);
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindDouble(int i, double d) {
            RoomSQLiteQuery.this.bindDouble(i, d);
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindLong(int i, long j) {
            RoomSQLiteQuery.this.bindLong(i, j);
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindNull(int i) {
            RoomSQLiteQuery.this.bindNull(i);
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void bindString(int i, String str) {
            RoomSQLiteQuery.this.bindString(i, str);
        }

        @Override // androidx.sqlite.db.SupportSQLiteProgram
        public void clearBindings() {
            RoomSQLiteQuery.this.clearBindings();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    public RoomSQLiteQuery(int i) {
        this.n = i;
        int i2 = i + 1;
        this.m = new int[i2];
        this.i = new long[i2];
        this.j = new double[i2];
        this.k = new String[i2];
        this.l = new byte[i2];
    }

    public static RoomSQLiteQuery acquire(String str, int i) {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = p;
        synchronized (treeMap) {
            Map.Entry<Integer, RoomSQLiteQuery> ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                treeMap.remove(ceilingEntry.getKey());
                RoomSQLiteQuery value = ceilingEntry.getValue();
                value.a(str, i);
                return value;
            }
            RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(i);
            roomSQLiteQuery.a(str, i);
            return roomSQLiteQuery;
        }
    }

    public static void b() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = p;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator<Integer> it = treeMap.descendingKeySet().iterator();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = i;
        }
    }

    public static RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery) {
        RoomSQLiteQuery acquire = acquire(supportSQLiteQuery.getSql(), supportSQLiteQuery.getArgCount());
        supportSQLiteQuery.bindTo(new a());
        return acquire;
    }

    public void a(String str, int i) {
        this.h = str;
        this.o = i;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int i, byte[] bArr) {
        this.m[i] = 5;
        this.l[i] = bArr;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int i, double d) {
        this.m[i] = 3;
        this.j[i] = d;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int i, long j) {
        this.m[i] = 2;
        this.i[i] = j;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int i) {
        this.m[i] = 1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int i, String str) {
        this.m[i] = 4;
        this.k[i] = str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        for (int i = 1; i <= this.o; i++) {
            int i2 = this.m[i];
            if (i2 == 1) {
                supportSQLiteProgram.bindNull(i);
            } else if (i2 == 2) {
                supportSQLiteProgram.bindLong(i, this.i[i]);
            } else if (i2 == 3) {
                supportSQLiteProgram.bindDouble(i, this.j[i]);
            } else if (i2 == 4) {
                supportSQLiteProgram.bindString(i, this.k[i]);
            } else if (i2 == 5) {
                supportSQLiteProgram.bindBlob(i, this.l[i]);
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void clearBindings() {
        Arrays.fill(this.m, 1);
        Arrays.fill(this.k, (Object) null);
        Arrays.fill(this.l, (Object) null);
        this.h = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public void copyArgumentsFrom(RoomSQLiteQuery roomSQLiteQuery) {
        int argCount = roomSQLiteQuery.getArgCount() + 1;
        System.arraycopy(roomSQLiteQuery.m, 0, this.m, 0, argCount);
        System.arraycopy(roomSQLiteQuery.i, 0, this.i, 0, argCount);
        System.arraycopy(roomSQLiteQuery.k, 0, this.k, 0, argCount);
        System.arraycopy(roomSQLiteQuery.l, 0, this.l, 0, argCount);
        System.arraycopy(roomSQLiteQuery.j, 0, this.j, 0, argCount);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        return this.o;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.h;
    }

    public void release() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = p;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.n), this);
            b();
        }
    }
}
