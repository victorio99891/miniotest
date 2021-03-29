package pl.wiktor.minioapi.service.errors;

public class MinioClientException extends RuntimeException {
    public MinioClientException(Exception e) {
        super(e.getMessage());
    }
}
