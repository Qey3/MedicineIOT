package iotms.medicine.sugar.service;

import my.entity.sugarMS.RowSugarData;
import my.entity.sugarMS.SugarTests;
import iotms.medicine.sugar.repo.SugarTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SugarTestsService {

    @Autowired
    SugarTestsRepository sugarTestsRepository;

    public void saveAnalysis(RowSugarData rowSugarData) {

        SugarTests analysis = new SugarTests();

        analysis.setAnalysisTime(new Date());
        analysis.setDevice(rowSugarData.getDeviceId());
        analysis.setGlucose(rowSugarData.getGlucose());

        sugarTestsRepository.save(analysis);
    }
}
