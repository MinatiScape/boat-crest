package com.coveiot.android.sportsnotification.model;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AppDynamicSportsField {
    @NotNull
    public static final AppDynamicSportsField INSTANCE = new AppDynamicSportsField();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static ArrayList<DynamicSportsField> f5867a;

    @Nullable
    public final ArrayList<DynamicSportsField> getMDynamicSportsFieldList() {
        return f5867a;
    }

    public final void setMDynamicSportsFieldList(@Nullable ArrayList<DynamicSportsField> arrayList) {
        f5867a = arrayList;
    }
}
