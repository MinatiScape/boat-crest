package com.coveiot.repository.ppg.datasource.db.write;

import com.coveiot.covedb.ppg.RawPPGDao;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class PPGDataWrite {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void insert(@NotNull RawPPGDao rawPPGDao, @NotNull EntityRawPPGData entityRawPPGData) {
            Intrinsics.checkNotNullParameter(rawPPGDao, "rawPPGDao");
            Intrinsics.checkNotNullParameter(entityRawPPGData, "entityRawPPGData");
            rawPPGDao.insert(entityRawPPGData);
        }
    }
}
