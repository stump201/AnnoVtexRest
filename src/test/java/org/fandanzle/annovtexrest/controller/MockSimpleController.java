package org.fandanzle.annovtexrest.controller;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.fandanzle.annovtexrest.MimeTypes;
import org.fandanzle.annovtexrest.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by alexb on 28/10/2016.
 */

@Controller(uri = "/simple")
public class MockSimpleController {

    /**
     * USERS GENERIC CLASS
     * Param types must be of type primitive is there is a primitive
     *
     * @return
     */
    @RequestMapping(
            uri = "/all",
            method = RequestMethods.GET,
            consumes = MimeTypes.APPLICATION_HTML,
            produces = MimeTypes.APPLICATION_JSON,
            description = "An endpoint to get all users"
    )
    public List<String> tesjiot(
            Vertx vertx,
            HttpServerRequest request,
            HttpServerResponse response,
            RoutingContext context
    ){

        List<String> arr = Arrays.asList("111111","222222","3333","44444444","efoihwefouwehf","wpifnrwiofn");

        return arr;

    }

    /**
     * USERS GENERIC CLASS
     * Param types must be of type primitive is there is a primitive
     *
     * @return
     */
    @RequestMapping(
            uri = "/users/:id1/:id2/:id3",
            method = RequestMethods.GET,
            consumes = MimeTypes.APPLICATION_HTML,
            produces = MimeTypes.APPLICATION_JSON,
            description = "An endpoint to get all users"
    )
    public List<String> test(
            Vertx vertx,
            HttpServerRequest request,
            HttpServerResponse response,
            RoutingContext context,
            @PathParam(name = "id1") Integer id1,
            @PathParam(name = "id2") String id2,
            @PathParam(name = "id3") String id3
    ){

        System.out.println("id1 : " + id1);
        System.out.println("id1 : " + id2);
        System.out.println("id1 : " + id3);

        List<String> arr = Arrays.asList(
                id1.toString(),
                id2,
                id3);

        return arr;

    }
}