package pl.wiktor.minioapi.utils;

import com.google.common.collect.Lists;
import io.minio.Result;
import io.minio.messages.Item;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.errors.MinioClientException;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface ObjectMapper {
    static List<ObjectDTO> transformResults(Iterator<Result<Item>> iterator) {
        return Lists.newArrayList(iterator).stream().map(itemResult -> {
            try {
                Item item = itemResult.get();
                if (!item.isDir()) {
                    return new ObjectDTO(item.objectName(), item.lastModified(), item.userMetadata(), item.size(), item.owner().displayName());
                } else {
                    return new ObjectDTO(item.objectName());
                }
            } catch (Exception e) {
                throw new MinioClientException(e);
            }
        }).collect(Collectors.toList());
    }
}
