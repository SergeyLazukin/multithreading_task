import model.CalculateResult;
import model.DownloadResult;

import java.util.Random;

public class Calculate {

    public CalculateResult calculate(DownloadResult downloadResult) {
        Random r = new Random();
        CalculateResult result = new CalculateResult();
        result.id = downloadResult.id;

        int check;
        do {
            check = r.nextInt(2000000);
        } while (!downloadResult.check(check));

        result.found = true;

        return result;
    }
}
