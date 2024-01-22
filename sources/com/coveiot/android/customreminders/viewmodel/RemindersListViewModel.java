package com.coveiot.android.customreminders.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class RemindersListViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4191a;
    @NotNull
    public MutableLiveData<List<CustomReminder>> b;

    public RemindersListViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4191a = context;
        this.b = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f4191a;
    }

    @NotNull
    public final MutableLiveData<List<CustomReminder>> getRemindersListLiveData() {
        return this.b;
    }

    public final void loadRemindersList() {
        this.b.setValue(PreferenceManagerCustomReminders.Companion.getReminders(this.f4191a));
    }

    public final void setRemindersListLiveData(@NotNull MutableLiveData<List<CustomReminder>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
