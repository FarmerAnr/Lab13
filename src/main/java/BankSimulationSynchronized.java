import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankSimulationSynchronized {
    private static final Logger logger = LogManager.getLogger(BankSimulationSynchronized.class);
    public static void main(String[] args) {
        logger.info("🔐 Starting SYNCHRONIZED simulation");
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);
        Thread t1 = new Thread(new SynchronizedCustomer(account1, account2, 100, 10));
        Thread t2 = new Thread(new SynchronizedCustomer(account2, account1, 100, 10));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        logger.info(
            "🏁 SYNCHRONIZED simulation complete. Final balances: account1={}, account2={}",
            account1.getBalance(),
            account2.getBalance()
        );
    }
}