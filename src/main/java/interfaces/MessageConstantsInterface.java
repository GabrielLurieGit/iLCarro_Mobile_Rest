package interfaces;

public interface MessageConstantsInterface {
    String SUCCESSFULL_REGISTRATION = "Registration success!";
    String MISSING_FIELD = "All fields must be filled and agree terms";
    String INVALID_EMAIL = "{username=must be a well-formed email address}";
    String INVALID_PASSWORD = "{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number;" +
            " Can contain special characters [@$#^&*!]}";

}
