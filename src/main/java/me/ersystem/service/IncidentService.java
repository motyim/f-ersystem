package me.ersystem.service;

import me.ersystem.dto.*;
import me.ersystem.entity.Incident;
import me.ersystem.entity.User;
import me.ersystem.repo.EmployeeRepo;
import me.ersystem.repo.IncidentRepo;
import me.ersystem.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
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

    private static String UPLOADED_FOLDER = "images/";

    public Integer addIncident(IncidentDto dto) {

        Incident incident = mapper.map(dto, Incident.class);

        Optional<User> user = userRepo.findById(dto.getUserId());

        if (!user.isPresent())
            throw new RuntimeException("user not found");

//        incident.setEmployeeId(employeeRepo.findById(1).get());
        incident.setUserId(user.get());
        incident.setDate(new Date());
        incident.setStatus("new");
        return repo.save(incident).getId();
    }

    public void uploadImage(UploadImageDto imageDto) {

        Optional<Incident> incidentOptional = repo.findById(imageDto.getIncidentID());
        if (!incidentOptional.isPresent())
            throw new RuntimeException("incident not exsist");

        String imageType = Optional.of(imageDto.getImageType()).orElse("png");
        String fileName = decodeToImage(imageDto.getEncodedImage(),imageType);

        Incident incident = incidentOptional.get();
        incident.setImage(fileName);
        repo.save(incident);

    }

    private String decodeToImage(String imageString, String imageType) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            String fileName = "Image_" + new Date().getTime()+"."+imageType;
            File outputfile = new File(UPLOADED_FOLDER+fileName);
            ImageIO.write(image, imageType, outputfile);
            bis.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageString;
    }

    @Transactional
    public List<IncidentResponse> getAllIncidentByUserId(int id) {

        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent())
            throw new RuntimeException("user not found");

        Stream<Incident> incidentStream = repo.findAllByUserId(user.get());

        Type listType = new TypeToken<List<IncidentResponse>>() {}.getType();

        return getIncidentResponse(incidentStream,listType);
    }

    public List<IncidentDto> getAllIncident() {

        Iterable<Incident> incident = repo.findAll();

        Type listType = new TypeToken<List<IncidentDto>>() {}.getType();

        List<IncidentDto> incidentsDtos = mapper.map(incident, listType);

        return incidentsDtos;
    }

    @Transactional
    public List<IncidentDto> getAllIncidentByStatus(List status) {

        Stream<Incident> incidentStream = repo.findAllByStatusIn(status);

        Type listType = new TypeToken<List<IncidentDto>>() {}.getType();

        return getIncidentResponse(incidentStream,listType);
    }


    private <R> List<R> getIncidentResponse(Stream<Incident> incidentStream,Type listType){


        List<Incident> incidents = incidentStream.collect(Collectors.toList());

        List<R> list = mapper.map(incidents, listType);

        return list;
    }

    public IncidentDto getIncidentById(int id) {
        Optional<Incident> optionalIncident = repo.findById(id);

        Incident incident = optionalIncident.orElseThrow(RuntimeException::new);

        IncidentDto incidentDto = mapper.map(incident, IncidentDto.class);

        return incidentDto;

    }

    public List<LocationStatDto> getLocationStatistics() {
        return repo.findLocationCount();
    }

    public List<IncidentTypeStatDto> getTypeStatistics() {
        return repo.findTypeCount();
    }

    public void changeStatus(int id, String status) {
        Incident incident = repo.findById(id).orElseThrow(RuntimeException::new);
        incident.setStatus(status);
        repo.save(incident);
    }
}
