package pl.wiktor.minioapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.minioapi.model.BucketDTO;
import pl.wiktor.minioapi.service.bucket.BucketService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/bucket")
public class BucketController {


    private final BucketService bs;

    public BucketController(BucketService bs) {
        this.bs = bs;
    }

    @GetMapping
    public ResponseEntity<List<BucketDTO>> listBuckets() {
        log.info("List all buckets.");
        return ResponseEntity.ok(this.bs.listBuckets().stream()
                .map(bucket -> new BucketDTO(bucket.name(), bucket.creationDate()))
                .collect(Collectors.toList())
        );
    }

    @PostMapping("/{bucketName}")
    public ResponseEntity<Boolean> createBucket(@PathVariable("bucketName") String bucketName) {
        log.info("Create bucket: " + bucketName);
        return ResponseEntity.ok(bs.createBucket(bucketName));
    }

    @DeleteMapping("/{bucketName}")
    public ResponseEntity<Boolean> deleteBucket(@PathVariable("bucketName") String bucketName) {
        log.info("Delete bucket: " + bucketName);
        return ResponseEntity.ok(bs.deleteBucket(bucketName));
    }
}
