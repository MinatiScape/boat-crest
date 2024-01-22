package com.android.volley;
/* loaded from: classes.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;

    /* renamed from: a  reason: collision with root package name */
    public int f2144a;
    public int b;
    public final int c;
    public final float d;

    public DefaultRetryPolicy() {
        this(DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    public float getBackoffMultiplier() {
        return this.d;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentRetryCount() {
        return this.b;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentTimeout() {
        return this.f2144a;
    }

    public boolean hasAttemptRemaining() {
        return this.b <= this.c;
    }

    @Override // com.android.volley.RetryPolicy
    public void retry(VolleyError volleyError) throws VolleyError {
        this.b++;
        int i = this.f2144a;
        this.f2144a = i + ((int) (i * this.d));
        if (!hasAttemptRemaining()) {
            throw volleyError;
        }
    }

    public DefaultRetryPolicy(int i, int i2, float f) {
        this.f2144a = i;
        this.c = i2;
        this.d = f;
    }
}
