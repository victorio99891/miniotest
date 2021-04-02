package pl.wiktor.minioapi.service.object;

import io.minio.*;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;
import pl.wiktor.minioapi.client.MinioClientService;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.errors.MinioClientException;
import pl.wiktor.minioapi.utils.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

@Service
public class ObjectService {

    private final MinioClientService mcs;

    public ObjectService(MinioClientService mcs) {
        this.mcs = mcs;
    }

    public List<ObjectDTO> listObjects(String bucketName, String path, boolean recurisve) {
        Iterable<Result<Item>> results = mcs.client()
                .listObjects(ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix(path)
                        .recursive(recurisve)
                        .build());
        return ObjectMapper.transformResults(results.iterator());
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

    public Boolean deleteObject(String bucketName, String path) {
        try {

            mcs.client().getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path)
                    .build());

            mcs.client().removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path)
                    .build());

        } catch (Exception e) {
            throw new MinioClientException(e);
        }
        return true;
    }
}
