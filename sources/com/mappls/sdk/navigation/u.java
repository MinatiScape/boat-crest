package com.mappls.sdk.navigation;

import android.os.AsyncTask;
/* loaded from: classes11.dex */
public final class u {

    /* loaded from: classes11.dex */
    public class a<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

        /* renamed from: a  reason: collision with root package name */
        public final b<Params, Progress, Result> f12965a;

        public a(b bVar) {
            this.f12965a = bVar;
        }

        public /* synthetic */ a(b bVar, int i) {
            this(bVar);
        }

        @Override // android.os.AsyncTask
        public final Result doInBackground(Params... paramsArr) {
            return this.f12965a.a((Object[]) paramsArr);
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(Result result) {
            this.f12965a.a((b<Params, Progress, Result>) result);
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
            this.f12965a.a();
        }

        @Override // android.os.AsyncTask
        public final void onProgressUpdate(Progress... progressArr) {
            this.f12965a.getClass();
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class b<Params, Progress, Result> {
        public abstract Result a(Params... paramsArr);

        public void a() {
        }

        public abstract void a(Result result);
    }

    @SafeVarargs
    public static void a(b bVar, Object... objArr) {
        new a(bVar, 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objArr);
    }
}
