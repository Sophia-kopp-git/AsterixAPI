package org.example.asterixapi.model;

import lombok.With;

@With
public record Character(String id, String name, int age, String profession) {
}
