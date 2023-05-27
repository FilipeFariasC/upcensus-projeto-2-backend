package br.edu.ifpb.upcensus.presentation.user.response;

import java.util.Objects;

public class ApiResponse {
    private final Boolean success;
    private final String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse that = (ApiResponse) o;
        return Objects.equals(success, that.success) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
