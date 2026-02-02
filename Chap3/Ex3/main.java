import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {

        CompletableFuture
                .supplyAsync(() -> {

                    int[] arr = {1, 2, 5, 3, 100};
                    return arr;
                })

                .thenApply(arr -> {

                    List<Integer> oddNumbers = Arrays.stream(arr)
                            .filter(x -> x % 2 != 0)
                            .sorted()
                            .boxed()
                            .collect(Collectors.toList());

                    return oddNumbers;
                })

                .thenApply(result -> {

                    return "Ket qua la: " + result;
                })

                .thenAccept(finalString -> {

                    System.out.println(finalString);
                });
    }
}

