
public interface Chain {
    void setNextChain(Chain nextChain);
    Chain nextChain();
    void dispense(int remainder);
}