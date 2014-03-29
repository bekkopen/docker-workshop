import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class HelloWorld {

    public static void main(String[] args) {
        final Jedis jedis;
        try {
            if (args.length == 2) {
                jedis = initRedis(args);
                setupRoutes(jedis);
            }
            else {
                System.out.println("No args given");
                setupHelloWorldPage();
            }
        }
        catch (JedisConnectionException e) {
            System.out.println("Couldn't connect to Redis");
            setupHelloWorldPage();
        }
    }

    private static void setupRoutes(final Jedis jedis) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return getIndex(jedis);
            }
        });

        post(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                jedis.set(request.queryParams("key"), request.queryParams("value"));
                return getIndex(jedis);
            }
        });
    }

    private static Jedis initRedis(String[] args) {
        Jedis jedis;
        System.out.println("arg0: " + args[0]);
        System.out.println("arg1: " + args[1]);

        jedis = new Jedis(args[0], Integer.valueOf(args[1]));
        jedis.set("test", " ");
        String reply = jedis.get("test");
        jedis.del("test");
        if (reply.equals(" ")) {
            System.out.println("Hooray! Connected to Redis");
        }
        else {
            System.out.println("Sort of connected to Redis, but somethings not quite right");
        }

        return jedis;
    }

    private static String getIndex(Jedis jedis) {
        Set<String> keys = jedis.keys("*");
        String messages = "";
        for(String key : keys){
            String value = jedis.get(key);
            messages += "<li>"+ key +": "+ value +"</li>";
        }
        return indexHtml.replace("{{messages}}", messages);
    }

    private static void setupHelloWorldPage() {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }

    static String indexHtml =
        "<html>" +
        "<form action=\".\" method=\"post\">" +
            "<input type=\"text\" placeholder=\"key\" name=\"key\" style=\"font-size: 25pt\" autofocus>" +
            "<input type=\"text\" placeholder=\"value\" name=\"value\" style=\"font-size: 25pt\">" +
            "<input type=\"submit\" style=\"visibility: hidden\"/>"+
        "</form>" +
        "<h1>Keys and values:</h1>" +
        "<ul>{{messages}}</ul>" +
        "</html>";
}