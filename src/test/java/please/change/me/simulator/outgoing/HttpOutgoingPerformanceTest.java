package please.change.me.simulator.outgoing;

import nablarch.fw.launcher.CommandLine;
import nablarch.fw.launcher.Main;

import org.junit.Ignore;

import please.change.me.simulator.outgoing.action.OutgoingSimulatorAction;

/**
 * HTTPメッセージ送信シミュレータの性能測定用テスト。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
@Ignore("性能測定用のテストクラス（mainメソッドのみ）のため自動テスト対象外とする")
public class HttpOutgoingPerformanceTest {

    private static final String SEND_COUNT = String.valueOf(10000);

    public static void main(String[] args) throws Exception {
        System.out.println("相手先HTTPサーバを起動して、何かキーを入力してください。");
        System.in.read();

        System.setProperty("requests-to-send", "http-RequestsToSend-102.csv");
        long start = System.currentTimeMillis();
        System.out.println("start Simulator.");
        CommandLine commandLine = new CommandLine(
                "-diConfig",
                "outgoing-http-simulator-component-configuration.xml",
                "-requestPath", "",
                "-userId", "xxx",
                "-" + OutgoingSimulatorAction.SEND_COUNT_OPTION, SEND_COUNT
        );
        Main.execute(commandLine);
        long end = System.currentTimeMillis();
        System.out.println("time=" + (end - start));
        System.exit(0);
    }
}
