package pl.wiktor.minioapi.service.object;

import com.google.common.collect.Lists;
import io.minio.ListObjectsArgs;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;
import pl.wiktor.minioapi.client.MinioClientService;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.errors.MinioClientException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjectService {

    private final MinioClientService mcs;

    public ObjectService(MinioClientService mcs) {
        this.mcs = mcs;
    }

    public List<ObjectDTO> listObjects(String bucketName) {
        Iterable<Result<Item>> results = mcs.client()
                .listObjects(ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .recursive(true)
                        .build());

        Iterator<Result<Item>> iterator = results.iterator();

        return Lists.newArrayList(iterator).stream().map(itemResult -> {
            try {
                Item item = itemResult.get();
                return new ObjectDTO(item.objectName(), item.lastModified(), item.userMetadata(), item.size(), item.owner());
            } catch (Exception e) {
                throw new MinioClientException(e);
            }
        }).collect(Collectors.toList());
    }

    public String createObject(String bucketName, String path, String content) {
        try {

            String[] sections = path.split("/");
            BufferedWriter writer = new BufferedWriter(new FileWriter(sections[sections.length - 1]));
            writer.write(content);
            writer.close();

            return mcs.client()
                    .putObject(PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
//                            .stream()
                            .build()).object();
        } catch (Exception e) {
            throw new MinioClientException(e);
        }
    }
}
