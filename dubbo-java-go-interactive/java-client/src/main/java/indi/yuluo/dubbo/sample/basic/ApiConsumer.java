package indi.yuluo.dubbo.sample.basic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.sample.hello.Helloworld;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ApiConsumer {

	public static void main(String[] args) throws InterruptedException, IOException {
		ReferenceConfig<IGreeter> ref = new ReferenceConfig<>();
		ref.setInterface(IGreeter.class);
		ref.setCheck(false);
		ref.setProtocol(CommonConstants.TRIPLE);
		ref.setLazy(true);
		ref.setTimeout(100000);

		DubboBootstrap.getInstance()
				.application(new ApplicationConfig("demo-consumer"))
				.registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
				.reference(ref).start();

		final IGreeter iGreeter = ref.get();

		System.out.println("dubbo ref started");
		Helloworld.HelloRequest req = Helloworld.HelloRequest.newBuilder().setName("laurence").build();
		try {
			final Helloworld.User reply = iGreeter.sayHello(req);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Reply:" + reply);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		System.in.read();
	}

}
