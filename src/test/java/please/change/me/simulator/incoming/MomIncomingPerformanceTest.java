package please.change.me.simulator.incoming;

import org.junit.Ignore;

import nablarch.fw.launcher.Main;

/**
 * MOMメッセージ受信シミュレータの性能測定用テスト。
 *
 * @author T.Kawasaki
 * @since 1.4.2
 */
@Ignore("性能測定用のテストクラス（mainメソッドのみ）のため自動テスト対象外とする")
public class MomIncomingPerformanceTest {

    /**
     * メッセージ応答を開始する。
     *
     * @param args 使用しない。
     */
    public static void main(String[] args) {
        String[] programArgs = {
                "-diConfig", "incoming-mom-simulator-component-configuration.xml",
                "-requestPath", "xxx",
                "-userId", "xxx"
        };
        Main.main(programArgs);
    }
}

