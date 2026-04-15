// INTERFACE: Defines contract for objects that can be printed
// Classes implementing this interface must provide a print() method
public interface Printable {
    // METHOD SIGNATURE: print() method must be implemented by all implementing classes
    // Enables polymorphic behavior - any object can be printed uniformly
    void print();
}