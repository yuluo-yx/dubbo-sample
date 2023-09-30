package indi.yuluo.dubbo.sample.basic;

import org.apache.dubbo.sample.hello.Helloworld;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class IGreeter1Impl implements IGreeter {

	@Override
	public Helloworld.User sayHello(Helloworld.HelloRequest request) {
		System.out.println("receiv: " + request);
		Helloworld.User usr = Helloworld.User.newBuilder()
				.setName("你好 " + request.getName())
				.setAge(18)
				.setId("0507").build();
		return usr;
	}

}
