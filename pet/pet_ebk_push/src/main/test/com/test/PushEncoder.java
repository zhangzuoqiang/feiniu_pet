package com.test;
/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */


import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/** 
 *  Encoder class that does nothing (to the already encoded data). 
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class PushEncoder implements ProtocolEncoder {
	private final Charset charset;
    
    /**
     * 构造依赖
     * @param charset
     */
    public PushEncoder(Charset charset){
        this.charset = charset;
    }
    
    /**
     * 编码规则
     */
    @Override
    public void encode(IoSession session, Object obj, ProtocolEncoderOutput out)
            throws Exception {
        CharsetEncoder encoder = charset.newEncoder();
        IoBuffer io = IoBuffer.allocate(100).setAutoExpand(true);   
        io.putString(obj.toString(), encoder);   
        io.put((byte)'\r');   
        io.put((byte)'\n');   
        io.flip();   
        out.write(io);   
    }

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	} 
}
