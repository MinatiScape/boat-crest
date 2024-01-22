package com.mappls.sdk.plugins.places.autocomplete.data.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class d implements com.mappls.sdk.plugins.places.autocomplete.data.dao.a {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f13133a;
    public final EntityInsertionAdapter<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes10.dex */
    public class a implements Callable<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public a(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        public final List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> call() {
            Cursor query = DBUtil.query(d.this.f13133a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "placeId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "eLocation");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "place_name");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "place_address");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "alternate_name");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    arrayList.add(new com.mappls.sdk.plugins.places.autocomplete.data.entity.a(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), com.mappls.sdk.plugins.places.autocomplete.data.converter.a.a(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2)), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), (query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3))).longValue()));
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public final void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Callable<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public b(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        public final List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> call() {
            Cursor query = DBUtil.query(d.this.f13133a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "placeId");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "eLocation");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "place_name");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "place_address");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "alternate_name");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    arrayList.add(new com.mappls.sdk.plugins.places.autocomplete.data.entity.a(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), com.mappls.sdk.plugins.places.autocomplete.data.converter.a.a(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2)), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6), (query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3))).longValue()));
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public final void finalize() {
            this.h.release();
        }
    }

    public d(SearchHistoryDatabase searchHistoryDatabase) {
        this.f13133a = searchHistoryDatabase;
        this.b = new com.mappls.sdk.plugins.places.autocomplete.data.dao.b(searchHistoryDatabase);
        this.c = new c(searchHistoryDatabase);
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.data.dao.a
    public final LiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> a(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM searchhistory WHERE place_name LIKE '%' || ? || '%' OR place_address LIKE '%' || ? || '%' OR placeId LIKE '%' || ? || '%' order by timestamp desc", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        return this.f13133a.getInvalidationTracker().createLiveData(new String[]{"searchhistory"}, false, new b(acquire));
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.data.dao.a
    public final void a() {
        this.f13133a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        this.f13133a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f13133a.setTransactionSuccessful();
        } finally {
            this.f13133a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.data.dao.a
    public final void a(com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar) {
        this.f13133a.assertNotSuspendingTransaction();
        this.f13133a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>) aVar);
            this.f13133a.setTransactionSuccessful();
        } finally {
            this.f13133a.endTransaction();
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.data.dao.a
    public final LiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> getAll() {
        return this.f13133a.getInvalidationTracker().createLiveData(new String[]{"searchhistory"}, false, new a(RoomSQLiteQuery.acquire("SELECT * FROM searchhistory order by timestamp desc", 0)));
    }
}
