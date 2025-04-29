import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankAccount {
    private static final Logger logger = LogManager.getLogger(BankAccount.class);
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
        logger.info("Account created with initial balance {}", initialBalance);
    }

    public void deposit(int amount) {
        balance += amount;
        logger.debug("Deposited {}, new balance {}", amount, balance);
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            logger.debug("Withdrew {}, new balance {}", amount, balance);
            return true;
        } else {
            logger.warn("Withdrawal of {} failed, insufficient funds (balance: {})", amount, balance);
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }
}