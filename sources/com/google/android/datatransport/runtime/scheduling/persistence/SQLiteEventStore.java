package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.paho.android.service.MqttServiceConstants;
@Singleton
@WorkerThread
/* loaded from: classes6.dex */
public class SQLiteEventStore implements EventStore, SynchronizationGuard {
    public static final Encoding l = Encoding.of("proto");
    public final SchemaManager h;
    public final Clock i;
    public final Clock j;
    public final com.google.android.datatransport.runtime.scheduling.persistence.c k;

    /* loaded from: classes6.dex */
    public interface b<T, U> {
        U apply(T t);
    }

    /* loaded from: classes6.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f8134a;
        public final String b;

        public c(String str, String str2) {
            this.f8134a = str;
            this.b = str2;
        }
    }

    /* loaded from: classes6.dex */
    public interface d<T> {
        T a();
    }

    @Inject
    public SQLiteEventStore(@WallTime Clock clock, @Monotonic Clock clock2, com.google.android.datatransport.runtime.scheduling.persistence.c cVar, SchemaManager schemaManager) {
        this.h = schemaManager;
        this.i = clock;
        this.j = clock2;
        this.k = cVar;
    }

    public static /* synthetic */ Object A(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("events", null, new String[0]);
        sQLiteDatabase.delete("transport_contexts", null, new String[0]);
        return null;
    }

    public static /* synthetic */ Object B(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        return null;
    }

