package com.zhang.practice.netty.chapter4;

import com.zhang.practice.netty.chapter4.handler.AbsIntegerEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 * @author : zzh
 * create at:  2020/4/9
 * @description:
 */
public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(channel.writeOutbound(buf));
        assertTrue(channel.finish());
        // read bytes
        for (int i = 0; i < 10; i++) {
            int value = (int) channel.readOutbound();
            System.out.println("value = " + value);
            assertEquals(i, value);
        }
        assertNull(channel.readOutbound());

    }
}
