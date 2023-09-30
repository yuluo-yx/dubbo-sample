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
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface Greeter {

    String JAVA_SERVICE_NAME = "org.apache.dubbo.hello.Greeter";
    String SERVICE_NAME = "helloworld.Greeter";

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
    org.apache.dubbo.hello.User sayHello(org.apache.dubbo.hello.HelloRequest request);

    default CompletableFuture<org.apache.dubbo.hello.User> sayHelloAsync(org.apache.dubbo.hello.HelloRequest request){
        return CompletableFuture.completedFuture(sayHello(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void sayHello(org.apache.dubbo.hello.HelloRequest request, StreamObserver<org.apache.dubbo.hello.User> responseObserver){
        sayHelloAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }

        /**
         * <pre>
         *  java
         * </pre>
         */
    org.apache.dubbo.hello.HelloReply greet(org.apache.dubbo.hello.HelloRequest request);

    default CompletableFuture<org.apache.dubbo.hello.HelloReply> greetAsync(org.apache.dubbo.hello.HelloRequest request){
        return CompletableFuture.completedFuture(greet(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void greet(org.apache.dubbo.hello.HelloRequest request, StreamObserver<org.apache.dubbo.hello.HelloReply> responseObserver){
        greetAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }



        /**
         * <pre>
         *  Sends a greeting via stream
         * </pre>
         */
    StreamObserver<org.apache.dubbo.hello.HelloRequest> sayHelloStream(StreamObserver<org.apache.dubbo.hello.User> responseObserver);



}
