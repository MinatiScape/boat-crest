package com.coveiot.android.remotecommandframeworksdk.listener;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;", "Lcom/coveiot/android/remotecommandframeworksdk/listener/BaseListener;", "", "onSuccess", "()V", "", "msg", "onFailure", "(Ljava/lang/String;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface SuccessListener extends BaseListener {
    void onFailure(@Nullable String str);

    void onSuccess();
}
