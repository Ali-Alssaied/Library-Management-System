package com.example.lbms.dto;


import com.example.lbms.model.entity.Patron;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronDto
{
    private Integer id;
    private String Name;
    private String email;
    private String phoneNumber;

    public static PatronDto toDto(Patron parton)
    {
        return  builder().id(parton.getId())
                .Name(parton.getName())
                .email(parton.getEmail())
                .phoneNumber(parton.getPhoneNumber())
                .build();
    }
}
