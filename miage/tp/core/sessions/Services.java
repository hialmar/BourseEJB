/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bourse.miage.tp.core.sessions;

import bourse.miage.tp.core.entities.BourseBeanLocal;
import bourse.miage.tp.core.entities.TitreBoursier;
import bourse.miage.tp.core.exceptions.TitreExistantException;
import bourse.miage.tp.core.exceptions.TitreInconnuException;
import bourse.miage.tp.core.exceptions.TitreIncorrectException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * EJB qui gère l'encapsulation JSON
 * 
 * @author Cédric Teyssié
 */
@Stateless
public class Services implements ServicesLocal {

    @EJB
    private BourseBeanLocal bourseBean;

    private Gson gson;

    public Services() {
        this.gson = new Gson();
    }

    /**
     * Ajoute un titre boursier
     * @param t un titre au format JSON de la forme { mnemo : "MSOFT", nom : "Microsoft", cours : 3.5 } 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreExistantException si le titre existe déjà
     * @throws TitreIncorrectException si t est vide ou que la syntaxe JSON n'est pas correcte
     */
    @Override
    public String ajouterTitre(String t) throws TitreExistantException, TitreIncorrectException {
        // vérif Titre
        if (t.isEmpty()) {
            throw new TitreIncorrectException();
        }
        try {
            System.out.println(t);
            TitreBoursier titre = this.gson.fromJson(t, TitreBoursier.class);
            // titrevalide permet de positionner correctement la date de prise en compte de la cotation
            TitreBoursier titrevalide = new TitreBoursier(titre.getMnemo(), titre.getNom(), titre.getCours());
            return this.gson.toJson(this.bourseBean.ajouterTitre(titrevalide));
        } catch (JsonSyntaxException e) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, e);
            throw new TitreIncorrectException();
        }
    }

    /**
     * Ajoute un titre boursier 
     * @param mnemo le mnémonique du titre
     * @param nom le nom du titre
     * @param cours le cours actuel du titre
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreExistantException si le titre existe déjà
     * @throws TitreIncorrectException si mnemo ou le cours sont vides ou incorrects (pb de format flottant pour le cours)
     */
    @Override
    public String ajouterTitre(String mnemo, String nom, String cours) throws TitreExistantException, TitreIncorrectException {
        if (mnemo.isEmpty() || cours.isEmpty()) {
            throw new TitreIncorrectException();
        }
        try {
            Double lecours = Double.parseDouble(cours);
            TitreBoursier t = new TitreBoursier(mnemo, nom, lecours);
            return this.gson.toJson(this.bourseBean.ajouterTitre(t));
        } catch (NullPointerException | NumberFormatException e) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, e);
            throw new TitreIncorrectException();
        }
    }

    /**
     * Supprime un titre boursier
     * @param mnemo le mnémonique du titre
     * @throws TitreInconnuException si le titre n'existe pas
     */
    @Override
    public void retraitTitre(String mnemo) throws TitreInconnuException {
        this.bourseBean.retraitTitre(mnemo);
    }

    /**
     * Renvoie un titre boursier
     * @param mnemo le mnémonique du titre
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException si le titre n'existe pas
     */
    @Override
    public String getTitre(String mnemo) throws TitreInconnuException {
        return this.gson.toJson(this.bourseBean.getTitre(mnemo));
    }

    /**
     * Renvoie la liste des mnémoniques des titres
     * @return une liste JSON de la forme ["GOOG","MSOFT"]
     */
    @Override
    public String getListeTitres() {
        return this.gson.toJson(this.bourseBean.getListeTitres());
    }

    /**
     * Met à jour le cours d'un titre
     * @param t un titre au format JSON de la forme { mnemo : "MSOFT", cours : 3.5 } 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException  si le titre n'existe pas
     */
    @Override
    public String majTitre(String t) throws TitreInconnuException {
        try {
            TitreBoursier titre = this.gson.fromJson(t, TitreBoursier.class);
            this.bourseBean.updateCours(titre.getMnemo(), titre.getCours());
            return this.gson.toJson(this.bourseBean.getTitre(titre.getMnemo()));
        } catch (TitreInconnuException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            throw new TitreInconnuException();
        }
    }

    /**
     * Met à jour le cours d'un titre
     * @param id le mnémonique tu titre
     * @param cours le nouveau cours du titre 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException  si le titre n'existe pas
     */
    @Override
    public String majTitre(String id, String cours) throws TitreInconnuException {
        try {
            this.bourseBean.updateCours(id, Double.parseDouble(cours));
            return this.gson.toJson(this.bourseBean.getTitre(id));
        } catch (TitreInconnuException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            throw new TitreInconnuException();
        }
    }

}
