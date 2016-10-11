package please.change.me.simulator.incoming;

import nablarch.test.RepositoryInitializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import please.change.me.simulator.incoming.http.launcher.HttpIncomingSimulatorLauncher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * HTTPメッセージ受信シミュレータの疎通確認、性能測定用テスト。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
@Ignore("性能測定用のテストクラスのため自動テスト対象外とする")
public class HttpIncomingPerformanceTest {

    private static final int HTTP_SERVER_PORT = 7373;

    private HttpRequester requester;

    private static Properties systemPropOrig;

    @BeforeClass
    public static void startServer() {
        systemPropOrig = System.getProperties();
        System.setProperty("port", String.valueOf(HTTP_SERVER_PORT));
        // シミュレータを起動
        HttpIncomingSimulatorLauncher.main();
    }


    @After
    public void tearDown() {
        if (requester != null) {
            requester.terminate();
        }
    }

    @AfterClass
    public static void terminate() {
        RepositoryInitializer.initializeDefaultRepository();
        System.setProperties(systemPropOrig);
    }


    @Test
    public void test() throws MalformedURLException {

        // 送信用クラス
        requester = new HttpRequester(100);
        List<String> ret = requester.requestWithResult(new URL("http", "localhost", HTTP_SERVER_PORT, "/hoge"));
        System.out.println("ret = " + ret);
    }

    @Test
    public void test2() throws MalformedURLException, InterruptedException {

        // 送信用クラス
        requester = new HttpRequester(100, 50);
        long mills = requester.requestWithoutResult(new URL("http", "localhost", HTTP_SERVER_PORT, "/hoge"));
        System.out.println(mills);
    }
}

