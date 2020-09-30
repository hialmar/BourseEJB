/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.core.sessions;

import miage.tp.core.exceptions.TitreExistantException;
import miage.tp.core.exceptions.TitreInconnuException;
import miage.tp.core.exceptions.TitreIncorrectException;
import javax.ejb.Local;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON
 * @author Cédric Teyssié
 */
@Local
public interface ServicesLocal {

    /**
     * Ajoute un titre boursier
     * @param t un titre au format JSON de la forme { mnemo : "MSOFT", nom : "Microsoft", cours : 3.5 } 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreExistantException si le titre existe déjà
     * @throws TitreIncorrectException si t est vide ou que la syntaxe JSON n'est pas correcte
     */
    public String ajouterTitre(String t) throws TitreExistantException, TitreIncorrectException;

    /**
     * Ajoute un titre boursier 
     * @param mnemo le mnémonique du titre
     * @param nom le nom du titre
     * @param cours le cours actuel du titre
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreExistantException si le titre existe déjà
     * @throws TitreIncorrectException si mnemo ou le cours sont vides ou incorrects (pb de format flottant pour le cours)
     */
    public String ajouterTitre(String mnemo, String nom, String cours) throws TitreExistantException, TitreIncorrectException;

    /**
     * Supprime un titre boursier
     * @param mnemo le mnémonique du titre
     * @throws TitreInconnuException si le titre n'existe pas
     */
    public void retraitTitre(String mnemo) throws TitreInconnuException;

    /**
     * Renvoie un titre boursier
     * @param mnemo le mnémonique du titre
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException si le titre n'existe pas
     */
    public String getTitre(String mnemo) throws TitreInconnuException;

    /**
     * Renvoie la liste des mnémoniques des titres
     * @return une liste JSON de la forme ["GOOG","MSOFT"]
     */
    public String getListeTitres();

    /**
     * Met à jour le cours d'un titre
     * @param t un titre au format JSON de la forme { mnemo : "MSOFT", cours : 3.5 } 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException  si le titre n'existe pas
     */
    public String majTitre(String t) throws TitreInconnuException;

    /**
     * Met à jour le cours d'un titre
     * @param id le mnémonique tu titre
     * @param cours le nouveau cours du titre 
     * @return le titre au format JSON de la forme {"mnemo":"MSOFT","nom":"Microsoft","cours":3.5,"datecours":1475755463241,"variation":0.0}
     * @throws TitreInconnuException  si le titre n'existe pas
     */
    public String majTitre(String id, String cours) throws TitreInconnuException;

}
