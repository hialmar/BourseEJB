/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bourse.miage.tp.core.entities;

import java.util.Calendar;

/**
 *
 * TitreBoursier
 *
 * @author Cédric Teyssié  <cedric.teyssie@irit.fr>, IRIT-SIERA, Université Paul Sabatier
 * @version 0.1, 3 oct. 2016
 * @since 0.1, 3 oct. 2016
 */
// BourseEJB
// entities
// TitreBoursier.java
public class TitreBoursier {

    private String mnemo;
    private String nom;
    private double cours;
    private long datecours;
    private double variation;

    /**
     * Constructeur
     * @param mnemo le mnémonique du titre
     * @param nom le nom du titre
     * @param cours le cours du titre
     */
    public TitreBoursier(String mnemo, String nom, double cours) {
        this.mnemo = mnemo;
        this.nom = nom;
        this.cours = cours;
        this.datecours = Calendar.getInstance().getTimeInMillis();
        this.variation = 0;
    }

    /**
     * getter
     * @return le mnémonique tu titre
     */
    public String getMnemo() {
        return mnemo;
    }

    /**
     * setter
     * @param mnemo le mnémonique tu titre
     */
    public void setMnemo(String mnemo) {
        this.mnemo = mnemo;
    }

    /**
     * getter
     * @return le nom du titre
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter
     * @param nom le nom du titre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter
     * @return le cours du titre
     */
    public double getCours() {
        return cours;
    }

    /**
     * setter
     * Note : met à jour la date et la variation
     * @param cours le cours du titre
     */
    public void setCours(double cours) {
        this.setDatecours(Calendar.getInstance().getTimeInMillis());
        this.variation = (cours / this.cours) * 100;
        this.cours = cours;
    }

    /**
     * getter
     * @return la date de dernière quotation
     */
    public long getDatecours() {
        return datecours;
    }

    /**
     * setter
     * @param datecours la date de dernière quotation
     */
    public void setDatecours(long datecours) {
        this.datecours = datecours;
    }

    /**
     * getter
     * @return la variation depuis la dernière quotation
     */
    public double getVariation() {
        return variation;
    }

    /**
     * setter
     * @param variation la variation depuis la dernière quotation
     */
    public void setVariation(double variation) {
        this.variation = variation;
    }

}
