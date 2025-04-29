import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private final BankAccount from;
    private final BankAccount to;
    private final int amount;
    private final int iterations;

    public Customer(BankAccount from, BankAccount to, int amount, int iterations) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.iterations = iterations;
        logger.info("Customer created: transfer {} x{}", amount, iterations);
    }

    @Override
    public void run() {
        for (int i = 1; i <= iterations; i++) {
            logger.info("Customer {} iteration {}: attempting to transfer {} from {} to {}",
                        Thread.currentThread().getName(), i, amount, from);
            if (from.withdraw(amount)) {
                to.deposit(amount);
                logger.debug("Customer {} iteration {}: transfer succeeded",
                             Thread.currentThread().getName(), i);
            } else {
                logger.error("Customer {} iteration {}: transfer failed due to insufficient funds", 
                             Thread.currentThread().getName(), i);
            }
        }
        logger.info("Customer {} finished all transactions", Thread.currentThread().getName());
    }
}