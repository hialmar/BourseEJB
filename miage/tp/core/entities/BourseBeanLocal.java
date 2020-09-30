/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.core.entities;

import miage.tp.core.exceptions.TitreExistantException;
import miage.tp.core.exceptions.TitreInconnuException;
import java.util.Collection;
import javax.ejb.Local;

/**
 * Interface de l'EJB qui stocke les informations des titres boursiers
 * @author Cédric Teyssié
 */
@Local
public interface BourseBeanLocal {

    /**
     * Ajoute un nouveau Titre Boursier
     * @param t le titre boursier
     * @return le titre boursier en précisant la date de création
     * @throws TitreExistantException si le titre existe déjà
     */
    public TitreBoursier ajouterTitre(TitreBoursier t) throws TitreExistantException;

    /**
     * Supprime un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @throws TitreInconnuException si le titre n'existe pas
     */
    public void retraitTitre(String mnemo) throws TitreInconnuException;

    /**
     * Permet de récupérer un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @return e titre boursier
     * @throws TitreInconnuException si le titre n'existe pas
     */
    public TitreBoursier getTitre(String mnemo) throws TitreInconnuException;

    /**
     * Permet de récupérer tous les titres boursiers
     * @return la collection des titres
     */
    public Collection<TitreBoursier> getListeTitres();

    /**
     * Met à jour le cours d'un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @param cours le nouveau cours
     * @throws TitreInconnuException si le titre n'existe pas
     */
    public void updateCours(String mnemo, double cours) throws TitreInconnuException;

}
