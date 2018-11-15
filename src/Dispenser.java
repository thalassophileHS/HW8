public class Dispenser implements Chain {
    private Chain chain = null;

    String currency;
    int value;
    int billsLeft;

    public Dispenser(String currency, int value, int billsLeft) {
        this.currency = currency;
        this.value = value;
        this.billsLeft = billsLeft;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public Chain nextChain() {
        return chain;
    }

    @Override
    public void dispense(int remainder) {
        if (remainder >= value) {
            int num = remainder / value;
            if (num > billsLeft)
                num = billsLeft;
            remainder -= num * value;
            billsLeft -= num;
            System.out.println("Dispensing " + num + " " + value + currency + " note" + (num > 1 ? "s" : ""));
        }
        if (remainder > 0) {
            if (nextChain() != null) {
                nextChain().dispense(remainder);
            } else
                System.out.println("Could not dispense the rest " + remainder + currency);
        } else {
            System.out.println("Dispensed Successfully");
        }
    }
}