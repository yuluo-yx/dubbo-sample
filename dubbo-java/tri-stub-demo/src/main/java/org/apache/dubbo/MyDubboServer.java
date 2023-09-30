package org.apache.dubbo;

import java.io.IOException;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.hello.Greeter;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class MyDubboServer {

	public static void main(String[] args) throws IOException {
		ServiceConfig<Greeter> service = new ServiceConfig<>();
		service.setInterface(Greeter.class);
		service.setRef(new GreeterImpl());

		DubboBootstrap bootstrap = DubboBootstrap.getInstance();
		bootstrap.application(new ApplicationConfig("tri-stub-server"))
				.registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
				.protocol(new ProtocolConfig(CommonConstants.TRIPLE, 50051))
				.service(service)
				.start();
		System.out.println("Dubbo triple stub server started");
		System.in.read();
	}

}
