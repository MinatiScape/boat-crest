package io.shipbook.shipbooksdk.Models;

import com.coveiot.coveaccess.constants.ErrorConstants;
import com.google.common.net.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Severity;", "", "", "value", "I", "getValue", "()I", "<init>", "(Ljava/lang/String;II)V", "Companion", "Off", ErrorConstants.GENERIC_ERROR, HttpHeaders.WARNING, "Info", "Debug", "Verbose", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public enum Severity {
    Off(0),
    Error(6),
    Warning(5),
    Info(4),
    Debug(3),
    Verbose(2);
    
    public static final Companion Companion = new Companion(null);
    private static final Map<Integer, Severity> map;
    private final int value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Severity$Companion;", "", "", "type", "Lio/shipbook/shipbooksdk/Models/Severity;", "fromInt", "", "map", "Ljava/util/Map;", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final Severity fromInt(int i) {
            Severity severity = (Severity) Severity.map.get(Integer.valueOf(i));
            return severity != null ? severity : Severity.Verbose;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Severity[] values = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(h.coerceAtLeast(r.mapCapacity(values.length), 16));
        for (Severity severity : values) {
            linkedHashMap.put(Integer.valueOf(severity.value), severity);
        }
        map = linkedHashMap;
    }

    Severity(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
