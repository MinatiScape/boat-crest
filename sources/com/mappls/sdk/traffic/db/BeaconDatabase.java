package com.mappls.sdk.traffic.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.mappls.sdk.traffic.db.dao.a;
@Database(entities = {ProbeLocation.class}, version = 1)
/* loaded from: classes8.dex */
public abstract class BeaconDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static BeaconDatabase f13316a;

    public static BeaconDatabase a(Context context) {
        if (f13316a == null) {
            f13316a = (BeaconDatabase) Room.inMemoryDatabaseBuilder(context.getApplicationContext(), BeaconDatabase.class).build();
        }
        return f13316a;
    }

    public abstract a a();
}
