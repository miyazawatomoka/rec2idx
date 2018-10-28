package cc.honoka.utils;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import cc.honoka.utils.TypeConvert;
import cc.honoka.utils.IdentifierUtils;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TypeConvertTest {
    @Test
    public void testBtyes2long() {
        byte[] bytes= {(byte)0x0A, (byte)0x23, (byte)0xD7, (byte)0xCE};
        assertEquals(TypeConvert.btyes2int(bytes, 0 ), IdentifierUtils.MAGIC);
    }

    @Test
    public void testInt2Bytes() {
        byte[] bytes= {(byte)0x0A, (byte)0x23, (byte)0xD7, (byte)0xCE};
        byte[] result = TypeConvert.int2bytes(IdentifierUtils.MAGIC);
        assertArrayEquals(bytes, result);
    }
} 
