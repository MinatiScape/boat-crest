package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import androidx.annotation.VisibleForTesting;
import com.android.volley.toolbox.JsonRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class WebViewYouTubePlayerKt {
    @VisibleForTesting
    @NotNull
    public static final String readHTMLFromUTF8File(@NotNull InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            try {
                String joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(TextStreamsKt.readLines(new BufferedReader(new InputStreamReader(inputStream, JsonRequest.PROTOCOL_CHARSET))), "\n", null, null, 0, null, null, 62, null);
                CloseableKt.closeFinally(inputStream, null);
                return joinToString$default;
            } catch (Exception unused) {
                throw new RuntimeException("Can't parse HTML file.");
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStream, th);
                throw th2;
            }
        }
    }
}
