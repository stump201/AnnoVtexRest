package org.fandanzle.annovtexrest.controller;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import org.apache.log4j.Logger;
import org.fandanzle.annovtexrest.AnnoVtexRest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.core.http.*;
import io.vertx.test.core.VertxTestBase;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 *
 * Created by alexb on 06/07/15.
 *
 */
@RunWith(VertxUnitRunner.class)
public class SimpleMockTest  {

    private Logger logger = Logger.getLogger(SimpleMockTest.class);

    private Vertx vertx;

    protected HttpServer server;
    protected HttpClient client;
    protected AnnoVtexRest annoVtexRest;
    protected Router router;

    /**
     *
     * Setup for the test
     * @throws Exception
     */
    @Test
    public void e(TestContext context)  {

        final Async async = context.async();

        vertx = Vertx.vertx();

        try {

            annoVtexRest = new AnnoVtexRest(vertx);
            annoVtexRest.build("org.fandanzle.annovtexrest.controller");
            router = Router.router(vertx);

            System.out.println( Json.encode( annoVtexRest.getRouter().getRoutes())  );

            for(org.fandanzle.annovtexrest.entity.Route rr : annoVtexRest.getRoutes()){

                System.out.println("Route URI : " + rr.getUri() );
                System.out.println("Route Method : " + rr.getMethod() );
                System.out.println("Route Description : " + rr.getDescription() );
                System.out.println("Route Consumes : " + rr.getConsumes());
                System.out.println("Route Produces : " + rr.getProduces());
                System.out.println("Route Envoked Clazz : " + rr.getInvokeClazz() );
                System.out.println("Route Envoked Method : " + rr.getInvokeMethod() );

            }

            server =  vertx.createHttpServer().requestHandler(annoVtexRest.getRouter()::accept).listen(8000);
            client = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(8000));

        }catch (Exception e){
            e.printStackTrace();
            async.complete();
        }

        async.complete();

    }

    /**

    @Test
    public void testSimpleMockTest(TestContext context) throws Exception{


        final Async async = context.async();

        Object[] obj = {  1 , "wwoifhweoif" , true }; // for method1()

        System.out.println(Json.encode(obj));
        // Object[] obj={"hello"}; for method1(String str)
        // Object[] obj={"hello",1}; for method1(String str,int number)
        // Step 2) Create a class array which will hold the signature of the
        // method being called.
        Class<?> params[] = new Class[obj.length];
        for (int i = 0; i < obj.length; i++) {

            System.out.println("VALUE : " + obj[i]);
            System.out.println("CLAZZ : " + obj[i].getClass().getCanonicalName());
            System.out.println("IS PRIMITIVE : " + obj[i].getClass().isPrimitive());

            if (obj[i] instanceof Integer) {
                params[i] = Integer.class;
            }
            if (obj[i] instanceof Boolean) {
                params[i] = Boolean.class;
            }if (obj[i] instanceof String) {
                params[i] = String.class;
            }else {
                throw new Exception("INVALID TYPE ***********");
            }
        }

        try {
            String methoName = "test"; // methodname to be invoked
            String className = MockSimpleController.class.getName();// Class name
            Class<?> cls = Class.forName(className);
            Object _instance = cls.newInstance();
            Method myMethod = cls.getDeclaredMethod(methoName, params);
            System.out.println( myMethod.invoke(_instance, obj) );
        }catch (Exception e){
            e.printStackTrace();
        }

        async.complete();

    }


    @Test
    public void testSimpleMockComplex(TestContext context) throws Exception{

        vertx = Vertx.vertx();

        final Async async = context.async();

        Object[] obj = {  1 , "wwoifhweoif" , true , new Date()}; // for method1()

        System.out.println(Json.encode(obj));
        // Object[] obj={"hello"}; for method1(String str)
        // Object[] obj={"hello",1}; for method1(String str,int number)
        // Step 2) Create a class array which will hold the signature of the
        // method being called.
        Class<?> params[] = new Class[obj.length];
        for (int i = 0; i < obj.length; i++) {

            System.out.println("VALUE : " + obj[i]);
            System.out.println("CLAZZ : " + obj[i].getClass().getCanonicalName());
            System.out.println("IS PRIMITIVE : " + obj[i].getClass().isPrimitive());

            if (obj[i] instanceof Date) {
                params[i] = Date.class;
            }
            else if (obj[i] instanceof Integer) {
                params[i] = Integer.class;
            }
            else if (obj[i] instanceof Boolean) {
                params[i] = Boolean.class;
            }else if (obj[i] instanceof String) {
                params[i] = String.class;
            }else {
                throw new Exception("INVALID TYPE ***********");
            }
        }

        try {
            String methoName = "advanced"; // methodname to be invoked
            String className = MockSimpleController.class.getName();// Class name
            Class<?> cls = Class.forName(className);
            Object _instance = cls.newInstance();
            Method myMethod = cls.getDeclaredMethod(methoName, params);
            System.out.println( myMethod.invoke(_instance, obj) );
        }catch (Exception e){
            e.printStackTrace();
        }

        async.complete();

    }

    **/
}