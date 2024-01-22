package com.mappls.sdk.maps.renderer.egl;

import android.opengl.GLException;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import java.io.IOException;
import java.io.Writer;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGL11;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
/* loaded from: classes11.dex */
public class EGLLogWrapper implements EGL11 {

    /* renamed from: a  reason: collision with root package name */
    public EGL10 f12824a;
    public Writer b;
    public boolean c;
    public boolean d;
    public int e;

    public EGLLogWrapper(EGL egl, int i, Writer writer) {
        this.f12824a = (EGL10) egl;
        this.b = writer;
        this.c = (i & 4) != 0;
        this.d = (i & 1) != 0;
    }

    public static String getErrorString(int i) {
        switch (i) {
            case RcspErrorCode.ERR_CMD_SEND /* 12288 */:
                return "EGL_SUCCESS";
            case RcspErrorCode.ERR_PARSE_DATA /* 12289 */:
                return "EGL_NOT_INITIALIZED";
            case 12290:
                return "EGL_BAD_ACCESS";
            case 12291:
                return "EGL_BAD_ALLOC";
            case 12292:
                return "EGL_BAD_ATTRIBUTE";
            case 12293:
                return "EGL_BAD_CONFIG";
            case 12294:
                return "EGL_BAD_CONTEXT";
            case ErrorCode.SUB_ERR_SEND_TIMEOUT /* 12295 */:
                return "EGL_BAD_CURRENT_SURFACE";
            case ErrorCode.SUB_ERR_RESPONSE_BAD_STATUS /* 12296 */:
                return "EGL_BAD_DISPLAY";
            case 12297:
                return "EGL_BAD_MATCH";
            case ErrorCode.SUB_ERR_RESPONSE_BAD_RESULT /* 12298 */:
                return "EGL_BAD_NATIVE_PIXMAP";
            case ErrorCode.SUB_ERR_WAITING_COMMAND_TIMEOUT /* 12299 */:
                return "EGL_BAD_NATIVE_WINDOW";
            case 12300:
                return "EGL_BAD_PARAMETER";
            case 12301:
                return "EGL_BAD_SURFACE";
            case 12302:
                return "EGL_CONTEXT_LOST";
            default:
                return m(i);
        }
    }

    public static String m(int i) {
        return HexStringBuilder.DEFAULT_PREFIX + Integer.toHexString(i);
    }

    public final void a(String str, int i) {
        c(str, Integer.toString(i));
    }

    public final void b(String str, Object obj) {
        c(str, v(obj));
    }

    public final void c(String str, String str2) {
        int i = this.e;
        this.e = i + 1;
        if (i > 0) {
            n(", ");
        }
        if (this.c) {
            n(str + "=");
        }
        n(str2);
    }

    public final void d(String str, EGLContext eGLContext) {
        if (eGLContext == EGL10.EGL_NO_CONTEXT) {
            c(str, "EGL10.EGL_NO_CONTEXT");
        } else {
            c(str, v(eGLContext));
        }
    }

