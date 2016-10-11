package please.change.me.simulator.outgoing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.thread.QueuedThreadPool;

/**
 * 毎回同じ結果を返却するHTTPサーバ。
 * HTTPメッセージ送信シミュレータのテストに使用する。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
public class DummyHttpServer {

    private static final int MAX_THREADS = 30;

    public static void main(String[] args) throws Exception {
        DummyHttpServer server = new DummyHttpServer(RET);
        server.start();
        server.join();
    }

    /** 返却する電文 */
    private static final String RET = "<?xml version=\"1.0\" encoding=\"Shift-JIS\" ?>\n" +
            "<response>\n" +
            "  <failureCode>0</failureCode>\n" +
            "  <userInfoId>0</userInfoId>\n" +
            "  <dataKbn></dataKbn>\n" +
            "</response>\n";

    /** ポート番号 */
    private static final int PORT = 10000;

    /** jettyサーバ*/
    private Server jetty;

    private final String responseBody;

    public DummyHttpServer(String responseBody) {
        this.responseBody = responseBody;
    }

    /**
     * HTTPサーバを起動する。
     * @throws Exception 予期しない例外
     */
    private void start() throws Exception {
        jetty = new Server(PORT);
        jetty.setThreadPool(new QueuedThreadPool(MAX_THREADS));
        jetty.setHandler(new XmlHandler());
        jetty.start();
    }

    private void join() throws InterruptedException {
        jetty.join();
    }

    private class XmlHandler extends AbstractHandler {

        @Override
        public void handle(String s, HttpServletRequest baseRequest,
                           HttpServletResponse response, int i)
                throws IOException, ServletException {

            response.setContentType("text/xml;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(responseBody);
            ((Request) baseRequest).setHandled(true);
        }
    }
}
