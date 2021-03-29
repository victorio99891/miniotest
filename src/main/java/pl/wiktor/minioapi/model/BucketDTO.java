package pl.wiktor.minioapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public final class BucketDTO {
    private final String name;
    private final ZonedDateTime creationDate;

    public BucketDTO(String name, ZonedDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
