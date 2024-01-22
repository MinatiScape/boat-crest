package com.abupdate.http_libs.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.abupdate.http_libs.request.base.Request;
import com.abupdate.http_libs.response.Response;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public b f1864a;
    public boolean b;
    public a c;
    public long d;
    public boolean e;

    /* loaded from: classes.dex */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (a.this.e) {
                return;
            }
            switch (message.what) {
                case 1:
                    a.this.b((Request) message.obj);
                    return;
                case 2:
                    Object[] objArr = (Object[]) message.obj;
                    a.this.b((String) objArr[0], (Response) objArr[1]);
                    return;
                case 3:
                    Object[] objArr2 = (Object[]) message.obj;
                    a.this.b((com.abupdate.http_libs.a.a) objArr2[0], (Response) objArr2[1]);
                    return;
                case 4:
                    Object[] objArr3 = (Object[]) message.obj;
                    a.this.c((Request) objArr3[0], ((Integer) objArr3[1]).intValue(), ((Integer) objArr3[2]).intValue());
                    return;
                case 5:
                    Object[] objArr4 = (Object[]) message.obj;
                    a.this.d((Request) objArr4[0], ((Integer) objArr4[1]).intValue(), ((Integer) objArr4[2]).intValue());
                    return;
                case 6:
                    a.this.b((Response) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public a() {
        this(true);
    }

    public a(boolean z) {
        this.b = true;
        this.e = false;
        a(z);
    }

    public final a a(boolean z) {
        this.b = z;
        if (z) {
            this.f1864a = new b(Looper.getMainLooper());
        } else {
            this.f1864a = null;
        }
        return this;
    }

    public final void a(com.abupdate.http_libs.a.a aVar, Response response) {
        b();
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(3);
            obtainMessage.obj = new Object[]{aVar, response};
            this.f1864a.sendMessage(obtainMessage);
        } else {
            b(aVar, response);
        }
        a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.a(aVar, response);
        }
    }

    public final void a(Request request) {
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(1);
            obtainMessage.obj = request;
            this.f1864a.sendMessage(obtainMessage);
        } else {
            b(request);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(request);
        }
    }

    public final void a(Request request, int i, int i2) {
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(4);
            obtainMessage.obj = new Object[]{request, Integer.valueOf(i), Integer.valueOf(i2)};
            this.f1864a.sendMessage(obtainMessage);
        } else {
            c(request, i, i2);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(request, i, i2);
        }
    }

    public final void a(Response response) {
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(6);
            obtainMessage.obj = response;
            this.f1864a.sendMessage(obtainMessage);
        } else {
            b(response);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(response);
        }
    }

    public final void a(String str, Response response) {
        b();
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(2);
            obtainMessage.obj = new Object[]{str, response};
            this.f1864a.sendMessage(obtainMessage);
        } else {
            b(str, response);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(str, response);
        }
    }

    public void b(com.abupdate.http_libs.a.a aVar, Response response) {
    }

    public void b(Request request) {
    }

    public final void b(Request request, int i, int i2) {
        if (this.e) {
            return;
        }
        if (this.b) {
            Message obtainMessage = this.f1864a.obtainMessage(5);
            obtainMessage.obj = new Object[]{request, Integer.valueOf(i), Integer.valueOf(i2)};
            this.f1864a.sendMessage(obtainMessage);
        } else {
            d(request, i, i2);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.b(request, i, i2);
        }
    }

    public void b(Response response) {
    }

    public void b(String str, Response response) {
    }

    public void b(boolean z) {
        this.e = z;
    }

    public final boolean b() {
        long j = this.d;
        if (j > 0) {
            try {
                Thread.sleep(j);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void c(Request request, int i, int i2) {
    }

    public void d(Request request, int i, int i2) {
    }
}
