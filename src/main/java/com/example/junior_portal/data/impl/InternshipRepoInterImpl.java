package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.InternshipRepoInter;
import com.example.junior_portal.data.repository.InternshipRepository;
import com.example.junior_portal.model.Internship;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InternshipRepoInterImpl implements InternshipRepoInter {

    private final InternshipRepository internshipRepository;
    @Override
    public Internship addInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship getInternshipById(Long id) {
        return internshipRepository.findAllById(id);
    }

    @Override
    public Internship changeInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    @Transactional
    public String changeStatusForInternship(Long id) {
        try {
            Internship internship = internshipRepository.findAllById(id);
            internship.setActive(!internship.getActive());
            internshipRepository.save(internship);
            return "Success";
        }
        catch (Exception e){
            return "Something went wrong";
        }
    }

}
