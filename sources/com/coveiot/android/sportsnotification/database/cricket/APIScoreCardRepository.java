package com.coveiot.android.sportsnotification.database.cricket;

import android.content.Context;
import com.coveiot.android.sportsnotification.database.SportsDatabase;
import com.coveiot.android.sportsnotification.database.cricket.dao.EntityAPIScoreCardDataDao;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityAPIScoreCardData;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class APIScoreCardRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5843a;
    @NotNull
    public EntityAPIScoreCardDataDao b;

    /* loaded from: classes7.dex */
    public static final class Companion extends SingletonHolder<APIScoreCardRepository, Context> {

        /* loaded from: classes7.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, APIScoreCardRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, APIScoreCardRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final APIScoreCardRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new APIScoreCardRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public APIScoreCardRepository(Context context) {
        this.f5843a = context;
        this.b = SportsDatabase.Companion.getDatabase(context).getScoreCardDao();
    }

    public /* synthetic */ APIScoreCardRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityAPIScoreCardData getMatchScoreCardData(@NotNull String matchId) {
        Intrinsics.checkNotNullParameter(matchId, "matchId");
        return this.b.getMatchScoreCardData(matchId);
    }

    public final void insertScoreCardData(@NotNull EntityAPIScoreCardData dataScoreCardData) {
        Intrinsics.checkNotNullParameter(dataScoreCardData, "dataScoreCardData");
        this.b.insert(dataScoreCardData);
    }

    public final void insertScoreCardDataList(@NotNull List<EntityAPIScoreCardData> dataListScoreCardData) {
        Intrinsics.checkNotNullParameter(dataListScoreCardData, "dataListScoreCardData");
        this.b.insertAll(dataListScoreCardData);
    }
}
