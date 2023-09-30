/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

    package org.apache.dubbo.hello;

import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.stub.BiStreamMethodHandler;
import org.apache.dubbo.rpc.stub.ServerStreamMethodHandler;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public final class DubboGreeterTriple {

    public static final String SERVICE_NAME = Greeter.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,Greeter.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,HelloWorldProto.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboGreeterTriple::newStub);
        StubSuppliers.addSupplier(Greeter.JAVA_SERVICE_NAME,  DubboGreeterTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(Greeter.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static Greeter newStub(Invoker<?> invoker) {
        return new GreeterStub((Invoker<Greeter>)invoker);
    }

    /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
    private static final StubMethodDescriptor sayHelloMethod = new StubMethodDescriptor("SayHello",
    org.apache.dubbo.hello.HelloRequest.class, org.apache.dubbo.hello.User.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.User::parseFrom);

    private static final StubMethodDescriptor sayHelloAsyncMethod = new StubMethodDescriptor("SayHello",
    org.apache.dubbo.hello.HelloRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.User::parseFrom);

    private static final StubMethodDescriptor sayHelloProxyAsyncMethod = new StubMethodDescriptor("SayHelloAsync",
    org.apache.dubbo.hello.HelloRequest.class, org.apache.dubbo.hello.User.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.User::parseFrom);

    /**
         * <pre>
         *  java
         * </pre>
         */
    private static final StubMethodDescriptor greetMethod = new StubMethodDescriptor("greet",
    org.apache.dubbo.hello.HelloRequest.class, org.apache.dubbo.hello.HelloReply.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.HelloReply::parseFrom);

    private static final StubMethodDescriptor greetAsyncMethod = new StubMethodDescriptor("greet",
    org.apache.dubbo.hello.HelloRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.HelloReply::parseFrom);

    private static final StubMethodDescriptor greetProxyAsyncMethod = new StubMethodDescriptor("greetAsync",
    org.apache.dubbo.hello.HelloRequest.class, org.apache.dubbo.hello.HelloReply.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.HelloReply::parseFrom);




    /**
         * <pre>
         *  Sends a greeting via stream
         * </pre>
         */
    private static final StubMethodDescriptor sayHelloStreamMethod = new StubMethodDescriptor("SayHelloStream",
    org.apache.dubbo.hello.HelloRequest.class, org.apache.dubbo.hello.User.class, serviceDescriptor, MethodDescriptor.RpcType.BI_STREAM,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), org.apache.dubbo.hello.HelloRequest::parseFrom,
    org.apache.dubbo.hello.User::parseFrom);

    public static class GreeterStub implements Greeter{
        private final Invoker<Greeter> invoker;

        public GreeterStub(Invoker<Greeter> invoker) {
            this.invoker = invoker;
        }

            /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        @Override
        public org.apache.dubbo.hello.User sayHello(org.apache.dubbo.hello.HelloRequest request){
            return StubInvocationUtil.unaryCall(invoker, sayHelloMethod, request);
        }

        public CompletableFuture<org.apache.dubbo.hello.User> sayHelloAsync(org.apache.dubbo.hello.HelloRequest request){
            return StubInvocationUtil.unaryCall(invoker, sayHelloAsyncMethod, request);
        }

            /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        @Override
        public void sayHello(org.apache.dubbo.hello.HelloRequest request, StreamObserver<org.apache.dubbo.hello.User> responseObserver){
            StubInvocationUtil.unaryCall(invoker, sayHelloMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  java
         * </pre>
         */
        @Override
        public org.apache.dubbo.hello.HelloReply greet(org.apache.dubbo.hello.HelloRequest request){
            return StubInvocationUtil.unaryCall(invoker, greetMethod, request);
        }

        public CompletableFuture<org.apache.dubbo.hello.HelloReply> greetAsync(org.apache.dubbo.hello.HelloRequest request){
            return StubInvocationUtil.unaryCall(invoker, greetAsyncMethod, request);
        }

            /**
         * <pre>
         *  java
         * </pre>
         */
        @Override
        public void greet(org.apache.dubbo.hello.HelloRequest request, StreamObserver<org.apache.dubbo.hello.HelloReply> responseObserver){
            StubInvocationUtil.unaryCall(invoker, greetMethod , request, responseObserver);
        }


            /**
         * <pre>
         *  Sends a greeting via stream
         * </pre>
         */
        @Override
        public StreamObserver<org.apache.dubbo.hello.HelloRequest> sayHelloStream(StreamObserver<org.apache.dubbo.hello.User> responseObserver){
            return StubInvocationUtil.biOrClientStreamCall(invoker, sayHelloStreamMethod , responseObserver);
        }

    }

    public static abstract class GreeterImplBase implements Greeter, ServerService<Greeter> {

        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public final Invoker<Greeter> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/SayHello" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/SayHelloAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/SayHelloStream" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/SayHelloStreamAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/greet" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/greetAsync" );

            BiConsumer<org.apache.dubbo.hello.HelloRequest, StreamObserver<org.apache.dubbo.hello.User>> sayHelloFunc = this::sayHello;
            handlers.put(sayHelloMethod.getMethodName(), new UnaryStubMethodHandler<>(sayHelloFunc));
            BiConsumer<org.apache.dubbo.hello.HelloRequest, StreamObserver<org.apache.dubbo.hello.User>> sayHelloAsyncFunc = syncToAsync(this::sayHello);
            handlers.put(sayHelloProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(sayHelloAsyncFunc));
            BiConsumer<org.apache.dubbo.hello.HelloRequest, StreamObserver<org.apache.dubbo.hello.HelloReply>> greetFunc = this::greet;
            handlers.put(greetMethod.getMethodName(), new UnaryStubMethodHandler<>(greetFunc));
            BiConsumer<org.apache.dubbo.hello.HelloRequest, StreamObserver<org.apache.dubbo.hello.HelloReply>> greetAsyncFunc = syncToAsync(this::greet);
            handlers.put(greetProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(greetAsyncFunc));



            handlers.put(sayHelloStreamMethod.getMethodName(), new BiStreamMethodHandler<>(this::sayHelloStream));

            return new StubInvoker<>(this, url, Greeter.class, handlers);
        }


        @Override
        public org.apache.dubbo.hello.User sayHello(org.apache.dubbo.hello.HelloRequest request){
            throw unimplementedMethodException(sayHelloMethod);
        }

        @Override
        public org.apache.dubbo.hello.HelloReply greet(org.apache.dubbo.hello.HelloRequest request){
            throw unimplementedMethodException(greetMethod);
        }



        @Override
        public StreamObserver<org.apache.dubbo.hello.HelloRequest> sayHelloStream(StreamObserver<org.apache.dubbo.hello.User> responseObserver){
            throw unimplementedMethodException(sayHelloStreamMethod);
        }


        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }

}
