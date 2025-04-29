import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankSimulationExecutor {
    private static final Logger logger = LogManager.getLogger(BankSimulationExecutor.class);
    public static void main(String[] args) {
        logger.info("🚀 Starting EXECUTOR simulation");
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new Customer(account1, account2, 100, 10));
        exec.execute(new Customer(account2, account1, 100, 10));
        exec.shutdown();
        try {
            if (!exec.awaitTermination(1, TimeUnit.MINUTES)) {
                logger.warn("Executor didn’t finish in time");
            }
        } catch (InterruptedException e) {
            logger.error("Executor interrupted", e);
            Thread.currentThread().interrupt();
        }
        logger.info(
            "🏁 EXECUTOR simulation complete. Final balances: account1={}, account2={}",
            account1.getBalance(),
            account2.getBalance()
        );
    }
}