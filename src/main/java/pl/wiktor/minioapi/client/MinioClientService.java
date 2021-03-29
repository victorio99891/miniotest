package pl.wiktor.minioapi.client;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class MinioClientService {

    private final String url;
    private final String username;
    private final String password;

    private MinioClientService(
            @Value("${minio.url}") String url,
            @Value("${minio.username}") String username,
            @Value("${minio.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public MinioClient client() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(username, password)
                .build();
    }


}
