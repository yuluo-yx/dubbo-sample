package org.apache.dubbo;

import org.apache.dubbo.hello.DubboGreeterTriple;
import org.apache.dubbo.hello.HelloReply;
import org.apache.dubbo.hello.HelloRequest;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class GreeterImpl extends DubboGreeterTriple.GreeterImplBase {
	@Override
	public HelloReply greet(HelloRequest request) {
		return HelloReply.newBuilder()
				.setMessage("Hello," + request.getName() + "!")
				.build();
	}
}
