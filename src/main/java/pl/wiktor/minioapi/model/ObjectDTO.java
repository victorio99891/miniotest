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

    private String contentType;

    private Map<String, String> metadata;

    private long size;

    public ObjectDTO(String objectName, ZonedDateTime lastModified, String contentType, Map<String, String> metadata, long size) {
        this.objectName = objectName;
        this.lastModified = lastModified;
        this.contentType = contentType;
        this.metadata = metadata;
        this.size = size;
    }
}
