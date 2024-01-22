package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MyWatchViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5194a;
    @NotNull
    public final String b;
    @NotNull
    public final MutableLiveData<WatchFaceBean> c;

    public MyWatchViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5194a = context;
        this.b = "MyWatchViewModel";
        this.c = new MutableLiveData<>();
    }

    public final void downloadDefaultWatchFaces() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MyWatchViewModel$downloadDefaultWatchFaces$1(this, null), 2, null);
    }

    public final void downloadWatchFaceFromServer(@NotNull WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new MyWatchViewModel$downloadWatchFaceFromServer$1(this, watchFaceBean, null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f5194a;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<WatchFaceBean> getWatchFaceDownloadLiveData() {
        return this.c;
    }
}
