package projlab;

public interface Visitable {
public void accept(Visitor visitor);
public Visitable getVisitable(int direction);
}
