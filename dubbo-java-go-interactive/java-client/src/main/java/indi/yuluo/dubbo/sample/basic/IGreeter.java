package indi.yuluo.dubbo.sample.basic;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

import org.apache.dubbo.sample.hello.Helloworld;

public interface IGreeter {
	/**
	 * <pre>
	 *  Sends a greeting
	 * </pre>
	 */
	Helloworld.User sayHello(Helloworld.HelloRequest request);

}
