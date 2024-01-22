package com.mappls.sdk.plugins.places.autocomplete.data.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
/* loaded from: classes10.dex */
public final class b extends EntityInsertionAdapter<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> {
    public b(SearchHistoryDatabase searchHistoryDatabase) {
        super(searchHistoryDatabase);
    }

    @Override // androidx.room.EntityInsertionAdapter
    public final void bind(SupportSQLiteStatement supportSQLiteStatement, com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar) {
        com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar2 = aVar;
        if (aVar2.d() == null) {
            supportSQLiteStatement.bindNull(1);
        } else {
            supportSQLiteStatement.bindString(1, aVar2.d());
        }
        String a2 = com.mappls.sdk.plugins.places.autocomplete.data.converter.a.a(aVar2.b());
        if (a2 == null) {
            supportSQLiteStatement.bindNull(2);
        } else {
            supportSQLiteStatement.bindString(2, a2);
        }
        if (aVar2.f() == null) {
            supportSQLiteStatement.bindNull(3);
        } else {
            supportSQLiteStatement.bindLong(3, aVar2.f().longValue());
        }
        if (aVar2.e() == null) {
            supportSQLiteStatement.bindNull(4);
        } else {
            supportSQLiteStatement.bindString(4, aVar2.e());
        }
        if (aVar2.c() == null) {
            supportSQLiteStatement.bindNull(5);
        } else {
            supportSQLiteStatement.bindString(5, aVar2.c());
        }
        if (aVar2.a() == null) {
            supportSQLiteStatement.bindNull(6);
        } else {
            supportSQLiteStatement.bindString(6, aVar2.a());
        }
    }

    @Override // androidx.room.SharedSQLiteStatement
    public final String createQuery() {
        return "INSERT OR REPLACE INTO `searchhistory` (`placeId`,`eLocation`,`timestamp`,`place_name`,`place_address`,`alternate_name`) VALUES (?,?,?,?,?,?)";
    }
}
