/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.core.entities;

import miage.tp.core.exceptions.TitreExistantException;
import miage.tp.core.exceptions.TitreInconnuException;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Singleton;

/**
 * Bean Singleton qui stocke les informations des titres boursiers
 * @author Cédric Teyssié
 */
@Singleton
public class BourseBean implements BourseBeanLocal {

    private HashMap<String, TitreBoursier> lestitres;

    public BourseBean() {
        this.lestitres = new HashMap<>();
    }

    /**
     * Ajoute un nouveau Titre Boursier
     * @param t le titre boursier
     * @return le titre boursier en précisant la date de création
     * @throws TitreExistantException si le titre existe déjà
     */
    @Override
    public TitreBoursier ajouterTitre(TitreBoursier t) throws TitreExistantException {
        if (this.lestitres.containsKey(t.getMnemo())) {
            throw new TitreExistantException();
        } else {
            this.lestitres.put(t.getMnemo(), t);
            return t;
        }
    }


    /**
     * Permet de récupérer un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @return e titre boursier
     * @throws TitreInconnuException si le titre n'existe pas
     */
    @Override
    public TitreBoursier getTitre(String mnemo) throws TitreInconnuException {
        if (!this.lestitres.containsKey(mnemo)) {
            throw new TitreInconnuException();
        } else {
            return this.lestitres.get(mnemo);
        }
    }

    /**
     * Permet de récupérer tous les titres boursiers
     * @return la collection des titres
     */
    @Override
    public Collection<TitreBoursier> getListeTitres() {
        return (Collection) this.lestitres.keySet();
    }

    /**
     * Supprime un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @throws TitreInconnuException si le titre n'existe pas
     */
    @Override
    public void retraitTitre(String mnemo) throws TitreInconnuException {
        if (!this.lestitres.containsKey(mnemo)) {
            throw new TitreInconnuException();
        } else {
            this.lestitres.remove(mnemo);
        }
    }

    /**
     * Met à jour le cours d'un titre boursier
     * @param mnemo le mnémonique du titre boursier
     * @param cours le nouveau cours
     * @throws TitreInconnuException si le titre n'existe pas
     */

    @Override
    public void updateCours(String mnemo, double cours) throws TitreInconnuException {
        if (!this.lestitres.containsKey(mnemo)) {
            throw new TitreInconnuException();
        } else {
            this.lestitres.get(mnemo).setCours(cours);
        }
    }
}
