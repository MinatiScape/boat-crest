package kotlin.enums;

import java.lang.Enum;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.markers.KMappedMarker;
@SinceKotlin(version = "1.8")
@ExperimentalStdlibApi
/* loaded from: classes12.dex */
public interface EnumEntries<E extends Enum<E>> extends List<E>, KMappedMarker {
}
