

package com.afriland.packageservices.dto;
import com.afriland.packageservices.entity.OTP;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CBSClientDTO {


    private String matricule;
    private String accountNo;
    private String accountName;
    private String customerName;
    private String sexe;
    private String adresse;
    //private String dataNaissance;
    private String lieuNaissance;
    private String departNaissance;
    private String situation;
    private String typePiece;
    private String numCNI;
    private String niu;
    private String dateDelivrance;
    private String lieuDelivrance;
    private String dateExpiration;
    private String ville;
    private String codeGestionnaire;
    private String loginGestionnaire;
    private List<String> comptes;
    private List<String> telephones;
    private List<String> adresseMails;

    private List<OtpDTO> otpList = new ArrayList<>();

    public String toString() {

        return getMatricule() + " - " + getAccountName() + " - " + getAccountNo();
    }
}
