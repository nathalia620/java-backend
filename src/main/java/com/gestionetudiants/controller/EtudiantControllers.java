package com.my_first_jpa.demo.Contrôleur;


import com.my_first_jpa.demo.Service.EtudiantService;
import com.my_first_jpa.demo.model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public Optional<Etudiant> getEtudiantsById(@PathVariable long id) {
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/stats")
    public DoubleSummaryStatistics getStats() {
        return etudiantService.getStats();
    }


    @PostMapping
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }
}