    public static /* synthetic */ Object C(Throwable th) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", th);
    }

    public static /* synthetic */ SQLiteDatabase D(Throwable th) {
        throw new SynchronizationException("Timed out while trying to open db.", th);
    }

    public static /* synthetic */ Long E(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return 0L;
    }

    public static /* synthetic */ Long F(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean G(TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        Long v = v(sQLiteDatabase, transportContext);
        if (v == null) {
            return Boolean.FALSE;
        }
        return (Boolean) X(t().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{v.toString()}), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.g
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                return Boolean.valueOf(((Cursor) obj).moveToNext());
            }
        });
    }

    public static /* synthetic */ List H(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(TransportContext.builder().setBackendName(cursor.getString(1)).setPriority(PriorityMapping.valueOf(cursor.getInt(2))).setExtras(S(cursor.getString(3))).build());
        }
        return arrayList;
    }

    public static /* synthetic */ List I(SQLiteDatabase sQLiteDatabase) {
        return (List) X(sQLiteDatabase.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.v
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                List H;
                H = SQLiteEventStore.H((Cursor) obj);
                return H;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List J(TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        List<PersistedEvent> Q = Q(sQLiteDatabase, transportContext);
        return y(Q, R(sQLiteDatabase, Q));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object K(List list, TransportContext transportContext, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            boolean z = cursor.getInt(7) != 0;
            EventInternal.Builder uptimeMillis = EventInternal.builder().setTransportName(cursor.getString(1)).setEventMillis(cursor.getLong(2)).setUptimeMillis(cursor.getLong(3));
            if (z) {
                uptimeMillis.setEncodedPayload(new EncodedPayload(V(cursor.getString(4)), cursor.getBlob(5)));
            } else {
                uptimeMillis.setEncodedPayload(new EncodedPayload(V(cursor.getString(4)), T(j)));
            }
            if (!cursor.isNull(6)) {
                uptimeMillis.setCode(Integer.valueOf(cursor.getInt(6)));
            }
            list.add(PersistedEvent.create(j, transportContext, uptimeMillis.build()));
        }
        return null;
    }

    public static /* synthetic */ Object L(Map map, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            Set set = (Set) map.get(Long.valueOf(j));
            if (set == null) {
                set = new HashSet();
                map.put(Long.valueOf(j), set);
            }
            set.add(new c(cursor.getString(1), cursor.getString(2)));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Long M(TransportContext transportContext, EventInternal eventInternal, SQLiteDatabase sQLiteDatabase) {
        if (x()) {
            return -1L;
        }
        long s = s(sQLiteDatabase, transportContext);
        int e = this.k.e();
        byte[] bytes = eventInternal.getEncodedPayload().getBytes();
        boolean z = bytes.length <= e;
        ContentValues contentValues = new ContentValues();
        contentValues.put("context_id", Long.valueOf(s));
        contentValues.put("transport_name", eventInternal.getTransportName());
        contentValues.put("timestamp_ms", Long.valueOf(eventInternal.getEventMillis()));
        contentValues.put("uptime_ms", Long.valueOf(eventInternal.getUptimeMillis()));
        contentValues.put("payload_encoding", eventInternal.getEncodedPayload().getEncoding().getName());
        contentValues.put("code", eventInternal.getCode());
        contentValues.put("num_attempts", (Integer) 0);
        contentValues.put("inline", Boolean.valueOf(z));
        contentValues.put(MqttServiceConstants.PAYLOAD, z ? bytes : new byte[0]);
        long insert = sQLiteDatabase.insert("events", null, contentValues);
        if (!z) {
            int ceil = (int) Math.ceil(bytes.length / e);
            for (int i = 1; i <= ceil; i++) {
                byte[] copyOfRange = Arrays.copyOfRange(bytes, (i - 1) * e, Math.min(i * e, bytes.length));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(insert));
                contentValues2.put("sequence_num", Integer.valueOf(i));
                contentValues2.put("bytes", copyOfRange);
                sQLiteDatabase.insert("event_payloads", null, contentValues2);
            }
        }
        for (Map.Entry<String, String> entry : eventInternal.getMetadata().entrySet()) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("event_id", Long.valueOf(insert));
            contentValues3.put(AppMeasurementSdk.ConditionalUserProperty.NAME, entry.getKey());
            contentValues3.put("value", entry.getValue());
            sQLiteDatabase.insert("event_metadata", null, contentValues3);
        }
        return Long.valueOf(insert);
    }

    public static /* synthetic */ byte[] N(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(0);
            arrayList.add(blob);
            i += blob.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            byte[] bArr2 = (byte[]) arrayList.get(i3);
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += bArr2.length;
        }
        return bArr;
    }

    public static /* synthetic */ Object O(String str, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.compileStatement(str).execute();
        sQLiteDatabase.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    public static /* synthetic */ Object P(long j, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("next_request_ms", Long.valueOf(j));
        if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}) < 1) {
            contentValues.put("backend_name", transportContext.getBackendName());
            contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
            sQLiteDatabase.insert("transport_contexts", null, contentValues);
        }
        return null;
    }

    public static byte[] S(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    public static Encoding V(@Nullable String str) {
        if (str == null) {
            return l;
        }
        return Encoding.of(str);
    }

    public static String W(Iterable<PersistedEvent> iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator<PersistedEvent> it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getId());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        return sb.toString();
    }

    @VisibleForTesting
    public static <T> T X(Cursor cursor, b<Cursor, T> bVar) {
        try {
            return bVar.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    public static /* synthetic */ Integer z(long j, SQLiteDatabase sQLiteDatabase) {
        return Integer.valueOf(sQLiteDatabase.delete("events", "timestamp_ms < ?", new String[]{String.valueOf(j)}));
    }

    public final List<PersistedEvent> Q(SQLiteDatabase sQLiteDatabase, final TransportContext transportContext) {
        final ArrayList arrayList = new ArrayList();
        Long v = v(sQLiteDatabase, transportContext);
        if (v == null) {
            return arrayList;
        }
        X(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", MqttServiceConstants.PAYLOAD, "code", "inline"}, "context_id = ?", new String[]{v.toString()}, null, null, null, String.valueOf(this.k.d())), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.r
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Object K;
                K = SQLiteEventStore.this.K(arrayList, transportContext, (Cursor) obj);
                return K;
            }
        });
        return arrayList;
    }

    public final Map<Long, Set<c>> R(SQLiteDatabase sQLiteDatabase, List<PersistedEvent> list) {
        final HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder("event_id IN (");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId());
            if (i < list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        X(sQLiteDatabase.query("event_metadata", new String[]{"event_id", AppMeasurementSdk.ConditionalUserProperty.NAME, "value"}, sb.toString(), null, null, null, null), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.t
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Object L;
                L = SQLiteEventStore.L(hashMap, (Cursor) obj);
                return L;
            }
        });
        return hashMap;
    }

    public final byte[] T(long j) {
        return (byte[]) X(t().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(j)}, null, null, "sequence_num"), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.u
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                byte[] N;
                N = SQLiteEventStore.N((Cursor) obj);
                return N;
            }
        });
    }

    public final <T> T U(d<T> dVar, b<Throwable, T> bVar) {
        long time = this.j.getTime();
        while (true) {
            try {
                return dVar.a();
            } catch (SQLiteDatabaseLockedException e) {
                if (this.j.getTime() >= this.k.b() + time) {
                    return bVar.apply(e);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int cleanUp() {
        final long time = this.i.getTime() - this.k.c();
        return ((Integer) w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.d
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Integer z;
                z = SQLiteEventStore.z(time, (SQLiteDatabase) obj);
                return z;
            }
        })).intValue();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public void clearDb() {
        w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.i
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Object A;
                A = SQLiteEventStore.A((SQLiteDatabase) obj);
                return A;
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.h.close();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long getNextCallTime(TransportContext transportContext) {
        return ((Long) X(t().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.f
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Long E;
                E = SQLiteEventStore.E((Cursor) obj);
                return E;
            }
        })).longValue();
    }

    public final long getPageSize() {
        return t().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean hasPendingEventsFor(final TransportContext transportContext) {
        return ((Boolean) w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.o
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Boolean G;
                G = SQLiteEventStore.this.G(transportContext, (SQLiteDatabase) obj);
                return G;
            }
        })).booleanValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<TransportContext> loadActiveContexts() {
        return (Iterable) w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.h
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                List I;
                I = SQLiteEventStore.I((SQLiteDatabase) obj);
                return I;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<PersistedEvent> loadBatch(final TransportContext transportContext) {
        return (Iterable) w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.p
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                List J;
                J = SQLiteEventStore.this.J(transportContext, (SQLiteDatabase) obj);
                return J;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    @Nullable
    public PersistedEvent persist(final TransportContext transportContext, final EventInternal eventInternal) {
        Logging.d("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", transportContext.getPriority(), eventInternal.getTransportName(), transportContext.getBackendName());
        long longValue = ((Long) w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.q
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Long M;
                M = SQLiteEventStore.this.M(transportContext, eventInternal, (SQLiteDatabase) obj);
                return M;
            }
        })).longValue();
        if (longValue < 1) {
            return null;
        }
        return PersistedEvent.create(longValue, transportContext, eventInternal);
    }

    public final void r(final SQLiteDatabase sQLiteDatabase) {
        U(new d() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.l
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.d
            public final Object a() {
                Object B;
                B = SQLiteEventStore.B(sQLiteDatabase);
                return B;
            }
        }, new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.k
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Object C;
                C = SQLiteEventStore.C((Throwable) obj);
                return C;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordFailure(Iterable<PersistedEvent> iterable) {
        if (iterable.iterator().hasNext()) {
            final String str = "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + W(iterable);
            w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.s
                @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
                public final Object apply(Object obj) {
                    Object O;
                    O = SQLiteEventStore.O(str, (SQLiteDatabase) obj);
                    return O;
                }
            });
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordNextCallTime(final TransportContext transportContext, final long j) {
        w(new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.n
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Object P;
                P = SQLiteEventStore.P(j, transportContext, (SQLiteDatabase) obj);
                return P;
            }
        });
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordSuccess(Iterable<PersistedEvent> iterable) {
        if (iterable.iterator().hasNext()) {
            t().compileStatement("DELETE FROM events WHERE _id in " + W(iterable)).execute();
        }
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public <T> T runCriticalSection(SynchronizationGuard.CriticalSection<T> criticalSection) {
        SQLiteDatabase t = t();
        r(t);
        try {
            T execute = criticalSection.execute();
            t.setTransactionSuccessful();
            return execute;
        } finally {
            t.endTransaction();
        }
    }

    public final long s(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        Long v = v(sQLiteDatabase, transportContext);
        if (v != null) {
            return v.longValue();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("backend_name", transportContext.getBackendName());
        contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        contentValues.put("next_request_ms", (Integer) 0);
        if (transportContext.getExtras() != null) {
            contentValues.put(NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE, Base64.encodeToString(transportContext.getExtras(), 0));
        }
        return sQLiteDatabase.insert("transport_contexts", null, contentValues);
    }

    @VisibleForTesting
    public SQLiteDatabase t() {
        final SchemaManager schemaManager = this.h;
        Objects.requireNonNull(schemaManager);
        return (SQLiteDatabase) U(new d() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.m
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.d
            public final Object a() {
                return SchemaManager.this.getWritableDatabase();
            }
        }, new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.j
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                SQLiteDatabase D;
                D = SQLiteEventStore.D((Throwable) obj);
                return D;
            }
        });
    }

    public final long u() {
        return t().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    @Nullable
    public final Long v(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))));
        if (transportContext.getExtras() != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(transportContext.getExtras(), 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) X(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null), new b() { // from class: com.google.android.datatransport.runtime.scheduling.persistence.e
            @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.b
            public final Object apply(Object obj) {
                Long F;
                F = SQLiteEventStore.F((Cursor) obj);
                return F;
            }
        });
    }

    @VisibleForTesting
    public <T> T w(b<SQLiteDatabase, T> bVar) {
        SQLiteDatabase t = t();
        t.beginTransaction();
        try {
            T apply = bVar.apply(t);
            t.setTransactionSuccessful();
            return apply;
        } finally {
            t.endTransaction();
        }
    }

    public final boolean x() {
        return u() * getPageSize() >= this.k.f();
    }

    public final List<PersistedEvent> y(List<PersistedEvent> list, Map<Long, Set<c>> map) {
        ListIterator<PersistedEvent> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            PersistedEvent next = listIterator.next();
            if (map.containsKey(Long.valueOf(next.getId()))) {
                EventInternal.Builder builder = next.getEvent().toBuilder();
                for (c cVar : map.get(Long.valueOf(next.getId()))) {
                    builder.addMetadata(cVar.f8134a, cVar.b);
                }
                listIterator.set(PersistedEvent.create(next.getId(), next.getTransportContext(), builder.build()));
            }
        }
        return list;
    }
}
