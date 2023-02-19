package com.learn.testfilter.payload.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignatureDTO {

    private String signature; // it has date and url

    private String key; // open for signature

    private String name;

}
