package com.coveiot.android.fitnessbuddies.utils;

import com.coveiot.utils.model.CoveContact;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class CoveContactsManager {
    @NotNull
    public static final CoveContactsManager INSTANCE = new CoveContactsManager();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static List<? extends CoveContact> f4472a;
    @Nullable
    public static List<? extends CoveContact> b;
    @Nullable
    public static List<? extends CoveContact> c;

    @Nullable
    public final List<CoveContact> getActiveContacts() {
        return b;
    }

    @Nullable
    public final List<CoveContact> getContacts() {
        return f4472a;
    }

    @Nullable
    public final List<CoveContact> getInActiveContacts() {
        return c;
    }

    public final void setActiveContacts(@NotNull List<? extends CoveContact> activeContacts) {
        Intrinsics.checkNotNullParameter(activeContacts, "activeContacts");
        b = activeContacts;
    }

    public final void setContacts(@NotNull List<? extends CoveContact> contacts) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        f4472a = contacts;
    }

    public final void setInActiveContacts(@NotNull List<? extends CoveContact> inActiveContacts) {
        Intrinsics.checkNotNullParameter(inActiveContacts, "inActiveContacts");
        c = inActiveContacts;
    }
}
