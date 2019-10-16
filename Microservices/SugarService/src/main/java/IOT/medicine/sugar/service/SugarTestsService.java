package IOT.medicine.sugar.service;

import IOT.medicine.sugar.pojo.CreateTestsCmd;
import IOT.medicine.sugar.pojo.SugarTests;
import IOT.medicine.sugar.repo.SugarTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SugarTestsService {

    @Autowired
    SugarTestsRepository sugarTestsRepository;

    public void saveAnalysis(CreateTestsCmd createAnalysisCm){

        SugarTests analysis = new SugarTests();

        analysis.setAnalysisTime(new Date());
        analysis.setDeviceId(createAnalysisCm.getDeviceId());
        analysis.setGlucose(createAnalysisCm.getGlucose());

        sugarTestsRepository.save(analysis);
    }
}
