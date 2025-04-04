package com.gestionetudiants.model;

import jakarta.persistence.*;  // Changement de javax.persistence à jakarta.persistence

@Entity
@Table(name = "etudiants")  // Optionnel: précise le nom de la table
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Validation supplémentaire
    private String nom;

    @Column(nullable = false)
    private float moyenne;

    // Constructeurs
    public Etudiant() {
    }

    public Etudiant(String nom, float moyenne) {
        this.nom = nom;
        this.moyenne = moyenne;
    }

    // Getters et Setters
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

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    // Méthode utilitaire pour le statut
    public String getStatut() {
        if (moyenne >= 10) return "Admis";
        else if (moyenne >= 5) return "Rattrapage";
        else return "Exclu";
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", moyenne=" + moyenne +
                ", statut='" + getStatut() + '\'' +
                '}';
    }
}