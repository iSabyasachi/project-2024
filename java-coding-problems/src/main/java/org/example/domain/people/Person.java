package org.example.domain.people;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    String name;
    int age;
    List<String> pets;
}
