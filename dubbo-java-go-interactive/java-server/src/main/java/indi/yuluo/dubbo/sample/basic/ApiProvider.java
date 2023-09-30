package indi.yuluo.dubbo.sample.basic;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ApiProvider {
	public static void main(String[] args) {
		ServiceConfig<IGreeter> service = new ServiceConfig<>();
		service.setInterface(IGreeter.class);
		service.setRef(new IGreeter1Impl());
		service.setProtocol(new ProtocolConfig(CommonConstants.TRIPLE, 50051));
		System.out.println("dubbo service started");

		DubboBootstrap.getInstance()
				.application(new ApplicationConfig("demo-provider"))
				.registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
				.service(service)
				.start()
				.await();
	}
}
