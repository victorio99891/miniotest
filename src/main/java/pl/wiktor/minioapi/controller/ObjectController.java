package pl.wiktor.minioapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.object.ObjectService;
import pl.wiktor.minioapi.utils.ObjectPathExtractor;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bucket")
public class ObjectController {

    private final ObjectService os;

    public ObjectController(ObjectService os) {
        this.os = os;
    }

    @GetMapping("/{bucketName}/**")
    public ResponseEntity<List<ObjectDTO>> listObjects(
            HttpServletRequest request,
            @PathVariable("bucketName") String bucketName,
            @RequestParam("recursive") boolean recursive) {
        String extractedPath = ObjectPathExtractor.extractPath(request.getRequestURI(), bucketName);
        log.info("List objects. Bucket name: " + bucketName + " Path: " + extractedPath + " Recursive: " + recursive);
        return ResponseEntity.ok(
                os.listObjects(bucketName, URLDecoder.decode(extractedPath, StandardCharsets.UTF_8), recursive)
        );
    }

    @PostMapping("/{bucketName}/**")
    public ResponseEntity<String> createObject(
            @PathVariable("bucketName") String bucketName) {
        log.info("Create object. Not implemented.");
        return ResponseEntity.ok("Not implemented");
    }

    @DeleteMapping("/{bucketName}/**")
    public ResponseEntity<Boolean> deleteObject(
            HttpServletRequest request,
            @PathVariable("bucketName") String bucketName) {
        String extractedPath = ObjectPathExtractor.extractPath(request.getRequestURI(), bucketName);
        log.info("Delete object. Bucket name: " + bucketName + " Path: " + extractedPath);
        return ResponseEntity.ok(os.deleteObject(bucketName, URLDecoder.decode(extractedPath, StandardCharsets.UTF_8)));
    }
}
