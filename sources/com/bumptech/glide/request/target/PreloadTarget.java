package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes2.dex */
public final class PreloadTarget<Z> extends CustomTarget<Z> {
    public static final Handler l = new Handler(Looper.getMainLooper(), new a());
    public final RequestManager k;

    /* loaded from: classes2.dex */
    public class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((PreloadTarget) message.obj).a();
                return true;
            }
            return false;
        }
    }

    public PreloadTarget(RequestManager requestManager, int i, int i2) {
        super(i, i2);
        this.k = requestManager;
    }

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager, int i, int i2) {
        return new PreloadTarget<>(requestManager, i, i2);
    }

    public void a() {
        this.k.clear(this);
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onResourceReady(@NonNull Z z, @Nullable Transition<? super Z> transition) {
        Request request = getRequest();
        if (request == null || !request.isComplete()) {
            return;
        }
        l.obtainMessage(1, this).sendToTarget();
    }
}
