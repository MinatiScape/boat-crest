package com.mappls.sdk.plugins.places.autocomplete.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
/* loaded from: classes10.dex */
public final class a extends RoomDatabase.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f13132a;

    public a(Context context) {
        this.f13132a = context;
    }

    @Override // androidx.room.RoomDatabase.Callback
    public final void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        super.onCreate(supportSQLiteDatabase);
        SearchHistoryDatabase.c(SearchHistoryDatabase.a(this.f13132a));
    }
}
