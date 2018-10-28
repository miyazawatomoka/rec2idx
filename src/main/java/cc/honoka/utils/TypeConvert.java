package cc.honoka.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TypeConvert {
    private static ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN);
    public static int btyes2int(byte[] bytes, int offset) {
        return buffer.clear().put(bytes, offset, Integer.BYTES).flip().getInt();
    }

    public static byte[] int2bytes(int num) {
        return ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN).clear().putInt(num).array();
    }
}
