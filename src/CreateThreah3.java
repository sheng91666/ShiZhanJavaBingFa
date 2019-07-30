import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreateThreah3 implements Runnable{
    @Override
    public void run() {
        System.out.println("hello");
    }

    public static void main(String args[]){
//        new Thread(new CreateThreah3()).start();

        List<BigDecimal> bigDecimals = new ArrayList<>();
        bigDecimals.add(new BigDecimal(1));
        bigDecimals.add(new BigDecimal(2));
        bigDecimals.add(new BigDecimal(4));
        bigDecimals.add(new BigDecimal(5));

        BigDecimal a = new BigDecimal(0);
        for(BigDecimal bigDecimal : bigDecimals){
            a = a.add(bigDecimal);
        }
        System.out.println(a);
    }
}
