package pl.wiktor.minioapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.object.ObjectService;

import java.util.List;

@RestController
@RequestMapping("/bucket")
public class ObjectController {

    private final ObjectService os;

    public ObjectController(ObjectService os) {
        this.os = os;
    }

    @GetMapping("/{bucketName}/object")
    public ResponseEntity<List<ObjectDTO>> listObjects(@PathVariable("bucketName") String bucketName) {
        return ResponseEntity.ok(os.listObjects(bucketName));
    }

    @PostMapping("/{bucketName}/object")
    public ResponseEntity<String> createObject(
            @PathVariable("bucketName") String bucketName,
            @RequestParam(required = true) String path,
            @RequestParam(required = true) String content) {
        return ResponseEntity.ok(os.createObject(bucketName, path, content));
    }


}
