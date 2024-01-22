package io.shipbook.shipbooksdk.Models;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0080\b\u0018\u0000 #2\u00020\u0001:\u0001#B)\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b!\u0010\"J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\t\u001a\u00020\bHÆ\u0003J3\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\r\u001a\u00020\bHÆ\u0001J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\bHÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003R\u0019\u0010\n\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u001b\u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u0019\u0010\r\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "component3", "", "component4", "declaringClass", "methodName", "fileName", "lineNumber", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getDeclaringClass", "()Ljava/lang/String;", "b", "getMethodName", c.f10260a, "getFileName", "d", "I", "getLineNumber", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class StackTraceElement implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14038a;
    @NotNull
    public final String b;
    @Nullable
    public final String c;
    public final int d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/StackTraceElement$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final StackTraceElement create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String declaringClass = json.getString("declaringClass");
            String methodName = json.getString("methodName");
            String optString = json.optString("fileName");
            int i = json.getInt("lineNumber");
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
            Intrinsics.checkExpressionValueIsNotNull(methodName, "methodName");
            return new StackTraceElement(declaringClass, methodName, optString, i);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public StackTraceElement(@NotNull String declaringClass, @NotNull String methodName, @Nullable String str, int i) {
        Intrinsics.checkParameterIsNotNull(declaringClass, "declaringClass");
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        this.f14038a = declaringClass;
        this.b = methodName;
        this.c = str;
        this.d = i;
    }

    @NotNull
    public static /* synthetic */ StackTraceElement copy$default(StackTraceElement stackTraceElement, String str, String str2, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = stackTraceElement.f14038a;
        }
        if ((i2 & 2) != 0) {
            str2 = stackTraceElement.b;
        }
        if ((i2 & 4) != 0) {
            str3 = stackTraceElement.c;
        }
        if ((i2 & 8) != 0) {
            i = stackTraceElement.d;
        }
        return stackTraceElement.copy(str, str2, str3, i);
    }

    @NotNull
    public final String component1() {
        return this.f14038a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final StackTraceElement copy(@NotNull String declaringClass, @NotNull String methodName, @Nullable String str, int i) {
        Intrinsics.checkParameterIsNotNull(declaringClass, "declaringClass");
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        return new StackTraceElement(declaringClass, methodName, str, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof StackTraceElement) {
                StackTraceElement stackTraceElement = (StackTraceElement) obj;
                if (Intrinsics.areEqual(this.f14038a, stackTraceElement.f14038a) && Intrinsics.areEqual(this.b, stackTraceElement.b) && Intrinsics.areEqual(this.c, stackTraceElement.c)) {
                    if (this.d == stackTraceElement.d) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getDeclaringClass() {
        return this.f14038a;
    }

    @Nullable
    public final String getFileName() {
        return this.c;
    }

    public final int getLineNumber() {
        return this.d;
    }

    @NotNull
    public final String getMethodName() {
        return this.b;
    }

    public int hashCode() {
        String str = this.f14038a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        return ((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.d;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("declaringClass", this.f14038a);
        jSONObject.put("methodName", this.b);
        jSONObject.putOpt("fileName", this.c);
        jSONObject.put("lineNumber", this.d);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "StackTraceElement(declaringClass=" + this.f14038a + ", methodName=" + this.b + ", fileName=" + this.c + ", lineNumber=" + this.d + ")";
    }
}
