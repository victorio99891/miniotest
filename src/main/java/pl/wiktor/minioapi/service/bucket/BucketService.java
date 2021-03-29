package pl.wiktor.minioapi.service.bucket;

import io.minio.MakeBucketArgs;
import io.minio.RemoveBucketArgs;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Service;
import pl.wiktor.minioapi.client.MinioClientService;
import pl.wiktor.minioapi.service.errors.MinioClientException;

import java.util.List;

@Service
public class BucketService {

    private MinioClientService mcs;

    public BucketService(MinioClientService mcs) {
        this.mcs = mcs;
    }

    public List<Bucket> listBuckets() {
        try {
            return this.mcs.client()
                    .listBuckets();
        } catch (Exception e) {
            throw new MinioClientException(e);
        }
    }

    public boolean createBucket(String bucketName) {
        try {
            this.mcs.client().makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioClientException(e);
        }
        return true;
    }

    public boolean deleteBucket(String bucketName) {
        try {
            this.mcs.client().removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioClientException(e);
        }
        return true;
    }
}
