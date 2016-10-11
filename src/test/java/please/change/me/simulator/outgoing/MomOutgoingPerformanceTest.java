package please.change.me.simulator.outgoing;

import nablarch.fw.launcher.Main;

import org.junit.Ignore;

/**
 * MOMメッセージ送信シミュレータの性能測定用テスト。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
@Ignore("性能測定用のテストクラス（mainメソッドのみ）のため自動テスト対象外とする")
public class MomOutgoingPerformanceTest {

    /**
     * 指定された回数、メッセージを送信する。
     * MOMメッセージ受信シミュレータを起動した状態で、本クラスを起動する。
     *
     * @param args 使用しない
     */
    public static void main(String[] args) {
        System.setProperty("requests-to-send", "mom-RequestsToSend-104.csv");
        String[] programArgs = {
                "-diConfig", "outgoing-simulator-component-configuration.xml",
                "-requestPath", "",
                "-userId", "xxx",
                "-sendCount", "10000"};
        Main.main(programArgs);
    }
}
