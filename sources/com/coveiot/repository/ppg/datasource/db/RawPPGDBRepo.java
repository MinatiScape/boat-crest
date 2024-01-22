package com.coveiot.repository.ppg.datasource.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.ppg.RawPPGDao;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import com.coveiot.repository.ppg.datasource.db.write.PPGDataWrite;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class RawPPGDBRepo {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static RawPPGDBRepo b;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final RawPPGDao f7421a;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final RawPPGDBRepo getInstance(@Nullable Context context) {
            if (RawPPGDBRepo.b == null) {
                RawPPGDBRepo.b = new RawPPGDBRepo(context);
            }
            return RawPPGDBRepo.b;
        }
    }

    public RawPPGDBRepo(@Nullable Context context) {
        RawPPGDao rawPPGDao = CoveAppDatabase.getAppDatabase(context).rawPPGDao();
        Intrinsics.checkNotNullExpressionValue(rawPPGDao, "appDatabase.rawPPGDao()");
        this.f7421a = rawPPGDao;
    }

    @NotNull
    public final RawPPGDao getRawPPGDao() {
        return this.f7421a;
    }

    public final void insert(@NotNull EntityRawPPGData entityRawPPGData) {
        Intrinsics.checkNotNullParameter(entityRawPPGData, "entityRawPPGData");
        PPGDataWrite.Companion.insert(this.f7421a, entityRawPPGData);
    }
}
