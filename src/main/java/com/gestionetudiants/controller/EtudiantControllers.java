package com.gestionetudiants.controller;

import com.gestionetudiants.model.Etudiant;
import com.gestionetudiants.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:8081") // Autorise les requêtes depuis votre client Swing
public class EtudiantControllers {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Récupérer tous les étudiants
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Créer un nouvel étudiant
    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Étudiant non trouvé avec l'id: " + id));
        return ResponseEntity.ok(etudiant);
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Étudiant non trouvé avec l'id: " + id));

        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setMoyenne(etudiantDetails.getMoyenne());

        Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
        return ResponseEntity.ok(updatedEtudiant);
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEtudiant(@PathVariable Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Étudiant non trouvé avec l'id: " + id));

        etudiantRepository.delete(etudiant);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Calculer les statistiques de la classe
    @GetMapping("/statistiques")
    public ResponseEntity<Map<String, Object>> getStatistiques() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        if (etudiants.isEmpty()) {
            throw new NoSuchElementException("Aucun étudiant trouvé");
        }

        double moyenneClasse = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .average()
                .orElse(0.0);

        double noteMin = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .min()
                .orElse(0.0);

        double noteMax = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .max()
                .orElse(0.0);

        Map<String, Object> statistiques = new HashMap<>();
        statistiques.put("moyenneClasse", Math.round(moyenneClasse * 100.0) / 100.0);
        statistiques.put("noteMin", noteMin);
        statistiques.put("noteMax", noteMax);
        statistiques.put("nombreEtudiants", etudiants.size());

        return ResponseEntity.ok(statistiques);
    }

    // Gestion des erreurs globales
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.internalServerError().body("Une erreur est survenue: " + e.getMessage());
    }
}