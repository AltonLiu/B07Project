package com.example.b07project.backend;

public class AuthValidation {
    private static final int USERNAME_MIN_LENGTH = 6;
    private static final int NAME_MIN_LENGTH = 2;
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&^_-]{8,}$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    // Ensures no instantiation of AuthValidation
    private AuthValidation(){}

    /**
     * @name isEmpty
     * @param field Represents authentication field to be validated
     * @return true if string isn't empty, false if it is
     */
    public static boolean isNotEmpty(String field){
        return field.length() > 0;
    }

    /**
     * @name isValidUsername
     * @param username Represents username field to be validated
     * @return true if username is above required length, false o/w
     */
    public static boolean isValidUsername(String username){
        return username.length() >= USERNAME_MIN_LENGTH;
    }

    /**
     * @name isValidPassword
     * @param password Represents password field to be validated
     * @return true if password matches PASSWORD_REGEX, false o/w
     */
    public static boolean isValidPassword(String password){
        return password.matches(PASSWORD_REGEX);
    }
    /**
     * @name isValidEmail
     * @param email Represents email field to be validated
     * @return true if email matches EMAIL_REGEX, false o/w
     */
    public static boolean isValidEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    /**
     * @name isValidName
     * @param name Represents name field to be validated
     * @return true if name is above required length, false o/w
     */
    public static boolean isValidName(String name){
        return name.length() >= NAME_MIN_LENGTH;
    }
}
