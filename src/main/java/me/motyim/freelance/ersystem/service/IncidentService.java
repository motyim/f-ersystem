package me.motyim.freelance.ersystem.service;

import me.motyim.freelance.ersystem.dto.IncidentDto;
import me.motyim.freelance.ersystem.dto.IncidentResponse;
import me.motyim.freelance.ersystem.entity.Incident;
import me.motyim.freelance.ersystem.entity.User;
import me.motyim.freelance.ersystem.repo.EmployeeRepo;
import me.motyim.freelance.ersystem.repo.IncidentRepo;
import me.motyim.freelance.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public void addIncident(IncidentDto dto, MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("image not found");
        }

        String imagePath = uploadImage(file);

        Incident incident = mapper.map(dto, Incident.class);
        incident.setImage(imagePath);

        Optional<User> user = userRepo.findById(dto.getUserId());

        if(!user.isPresent())
            throw new RuntimeException("user not found");

        // TODO: 09-Feb-19 make it dynamic
        incident.setEmployeeId(employeeRepo.findById(1).get());
        incident.setUserId(user.get());
        incident.setDate(new Date());
        repo.save(incident);

    }

    private String uploadImage(MultipartFile file) {
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            System.out.println(   "You successfully uploaded '" + file.getOriginalFilename() + "'");

            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("image can't uploded");
        }
    }

    @Transactional
    public  List <IncidentResponse>  getAllIncident(int id) {

        Optional<User> user = userRepo.findById(id);

        if(!user.isPresent())
            throw new RuntimeException("user not found");

        Stream<Incident> incident = repo.findAllByUserId(user.get());

        List<Incident> incidents = incident.collect(Collectors.toList());

//        incidents.forEach(System.out::println);
        ArrayList <IncidentResponse> responses = new ArrayList<>();

        // TODO: 09-Feb-19 make model mapper
//        mapper.map(incidents, responses);

        incidents.forEach(i->{
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
