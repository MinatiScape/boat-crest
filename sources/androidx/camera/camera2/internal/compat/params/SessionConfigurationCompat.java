package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class SessionConfigurationCompat {
    public static final int SESSION_HIGH_SPEED = 1;
    public static final int SESSION_REGULAR = 0;

    /* renamed from: a  reason: collision with root package name */
    public final c f535a;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface SessionMode {
    }

    /* loaded from: classes.dex */
    public static final class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public final List<OutputConfigurationCompat> f537a;
        public final CameraCaptureSession.StateCallback b;
        public final Executor c;
        public int d;
        public InputConfigurationCompat e = null;
        public CaptureRequest f = null;

        public b(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull Executor executor, @NonNull CameraCaptureSession.StateCallback stateCallback) {
            this.d = i;
            this.f537a = Collections.unmodifiableList(new ArrayList(list));
            this.b = stateCallback;
            this.c = executor;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        @Nullable
        public InputConfigurationCompat a() {
            return this.e;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public CameraCaptureSession.StateCallback b() {
            return this.b;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public void c(@NonNull InputConfigurationCompat inputConfigurationCompat) {
            if (this.d != 1) {
                this.e = inputConfigurationCompat;
                return;
            }
            throw new UnsupportedOperationException("Method not supported for high speed session types");
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public CaptureRequest d() {
            return this.f;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public List<OutputConfigurationCompat> e() {
            return this.f537a;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (Objects.equals(this.e, bVar.e) && this.d == bVar.d && this.f537a.size() == bVar.f537a.size()) {
                    for (int i = 0; i < this.f537a.size(); i++) {
                        if (!this.f537a.get(i).equals(bVar.f537a.get(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        @Nullable
        public Object f() {
            return null;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public int g() {
            return this.d;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public Executor getExecutor() {
            return this.c;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public void h(CaptureRequest captureRequest) {
            this.f = captureRequest;
        }

        public int hashCode() {
            int hashCode = this.f537a.hashCode() ^ 31;
            int i = (hashCode << 5) - hashCode;
            InputConfigurationCompat inputConfigurationCompat = this.e;
            int hashCode2 = (inputConfigurationCompat == null ? 0 : inputConfigurationCompat.hashCode()) ^ i;
            return this.d ^ ((hashCode2 << 5) - hashCode2);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        InputConfigurationCompat a();

        CameraCaptureSession.StateCallback b();

        void c(@NonNull InputConfigurationCompat inputConfigurationCompat);

        CaptureRequest d();

        List<OutputConfigurationCompat> e();

        @Nullable
        Object f();

        int g();

        Executor getExecutor();

        void h(CaptureRequest captureRequest);
    }

    public SessionConfigurationCompat(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull Executor executor, @NonNull CameraCaptureSession.StateCallback stateCallback) {
        if (Build.VERSION.SDK_INT < 28) {
            this.f535a = new b(i, list, executor, stateCallback);
        } else {
            this.f535a = new a(i, list, executor, stateCallback);
        }
    }

    @RequiresApi(24)
    public static List<OutputConfigurationCompat> a(@NonNull List<OutputConfiguration> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (OutputConfiguration outputConfiguration : list) {
            arrayList.add(OutputConfigurationCompat.wrap(outputConfiguration));
        }
        return arrayList;
    }

    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static List<OutputConfiguration> transformFromCompat(@NonNull List<OutputConfigurationCompat> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (OutputConfigurationCompat outputConfigurationCompat : list) {
            arrayList.add((OutputConfiguration) outputConfigurationCompat.unwrap());
        }
        return arrayList;
    }

    @Nullable
    public static SessionConfigurationCompat wrap(@Nullable Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 28) {
            return new SessionConfigurationCompat(new a(obj));
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SessionConfigurationCompat) {
            return this.f535a.equals(((SessionConfigurationCompat) obj).f535a);
        }
        return false;
    }

    public Executor getExecutor() {
        return this.f535a.getExecutor();
    }

    public InputConfigurationCompat getInputConfiguration() {
        return this.f535a.a();
    }

    public List<OutputConfigurationCompat> getOutputConfigurations() {
        return this.f535a.e();
    }

    public CaptureRequest getSessionParameters() {
        return this.f535a.d();
    }

    public int getSessionType() {
        return this.f535a.g();
    }

    public CameraCaptureSession.StateCallback getStateCallback() {
        return this.f535a.b();
    }

    public int hashCode() {
        return this.f535a.hashCode();
    }

    public void setInputConfiguration(@NonNull InputConfigurationCompat inputConfigurationCompat) {
        this.f535a.c(inputConfigurationCompat);
    }

    public void setSessionParameters(CaptureRequest captureRequest) {
        this.f535a.h(captureRequest);
    }

    @Nullable
    public Object unwrap() {
        return this.f535a.f();
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static final class a implements c {

        /* renamed from: a  reason: collision with root package name */
        public final SessionConfiguration f536a;
        public final List<OutputConfigurationCompat> b;

        public a(@NonNull Object obj) {
            SessionConfiguration sessionConfiguration = (SessionConfiguration) obj;
            this.f536a = sessionConfiguration;
            this.b = Collections.unmodifiableList(SessionConfigurationCompat.a(sessionConfiguration.getOutputConfigurations()));
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public InputConfigurationCompat a() {
            return InputConfigurationCompat.wrap(this.f536a.getInputConfiguration());
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public CameraCaptureSession.StateCallback b() {
            return this.f536a.getStateCallback();
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public void c(@NonNull InputConfigurationCompat inputConfigurationCompat) {
            this.f536a.setInputConfiguration((InputConfiguration) inputConfigurationCompat.unwrap());
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public CaptureRequest d() {
            return this.f536a.getSessionParameters();
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public List<OutputConfigurationCompat> e() {
            return this.b;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof a) {
                return Objects.equals(this.f536a, ((a) obj).f536a);
            }
            return false;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        @Nullable
        public Object f() {
            return this.f536a;
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public int g() {
            return this.f536a.getSessionType();
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public Executor getExecutor() {
            return this.f536a.getExecutor();
        }

        @Override // androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat.c
        public void h(CaptureRequest captureRequest) {
            this.f536a.setSessionParameters(captureRequest);
        }

        public int hashCode() {
            return this.f536a.hashCode();
        }

        public a(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull Executor executor, @NonNull CameraCaptureSession.StateCallback stateCallback) {
            this(new SessionConfiguration(i, SessionConfigurationCompat.transformFromCompat(list), executor, stateCallback));
        }
    }

    public SessionConfigurationCompat(@NonNull c cVar) {
        this.f535a = cVar;
    }
}
