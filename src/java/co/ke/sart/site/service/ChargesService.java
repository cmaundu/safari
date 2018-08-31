/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.service;

import co.ke.sart.site.entity.ChargeMatrix;
import co.ke.sart.site.entity.PaymentMode;
import co.ke.sart.site.interfaces.ICharges;
import co.ke.sart.site.model.Attendance;
import co.ke.sart.site.model.ChargeDisplay;
import co.ke.sart.site.repository.ChargeMatrixRepository;
import co.ke.sart.site.repository.PaymentModeRepository;
import co.ke.sart.site.repository.ReportRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ChargesService {

    @Autowired
    ChargeMatrixRepository chargeMatrixRepository;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    PaymentModeRepository paymentModeRepository;

    @Autowired
    private ApplicationContext appContext;

    public Map<String, Object> getCharges(String lovType) {
        ReportRepository reportRepository = appContext.getBean("reportRepository", ReportRepository.class);
        Map<String, Object> modelAttributes = new TreeMap<>();
        String dbLovType = "";
        switch (lovType) {
            case "procedure":
                dbLovType = "PROCEDURE_DEF";
                modelAttributes.put("title", "Procedures");
                break;
            case"labtest":
                dbLovType = "LAB_TEST";
                modelAttributes.put("title", "Lab Tests");
                break;                        
            case"radiology":
                dbLovType = "RADIOLOGY_DEF";
                modelAttributes.put("title", "Radiologies");
                break;  
            case"attendance":
                dbLovType = "ATTENDANCE_TYPE";
                modelAttributes.put("title", "Attendances");
                break;    
            case"prescription":
                dbLovType = "PRESCRIPTION";
                modelAttributes.put("title", "Drugs");
                break;                    
        }

        ChargeDisplay cd = new ChargeDisplay();
        cd.setCdList(reportRepository.getChargeMatrix(dbLovType));
        cd.generateRows();
        modelAttributes.put("cdlist", cd);

        List<PaymentMode> paymentModes = this.toList(paymentModeRepository.findAll());
        paymentModes.sort((a, b) ->  a.getName().compareTo(b.getName()));
        modelAttributes.put("pmlist", paymentModes);
        return modelAttributes;
    }

    public List<ChargeMatrix> getChargeMatrix(String lovType, List<Integer> lovTypeIDs, int paymentMode) {
        List<ChargeMatrix> matrices = chargeMatrixRepository.findByLovTypeAndLovTypeIDInAndPaymentMode(lovType, lovTypeIDs, paymentMode);

        List<Integer> missingLovTypes = lovTypeIDs.stream().filter(p -> matrices.stream().noneMatch(e -> e.getLovTypeID() == p)).collect(Collectors.toList());
        if (missingLovTypes.size() > 0) {
            List<ChargeMatrix> defaultMatrices = chargeMatrixRepository.findByLovTypeAndLovTypeIDInAndPaymentMode(lovType, missingLovTypes, 0);

            matrices.addAll(defaultMatrices);
        }

        return matrices;
    }

    public int getPaymentType(int attendanceID) {
        int paymentType = 0;
        Attendance attendance = attendanceService.getAttendance(attendanceID);
        if (attendance != null) {
            paymentType = attendance.getPaymentTypeID();
        }
        return paymentType;
    }

    public List<ICharges> addCharges(List<ICharges> items, int attendanceID, String lovType) {
        int paymentType = getPaymentType(attendanceID);

        if (items != null) {
            List<Integer> lovs = items.stream().map(e -> e.getLovTypeLovID()).collect(Collectors.toList());

            System.out.println("Type: " + lovType + " Lovs :" + lovs.toString() + "PaymentType: " + paymentType);
            List<ChargeMatrix> chargeMatrices = getChargeMatrix(lovType, lovs, paymentType);

            System.out.println(chargeMatrices);

            items.forEach(p -> {
                Optional<Double> amount = chargeMatrices.stream()
                        .filter(c -> c.getLovTypeID() == p.getLovTypeLovID() && c.getPaymentMode() == paymentType)
                        .findFirst().map(c -> c.getAmount());

                if (!amount.isPresent()) {
                    amount = chargeMatrices.stream()
                            .filter(c -> c.getLovTypeID() == p.getLovTypeLovID() && c.getPaymentMode() == 0)
                            .findFirst().map(c -> c.getAmount());
                }
                p.setChargeAmount(amount.isPresent() ? amount.get() : 0);
                System.out.println(amount);
            });

        }

        return items;
    }

    private <E> List<E> toList(Iterable<E> i) {
        List<E> list = new ArrayList<>();
        i.forEach(list::add);
        return list;
    }
}
