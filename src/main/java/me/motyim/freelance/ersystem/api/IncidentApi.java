package me.motyim.freelance.ersystem.api;

import me.motyim.freelance.ersystem.dto.IncidentDto;
import me.motyim.freelance.ersystem.dto.IncidentResponse;
import me.motyim.freelance.ersystem.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@RestController
@RequestMapping("incident")
public class IncidentApi {

    @Autowired
    IncidentService service;


    @PostMapping()
    public ResponseEntity<String> addIncident(IncidentDto dto ,@RequestParam ("file") MultipartFile file) {

        service.addIncident(dto,file);

        return ResponseEntity.ok("Incident Added");

    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAll(int id) {

        List<IncidentResponse> allIncident = service.getAllIncident(id);
        System.out.println(allIncident.size());
        return ResponseEntity.ok(allIncident);

    }

}
