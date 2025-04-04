package com.gestionetudiants.repository;  // Supprimez le point à la fin

import com.gestionetudiants.model.Etudiant;  // Ajoutez cette importation
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}