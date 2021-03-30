package pl.wiktor.minioapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.minioapi.model.ObjectDTO;
import pl.wiktor.minioapi.service.object.ObjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bucket")
public class ObjectController {

    private final ObjectService os;

    public ObjectController(ObjectService os) {
        this.os = os;
    }

//    @GetMapping("/{bucketName}/object")
//    public ResponseEntity<List<ObjectDTO>> listObjects(@PathVariable("bucketName") String bucketName) {
//        return ResponseEntity.ok(os.listObjects(bucketName));
//    }

    @GetMapping("/{bucketName}/**")
    public ResponseEntity<List<ObjectDTO>> listObjects(
            HttpServletRequest request,
            @PathVariable("bucketName") String bucketName) {
        log.info(request.getRequestURI().split(bucketName)[1]);
        return ResponseEntity.ok(os.listObjects(bucketName));
    }

    @PostMapping("/{bucketName}/{objectPath}")
    public ResponseEntity<String> createObject(
            @PathVariable("bucketName") String bucketName,
            @RequestParam(required = true) String path,
            @RequestParam(required = true) String content) {
        return ResponseEntity.ok(os.createObject(bucketName, path, content));
    }


}
