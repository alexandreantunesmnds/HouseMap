package com.example.housemap.model;

import java.io.Serializable;

public class FabriqueNumero implements Serializable {
        private static FabriqueNumero instance = new FabriqueNumero();
        private int cptPiece;

        /**
         * Constructeur
         */
        private FabriqueNumero(){
            this.cptPiece = 0;
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
        public int getNumeroEtape(){
            return this.cptPiece++;
        }

        /**
         * Fonction reset qui remet les compteurs à zéro
         */
        public void reset(){
            this.cptPiece = 0;
        }
    }
