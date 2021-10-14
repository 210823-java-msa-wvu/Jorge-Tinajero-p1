package dev.tinajero.services;

import dev.tinajero.models.Reimbursement;
import dev.tinajero.models.Users;
import dev.tinajero.repos.ApprovalRepo;
import dev.tinajero.repos.DisplayReimRepo;

import java.util.ArrayList;
import java.util.List;

public class DisplayReimService {

    Reimbursement reimbursement = new Reimbursement();
    DisplayReimRepo displayRepo = new DisplayReimRepo();
    ApprovalRepo approvalRepo = new ApprovalRepo();

    public List<Reimbursement> getAll(boolean direct, boolean dphead, boolean benco){
        List<Reimbursement> arr = new ArrayList<Reimbursement>();

        if(benco == true){
            System.out.println("in benco");
            return displayRepo.getAllRequests();
        }
        else if(dphead == true){
            return displayRepo.getAllRequests();
        }
        else if( direct == true){
            System.out.println("direct super service");
            return displayRepo.getAllRequests();
        }
        else if(direct == true && dphead == true){
            return displayRepo.getAllRequests();
        }
        else{
            System.out.println("failed in display service");
            return null;
        }

    }

    public Boolean setApproval(Users u, int appId, String approval){

        if(u.isDepartH() && u.isDirectS()){
            if ("approve".equals(approval)) {
                approvalRepo.getDHApp(appId, true);
                return approvalRepo.getSuperApp(appId, true);
            } else if ("deny".equals(approval)) {
                return approvalRepo.getDanH(appId, false);
            }
            else {
                approvalRepo.getSuperApp(appId, false);
                return approvalRepo.getDanH(appId, false);
            }
        }
        if(u.isDirectS()) {
            if("approve".equals(approval)){
                System.out.println("inside directS");
                return approvalRepo.getSuperApp(appId, true);
            }
            else if("deny".equals(approval)){
                return approvalRepo.getSuperApp(appId, false);
            }
            else
                return approvalRepo.getSuperApp(appId, false);
        }
        else if(u.isDepartH()){
            if ("approve".equals(approval)) {
                System.out.println("inside if depH");
                return approvalRepo.getDHApp(appId, true);
            } else if ("deny".equals(approval)) {
                return approvalRepo.getDHApp(appId, false);
            }
            else
                return approvalRepo.getDHApp(appId, false);
        }
        else if(u.isBenCo()){
            if ("approve".equals(approval)) {
                approvalRepo.updateApp(appId,true);
                return approvalRepo.getBencoApp(appId, true);
            } else if ("deny".equals(approval)) {
                approvalRepo.updateApp(appId,false);
                return approvalRepo.getBencoApp(appId, false);
            }
            else
                return approvalRepo.getBencoApp(appId, false);

        }

        return null;

    }




}
