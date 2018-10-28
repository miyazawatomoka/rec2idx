package cc.honoka.utils;

public class IdentifierUtils {
    private static final int FLAG_MOVE_BIT = 29;
    public static final int WITH_OUT_MAGIC_CODE = 0, FIRST_MAGIC_CODE = 1, OTHERS_MAGIC_CODE = 2, WIHT_MAGIC_CODE = 3;
    public static final int MAGIC = 0xced7230a;
    public static final byte[] MAGIC_BYTES = TypeConvert.int2bytes(MAGIC);


    private static int getFlagMask(int code) {
        return code << FLAG_MOVE_BIT;
    }


    public static int getLength(int identifier) {
        return (identifier << 4) >>> 4;
    }

    public static int getCode(byte[] identifierBytes, int offset) {
        return identifierBytes[offset + 3] >>> 5;
    }
}
