import model.CalculateResult;
import model.DownloadResult;

import java.util.concurrent.Callable;

public class CalculateCallable implements Callable<CalculateResult> {

    private Calculate calculate;
    private DownloadResult downloadResult;

    public CalculateCallable(Calculate calculate, DownloadResult downloadResult) {
        this.calculate = calculate;
        this.downloadResult = downloadResult;
    }

    @Override
    public CalculateResult call() throws Exception {
        return calculate.calculate(downloadResult);
    }
}
