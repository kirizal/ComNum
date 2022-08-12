package ProgramFunctionality.Variables;


public interface Variable<T> {
    T getValue();
    void setValue(T value);
    String getName();
    String getType();
}
