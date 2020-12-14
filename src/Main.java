import model.CalculateResult;
import model.DownloadResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        Calendar start = Calendar.getInstance();

        int finishCounter = 0;
        Download d = new Download();
        Calculate c = new Calculate();

        List<Future<DownloadResult>> futureDownloadResultList = new ArrayList<>();
        ExecutorService downloadService = Executors.newFixedThreadPool(100);

        for(int i = 0; i < 1000; i++) {
            DownloadCallable task = new DownloadCallable(d, i);
            Future<DownloadResult> futureResult = downloadService.submit(task);
            futureDownloadResultList.add(futureResult);
        }

        List<Future<CalculateResult>> futureCalculateResultList = new ArrayList<>();
        ExecutorService calculateService = Executors.newFixedThreadPool(100);

        for(Future<DownloadResult> downloadResult : futureDownloadResultList) {
            CalculateCallable task = new CalculateCallable(c, downloadResult.get());
            Future<CalculateResult> futureResult = calculateService.submit(task);
            futureCalculateResultList.add(futureResult);
        }

        for(Future<CalculateResult> calculateResult : futureCalculateResultList) {
            if(calculateResult.get().found) {
                finishCounter++;
            }
        }

        System.out.println("Total success checks: " + finishCounter);

        Calendar stop = Calendar.getInstance();

        System.out.println("Total time: " + (stop.getTimeInMillis() - start.getTimeInMillis()) + " ms");

    }
}
