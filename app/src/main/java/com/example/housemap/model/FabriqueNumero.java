package com.example.housemap.model;

import java.io.Serializable;

public class FabriqueNumero implements Serializable {
        private static FabriqueNumero instance = new FabriqueNumero();
        private int cptPiece;
        private int cptSortie;


    /**
         * Constructeur
         */
        private FabriqueNumero(){
        }

        /**
         * Fonction getInstance
         * @return FabriqueNumero qui est utilisé comme Singleton
         */
        public static FabriqueNumero getInstance(){
            return instance;
        }

        /**
         * Fonction getNumeroEtape
         * @return le numéro de l'étape qui commence à partir de 0
         */
        public int getNumeroPiece(){
            return this.cptPiece++;
        }

        /**
         * Fonction getNumeroEtape
         * @return le numéro de l'étape qui commence à partir de 0
         */
        public int getNumeroSortie(){
            return this.cptSortie++;
        }

        /**
         * Fonction reset qui remet les compteurs à zéro
         */
        public void reset(){
            this.cptPiece = 0;
            this.cptSortie = 0;
        }
    }
