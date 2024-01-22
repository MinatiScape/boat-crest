package com.coveiot.android.remotecommandframeworksdk;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\u0006\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/IMessageObserver;", "Lcom/coveiot/android/remotecommandframeworksdk/IObserver;", "", "topic", Constants.KEY_MESSAGE, "", "onMessageUpdate", "(Ljava/lang/String;Ljava/lang/String;)V", "", "byteArray", "(Ljava/lang/String;[B)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IMessageObserver extends IObserver {
    void onMessageUpdate(@NotNull String str, @NotNull String str2);

    void onMessageUpdate(@NotNull String str, @NotNull byte[] bArr);
}
