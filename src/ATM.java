import java.util.*;

public class ATM implements Iterable<Dispenser> {

    private Dispenser[] currencyDispensers;

    public ATM(List<Integer> denominations, String currency, int initialBills) {
        currencyDispensers = new Dispenser[denominations.size()];
        denominations.sort((a, b) -> b - a);
        for (int i = 0; i < denominations.size(); ++i)
            currencyDispensers[i] = new Dispenser(currency, denominations.get(i), initialBills);
        for (int i = 0; i + 1 < denominations.size(); ++i)
            currencyDispensers[i].setNextChain(currencyDispensers[i + 1]);
    }

    public void withdraw(int amount) {
        currencyDispensers[0].dispense(amount);
    }

    public class CDIterator implements Iterator {
        int position;

        public CDIterator(int position) {
            this.position = position;
        }

        @Override
        public boolean hasNext() {
            return position < currencyDispensers.length;
        }

        @Override
        public Object next() {
            return currencyDispensers[position++];
        }
    }

    public int getBalance() {
        int balance = 0;
        for (Dispenser cd: this) {
            balance += cd.billsLeft * cd.value;
        }
        return balance;
    }



    public static void main(String[] args) {
        ArrayList<Integer> denom = new ArrayList();
        denom.add(5);
        denom.add(10);
        denom.add(20);
        denom.add(50);
        denom.add(100);
        ATM atm = new ATM(denom, "€", 10);
        atm.withdraw(820);

        System.out.println(atm.getBalance());
    }

    @Override
    public Iterator iterator() {
        return new CDIterator(0);
    }

}