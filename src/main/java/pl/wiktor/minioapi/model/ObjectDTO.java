package pl.wiktor.minioapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Setter
public class ObjectDTO {


    private String objectName;

    private ZonedDateTime lastModified;

    private Map<String, String> metadata;

    private long size;

    private String owner;

    public ObjectDTO(String objectName) {
        this.objectName = objectName;
    }

    public ObjectDTO(String objectName, ZonedDateTime lastModified, Map<String, String> metadata, long size, String owner) {
        this.objectName = objectName;
        this.lastModified = lastModified;
        this.metadata = metadata;
        this.size = size;
        this.owner = owner;
    }
}
