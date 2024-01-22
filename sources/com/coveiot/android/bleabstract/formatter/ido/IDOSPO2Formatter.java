package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.spo2.EntityHealthSpo2;
import com.coveiot.khidodb.spo2.model.KHHealthSpo2Item;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOSPO2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3358a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOSPO2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOSPO2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOSPO2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3359a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOSPO2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOSPO2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOSPO2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3359a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOSPO2Formatter(Context context) {
        this.f3358a = context;
    }

    public /* synthetic */ IDOSPO2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityHealthSpo2 convertHealthSpo2ToEntity(@NotNull HealthSpO2 healthSpo2, @NotNull List<? extends HealthSpO2Item> healthSpo2ItemList) {
        Intrinsics.checkNotNullParameter(healthSpo2, "healthSpo2");
        Intrinsics.checkNotNullParameter(healthSpo2ItemList, "healthSpo2ItemList");
        if (!healthSpo2ItemList.isEmpty()) {
            EntityHealthSpo2 entityHealthSpo2 = new EntityHealthSpo2(healthSpo2.day, healthSpo2.month, healthSpo2.year, healthSpo2.startTime, IDOUtils.INSTANCE.getMacAddress(this.f3358a));
            ArrayList arrayList = new ArrayList();
            int size = healthSpo2ItemList.size();
            for (int i = 0; i < size; i++) {
                KHHealthSpo2Item kHHealthSpo2Item = new KHHealthSpo2Item();
                kHHealthSpo2Item.setOffset(healthSpo2ItemList.get(i).offset);
                kHHealthSpo2Item.setValue(healthSpo2ItemList.get(i).value);
                arrayList.add(kHHealthSpo2Item);
            }
            entityHealthSpo2.setItems(arrayList);
            entityHealthSpo2.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthSpo2.year, healthSpo2.month, healthSpo2.day, 0, 0, 0));
            entityHealthSpo2.setDate(healthSpo2.date);
            return entityHealthSpo2;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3358a;
    }

    @NotNull
    public final ReadManualSpo2Response getSPO2Data(@NotNull EntityHealthSpo2 entityHealthSpo2) {
        List<KHHealthSpo2Item> items;
        Intrinsics.checkNotNullParameter(entityHealthSpo2, "entityHealthSpo2");
        ArrayList arrayList = new ArrayList();
        if (entityHealthSpo2.getItems() != null) {
            Intrinsics.checkNotNull(entityHealthSpo2.getItems());
            if (!items.isEmpty()) {
                int startTime = entityHealthSpo2.getStartTime();
                List<KHHealthSpo2Item> items2 = entityHealthSpo2.getItems();
                Intrinsics.checkNotNull(items2);
                int size = items2.size();
                for (int i = 0; i < size; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(1, entityHealthSpo2.getYear());
                    calendar.set(2, entityHealthSpo2.getMonth() - 1);
                    calendar.set(5, entityHealthSpo2.getDay());
                    List<KHHealthSpo2Item> items3 = entityHealthSpo2.getItems();
                    Intrinsics.checkNotNull(items3);
                    startTime += items3.get(i).getOffset();
                    calendar.set(11, startTime / 60);
                    calendar.set(12, startTime % 60);
                    calendar.set(13, 0);
                    ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
                    List<KHHealthSpo2Item> items4 = entityHealthSpo2.getItems();
                    Intrinsics.checkNotNull(items4);
                    if (items4.get(i).getValue() > 0) {
                        List<KHHealthSpo2Item> items5 = entityHealthSpo2.getItems();
                        Intrinsics.checkNotNull(items5);
                        manualSpo2Reading.setSpo2(items5.get(i).getValue());
                        manualSpo2Reading.setTimeStamp(calendar.getTimeInMillis());
                        arrayList.add(manualSpo2Reading);
                    }
                }
            }
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList);
        readManualSpo2Response.setComplete(true);
        return readManualSpo2Response;
    }
}
