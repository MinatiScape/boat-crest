package com.coveiot.repository.accelerometer.datasource.db.write;

import com.coveiot.covedb.accelerometer.RawAccelerometerDao;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class AccelerometerDataWrite {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void insert(@NotNull RawAccelerometerDao rawAccelerometerDao, @NotNull EntityRawAccelerometerData entityRawAccelerometerData) {
            Intrinsics.checkNotNullParameter(rawAccelerometerDao, "rawAccelerometerDao");
            Intrinsics.checkNotNullParameter(entityRawAccelerometerData, "entityRawAccelerometerData");
            rawAccelerometerDao.insert(entityRawAccelerometerData);
        }
    }
}
