package fr.sdv.b32324.bo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TestBibliotheque {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = entityManagerFactory.createEntityManager();

        // Réalisez une requête qui permet d’extraire un emprunt et tous ses livres associés
        Emprunt emprunt = findEmpruntWithLivres(em, 1);
        if (emprunt != null) {
            System.out.println("Emprunt trouvé:");
            System.out.println("Date début: " + emprunt.getDateDebut());
            System.out.println("Date fin: " + emprunt.getDateFin());
            System.out.println("Livres associés:");
            for (Livre livre : emprunt.getLivres()) {
                System.out.println("- Titre: " + livre.getTitre() + ", Auteur: " + livre.getAuteur());
            }
        } else {
            System.out.println("Aucun emprunt trouvé avec l'identifiant 1.");
        }

        // Réalisez une requête qui permet d’extraire tous les emprunts d’un client donné
        List<Emprunt> empruntsClient = findEmpruntsByClientId(em, 1);
        if (!empruntsClient.isEmpty()) {
            System.out.println("\nEmprunts du client:");
            for (Emprunt e : empruntsClient) {
                System.out.println("- Date début: " + e.getDateDebut() + ", Date fin: " + e.getDateFin());
            }
        } else {
            System.out.println("Aucun emprunt trouvé pour le client avec l'identifiant 1.");
        }

        em.close();
        entityManagerFactory.close();
    }

    private static Emprunt findEmpruntWithLivres(EntityManager em, int id) {
        return em.find(Emprunt.class, id);
    }

    private static List<Emprunt> findEmpruntsByClientId(EntityManager em, int idClient) {
        TypedQuery<Emprunt> query = em.createQuery("SELECT e FROM Emprunt e WHERE e.client.id = :idClient", Emprunt.class);
        query.setParameter("idClient", idClient);
        return query.getResultList();
    }
}
