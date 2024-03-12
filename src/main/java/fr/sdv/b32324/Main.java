package fr.sdv.b32324;

import fr.sdv.b32324.bo.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Livre monLivre=em.find(Livre.class,2);
        if (monLivre!=null){
            System.out.println("Titre: " + monLivre.getTitre());
            System.out.println("Auteur: " + monLivre.getAuteur());
        }
        /*
        Livre nouveauLivre=new Livre("Cendrillon","Julien Pajot");
        em.persist(nouveauLivre);
         */

        Livre correctionLivre = em.find(Livre.class,5);
        if (correctionLivre!=null){
            correctionLivre.setTitre("Du plaisir dans la cuisine");
        }
        Query query2 = em.createQuery("select l from Livre l");
        System.out.println(query2.getResultList().toString());


        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();
    }
}