package cc.honoka.utils;

import cc.honoka.utils.IdentifierUtils;
import cc.honoka.utils.TypeConvert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IdentifierUtilsTest { 
    @Test
    public void testGetCode() {
        int num = 3 << 29;
        byte[] bytes = TypeConvert.int2bytes(num);
        assertEquals (3, IdentifierUtils.getCode(bytes, 0));
    }

    @Test
    public void testGetLength() {
        byte[] bytes = {0x07, 0x00, 0x00, 0x00};
        int num = TypeConvert.btyes2int(bytes, 0);
        assertEquals(7, IdentifierUtils.getLength(num));
    }

    @Test
    public void testMagicBytes() {
        byte[] magicBytes = {(byte)0x0A, (byte)0x23, (byte)0xD7, (byte)0xCE};
        assertArrayEquals(magicBytes, IdentifierUtils.MAGIC_BYTES);
    }

} 
