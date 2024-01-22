package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class AndroidViewModel extends ViewModel {
    @SuppressLint({"StaticFieldLeak"})
    public Application c;

    public AndroidViewModel(@NonNull Application application) {
        this.c = application;
    }

    @NonNull
    public <T extends Application> T getApplication() {
        return (T) this.c;
    }
}
