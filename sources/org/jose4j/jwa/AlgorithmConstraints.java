package org.jose4j.jwa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jose4j.lang.InvalidAlgorithmException;
/* loaded from: classes13.dex */
public class AlgorithmConstraints {
    public static final AlgorithmConstraints ALLOW_ONLY_NONE;
    public static final AlgorithmConstraints DISALLOW_NONE;
    public static final AlgorithmConstraints NO_CONSTRAINTS;

    /* renamed from: a  reason: collision with root package name */
    public final ConstraintType f15508a;
    public final Set<String> b;

    /* loaded from: classes13.dex */
    public enum ConstraintType {
        WHITELIST,
        BLACKLIST,
        PERMIT,
        BLOCK
    }

    /* loaded from: classes13.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f15509a;

        static {
            int[] iArr = new int[ConstraintType.values().length];
            f15509a = iArr;
            try {
                iArr[ConstraintType.PERMIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15509a[ConstraintType.WHITELIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15509a[ConstraintType.BLOCK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15509a[ConstraintType.BLACKLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        ConstraintType constraintType = ConstraintType.BLOCK;
        NO_CONSTRAINTS = new AlgorithmConstraints(constraintType, new String[0]);
        DISALLOW_NONE = new AlgorithmConstraints(constraintType, "none");
        ALLOW_ONLY_NONE = new AlgorithmConstraints(ConstraintType.PERMIT, "none");
    }

    public AlgorithmConstraints(ConstraintType constraintType, String... strArr) {
        Objects.requireNonNull(constraintType, "ConstraintType cannot be null");
        this.f15508a = constraintType;
        this.b = new HashSet(Arrays.asList(strArr));
    }

    public void checkConstraint(String str) throws InvalidAlgorithmException {
        int i = a.f15509a[this.f15508a.ordinal()];
        if (i != 1 && i != 2) {
            if ((i == 3 || i == 4) && this.b.contains(str)) {
                throw new InvalidAlgorithmException("'" + str + "' is a blocked algorithm.");
            }
        } else if (this.b.contains(str)) {
        } else {
            throw new InvalidAlgorithmException("'" + str + "' is not a permitted algorithm.");
        }
    }
}
