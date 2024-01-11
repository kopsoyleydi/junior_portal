package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.Internship;

import java.util.List;

public interface InternshipRepoInter {

    Internship addInternship(Internship internship);

    Internship getInternshipById(Long id);

    Internship changeInternship(Internship internship);

    List<Internship> getAllInternships();

    String changeStatusForInternship(Long id);
}
