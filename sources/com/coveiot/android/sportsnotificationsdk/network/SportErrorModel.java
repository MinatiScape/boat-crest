package com.coveiot.android.sportsnotificationsdk.network;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\t\u0010\rR$\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00038\u0006@BX\u0087\u000e¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/network/SportErrorModel;", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiErrorModel;", "Ljava/io/Serializable;", "", "<set-?>", "msg", "Ljava/lang/String;", "getMsg", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "", "code", "(Ljava/lang/String;I)V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class SportErrorModel extends SportsApiErrorModel {
    @SerializedName(Constants.KEY_MESSAGE)
    @NotNull
    private String msg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportErrorModel(@NotNull String msg) {
        super(-1);
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.msg = msg;
    }

    @NotNull
    public final String getMsg() {
        return this.msg;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportErrorModel(@NotNull String msg, int i) {
        super(i);
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.msg = msg;
    }
}