    public final void e(String str, EGLDisplay eGLDisplay) {
        if (eGLDisplay == EGL10.EGL_DEFAULT_DISPLAY) {
            c(str, "EGL10.EGL_DEFAULT_DISPLAY");
        } else if (eGLDisplay == EGL11.EGL_NO_DISPLAY) {
            c(str, "EGL10.EGL_NO_DISPLAY");
        } else {
            c(str, v(eGLDisplay));
        }
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglChooseConfig(EGLDisplay eGLDisplay, int[] iArr, EGLConfig[] eGLConfigArr, int i, int[] iArr2) {
        i("eglChooseConfig");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        g("attrib_list", iArr);
        a("config_size", i);
        k();
        boolean eglChooseConfig = this.f12824a.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i, iArr2);
        h("configs", eGLConfigArr);
        g("num_config", iArr2);
        s(eglChooseConfig);
        j();
        return eglChooseConfig;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglCopyBuffers(EGLDisplay eGLDisplay, EGLSurface eGLSurface, Object obj) {
        i("eglCopyBuffers");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        f("surface", eGLSurface);
        b("native_pixmap", obj);
        k();
        boolean eglCopyBuffers = this.f12824a.eglCopyBuffers(eGLDisplay, eGLSurface, obj);
        s(eglCopyBuffers);
        j();
        return eglCopyBuffers;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLContext eglCreateContext(EGLDisplay eGLDisplay, EGLConfig eGLConfig, EGLContext eGLContext, int[] iArr) {
        i("eglCreateContext");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        b(com.clevertap.android.sdk.Constants.KEY_CONFIG, eGLConfig);
        d("share_context", eGLContext);
        g("attrib_list", iArr);
        k();
        EGLContext eglCreateContext = this.f12824a.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        q(eglCreateContext);
        j();
        return eglCreateContext;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLSurface eglCreatePbufferSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int[] iArr) {
        i("eglCreatePbufferSurface");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        b(com.clevertap.android.sdk.Constants.KEY_CONFIG, eGLConfig);
        g("attrib_list", iArr);
        k();
        EGLSurface eglCreatePbufferSurface = this.f12824a.eglCreatePbufferSurface(eGLDisplay, eGLConfig, iArr);
        q(eglCreatePbufferSurface);
        j();
        return eglCreatePbufferSurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLSurface eglCreatePixmapSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj, int[] iArr) {
        i("eglCreatePixmapSurface");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        b(com.clevertap.android.sdk.Constants.KEY_CONFIG, eGLConfig);
        b("native_pixmap", obj);
        g("attrib_list", iArr);
        k();
        EGLSurface eglCreatePixmapSurface = this.f12824a.eglCreatePixmapSurface(eGLDisplay, eGLConfig, obj, iArr);
        q(eglCreatePixmapSurface);
        j();
        return eglCreatePixmapSurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLSurface eglCreateWindowSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj, int[] iArr) {
        i("eglCreateWindowSurface");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        b(com.clevertap.android.sdk.Constants.KEY_CONFIG, eGLConfig);
        b("native_window", obj);
        g("attrib_list", iArr);
        k();
        EGLSurface eglCreateWindowSurface = this.f12824a.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, iArr);
        q(eglCreateWindowSurface);
        j();
        return eglCreateWindowSurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglDestroyContext(EGLDisplay eGLDisplay, EGLContext eGLContext) {
        i("eglDestroyContext");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        d("context", eGLContext);
        k();
        boolean eglDestroyContext = this.f12824a.eglDestroyContext(eGLDisplay, eGLContext);
        s(eglDestroyContext);
        j();
        return eglDestroyContext;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglDestroySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        i("eglDestroySurface");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        f("surface", eGLSurface);
        k();
        boolean eglDestroySurface = this.f12824a.eglDestroySurface(eGLDisplay, eGLSurface);
        s(eglDestroySurface);
        j();
        return eglDestroySurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglGetConfigAttrib(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int[] iArr) {
        i("eglGetConfigAttrib");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        b(com.clevertap.android.sdk.Constants.KEY_CONFIG, eGLConfig);
        a("attribute", i);
        k();
        boolean eglGetConfigAttrib = this.f12824a.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr);
        g("value", iArr);
        s(eglGetConfigAttrib);
        j();
        return false;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglGetConfigs(EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr, int i, int[] iArr) {
        i("eglGetConfigs");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        a("config_size", i);
        k();
        boolean eglGetConfigs = this.f12824a.eglGetConfigs(eGLDisplay, eGLConfigArr, i, iArr);
        h("configs", eGLConfigArr);
        g("num_config", iArr);
        s(eglGetConfigs);
        j();
        return eglGetConfigs;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLContext eglGetCurrentContext() {
        i("eglGetCurrentContext");
        k();
        EGLContext eglGetCurrentContext = this.f12824a.eglGetCurrentContext();
        q(eglGetCurrentContext);
        j();
        return eglGetCurrentContext;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLDisplay eglGetCurrentDisplay() {
        i("eglGetCurrentDisplay");
        k();
        EGLDisplay eglGetCurrentDisplay = this.f12824a.eglGetCurrentDisplay();
        q(eglGetCurrentDisplay);
        j();
        return eglGetCurrentDisplay;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLSurface eglGetCurrentSurface(int i) {
        i("eglGetCurrentSurface");
        a("readdraw", i);
        k();
        EGLSurface eglGetCurrentSurface = this.f12824a.eglGetCurrentSurface(i);
        q(eglGetCurrentSurface);
        j();
        return eglGetCurrentSurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public EGLDisplay eglGetDisplay(Object obj) {
        i("eglGetDisplay");
        b("native_display", obj);
        k();
        EGLDisplay eglGetDisplay = this.f12824a.eglGetDisplay(obj);
        q(eglGetDisplay);
        j();
        return eglGetDisplay;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public int eglGetError() {
        i("eglGetError");
        k();
        int eglGetError = this.f12824a.eglGetError();
        r(getErrorString(eglGetError));
        return eglGetError;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglInitialize(EGLDisplay eGLDisplay, int[] iArr) {
        i("eglInitialize");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        k();
        boolean eglInitialize = this.f12824a.eglInitialize(eGLDisplay, iArr);
        s(eglInitialize);
        g("major_minor", iArr);
        j();
        return eglInitialize;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglMakeCurrent(EGLDisplay eGLDisplay, EGLSurface eGLSurface, EGLSurface eGLSurface2, EGLContext eGLContext) {
        i("eglMakeCurrent");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        f("draw", eGLSurface);
        f("read", eGLSurface2);
        d("context", eGLContext);
        k();
        boolean eglMakeCurrent = this.f12824a.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface2, eGLContext);
        s(eglMakeCurrent);
        j();
        return eglMakeCurrent;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglQueryContext(EGLDisplay eGLDisplay, EGLContext eGLContext, int i, int[] iArr) {
        i("eglQueryContext");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        d("context", eGLContext);
        a("attribute", i);
        k();
        boolean eglQueryContext = this.f12824a.eglQueryContext(eGLDisplay, eGLContext, i, iArr);
        p(iArr[0]);
        s(eglQueryContext);
        j();
        return eglQueryContext;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public String eglQueryString(EGLDisplay eGLDisplay, int i) {
        i("eglQueryString");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        a(AppMeasurementSdk.ConditionalUserProperty.NAME, i);
        k();
        String eglQueryString = this.f12824a.eglQueryString(eGLDisplay, i);
        r(eglQueryString);
        j();
        return eglQueryString;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglQuerySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface, int i, int[] iArr) {
        i("eglQuerySurface");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        f("surface", eGLSurface);
        a("attribute", i);
        k();
        boolean eglQuerySurface = this.f12824a.eglQuerySurface(eGLDisplay, eGLSurface, i, iArr);
        p(iArr[0]);
        s(eglQuerySurface);
        j();
        return eglQuerySurface;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglSwapBuffers(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        i("eglSwapBuffers");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        f("surface", eGLSurface);
        k();
        boolean eglSwapBuffers = this.f12824a.eglSwapBuffers(eGLDisplay, eGLSurface);
        s(eglSwapBuffers);
        j();
        return eglSwapBuffers;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglTerminate(EGLDisplay eGLDisplay) {
        i("eglTerminate");
        e(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, eGLDisplay);
        k();
        boolean eglTerminate = this.f12824a.eglTerminate(eGLDisplay);
        s(eglTerminate);
        j();
        return eglTerminate;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglWaitGL() {
        i("eglWaitGL");
        k();
        boolean eglWaitGL = this.f12824a.eglWaitGL();
        s(eglWaitGL);
        j();
        return eglWaitGL;
    }

    @Override // javax.microedition.khronos.egl.EGL10
    public boolean eglWaitNative(int i, Object obj) {
        i("eglWaitNative");
        a("engine", i);
        b("bindTarget", obj);
        k();
        boolean eglWaitNative = this.f12824a.eglWaitNative(i, obj);
        s(eglWaitNative);
        j();
        return eglWaitNative;
    }

    public final void f(String str, EGLSurface eGLSurface) {
        if (eGLSurface == EGL10.EGL_NO_SURFACE) {
            c(str, "EGL10.EGL_NO_SURFACE");
        } else {
            c(str, v(eGLSurface));
        }
    }

    public final void g(String str, int[] iArr) {
        if (iArr == null) {
            c(str, "null");
        } else {
            c(str, t(iArr.length, iArr, 0));
        }
    }

    public final void h(String str, Object[] objArr) {
        if (objArr == null) {
            c(str, "null");
        } else {
            c(str, u(objArr.length, objArr, 0));
        }
    }

    public final void i(String str) {
        n(str + HexStringBuilder.COMMENT_BEGIN_CHAR);
        this.e = 0;
    }

    public final void j() {
        int eglGetError = this.f12824a.eglGetError();
        if (eglGetError != 12288) {
            String str = "eglError: " + getErrorString(eglGetError);
            o(str);
            if (this.d) {
                throw new GLException(eglGetError, str);
            }
        }
    }

    public final void k() {
        n(");\n");
        l();
    }

    public final void l() {
        try {
            this.b.flush();
        } catch (IOException unused) {
            this.b = null;
        }
    }

    public final void n(String str) {
        try {
            this.b.write(str);
        } catch (IOException unused) {
        }
    }

    public final void o(String str) {
        n(str + '\n');
    }

    public final void p(int i) {
        r(Integer.toString(i));
    }

    public final void q(Object obj) {
        r(v(obj));
    }

    public final void r(String str) {
        n(" returns " + str + ";\n");
        l();
    }

    public final void s(boolean z) {
        r(Boolean.toString(z));
    }

    public final String t(int i, int[] iArr, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        int length = iArr.length;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i2 + i3;
            sb.append(" [" + i4 + "] = ");
            if (i4 >= 0 && i4 < length) {
                sb.append(iArr[i4]);
            } else {
                sb.append("out of bounds");
            }
            sb.append('\n');
        }
        sb.append("}");
        return sb.toString();
    }

    public final String u(int i, Object[] objArr, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        int length = objArr.length;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i2 + i3;
            sb.append(" [" + i4 + "] = ");
            if (i4 >= 0 && i4 < length) {
                sb.append(objArr[i4]);
            } else {
                sb.append("out of bounds");
            }
            sb.append('\n');
        }
        sb.append("}");
        return sb.toString();
    }

    public final String v(Object obj) {
        return obj == null ? "null" : obj.toString();
    }
}
