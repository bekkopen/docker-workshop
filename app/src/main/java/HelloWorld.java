import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class HelloWorld {

    public static void main(String[] args) {

        final Jedis jedis;
        try {
            if (args.length == 2) {
                jedis = new Jedis(args[0], Integer.valueOf(args[1]));
                jedis.set("testing_the_connection", "");

                System.out.println("Hooray! Connected to Redis");

                get(new Route("/") {
                    @Override
                    public Object handle(Request request, Response response) {
                        return getIndex(jedis);
                    }
                });

                post(new Route("/") {
                    @Override
                    public Object handle(Request request, Response response) {
                        jedis.set("message", request.queryParams("message"));
                        return getIndex(jedis);
                    }
                });
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

    private static String getIndex(Jedis jedis) {
        try {
            String html = readStream(HelloWorld.class.getResourceAsStream("/index.html"));
            return html.replace("{{message}}", jedis.get("message"));
        }
        catch (IOException e) {
            return e.getStackTrace().toString();
        }
    }

    private static void setupHelloWorldPage() {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }


    public static String readStream(InputStream s) throws IOException {
        InputStreamReader reader = new InputStreamReader(s, "UTF-8");

        BufferedReader bufferedReader = new BufferedReader(reader);
        String string;
        String output = "";
        do {
            string = bufferedReader.readLine();
            if (string != null) {
                output += string;
            }
        }
        while (string != null);
        s.close();

        return output;
    }
}