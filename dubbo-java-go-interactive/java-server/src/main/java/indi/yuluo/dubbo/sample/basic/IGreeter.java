package indi.yuluo.dubbo.sample.basic;

import org.apache.dubbo.sample.hello.Helloworld;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IGreeter {
	/**
	 * <pre>
	 *  Sends a greeting
	 * </pre>
	 */
	Helloworld.User sayHello(Helloworld.HelloRequest request);

}
