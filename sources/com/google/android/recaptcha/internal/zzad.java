package com.google.android.recaptcha.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.firebase.crashlytics.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.e;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzad extends SQLiteOpenHelper {
    @NotNull
    public static final zzab zza = new zzab(null);
    private static final int zzb;
    @Nullable
    private static zzad zzc;

    static {
        int zzb2;
        zzb2 = zzab.zzb(BuildConfig.VERSION_NAME);
        zzb = zzb2;
    }

    public /* synthetic */ zzad(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        super(context, "cesdb", (SQLiteDatabase.CursorFactory) null, zzb);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(@NotNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(@NotNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(@NotNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final int zza(@NotNull List list) {
        if (list.isEmpty()) {
            return 0;
        }
        return getWritableDatabase().delete("ce", "id IN ".concat(String.valueOf(CollectionsKt___CollectionsKt.joinToString$default(list, ", ", "(", ")", 0, null, zzac.zza, 24, null))), null);
    }

    public final int zzb() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM ce", null);
        try {
            if (rawQuery.moveToNext()) {
                return rawQuery.getInt(0);
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        } finally {
            rawQuery.close();
        }
    }

    @NotNull
    public final List zzd() {
        Cursor query = getReadableDatabase().query("ce", null, null, null, null, null, "ts ASC");
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzae(query.getString(query.getColumnIndexOrThrow("ss")), query.getLong(query.getColumnIndexOrThrow(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP)), query.getInt(query.getColumnIndexOrThrow("id"))));
            } catch (Exception unused) {
                return CollectionsKt__CollectionsKt.emptyList();
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    public final boolean zzf(@NotNull zzae zzaeVar) {
        return zza(e.listOf(zzaeVar)) == 1;
    }
}
