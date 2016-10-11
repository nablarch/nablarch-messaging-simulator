package please.change.me.simulator.incoming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpRequestHandler;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link HttpRequest}のテスト。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
public class HttpRequesterTest {

    private static final int PORT = 7373;
    private static HttpServer server = new HttpServer();

    private HttpRequester target;

    private URL url;

    @Before
    public void setUpUrl() throws Exception {
        url = new URL("http", "localhost", PORT, "/nabla_app/path/to/somewhere/Greeting");
    }

    @BeforeClass
    public static void setUpServer() {

        server.setServletContextPath("/nabla_app")
              .setWarBasePath("file://")
              .setPort(PORT)
              .addHandler("/path/to/somewhere/Greeting", new HttpRequestHandler() {
                  public HttpResponse handle(HttpRequest req, ExecutionContext ctx) {
                      return new HttpResponse().write("hoge");
                  }
              })
              .start();

    }

    @After
    public void terminate() {
        target.terminate();
    }

    @Test
    public void testRequestWithResult() throws MalformedURLException {

        target = new HttpRequester(100, 10);

        List<String> result = target.requestWithResult(url);
        for (String e : result) {
            assertThat(e, is("hoge"));
        }

    }


    @Test
    public void testRequestWithoutResult() throws MalformedURLException {
        target = new HttpRequester(100, 10);
        target.requestWithoutResult(url);
        target.requestWithoutResult(url);

    }
}
