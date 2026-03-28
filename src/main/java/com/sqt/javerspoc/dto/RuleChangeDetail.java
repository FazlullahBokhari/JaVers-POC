package com.sqt.javerspoc.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RuleChangeDetail {

    private String field;
    private Object oldValue;
    private Object newValue;
}