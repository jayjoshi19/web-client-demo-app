package io.pragra.learning.webclientdemo.dto;

import io.pragra.learning.webclientdemo.entity.GitUser;
import lombok.Builder;

import java.util.Objects;

@Builder
public class UserResponseDTO {

    private GitUser data;
    private String statusCode;
    private String statusDesc;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO userResponseDTO = (UserResponseDTO) o;
        return Objects.equals(data, userResponseDTO.data) && Objects.equals(statusCode, userResponseDTO.statusCode) && Objects.equals(statusDesc, userResponseDTO.statusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, statusCode, statusDesc);
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "data=" + data +
                ", statusCode='" + statusCode + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }

    public GitUser getData() {
        return data;
    }

    public void setData(GitUser data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
