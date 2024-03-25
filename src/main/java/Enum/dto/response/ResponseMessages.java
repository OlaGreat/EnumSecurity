package Enum.dto.response;

public enum ResponseMessages {
    USER_REGISTRATION_SUCCESSFUL("Registration successful"),
    COHORT_ADDED("Cohort %s successfully added")
    ;

    private String message;
    ResponseMessages(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
