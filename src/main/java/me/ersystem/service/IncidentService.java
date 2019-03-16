package me.ersystem.service;

import me.ersystem.dto.IncidentDto;
import me.ersystem.dto.IncidentResponse;
import me.ersystem.entity.Incident;
import me.ersystem.entity.User;
import me.ersystem.repo.EmployeeRepo;
import me.ersystem.repo.IncidentRepo;
import me.ersystem.repo.UserRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since 09-Feb-19
 */
@Service
public class IncidentService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    IncidentRepo repo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    private static String UPLOADED_FOLDER = "uploads/";

    public Integer addIncident(IncidentDto dto) {

        Incident incident = mapper.map(dto, Incident.class);

        Optional<User> user = userRepo.findById(dto.getUserId());

        if (!user.isPresent())
            throw new RuntimeException("user not found");

        // TODO: 09-Feb-19 make it dynamic
        incident.setEmployeeId(employeeRepo.findById(1).get());
        incident.setUserId(user.get());
        incident.setDate(new Date());
        Incident incident1 = repo.save(incident);
        return incident1.getId();

    }

    public void uploadImage(String encodedImage, int id) {

        try {
            Optional<Incident> incidentOptional = repo.findById(id);
            if (!incidentOptional.isPresent())
                throw new RuntimeException("incident not exsist");



            byte[] imageByte = Base64.decodeBase64(encodedImage);
            String fileName = "Image-" + new Date().toString();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, imageByte);

            Incident incident = incidentOptional.get();
            incident.setImage(fileName);
            repo.save(incident);

            System.out.println("You successfully uploaded '");

        } catch (IOException e) {
            throw new RuntimeException("image can't uploded");
        }
    }

    @Transactional
    public List<IncidentResponse> getAllIncident(int id) {

        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent())
            throw new RuntimeException("user not found");

        Stream<Incident> incident = repo.findAllByUserId(user.get());

        List<Incident> incidents = incident.collect(Collectors.toList());

//        incidents.forEach(System.out::println);
        ArrayList<IncidentResponse> responses = new ArrayList<>();

        // TODO: 09-Feb-19 make model mapper
//        mapper.map(incidents, responses);

        incidents.forEach(i -> {
            IncidentResponse res = new IncidentResponse();
            res.setDate(i.getDate());
            res.setId(i.getId());
            res.setType(i.getType());
            responses.add(res);
        });

//        responses.forEach(System.out::println);
//        System.out.println(responses.size());
        return responses;
    }
}
