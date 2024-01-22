package com.coveiot.repository.accelerometer.datasource.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.accelerometer.RawAccelerometerDao;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import com.coveiot.repository.accelerometer.datasource.db.write.AccelerometerDataWrite;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class RawAccelerometerDBRepo {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static RawAccelerometerDBRepo b;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RawAccelerometerDao f7307a;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final RawAccelerometerDBRepo getInstance(@Nullable Context context) {
            if (RawAccelerometerDBRepo.b == null) {
                RawAccelerometerDBRepo.b = new RawAccelerometerDBRepo(context);
            }
            return RawAccelerometerDBRepo.b;
        }
    }

    public RawAccelerometerDBRepo(@Nullable Context context) {
        RawAccelerometerDao rawAccelerometerDao = CoveAppDatabase.getAppDatabase(context).rawAccelerometerDao();
        Intrinsics.checkNotNullExpressionValue(rawAccelerometerDao, "appDatabase.rawAccelerometerDao()");
        this.f7307a = rawAccelerometerDao;
    }

    @NotNull
    public final RawAccelerometerDao getRawAccelerometerDao() {
        return this.f7307a;
    }

    public final void insert(@NotNull EntityRawAccelerometerData entityRawAccelerometerData) {
        Intrinsics.checkNotNullParameter(entityRawAccelerometerData, "entityRawAccelerometerData");
        AccelerometerDataWrite.Companion.insert(this.f7307a, entityRawAccelerometerData);
    }
}
