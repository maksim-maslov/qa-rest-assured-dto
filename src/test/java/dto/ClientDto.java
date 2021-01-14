package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientDto {
    @JsonProperty("_id")
    private String _id;

    @JsonProperty("balance")
    private int balance;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("__v")
    private int __v;
}
