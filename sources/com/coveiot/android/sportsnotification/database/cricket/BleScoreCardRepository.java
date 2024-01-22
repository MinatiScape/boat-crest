package com.coveiot.android.sportsnotification.database.cricket;

import android.content.Context;
import com.coveiot.android.sportsnotification.database.SportsDatabase;
import com.coveiot.android.sportsnotification.database.cricket.dao.EntityBleScoreDataDao;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityBleScoreCardData;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class BleScoreCardRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5844a;
    @NotNull
    public EntityBleScoreDataDao b;

    /* loaded from: classes7.dex */
    public static final class Companion extends SingletonHolder<BleScoreCardRepository, Context> {

        /* loaded from: classes7.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, BleScoreCardRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, BleScoreCardRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final BleScoreCardRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new BleScoreCardRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleScoreCardRepository(Context context) {
        this.f5844a = context;
        this.b = SportsDatabase.Companion.getDatabase(context).getBleScoreDataDao();
    }

    public /* synthetic */ BleScoreCardRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityBleScoreCardData getMatchScoreCardData(@NotNull String matchId) {
        Intrinsics.checkNotNullParameter(matchId, "matchId");
        return this.b.getMatchScoreCardData(matchId);
    }

    public final void insertScoreCardData(@NotNull EntityBleScoreCardData dataScoreCardData) {
        Intrinsics.checkNotNullParameter(dataScoreCardData, "dataScoreCardData");
        this.b.insert(dataScoreCardData);
    }

    public final void insertScoreCardDataList(@NotNull List<EntityBleScoreCardData> dataListScoreCardData) {
        Intrinsics.checkNotNullParameter(dataListScoreCardData, "dataListScoreCardData");
        this.b.insertAll(dataListScoreCardData);
    }
}
