package me.ersystem.api;

import me.ersystem.dto.IncidentDto;
import me.ersystem.dto.IncidentResponse;
import me.ersystem.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@RestController
@RequestMapping("api/incident")
public class IncidentApi {

    @Autowired
    IncidentService service;


    @PostMapping()
    public ResponseEntity<Integer> addIncident(IncidentDto dto) {
        Integer integer = service.addIncident(dto);
        return ResponseEntity.ok(integer);
    }

    @PostMapping("upload")
    public ResponseEntity<String> uploadImage(@RequestParam("encodedImage") String encodedImage,@RequestParam int id){
        service.uploadImage(encodedImage,id);
        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAll(int id) {

        List<IncidentResponse> allIncident = service.getAllIncidentByUserId(id);
        System.out.println(allIncident.size());
        return ResponseEntity.ok(allIncident);

    }

}
