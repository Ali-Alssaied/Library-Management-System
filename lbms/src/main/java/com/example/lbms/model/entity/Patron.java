package com.example.lbms.model.entity;

import com.example.lbms.dto.PatronDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name="Patron")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Patron
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;
    private String email;
    @NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNumber;

    public static Patron toEntity(PatronDto patronDto)
    {
        return builder().id(patronDto.getId())
                .Name(patronDto.getName())
                .email(patronDto.getEmail())
                .phoneNumber(patronDto.getPhoneNumber())
                .build();

    }

}
