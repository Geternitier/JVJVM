package runtime;

public class Reference implements Cloneable{


    @Override
    public Reference clone() {
        try {
            return (Reference) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
