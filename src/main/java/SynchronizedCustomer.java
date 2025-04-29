public class SynchronizedCustomer implements Runnable {
    private final BankAccount from;
    private final BankAccount to;
    private final int numTransfers;
    private final int amount;
    public SynchronizedCustomer(BankAccount from, BankAccount to, int numTransfers, int amount) {
        this.from = from;
        this.to = to;
        this.numTransfers = numTransfers;
        this.amount = amount;
    }
    @Override
    public void run() {
        for (int i = 0; i < numTransfers; i++) {
            synchronizedTransfer(from, to, amount);
        }
    }
    private void synchronizedTransfer(BankAccount from, BankAccount to, int amount) {
        BankAccount firstLock = from.hashCode() < to.hashCode() ? from : to;
        BankAccount secondLock = from.hashCode() < to.hashCode() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
            }
        }
    }
}