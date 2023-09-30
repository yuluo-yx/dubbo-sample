package org.apache.dubbo;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.hello.Greeter;
import org.apache.dubbo.hello.HelloReply;
import org.apache.dubbo.hello.HelloRequest;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class MyDubboClient {

	public static void main(String[] args) {
		DubboBootstrap bootstrap = DubboBootstrap.getInstance();
		ReferenceConfig<Greeter> ref = new ReferenceConfig<>();
		ref.setInterface(Greeter.class);
		ref.setProtocol(CommonConstants.TRIPLE);
		ref.setProxy(CommonConstants.NATIVE_STUB);
		ref.setTimeout(3000);
		bootstrap.application(new ApplicationConfig("tri-stub-client"))
				.registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
				.reference(ref)
				.start();

		Greeter greeter = ref.get();
//		HelloRequest request = HelloRequest.newBuilder().setName("Demo").build();
//		HelloReply reply = greeter.greet(request);
//		System.out.println("Received reply:" + reply);

		System.out.println("-------------------- 测试 java client 调用 go server ------------------------------");

		HelloRequest yuluo = HelloRequest.newBuilder().setName("yuluo").build();
		HelloReply helloReply = greeter.greet(yuluo);
		System.out.println(helloReply);
	}

}
