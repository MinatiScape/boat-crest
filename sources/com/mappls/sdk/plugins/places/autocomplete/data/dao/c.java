package com.mappls.sdk.plugins.places.autocomplete.data.dao;

import androidx.room.SharedSQLiteStatement;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
/* loaded from: classes10.dex */
public final class c extends SharedSQLiteStatement {
    public c(SearchHistoryDatabase searchHistoryDatabase) {
        super(searchHistoryDatabase);
    }

    @Override // androidx.room.SharedSQLiteStatement
    public final String createQuery() {
        return "DELETE FROM searchhistory";
    }
}
