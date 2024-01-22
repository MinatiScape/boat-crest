package okhttp3;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface Callback {
    void onFailure(@NotNull Call call, @NotNull IOException iOException);

    void onResponse(@NotNull Call call, @NotNull Response response) throws IOException;
}
