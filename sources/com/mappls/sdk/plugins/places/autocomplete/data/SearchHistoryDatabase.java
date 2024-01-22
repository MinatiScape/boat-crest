package com.mappls.sdk.plugins.places.autocomplete.data;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
@TypeConverters({com.mappls.sdk.plugins.places.autocomplete.data.converter.a.class})
@Database(entities = {com.mappls.sdk.plugins.places.autocomplete.data.entity.a.class}, exportSchema = false, version = 3)
/* loaded from: classes10.dex */
public abstract class SearchHistoryDatabase extends RoomDatabase {
    public static SearchHistoryDatabase b;

    /* renamed from: a  reason: collision with root package name */
    public final MutableLiveData<Boolean> f13129a = new MutableLiveData<>();

    /* loaded from: classes10.dex */
    public static class a extends AsyncTask<Void, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        public final SearchHistoryDatabase f13130a;
        public com.mappls.sdk.plugins.places.autocomplete.data.entity.a b;
        public boolean c;

        public a(SearchHistoryDatabase searchHistoryDatabase) {
            this.c = true;
            this.f13130a = searchHistoryDatabase;
        }

        public a(SearchHistoryDatabase searchHistoryDatabase, com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar) {
            this.b = aVar;
            this.f13130a = searchHistoryDatabase;
        }

        @Override // android.os.AsyncTask
        public final Void doInBackground(Void[] voidArr) {
            if (this.c) {
                this.f13130a.b().a();
                return null;
            }
            this.f13130a.b().a(this.b);
            return null;
        }
    }

    public static SearchHistoryDatabase a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            SearchHistoryDatabase searchHistoryDatabase = (SearchHistoryDatabase) Room.databaseBuilder(applicationContext, SearchHistoryDatabase.class, "com.mappls.sdk.plugins.places.database").fallbackToDestructiveMigration().addCallback(new com.mappls.sdk.plugins.places.autocomplete.data.a(applicationContext)).build();
            b = searchHistoryDatabase;
            Context applicationContext2 = context.getApplicationContext();
            searchHistoryDatabase.getClass();
            if (applicationContext2.getDatabasePath("com.mappls.sdk.plugins.places.database").exists()) {
                searchHistoryDatabase.f13129a.postValue(Boolean.TRUE);
            }
        }
        return b;
    }

    public static void a(SearchHistoryDatabase searchHistoryDatabase, com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar) {
        new a(searchHistoryDatabase, aVar).execute(new Void[0]);
    }

    public static void b(SearchHistoryDatabase searchHistoryDatabase) {
        new a(searchHistoryDatabase).execute(new Void[0]);
    }

    public static void c(SearchHistoryDatabase searchHistoryDatabase) {
        searchHistoryDatabase.f13129a.postValue(Boolean.TRUE);
    }

    public final MutableLiveData a() {
        return this.f13129a;
    }

    public abstract com.mappls.sdk.plugins.places.autocomplete.data.dao.a b();
}
