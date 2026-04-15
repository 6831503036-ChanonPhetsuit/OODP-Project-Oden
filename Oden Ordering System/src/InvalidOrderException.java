// CUSTOM EXCEPTION: Extends Exception class to represent order validation errors
// Used when order operations violate business rules (e.g., invalid quantity)
// MESSAGE PARAMETER: Passes custom error message to parent Exception class via super()
public class InvalidOrderException extends Exception {
    // EXCEPTION CONSTRUCTOR: Accepts custom error message for detailed error information
    public InvalidOrderException(String msg) {
        super(msg);
    }
}