package com.my_first_jpa.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @Column(nullable = false)
    private double moyenne;

    @Enumerated(EnumType.STRING)
    private StatutEtudiant statut;


    public Etudiant(Long id, String nom, double moyenne, StatutEtudiant statut) {
        this.id = id;
        this.nom = nom;
        this.moyenne = moyenne;
        this.statut = statut;
    }

    public Etudiant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public StatutEtudiant getStatut() {
        return statut;
    }

    public void setStatut(StatutEtudiant statut) {
        this.statut = statut;
    }

    @PrePersist
    @PreUpdate
    public void definirStatut() {
        if (moyenne >= 10) {
            this.statut = StatutEtudiant.ADMIS;
        } else if (moyenne >= 5) {
            this.statut = StatutEtudiant.REDOUBLANT;
        } else {
            this.statut = StatutEtudiant.EXCLU;
        }
    }


}
